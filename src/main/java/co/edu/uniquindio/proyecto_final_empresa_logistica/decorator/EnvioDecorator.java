package co.edu.uniquindio.proyecto_final_empresa_logistica.decorator;

public abstract class EnvioDecorator implements IEnvio {

    // Variable protegida para que las subclases (Carton, Burbuja, etc.) puedan usarla
    protected IEnvio envioDecorado;

    // --- CORRECCIÓN ---
    // El constructor debe asignar el valor a la variable de la clase
    public EnvioDecorator(IEnvio envioDecorado) {
        this.envioDecorado = envioDecorado; // <--- ¡ESTO FALTABA!
    }

    @Override
    public String descripcion() {
        return envioDecorado.descripcion();
    }

    @Override
    public double costo() {
        // Delega la llamada al objeto envuelto.
        // Ahora funcionará porque envioDecorado ya no es null.
        return envioDecorado.costo();
    }
}