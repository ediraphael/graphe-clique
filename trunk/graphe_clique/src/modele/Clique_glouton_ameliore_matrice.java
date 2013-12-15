package modele;

import java.util.ArrayList;

public class Clique_glouton_ameliore_matrice extends CliqueAbstraite
{
	public int maxSizeClique = Integer.MIN_VALUE;
	private int cptDivergence = 0;

	public Clique_glouton_ameliore_matrice(Graphe grapheRecherche)
	{
		super(grapheRecherche, new ArrayList<ArrayList<Integer>>());
	}

	public Graphe rechercheClique()
	{
		Affichage.afficher(("Début recherche sur le graphe : " + this.getGrapheRecherche().getNom()));
		int tailleMax = 0;
		int noeudRet = -1;
		// On recherche le noeud qui a la plus grande liste d'adjacence
		for (int i = 0; i < this.getGrapheRecherche().getGraphe().length; i++)
		{
			int tailleTemp = this.getGrapheRecherche().getNbNoeudAdjacent(this.getGrapheRecherche().getGraphe(), i);
			if (tailleTemp > tailleMax)
			{
				tailleMax = tailleTemp;
				noeudRet = i;
			}
		}
		// On initialise une clique
		ArrayList<Integer> clique = new ArrayList<Integer>();
		clique.add(noeudRet + 1);
		// On copie notre graphe
		int[][] grapheClone = new int[this.getGrapheRecherche().getGraphe().length][this.getGrapheRecherche().getGraphe().length];
		for (int j = 0; j < grapheClone.length; j++)
		{
			for (int j2 = 0; j2 < grapheClone.length; j2++)
			{
				grapheClone[j][j2] = this.getGrapheRecherche().getGraphe()[j][j2];
			}
		}
		// On lance notre recherche
		this.recursiveClique(grapheClone, clique, noeudRet);
		Affichage.afficher(("Fin de la recherche de clique"));

		return null;
	}

	public void recursiveClique(int[][] graphe, ArrayList<Integer> clique, int dernierNoeud)
	{
		// On sauvegarde la plus grande clique trouvé
		if (clique.size() > maxSizeClique)
		{
			maxSizeClique = clique.size();
		}
		
		int nbNoeudAdjacent = -1;
		//On initialise une liste de noeud a retenir
		ArrayList<Integer> noeudsRetenu = new ArrayList<Integer>();
		//On récupère la liste des noeuds qui on une liste de noeud adjacent au reste de la clique plus grande
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
					noeudsRetenu = new ArrayList<Integer>();
					noeudsRetenu.add(i);
				} else
				{
					noeudsRetenu.add(i);
				}
			}
		}
		//Si le nombre ne noeud adjacent de dépasse pas la taille de clique max trouvé, alors on ne fait pas de recherche
		if ((clique.size() + nbNoeudAdjacent) >= maxSizeClique && noeudsRetenu.size() > 0)
		{
			//On limite notre taux de divergence a 20
			if (cptDivergence < 20 && noeudsRetenu.size() > 1)
			{
				if ((cptDivergence + noeudsRetenu.size()) > 20)
				{
					ArrayList<Integer> newNoeudsRetenu = new ArrayList<Integer>();
					newNoeudsRetenu.addAll(noeudsRetenu.subList(0, 20 - cptDivergence));
					noeudsRetenu = newNoeudsRetenu;
				}
				cptDivergence += noeudsRetenu.size();
			} else
			{
				Integer noeudRetenu = noeudsRetenu.get(0);
				noeudsRetenu = new ArrayList<Integer>();
				noeudsRetenu.add(noeudRetenu);
			}
			
			//On parcours les noeuds retenu
			for (Integer noeudRetenu : noeudsRetenu)
			{
				if (noeudRetenu != -1 && nbNoeudAdjacent >= 0)
				{
					//On initialise une clique a envoyer
					ArrayList<Integer> cliqueEnvoie = new ArrayList<Integer>(clique);
					cliqueEnvoie.add(noeudRetenu + 1);
					// System.out.println(cliqueEnvoie);
					// System.out.println(cliqueEnvoie.size());
					boolean dejaFait = false;
					//On vérifie que on a pas déja fait une clique similaire
					for (ArrayList<Integer> cliqueL : this.getListeClique())
					{
						if (cliqueL.size() == cliqueEnvoie.size() && cliqueEnvoie.containsAll(cliqueL))
						{
							dejaFait = true;
						}
					}
					//Si ce n'est pas le cas
					if (!dejaFait)
					{
						//Alors on ajoute la clique
						this.getListeClique().add(cliqueEnvoie);
						//On clone le graphe
						int[][] grapheClone = this.getGrapheRecherche().getGraphe().clone();
						this.cpt++;
						//On continue la recherche de clique
						recursiveClique(grapheClone, cliqueEnvoie, noeudRetenu);
					}
				} else
				{
					ArrayList<Integer> cliqueEnvoie = new ArrayList<Integer>(clique);
					cliqueEnvoie.add(noeudRetenu + 1);
					this.getListeClique().add(cliqueEnvoie);
				}
			}
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
