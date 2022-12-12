package cn.ledyyer.plugin.service.normal;

import com.tibco.tibrv.*;

public class TibcoSenderService {
    private final String service;
    private final String network;
    private final String daemon;
    private final String subjectName;
    private final String fieldName;
    private TibrvTransport transport = null;
    public TibcoSenderService(String service, String network, String daemon, String subjectName, String fieldName){
        this.service = service;
        this.network = network;
        this.daemon = daemon;
        this.subjectName = subjectName;
        this.fieldName = fieldName;
    }

    public void open(){
        try{
            if (Tibrv.isValid()) {  // if Tibco has started, don't do anything
                System.out.println("Tibrv has started");
            }else {
                Tibrv.open(Tibrv.IMPL_NATIVE);
            }
        }catch (TibrvException e){
            System.err.println("Faild to open Tibrv in native implementation: ");
            e.printStackTrace();
            System.exit(0);
        }

        try {
            transport = new TibrvRvdTransport(service,network,daemon);
        } catch (TibrvException e) {
            System.err.println("Failed to  create TibrvRvdTransport: ");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void send(String message){
        TibrvMsg msg = new TibrvMsg();
        try {
            msg.setSendSubject(subjectName);
        } catch (TibrvException e) {
            System.err.println("Failed to set send subject:");
            e.printStackTrace();
            System.exit(0);
        }

        try {
            // Send one message for each parameter
            System.out.println("Publishing: subject=" + msg.getSendSubject() +
                    " \"" + message + "\"");
            msg.update(fieldName, message);
            transport.send(msg);
        } catch (TibrvException e) {
            System.err.println("Error sending a message:");
            e.printStackTrace();
            System.exit(0);
        }

    }

    public void send(String message, double timeout){
        TibrvMsg msg = new TibrvMsg();
        try {
            msg.setSendSubject(subjectName);
        } catch (TibrvException e) {
            System.err.println("Failed to set send subject:");
            e.printStackTrace();
            System.exit(0);
        }

        try {
            // Send one message for each parameter
            System.out.println("Publishing: subject=" + msg.getSendSubject() +
                    " \"" + message + "\"");
            msg.update(fieldName, message);
            TibrvMsg replyMessage = transport.sendRequest(msg, timeout);
        } catch (TibrvException e) {
            System.err.println("Error sending a message:");
            e.printStackTrace();
            System.exit(0);
        }

        // Close Tibrv, it will cleanup all underlying memory, destroy
        // transport and guarantee delivery.
        try {
            Tibrv.close();
        } catch (TibrvException e) {
            System.err.println("Exception dispatching default queue:");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void close(){
        // Close Tibrv, it will cleanup all underlying memory, destroy
        // transport and guarantee delivery.
        try {
            Tibrv.close();
        } catch (TibrvException e) {
            System.err.println("Exception dispatching default queue:");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
