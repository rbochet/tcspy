package spyer;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class FenGraph extends JFrame implements ActionListener {
	private JButton maj;
	private JButton config;
	private JButton  aprop;
	private Vector<Host> lesHosts;
	public FenGraph(Vector<Host> hosts){
		super();
		this.lesHosts = hosts;
		build();

		setIconImage(Toolkit.getDefaultToolkit().getImage("/home/romain/workspace/tc-spy/src/spyer/icon.png")); 
	}
	
	private void build(){
		setTitle("TcSpy"); 
		setSize(600,600); 
		setLocationRelativeTo(null);
		setResizable(true); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setContentPane(buildContentPane());
	}
	private JPanel buildContentPane(){
		JPanel global = new JPanel();
		global.setLayout(new GridLayout(this.lesHosts.size()+2, 1));
		
		/* Configuration en haut */
		JPanel conf = new JPanel();
		conf.setLayout(new FlowLayout());
		maj = new JButton("Mise à jour");
		maj.addActionListener(this);
		conf.add(maj);
		ImageIcon settings  =	new ImageIcon("/home/romain/workspace/tc-spy/src/spyer/config.png");
		config = new JButton("Configuration", settings);
		config.addActionListener(this);
		conf.add(config);
		ImageIcon about  =	new ImageIcon("/home/romain/workspace/tc-spy/src/spyer/about.png", "About");
		aprop = new JButton("À propos", about);
		aprop.addActionListener(this);
		conf.add(aprop);
		global.add(conf);
		
		JPanel titre = new JPanel();
		titre.setLayout(new GridLayout(1, 3));
		titre.add(new JLabel("HOST", JLabel.CENTER));
		titre.add(new JLabel("UP", JLabel.CENTER));
		titre.add(new JLabel("SERVICE (ssh : 22)", JLabel.CENTER));
		global.add(titre);
		
		/* Ajout des composants du vecteur */
		Iterator<Host> iter = lesHosts.iterator();
		while(iter.hasNext()) {
			Host h = iter.next();
			JPanel host = new JPanel();
			host.setLayout(new GridLayout(1, 3));
			host.add(h.jName);
			host.add(h.jUp);
			host.add(h.jService);
			global.add(host);
		}
		return global;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "À propos") {
			JOptionPane.showMessageDialog(null, "Romain Bochet (@ insa pour les bugs)" +
			"\n Ce programme est un logiciel libre : http://github.com/rbochet/tcspy") ;
		} else if (e.getActionCommand() == "Mise à jour") {
			Iterator<Host> iter = lesHosts.iterator();
			while(iter.hasNext())
				iter.next().forceCheck();
		}else if (e.getActionCommand() == "Configuration") {
			int time = Integer.parseInt(JOptionPane.showInputDialog("Entrer le temps de rafraîchissement (en s) :"));
			Iterator<Host> iter = lesHosts.iterator();
			while(iter.hasNext())
				iter.next().majTime(time*1000);
		}

	}
}
