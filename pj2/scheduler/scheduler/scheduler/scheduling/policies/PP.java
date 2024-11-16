/* PP.java */
/**
** Hecho por: Jose Flores
** Carnet: 24001279
** Seccion: A
**/

package scheduler.scheduling.policies;

import scheduler.processing.ArithmeticProcess;
import scheduler.processing.ConditionalProcess;
import scheduler.processing.IOProcess;
import scheduler.processing.LoopProcess;
import scheduler.processing.SimpleProcess;
import java.util.concurrent.ConcurrentLinkedQueue;;

public class PP extends Policy implements Enqueable {

    private ConcurrentLinkedQueue<SimpleProcess> ioProcess1;
    private ConcurrentLinkedQueue<SimpleProcess> arithProcess2;
    private ConcurrentLinkedQueue<SimpleProcess> conAndLoopProcess3;

    public PP() {
        ioProcess1 = new ConcurrentLinkedQueue<>();
        arithProcess2 = new ConcurrentLinkedQueue<>();
        conAndLoopProcess3 = new ConcurrentLinkedQueue<>();
    }

    /* Agrega el proceso a la fila segun el orden de prioridad */
    @Override
    public void add(SimpleProcess p) {
        if (p instanceof IOProcess) {
            ioProcess1.add(p);
        } else if (p instanceof ArithmeticProcess) {
            arithProcess2.add(p);
        } else if (p instanceof ConditionalProcess || p instanceof LoopProcess) {
            conAndLoopProcess3.add(p);
        }
    }

    /*
     * Obtiene el siguiente procesa a ser atendido, tomando en cuenta el orden de
     * prioridad
     */
    @Override
    public SimpleProcess next() {
        if (!ioProcess1.isEmpty()) {
            return ioProcess1.peek();
        } else if (!arithProcess2.isEmpty()) {
            return arithProcess2.peek();
        } else {
            return conAndLoopProcess3.peek();
        }
    }

    /* Elimina el ultimo proceso tomando en cuenta el orden de prioridad */
    @Override
    public void remove() {
        if (!ioProcess1.isEmpty()) {
            ioProcess1.poll();
        } else if (!arithProcess2.isEmpty()) {
            arithProcess2.poll();
        } else {
            conAndLoopProcess3.poll();
        }
    }

    /*
     * Saca el proceso en turno tomando el cuenta el tiempo segun el tipo de proceso
     */
    public void procesar() {
        if (!ioProcess1.isEmpty()) {
            SimpleProcess p = ioProcess1.poll();
            long t = p.getTiempoRestante();

            p.ejecutar(t);
        } else if (!arithProcess2.isEmpty()) {
            SimpleProcess p = arithProcess2.poll();
            long t = p.getTiempoRestante();

            p.ejecutar(t);
        } else {
            SimpleProcess p = conAndLoopProcess3.poll();
            long t = p.getTiempoRestante();

            p.ejecutar(t);
        }
    }
}
