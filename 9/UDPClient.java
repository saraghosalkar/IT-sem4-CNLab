package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {


        public static void main(String args[]) throws Exception{
            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress IPAddress = InetAddress.getByName("localhost");

            byte[] sendData1 = new byte[1024];
            byte[] sendData2 = new byte[1024];
            byte[] receiveData = new byte[1024];

            System.out.println("\nProgram for power of a number:");
            System.out.println("\nEnter the base number : ");
            String base = inFromUser.readLine();
            sendData1 = base.getBytes();

            System.out.println("\nEnter the power : ");
            String power = inFromUser.readLine();
            sendData2 = power.getBytes();

            DatagramPacket sendPacket1 = new DatagramPacket(sendData1, sendData1.length, IPAddress, 9876);
            clientSocket.send(sendPacket1);

            DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, IPAddress, 9876);
            clientSocket.send(sendPacket2);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String result = new String(receivePacket.getData());
            System.out.println("FROM SERVER:");
            System.out.println(base+" to the power "+power+" = "+result);
            clientSocket.close();
        }
    }
