// P2aClient.java
import java.io.*;
import java.net.Socket;

public class P2aClient {
   private Socket socket;
   private BufferedReader inFromServer;
   
   public void connectToServer() throws IOException {
      String serverAddress = "127.0.0.1";
      // serverAddress = "140.112.18.178";
      Socket socket = new Socket(serverAddress, 9090);
      this.socket = socket;
      inFromServer = new BufferedReader(
         new InputStreamReader(socket.getInputStream()));
      System.out.println("Connect to server at " + serverAddress + "..");
   }

   public void start() throws Exception {
      System.out.println(inFromServer.readLine());
      // TODO: finish it to communicate properly with the server..
      System.out.println(inFromServer.readLine());
      BufferedReader inFromUser = 
         new BufferedReader(new InputStreamReader(System.in));
      String sentence = inFromUser.readLine();
      DataOutputStream outToServer = 
         new DataOutputStream(socket.getOutputStream());
      outToServer.writeBytes(sentence + '\n');
      System.out.println(inFromServer.readLine());
      sentence = inFromUser.readLine();
      outToServer.writeBytes(sentence + '\n');
      System.out.println(inFromServer.readLine());
      sentence = inFromUser.readLine();
      outToServer.writeBytes(sentence + '\n');
      System.out.println(inFromServer.readLine());
   }


   /**
    * Runs the client application.
    */
   public static void main(String[] args) throws Exception {
      P2aClient client = new P2aClient();
      client.connectToServer();
      client.start();
      client.socket.close();
   }
}
