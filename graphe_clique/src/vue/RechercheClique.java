package vue;

import modele.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class RechercheClique extends JPanel implements ActionListener
{
	private JButton rechercheClique = new JButton("START");
	private JButton clear = new JButton("CLEAR");
	public static String typeRechercheClique;

	public RechercheClique()
	{
		typeRechercheClique="";
		add(clear, BorderLayout.WEST);
		add(rechercheClique, BorderLayout.CENTER);
		clear.addActionListener(this);
		rechercheClique.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == rechercheClique)
		{
			AfficheClique.reset();
			long start = System.currentTimeMillis();
			GrapheClique.loadFichier();
			// GrapheClique.setClique(new Clique_3(GrapheClique.getGraphe()));
			// GrapheClique.setClique(new Clique_2(GrapheClique.getGraphe()));
			long start2 = System.currentTimeMillis();
			switch (typeRechercheClique)
			{
			case "optimal":
				GrapheClique.setClique(new Clique_1(GrapheClique.getGraphe()));
				break;
			case "semiGlouton":
				GrapheClique.setClique(new Clique_2(GrapheClique.getGraphe()));
				break;
			case "glouton":
				GrapheClique.setClique(new Clique_3(GrapheClique.getGraphe()));
				break;

			default:
				GrapheClique.setClique(new Clique_3(GrapheClique.getGraphe()));
				break;
			}

			GrapheClique.getClique().rechercheClique();
			long dure = System.currentTimeMillis() - start;
			Affichage.afficher(("durée chargement fichier : " + ((float) (start2 - start)) / 1000 + "s"));
			Affichage.afficher(("durée calcul : " + ((float) (dure)) / 1000 + "s"));
			Affichage.afficher(("durée = " + ((float) dure + (start2 - start)) / 1000 + "s"));
		} else if (e.getSource() == clear)
		{
			AfficheClique.reset();
		}
	}
}
