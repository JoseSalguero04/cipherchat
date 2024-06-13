package pp.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import pp.*;

public class Usuario extends JFrame {
    private JTextField txtNombre, txtApellido, txtEdad, txtTelefono;
    private JPasswordField txtPassword;
    private JLabel lblCodigo, lblGenero;
    private JButton btnActualizar;

    public Usuario(final Persona persona, Login selfLogin) {

        setTitle("Bienvenido");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel pnlContactos = new JPanel();
        Contactos contactos = new Contactos();
        pnlContactos.add(contactos);
        tabbedPane.addTab("Contactos", pnlContactos);

        JPanel pnlNotificaciones = new JPanel();
        tabbedPane.addTab("Notificaciones", pnlNotificaciones);

        JPanel pnlEditar = new JPanel(); 
        pnlEditar.setLayout(new FlowLayout());

        txtNombre = new JTextField(persona.getNombre(), 20);
        txtApellido = new JTextField(persona.getApellido(), 20);
        txtPassword = new JPasswordField(persona.getPassword(), 20);
        txtEdad = new JTextField(String.valueOf(persona.getEdad()), 3);
        txtTelefono = new JTextField(persona.getTelefono(), 10);
        lblCodigo = new JLabel("Codigo: " + (persona.getCodigo() - 2));
        lblGenero = new JLabel("Genero: " + persona.getGenero());
        btnActualizar = new JButton("Actualizar");

        pnlEditar.add(new JLabel("Nombre:"));
        pnlEditar.add(txtNombre);
        pnlEditar.add(new JLabel("Apellido:"));
        pnlEditar.add(txtApellido);
        pnlEditar.add(new JLabel("Password:"));
        pnlEditar.add(txtPassword);
        pnlEditar.add(new JLabel("Edad:"));
        pnlEditar.add(txtEdad);
        pnlEditar.add(new JLabel("Telefono:"));
        pnlEditar.add(txtTelefono);
        pnlEditar.add(lblCodigo);
        pnlEditar.add(lblGenero);
        pnlEditar.add(btnActualizar);

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String password = new String(txtPassword.getPassword());
                int edad = Integer.parseInt(txtEdad.getText());
                String telefono = txtTelefono.getText();

                if (nombre.isEmpty() || apellido.isEmpty() || password.isEmpty() || txtEdad.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, llena todos los campos obligatorios correctamente.");
                } else {
                    persona.setNombre(nombre);
                    persona.setApellido(apellido);
                    persona.setPassword(password);
                    persona.setEdad(edad);
                    persona.setTelefono(telefono);
                    JOptionPane.showMessageDialog(null, "Datos actualizados exitosamente");
                }
            }
        });

        tabbedPane.addTab("Editar", pnlEditar);

        JPanel pnlCerrarSesion = new JPanel();
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana de usuario
                dispose();
            }
        });
        pnlCerrarSesion.add(btnCerrarSesion);
        tabbedPane.addTab("Cerrar Sesión", pnlCerrarSesion);

        add(tabbedPane);

        setVisible(true);
    }
}
