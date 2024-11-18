/* LoopProcess.java */
/**
** Hecho por: Erick Motta
** Carnet: 24003932
** Seccion: A
**/
//*Implementación del proceso LoopProcess, que permite un proceso iterativo que simula tareas repetitivas*//

package scheduler.processing;

public class LoopProcess extends SimpleProcess {
    // *Tiempo variable*//
    private double tiempoServicio;

    // *Constructor pra el proceso*//
    public LoopProcess(int id, double tiempoServicio) {
        super(id, tiempoServicio);
        this.tiempoServicio = tiempoServicio; // *Se le asigna su id y tiempo fijo al proceso*//
    }

    // *Se ejecuta el proceso, disminyuendo el tiempo restante según el tiempo
    // ingresado*//
    @Override
    public void ejecutar(double tiempo) {
        setTiempoRestante(getTiempoRestante() - tiempo);
    }
}