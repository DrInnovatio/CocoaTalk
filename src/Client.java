import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner scanner = new Scanner(System.in);

        try{
            clientSocket = new Socket("192.168.1.99", 5000);
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

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
                            System.out.println("Server : " + message);
                            message = in.readLine();
                        }
                        System.out.println("Server disconnection!");
                        out.close();
                        clientSocket.close();
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
