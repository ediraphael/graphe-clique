package modele;

public class Main
{
	public static void main(String[] args)
	{
		Graphe graphe = new Graphe("fichier.txt");
		Clique clique = new Clique(graphe);
		clique.rechercheClique();
	}
}
