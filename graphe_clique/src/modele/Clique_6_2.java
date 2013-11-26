package modele;

import java.util.ArrayList;

public class Clique_6_2 extends CliqueAbstraite
{
	public int maxSizeClique = Integer.MIN_VALUE;

	public Clique_6_2(Graphe grapheRecherche)
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
			int tailleTemp = this.getGrapheRecherche().getNbNoeudAdjacent(this.getGrapheRecherche().getGraphe(), i);
			if (tailleTemp > tailleMax)
			{
				tailleMax = tailleTemp;
				noeudRet = i;
			}
		}
		ArrayList<Integer> clique = new ArrayList<Integer>();
		clique.add(noeudRet + 1);
		int[][] grapheClone = new int[this.getGrapheRecherche().getGraphe().length][this.getGrapheRecherche().getGraphe().length];
		for (int j = 0; j < grapheClone.length; j++)
		{
			for (int j2 = 0; j2 < grapheClone.length; j2++)
			{
				grapheClone[j][j2] = this.getGrapheRecherche().getGraphe()[j][j2];
			}
		}
		this.recursiveClique(grapheClone, clique, noeudRet);
		Affichage.afficher(("Fin de la recherche de clique"));

		return null;
	}

	public void recursiveClique(int[][] graphe, ArrayList<Integer> clique, int dernierNoeud)
	{
		if (clique.size() > maxSizeClique)
		{
			maxSizeClique = clique.size();
		}
		// int noeudRetenu = -1;
		int nbNoeudAdjacent = -1;
		ArrayList<Integer> noeudsRetenu = new ArrayList<Integer>();
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
				int nbNoeudTemp = this.getGrapheRecherche().getNbNoeudAdjacent(graphe, i);
				if (nbNoeudTemp > nbNoeudAdjacent && nbNoeudTemp >= 0)
				{
					nbNoeudAdjacent = nbNoeudTemp;
					// noeudRetenu = i;
					noeudsRetenu = new ArrayList<Integer>();
					noeudsRetenu.add(i);
				} else
				{
					noeudsRetenu.add(i);
				}
			}
		}
		if ((clique.size() + nbNoeudAdjacent) > maxSizeClique)
		{
			for (Integer noeudRetenu : noeudsRetenu)
			{
				if (noeudRetenu != -1 && nbNoeudAdjacent >= 0)
				{
					ArrayList<Integer> cliqueEnvoie = new ArrayList<Integer>(clique);
					cliqueEnvoie.add(noeudRetenu + 1);
					System.out.println(cliqueEnvoie);
					System.out.println(cliqueEnvoie.size());
					boolean dejaFait = false;
					for (ArrayList<Integer> cliqueL : this.getListeClique())
					{
						if (cliqueL.size() == cliqueEnvoie.size() && cliqueEnvoie.containsAll(cliqueL))
						{
							dejaFait = true;
						}
					}
					if (!dejaFait)
					{
						this.getListeClique().add(cliqueEnvoie);

						int[][] grapheClone = new int[graphe.length][graphe.length];
						for (int j = 0; j < grapheClone.length; j++)
						{
							for (int j2 = 0; j2 < grapheClone.length; j2++)
							{
								grapheClone[j][j2] = graphe[j][j2];
							}
						}
						this.cpt++;
						recursiveClique(grapheClone, cliqueEnvoie, noeudRetenu);
					}
				} else
				{
					this.getListeClique().add(clique);
				}
			}
		}
	}

	@Override
	public void recursiveClique(ArrayList<Integer> clique, ArrayList<Integer> listeNoeud)
	{
		// TODO Auto-generated method stub

	}
}
