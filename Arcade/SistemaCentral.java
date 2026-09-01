public class SistemaCentral implements ObservadorArcade{
    @Override
    public void notificar(String mensaje){
        System.out.println("[SISTEMA CENTRAL] -> " + mensaje);
    }
}

