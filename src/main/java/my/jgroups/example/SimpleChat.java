package my.jgroups.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

public class SimpleChat extends ReceiverAdapter {
    JChannel channel;
    String user_name = System.getProperty("user.name", "n/a");

    private void start() throws Exception {
        channel = new JChannel();
        channel.setReceiver(this);
        channel.connect("ChatCluster");
        eventLoop();
        channel.close();
    }

    public void viewAccepted(View new_view) {
        System.out.println("** view: " + new_view.getViewId().getId());
    }

    public void receive(Message msg) {
        System.out.println("Message Sent by:"+msg.getSrc() + " : " + msg.getObject());
    }

    private void eventLoop() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print("> ");
                System.out.flush();

                String line = in.readLine().toLowerCase();

                if (line.startsWith("quit") || line.startsWith("exit")) {
                    break;
                }

                line = "[" + user_name + "] " + line;

                // Send the message to cluster, so that all other instances in the same cluster will receive the Message.
                Message msg = new Message(null, null, line);

                channel.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws Exception {
        new SimpleChat().start();
    }
}
