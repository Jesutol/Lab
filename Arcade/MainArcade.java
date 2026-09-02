import java.util.ArrayList;
import java.util.List;

public class MainArcade {
    static void main(String[] args) {
        // Se crea el observador y la lista que contiene a las maquinas
        List<MaquinaArcade> listaMaquinas = new ArrayList<>();
        SistemaCentral admin = new SistemaCentral(listaMaquinas);
        PersonalTecnico tecnico = new PersonalTecnico(listaMaquinas);

        // Se crean varias maquinas arcade
        for (int i = 0; i <= 50; i++) {
            MaquinaArcade maquina = new MaquinaArcade("ARCADE-" + i);
            maquina.agregarObservador(admin);
            maquina.agregarObservador(tecnico);
            listaMaquinas.add(maquina);
        }

        // Simulamos fallas aleatorias
        System.out.println("--- Ocurren fallas tecnicas ---");
        for (int i = 0; i < 12; i++) {
            int maquinaAleatoria = (int) (Math.random() * listaMaquinas.size());
            listaMaquinas.get(maquinaAleatoria).reportarFalla();
        }

        // Hacemos que la gente juegue hasta vaciar exactamente 5 maquinas
        System.out.println("--- Los clientes usan las maquinas ---");

        for (int i = 0; i < 10; i++) {
            // Realizamos 5 jugadas por maquina, lo que vacia la maquina
            System.out.println("--- Vaciando maquina:  " + i + " ---");
            for (int jugada = 0; jugada < 5; jugada++) {
                listaMaquinas.get(i).jugar();
            }

        }

    }

}
