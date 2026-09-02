package forkJoinRecursiveAction;

import java.util.concurrent.RecursiveAction;

public class Prueba extends RecursiveAction {

    Juego[] arreglo;
    int n;
    int m;
    int corte;

    public Prueba(int n, int m, Juego[] matriz, int corte) {
        this.n = n;
        this.m = m;
        this.arreglo = matriz;
        this.corte = corte;
    }

    @Override
    protected void compute() {

        if (m > n) {

            Prueba taskIzq;
            Prueba taskDer;

            int medio = n + ((m - n) / 2);

            taskIzq = new Prueba(
                    n,
                    medio,
                    arreglo,
                    corte + 1
            );

            taskDer = new Prueba(
                    medio + 1,
                    m,
                    arreglo,
                    corte + 1
            );

            if (corte > 4) {

                taskIzq.compute();
                taskDer.compute();

            } else {

                invokeAll(taskIzq, taskDer);
            }

            combinar(medio);
        }
    }

    private void combinar(int medio) {
        Juego[] res = new Juego[m - n + 1];
        int izquierda = n;
        int derecha = medio + 1;

        for (int indice = 0; indice < res.length; indice++) {
            if (izquierda <= medio
                    && (derecha > m
                    || arreglo[izquierda].precioFinal()
                    <= arreglo[derecha].precioFinal())) {
                res[indice] = arreglo[izquierda++];
            } else {
                res[indice] = arreglo[derecha++];
            }
        }

        System.arraycopy(res, 0, arreglo, n, res.length);
    }
}