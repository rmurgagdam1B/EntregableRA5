package ra5.eurovision.modelo;

public class PaisExcepcion extends Exception {
    private String mensaje;

    public PaisExcepcion(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }

    @Override
    public String toString() {
        return this.mensaje;
    }
}
