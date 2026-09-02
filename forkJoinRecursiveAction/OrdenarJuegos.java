package forkJoinRecursiveAction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.RecursiveAction;

public class OrdenarJuegos extends RecursiveAction {
    // Umbral empírico: por debajo de este tamaño no conviene paralelizar ni seguir dividiendo
    private static final int UMBRAL = 10;

    private final Juego[] arreglo;
    private final int inicio;
    private final int fin;

    public OrdenarJuegos(Juego[] arreglo, int inicio, int fin) {
        this.arreglo = arreglo;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    protected void compute() {
        // Caso base secuencial: si el tramo es chico, resolvemos directamente
        if ((fin - inicio + 1) <= UMBRAL) {
            Arrays.sort(arreglo, inicio, fin + 1, Comparator.comparingDouble(Juego::precioFinal));
            return;
        }

        int medio = inicio + ((fin - inicio) / 2);

        OrdenarJuegos taskIzq = new OrdenarJuegos(arreglo, inicio, medio);
        OrdenarJuegos taskDer = new OrdenarJuegos(arreglo, medio + 1, fin);

        // Divide y ejecuta en paralelo usando el pool
        invokeAll(taskIzq, taskDer);

        // Mezcla ambas mitades ya ordenadas
        combinar(medio);
    }

    private void combinar(int medio) {
        Juego[] res = new Juego[fin - inicio + 1];
        int izq = inicio;
        int der = medio + 1;
        int idx = 0;

        while (izq <= medio && der <= fin) {
            if (arreglo[izq].precioFinal() <= arreglo[der].precioFinal()) {
                res[idx++] = arreglo[izq++];
            } else {
                res[idx++] = arreglo[der++];
            }
        }

        while (izq <= medio) {
            res[idx++] = arreglo[izq++];
        }

        while (der <= fin) {
            res[idx++] = arreglo[der++];
        }

        System.arraycopy(res, 0, arreglo, inicio, res.length);
    }
}
