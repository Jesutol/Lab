package patronObserver;

public class Usuario implements Observer {
    private String name;

    public Usuario(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " recibió: " + message);
    }
}
