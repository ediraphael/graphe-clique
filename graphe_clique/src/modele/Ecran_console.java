package modele;

public class Ecran_console extends Affichage
{
	protected void afficher_message(Object message)
	{
		System.out.println(message);
	}

	@Override
	protected void reset_message()
	{

	}
}
