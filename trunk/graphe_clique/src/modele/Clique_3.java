package modele;

import java.util.ArrayList;

public class Clique_3 extends CliqueAbstraite
{
	public Clique_3(Graphe grapheRecherche)
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
		tailleMax = 0;
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
		// Affichage.afficher((listeRet));
		// System.out.println(listeRet.size());
		// Affichage.afficher(listeRet.size());
		// System.out.println("fin");
		Affichage.afficher(("Fin de la recherche de clique"));
		return null;
	}

	public void recursiveClique(ArrayList<Integer> clique, ArrayList<Integer> liste)
	{
		Integer noeudRetenu = null;
		int nbNoeudAdjacent = -1;
		// Pour chaque noeud de la liste
		for (Integer noeud : liste)
		{
			this.getGrapheRecherche().getListeNoeud().get(noeud).retainAll(liste);
			if (nbNoeudAdjacent < this.getGrapheRecherche().getListeNoeud().get(noeud).size())
			{
				noeudRetenu = noeud;
				nbNoeudAdjacent = this.getGrapheRecherche().getListeNoeud().get(noeud).size();
			}
		}
		if (noeudRetenu != null)
		{
			clique.add(noeudRetenu);
			System.out.println(clique);
			this.cpt++;
			// Affichage.afficher((clique));
			// System.out.println(this.getGrapheRecherche().getListeNoeud().get(noeudRetenu));
			// Affichage.afficher((this.getListeClique().size()));
			recursiveClique(clique, this.getGrapheRecherche().getListeNoeud().get(noeudRetenu));
		} else
		{
			this.getListeClique().add(clique);
		}
	}

	@Override
	public void recursiveClique(int[][] graphe, ArrayList<Integer> clique, int dernierNoeud)
	{
		// TODO Auto-generated method stub
		
	}
}
