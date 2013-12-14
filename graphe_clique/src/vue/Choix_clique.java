package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class Choix_clique extends JPanel implements ActionListener
{
	//private JRadioButton optimal = new JRadioButton("Optimal", false);
	private JRadioButton semiGlouton = new JRadioButton("Semi-glouton", false);
	private JRadioButton gloutonAmeliorePlus = new JRadioButton("Glouton Amélioré +", false);
	private JRadioButton gloutonAmeliore = new JRadioButton("Glouton Amélioré", false);
	private JRadioButton glouton = new JRadioButton("Glouton", true);

	public Choix_clique()
	{
		//optimal.addActionListener(this);
		semiGlouton.addActionListener(this);
		gloutonAmeliorePlus.addActionListener(this);
		gloutonAmeliore.addActionListener(this);
		glouton.addActionListener(this);

		ButtonGroup groupe = new ButtonGroup();
		//groupe.add(optimal);
		groupe.add(semiGlouton);
		groupe.add(gloutonAmeliorePlus);
		groupe.add(gloutonAmeliore);
		groupe.add(glouton);

		this.setLayout(new GridLayout());
		//this.add(optimal);
		this.add(new JLabel("Algorithme :"));
		this.add(semiGlouton);
		this.add(gloutonAmeliore);
		this.add(glouton);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == semiGlouton)
		{
			System.out.println("2");
			RechercheClique.typeRechercheClique = "semiGlouton";
		} else if (e.getSource() == glouton)
		{
			System.out.println("3");
			RechercheClique.typeRechercheClique = "glouton";
		} else if (e.getSource() == gloutonAmeliore)
		{
			System.out.println("4");
			RechercheClique.typeRechercheClique = "gloutonAmeliore";
		}
		else if (e.getSource() == gloutonAmeliorePlus)
		{
			System.out.println("5");
			RechercheClique.typeRechercheClique = "gloutonAmeliorePlus";
		}

	}

}
