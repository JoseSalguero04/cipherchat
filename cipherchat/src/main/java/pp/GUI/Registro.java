package pp.GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.FlowLayout;
import pp.Persona;

public class Registro extends JFrame {
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JPasswordField txtPassword;
    private JComboBox cmbGenero;
    private JTextField txtEdad;
    private JTextField txtTelefono;
    private JButton btnRegister;
    private static int codigo = 202300000;

    public Registro(final HashMap<Integer, Persona> personas) {
        setLayout(new FlowLayout());
    
        txtNombre = new JTextField(20);
        txtApellido = new JTextField(20);
        txtPassword = new JPasswordField(20);
        String[] generos = {"M", "F"};
        cmbGenero = new JComboBox<String>(generos);
        txtEdad = new JTextField(3);
        txtTelefono = new JTextField(10);
        btnRegister = new JButton("Registrarse");
    
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Apellido:"));
        add(txtApellido);
        add(new JLabel("Password:"));
        add(txtPassword);
        add(new JLabel("Genero (M/F):"));
        add(cmbGenero);
        add(new JLabel("Edad:"));
        add(txtEdad);
        add(new JLabel("Telefono:"));
        add(txtTelefono);
        add(btnRegister);
    
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String password = new String(txtPassword.getPassword());
                char genero = ((String)cmbGenero.getSelectedItem()).charAt(0);
                int edad = Integer.parseInt(txtEdad.getText());
                String telefono = txtTelefono.getText();
    
                if (nombre.isEmpty() || apellido.isEmpty() || password.isEmpty() || txtEdad.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, llena todos los campos obligatorios correctamente.");
                } else {
                    Persona persona = new Persona(nombre, apellido, password, genero, edad, telefono);
                    personas.put(codigo, persona);
                    JOptionPane.showMessageDialog(null, "Tu codigo es: " + codigo);
                    codigo++;
                    dispose();
                }
            }
        });
    
        setSize(300, 300);
        setVisible(true);
    }
}