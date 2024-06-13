package pp.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Chat extends JFrame {
    private String[] datosContacto;
    private String txtRutaA, txtRutaB;
    private Chat2 chatPanel;
    boolean matrizLoadedA = false;
    boolean matrizLoadedB = false;
    JFileChooser fileChooserA = new JFileChooser();
    JFileChooser fileChooserB = new JFileChooser();
    private int[][] matrizA;
    private int[][] matrizB;

    public Chat(String[] datosContacto) {
        this.datosContacto = datosContacto;

        setTitle("Chat");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblCodigo = new JLabel("Codigo: " + datosContacto[0]);
        add(lblCodigo, BorderLayout.NORTH);

        // Create the chat panel
        chatPanel = new Chat2(datosContacto[0]);
        add(chatPanel, BorderLayout.CENTER);

        JPanel panelMatrices = new JPanel(new GridLayout(2, 1));

        JLabel lblRutaA = new JLabel("Ruta de la matriz clave A:");

        JButton btnCargarA = new JButton("Cargar");
        panelMatrices.add(lblRutaA);
        panelMatrices.add(btnCargarA);

        JLabel lblRutaB = new JLabel("Ruta de la matriz clave B:");

        JButton btnCargarB = new JButton("Cargar");
        panelMatrices.add(lblRutaB);
        panelMatrices.add(btnCargarB);

        add(panelMatrices, BorderLayout.EAST);

        // ActionListeners
        btnCargarA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooserA.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    txtRutaA = fileChooserA.getSelectedFile().getPath();
                    matrizLoadedA = true;
                    matrizA = cargarMatrizA(txtRutaA, 3, 3); // Loading 3x3 matrizA
                    chatPanel.setMatrizA(matrizA);
                    checkMatricesLoaded();
                }
            }
        });

        btnCargarB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooserB.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    txtRutaB = fileChooserB.getSelectedFile().getPath();
                    matrizLoadedB = true;
                    matrizB = cargarMatrizB(txtRutaB, 3); // Loading matrizB with 3 rows
                    chatPanel.setMatrizB(matrizB);
                    checkMatricesLoaded();
                }
            }
        });

        // Display the frame
        setVisible(true);
    }

    private void checkMatricesLoaded() {
        if (matrizLoadedA && matrizLoadedB) {
            chatPanel.setMatricesLoaded(true);
        }
    }

    private int[][] cargarMatrizA(String ruta, int filas, int columnas) {
        int[][] matriz = new int[filas][columnas];
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < filas) {
                String[] values = line.split(",");
                for (int i = 0; i < values.length && i < columnas; i++) {
                    matriz[row][i] = Integer.parseInt(values[i]);
                }
                row++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return matriz;
    }

    private int[][] cargarMatrizB(String ruta, int filas) {
        int[][] matriz = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String line = reader.readLine();
            if (line != null) {
                String[] firstLine = line.split(",");
                int columnas = firstLine.length;
                matriz = new int[filas][columnas];
                for (int i = 0; i < columnas; i++) {
                    matriz[0][i] = Integer.parseInt(firstLine[i]);
                }
                int row = 1;
                while ((line = reader.readLine()) != null && row < filas) {
                    String[] values = line.split(",");
                    for (int i = 0; i < values.length && i < columnas; i++) {
                        matriz[row][i] = Integer.parseInt(values[i]);
                    }
                    row++;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return matriz;
    }
    
}
