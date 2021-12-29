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
        final Scanner scanner = new Scanner(System.in);

        try {
            serverSocket = new ServerSocket(5000);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Thread sender = new Thread(new Runnable() {
                String message;
                @Override
                public void run() {
                    while (true) {
                        message = scanner.nextLine();
                        out.println(message);
                        out.flush();
                    }
                }
            });
            sender.start();
//        }catch(IOException event){
//            event.printStackTrace();
//        }

            Thread receive = new Thread(new Runnable() {
                String message;
                @Override
                public void run() {
                    try {
                        message = in.readLine();
                        while (message != null) {
                            System.out.println("Client : " + message);
                            message = in.readLine();
                        }
                        System.out.println("Client Disconnection!");
                        out.close();
                        clientSocket.close();
                        serverSocket.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receive.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
