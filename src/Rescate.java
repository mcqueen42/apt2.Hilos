import java.util.List;

public class Rescate implements Runnable {

    private Balsa balsa;
    private Barco barco;

    public Rescate(Balsa balsa, Barco barco) {
        this.balsa = balsa;
        this.barco = barco;
    }

    @Override
    public void run() {
        try {
            while (true) {
                List<Pasajero> grupo = barco.obtenerPasajerosParaBalsa(balsa.getCapacidad());

                if (grupo.isEmpty()) {
                    if (!barco.hayPasajeros()) {
                        System.out.printf( balsa.getNombre()+ " No quedan pasajeros. Finaliza hilo\n");
                        break;
                    } else {
                        Thread.yield();
                        continue;
                    }
                }


                String mensaje = "La balsa " + balsa.getNombre() + " salió con " + grupo.size() + " pasajeros: [";

                for (int i = 0; i < grupo.size(); i++) {
                    mensaje += grupo.get(i).getId();
                    if (i < grupo.size() - 1) mensaje += ", ";
                }
                mensaje += "]";

                System.out.println(mensaje);


                long sleepMillis = (long) (balsa.getTiempoSegundos() * 1000);
                try {
                    Thread.sleep(sleepMillis);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("[" + (balsa != null ? balsa.getNombre() : "BalsaDesconocida") + "] Excepción en Rescate: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
