package spyer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Host extends Thread {
	private int time;
	private String name;
	private long lastCheck;
	private InetAddress ad;
	private boolean runner;
	private int port;
	
	private boolean up;			// Est il allumé ?
	private boolean service;	// Est ce que le service tourne ?s
	private final int timeOut;
	
	private ImageIcon i_down;
	private ImageIcon i_up;
	private ImageIcon s_down;
	private ImageIcon s_up;

	public JLabel jName;
	public JLabel jUp;
	public JLabel jService;
	
	/**
	 * Constructeur
	 * @param host L'adresse de l'hote à checker
	 * @param time L'intervalle de temps de check
	 */
	public Host(String name, int port, int time) {
		this.time = time;
		this.name = name;
		this.port = port;
		
		this.up = false;
		this.service = false;
		this.timeOut = 6000;
		
		this.runner = true;
		
		this.jName = new JLabel(name);
		this.jUp = new JLabel("Computing ...");
		this.jUp.setIcon(i_down);
		this.jService = new JLabel("Computing ...");
		this.jService.setIcon(s_down);
		
		i_down = 	new ImageIcon("down.jpg");
		i_up = 		new ImageIcon("up.jpg");
		s_down = 	new ImageIcon("s_down.jpg");
		s_up = 		new ImageIcon("s_up.jpg");
	}
	

	@Override
	public void run() {
		try {
			ad = InetAddress.getByName(name);
			try {
				while(this.runner) {
					this.check();
					System.out.println(this);
					Thread.yield();
					Thread.sleep(time*1000);
				}
			} catch(InterruptedException e) {
				System.out.println(" JE SUIS SORTI ! "+this);
			}
		} catch (UnknownHostException e) {
			runner = false;
		} 
	}
	
	/**
	 * Permet de savoir l'état de l'host
	 * @return L'état courant
	 */
	public boolean getUp() {
		return this.up;
	}


	@Override
	public String toString() {
		return this.name+" :: UP : "+this.up+" :: SERVICE : ("+this.port+") "+service;
	}
	
	/**
	 * Force la mise à jour de l'état
	 */
	public void forceCheck() {
		this.check();
	}
	
	/**
	 * Met à jour l'état de l'host
	 * Au niveau de UP/DOWN et du service associé
	 */
	private void check() {
		Socket maSock;
		try {
			this.up = this.ad.isReachable(timeOut);
		} catch (UnknownHostException e) {
			this.up = false;
			this.service = false;
			return;
		} catch (IOException e) {
			this.up = false;
			this.service = false;
			return;
		}

		/* On teste le service */
		try {
			maSock = new Socket(ad, this.port);
			this.service = true;
			this.up = true;		// permet de contrer le blocage de pings
			maSock.close();
		} catch (UnknownHostException e) {
			this.up = false;
			this.service = false;
			return;
		} catch (IOException e) {
			this.service = false;
		}

		this.jUp.setText("Up : "+this.up);
		this.jService.setText("Service : "+this.service);
	}
	
	public JLabel getJName() {
		return jName;
	}


	public JLabel getJUp() {
		return jUp;
	}


	public JLabel getJService() {
		return jService;
	}
}
