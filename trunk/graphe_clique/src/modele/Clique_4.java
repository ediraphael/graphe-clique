package modele;

import java.util.ArrayList;

public class Clique_4 extends CliqueAbstraite
{
	public int maxSizeClique = Integer.MIN_VALUE;

	public Clique_4(Graphe grapheRecherche)
	{
		super(grapheRecherche, new ArrayList<ArrayList<Integer>>());
	}

	public Graphe rechercheClique()
	{
		// System.out.println("Début recherche sur le graphe : " +
		// this.grapheRecherche.getNom());
		Affichage.afficher(("Début recherche sur le graphe : " + this.getGrapheRecherche().getNom()));
		int tailleMax = 0;
		ArrayList<Integer> noeudsRet = new ArrayList<Integer>();
		for (Integer noeud : this.getGrapheRecherche().getListeNoeud().keySet())
		{
			if (this.getGrapheRecherche().getListeNoeud().get(noeud).size() > tailleMax)
			{
				noeudsRet = new ArrayList<Integer>();
				tailleMax = this.getGrapheRecherche().getListeNoeud().get(noeud).size();
				noeudsRet.add(noeud);
			} else if (this.getGrapheRecherche().getListeNoeud().get(noeud).size() == tailleMax)
			{
				noeudsRet.add(noeud);
			}
		}
		System.out.println("Liste de noeud retenu :" + noeudsRet);
		ArrayList<ThreadRecherche> threads = new ArrayList<ThreadRecherche>();
		for (Integer noeudRet : noeudsRet)
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
				ArrayList<ThreadRecherche> threadsCopy = (ArrayList<ThreadRecherche>) threads.clone();
				for (ThreadRecherche threadRecherche : threadsCopy)
				{
					if (!threadRecherche.isAlive())
					{
						threads.remove(threadRecherche);
					}
				}
			}
			ArrayList<Integer> clique = new ArrayList<Integer>();
			clique.add(noeudRet);
			// this.recursiveClique(clique,
			// this.getGrapheRecherche().getListeNoeud().get(noeudRet));
			ThreadRecherche thread = new ThreadRecherche(this, clique, this.getGrapheRecherche().getListeNoeud().get(noeudRet));
			threads.add(thread);
			thread.start();
			cpt++;
		}
		boolean running = true;
		while (running)
		{
			running = false;
			for (ThreadRecherche threadRecherche : threads)
			{
				if (threadRecherche.isAlive())
				{
					running = true;
				}
			}
		}
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
