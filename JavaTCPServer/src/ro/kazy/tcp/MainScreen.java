package ro.kazy.tcp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Description
 *
 * @author Catalin Prata
 *         Date: 2/12/13
 */
public class MainScreen extends JFrame {

    private JTextArea messagesArea;
    private JButton sendButton;
    private JTextField message;
    private JButton startServer;
    private JButton stopServer;
    private TcpServer mServer;
    public static final String SERVER_NAME = "Server";

    public MainScreen() {

        super("MainScreen");

        JPanel panelFields = new JPanel();
        panelFields.setLayout(new BoxLayout(panelFields, BoxLayout.X_AXIS));

        JPanel panelFields2 = new JPanel();
        panelFields2.setLayout(new BoxLayout(panelFields2, BoxLayout.X_AXIS));

        //here we will have the text messages screen
        messagesArea = new JTextArea();
        messagesArea.setColumns(30);
        messagesArea.setRows(10);
        messagesArea.setEditable(false);

        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get the message from the text view
                String messageText = message.getText();
                // add message to the message area
                messagesArea.append("\n" + messageText);
                if (mServer != null) {
                    // send the message to the client
                    mServer.sendMessage(new User(SERVER_NAME, messageText));
                }
                // clear text
                message.setText("");
            }
        });

        startServer = new JButton("Start");
        startServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //creates the object OnMessageReceived asked by the TCPServer constructor
                mServer = new TcpServer(new TcpServer.OnMessageReceived() {
                    @Override
                    //this method declared in the interface from TCPServer class is implemented here
                    //this method is actually a callback method, because it will run every time when it will be called from
                    //TCPServer class (at while)
                    public void messageReceived(String message) {
                        messagesArea.append("\n " + message);
                    }
                });
                mServer.start();

                // disable the start button and enable the stop one
                startServer.setEnabled(false);
                stopServer.setEnabled(true);

            }
        });

        stopServer = new JButton("Stop");
        stopServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (mServer != null) {
                    mServer.close();
                }

                // disable the stop button and enable the start one
                startServer.setEnabled(true);
                stopServer.setEnabled(false);

            }
        });

        //the box where the user enters the text (EditText is called in Android)
        message = new JTextField();
        message.setSize(200, 20);

        //add the buttons and the text fields to the panel
        panelFields.add(messagesArea);
        panelFields.add(startServer);
        panelFields.add(stopServer);

        panelFields2.add(message);
        panelFields2.add(sendButton);

        getContentPane().add(panelFields);
        getContentPane().add(panelFields2);


        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        setSize(300, 170);
        setVisible(true);
    }


}
