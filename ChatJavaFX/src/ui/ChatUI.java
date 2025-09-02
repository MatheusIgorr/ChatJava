package ChatJavaFX.src.ui;

import ChatJavaFX.src.client.ChatClient;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ChatUI extends Application {
    private ChatClient client;
    private TextArea chatArea;
    private TextField messageField;
    private String username;

    @Override
    public void start(Stage primaryStage) {
        // Tela de login
        TextInputDialog loginDialog = new TextInputDialog();
        loginDialog.setTitle("Chat Login");
        loginDialog.setHeaderText("Bem-vindo ao Chat");
        loginDialog.setContentText("Digite seu nome:");
        loginDialog.showAndWait().ifPresent(name -> username = name);

        try {
            client = new ChatClient();
            client.connect("localhost", 12345, username);
        } catch (Exception e) {
            showAlert("Erro de conexão");
            System.exit(0);
        }

        // Configuração da UI
        BorderPane root = new BorderPane();
        chatArea = new TextArea();
        chatArea.setEditable(false);

        messageField = new TextField();
        messageField.setPromptText("Digite sua mensagem...");
        messageField.setOnAction(e -> sendMessage());

        Button sendButton = new Button("Enviar");
        sendButton.setOnAction(e -> sendMessage());

        HBox bottomBox = new HBox(messageField, sendButton);
        bottomBox.setSpacing(10);
        bottomBox.setPadding(new Insets(10));

        root.setCenter(chatArea);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Chat - " + username);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Thread para receber mensagens
        new Thread(this::receiveMessages).start();
    }

    private void sendMessage() {
        String message = messageField.getText();
        if (!message.trim().isEmpty()) {
            client.sendMessage(message);
            messageField.clear();
        }
    }

    private void receiveMessages() {
        try {
            while (true) {
                String message = client.receiveMessage();
                if (message != null) {
                    javafx.application.Platform.runLater(() -> {
                        chatArea.appendText(message + "\n");
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    @Override
    public void stop() throws Exception {
        if (client != null)
            client.disconnect();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}