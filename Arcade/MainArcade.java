import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class MainArcade {
    static void main(String[] args) {
        // Se crea el observador
        SistemaCentral sistemaAdmin = new SistemaCentral();

        // Se crean varias maquinas arcade
        List<MaquinaArcade> listaMaquinas = new ArrayList<>();
        for (int i = 0; i <= 50; i++){
            MaquinaArcade maquina = new MaquinaArcade("ARCADE-" + i);
            maquina.agregarObservador(sistemaAdmin);
            listaMaquinas.add(maquina);
        }

        // Se configura la tarea de recarga masiva
        ForkJoinPool pool = new ForkJoinPool();
        TareaRecargaMasiva tarea = new TareaRecargaMasiva(listaMaquinas, 0, listaMaquinas.size());

        // Se ejecuta la tarea y lanza los hilos en paralelo
        pool.invoke(tarea);
    }
}
