package forkJoinRecursiveAction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        List<Juego> listaJuegos = new ArrayList<>();
        String rutaArchivo = "forkJoinRecursiveAction/juegos.txt";

        // Lectura segura del archivo
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                String[] datos = linea.split(";");
                if (datos.length >= 3) {
                    String nombre = datos[0].trim();
                    double precio = Double.parseDouble(datos[1].trim());
                    int descuento = Integer.parseInt(datos[2].trim());

                    listaJuegos.add(new Juego(nombre, precio, descuento));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar los juegos: " + e.getMessage());
            return;
        }

        if (listaJuegos.isEmpty()) {
            System.out.println("El archivo está vacío o no se pudieron leer registros.");
            return;
        }

        // Convertimos a arreglo con la cantidad exacta leída
        Juego[] juegos = listaJuegos.toArray(new Juego[0]);

        System.out.println("Juegos antes de ordenar (" + juegos.length + "):");
        System.out.println(Arrays.toString(juegos));

        // Ejecución con ForkJoinPool
        ForkJoinPool pool = ForkJoinPool.commonPool();

        // Llamada actualizada a la nueva clase
        pool.invoke(new OrdenarJuegos(juegos, 0, juegos.length - 1));

        System.out.println("\nJuegos ordenados por precio:");
        System.out.println(Arrays.toString(juegos));

        System.out.println("\nCantidad total de juegos: " + juegos.length);
    }
}