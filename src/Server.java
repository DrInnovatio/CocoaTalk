import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {

        final ServerSocket serverSocket;
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner scanner = new Scanner(System.in);  // It can read data from the user’s keybord.

        try{
            serverSocket = new ServerSocket(5000);
            clientSocket = serverSocket.accept();
            System.out.println("Waiting for the connection request.....");
            out = new PrintWriter(clientSocket.getOutputStream());    //  out is ready to write data into clientsocket.
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  //in is to read from clientSocket.

            Thread sender = new Thread(new Runnable(){
                String message;
                @Override
                public void run() {
                    while(true){
                        message = scanner.nextLine();
                        out.println(message);
                        out.flush();
                    }
                }
            });
            sender.start();

            Thread receiver = new Thread(new Runnable(){
                String message;
                @Override
                public void run() {
                    try {
                        message = in.readLine();
                        while (message != null) {
                            System.out.println("Client : " + message);
                            message = in.readLine();
                        }
                        System.out.println("Client Connection!");
                        out.close();
                        clientSocket.close();
                        serverSocket.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
