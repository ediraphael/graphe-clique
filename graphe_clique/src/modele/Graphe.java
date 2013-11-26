package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Graphe
{
	private String nom;
	// private HashMap<String, Noeud> listeNoeud;
	private HashMap<Integer, ArrayList<Integer>> listeNoeud;
	private int[][] graphe;

	public Graphe()
	{
		this.nom = "";
		this.listeNoeud = new HashMap<Integer, ArrayList<Integer>>();
	}

	public Graphe(String fichier)
	{
		this.nom = "";
		this.listeNoeud = new HashMap<Integer, ArrayList<Integer>>();
		this.loadFile(fichier);
	}

	public Graphe(String nomGraphe, String fichier)
	{
		this.nom = nomGraphe;
		this.listeNoeud = new HashMap<Integer, ArrayList<Integer>>();
		this.loadFile(fichier);
	}

	public void setNoeuds(HashMap<Integer, ArrayList<Integer>> noeuds)
	{
		this.listeNoeud = noeuds;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public HashMap<Integer, ArrayList<Integer>> getListeNoeud()
	{
		return listeNoeud;
	}

	public void setListeNoeud(HashMap<Integer, ArrayList<Integer>> listeNoeud)
	{
		this.listeNoeud = listeNoeud;
	}

	public boolean contain(Noeud noeud)
	{
		return this.listeNoeud.containsKey(noeud.getNom());
	}

	public boolean contain(Integer noeud)
	{
		return this.listeNoeud.containsKey(noeud);
	}

	public boolean addNoeud(Integer nom, ArrayList<Integer> listadj)
	{
		return this.listeNoeud.put(nom, listadj) != null;
	}

	public boolean addNoeud(Integer noeud)
	{
		return this.addNoeud(noeud, new ArrayList<Integer>());
	}

	public int[][] getGraphe()
	{
		return graphe;
	}

	public int getNbNoeud()
	{
		return graphe.length;
	}

	public int getNbNoeudAdjacent(int[][] graphe, int noeud)
	{
		int cpt = 0;
		for (int i = 0; i < graphe.length; i++)
		{
			if (graphe[noeud][i] == 1)
			{
				cpt++;
			}
		}
		return cpt;
	}

	public void setGraphe(int[][] graphe)
	{
		this.graphe = graphe;
	}

	public void loadFile(String fichier)
	{
		try
		{
			BufferedReader inputF = new BufferedReader(new FileReader(fichier));
			int nbNoeud = 0;
			try
			{
				String line = null;
				// On parcours toutes les lignes
				while ((line = inputF.readLine()) != null)
				{
					if (line.contains("c number of vertices"))
					{
						String[] lineSplit = line.split(":");
						nbNoeud = Integer.parseInt(lineSplit[1].trim());
						break;
					}else if (line.contains("Graph Size"))
					{
						String[] lin = line.split(",");
						String[] lineSplit = lin[0].split(":");
						nbNoeud = Integer.parseInt(lineSplit[1].trim());
						break;
					}
					else if (line.contains("n ="))
					{
						String[] lin = line.split("a");
						String[] lineSplit = lin[0].split("=");
						nbNoeud = Integer.parseInt(lineSplit[1].trim());
						break;
					}
				}
			} finally
			{
				inputF.close();
			}
			System.out.println(nbNoeud);
			this.graphe = new int[nbNoeud][nbNoeud];
			for (int i = 0; i < graphe.length; i++)
			{
				for (int j = 0; j < graphe.length; j++)
				{
					graphe[i][j] = 0;
				}
			}
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
						Integer stringNoeud1 = Integer.parseInt(token.nextToken());
						Integer stringNoeud2 = Integer.parseInt(token.nextToken());
						// On les ajoutes au graph si il ne sont pas déjà
						// présents

						/*
						 * if (!this.contain(stringNoeud1)) {
						 * this.addNoeud(stringNoeud1); } if
						 * (!this.contain(stringNoeud2)) {
						 * this.addNoeud(stringNoeud2); }
						 */

						// On ajout les adjacence
						// this.listeNoeud.get(stringNoeud1).add(stringNoeud2);
						// this.listeNoeud.get(stringNoeud2).add(stringNoeud1);
						this.graphe[stringNoeud1 - 1][stringNoeud2 - 1] = 1;
						this.graphe[stringNoeud2 - 1][stringNoeud1 - 1] = 1;
					} else if (token.hasMoreTokens() && token.nextToken().equals("FILE:"))
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
			Affichage.afficher("Erreur Graphe:loadFile(" + fichier + "):" + ex.getMessage());
		}
	}
}
