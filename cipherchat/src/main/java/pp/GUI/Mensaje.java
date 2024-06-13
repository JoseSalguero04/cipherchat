package pp.GUI;

import java.time.LocalDateTime;

public class Mensaje {
    private String remitente;
    private String destinatario;
    private String contenido;
    private LocalDateTime fecha;

    public Mensaje(String remitente, String destinatario, String contenido) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.contenido = contenido;
        this.fecha = LocalDateTime.now();
    }

    public String getRemitente() {
        return remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}