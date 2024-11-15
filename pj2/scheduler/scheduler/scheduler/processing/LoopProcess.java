/* LoopProcess.java */
/**
** Hecho por: Erick Motta
** Carnet: 24003932
** Seccion: A
**/
//*Implementación del proceso LoopProcess, que permite un proceso iterativo que simula tareas repetitivas*//

package scheduler.processing;

public class LoopProcess extends SimpleProcess {
    // *Tiempo fijo en milisegundos para los procesos de tipo loop*//
    private static final long TIEMPO_PROCESO = 600;

    // *Constructor pra el proceso*//
    public LoopProcess(int id) {
        super(id, TIEMPO_PROCESO); // *Se le asigna su id y tiempo fijo al proceso*//
    }

    // *Se ejecuta el proceso, disminyuendo el tiempo restante según el tiempo
    // ingresado*//
    @Override
    public void ejecutar(long tiempo) {
        setTiempoRestante(getTiempoRestante() - tiempo);
    }
}