package modele;

import java.util.ArrayList;

public class Clique_glouton_ameliore_list extends CliqueAbstraite
{
	public int maxSizeClique = Integer.MIN_VALUE;

	public Clique_glouton_ameliore_list(Graphe grapheRecherche)
	{
		super(grapheRecherche, new ArrayList<ArrayList<Integer>>());
	}

	public Graphe rechercheClique()
	{
		Affichage.afficher(("Début recherche sur le graphe : " + this.getGrapheRecherche().getNom()));
		int tailleMax = 0;
		Integer noeudRet = null;
		for (Integer noeud : this.getGrapheRecherche().getListeNoeud().keySet())
		{
			if (this.getGrapheRecherche().getListeNoeud().get(noeud).size() > tailleMax)
			{
				tailleMax = this.getGrapheRecherche().getListeNoeud().get(noeud).size();
				noeudRet = noeud;
			}
		}

		ArrayList<Integer> clique = new ArrayList<Integer>();
		clique.add(noeudRet);
		this.recursiveClique(clique, this.getGrapheRecherche().getListeNoeud().get(noeudRet));
		Affichage.afficher(("Fin de la recherche de clique"));
		return null;
	}

	public void recursiveClique(ArrayList<Integer> clique, ArrayList<Integer> listeNoeud)
	{
		if (clique.size() > maxSizeClique)
		{
			maxSizeClique = clique.size();
		}
		// On clone la liste d'adjacence et la clique
		ArrayList<Integer> liste = new ArrayList<Integer>(listeNoeud);
		// On retire tous les noeud de la clique dans la liste pour ne pas
		// revenir dessus
		liste.removeAll(clique);

		// Integer noeudRetenu = null;
		ArrayList<Integer> noeudsRetenu = new ArrayList<Integer>();
		int nbNoeudAdjacent = 0;
		// Pour chaque noeud de la liste
		for (Integer noeud : liste)
		{
			// On récupère une copie de sa liste d'adjacence
			ArrayList<Integer> listeAdjNoeud = new ArrayList<Integer>(this.getGrapheRecherche().getListeNoeud().get(noeud));
			// On ne garde que les noeud qui sont encore atteignable;
			listeAdjNoeud.retainAll(liste);
			if (noeudsRetenu.size() == 0 || nbNoeudAdjacent < listeAdjNoeud.size())
			{
				noeudsRetenu = new ArrayList<Integer>();
				noeudsRetenu.add(noeud);
				nbNoeudAdjacent = listeAdjNoeud.size();
			} else if (nbNoeudAdjacent == listeAdjNoeud.size())
			{
				noeudsRetenu.add(noeud);
			}
		}
		// On récupère une copie de la liste d'adjacence
		for (Integer noeudRetenu : noeudsRetenu)
		{
			ArrayList<Integer> listeAdjNoeud = new ArrayList<Integer>(this.getGrapheRecherche().getListeNoeud().get(noeudRetenu));
			// On ne garde que les noeud qui sont encore atteignable;
			listeAdjNoeud.retainAll(liste);
			// On reconstruit une clique
			ArrayList<Integer> cliqueEnvoi = new ArrayList<Integer>(clique);
			// On y ajoute le noeud courant
			cliqueEnvoi.add(noeudRetenu);

			// On recherche si on a déja vue cette clique
			boolean dejaFait = false;
			// Pour chaque clique
			ArrayList<ArrayList<Integer>> lis = new ArrayList<ArrayList<Integer>>(this.getListeClique());
			for (ArrayList<Integer> cli : lis)
			{
				if (cli.size() >= cliqueEnvoi.size() && cliqueEnvoi.containsAll(cli))
				{
					dejaFait = true;
				}
			}
			// On vérifie si on peut faire une clique plus grande que ceux que
			// on a
			// déja trouvé
			if (!((cliqueEnvoi.size() + listeAdjNoeud.size()) <= maxSizeClique))
			{
				if (!dejaFait)
				{
					System.out.println(listeAdjNoeud);
					System.out.println(cliqueEnvoi);
					// Affichage.afficher((cliqueEnvoi));
					System.out.println(this.getListeClique().size());
					// Affichage.afficher((this.getListeClique().size()));
					System.out.println(maxSizeClique);
					this.getListeClique().add(cliqueEnvoi);
					recursiveClique(cliqueEnvoi, listeAdjNoeud);
				}
			}
		}
	}

	@Override
	public void recursiveClique(int[][] graphe, ArrayList<Integer> clique, int dernierNoeud)
	{
		// TODO Auto-generated method stub
		
	}
}
