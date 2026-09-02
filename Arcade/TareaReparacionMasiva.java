import java.util.concurrent.RecursiveAction;
import java.util.List;

public class TareaReparacionMasiva extends RecursiveAction {
    private List<MaquinaArcade> maquinas;
    private int inicio;
    private int fin;
    private static final int UMBRAL = 10;

    public TareaReparacionMasiva(List<MaquinaArcade> maquinas, int inicio, int fin) {
        this.maquinas = maquinas;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    protected void compute() {
        if ((fin - inicio) <= UMBRAL) {
            for (int i = inicio; i < fin; i++) {
                maquinas.get(i).reparar();
            }
        } else {
            int medio = inicio + (fin - inicio) / 2;

            TareaReparacionMasiva mitad1 = new TareaReparacionMasiva(maquinas, inicio, medio);
            TareaReparacionMasiva mitad2 = new TareaReparacionMasiva(maquinas, medio + 1, fin);

            invokeAll(mitad1, mitad2);
        }
    }
}
