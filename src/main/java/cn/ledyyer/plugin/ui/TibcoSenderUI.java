package cn.ledyyer.plugin.ui;

import cn.ledyyer.plugin.bean.TibcoSenderConfig;
import cn.ledyyer.plugin.service.normal.TibcoSenderService;
import cn.ledyyer.plugin.service.persistence.TibcoSenderConfigService;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TibcoSenderUI {
    private JPanel root;
    private JPanel topOption;
    private JLabel serviceText;
    private JTextField serviceInput;
    private JLabel networkText;
    private JTextField networkInput;
    private JLabel daemonText;
    private JTextField daemonInput;
    private JLabel subjectNameText;
    private JTextField subjectNameInput;
    private JLabel timeoutText;
    private JTextField timeoutInput;
    private JLabel fieldNameText;
    private JTextField fieldNameInput;
    private JLabel line;
    private JScrollPane content;
    private JTextArea messageTextArea;
    private JPanel bottomOption;
    private JButton saveButton;
    private JButton sendButton;

    private final TibcoSenderConfig state = TibcoSenderConfigService.getInstance().getState();

    public JPanel getRoot() {
        return root;
    }

    public TibcoSenderUI(){
        // get the last saved configuration and fill in the input box. if first open, will get a blank field
        init();

        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String service = serviceInput.getText();
                String network = networkInput.getText();
                String daemon = daemonInput.getText();
                String subjectName = subjectNameInput.getText();
                String timeout = timeoutInput.getText();
                String fieldName = fieldNameInput.getText();
                String message = messageTextArea.getText();

                TibcoSenderService tibcoSenderService = new TibcoSenderService(service, network, daemon, subjectName, fieldName);
                tibcoSenderService.open();
                if (timeout.equals("")) {
                    tibcoSenderService.send(message);
                } else {
                    tibcoSenderService.send(message, Double.parseDouble(timeout));
                }
                tibcoSenderService.close();
                TibcoSenderConfig state = new TibcoSenderConfig(service,network,daemon,subjectName,timeout,fieldName,message);
                TibcoSenderConfigService.getInstance().loadState(state);
            }
        });

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String service = serviceInput.getText();
                String network = networkInput.getText();
                String daemon = daemonInput.getText();
                String subjectName = subjectNameInput.getText();
                String timeout = timeoutInput.getText();
                String fieldName = fieldNameInput.getText();
                String message = messageTextArea.getText();

                TibcoSenderConfig state = new TibcoSenderConfig(service,network,daemon,subjectName,timeout,fieldName,message);
                TibcoSenderConfigService.getInstance().loadState(state);
            }
        });
    }


    private void init(){
        if (state != null) {
            serviceInput.setText(state.getService());
            networkInput.setText(state.getNetwork());
            daemonInput.setText(state.getDaemon());
            subjectNameInput.setText(state.getSubjectName());
            timeoutInput.setText(state.getTimeout());
            fieldNameInput.setText(state.getFieldName());
            messageTextArea.setText(state.getTimeout());
        }
    }
}
