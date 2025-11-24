import java.util.ArrayList;
import java.util.List;

public class Barco {

    private  List<Pasajero> pasajeros = new ArrayList<>();

    public Barco(List<Pasajero> pasajeros) {
        this.pasajeros.addAll(pasajeros);
    }

    public synchronized boolean hayPasajeros() {
        return !pasajeros.isEmpty();
    }


    public synchronized List<Pasajero> obtenerPasajerosParaBalsa(int capacidad) {
        List<Pasajero> recogidos = new ArrayList<>(capacidad);
        if (pasajeros.isEmpty()) return recogidos;

        // Recorremos prioridades 1 hasta 4
        for (int prioridad = 1; prioridad <= 4 && recogidos.size() < capacidad; prioridad++) {

            // Recorremos la lista por índice
            for (int i = 0; i < pasajeros.size() && recogidos.size() < capacidad; i++) {

                Pasajero p = pasajeros.get(i);

                if (p.getPrioridad() == prioridad) {
                    recogidos.add(p);
                    pasajeros.remove(i);
                    i--;
                }
            }
        }

        return recogidos;
    }


    public synchronized int pasajerosRestantes() {
        return pasajeros.size();
    }
}
