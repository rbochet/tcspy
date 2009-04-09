/**
 * Classe principale 
 * v0
 */
package spyer;

import java.util.Vector;

/**
 * @author Romain Bochet
 */
public class TcSpy {

	/**
	 * Point d'entrée
	 * @param args
	 */
	public static void main(String[] args) {		
		System.out.println("Lancement de l'application");
		System.out.println("+ Chargement des paramètres");
		LoadParam lp = new LoadParam("/home/romain/workspace/tc-spy/src/spyer/config");
		System.out.println("+ Lancement de l'interface");
		FenGraph maFen = new FenGraph(lp.getHosts());
		maFen.setVisible(true);
		
		System.out.println("Threads lancés");
		
		while(true) {int a=1;}

	}

}
