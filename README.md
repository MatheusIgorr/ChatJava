# ChatJava

Um sistema de **chat em tempo real** desenvolvido em **Java**, utilizando **Sockets** para comunicação cliente-servidor e **JavaFX** para interface gráfica.  
Além dos usuários humanos, o chat conta com um **ChatBot integrado** 🤖 que interage automaticamente com os participantes.

---

## 🚀 Funcionalidades
- Comunicação em tempo real entre múltiplos clientes.
- Servidor centralizado que faz broadcast das mensagens.
- Interface gráfica feita em **JavaFX**.
- Cliente **bot** que responde automaticamente a palavras-chave.
- Suporte a múltiplas conexões simultâneas com **threads**.
- Histórico exibido na tela do usuário (não se perde ao receber novas mensagens).

---

## 📂 Estrutura do Projeto

ChatJavaFX/
│
├── src/
│ ├── server/
│ │ └── ChatServer.java # Servidor principal
│ │
│ ├── client/
│ │ └── ChatClient.java # Cliente console
│ │
│ ├── ui/
│ │ └── ChatUI.java # Cliente com interface em JavaFX
│ │
│ └── bot/
│ └── ChatBot.java # IA que responde automaticamente
/
