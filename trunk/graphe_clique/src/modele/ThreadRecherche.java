package modele;

import java.util.Vector;

public class ThreadRecherche extends Thread
{
	CliqueAbstraite cli;
	Vector<Noeud> clique;
	Vector<Noeud> adj;
	
	public ThreadRecherche(CliqueAbstraite cli,Vector<Noeud> clique,Vector<Noeud> adj)
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
