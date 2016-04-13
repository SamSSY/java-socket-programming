import java.io.*;
import java.net.*;

class UDPServer {
	public static void main(String argv[]) throws Exception
	{
		DatagramSocket serverSocket = new DatagramSocket(9090);
		System.out.println("server ready...");
		int count = 0;
		int disorderCount = 0;
		int oldData = 0;
		while(true){

			DatagramPacket receivePacket =
				new DatagramPacket(new byte[128], 128);
			serverSocket.receive(receivePacket);
			++count;
			String receiveData = new String(receivePacket.getData());
			
			int newData = Integer.parseInt(receiveData.trim());
			System.out.println("oldData: " + oldData + " newData: " + newData);
			if(count == 1) {
				oldData = newData;
			}
			if(newData < oldData){
				++disorderCount;
			}
			oldData = newData;
			InetAddress ipAddr = receivePacket.getAddress();
			int port = receivePacket.getPort();
			System.out.println("packet number" + count + " :receive from " + ipAddr + ":" + port +
				" : " + receiveData + " disorderCount: " + disorderCount);
		}
	} 
}