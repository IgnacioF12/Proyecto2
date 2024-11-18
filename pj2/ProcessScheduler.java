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
import java.util.Random;
import java.util.Scanner;

import scheduler.processing.ArithmeticProcess;
import scheduler.processing.ConditionalProcess;
import scheduler.processing.IOProcess;
import scheduler.processing.LoopProcess;
import scheduler.processing.SimpleProcess;
import scheduler.scheduling.policies.FCFS;
import scheduler.scheduling.policies.LCFSPolicy;
import scheduler.scheduling.policies.PP;
import scheduler.scheduling.policies.Policy;
import scheduler.scheduling.policies.RRPolicy;

public class ProcessScheduler {
    static Queue<SimpleProcess> processQueue = new LinkedList<>();
    static int processDone = 0;
    static int processId = 6;
    static Policy policy;

    public static void main(String[] args) {
        if (args.length < 6) {
            System.out.println("Error capa 8");
            return;
        }

        boolean dual = args[0].equalsIgnoreCase("-dual");
        String p = dual ? args[1] : args[0];
        String tRange = dual ? args[2] : args[1];
        double arithT = Double.parseDouble(dual ? args[3] : args[2]);
        double ioT = Double.parseDouble(dual ? args[4] : args[3]);
        double condT = Double.parseDouble(dual ? args[5] : args[4]);
        double loopT = Double.parseDouble(dual ? args[6] : args[5]);
        double quantum = 0;

        /*
         * Detecta si se escogio la politica round robin y comprueba que este el dato de
         * quantum
         */
        if (p.equals("-rr")) {
            if ((dual && args.length < 8) || (!dual && args.length < 7)) {
                System.out.println("Quantum necesario");
                return;
            }
            quantum = Double.parseDouble(dual ? args[7] : args[6]);
        }

        /* Crea las colas de cada politica respectivamente */
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
            return;
        }

        System.out.println("Simulación iniciada con política: " + p);

        queue(new IOProcess(1, ioT));
        queue(new ArithmeticProcess(2, arithT));
        queue(new LoopProcess(3, loopT));
        queue(new ConditionalProcess(4, condT));
        queue(new IOProcess(5, ioT));

