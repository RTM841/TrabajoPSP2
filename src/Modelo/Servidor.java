/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author ruben
 */
public class Servidor {

    private static final int PUERTO = 9876;
    private static final int TAMANO_BUFFER = 1024;
    private static final int MAX_INTENTOS = 4;

    private List<Usuario> usuarios; // Lista de usuarios conectados
    private DatagramSocket socket;
    private ExecutorService executorService; // Pool de hilos para manejar a los usuarios

    private class Usuario {

        private int id;
        private InetAddress direccion;
        private int puerto;
        private int intentos;
        private int premios;

        public Usuario(int id, InetAddress direccion, int puerto) {
            this.id = id;
            this.direccion = direccion;
            this.puerto = puerto;
            this.intentos = 0;
            this.premios = 0;
        }

        public int getId() {
            return id;
        }

        public InetAddress getDireccion() {
            return direccion;
        }

        public int getPuerto() {
            return puerto;
        }

        public int getIntentos() {
            return intentos;
        }

        public void incrementarIntentos() {
            intentos++;
        }

        public int getPremios() {
            return premios;
        }

        public void incrementarPremios() {
            premios++;
        }
    }

    public Servidor() {
        usuarios = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(10); // Pool de 10 hilos
    }

    public void iniciar() {
        

        try {
            socket = new DatagramSocket(PUERTO);
            System.out.println("Servidor iniciado en el puerto " + PUERTO);
            System.out.println("Posiciones con premio [0,0][1,3][2,4][2,0]");

            while (true) {
                byte[] buffer = new byte[TAMANO_BUFFER];
                DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteEntrada);

                InetAddress direccionCliente = paqueteEntrada.getAddress();
                int puertoCliente = paqueteEntrada.getPort();

                // Crear un nuevo hilo para manejar al usuario
                executorService.submit(() -> manejarUsuario(direccionCliente, puertoCliente));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            executorService.shutdown(); // Apagar el pool de hilos al finalizar
        }
    }

    private void manejarUsuario(InetAddress direccion, int puerto) {
        Usuario usuario = obtenerUsuarioPorDireccion(direccion);

        if (usuario == null) {
            // Nuevo usuario, asignar ID y agregar a la lista
            int nuevoId = usuarios.size() + 1;
            usuario = new Usuario(nuevoId, direccion, puerto);
            usuarios.add(usuario);
        }

        // Incrementar el número de intentos del usuario
        usuario.incrementarIntentos();

        System.out.println("Usuario " + usuario.getId() + " conectado desde "
                + usuario.getDireccion() + ":" + usuario.getPuerto()
                + " - Intento: " + usuario.getIntentos()
                + " - Premios: " + usuario.getPremios());

        // Realizar la lógica del juego y verificar si el usuario ha ganado un premio
        if (usuario.getIntentos() <= MAX_INTENTOS) {
            if (haGanadoPremio()) {
                // Incrementar el número de premios del usuario
                usuario.incrementarPremios();
                System.out.println("¡El usuario " + usuario.getId() + " ha ganado un premio!");
            }
        }

        // Reenviar el tablero a todos los clientes conectados
        enviarTableroAClientes();
    }

    private Usuario obtenerUsuarioPorDireccion(InetAddress direccion) {
        for (Usuario usuario : usuarios) {
            if (usuario.getDireccion().equals(direccion)) {
                return usuario;
            }
        }
        return null;
    }

    private void enviarTableroAClientes() {
        // Implementar el código del envío del tablero a los clientes
        // (Este método debe ser personalizado según tus necesidades específicas)
    }

    private boolean haGanadoPremio() {
        // Implementar la lógica para determinar si el usuario ha ganado un premio
        // (Por ejemplo, utilizando una condición aleatoria)
        return Math.random() < 0.25; // 25% de probabilidad de ganar un premio
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciar();

        // Matriz de 3x4
        String[][] tablero = new String[3][4];

        tablero[0][0] = "1000€";
        tablero[1][3] = "Viaje";
        tablero[2][4] = "Play 5";
        tablero[2][0] = "Entradas Final Champions";
        
    }
}
