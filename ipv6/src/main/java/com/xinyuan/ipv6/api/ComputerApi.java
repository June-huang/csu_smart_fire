package com.xinyuan.ipv6.api;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class ComputerApi {


    /**
     * 电脑网卡唤醒 网卡唤醒一定要连着网线，不然没用 网卡地址格式 AB-CD-EF-AB-CD-EF EC-A8-6B-82-0B-45
     *
     * @param mac 必选参数 网卡地址
     * @param ip  可选参数 ip地址默认为255.255.255.255（这是在局域网内，如果在外网访问内网的话需要用到外网IP地址）
     */
    public static boolean OpenComputer(String mac, String ip) {
        int port = 9;
        String magicPacage = "0xFFFFFFFFFFFF";
        for (int i = 0; i < 16; i++) {
            magicPacage += mac;
        }
        return sendSocket(ip, port, magicPacage);
    }

    public static boolean closeComputer(String ipAddress) {
        int port = 1234;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ipAddress, port));
            OutputStream outputStream = socket.getOutputStream();
            //关机命令
            outputStream.write(("close").getBytes("utf-8"));
            socket.close();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    private static boolean sendSocket(String ip, int port, String magicPacage) {
        byte[] command = hexToBinary(magicPacage.replace("-", ""));
        try {
            InetAddress address = InetAddress.getByName(ip);
            MulticastSocket socket = new MulticastSocket(port);
            DatagramPacket packet = new DatagramPacket(command, command.length, address, port);
            System.out.println(packet.getSocketAddress());
            socket.send(packet);
            TimeUnit.SECONDS.sleep(3);
            socket.close();
            return true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return true;
        }
    }


    private static byte[] hexToBinary(String hexString) {
        byte[] result = new byte[hexString.length() / 2 - 1];
        hexString = hexString.toUpperCase().replace("0X", "");
        char tmp1 = '0';
        char tmp2 = '0';
        for (int i = 0; i < hexString.length(); i += 2) {
            tmp1 = hexString.substring(i, i + 1).toCharArray()[0];
            tmp2 = hexString.substring(i + 1, i + 2).toCharArray()[0];
            result[i / 2] = (byte) ((hexToDec(tmp1) << 4) | (hexToDec(tmp2)));
        }
        return result;
    }

    private static byte hexToDec(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


}
