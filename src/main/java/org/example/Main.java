package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Main {
    /*
    Создать окно клиента чата. Окно должно содержать JtextField для ввода логина, пароля,
    IP-адреса сервера, порта подключения к серверу, область ввода сообщений, JTextArea
    область просмотра сообщений чата и JButton подключения к серверу и отправки сообщения в чат.
    Желательно сразу сгруппировать компоненты, относящиеся к серверу сгруппировать на JPanel
    сверху экрана, а компоненты, относящиеся к отправке сообщения – на JPanel снизу
     */
    public static class Chat extends JFrame {
        private static final int WINDOW_HEIGHT = 400;
        private static final int WINDOW_WIDTH = 700;
        private static final int WINDOW_POSX = 800;
        private static final int WINDOW_POSY = 300;
        JButton btnSend = new JButton("Отправить");
        JLabel lblLogin = new JLabel("Login", SwingConstants.CENTER);
        JLabel lblPassword = new JLabel("Password", SwingConstants.CENTER);
        JLabel lblIP = new JLabel("IP", SwingConstants.CENTER);
        JLabel lblMessage = new JLabel("Сообщение", SwingConstants.LEFT);
        JLabel lblTextForSend = new JLabel("Введите текст", SwingConstants.LEFT);
        JTextField txtFieldLogin = new JTextField();
        JTextField txtFieldPassword = new JTextField();
        JTextField txtFieldIP = new JTextField();
        JTextField txtFieldMessage = new JTextField();
        JTextArea areaMessage = new JTextArea();
        JPanel panServer = new JPanel(new GridLayout(6, 2));
        JPanel panClient = new JPanel(new GridLayout(5, 1));
        String login;
        String password;
        String IP;
        String message;

        Chat() {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocation(WINDOW_POSX, WINDOW_POSY);
            setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            setTitle("ChatWindow");
            setResizable(false);
            panServer.add(lblLogin);
            panServer.add(txtFieldLogin);
            panServer.add(lblPassword);
            panServer.add(txtFieldPassword);
            panServer.add(lblIP);
            panServer.add(txtFieldIP);
            panClient.add(lblMessage);
            panClient.add(areaMessage);
            panClient.add(lblTextForSend);
            panClient.add(txtFieldMessage);
            panClient.add(btnSend);
            Logger msgLog = new Logger();
            HistoryReader hr = new HistoryReader();
            areaMessage.setText(hr.readHistory("History.txt"));

            btnSend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    message = txtFieldLogin.getText() + ": " + txtFieldMessage.getText() + "\n";
                    try {
                        msgLog.writeLog(txtFieldLogin.getText() + ": " + txtFieldMessage.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    areaMessage.append(message);
                    txtFieldMessage.setText(null);
                    System.out.println("Отправлено сообщение: " + message);
                }
            });
            txtFieldMessage.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed (KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER){
                        message = txtFieldLogin.getText() + ": " + txtFieldMessage.getText() + "\n";
                        areaMessage.append(message);
                        try {
                            msgLog.writeLog(txtFieldLogin.getText() + ": " + txtFieldMessage.getText());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        txtFieldMessage.setText(null);
                        System.out.println("Отправлено сообщение: " + message);
                    }
                }
            });
            setLayout(new GridLayout(2, 1));
            add(panServer);
            add(panClient);
            setVisible(true);
        }

    }

    public static void main(String[] args) {
        new Chat();
    }
}
