package scheduler.processing;

/**
 * Esta clase representa un proceso simple.
 */
public abstract class SimpleProcess {
	/**
	 * El campo id es un entero que identifica al proceso, los ids son correlativos
	 * por política
	 **/
	protected int id;
	/** El tiempo restante de ejecución del proceso (en milisegundos). **/
	protected long tiempoRestante;

	/**
	 * Inicializa el SimpleProcess con un id específico y un tiempo de servicio.
	 * 
	 * @param id             representa el id que se le asigna al SimpleProcess
	 * @param tiempoRestante representa el tiempo que el proceso necesita para
	 *                       completar su ejecución
	 */
	public SimpleProcess(int id, long tiempoRestante) {
		this.id = id;
		this.tiempoRestante = tiempoRestante;
	}

	/**
	 * Devuelve el id del SimpleProcess
	 * 
	 * @return devuelve el entero que representa el id del proceso
	 */
	public final int getId() {
		return this.id;
	}

	/**
	 * Devuelve el tiempo restante para que el proceso complete su ejecución.
	 * 
	 * @return el tiempo restante en milisegundos
	 */
	public long getTiempoRestante() {
		return this.tiempoRestante;
	}

	/**
	 * Establece el tiempo restante para la ejecución del proceso.
	 * 
	 * @param tiempoRestante el nuevo tiempo restante en milisegundos
	 */
	public void setTiempoRestante(long tiempoRestante) {
		this.tiempoRestante = tiempoRestante;
	}

	/**
	 * Método abstracto para ejecutar el proceso. Cada subclase debe definir cómo se
	 * ejecuta el proceso.
	 * Este método ahora acepta un parámetro de tiempo.
	 */
	public abstract void ejecutar(long tiempo);

	/**
	 * Formato imprimible para objetos SimpleProcess
	 * 
	 * @return devuelve un String de la forma [id:id_del_proceso,
	 *         tiempoRestante:tiempo]
	 */
	@Override
	public String toString() {
		return "[id:" + this.id + ", tiempoRestante:" + this.tiempoRestante + "]";
	}
}