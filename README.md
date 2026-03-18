# 💬 JavaChat — Socket-Based Chat Application

A real-time two-way chat application built with **Java Sockets**, **Multithreading**, and **Java Swing**. A Server and Client communicate over a local network with a clean graphical interface — messages sent instantly, displayed live.

---

## 📸 Preview

> _Replace with actual screenshots after running the app._

| Server Window | Client Window |
|---|---|
| ![server](#) | ![client](#) |

---

## 🚀 Features

- **Two-way real-time chat** — Server and Client can send and receive messages instantly
- **Java Swing GUI** — graphical chat window with scrollable message area
- **Multithreaded reading** — a background thread handles incoming messages without freezing the UI
- **Enter key to send** — press Enter in the input field to send a message
- **Exit handling** — typing `exit` terminates the connection and disables the input
- **Custom avatar/icon** — loads `IMAGE.png` as a display icon in the heading

---

## 🛠️ Tech Stack

| Component | Technology |
|---|---|
| Language | Java 8+ |
| Networking | `java.net.Socket`, `java.net.ServerSocket` |
| Concurrency | `java.lang.Thread` (Runnable lambda) |
| GUI | `javax.swing` — `JFrame`, `JTextArea`, `JTextField`, `JScrollPane` |
| I/O | `BufferedReader`, `PrintWriter` |

---

## 📁 Project Structure

```
JavaChat/
├── Server.java       # Server — listens on port 7778, accepts one client
├── Client.java       # Client — connects to server at 127.0.0.1:7778
├── IMAGE.png         # Avatar/icon shown in the chat window heading
└── README.md
```

---

## ⚙️ How It Works

```
┌──────────────────────┐                  ┌──────────────────────┐
│      Server.java     │   Socket :7778   │      Client.java     │
│                      │ ◄──────────────► │                      │
│  ServerSocket(7778)  │                  │  Socket(127.0.0.1,   │
│  accept() → Socket   │                  │          7778)       │
│                      │                  │                      │
│  startReading()      │  message text    │  startReading()      │
│  [background thread] │ ◄──────────────► │  [background thread] │
│                      │                  │                      │
│  KeyListener →       │                  │  KeyListener →       │
│  out.println(msg)    │ ──────────────►  │  out.println(msg)    │
└──────────────────────┘                  └──────────────────────┘
```

1. **`Server.java`** creates a `ServerSocket` on port `7778` and waits for a connection
2. **`Client.java`** connects to `127.0.0.1:7778`
3. Both sides set up `BufferedReader` (input) and `PrintWriter` (output) streams
4. Each side calls **`startReading()`** — a background thread that continuously reads incoming messages and appends them to the `JTextArea`
5. Sending uses a **`KeyListener`** on the `JTextField` — pressing Enter (keyCode `10`) reads the input, appends `"Me: ..."` locally, and writes to the socket stream
6. Typing **`exit`** closes the connection, shows an error dialog, and disables the input field

---

## 🔧 Getting Started

### Prerequisites

- Java JDK 8 or above
- `IMAGE.png` placed in the **same directory** as the compiled `.class` files (used as the heading icon)

---

### Step 1 — Compile

```bash
javac Server.java
javac Client.java
```

### Step 2 — Run the Server

```bash
java Server
```

Console output:
```
server started...
waiting for client....
```

### Step 3 — Run the Client (in a new terminal)

```bash
java Client
```

Console output:
```
sending request to server
connection done....
reader started
```

Both Swing windows open. Type in the input field of either window and press **Enter** to chat.

---

## 🖥️ Usage

| Action | How |
|---|---|
| Send a message | Type in the bottom text field → press **Enter** |
| End the chat | Type `exit` and press **Enter** |
| Scroll chat history | Use the scroll bar in the message area |

> ⚠️ When one side types `exit`, the other side sees a "connection terminated" dialog and the input is disabled.

---

## 🧵 Threading Model

| Thread | Where | Purpose |
|---|---|---|
| Main thread | `Server` / `Client` constructor | Sets up socket, GUI, event listeners |
| Reader thread | `startReading()` | Runs in background, reads lines from socket continuously |
| Swing EDT | `KeyListener` callback | Sends messages and updates UI on user input |

The reader thread uses a lambda `Runnable` passed to `new Thread(r1).start()` — keeping the GUI responsive while waiting for incoming messages.

---

## ⚙️ Configuration

To change the port or host, edit these lines:

```java
// Server.java
server = new ServerSocket(7778);

// Client.java
socket = new Socket("127.0.0.1", 7778);
```

To run across two machines on the same network, replace `"127.0.0.1"` in `Client.java` with the server machine's local IP (e.g., `"192.168.1.5"`).

---

## 📌 Limitations

- Supports only **one client** at a time (no multi-client broadcast)
- No **chat history** — messages are lost when the app closes
- No **encryption** — messages are sent as plain text
- `IMAGE.png` must exist in the working directory or the icon silently fails to load

---

## 🤝 Contributing

Pull requests are welcome!

1. Fork the repo
2. Create a branch: `git checkout -b feature/your-feature`
3. Commit: `git commit -m "Add your feature"`
4. Push: `git push origin feature/your-feature`
5. Open a Pull Request

---

## 📄 License

This project is licensed under the **MIT License**.

---

## 👨‍💻 Author

**Your Name**
- GitHub: [@your-username](https://github.com/your-username)

---

> ⭐ Star this repo if it helped you learn Java networking!
