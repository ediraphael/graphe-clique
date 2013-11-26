package modele;

import java.util.ArrayList;

public class Clique_5 extends CliqueAbstraite
{
	public int maxSizeClique = Integer.MIN_VALUE;
	public int cptTemp = 0;
	public int nbNoeudAParcourir = 1;

	public Clique_5(Graphe grapheRecherche)
	{
		super(grapheRecherche, new ArrayList<ArrayList<Integer>>());
	}

	public Graphe rechercheClique()
	{
		// System.out.println("Début recherche sur le graphe : " +
		// this.getGrapheRecherche().getNom());
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
		Affichage.afficher("Liste des noeuds à retenu:" + noeudsRet);
		System.out.println("Nombre de noeud retenu :" + noeudsRet.size());
		nbNoeudAParcourir = noeudsRet.size();
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
			Affichage.afficher("Recherche à partir du noeud:" + noeudRet);
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
		if (!((clique.size() + liste.size()) <= maxSizeClique))
		{
			Integer noeudRetenu = null;
			int nbNoeudAdjacent = 0;
			// Pour chaque noeud de la liste
			for (Integer noeud : liste)
			{
				// On récupère une copie de sa liste d'adjacence
				ArrayList<Integer> listeAdjNoeud = new ArrayList<Integer>(this.getGrapheRecherche().getListeNoeud().get(noeud));
				// On ne garde que les noeud qui sont encore atteignable;
				listeAdjNoeud.retainAll(liste);
				if (noeudRetenu == null || nbNoeudAdjacent < listeAdjNoeud.size())
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

				System.out.println(cliqueEnvoi);
				// Affichage.afficher((cliqueEnvoi));
				System.out.println(this.getListeClique().size());
				System.out.println(this.maxSizeClique);
				// Affichage.afficher((this.getListeClique().size()));
				this.getListeClique().add(cliqueEnvoi);
				recursiveClique(cliqueEnvoi, listeAdjNoeud);
			}else
			{
				cptTemp++;
				cpt = (int) ((((float) cptTemp) / ((float) nbNoeudAParcourir)) * 1000);
				System.out.println(((((float) cptTemp) / ((float) nbNoeudAParcourir)) * 1000));
				System.out.println("cptTemp=" + cptTemp);
				System.out.println("nbNoeud=" + nbNoeudAParcourir);
				System.out.println("cpt=" + cpt);
			}
		}
	}

	@Override
	public void recursiveClique(int[][] graphe, ArrayList<Integer> clique, int dernierNoeud)
	{
		// TODO Auto-generated method stub
		
	}
}
