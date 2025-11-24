import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Pasajero> lista = new ArrayList<>();
        int total = 352;
        int porCategoria = total / 4;
        int id = 1;
        for (int prioridad = 1; prioridad <= 4; prioridad++) {
            for (int i = 0; i < porCategoria; i++) {
                lista.add(new Pasajero(id++, prioridad));
            }
        }

        while (id <= total) {
            lista.add(new Pasajero(id++, 4));
        }

        Barco barco = new Barco(lista);


        List<Balsa> balsas = new ArrayList<>();
        balsas.add(new Balsa("Acasta", 1, 0.5));
        balsas.add(new Balsa("Banff", 2, 1.0));
        balsas.add(new Balsa("Cadiz", 3, 2.0));
        balsas.add(new Balsa("Deimos", 4, 4.0));
        balsas.add(new Balsa("Expedicion", 5, 8.0));


        List<Thread> hilos = new ArrayList<>();

        for (int i = 0; i < balsas.size(); i++) {
            Balsa b = balsas.get(i);
            Thread t = new Thread(new Rescate(b, barco), "Hilo-" + b.getNombre());
            hilos.add(t);
            t.start();
        }


        for (int i = 0; i < hilos.size(); i++) {
            try {
                hilos.get(i).join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("\nMain interrumpido mientras esperaba hilos.");
            }
        }


        System.out.println("\nTodos los pasajeros han sido rescatados. Fin del rescate.");
    }
}
