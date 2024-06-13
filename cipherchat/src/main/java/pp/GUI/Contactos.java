package pp.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import pp.*;

public class Contactos extends JPanel {
    private Map<String, String[]> contactos = new HashMap<>();
    private String codigoStr;
    private String nombreCompleto;

    public Contactos() {
        setLayout(new BorderLayout());

        // Tus contactos
        JLabel labelContactos = new JLabel("Tus contactos");
        add(labelContactos, BorderLayout.NORTH);

        // Tabla de contactos
        String[] columnNames = {"Codigo", "Nombre", "Chat", "Eliminar"};
        final DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        

        // Buscar contacto
        JPanel panelBuscar = new JPanel();
        panelBuscar.setLayout(new BorderLayout());
        JLabel labelBuscar = new JLabel("Buscar Contacto");
        final JTextField fieldBuscar = new JTextField();
        fieldBuscar.setPreferredSize(new Dimension(100, 10));
        JButton buttonBuscar = new JButton("Buscar");
        panelBuscar.add(labelBuscar, BorderLayout.NORTH);
        panelBuscar.add(fieldBuscar, BorderLayout.CENTER);
        panelBuscar.add(buttonBuscar, BorderLayout.SOUTH);
        add(panelBuscar, BorderLayout.EAST);

        // Funcionalidad de búsqueda y agregar contactos
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigoStr = fieldBuscar.getText();
                Integer codigo = Integer.parseInt(codigoStr);
                if (App.personas.containsKey(codigo)) {
                    // Mostrar ventana de confirmación para agregar contacto
                    int dialogResult = JOptionPane.showConfirmDialog(null, "¿Desea agregar este contacto?");
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        Persona persona = App.personas.get(codigo);
                        nombreCompleto = persona.getNombre() + " " + persona.getApellido();
                        // Agregar a contactos
                        contactos.put(codigoStr, new String[]{codigoStr, nombreCompleto});
                        // Agregar a la tabla
                        tableModel.addRow(new Object[]{codigoStr, nombreCompleto, new JButton("Chat"), new JButton("Eliminar")});
                    }
                } else {
                    // Mostrar ventana emergente de que el contacto no existe
                    JOptionPane.showMessageDialog(null, "El contacto no existe");
                }
            }
        });

        // Agregar a la tabla
        tableModel.addRow(new Object[]{codigoStr, nombreCompleto, new JButton("Chat"), new JButton("Eliminar")});

        // Agregar ActionListener al botón "Eliminar"
        Action delete = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int modelRow = Integer.valueOf(e.getActionCommand());
                tableModel.removeRow(modelRow);
                contactos.remove(codigoStr);
            }
        };

        ButtonColumn buttonColumn = new ButtonColumn(table, delete, 3);
        buttonColumn.setMnemonic(KeyEvent.VK_D);

        Action openChat = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int modelRow = Integer.valueOf(e.getActionCommand());
                // Obtener el código del contacto
                String codigoContacto = (String) tableModel.getValueAt(modelRow, 0);
                // Obtener los datos del contacto
                String[] datosContacto = contactos.get(codigoContacto);
                // Crear una nueva ventana de chat
                Chat chatWindow = new Chat(datosContacto);
                chatWindow.setVisible(true);
            }
        };
        
        ButtonColumn buttonColumnChat = new ButtonColumn(table, openChat, 2);
        buttonColumnChat.setMnemonic(KeyEvent.VK_C);

    }
}