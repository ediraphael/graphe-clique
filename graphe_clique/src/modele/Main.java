package modele;

public class Main
{
	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();

		// Graphe graphe = new Graphe("C2000.9.clq");
		// Graphe graphe = new Graphe("fichier.txt");

		// Graphe graphe = new Graphe("hier.txt");
		// Graphe graphe = new Graphe("fichier.txt");

		// Graphe graphe = new Graphe("C125.9.clq");
		// Graphe graphe = new Graphe("C250.9.clq");
		// Graphe graphe = new Graphe("C500.9.clq");
		// Graphe graphe = new Graphe("C1000.9.clq");
		Graphe graphe = new Graphe("C2000.9.clq");
		// Graphe graphe = new Graphe("C4000.5.clq");
		// Graphe graphe = new Graphe("p_hat1500-3.clq");
		long start2 = System.currentTimeMillis();
		Affichage.afficher("durée chargement fichier : " + ((float) (start2 - start)) / 1000 + "s");

		Affichage.afficher("Clique 2");
		CliqueAbstraite clique = new Clique_2_2(graphe);
		// start2=start2;
		clique.rechercheClique();
		long start3 = System.currentTimeMillis();
		Affichage.afficher("Plus grande taille de clique : " + clique.getMaximumSizeClique());
		Affichage.afficher("durée calcul 2 : " + ((float) (start3 - start2)) / 1000 + "s");

		Affichage.afficher("Clique 3");
		clique = new Clique_3(graphe);
		start2 = start3;
		//clique.rechercheClique();
		start3 = System.currentTimeMillis();
		Affichage.afficher("Plus grande taille de clique : " + clique.getMaximumSizeClique());
		Affichage.afficher("durée calcul 3 : " + ((float) (start3 - start2)) / 1000 + "s");
		CliqueAbstraite clique2 = new Clique_3(graphe);

		// clique2.rechercheClique();

		// Affichage.afficher("Plus grande taille de clique2 : "+
		// clique2.getMaximumSizeClique());
		long startFin = System.currentTimeMillis();
		Affichage.afficher("durée = " + ((float) (startFin - start)) / 1000 + "s");
	}
}
