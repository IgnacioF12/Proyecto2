package scheduler.scheduling.policies;

import scheduler.processing.SimpleProcess;

/**
 * Interfaz que deben implementar todas las clases que quieren
 * comportarse como políticas de calendarización de procesos.
 */
public interface Enqueable {
    /** Ingresa un proceso a la cola de procesos de la política
     *  @param p Proceso a ingresar en la cola de la política
     */
    void add(SimpleProcess p);

    /** Remueve un proceso de la cola de procesos (el siguiente a
     *  ser atendido)
     */
    void remove();

    /** Devuelve el siguiente proceso a ser atendido. No lo remueve de la
     *  cola
     *  @return devuelve la instancia de SimpleProcess siguiente a 
     *          ser atendido.
     */
    SimpleProcess next();
}
