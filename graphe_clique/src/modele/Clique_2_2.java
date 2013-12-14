package modele;

import java.util.ArrayList;

public class Clique_2_2 extends CliqueAbstraite
{
	public Clique_2_2(Graphe grapheRecherche)
	{
		super(grapheRecherche, new ArrayList<ArrayList<Integer>>());
	}

	public Graphe rechercheClique()
	{
		Affichage.afficher(("Début recherche sur le graphe : " + this.getGrapheRecherche().getNom()));
		ArrayList<ThreadRechercheMatrice> threads = new ArrayList<ThreadRechercheMatrice>();
		for (int i = 0; i < this.getGrapheRecherche().getGraphe().length; i++)
		{
			while (threads.size() > 4)
			{
				try
				{
					Thread.sleep(20);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				ArrayList<ThreadRechercheMatrice> threadsCopy = (ArrayList<ThreadRechercheMatrice>) threads.clone();
				for (ThreadRechercheMatrice threadRecherche : threadsCopy)
				{
					if (!threadRecherche.isAlive())
					{
						threads.remove(threadRecherche);
					}
				}
			}
			ArrayList<Integer> clique = new ArrayList<Integer>();
			clique.add(i + 1);
			int[][] grapheClone = new int[this.getGrapheRecherche().getGraphe().length][this.getGrapheRecherche().getGraphe().length];
			for (int j = 0; j < grapheClone.length; j++)
			{
				for (int j2 = 0; j2 < grapheClone.length; j2++)
				{
					grapheClone[j][j2] = this.getGrapheRecherche().getGraphe()[j][j2];
				}
			}
			// this.recursiveClique(grapheClone, clique, i);
			ThreadRechercheMatrice thread = new ThreadRechercheMatrice(this, grapheClone, clique, i);
			threads.add(thread);
			thread.start();
			this.cpt++;
		}
		boolean enAttente = true;
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
					noeudRetenu = i;
				}
			}
		}
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
