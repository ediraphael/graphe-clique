package modele;

import java.util.ArrayList;

public class Clique_semi_glouton_list extends CliqueAbstraite
{
	public int maxSizeClique = Integer.MIN_VALUE;

	public Clique_semi_glouton_list(Graphe grapheRecherche)
	{
		super(grapheRecherche,new ArrayList<ArrayList<Integer>>());
	}

	@SuppressWarnings("unchecked")
	public Graphe rechercheClique()
	{
		// System.out.println("Début recherche sur le graphe : " +
		// this.grapheRecherche.getNom());
		Affichage.afficher(("Début recherche sur le graphe : " + this.getGrapheRecherche().getNom()));
		ArrayList<ThreadRecherche> threads = new ArrayList<ThreadRecherche>();
		for (Integer noeud : this.getGrapheRecherche().getListeNoeud().keySet())
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
			clique.add(noeud);
			// this.recursiveClique(clique,
			// this.getGrapheRecherche().getListeNoeud().get(noeud).getListeAdjacence());
			ThreadRecherche thread = new ThreadRecherche(this, clique, this.getGrapheRecherche().getListeNoeud().get(noeud));
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

		int tailleMax = 0;
		ArrayList<Integer> listeRet = new ArrayList<Integer>();
		for (ArrayList<Integer> liste : this.getListeClique())
		{
			if (liste.size() > tailleMax)
			{
				listeRet = liste;
				tailleMax = liste.size();
			}
		}
		// System.out.println(listeRet);
		//Affichage.afficher((listeRet));
		// System.out.println(listeRet.size());
		//Affichage.afficher(listeRet.size());
		// System.out.println("fin");
		Affichage.afficher("Fin de la recherche de clique");
		return null;
	}

	public void recursiveClique(ArrayList<Integer> clique, ArrayList<Integer> listeNoeud)
	{
		if (clique.size() > maxSizeClique)
		{
			maxSizeClique = clique.size();
		}
		// On clone la liste d'adjacence
		ArrayList<Integer> liste = new ArrayList<Integer>(listeNoeud);
		// On retire tous les noeud de la clique dans la liste pour ne pas
		// revenir dessus
		liste.removeAll(clique);
		// On vérifie si on peut faire une clique plus grande que ceux que on a
		// déja trouvé
		if (!((clique.size() + liste.size()) <= maxSizeClique))
		{
			Integer noeudRetenu = null;
			int nbNoeudAdjacent = -1;
			// Pour chaque noeud de la liste
			for (Integer noeud : liste)
			{
				// On récupère une copie de la liste d'adjacence
				ArrayList<Integer> listeAdjNoeud = new ArrayList<Integer>(this.getGrapheRecherche().getListeNoeud().get(noeud));
				// On ne garde que les noeud qui sont encore atteignable;
				listeAdjNoeud.retainAll(liste);
				if (nbNoeudAdjacent < listeAdjNoeud.size())
				{
					noeudRetenu = noeud;
					nbNoeudAdjacent = listeAdjNoeud.size();
				}
			}

			// On récupère une copie de la liste d'adjacence
			if (noeudRetenu != null)
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
				if (!dejaFait)
				{
					System.out.println(cliqueEnvoi);
					//Affichage.afficher(cliqueEnvoi);
					System.out.println(this.getListeClique().size());
					//Affichage.afficher(this.getListeClique().size());
					System.out.println(maxSizeClique);
					//cpt=this.getListeClique().size();
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
