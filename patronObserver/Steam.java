package patronObserver;

import java.util.ArrayList;
import java.util.List;

public class Steam {
    private List<Observer> observers = new ArrayList<>();
    private String message;

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
    public void notificarRebaja(String notificacionListaDeDeseos) {
        System.out.println("Inicio la temporada de descuentos " + notificacionListaDeDeseos);
        setMessage("Un juego de tu lista de deseos está en descuento: " + notificacionListaDeDeseos);
    }

    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }
}