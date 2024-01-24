package Modelo;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAM
 */

class HiloServidor implements Runnable {

    private final static String COD_TEXTO = "UTF-8";
    private final Socket socketComunicacion;
    private final Tablero tablero;
    
    HiloServidor(Socket socketComunicacion,Tablero miTablero) {
        this.socketComunicacion = socketComunicacion;
        this.tablero = miTablero;
    }

    @Override
    public void run() {
        try (   
                InputStream isDeCliente = this.socketComunicacion.getInputStream();  
                OutputStream osACliente = this.socketComunicacion.getOutputStream();
                InputStreamReader isrDeCliente = new InputStreamReader(isDeCliente, COD_TEXTO);  
                BufferedReader brDeCliente = new BufferedReader(isrDeCliente);  
                OutputStreamWriter oswACliente = new OutputStreamWriter(osACliente, COD_TEXTO);  
                BufferedWriter bwACliente = new BufferedWriter(oswACliente)) {
               
                enviarObjetoAlCliente(socketComunicacion, tablero);
            
            
                String lineaRecibida;
            while ((lineaRecibida = brDeCliente.readLine()) != null && lineaRecibida.length() > 0) {
                System.out.println("Recibido: " + lineaRecibida);
                //Comprobar en el tablero si hay premio
                int[] coordenadas = obtenerCoordenadasDesdeMensaje(lineaRecibida);
                int fila = 0;
                int columna = 0;
                if (coordenadas != null) {
                     fila = coordenadas[0];
                     columna = coordenadas[1];
                } else {
                    System.out.println("El mensaje no tiene el formato correcto.");
                }
                String premio = tablero.obtenerPremio(fila, columna);
                if (!premio.equalsIgnoreCase("")) {
                    bwACliente.write(fila+","+columna+","+premio);
                }else{
                    bwACliente.write(fila+","+columna+","+"SIN PREMIO");
                }
                bwACliente.newLine();
                bwACliente.flush();
            }
        } catch (IOException ex) {
            System.out.println("Excepción de E/S");
            ex.printStackTrace();
            System.exit(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (this.socketComunicacion != null) {
                try {
                    this.socketComunicacion.close();
                    System.out.println("Cliente desconectado.");
                } catch (IOException ex) {
                }
            }
        }
    }

    // Método para obtener las coordenadas desde un mensaje
    private static int[] obtenerCoordenadasDesdeMensaje(String mensaje) {
        try {
            // Dividir el mensaje en partes usando el espacio como delimitador
            String[] partes = mensaje.split(" ");

            // Verificar si hay dos partes en el mensaje (fila y columna)
            if (partes.length == 2) {
                // Convertir las partes a enteros
                int fila = Integer.parseInt(partes[0]);
                int columna = Integer.parseInt(partes[1]);

                // Devolver las coordenadas como un array
                return new int[]{fila, columna};
            }
        } catch (NumberFormatException e) {
            // Manejar la excepción si las partes no son números enteros
            e.printStackTrace();
        }

        // Devolver null si el mensaje no tiene el formato correcto
        return null;
    }

    private static void enviarObjetoAlCliente(Socket clienteSocket, Object objeto) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(clienteSocket.getOutputStream())) {
            objectOutputStream.writeObject(objeto);
            System.out.println("Objeto enviado al cliente: " + objeto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

class ServidorMultihiloEco {

    public static void main(String[] args) {

        int numPuerto = 4444;
        int id = 1;
        Tablero miTablero = new Tablero();
        try ( ServerSocket socketServidor = new ServerSocket(numPuerto)) {
            System.out.printf("Creado socket de servidor en puerto %d. Esperando conexiones de clientes.\n", numPuerto);

           
            while (true) {    // Acepta una conexión de cliente tras otra
                Socket socketComNuevoCliente = socketServidor.accept();
                System.out.printf("Cliente conectado desde %s:%d.\n",
                        socketComNuevoCliente.getInetAddress().getHostAddress(),
                        socketComNuevoCliente.getPort());
                
                Thread hiloSesion = new Thread(new HiloServidor(socketComNuevoCliente,miTablero));
                hiloSesion.start();
            }

        } catch (IOException ex) {
            System.out.println("Excepción de E/S");
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    

}
