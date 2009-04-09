package spyer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Permet de charger les paramètres depuis le fichier de configuration
 * Syntaxe :
 * host port
 * google.fr 80
 * localhost 22 
 * @author Romain Bochet
 */
public class LoadParam {
	private String path;
	private Vector<Host> hosts;
	
	public LoadParam(String path) {
		this.path = path;
		this.hosts = new Vector<Host>();
		readFile();
	}
	
	private void readFile() {
		Scanner scanner;
		String[] line;
		Host h;
		try {
			scanner = new Scanner(new File(path));
			while (scanner.hasNextLine()) {
				line = scanner.nextLine().split(" ");
				h = new Host(line[0], Integer.parseInt(line[1]), 1);
				h.start();
				this.hosts.add(h);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier de paramètres n'existe pas !");
		}
	}
	
	public Vector<Host> getHosts() {
		return this.hosts;
	}
}
