package modele;

import java.util.Vector;

public class Noeud
{
	private String nom;
	private Vector<Noeud> listeAdjacence;

	public Noeud(String nom)
	{
		this.nom = nom;
		this.listeAdjacence = new Vector<Noeud>();
	}

	public Noeud(String nom, Vector<Noeud> listeAdjacence)
	{
		this.nom = nom;
		this.listeAdjacence = listeAdjacence;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public Vector<Noeud> getListeAdjacence()
	{
		return listeAdjacence;
	}

	public void setListeAdjacence(Vector<Noeud> listeAdjacence)
	{
		this.listeAdjacence = listeAdjacence;
	}

	public void addNoeudAdjacent(Noeud noeud)
	{
		if (!this.listeAdjacence.contains(noeud))
		{
			boolean dejaPresent = false;
			for (Noeud noeudListe : this.listeAdjacence)
			{
				if (noeudListe.estNoeud(noeud.getNom()))
				{
					dejaPresent = true;
				}
			}
			if (!dejaPresent)
			{
				this.listeAdjacence.add(noeud);
			}
		}
	}

	public void removeNoeudAdjacent(Noeud noeud)
	{
		this.listeAdjacence.remove(noeud);
	}

	public boolean estNoeud(String noeud)
	{
		return this.nom.equals(noeud);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Noeud))
		{
			return false;
		} else if (obj == this)
		{
			return true;
		} else if (!((Noeud) obj).getNom().equals(this.getNom()))
		{
			return false;
		} else if (!((Noeud) obj).getListeAdjacence().equals(this.getListeAdjacence()))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "Noeud:" + this.nom;
	}
}
