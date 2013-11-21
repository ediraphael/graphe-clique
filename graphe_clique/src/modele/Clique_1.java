package modele;

import java.util.Vector;

public class Clique_1 extends CliqueAbstraite
{
	private Graphe grapheRecherche;
	private static Vector<Vector<Noeud>> listeClique;
	static int cpt = 0;

	public Clique_1(Graphe grapheRecherche)
	{
		this.grapheRecherche = grapheRecherche;
		this.listeClique = new Vector<Vector<Noeud>>();
	}

	public Graphe getGrapheRecherche()
	{
		return grapheRecherche;
	}

	public void setGrapheRecherche(Graphe grapheRecherche)
	{
		this.grapheRecherche = grapheRecherche;
	}

	public Graphe rechercheClique()
	{
		//System.out.println("Début recherche sur le graphe : " + this.grapheRecherche.getNom());
		Affichage.afficher("Début recherche sur le graphe : " + this.grapheRecherche.getNom());
		Vector<ThreadRecherche> threads = new Vector<ThreadRecherche>();
		for (Noeud noeud : this.grapheRecherche.getListeNoeud())
		{
			Vector<Noeud> clique = new Vector<Noeud>();
			clique.add(noeud);
			ThreadRecherche thread = new ThreadRecherche(this,clique,noeud.getListeAdjacence());
			threads.add(thread);
			thread.start();
			//this.recursiveClique(clique, noeud.getListeAdjacence());
			//break;
		}
		//System.out.println("fin");
		Affichage.afficher("Fin de la recherche de clique");
		return null;
	}

	public Vector<Noeud> recursiveClique(Vector<Noeud> clique, Vector<Noeud> listeNoeud)
	{
		// On clone la liste d'adjacence et la clique
		Vector<Noeud> cliqueRetour = new Vector<Noeud>(clique);
		Vector<Noeud> liste = new Vector<Noeud>(listeNoeud);
		// On retire tous les noeud de la clique dans la liste pour ne pas
		// revenir dessus
		liste.removeAll(clique);

		// Pour chaque noeud de la liste
		for (Noeud noeud : liste)
		{
			// On récupère une copie de sa liste d'adjacence
			Vector<Noeud> listeAdjNoeud = new Vector<Noeud>(noeud.getListeAdjacence());
			// On ne garde que les noeud qui sont encore atteignable;
			listeAdjNoeud.retainAll(liste);

			// On reconstruit une clique
			Vector<Noeud> cliqueEnvoi = new Vector<Noeud>(clique);
			// On y ajoute le noeud courant
			cliqueEnvoi.add(noeud);

			// On recherche si on a déja vue cette clique
			boolean dejaFait = false;
			// Pour chaque clique
			Vector<Vector<Noeud>> lis = new Vector<Vector<Noeud>>(listeClique);
			for (Vector<Noeud> cli : lis)
			{
				if (cli.containsAll(cliqueEnvoi))
				{
					dejaFait = true;
				}
			}
			if (dejaFait)
			{

			} else
			{
				System.out.println(cliqueEnvoi);
				System.out.println(listeClique.size());
				listeClique.add(cliqueEnvoi);
				recursiveClique(cliqueEnvoi, listeAdjNoeud);
			}

			cpt++;

		}
		return cliqueRetour;
	}
}
