public class Pasajero {
    private int id;
    private int prioridad;

    public Pasajero(int id, int prioridad) {
        this.id = id;
        this.prioridad = prioridad;
    }

    public int getId() {
        return id;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return "Pasajero{" +
                "id=" + id +
                ", prioridad=" + prioridad +
                '}';
    }
}
