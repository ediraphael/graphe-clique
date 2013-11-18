package modele;

public abstract class Affichage
{
	private static Affichage ECRAN = new Ecran_console();
	//private static Affichage ECRAN = new Ecran_fenetre();

	protected abstract void afficher_message(Object message);

	protected abstract void reset_message();

	public static void afficher(Object message)
	{
		ECRAN.afficher_message(message);
	}

	public static void reset()
	{
		ECRAN.reset_message();
	}

	public static Affichage getECRAN()
	{
		return ECRAN;
	}

	public static void setECRAN(Affichage eCRAN)
	{
		ECRAN = eCRAN;
	}
}