package com.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        try {
            Socket socket = new Socket("localhost", 3000);
            System.out.println("Socket inizializzato");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            
            Scanner scanner = new Scanner(System.in);
            int risposta = 0;
            
            do{
                String in = scanner.nextLine();
                output.writeBytes(in + "\n");
                
                risposta = Integer.parseInt(input.readLine());
                System.out.println(risposta);
            }while(risposta != 3);

            System.out.println(input.readLine());

            scanner.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("ERRORE GENERICO");
            System.out.println(e.getMessage());
            return;
        }

    }
}
