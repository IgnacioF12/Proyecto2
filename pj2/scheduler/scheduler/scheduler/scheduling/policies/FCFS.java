/*FCFS.java*/
/**
** Hecho por: Erick Motta
* Carnet: 24003932
** Seccion: A
**/
//*Implementación de la política FCFS donde el primer proceso en ingresar es el que va a ser atendido primero*//

package scheduler.scheduling.policies;

import scheduler.processing.SimpleProcess;
import java.util.LinkedList;
import java.util.Queue;


public class FCFS extends Policy implements Enqueable {

    private Queue<SimpleProcess> queue;
//*Creación de la cola*//
    public FCFS() {
        queue = new LinkedList<>();
    }

//*aqui se agrega el proceso a la cola por eso el paramatero p*//
    @Override
    public void add(SimpleProcess p) {
        queue.offer(p);
    }

//*si el proceso se ha completado lo elimina de la cola *//
    @Override
    public void remove() {
        if (!queue.isEmpty()) {
            queue.poll();
        }
    }

//*Retorna el proceso que esta por atenderse, y retorna el proceso que esta hasta arriba de la cola, si esat vacio retorna null*//
    @Override
    public SimpleProcess next() {
        return queue.isEmpty() ? null : queue.peek();
    }

//*Ejecuta el proceso hasta completarlo*/¨/
    public void procesar() {
        if (!queue.isEmpty()) {
            SimpleProcess proceso = queue.peek();
            long tiempoRestante = proceso.getTiempoRestante();
            
            proceso.ejecutar(tiempoRestante); //*ejecuta el proceso en cuestión de su tiempo total*//
            
            //*al terminar el proceso lo elimina de la cola**//
            if (proceso.getTiempoRestante() <= 0) {
                queue.poll();
            }
        }
    }
}
