package vue;

import modele.*;

import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.util.Vector;

public class GrapheClique
{
	public static Graphe graphe = new Graphe();
	public static CliqueAbstraite clique;
	public static DownloadFile dfile = new DownloadFile("Votre fichier correspondant au graphe", "C250.9.clq");
	public static Choix_clique cliques = new Choix_clique();
	// public static ProgressBar progressBar = new ProgressBar(clique, 100);
	public static JProgressBar progressBar = new JProgressBar(0,5000);

	public static Graphe getGraphe()
	{
		return graphe;
	}

	public static void setGraphe(Graphe graphe)
	{
		GrapheClique.graphe = graphe;
	}

	public static CliqueAbstraite getClique()
	{
		return clique;
	}

	public static void setClique(CliqueAbstraite clique)
	{
		GrapheClique.clique = clique;
	}

	public static void loadFichier()
	{
		// graphe.setNoeuds(new Vector<Noeud>());
		graphe = new Graphe(dfile.getPathText());
		// graphe.loadFile(dfile.getPathText());
	}

	public static void lancerAffichage() throws IOException
	{
		JFrame cadre = new javax.swing.JFrame("Algo Glouton");

		AfficheClique aclique = new AfficheClique();
		RechercheClique rclique = new RechercheClique();
		Ecran_fenetre ecran = new Ecran_fenetre(aclique);
		Affichage.setECRAN(ecran);

		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		top.add(dfile, BorderLayout.NORTH);
		top.add(cliques, BorderLayout.CENTER);
		top.add(progressBar, BorderLayout.SOUTH);

		JPanel panneau = new JPanel();
		panneau.setLayout(new BorderLayout());
		panneau.add(top, BorderLayout.NORTH);
		panneau.add(aclique, BorderLayout.CENTER);
		panneau.add(rclique, BorderLayout.SOUTH);

		cadre.setContentPane(panneau);
		cadre.setLocation(400, 300);
		cadre.pack();
		cadre.setVisible(true);
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] arg) throws IOException
	{
		GrapheClique.lancerAffichage();
	}
}
