
/* ConditionalProcess.java */
package scheduler.processing;

import scheduler.processing.SimpleProcess;

public class ConditionalProcess extends SimpleProcess {
    private long t;

    public ConditionalProcess(int id, long t) {
        super(id);
        this.t = t;
    }

    public long getT() {
        return t;
    }

    public void run(long t) {
        try {
            if (t < 0) {
                throw new Error("Tiempo de servicio invalido");
            }
            System.out.println("Ejecutando proceso " + getId() + " con tiempo de " + t);
            Thread.sleep(t);
        } catch (Exception err) {
            throw new Error(err);
        }
    }
}
