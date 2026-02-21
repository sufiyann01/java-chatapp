import java.net.*;
import java.io.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Server extends JFrame{
    ServerSocket  server;
    Socket socket;
    BufferedReader rd;
    PrintWriter out;
    JLabel heading=new JLabel("SERVER");
    Font font=new Font("Arial",Font.BOLD,20);
    JTextArea message=new JTextArea();
    JTextField input=new JTextField();
    public Server(){
        try{
    server=new ServerSocket(7778);
   System.out.println("server started...");
   System.out.println("waiting for client....");
   socket=server.accept();
   rd=new BufferedReader(new InputStreamReader(socket.getInputStream()));
   out=new PrintWriter(socket.getOutputStream());
   createGUI();
   eventHandler();
   startReading();
//    startWriting();
        }
    catch(Exception e){
        e.printStackTrace();
    }
}
public void eventHandler(){
    input.addKeyListener(new KeyListener() {
        @Override
        public void keyPressed(KeyEvent e){
        }
        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode()==10){
            String input1=input.getText();
            message.append("Me: "+input1+"\n");
            out.println(input1);
            out.flush();
            input.setText("");
            input.requestFocus();
            }
        }
    });
}
public void createGUI(){
    this.setTitle("Server Messenger");
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(500,500);

    heading.setFont(font);
    message.setFont(font);
    input.setFont(font);
    heading.setHorizontalAlignment(SwingConstants.CENTER);
    heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    ImageIcon originalIcon = new ImageIcon("IMAGE.png");
    Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    heading.setIcon(new ImageIcon(scaledImage)); 

    heading.setHorizontalTextPosition(SwingConstants.CENTER);
    heading.setVerticalTextPosition(SwingConstants.BOTTOM);
    input.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    message.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    message.setEditable(false);


    this.setLayout(new BorderLayout());



    this.add(heading,BorderLayout.NORTH);
    JScrollPane jScrollPane=new JScrollPane(message);
    this.add(jScrollPane,BorderLayout.CENTER);
    this.add(input,BorderLayout.SOUTH);
     this.setVisible(true);




}
    public void startReading(){
        Runnable r1=()->{
            System.out.println("reader started");

        while(true){
            try{
                String msg=rd.readLine();
                if(msg.equals("exit")){
                    System.out.println("chat exited successfully");
                    JOptionPane.showMessageDialog(this,"connection terminated","Error",JOptionPane.ERROR_MESSAGE);
                    input.setEnabled(false);
                    socket.close();
                    break;
                }
                message.append("Client: "+msg+"\n");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
       };
       new Thread(r1).start();

    }
//     public void startWriting(){
//         Runnable r2= () ->{
//             System.out.println("writer started");
//             while(true){
//                 try{
//                 BufferedReader msg2=new BufferedReader(new InputStreamReader(System.in));
//                 String content=msg2.readLine();
//                 out.println(content);
//                 out.flush();
//             }
//             catch(Exception e){
//                 e.printStackTrace();
//             }
//         }
//     };
//        new Thread(r2).start();
// }
public  static void main(String []args){
    new Server();
} 
}