package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class Graphe
{
	private String nom;
	private Vector<Noeud> listeNoeud;

	public Graphe()
	{
		this.nom = "";
		this.listeNoeud = new Vector<Noeud>();
	}

	public Graphe(String fichier)
	{
		this.nom = "";
		this.listeNoeud = new Vector<Noeud>();
		this.loadFile(fichier);
	}

	public Graphe(String nomGraphe, String fichier)
	{
		this.nom = nomGraphe;
		this.listeNoeud = new Vector<Noeud>();
		this.loadFile(fichier);
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public Vector<Noeud> getListeNoeud()
	{
		return listeNoeud;
	}

	public void setListeNoeud(Vector<Noeud> listeNoeud)
	{
		this.listeNoeud = listeNoeud;
	}

	public boolean contain(Noeud noeud)
	{
		return this.listeNoeud.contains(noeud);
	}

	public boolean contain(String noeud)
	{
		for (Noeud noeudListe : this.listeNoeud)
		{
			if (noeudListe.estNoeud(noeud))
			{
				return true;
			}
		}
		return false;
	}

	public boolean addNoeud(Noeud noeud)
	{
		return this.listeNoeud.add(noeud);
	}

	public boolean addNoeud(String noeud)
	{
		return this.addNoeud(new Noeud(noeud));
	}

	public Noeud getNoeud(String noeud)
	{
		for (Noeud noeudListe : this.listeNoeud)
		{
			if (noeudListe.estNoeud(noeud))
			{
				return noeudListe;
			}
		}
		return null;
	}

	public void loadFile(String fichier)
	{
		try
		{
			// On ouvre un pointer sur fichier
			BufferedReader input = new BufferedReader(new FileReader(fichier));
			try
			{
				String line = null;
				// On parcours toutes les lignes
				while ((line = input.readLine()) != null)
				{
					// On split la ligne
					StringTokenizer token = new StringTokenizer(line, " ");
					// Si le premier mot est un e
					String firstToken = token.nextToken();
					if (firstToken.equals("e"))
					{
						// Alors on récupère les noeud
						String stringNoeud1 = token.nextToken();
						String stringNoeud2 = token.nextToken();
						// On les ajoutes au graph si il ne sont pas déjà
						// présents
						if (!this.contain(stringNoeud1))
						{
							this.addNoeud(stringNoeud1);
						}
						if (!this.contain(stringNoeud2))
						{
							this.addNoeud(stringNoeud2);
						}
						// On récupère les vrai noeud du graphe
						Noeud noeud1 = this.getNoeud(stringNoeud1);
						Noeud noeud2 = this.getNoeud(stringNoeud2);
						// On ajout les adjacence
						noeud1.addNoeudAdjacent(noeud2);
						noeud2.addNoeudAdjacent(noeud1);
					} else if (token.hasMoreTokens() && token.nextToken().equals("FILE:") && this.nom.equals(""))
					{
						this.nom = token.nextToken();
					}
				}
			} finally
			{
				input.close();
			}
		} catch (IOException ex)
		{
			System.err.println("Erreur Graphe:loadFile(" + fichier + "):" + ex.getMessage());
		}
	}
}
