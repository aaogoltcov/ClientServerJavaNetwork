import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String host = "netology.homework";
        final int port = 8888;

        try (
            Socket clientSocket = new Socket(host, port);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            while (true) {
                String resp = in.readLine();

                if (resp.equals("closed")) {
                    break;
                }

                System.out.println(resp);
                String answer = reader.readLine();

                out.println(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Клиент закрыт!");
        }
    }
}
