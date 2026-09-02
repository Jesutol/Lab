import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        Juego[] juegos = new Juego[100];

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader("juegos.txt")
            );

            String linea;
            int i = 0;

            while ((linea = br.readLine()) != null && i < 100) {

                String[] datos = linea.split(";");

                String nombre = datos[0];
                double precio = Double.parseDouble(datos[1]);
                int descuento = Integer.parseInt(datos[2]);

                juegos[i] = new Juego(
                        nombre,
                        precio,
                        descuento
                );

                i++;
            }

            br.close();

        } catch (Exception e) {

            System.out.println("Error al cargar los juegos: "
                    + e.getMessage());

            return;
        }

        System.out.println("Juegos antes de ordenar:");
        System.out.println(Arrays.toString(juegos));

        ForkJoinPool pool = ForkJoinPool.commonPool();

        juegos = pool.invoke(
                new Prueba(0, juegos.length - 1, juegos, 1)
        );

        System.out.println("\nJuegos ordenados por precio:");
        System.out.println(Arrays.toString(juegos));

        System.out.println("\nCantidad de juegos: "
                + juegos.length);
    }
}