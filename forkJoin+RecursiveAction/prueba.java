import java.util.concurrent.RecursiveTask;

public class Prueba extends RecursiveTask<Juego[]> {

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
    protected Juego[] compute() {

        Juego[] res = new Juego[(m - n) + 1];
        int largo = (m - n) + 1;

        if (m > n) {

            int i, d, j;

            Juego[] izq;
            Juego[] der;

            Prueba taskIzq;
            Prueba taskDer;

            taskIzq = new Prueba(
                    n,
                    n + ((m - n) / 2),
                    arreglo,
                    corte + 1
            );

            taskDer = new Prueba(
                    n + ((m - n) / 2) + 1,
                    m,
                    arreglo,
                    corte + 1
            );

            if (corte > 4) {

                izq = taskIzq.compute();
                der = taskDer.compute();

            } else {

                taskIzq.fork();

                der = taskDer.compute();

                izq = taskIzq.join();
            }

            i = 0;
            d = 0;

            // Combinar las dos partes ordenadas
            for (j = 0; j < largo; j++) {

                if (i < izq.length && d < der.length) {

                    if (izq[i].precioFinal() < der[d].precioFinal()) {

                        res[j] = izq[i];
                        i++;

                    } else {

                        res[j] = der[d];
                        d++;
                    }

                } else {

                    if (i < izq.length) {

                        res[j] = izq[i];
                        i++;

                    } else {

                        res[j] = der[d];
                        d++;
                    }
                }
            }

        } else {

            res[0] = arreglo[m];
        }

        return res;
    }
}