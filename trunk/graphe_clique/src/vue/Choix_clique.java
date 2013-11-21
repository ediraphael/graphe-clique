package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class Choix_clique extends JPanel implements ActionListener
{
	private JRadioButton optimal = new JRadioButton("Optimal", false);
	private JRadioButton semiGlouton = new JRadioButton("Semi-glouton", false);
	private JRadioButton glouton = new JRadioButton("Glouton", true);

	public Choix_clique()
	{
		optimal.addActionListener(this);
		semiGlouton.addActionListener(this);
		glouton.addActionListener(this);
		
		ButtonGroup groupe = new ButtonGroup();
		groupe.add(optimal);
		groupe.add(semiGlouton);
		groupe.add(glouton);
		
		this.setLayout(new GridLayout());
		this.add(optimal);
		this.add(semiGlouton);
		this.add(glouton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == optimal)
		{
			System.out.println("1");
			RechercheClique.typeRechercheClique="optimal";
		}else if (e.getSource() == semiGlouton)
		{
			System.out.println("2");
			RechercheClique.typeRechercheClique="semiGlouton";
		}else if (e.getSource() == glouton)
		{
			System.out.println("3");
			RechercheClique.typeRechercheClique="glouton";
		}

	}

}
