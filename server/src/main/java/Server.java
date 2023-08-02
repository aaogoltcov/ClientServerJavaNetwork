import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try (
            ServerSocket serverSocket = new ServerSocket(8888)
        ) {
            System.out.println("Сервер запущен!");

            while (true) {
                try (
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.printf("Новое соединение установлено. Порт: %d\n", clientSocket.getPort());

                    String username = "";
                    boolean isAdult = false;

                    for (int question = 1; question < 4; question++) {
                        switch (question) {
                            case 1 -> {
                                out.println("Write your name");
                                username = in.readLine();
                            }
                            case 2 -> {
                                out.println("Are you child? (yes/no)");
                                isAdult = !in.readLine().equals("yes");
                            }
                            case 3 -> {
                                if (isAdult) {
                                    out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", username));
                                } else {
                                    out.println(String.format("Welcome to the kids area, %s! Let's play!", username));
                                }
                            }
                        }
                    }
                    out.println("closed");

                    break;
                }
            }
        } finally {
            System.out.println("Сервер закрыт!");
        }
    }
}
