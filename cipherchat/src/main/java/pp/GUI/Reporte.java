package pp.GUI;

public class Reporte {
    private int codigoPersonaLoggeada;
    private int codigoPersonaChat;
    private String mensaje;
    private int[][] matrizM2;
    private boolean estado;

    public Reporte(int codigoPersonaLoggeada, int codigoPersonaChat, String mensaje, int[][] matrizM2, boolean estado) {
        this.codigoPersonaLoggeada = codigoPersonaLoggeada;
        this.codigoPersonaChat = codigoPersonaChat;
        this.mensaje = mensaje;
        this.matrizM2 = matrizM2;
        this.estado = estado;
    }

    // Getters and Setters
    public int getCodigoPersonaLoggeada() {
        return codigoPersonaLoggeada;
    }

    public void setCodigoPersonaLoggeada(int codigoPersonaLoggeada) {
        this.codigoPersonaLoggeada = codigoPersonaLoggeada;
    }

    public int getCodigoPersonaChat() {
        return codigoPersonaChat;
    }

    public void setCodigoPersonaChat(int codigoPersonaChat) {
        this.codigoPersonaChat = codigoPersonaChat;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int[][] getMatrizM2() {
        return matrizM2;
    }

    public void setMatrizM2(int[][] matrizM2) {
        this.matrizM2 = matrizM2;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
