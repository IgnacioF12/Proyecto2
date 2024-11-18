package scheduler.processing;

import scheduler.processing.SimpleProcess;

/**
 * IOProcess.java
 * 
 * Hecho por: Jose Angel Salazar
 * Carnet: 23012055
 * Sección: A
 * 
 * Esta clase representa un proceso de entrada/salida, con un ID y un tiempo de
 * servicio
 * fijo. Hereda de la clase SimpleProcess.
 */
public class IOProcess extends SimpleProcess {

    private double tiempoServicio;

    public IOProcess(int id, double tiempoServicio) {
        super(id, tiempoServicio); // Llama al constructor de SimpleProcess
        this.tiempoServicio = tiempoServicio;
    }

    public double getTiempoServicio() {
        return tiempoServicio;
    }

    @Override
    public void ejecutar(double tiempo) {
        try {
            // Verificar si el tiempo de servicio es válido
            if (tiempo < 0) {
                throw new IllegalArgumentException("El tiempo de servicio no puede ser negativo.");
            }

            System.out.println("Ejecutando IOProcess con ID: " + getId() + " por " + tiempo + " ms.");
            Thread.sleep((long) tiempo * 1000); // Aquí se puede lanzar InterruptedException
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("El proceso fue interrumpido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage()); // Error por tiempo de servicio inválido
        } catch (Exception e) {
            e.printStackTrace(); // Imprime cualquier otro tipo de error inesperado
        }
    }
}