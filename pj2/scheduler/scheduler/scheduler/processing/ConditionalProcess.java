/* ConditionalProcess.java */

/**
** Hecho por: Jose Flores
** Carnet: 24001279
** Seccion: A
**/

package scheduler.processing;

import scheduler.processing.SimpleProcess;

public class ConditionalProcess extends SimpleProcess {
    private long t;

    /* Constructor */
    public ConditionalProcess(int id, long t) {
        super(id, t);
        this.t = t;
    }

    /* Devuelve el tiempo del proceso */
    @Override
    public long getTiempoRestante() {
        return t;
    }

    /* Ejecuta el proceso */
    @Override
    public void ejecutar(long t) {
        try {
            if (t < 0) {
                throw new Error("Tiempo de servicio invalido");
            }
            System.out.println("Ejecutando proceso " + getId() + " con tiempo de " + t + "ms");
            Thread.sleep(t);
        } catch (Exception err) {
            throw new Error(err);
        }
    }
}
