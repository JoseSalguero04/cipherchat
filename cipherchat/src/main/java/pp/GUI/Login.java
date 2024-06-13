package pp.GUI;

import javax.swing.*;
import pp.Persona;
import pp.App;
import java.awt.event.*;
import java.util.Map;
import java.awt.GridLayout;

public class Login {
    private JFrame frame;
    private JTextField txtCodigo;
    private JPasswordField txtPassword;
    

    public Login() {
        frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        frame.add(new JLabel("Codigo:"));
        txtCodigo = new JTextField();
        frame.add(txtCodigo);

        frame.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        frame.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        final Login self = this;
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(txtCodigo.getText());
                String password = new String(txtPassword.getPassword());
        
                if (App.personas.containsKey(codigo)) {
                    Persona persona = App.personas.get(codigo);
                    if (persona.getPassword().equals(password)) {
                        System.out.println("Login exitoso");
                        for (Map.Entry<Integer, Persona> entry : App.personas.entrySet()) {
                            System.out.println("Codigo: " + entry.getKey() + ", Persona: " + entry.getValue());
                            
                        }
                        if (codigo == 202010033) {
                            Mod ventanaMod = new Mod(persona, self);
                            ventanaMod.setVisible(true);
                        } else if (codigo == 202300708) {
                            Admin ventanaAdmin = new Admin(persona, self);
                            ventanaAdmin.setVisible(true);
                        } else {
                            Usuario ventanaUsuario = new Usuario(persona, self);
                            ventanaUsuario.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Codigo o Contraseña incorrectos");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Codigo o Contraseña incorrectos");
                }
            }
        });
        frame.add(btnLogin);

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Registro(App.personas);
            }
        });
        frame.add(btnRegister);

        frame.setVisible(true);
    }


    public void setVisible(boolean b) {
        frame.setVisible(b);
    }

}
