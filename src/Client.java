

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Client extends JFrame 
{

    private JPanel textPanel;
    private DatagramSocket socket;
    private boolean logged;
    private String name;

    private javax.swing.JLabel passLabel;
    private javax.swing.JLabel portLabel;
    private javax.swing.JLabel ipLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel connectionLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPasswordField passField;
    private javax.swing.JScrollPane textPane;
    private javax.swing.JTextArea displayArea;
    private javax.swing.JTextField enterField;
    private javax.swing.JTextField portField;
    private javax.swing.JTextField ipField;
    private javax.swing.JTextField nameField;
    private javax.swing.JToggleButton logButton;

    public Client()
    {
        super("Client");

        initComponents();

        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                try
                {
                    String message = name+": disconnectjsopjsgpjspfjosidvps[phd'dtdsrg";
                    byte data[] = message.getBytes();

                    DatagramPacket sendPacket = new DatagramPacket(data, data.length,InetAddress.getByName(ipField.getText()),Integer.parseInt(portField.getText()));

                    socket.send(sendPacket);
                }
                catch(IOException io)
                {
                    displayMessage(io.toString() + "\n");
                    io.printStackTrace();
                }
            }
        });
        enterField.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        try
                        {
                            String message = event.getActionCommand();
                            message = name+": "+message;
                            byte data[] = message.getBytes();

                            DatagramPacket sendPacket = new DatagramPacket(data, data.length,InetAddress.getByName(ipField.getText()),Integer.parseInt(portField.getText()));

                            socket.send(sendPacket);
                            displayArea.append("\n"+message);
                            displayArea.setCaretPosition(displayArea.getText().length());
                            enterField.setText("");
                        }
                        catch(IOException io)
                        {
                            displayMessage(io.toString() + "\n");
                            io.printStackTrace();
                        }
                    }
                }
        );

        try
        {
            socket = new DatagramSocket();
        }
        catch(SocketException socketException)
        {
            socketException.printStackTrace();
            System.exit(1);
        }
        logged = false;

    }

    public void waitForPackets()
    {
        while(true)
        {
            try
            {
                byte data[] = new byte[100];
                DatagramPacket receivePacket = new DatagramPacket(data,data.length);

                socket.receive(receivePacket);

                displayMessage("\n" + new String(receivePacket.getData(), 0, receivePacket.getLength()));
            }
            catch(IOException exception)
            {
                displayMessage(exception.toString() + "\n");
                exception.printStackTrace();
            }
        }
    }

    private void displayMessage(final String messageToDisplay)
    {
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run()
                    {
                        displayArea.append(messageToDisplay);
                    }
                }
        );
    }

    private void initComponents() {

        textPane = new javax.swing.JScrollPane();
        displayArea = new javax.swing.JTextArea();
        mainPanel = new javax.swing.JPanel();
        enterField = new javax.swing.JTextField();
        passField = new javax.swing.JPasswordField();
        passLabel = new javax.swing.JLabel();
        portField = new javax.swing.JTextField();
        portLabel = new javax.swing.JLabel();
        ipField = new javax.swing.JTextField();
        ipLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        connectionLabel = new javax.swing.JLabel();
        logButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        displayArea.setColumns(20);
        displayArea.setRows(5);
        textPane.setViewportView(displayArea);

        enterField.setText("Type Here");

        passField.setText("password");

        passLabel.setText("Password");

        portField.setText("4000");

        portLabel.setText("Port");

        ipField.setText("172.28.90.34");

        ipLabel.setText("IP");

        nameField.setText("New_User");

        nameLabel.setText("Username");

        connectionLabel.setText("Not Connected");

        logButton.setText("Connect");

        logButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                if(!logged)
                {
                    while(!logged)
                    {
                        try
                        {
                            name = nameField.getText();
                            String message = name+": loginzoeooeowwpdsddasf";
                            byte data[] = message.getBytes();

                            DatagramPacket sendPacket = new DatagramPacket(data, data.length,InetAddress.getByName(ipField.getText()),Integer.parseInt(portField.getText()));

                            socket.send(sendPacket);
                            logButton.setText("Disconnect");
                            connectionLabel.setText("Connected");
                            logged = true;
                        }
                        catch(IOException io)
                        {
                            logged = false;
                        }
                    }
                }
                else
                {
                    while(logged)
                    {
                        try
                        {
                            String message = name+": disconnectjsopjsgpjspfjosidvps[phd'dtdsrg";
                            byte data[] = message.getBytes();

                            DatagramPacket sendPacket = new DatagramPacket(data, data.length,InetAddress.getByName(ipField.getText()),Integer.parseInt(portField.getText()));

                            socket.send(sendPacket);
                            logButton.setText("Connect");
                            connectionLabel.setText("Not Connected");
                            
                            logged = false;
                        }
                        catch(IOException io)
                        {
                            displayMessage(io.toString() + "\n");
                            io.printStackTrace();
                            logged = true;
                        }
                    }
                }
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(enterField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(connectionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(logButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ipLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ipField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(portLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(enterField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passLabel)
                    .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portLabel)
                    .addComponent(ipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ipLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connectionLabel)
                    .addComponent(logButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(textPane, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    public static void main(String args[])
    {
        Client app = new Client();
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.waitForPackets();
    }
}
