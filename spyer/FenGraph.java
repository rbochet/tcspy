package spyer;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenGraph extends JFrame{
	Vector<Host> lesHosts;
	public FenGraph(Vector<Host> hosts){
		super();
		this.lesHosts = hosts;
		build();
	}
	
	private void build(){
		setTitle("TcSpy"); 
		setSize(500,500); 
		setLocationRelativeTo(null);
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setContentPane(buildContentPane());
	}
	private JPanel buildContentPane(){
		JPanel global = new JPanel();
		global.setLayout(new GridLayout(this.lesHosts.size()+2, 1));
		
		/* Configuration en haut */
		JPanel conf = new JPanel();
		conf.setLayout(new FlowLayout());
		JButton maj = new JButton("Mise à jour");
		conf.add(maj);
		JButton config = new JButton("Configuration");
		conf.add(config);
		JButton  aprop = new JButton("À propos");
		conf.add(aprop);
		global.add(conf);
		
		JPanel titre = new JPanel();
		titre.setLayout(new GridLayout(1, 3));
		titre.add(new JLabel("HOST"));
		titre.add(new JLabel("UP"));
		titre.add(new JLabel("SERVICE"));
		global.add(titre);
		
		/* Ajout des composants du vecteur */
		Iterator<Host> iter = lesHosts.iterator();
		while(iter.hasNext()) {
			Host h = iter.next();
			JPanel host = new JPanel();
			host.setLayout(new GridLayout(1, 3));
			host.add(h.getJName());
			host.add(h.getJUp());
			host.add(h.getJService());
			global.add(host);
		}
		return global;
	}
}
