package ro.kazy.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Manages a single user(client) read/write operations
 * <p/>
 * Created by catalin on 12/30/13.
 */
public class UserManager extends Thread {

    // contains information about the current user
    private User user;
    // the socket that links the user(client) to this server
    private Socket socket;
    private PrintWriter bufferSender;
    // flag used to stop the read operation
    private boolean running;
    // used to notify certain user actions like receiving a message or disconnect
    private UserManagerDelegate managerDelegate;

    public UserManager(Socket socket, UserManagerDelegate managerDelegate) {
        this.user = new User();
        this.socket = socket;
        this.managerDelegate = managerDelegate;
        running = true;
    }

    public User getUser() {
        return user;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        super.run();

        System.out.println("S: Receiving...");

        try {

            //sends the message to the client
            bufferSender = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            //read the message received from client
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //in this while we wait to receive messages from client (it's an infinite loop)
            //this while it's like a listener for messages
            while (running) {

                String message = null;
                try {
                    message = in.readLine();
                } catch (IOException e) {
                    System.out.println("Error reading message: " + e.getMessage());
                }

                if (hasCommand(message)) {
                    continue;
                }

                if (message != null && managerDelegate != null) {
                    user.setMessage(message);
                    // notify message received action
                    managerDelegate.messageReceived(user, null);
                }
            }

        } catch (Exception e) {
            System.out.println("S: Error");
            e.printStackTrace();
        }

    }

    /**
     * Close the server
     */
    public void close() {

        running = false;

        if (bufferSender != null) {
            bufferSender.flush();
            bufferSender.close();
            bufferSender = null;
        }

        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("S: User " + user.getUsername() + " leaved the room.");
        socket = null;

        //todo close all user connections

    }

    /**
     * Method to send the messages from server to client
     *
     * @param message the message sent by the server
     */
    public void sendMessage(String message) {
        if (bufferSender != null && !bufferSender.checkError()) {
            bufferSender.println(message);
            bufferSender.flush();
        }
    }

    public boolean hasCommand(String message) {
        if (message != null) {
            if (message.contains(Constants.CLOSED_CONNECTION)) {
                close();
                // let the server know that this user was disconnected
                managerDelegate.userDisconnected(this);
                return true;
            } else if (message.contains(Constants.LOGIN_NAME)) {
                // parse the username
                user.setUsername(message.replaceAll(Constants.LOGIN_NAME, ""));
                user.setUserID(socket.getPort());
                // send a user connected message
                managerDelegate.userConnected(user);
                return true;
            }
        }

        return false;
    }

    /**
     * Used to talk with the TcpServer class or whoever wants to receive notifications from this manager
     */
    public interface UserManagerDelegate {

        /**
         * Called whenever a user is connected to the server
         *
         * @param connectedUser the connected user
         */
        public void userConnected(User connectedUser);

        /**
         * Called when a user is disconnected from the server
         *
         * @param userManager the manager of the disconnected user
         */
        public void userDisconnected(UserManager userManager);

        /**
         * Called when the manager receives a new message from the client
         *
         * @param fromUser the user that sent the message
         * @param toUser   the user that should receive the message
         */
        public void messageReceived(User fromUser, User toUser);

    }

}
