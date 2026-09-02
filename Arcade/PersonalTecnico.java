import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class PersonalTecnico implements ObservadorArcade {

    private int contadorMaquinasAveriadas = 0;
    private static final int LIMITE_MAQUINAS_AVERIADAS = 12;
    private List<MaquinaArcade> maquinas;

    public PersonalTecnico(List<MaquinaArcade> maquinas) {
        this.maquinas = maquinas;
    }

    @Override
    public void notificar(String idMaquina, String mensaje, boolean requiereAtencion) {
        if (requiereAtencion) {
            System.out.println("[PERSONAL TECNICO] Alerta en maquina " + idMaquina + ": " + mensaje);
        }
        if (mensaje.contains("La máquina ha reportado una falla.")) {
            contadorMaquinasAveriadas++;
            if (contadorMaquinasAveriadas >= LIMITE_MAQUINAS_AVERIADAS) {
                System.out.println("[PERSONAL TECNICO] Alerta: Se han detectado " + contadorMaquinasAveriadas + " maquinas averiadas. Iniciando revision masiva.");
                ejecutarRevisionMasiva();
                contadorMaquinasAveriadas = 0; // Reset counter after action
            }
        }
    }

    public void ejecutarRevisionMasiva() {
        ForkJoinPool pool = new ForkJoinPool();
        TareaReparacionMasiva tarea = new TareaReparacionMasiva(maquinas, 0, maquinas.size());
        pool.invoke(tarea);
        System.out.println("[PERSONAL TECNICO] Reparacion masiva completada.");
    }
}
