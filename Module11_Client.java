package cen3024;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Module11_Client {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton button;

    public Module11_Client() {
        
        frame = new JFrame("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

        
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        
        label = new JLabel("Enter a Number:");
        panel.add(label);

        
        textField = new JTextField();
        panel.add(textField);

        
        button = new JButton("Check if number is Prime");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(textField.getText());
                String result = performServerRequest(num);
                JOptionPane.showMessageDialog(frame, "Number " + num + " Is Prime Number: " + result);
            }
        });
        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);
    }

    
    private String performServerRequest(int num) {
        
        String result = "";
        try {
            
            Socket s = new Socket("localhost", 8000);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            dout.writeInt(num);
            result = (String) dis.readUTF();

            dout.flush();
            dout.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public static void main(String[] args) {
        new Module11_Client();
    }
}
