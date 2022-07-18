package ecoremoto;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) throws SocketException, IOException {

        final int PUERTO = 5001;
        byte[] buffer = new byte[1024];
        
        try {
            System.out.println("Iniciado el servidor UDP");
            //Creacion del socket
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);
            //Siempre atendera peticiones
            while(true){
                //Preparo la respuesta
               DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                
                //Recibo el datagrama
                socketUDP.receive(peticion);
                System.out.println("Recibo la informacion del cliente");
                
                //Convierto lo recibido y mostrar el mensaje
                String mensaje = new String(peticion.getData());
                System.out.println(mensaje);
                
                //Obtengo el puerto y la direccion de origen
                //Sino se quiere responder, no es necesario
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();
                 
                System.out.println("*****************************");
               
                // mensaje = "¡Hola mundo desde el servidor!";
                buffer = mensaje.getBytes();

                //creo el datagrama
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);

                //Envio la información
                System.out.println("Envio la informacion al cliente");
                socketUDP.send(respuesta);
                System.out.println(mensaje);
                
            }

        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}