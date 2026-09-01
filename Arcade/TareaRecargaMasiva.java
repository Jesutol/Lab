import java.util.concurrent.RecursiveAction;
import java.util.List;

public class TareaRecargaMasiva extends RecursiveAction {
    private List <MaquinaArcade> maquinas;
    private int inicio;
    private int fin;
    private static final int UMBRAL = 10;

    public TareaRecargaMasiva(List<MaquinaArcade> maquinas, int inicio, int fin){
        this.maquinas = maquinas;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    protected void compute(){
        if ((fin - inicio) <= UMBRAL) {
            for (int i = inicio; i < fin; i++){
                maquinas.get(i).recargarTickets();
            }
        } else {
            int medio = inicio + (fin - inicio) / 2;

            TareaRecargaMasiva mitad1 = new TareaRecargaMasiva(maquinas, inicio, medio);
            TareaRecargaMasiva mitad2 = new TareaRecargaMasiva(maquinas, medio + 1, fin);

            invokeAll(mitad1, mitad2);
        }
    }
}
