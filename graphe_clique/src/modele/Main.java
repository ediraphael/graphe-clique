package modele;

public class Main
{
	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();

		// Graphe graphe = new Graphe("C2000.9.clq");
		// Graphe graphe = new Graphe("fichier.txt");

		Graphe graphe = new Graphe("fichier.txt");
		// Graphe graphe = new Graphe("C2000.9.clq");

		long start2 = System.currentTimeMillis();
		//CliqueAbstraite clique = new Clique_3(graphe);
		 CliqueAbstraite clique = new Clique_2(graphe);
		clique.rechercheClique();
		long start3 = System.currentTimeMillis();
		// CliqueAbstraite clique2 = new Clique_3(graphe);
		// clique2.rechercheClique();
		long start4 = System.currentTimeMillis();
		Affichage.afficher("durée chargement fichier : " + ((float) (start2 - start)) / 1000 + "s");
		Affichage.afficher("durée calcul clique_2 : " + ((float) (start3 - start2)) / 1000 + "s");
		Affichage.afficher("durée calcul clique_3 : " + ((float) (start4 - start3)) / 1000 + "s");
		Affichage.afficher("durée = " + ((float) (start4 - start)) / 1000 + "s");
	}
}
