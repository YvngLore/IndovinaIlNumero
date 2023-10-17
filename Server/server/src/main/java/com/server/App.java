package com.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class App 
{
    public static void main( String[] args )
    {
        try {
            ServerSocket server = new ServerSocket(3000);
            System.out.println("Server inizializzato");

            Socket socket = server.accept();
            System.out.println("Un client si è connesso!");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream output = new DataOutputStream(socket.getOutputStream()); 

            Random random = new Random();
            int numero_casuale = random.nextInt(1, 100);
            int tentativi = 0;
            int conversione = 0;
            
            do{
                String numero_ricevuto = input.readLine();
                conversione = Integer.parseInt(numero_ricevuto);

                if(conversione < numero_casuale){
                    output.writeBytes(1 + "\n");
                    tentativi++;
                }
                if(conversione > numero_casuale){
                    output.writeBytes(2 + "\n");
                    tentativi++;
                }
            }while(conversione != numero_casuale);

            tentativi++;
            output.writeBytes(3 + "\n");
            output.writeBytes(tentativi + "\n");

            server.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("ERRORE GENERICO");
            System.out.println(e.getMessage());
            return;
        }
    }
}