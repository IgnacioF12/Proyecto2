/* ConditionalProcess.java */

/**
** Hecho por: Jose Flores
** Carnet: 24001279
** Seccion: A
**/

package scheduler.processing;

import scheduler.processing.SimpleProcess;

public class ConditionalProcess extends SimpleProcess {
    private double t;

    /* Constructor */
    public ConditionalProcess(int id, double t) {
        super(id, t);
        this.t = t;
    }

    /* Devuelve el tiempo del proceso */
    @Override
    public double getTiempoRestante() {
        return t;
    }

    /* Ejecuta el proceso */
    @Override
    public void ejecutar(double t) {
        try {
            if (t < 0) {
                throw new Error("Tiempo de servicio invalido");
            }
            System.out.println("Ejecutando proceso " + getId() + " con tiempo de " + t + "ms");
            Thread.sleep((long) t * 1000);
        } catch (Exception err) {
            throw new Error(err);
        }
    }
}
