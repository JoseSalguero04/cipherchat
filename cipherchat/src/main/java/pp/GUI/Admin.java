package pp.GUI;

import javax.swing.JFrame;

import pp.Persona;

public class Admin extends JFrame{
    private JFrame pestaña;

    public Admin(Persona persona, Login login) {
        pestaña = new JFrame("Admin");
        pestaña.setSize(800, 600);
        pestaña.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pestaña.setVisible(false);
    }

    public void setVisible(boolean b) {
        pestaña.setVisible(b);
    }
}
