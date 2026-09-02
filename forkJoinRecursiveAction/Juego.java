package forkJoinRecursiveAction;
public class Juego {

    String nombre;
    double precio;
    int descuento;

    public Juego(String nombre, double precio, int descuento) {
        this.nombre = nombre;
        this.precio = precio;
        this.descuento = descuento;
    }

    public double precioFinal() {
        return precio - (precio * descuento / 100);
    }

    @Override
    public String toString() {
        return nombre + " | $" + String.format("%.2f", precioFinal())
                + " | -" + descuento + "%";
    }
}