package spyer;

public class Host implements Runnable {
	private int time;
	private String name;
	private int etat;	// L'état de l'hote
	private int 
	
	public static final int UP = 1;
	public static final int DOWN = 0;
	
	/**
	 * Constructeur
	 * @param host L'adresse de l'hote à checker
	 * @param time L'intervalle de temps de check
	 */
	public Host(String name, int time) {
		this.time = time;
		this.name = name;
		this.etat = Host.DOWN;
	}
	

	@Override
	public void run() {
	}
	
	/**
	 * Permet de savoir l'état de l'host
	 * @return L'état courant
	 */
	public int getEtat() {
		return this.etat;
	}


	@Override
	public String toString() {
		return this.name+" : "+this.etat;
	}
	
	/**
	 * Force la mise à jour de l'état
	 */
	public void forceCheck() {
		this.check();
	}
	
	/**
	 * Met à jour l'état de l'host
	 */
	private void check() {
		
	}
	

}
