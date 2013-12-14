package modele;

public class Main
{
	public static void main(String[] args)
	{
		// String nomGraphe = "C2000.9.clq";
		String graphes[] = { "C2000.9.clq", "C1000.9.clq", "C500.9.clq", "C125.9.clq" };

		/*for (String nomGraphe : graphes)
		{
			long start = System.currentTimeMillis();
			Graphe graphe = new Graphe(nomGraphe);
			long start2 = System.currentTimeMillis();
			System.out.println("durée chargement fichier noeuds: " + ((float) (start2 - start)) / 1000 + "s");

			graphe = new Graphe(nomGraphe);
			start2 = System.currentTimeMillis();
			System.out.print("Semi-glouton : ");
			CliqueAbstraite clique = new Clique_2_2(graphe);
			clique.rechercheClique();
			long start3 = System.currentTimeMillis();
			System.out.print(" taille : " + clique.getMaximumSizeClique());
			//System.out.print(clique.getMaximumClique());
			System.out.println(" durée calcul : " + ((float) (start3 - start2)) / 1000 + "s");

			graphe = new Graphe(nomGraphe);
			start2 = System.currentTimeMillis();
			System.out.print("Glouton amélioré : ");
			clique = new Clique_6_2(graphe);
			clique.rechercheClique();
			start3 = System.currentTimeMillis();
			System.out.print(" taille : " + clique.getMaximumSizeClique());
			//System.out.println(clique.getMaximumClique());
			System.out.println(" durée calcul : " + ((float) (start3 - start2)) / 1000 + "s");

			graphe = new Graphe(nomGraphe);
			start2 = System.currentTimeMillis();
			System.out.print("Glouton");
			clique = new Clique_3_2(graphe);
			clique.rechercheClique();
			start3 = System.currentTimeMillis();
			System.out.print(" taille : " + clique.getMaximumSizeClique());
			//System.out.print(clique.getMaximumClique());
			System.out.println(" durée calcul : " + ((float) (start3 - start2)) / 1000 + "s");
		}*/
		
		Graphe graphe = new Graphe("C2000.9.clq",true);
		long start2 = System.currentTimeMillis();
		System.out.print("Glouton");
		CliqueAbstraite clique = new Clique_2_2(graphe);
		clique.rechercheClique();
		long start3 = System.currentTimeMillis();
		System.out.print(" taille : " + clique.getMaximumSizeClique());
		//System.out.print(clique.getMaximumClique());
		System.out.println(" durée calcul : " + ((float) ( start3 - start2)) / 1000 + "s");
		// clique2.rechercheClique();

		// System.out.println("Plus grande taille de clique2 : "+
		// clique2.getMaximumSizeClique());
		// long startFin = System.currentTimeMillis();
		// System.out.println("durée = " + ((float) (startFin - start)) / 1000 +
		// "s");
	}
}
