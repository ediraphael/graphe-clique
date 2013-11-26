package vue;

import javax.swing.*;

import java.awt.*;
import java.io.*;

@SuppressWarnings("serial")
class AfficheClique extends JPanel
{
	public static final String AfficheClique = null;
	static JEditorPane grandeZone;
	
	public AfficheClique() throws IOException
	{

		JScrollPane texteAsc;
		grandeZone = new JEditorPane("Affichage des cliques", "");
		grandeZone.setEditable(false);
		texteAsc = new JScrollPane(grandeZone);
		texteAsc.setPreferredSize(new Dimension(600, 300));
		add(texteAsc, BorderLayout.CENTER);
	}

	public void rajouterTexte(String texte)
	{
		grandeZone.setText((grandeZone.getText().concat("\n")).concat(texte));
	}

	public static void reset()
	{
		System.out.println("reset de l'affichage");
		grandeZone.setText("");
	}
}
