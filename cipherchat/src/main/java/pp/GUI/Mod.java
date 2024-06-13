package pp.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import pp.Persona;

import java.awt.*;
import java.awt.event.ActionEvent;


public class Mod extends JFrame{
    private JFrame pestaña;
    private JLabel label;
    private JTable table;
    private DefaultTableModel model;

    public Mod(Persona persona, Login login){
    {
        pestaña = new JFrame("Mod");
        pestaña.setSize(800, 600);
        pestaña.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    
        // Crear el JLabel
        label = new JLabel("Así se debío ver la tabla reportes pipipi");
        pestaña.add(label, BorderLayout.NORTH);
    
        // Crear el modelo de la tabla
        model = new DefaultTableModel();
        model.addColumn("Codigo que Reportó");
        model.addColumn("Codigo reportado");
        model.addColumn("Mensaje");
        model.addColumn("Estado");
    
        // Crear la tabla y añadir el modelo
        table = new JTable(model);
        pestaña.add(new JScrollPane(table), BorderLayout.CENTER);
    
        // Crear una acción que cambia el estado de la fila a "Atendido"
        Action action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                ((DefaultTableModel)table.getModel()).setValueAt("Atendido", modelRow, 3);
            }
        };
        
        model.addRow(new Object[]{"202399999", "696969696", "Analizar", ""});
        

        new ButtonColumn(table, action, 2);
                pestaña.setVisible(false);
            }
    
    }
    public void setVisible(boolean b) {
        pestaña.setVisible(b);
    }
    }
