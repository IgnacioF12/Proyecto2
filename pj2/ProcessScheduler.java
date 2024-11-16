/* ProcessScheduler.java */

/**
** Hecho por: Jose Flores
** Carnet: 24001279
** Seccion: A
**/

package pj2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import scheduler.processing.SimpleProcess;
import scheduler.scheduling.policies.FCFS;
import scheduler.scheduling.policies.LCFSPolicy;
import scheduler.scheduling.policies.PP;
import scheduler.scheduling.policies.Policy;
import scheduler.scheduling.policies.RRPolicy;

public class ProcessScheduler {
    static Queue<SimpleProcess> processQueue = new LinkedList<>();
    static int processDone = 0;
    static Policy policy;

    public static void main(String[] args) {
        if (args.length < 6) {
            System.out.println("Error capa 8");
            return;
        }

        boolean dual = args[0].equalsIgnoreCase("-dual");
        String p = dual ? args[1] : args[0];
        String tRange = dual ? args[2] : args[1];
        long arithT = Long.parseLong(dual ? args[3] : args[2]);
        long ioT = Long.parseLong(dual ? args[4] : args[3]);
        long condT = Long.parseLong(dual ? args[5] : args[4]);
        long loopT = Long.parseLong(dual ? args[6] : args[5]);
        long quantum = 0;

        if (p.equals("-rr")) {
            if ((dual && args.length < 8) || (!dual && args.length < 7)) {
                System.out.println("Quantum necesario");
                return;
            }
            quantum = Long.parseLong(dual ? args[7] : args[6]);
        }

        if (p.equalsIgnoreCase("-fcfs")) {
            policy = new FCFS();
        } else if (p.equalsIgnoreCase("-lcfs")) {
            policy = new LCFSPolicy();
        } else if (p.equalsIgnoreCase("-pp")) {
            policy = new PP();
        } else if (p.equalsIgnoreCase("-rr")) {
            policy = new RRPolicy((long) (quantum * 1000));
        } else {
            System.out.println("Error politica");
        }

        System.out.println("Dual: " + (dual ? "Activo" : "Desactivo"));
        System.out.println("Politca" + p);

    }

    /*  */
    public static void run(long arithT, long ioT, long condT, long loopT) {

    }

    /*  */
    public static void queue(SimpleProcess p) {

    }

    /*  */
    public static void print() {

    }
}
