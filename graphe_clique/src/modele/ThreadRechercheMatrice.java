package modele;

import java.util.ArrayList;

public class ThreadRechercheMatrice extends Thread
{
	CliqueAbstraite cliqueAbstraite;
	int[][] graphe;
	ArrayList<Integer> clique;
	int noeud;
	
	public ThreadRechercheMatrice(CliqueAbstraite cliqueAbstraite,int[][] graphe,ArrayList<Integer> clique,int noeud)
	{
		this.cliqueAbstraite=cliqueAbstraite;
		this.graphe=graphe;
		this.clique = clique;
		this.noeud=noeud;
	}
	@Override
	public void run()
	{
		cliqueAbstraite.recursiveClique(graphe, clique, noeud);
	}
}
