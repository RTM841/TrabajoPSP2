/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.Cliente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAM
 */
public class ClienteHilo extends Thread {

    private static int intentos = 0;
    private static int premios = 0;
    Cliente clienteVista;
    private String mensajeUser;

    public ClienteHilo(Cliente clienteVista) {
        this.clienteVista = clienteVista;
    }

    @Override
    public void run() {
        try {
            empezarCliente();
        } catch (IOException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void empezarCliente() throws IOException {
        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;
        String idRecibido = "";
        Tablero miTablero = null;
        ObjectInputStream objectInputStream = null;
        boolean register = false;
        // Creamos un socket en el lado cliente, enlazado con un
        // servidor que está en la misma máquina que el cliente
        // y que escucha en el puerto 4444
        try {
                socketCliente = new Socket("localhost", 4444);
                // Obtenemos el canal de entrada
                entrada = new BufferedReader(
                        new InputStreamReader(socketCliente.getInputStream()));
                // Obtenemos el canal de salida
                salida = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(socketCliente.getOutputStream())), true);

            } catch (IOException e) {
                System.err.println("No puede establecer canales de E/S para la conexión");
                System.exit(-1);
            }
        
        
        /*if (clienteVista.getMensaje().equalsIgnoreCase("")) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } */
        //mensajeUser = clienteVista.getMensaje();
        // La envia al servidor por el OutputStream
        if (!register) {
            String conectado = "REGISTER";
            salida.println(conectado);
            register = true;
            // Recibe la respuesta del servidor por el InputStream
            idRecibido = entrada.readLine();
            // Envía a la salida estándar la respuesta del servidor
            System.out.println("Respuesta servidor: " + idRecibido);
        }
        while (true) {
            try {
                socketCliente = new Socket("localhost", 4444);
                // Obtenemos el canal de entrada
                entrada = new BufferedReader(
                        new InputStreamReader(socketCliente.getInputStream()));
                // Obtenemos el canal de salida
                salida = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(socketCliente.getOutputStream())), true);
                objectInputStream = new ObjectInputStream(socketCliente.getInputStream());

            } catch (IOException e) {
                System.err.println("No puede establecer canales de E/S para la conexión");
                System.exit(-1);
            }

            try {
                miTablero = (Tablero) objectInputStream.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            String[] partes = obtenerArrayDesdeMensaje(mensajeUser);
            String premio = "";
            try {
                premio = miTablero.obtenerPremio(Integer.valueOf(partes[0]), Integer.valueOf(partes[1]));
            } catch (InterruptedException ex) {
                Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
            clienteVista.appendTextArea(partes[0], partes[1], premio);

            // Libera recursos
            salida.close();
            entrada.close();
            socketCliente.close();

        }

    }

    // Método que devuelve un array de strings a partir de un mensaje
    private static String[] obtenerArrayDesdeMensaje(String mensaje) {
        // Dividir el mensaje utilizando la coma como delimitador
        return mensaje.split(",");
    }

}
