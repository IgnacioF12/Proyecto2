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

public abstract class PP extends Policy implements Enqueable {

    private ConcurrentLinkedQueue<SimpleProcess> ioProcess1;
    private ConcurrentLinkedQueue<SimpleProcess> arithProcess2;
    private ConcurrentLinkedQueue<SimpleProcess> conAndLoopProcess3;

    public PP() {
        ioProcess1 = new ConcurrentLinkedQueue<>();
        arithProcess2 = new ConcurrentLinkedQueue<>();
        conAndLoopProcess3 = new ConcurrentLinkedQueue<>();
    }

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
}
