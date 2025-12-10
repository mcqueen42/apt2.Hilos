import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Barco {

    private final List<Pasajero> pasajeros = new ArrayList<>();
    private final Semaphore semaforo = new Semaphore(1);

    public Barco(List<Pasajero> pasajeros) {
        this.pasajeros.addAll(pasajeros);
    }

    public boolean hayPasajeros() {
        try {
            semaforo.acquire();
            return !pasajeros.isEmpty();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            semaforo.release();
        }
    }

    public List<Pasajero> obtenerPasajerosParaBalsa(int capacidad) {
        List<Pasajero> recogidos = new ArrayList<>(capacidad);

        try {
            semaforo.acquire();

            if (pasajeros.isEmpty()) {
                return recogidos;
            }


            for (int prioridad = 1; prioridad <= 4 && recogidos.size() < capacidad; prioridad++) {
                for (int i = 0; i < pasajeros.size() && recogidos.size() < capacidad; i++) {

                    Pasajero p = pasajeros.get(i);

                    if (p.getPrioridad() == prioridad) {
                        recogidos.add(p);
                        pasajeros.remove(i);
                        i--;
                    }
                }
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaforo.release();
        }

        return recogidos;
    }

    public int pasajerosRestantes() {
        try {
            semaforo.acquire();
            return pasajeros.size();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return 0;
        } finally {
            semaforo.release();
        }
    }
}
