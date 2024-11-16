package scheduler.scheduling.policies;

import scheduler.processing.SimpleProcess;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * RRPolicy.java
 * 
 * Hecho por: Nombre del Implementador
 * Carnet: 12345678
 * Sección: A
 * 
 * Esta clase implementa la política Round-Robin (RR),
 * asignando a cada proceso un quantum fijo de tiempo antes de rotarlos.
 */
public class RRPolicy extends Policy implements Enqueable {

    private ConcurrentLinkedQueue<SimpleProcess> queue;
    private long quantum; // Cambié el tipo a long ya que el tiempo debería ser en milisegundos enteros

    public RRPolicy(long quantum) {
        this.queue = new ConcurrentLinkedQueue<>();
        this.quantum = quantum;
    }

    /**
     * Ingresa un proceso a la cola de procesos (Round-Robin).
     * 
     * @param p Proceso a ingresar en la cola.
     */
    @Override
    public void add(SimpleProcess p) {
        queue.add(p);
    }

    /**
     * Remueve el siguiente proceso de la cola que ha terminado su quantum.
     */
    @Override
    public void remove() {
        queue.poll();
    }

    /**
     * Devuelve el siguiente proceso a ser atendido (en el frente de la cola)
     * sin removerlo.
     * 
     * @return El proceso en el frente de la cola, o null si la cola está vacía.
     */
    @Override
    public SimpleProcess next() {
        return queue.peek();
    }

    /**
     * Procesa el siguiente proceso en la cola por el tiempo del quantum.
     */
    public void procesar() {
        if (!queue.isEmpty()) {
            SimpleProcess proceso = queue.poll();

            long tiempoRestante = proceso.getTiempoRestante();
            long tiempoProcesado = Math.min(quantum, tiempoRestante);

            proceso.ejecutar(tiempoProcesado); // Ejecuta el proceso por el tiempo de quantum (en milisegundos)
            proceso.setTiempoRestante(tiempoRestante - tiempoProcesado);

            if (proceso.getTiempoRestante() > 0) {
                queue.add(proceso); // Re-ingresar si el proceso no ha terminado
            }
        }
    }
}