        new Thread(() -> {
            while (true) {
                add(arithT, ioT, condT, loopT, tRange);
                try {
                    double min = Double.parseDouble(tRange.split("-")[0]);
                    double max = Double.parseDouble(tRange.split("-")[1]);
                    double ra = getR(min, max);
                    Thread.sleep((long) (ra * 1000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

        if (dual) {
            new Thread(() -> {
                while (true) {
                    dual(p);
                    endProcess(p);
                }
            }).start();
        } else {
            new Thread(() -> {
                while (true) {
                    endProcess(p);
                }
            }).start();

            new Thread(() -> {
                Scanner sc = new Scanner(System.in);
                while (true) {
                    String input = sc.nextLine();
                    if (input.equalsIgnoreCase("q")) {
                        System.out.println("Simulación detenida.");
                        System.exit(0); // Detener el programa
                    }
                }
            }).start();
        }
    }

    /* Finaliza los procesos sacandolos de la cola */
    public static void endProcess(String pol) {
        final SimpleProcess p;
        synchronized (processQueue) {
            if (processQueue.isEmpty()) {
                return;
            } else {
                if (pol.equalsIgnoreCase("-lcfs") && processQueue instanceof LinkedList) {
                    p = ((LinkedList<SimpleProcess>) processQueue).removeLast();
                } else {
                    p = processQueue.poll();
                }

            }
            if (p != null) {
                System.out.println("=====================================");
                System.out.println("Atendiendo Proceso: ");
                System.out.println("Id: " + p.getId() + " | Tipo: " + getType(p) + " | Tiempo restante: "
                        + p.getTiempoRestante());
                print();

                double tProcesar = pol.equalsIgnoreCase("-rr")
                        ? Math.min(p.getTiempoRestante(), ((RRPolicy) policy).getQuantum() / 1000.0)
                        : p.getTiempoRestante();

                Thread simulate = new Thread(() -> {
                    try {
                        Thread.sleep((long) (tProcesar) * 1000);
                    } catch (Exception err) {
                        throw new Error(err);
                    }
                });

                simulate.start();
                try {
                    simulate.join();
                } catch (Exception err) {
                    throw new Error(err);
                }

                double tRestante = p.getTiempoRestante() - tProcesar;
                if (tRestante > 0) {
                    p.setTiempoRestante(tRestante);
                    synchronized (processQueue) {
                        processQueue.add(p);
                    }
                    System.out.println("=====================================");
                    System.out.println(
                            "Proceso " + p.getId() + " regresó a la cola con " + tRestante + " segundos restantes.");
                } else {
                    processDone++;
                    System.out.println("=====================================");
                    System.out.println("Proceso " + p.getId() + " terminado.");
                }

            }
        }
    }

    /* Segund procesador para dual */
    public static void dual(String pol) {
        final SimpleProcess p;
        synchronized (processQueue) {
            if (processQueue.isEmpty()) {
                return;
            } else {
                if (pol.equalsIgnoreCase("-lcfs") && processQueue instanceof LinkedList) {
                    p = ((LinkedList<SimpleProcess>) processQueue).removeLast();
                } else {
                    p = processQueue.poll();
                }
            }
            if (p != null) {
                processDone++;
                System.out.println("=====================================");
                System.out.println("Dual Atendiendo Proceso: ");
                System.out.println("Id: " + p.getId() + " | Tipo: " + getType(p) + " | Tiempo restante: "
                        + p.getTiempoRestante());
                print();

                Thread simulate = new Thread(() -> {
                    try {
                        Thread.sleep((long) (p.getTiempoRestante()) * 1000);
                    } catch (Exception err) {
                        throw new Error(err);
                    }
                });

                simulate.start();
                try {
                    simulate.join();
                } catch (Exception err) {
                    throw new Error(err);
                }
                System.out.println("Proceso " + p.getId() + " terminado.");
            }
        }
    }

    /* Obtiene un numero aleatorio segun el rango ingresado */
    public static double getR(double min, double max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    /* Agrega los procesos a la cola */
    public static void queue(SimpleProcess p) {
        processQueue.add(p);
        System.out.println("=====================================");
        System.out.println("Agregando Proceso: ");
        System.out.println("Id: " + p.getId() + " | Tipo: " + getType(p) + " | Tiempo: " + p.getTiempoRestante());
        print();
    }

    /* Decide el tipo de proceso a agregar */
    public static void add(double arithT, double ioT, double condT, double loopT, String tRange) {
        double min = Double.parseDouble(tRange.split("-")[0]);
        double max = Double.parseDouble(tRange.split("-")[1]);
        double ra = getR(min, max);

        new Thread(() -> {
            try {
                Thread.sleep((long) (ra * 1000));
            } catch (Exception err) {
                throw new Error(err);
            }

            Random r = new Random();
            int x = r.nextInt(4);
            SimpleProcess p = null;
            if (x == 0) {
                p = new IOProcess(processId++, ioT);
            } else if (x == 1) {
                p = new ArithmeticProcess(processId++, arithT);
            } else if (x == 2) {
                p = new ConditionalProcess(processId++, condT);
            } else {
                p = new LoopProcess(processId++, loopT);
            }

            queue(p);
        }).start();
    }

    /* Devuelve el tipo de proceso */
    private static String getType(SimpleProcess p) {
        if (p instanceof ArithmeticProcess) {
            return "Aritmetico";
        } else if (p instanceof IOProcess) {
            return "Input/Output";
        } else if (p instanceof ConditionalProcess) {
            return "Condicional";
        } else if (p instanceof LoopProcess) {
            return "Iterativo";
        } else {
            return "Tipo desconocido";
        }
    }

    /* print de estado */
    public static void print() {
        System.out.println("=====================================");
        System.out.println("Política: " + policy.getClass().getSimpleName());
        System.out.println("Procesos atendidos: " + processDone);
        System.out.println("=====================================");
        System.out.println("Cola de procesos: ");
        if (processQueue.isEmpty()) {
            System.out.println("  (Vacía)");
        } else {
            for (SimpleProcess p : processQueue) {
                System.out.println("Id: " + p.getId() + " | Tiempo restante: " + p.getTiempoRestante() + " | Tipo: "
                        + getType(p));
            }
        }
    }
}