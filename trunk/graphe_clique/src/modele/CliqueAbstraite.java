package modele;

import java.util.Vector;

public abstract class CliqueAbstraite
{
	public abstract Graphe rechercheClique();
	public abstract Vector<Noeud> recursiveClique(Vector<Noeud> clique, Vector<Noeud> listeNoeud);
}
