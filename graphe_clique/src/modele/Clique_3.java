package modele;

import java.util.Vector;

public class Clique_3 extends CliqueAbstraite
{
	private Graphe grapheRecherche;
	private static Vector<Vector<Noeud>> listeClique;
	static int cpt = 0;

	public Clique_3(Graphe grapheRecherche)
	{
		this.grapheRecherche = grapheRecherche;
		listeClique = new Vector<Vector<Noeud>>();
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
		Affichage.afficher(("Début recherche sur le graphe : " + this.grapheRecherche.getNom()));
		int tailleMax=0;
		Noeud noeudRet=null;
		for (Noeud noeud : this.grapheRecherche.getListeNoeud())
		{
			if(noeud.getListeAdjacence().size()>tailleMax)
			{
				tailleMax=noeud.getListeAdjacence().size();
				noeudRet=noeud;
			}
		}
		
		Vector<Noeud> clique = new Vector<Noeud>();
		clique.add(noeudRet);
		this.recursiveClique(clique, noeudRet.getListeAdjacence());
		tailleMax = 0;
		Vector<Noeud> listeRet = new Vector<Noeud>();
		for (Vector<Noeud> liste : listeClique)
		{
			if (liste.size() > tailleMax)
			{
				listeRet = liste;
				tailleMax = liste.size();
			}
		}
		//System.out.println(listeRet);
		Affichage.afficher((listeRet));
		//System.out.println(listeRet.size());
		Affichage.afficher(listeRet.size());
		//System.out.println("fin");
		Affichage.afficher(("Fin de la recherche de clique"));
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

		Noeud noeudRetenu = null;
		int nbNoeudAdjacent = 0;
		// Pour chaque noeud de la liste
		for (Noeud noeud : liste)
		{
			// On récupère une copie de sa liste d'adjacence
			Vector<Noeud> listeAdjNoeud = new Vector<Noeud>(noeud.getListeAdjacence());
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
			Vector<Noeud> listeAdjNoeud = new Vector<Noeud>(noeudRetenu.getListeAdjacence());
			// On ne garde que les noeud qui sont encore atteignable;
			listeAdjNoeud.retainAll(liste);
			// On reconstruit une clique
			Vector<Noeud> cliqueEnvoi = new Vector<Noeud>(clique);
			// On y ajoute le noeud courant
			cliqueEnvoi.add(noeudRetenu);

			// On recherche si on a déja vue cette clique
			boolean dejaFait = false;
			// Pour chaque clique
			Vector<Vector<Noeud>> lis = new Vector<Vector<Noeud>>(listeClique);
			for (Vector<Noeud> cli : lis)
			{
				if (cli.size()>=cliqueEnvoi.size() &&  cliqueEnvoi.containsAll(cli))
				{
					dejaFait = true;
				}
			}
			if (dejaFait)
			{

			} else
			{
				System.out.println(cliqueEnvoi);
				Affichage.afficher((cliqueEnvoi));
				System.out.println(listeClique.size());
				Affichage.afficher((listeClique.size()));
				listeClique.add(cliqueEnvoi);
				recursiveClique(cliqueEnvoi, listeAdjNoeud);
			}
		}
		return cliqueRetour;
	}
}
