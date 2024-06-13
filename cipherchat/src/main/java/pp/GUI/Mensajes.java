package pp.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Mensajes extends JPanel {
    private Map<String, ArrayList<Mensaje>> mensajes = new HashMap<>();
    private JTextField fieldMensaje; // Convertir en variable de instancia
    private String destinatario; // Convertir en variable de instancia
    private String remitente;

    public Mensajes(String remitente, String destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        setLayout(new BorderLayout());

        // Panel para mostrar los mensajes
        JPanel panelMensajes = new JPanel();
        panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelMensajes);
        add(scrollPane, BorderLayout.CENTER);

        // Panel para enviar mensajes
        JPanel panelEnviar = new JPanel();
        panelEnviar.setLayout(new BorderLayout());
        fieldMensaje = new JTextField();
        JButton buttonEnviar = new JButton("Enviar");
        panelEnviar.add(fieldMensaje, BorderLayout.CENTER);
        panelEnviar.add(buttonEnviar, BorderLayout.EAST);
        add(panelEnviar, BorderLayout.SOUTH);

        // Acción del botón Enviar
        buttonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contenido = fieldMensaje.getText();
                if (!contenido.isEmpty()) {
                    agregarMensaje(Mensajes.this.remitente, Mensajes.this.destinatario, contenido);
                    fieldMensaje.setText("");
                }
            }
        });
    }

    public void agregarMensaje(String remitente, String destinatario, String contenido) {
        Mensaje mensaje = new Mensaje(remitente, destinatario, contenido);
        mensajes.putIfAbsent(destinatario, new ArrayList<Mensaje>());
        mensajes.get(destinatario).add(mensaje);
        mostrarMensajes(destinatario);
    }

    public void mostrarMensajes(String destinatario) {
        removeAll();

    JPanel panelMensajes = new JPanel();
    panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));
    JScrollPane scrollPane = new JScrollPane(panelMensajes);
    add(scrollPane, BorderLayout.CENTER);

    ArrayList<Mensaje> mensajesUsuario = mensajes.get(destinatario);
    if (mensajesUsuario != null) {
        for (Mensaje mensaje : mensajesUsuario) {
            JPanel panelMensaje = new JPanel();
            panelMensaje.setLayout(new BorderLayout());
            JLabel labelMensaje = new JLabel(mensaje.getContenido() + " (" + mensaje.getFecha().toString() + ")");
            panelMensaje.add(labelMensaje, BorderLayout.CENTER);

            if (!mensaje.getRemitente().equals("yo")) {
                JButton buttonReportar = new JButton("Reportar");
                buttonReportar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Acción para reportar el mensaje
                        JOptionPane.showMessageDialog(null, "Mensaje reportado");
                    }
                });
                panelMensaje.add(buttonReportar, BorderLayout.EAST);
            }

            panelMensajes.add(panelMensaje);
        }
    }

    // Panel para enviar mensajes
    JPanel panelEnviar = new JPanel();
    panelEnviar.setLayout(new BorderLayout());
    fieldMensaje = new JTextField(); // Crear nuevo campo de texto para enviar mensajes
    JButton buttonEnviar = new JButton("Enviar");
    panelEnviar.add(fieldMensaje, BorderLayout.CENTER);
    panelEnviar.add(buttonEnviar, BorderLayout.EAST);
    add(panelEnviar, BorderLayout.SOUTH);

    // Acción del botón Enviar
    buttonEnviar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String contenido = fieldMensaje.getText();
            if (!contenido.isEmpty()) {
                agregarMensaje(Mensajes.this.remitente, Mensajes.this.destinatario, contenido);
                fieldMensaje.setText("");
            }
        }
    });

    revalidate();
    repaint();
    }

    public Map<String, ArrayList<Mensaje>> getMensajes() {
        return mensajes;
    }

}
