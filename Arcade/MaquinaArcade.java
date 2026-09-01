import java.util.ArrayList;
import java.util.List;

public class MaquinaArcade {
    private String idMaquina;
    private int tickets;
    private List<ObservadorArcade> observadores = new ArrayList<>();

    public MaquinaArcade(String idMaquina){
        this.idMaquina = idMaquina;
        this.tickets = 0;
    }

    public void agregarObservador(ObservadorArcade observador){
        observadores.add(observador);
    }

    public void recargarTickets(){
        this.tickets = 1000;
        notificarObservadores("La maquina " + idMaquina + " fue recargada a 1000 tickets.");
    }

    private void notificarObservadores(String mensaje){
        for(ObservadorArcade obs : observadores){
            obs.notificar(mensaje);
        }
    }
}
