import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class SistemaCentral implements ObservadorArcade {

    private int contadorMaquinasVacias = 0;
    private static final int LIMITE_MAQUINAS_VACIAS = 10;
    private List<MaquinaArcade> maquinas;

    public SistemaCentral(List<MaquinaArcade> maquinas) {
        this.maquinas = maquinas;
    }

    @Override
    public void notificar(String idMaquina, String mensaje, boolean requiereAtencion) {
        System.out.println("[SISTEMA CENTRAL] Maquina: " + idMaquina + " | Info: " + mensaje);

        if (mensaje.contains("La máquina se quedo sin tickets.")) {
            contadorMaquinasVacias++;
            if (contadorMaquinasVacias >= LIMITE_MAQUINAS_VACIAS) {
                System.out.println("[SISTEMA CENTRAL] Alerta: Se han detectado " + contadorMaquinasVacias + " máquinas sin tickets. Iniciando recarga masiva.");
                ejecutarRecargaForkJoin();
                contadorMaquinasVacias = 0; // Reset counter after action
            }
        }
    }

    public void ejecutarRecargaForkJoin() {
        ForkJoinPool pool = new ForkJoinPool();
        TareaRecargaMasiva tarea = new TareaRecargaMasiva(maquinas, 0, maquinas.size());
        pool.invoke(tarea);
        System.out.println("[SISTEMA CENTRAL] Recarga masiva completada.");
    }
}

