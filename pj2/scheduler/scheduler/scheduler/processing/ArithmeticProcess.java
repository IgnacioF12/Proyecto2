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

    private static final long TIEMPO_PROCESO = 400; 

    //*Constructor pra el proceso*//
    public ArithmeticProcess(int id) {
        super(id, TIEMPO_PROCESO); //*Se le asigna su id y tiempo fijo al proceso*//
    }

//*Se ejecuta el proceso, disminyuendo el tiempo restante según el tiempo ingresado*//
    @Override
    public void ejecutar(long tiempo) {
        setTiempoRestante(getTiempoRestante() - tiempo);
    }
}
