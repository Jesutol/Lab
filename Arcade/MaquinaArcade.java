import java.util.ArrayList;
import java.util.List;

public class MaquinaArcade {
    private String idMaquina;
    private int tickets;
    private EstadoMaquina estado;
    private List<ObservadorArcade> observadores = new ArrayList<>();

    public MaquinaArcade(String idMaquina) {
        this.idMaquina = idMaquina;
        this.tickets = 50;
        this.estado = EstadoMaquina.OPERATIVA;
    }

    public void agregarObservador(ObservadorArcade observador) {
        observadores.add(observador);
    }

    public void recargarTickets() {
        if (this.estado == EstadoMaquina.AVERIADA) {
            notificarObservadores("La máquina está averiada y no puede recargar tickets.", true);
            return;
        }
        this.tickets = 1000;
        this.estado = EstadoMaquina.OPERATIVA;
        notificarObservadores("Recarga de tickets completada.", false);
    }

    public void reparar() {
        if (this.estado == EstadoMaquina.OPERATIVA) {
            return;
        }
        this.estado = EstadoMaquina.OPERATIVA;
        notificarObservadores("Reparacion hecha.", false);
    }

    private void notificarObservadores(String mensaje, boolean urgente) {
        for (ObservadorArcade obs : observadores) {
            obs.notificar(idMaquina, mensaje, urgente);
        }
    }

    public void reportarFalla() {
        this.estado = EstadoMaquina.AVERIADA;
        notificarObservadores("La máquina ha reportado una falla.", true);
    }

    public void jugar() {
        if (this.estado != EstadoMaquina.OPERATIVA) {
            return;
        }
        if (this.tickets <= 10) {
            this.estado = EstadoMaquina.SIN_TICKETS;
            notificarObservadores("La máquina se quedo sin tickets.", true);
        } else {
            this.tickets -= 10;
        }
    }
}
