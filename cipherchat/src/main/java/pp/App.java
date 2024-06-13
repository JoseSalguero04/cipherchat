package pp;

import javax.swing.SwingUtilities;
import pp.GUI.*;
import java.util.HashMap;

public class App 
{
    public static HashMap<Integer, Persona> personas = new HashMap<>();
    public static void main( String[] args ) {
        int codigo = 202300708;
        Persona admin = new Persona("Jose", "Salguero", "p1IPC1", 'M', 19, "56306458");
        personas.put(codigo, admin);
        int codigo2 = 202010033;
        Persona mod = new Persona("Auxiliar", "Pro", "p1IPC1", 'M', 25, "00000000");
        personas.put(codigo2, mod);    
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Login();
            }
        });
    }
}
