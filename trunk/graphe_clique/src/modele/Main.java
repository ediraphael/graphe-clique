package modele;

public class Main
{
	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();
		Graphe graphe = new Graphe("hier.txt");
		//Graphe graphe = new Graphe("C2000.9.clq");
		
		long start2 = System.currentTimeMillis();

		//CliqueAbstraite clique = new Clique_3(graphe);
		CliqueAbstraite clique = new Clique_2(graphe);
		clique.rechercheClique();
		long dure = System.currentTimeMillis() - start;
		System.out.println("durée chargement fichier : "+((float)(start2-start))/1000+"s");
		System.out.println("durée calcul : "+((float)(dure))/1000+"s");
		System.out.println("durée = "+((float)dure+(start2-start))/1000+"s");
	}
}
