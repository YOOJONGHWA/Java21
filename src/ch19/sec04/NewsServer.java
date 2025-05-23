package ch19.sec04;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class NewsServer {

    private static DatagramSocket datagramSocket = null;

    public static void main(String[] args) throws Exception {
        System.out.println("---------------------------------------------");
        System.out.println("서버를 종료하려면 q를 입력하고 Enter 키를 입력하세요");
        System.out.println("---------------------------------------------");

        startSever();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("q")) {
                break;
            }
        }
        scanner.close();

        stopServer();
    }
    private static void startSever(){

        Thread thread = new Thread(){

            @Override
            public void run(){

                try {
                    datagramSocket = new DatagramSocket(50001);
                    System.out.println("[서버] 시작됨");

                    while(true){

                        DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
                        datagramSocket.receive(receivePacket);
                        String newsKind = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");

                        SocketAddress socketAddress = receivePacket.getSocketAddress();

                        for (int i = 1; i <= 10; i++) {
                            String data = newsKind + ": 뉴스" + i;
                            byte[] bytes = data.getBytes("UTF-8");
                            DatagramPacket sendPacket = new DatagramPacket(bytes, 0, bytes.length, socketAddress);
                            datagramSocket.send(sendPacket);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("[서버]" + e.getMessage());
                }
            }
        };
        thread.start();
    }

    private static void stopServer(){

        datagramSocket.close();
        System.out.println("[서버] 종료됨");
    }
}
