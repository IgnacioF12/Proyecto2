/* ArithmeticProcess.java */
/**
** Hecho por: Erick Motta
** Carnet: 24003932
** Seccion: A
**/
//*Implementación del proceso ArithmeticProcess, que permite que cada proceso aritmético tenga un tiempo de ejecución fijo*//

package scheduler.processing;

//*Tiempo fijo en milisegundos para los procesos aritméticos*//
public class ArithmeticProcess extends SimpleProcess {

    private double tiempoServicio;

    // *Constructor pra el proceso*//
    public ArithmeticProcess(int id, double tiempoServicio) {
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