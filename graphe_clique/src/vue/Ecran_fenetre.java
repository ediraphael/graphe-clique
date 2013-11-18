package vue;

import modele.Affichage;

public class Ecran_fenetre extends Affichage
{
	AfficheClique affichage;

	public Ecran_fenetre(AfficheClique affichage)
	{
		super();
		this.affichage = affichage;
	}

	public AfficheClique getAffichage()
	{
		return affichage;
	}

	public void setAffichage(AfficheClique affichage)
	{
		this.affichage = affichage;
	}

	@Override
	protected void afficher_message(Object message)
	{
		this.affichage.rajouterTexte(message.toString());
	}
	
	protected void reset_message()
	{
		affichage.reset();
	}
}