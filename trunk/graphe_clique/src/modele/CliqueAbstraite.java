package modele;

import java.util.ArrayList;

import vue.GrapheClique;

public abstract class CliqueAbstraite
{
	private Graphe grapheRecherche;
	private ArrayList<ArrayList<Integer>> listeClique;
	public int cpt = 0;
	private boolean running;

	public CliqueAbstraite(Graphe grapheRecherche, ArrayList<ArrayList<Integer>> listeClique)
	{
		this.grapheRecherche = grapheRecherche;
		this.listeClique = listeClique;
		this.running = true;
	}

	protected abstract Graphe rechercheClique();

	public abstract void recursiveClique(ArrayList<Integer> clique, ArrayList<Integer> listeNoeud);

	public abstract void recursiveClique(int[][] graphe, ArrayList<Integer> clique, int dernierNoeud);
	
	public void lancerRecherche()
	{
		this.running = true;
		long start = System.currentTimeMillis();
		this.rechercheClique();
		this.running = false;
		long dure = System.currentTimeMillis() - start;
		Affichage.afficher("Plus grande clique trouvé : " + GrapheClique.getClique().getMaximumSizeClique());
		Affichage.afficher(GrapheClique.getClique().getMaximumClique());
		Affichage.afficher(("durée calcul : " + ((float) (dure)) / 1000 + "s"));
		// Affichage.afficher(("durée = " + ((float) dure + (start2 - start)) /
		// 1000 + "s"));
	}

	public boolean isRunning()
	{
		return running;
	}

	public void setRunning(boolean running)
	{
		this.running = running;
	}

	public int getMaximumSizeClique()
	{
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
		return tailleMax;
	}

	public ArrayList<Integer> getMaximumClique()
	{
		int tailleMax = 0;
		ArrayList<Integer> listeRet = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> copyList = new ArrayList<ArrayList<Integer>>(this.getListeClique());
		for (ArrayList<Integer> liste : copyList)
		{
			if (liste.size() > tailleMax)
			{
				listeRet = liste;
				tailleMax = liste.size();
			}
		}
		return listeRet;
	}

	public Graphe getGrapheRecherche()
	{
		return grapheRecherche;
	}

	public void setGrapheRecherche(Graphe grapheRecherche)
	{
		this.grapheRecherche = grapheRecherche;
	}

	public ArrayList<ArrayList<Integer>> getListeClique()
	{
		return listeClique;
	}

	public void setListeClique(ArrayList<ArrayList<Integer>> listeClique)
	{
		this.listeClique = listeClique;
	}

	public int getCpt()
	{
		return cpt;
	}

	public void setCpt(int cpt)
	{
		this.cpt = cpt;
	}
}
