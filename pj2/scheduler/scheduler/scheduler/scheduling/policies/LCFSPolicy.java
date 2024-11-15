package scheduler.scheduling.policies;

import scheduler.processing.SimpleProcess;
import java.util.Stack;

/**
 * LCFSPolicy.java
 * 
 * Esta clase implementa la política Last-Come-First-Served (LCFS),
 * en la que el último proceso en llegar es el primero en ser atendido.
 */
public class LCFSPolicy implements Enqueable {

    private Stack<SimpleProcess> stack;

    public LCFSPolicy() {
        stack = new Stack<>();
    }

    /**
     * Ingresa un proceso a la pila de procesos (último en llegar, primero en ser
     * atendido).
     * 
     * @param p Proceso a ingresar en la pila.
     */
    @Override
    public void add(SimpleProcess p) {
        stack.push(p);
    }

    /**
     * Remueve el proceso de la pila que será atendido (el proceso en el tope).
     */
    @Override
    public void remove() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    /**
     * Devuelve el siguiente proceso a ser atendido (último proceso en llegar),
     * sin removerlo de la pila.
     * 
     * @return El proceso en el tope de la pila, o null si la pila está vacía.
     */
    @Override
    public SimpleProcess next() {
        return stack.isEmpty() ? null : stack.peek();
    }

    /**
     * Procesa el siguiente proceso en la pila.
     */
    public void procesar() {
        if (!stack.isEmpty()) {
            SimpleProcess proceso = stack.pop();
            long tiempoRestante = proceso.getTiempoRestante(); // Obtiene el tiempo restante

            // Llama a ejecutar pasando el tiempo restante como argumento
            proceso.ejecutar(tiempoRestante); // Aquí ejecutamos con el tiempo restante

            // Si el proceso aún tiene tiempo restante, lo volvemos a poner en la pila
            if (proceso.getTiempoRestante() > 0) {
                stack.push(proceso);
            }
        }
    }
}