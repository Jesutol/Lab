package patronObserver;

public class Main {
    public static void main(String[] args) {
        Steam steam = new Steam();
        
        Usuario usuario1 = new Usuario("Juan");
        Usuario usuario2 = new Usuario("María");
        
        steam.subscribe(usuario1);
        steam.subscribe(usuario2);
        
        steam.setMessage("Hola a todos!");
        steam.unsubscribe(usuario1);
        steam.notificarRebaja("Un juego de tu lista de deseos está en descuento: The Witcher 3");
        
    }
}
