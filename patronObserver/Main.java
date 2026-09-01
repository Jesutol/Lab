package patronObserver;

public class Main {
    public static void main(String[] args) {
        Canal canal = new Canal();
        
        Usuario usuario1 = new Usuario("Juan");
        Usuario usuario2 = new Usuario("María");
        
        canal.subscribe(usuario1);
        canal.subscribe(usuario2);
        
        canal.setMessage("Hola a todos!");
    }
}
