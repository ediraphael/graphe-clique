package modele;

import java.util.ArrayList;

public class Clique_semi_glouton_matrice extends CliqueAbstraite
{
	public Clique_semi_glouton_matrice(Graphe grapheRecherche)
	{
		super(grapheRecherche, new ArrayList<ArrayList<Integer>>());
	}

	public Graphe rechercheClique()
	{
		Affichage.afficher(("Début recherche sur le graphe : " + this.getGrapheRecherche().getNom()));
		ArrayList<ThreadRechercheMatrice> threads = new ArrayList<ThreadRechercheMatrice>();
		//On parcours tout les noeud du graphe
		for (int i = 0; i < this.getGrapheRecherche().getGraphe().length; i++)
		{
			//On vérifie le nombre de thread créé
			while (threads.size() > 4)
			{
				try
				{
					Thread.sleep(20);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				//On vérifie si il y en a certain qui on fini
				ArrayList<ThreadRechercheMatrice> threadsCopy = (ArrayList<ThreadRechercheMatrice>) threads.clone();
				for (ThreadRechercheMatrice threadRecherche : threadsCopy)
				{
					if (!threadRecherche.isAlive())
					{
						//Si oui, alors on le supprime
						threads.remove(threadRecherche);
					}
				}
			}
			//On initialise une clique avec le noeud courant
			ArrayList<Integer> clique = new ArrayList<Integer>();
			clique.add(i + 1);
			//On clone notre matrice
			int[][] grapheClone = new int[this.getGrapheRecherche().getGraphe().length][this.getGrapheRecherche().getGraphe().length];
			for (int j = 0; j < grapheClone.length; j++)
			{
				for (int j2 = 0; j2 < grapheClone.length; j2++)
				{
					grapheClone[j][j2] = this.getGrapheRecherche().getGraphe()[j][j2];
				}
			}
			//On initiliase notre thread de recherche
			ThreadRechercheMatrice thread = new ThreadRechercheMatrice(this, grapheClone, clique, i);
			threads.add(thread);
			//On lance notre recherche
			thread.start();
			this.cpt++;
		}
		boolean enAttente = true;
		//On attent que tout nos thread soit fini
		while (enAttente)
		{
			enAttente = false;
			for (ThreadRechercheMatrice threadRechercheMatrice : threads)
			{
				if (threadRechercheMatrice.isAlive())
				{
					enAttente = true;
				}
			}
		}
		Affichage.afficher(("Fin de la recherche de clique"));

		return null;
	}

	public void recursiveClique(int[][] graphe, ArrayList<Integer> clique, int dernierNoeud)
	{
		int noeudRetenu = -1;
		int nbNoeudAdjacent = -1;
		//On parcours tout les noeuds du graphe
		for (int i = 0; i < graphe.length; i++)
		{
			//Si le noeud courant fait partie de la liste d'adjacence du dernier noeud parcouru
			if (graphe[dernierNoeud][i] == 1)
			{
				//On retire tout les noeuds qui ne peuvent plus etre atteint
				for (int j = 0; j < graphe.length; j++)
				{
					if (graphe[dernierNoeud][j] == 0)
					{
						graphe[i][j] = 0;
					}
				}
				//On récupère le nombre de noeud adjacent au noeud courant
				int nbNoeudTemp = this.getGrapheRecherche().getNbNoeudAdjacent(graphe, i);
				//On vérifie si elle est plus grande que le noeud déjà retenu
				if (nbNoeudTemp > nbNoeudAdjacent && nbNoeudTemp >= 0)
				{
					//Si c'est le cas on le garde
					nbNoeudAdjacent = nbNoeudTemp;
					noeudRetenu = i;
				}
			}
		}
		//Si on a un noeud retenu, alors on continu notre recherche de clique
		if (noeudRetenu != -1 && nbNoeudAdjacent >= 0)
		{
			clique.add(noeudRetenu + 1);
			// System.out.println(clique);
			// System.out.println(clique.size());
			recursiveClique(graphe, clique, noeudRetenu);
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
