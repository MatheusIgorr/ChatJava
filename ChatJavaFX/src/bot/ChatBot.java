package ChatJavaFX.src.bot;

import java.io.*;
import java.net.*;
import java.util.Random;

public class ChatBot {
    private static final String[] RESPONSES = {
            "Interessante!",
            "Conte-me mais sobre isso.",
            "Não tenho certeza sobre isso.",
            "Você já considerou outras perspectivas?",
            "Isso me faz pensar...",
            "Ótimo ponto!"
    };

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println("ChatBot");
            System.out.println("Bot conectado!");

            String serverMessage;
            Random random = new Random();

            while ((serverMessage = in.readLine()) != null) {
                if (!serverMessage.contains("[ChatBot]:")) {
                    System.out.println("Recebido: " + serverMessage);
                    // Responder aleatoriamente a ~30% das mensagens
                    if (random.nextDouble() < 0.3) {
                        String response = RESPONSES[random.nextInt(RESPONSES.length)];
                        out.println(response);
                        Thread.sleep(1000); // Simular "digitação"
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}