package com.company;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class udpserver{
        public static void main(String args[]) throws Exception{
            DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData1 = new byte[1024];
            byte[] receiveData2 = new byte[1024];
            byte[] sendData = new byte[1024];
            while(true){
                DatagramPacket receivePacket1 = new DatagramPacket(receiveData1, receiveData1.length);
                serverSocket.receive(receivePacket1);

                DatagramPacket receivePacket2 = new DatagramPacket(receiveData2, receiveData2.length);
                serverSocket.receive(receivePacket2);

                String num = new String(receivePacket1.getData());
                num = num.trim();
                int client_num = Integer.parseInt(num);

                String power = new String(receivePacket2.getData());
                power = power.trim();
                int client_power = Integer.parseInt(power);

                System.out.println("Received: ");
                System.out.println("Base = "+client_num+"\nPower = "+client_power);

                InetAddress IPAddress = receivePacket2.getAddress();
                int port = receivePacket2.getPort();

                String result = String.valueOf(Math.pow(client_num,client_power));
                sendData = result.getBytes();

                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        }
    }
