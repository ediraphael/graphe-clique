package modele;

import java.util.ArrayList;

public class Clique_3_2 extends CliqueAbstraite
{
	public Clique_3_2(Graphe grapheRecherche)
	{
		super(grapheRecherche, new ArrayList<ArrayList<Integer>>());
	}

	public Graphe rechercheClique()
	{
		Affichage.afficher(("Début recherche sur le graphe : " + this.getGrapheRecherche().getNom()));
		int tailleMax = 0;
		int noeudRet = -1;
		for (int i = 0; i < this.getGrapheRecherche().getGraphe().length; i++)
		{
			int tailleTemp = this.getGrapheRecherche().getNbNoeudAdjacent(this.getGrapheRecherche().getGraphe(),i);
			if (tailleTemp > tailleMax)
			{
				tailleMax = tailleTemp;
				noeudRet = i;
			}
		}

		ArrayList<Integer> clique = new ArrayList<Integer>();
		clique.add(noeudRet+1);
		this.recursiveClique(this.getGrapheRecherche().getGraphe(),clique, noeudRet);
		Affichage.afficher(("Fin de la recherche de clique"));
		return null;
	}

	public void recursiveClique(int[][] graphe,ArrayList<Integer> clique, int dernierNoeud)
	{
		int noeudRetenu = -1;
		int nbNoeudAdjacent = -1;
		// Pour chaque noeud de la liste
		for (int i = 0; i < graphe.length; i++)
		{
			if (graphe[dernierNoeud][i] == 1)
			{
				for (int j = 0; j < graphe.length; j++)
				{
					if (graphe[dernierNoeud][j] == 0)
					{
						graphe[i][j] = 0;
					}
				}
				int nbNoeudTemp = this.getGrapheRecherche().getNbNoeudAdjacent(graphe,i);
				if (nbNoeudTemp > nbNoeudAdjacent && nbNoeudTemp >= 0)
				{
					
					nbNoeudAdjacent = nbNoeudTemp;
					noeudRetenu = i;
				}
			}
		}
		if (noeudRetenu != -1 && nbNoeudAdjacent >= 0)
		{
			clique.add(noeudRetenu+1);
			System.out.println(clique);
			System.out.println(clique.size());
			this.cpt++;
			recursiveClique(graphe,clique, noeudRetenu);
		} else
		{
			this.getListeClique().add(clique);
		}
	}

	@Override
	public void recursiveClique(ArrayList<Integer> clique, ArrayList<Integer> listeNoeud)
	{
		// TODO Auto-generated method stub

	}
}
