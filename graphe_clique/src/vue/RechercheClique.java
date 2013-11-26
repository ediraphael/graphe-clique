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
		typeRechercheClique = "";
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
			long start2 = System.currentTimeMillis();
			Affichage.afficher(("durée chargement fichier : " + ((float) (start2 - start)) / 1000 + "s"));
			switch (typeRechercheClique)
			{
			case "optimal":
				GrapheClique.setClique(new Clique_1(GrapheClique.getGraphe()));
				GrapheClique.progressBar.setMaximum(GrapheClique.getGraphe().getNbNoeud() );
				break;
			case "semiGlouton":
				GrapheClique.setClique(new Clique_2_2(GrapheClique.getGraphe()));
				GrapheClique.progressBar.setMaximum(GrapheClique.getGraphe().getNbNoeud()  + 1);
				break;
			case "gloutonAmeliorePlus":
				GrapheClique.setClique(new Clique_4(GrapheClique.getGraphe()));
				GrapheClique.progressBar.setMaximum(GrapheClique.getGraphe().getNbNoeud() + 1);
				break;
			case "gloutonAmeliore":
				GrapheClique.setClique(new Clique_5(GrapheClique.getGraphe()));
				GrapheClique.progressBar.setMaximum(1001);
				break;
			case "glouton":
				GrapheClique.setClique(new Clique_3_2(GrapheClique.getGraphe()));
				GrapheClique.progressBar.setMaximum(GrapheClique.getGraphe().getNbNoeud()  / 10);
				break;

			default:
				GrapheClique.setClique(new Clique_3_2(GrapheClique.getGraphe()));
				GrapheClique.progressBar.setMaximum(GrapheClique.getGraphe().getNbNoeud()  / 10);
				break;
			}
			// GrapheClique.progressBar.setClique(GrapheClique.getClique());
			Thread th = new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					Thread th = new Thread(new Runnable()
					{
						@Override
						public void run()
						{
							while (GrapheClique.getClique().isRunning())
							{
								GrapheClique.progressBar.setValue(GrapheClique.getClique().getCpt());
							}
							GrapheClique.progressBar.setValue(GrapheClique.progressBar.getMaximum());
						}
					});
					th.start();
					GrapheClique.getClique().lancerRecherche();
				}
			});
			th.start();

		} else if (e.getSource() == clear)
		{
			Affichage.reset();
		}
	}
}
