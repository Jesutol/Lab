package patronObserver;

import java.util.ArrayList;
import java.util.List;

public class Canal {
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
    public void subirVideo(String video) {
        System.out.println("Nuevo video subido: " + video);
        setMessage("Nuevo video subido: " + video);
    }

    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }
}