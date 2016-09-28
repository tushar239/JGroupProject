package my.jgroups.example;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

public class AnotherSimpleChat extends ReceiverAdapter {
    JChannel channel;
    String user_name = System.getProperty("user.name", "n/a");

    private void start() throws Exception {
        channel = new JChannel();
        channel.setReceiver(this);
        channel.connect("ChatCluster2");
        eventLoop();
        channel.close();
    }
    public void viewAccepted(View new_view) {
        System.out.println("** view: " + new_view.getViewId().getId());
    }

    public void receive(Message msg) {
        System.out.println("Message Sent by:"+msg.getSrc() + " : " + msg.getObject());
    }
    public static void main(String[] args) throws Exception {
        new AnotherSimpleChat().start();
    }
    private void eventLoop() throws Exception {
        Message msg = new Message(null, null, "Hi, I am a message from AnotherSimpleChat.");
        channel.send(msg);
        
    }
}
