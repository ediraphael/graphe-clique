package modele;

import java.util.ArrayList;

public class ThreadRecherche extends Thread
{
	CliqueAbstraite cli;
	ArrayList<Integer> clique;
	ArrayList<Integer> adj;
	
	public ThreadRecherche(CliqueAbstraite cli,ArrayList<Integer> clique,ArrayList<Integer> adj)
	{
		this.cli=cli;
		this.clique = clique;
		this.adj=adj;
	}
	@Override
	public void run()
	{
		cli.recursiveClique(clique, adj);
	}
}
