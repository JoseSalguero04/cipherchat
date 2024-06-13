package pp.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chat2 extends JPanel {
    private JPanel chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private String datosContacto;
    private JTextField txtRutaA;
    private JTextField txtRutaB;
    private boolean matricesLoaded = false;
    private int[][] matrizA;
    private int[][] matrizB;

    public Chat2(String datosContacto) {
        this.datosContacto = datosContacto;
        setLayout(new BorderLayout());

        chatArea = new JPanel();
        chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        sendButton = new JButton("Enviar");
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (matricesLoaded) {
                    sendMessage(matrizA, matrizB);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, carga las matrices A y B antes de enviar un mensaje.");
                }
            }
        });
    }

    public void setMatrizA(int[][] matrizA) {
        this.matrizA = matrizA;
    }

    public void setMatrizB(int[][] matrizB) {
        this.matrizB = matrizB;
    }

    public void setMatricesLoaded(boolean matricesLoaded) {
        this.matricesLoaded = matricesLoaded;
    }

    private void sendMessage(int[][] matrizA, int[][] matrizB) {
        String message = messageField.getText().trim();
        if (!message.isEmpty()) {
            int[][] matrizC = Codificado.codificar(matrizA, matrizB, message);
            int[][] matrizM2 = Codificado.decodificar(matrizA, matrizB, matrizC);

            JPanel messagePanel = new JPanel(new BorderLayout());
            JLabel messageLabel = new JLabel(message);
            messagePanel.add(messageLabel, BorderLayout.CENTER);

            JButton reportButton = new JButton("Reportar");
            reportButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Mensaje reportado");
                }
            });
            messagePanel.add(reportButton, BorderLayout.EAST);

            chatArea.add(messagePanel);
            chatArea.revalidate();
            chatArea.repaint();

            messageField.setText("");
        }
    }
}

