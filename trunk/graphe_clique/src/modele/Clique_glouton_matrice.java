package modele;

import java.util.ArrayList;

public class Clique_glouton_matrice extends CliqueAbstraite
{
	public Clique_glouton_matrice(Graphe grapheRecherche)
	{
		super(grapheRecherche, new ArrayList<ArrayList<Integer>>());
	}

	public Graphe rechercheClique()
	{
		Affichage.afficher(("Début recherche sur le graphe : " + this.getGrapheRecherche().getNom()));
		int tailleMax = 0;
		int noeudRet = -1;
		//On recupère le noeud qui a la plus grand liste d'adjacence
		for (int i = 0; i < this.getGrapheRecherche().getGraphe().length; i++)
		{
			int tailleTemp = this.getGrapheRecherche().getNbNoeudAdjacent(this.getGrapheRecherche().getGraphe(),i);
			if (tailleTemp > tailleMax)
			{
				tailleMax = tailleTemp;
				noeudRet = i;
			}
		}

		//On initialise une clique
		ArrayList<Integer> clique = new ArrayList<Integer>();
		clique.add(noeudRet+1);
		//On lance notre recherche de clique
		this.recursiveClique(this.getGrapheRecherche().getGraphe(),clique, noeudRet);
		Affichage.afficher(("Fin de la recherche de clique"));
		return null;
	}

	public void recursiveClique(int[][] graphe,ArrayList<Integer> clique, int dernierNoeud)
	{
		int noeudRetenu = -1;
		int nbNoeudAdjacent = -1;
		// Pour chaque noeud du graphe
		for (int i = 0; i < graphe.length; i++)
		{
			//Si le noeud courant fait parti de la liste d'ajacence du dernier noeud retenu
			if (graphe[dernierNoeud][i] == 1)
			{
				//On supprimer tout les noeud qui ne sont pas atteignable
				for (int j = 0; j < graphe.length; j++)
				{
					if (graphe[dernierNoeud][j] == 0)
					{
						graphe[i][j] = 0;
					}
				}
				//On compte le nombre noeud de la liste d'ajacence
				int nbNoeudTemp = this.getGrapheRecherche().getNbNoeudAdjacent(graphe,i);
				//On vérifie si le nombre de noeud adjacent est supérieur a ce préédement trouvé.
				if (nbNoeudTemp > nbNoeudAdjacent && nbNoeudTemp >= 0)
				{	
					//Si c'est le cas, on retient le noeud
					nbNoeudAdjacent = nbNoeudTemp;
					noeudRetenu = i;
				}
			}
		}
		//Si on a un noeud retenu
		if (noeudRetenu != -1 && nbNoeudAdjacent >= 0)
		{
			//On complete notre clique
			clique.add(noeudRetenu+1);
			//System.out.println(clique);
			//System.out.println(clique.size());
			this.cpt++;
			//Et on continu notre recherche
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
