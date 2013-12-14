package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class Choix_representation extends JPanel implements ActionListener
{
	//private JRadioButton optimal = new JRadioButton("Optimal", false);
	private JRadioButton matriceButton = new JRadioButton("Matrice", true);
	private JRadioButton arrayButton = new JRadioButton("ArrayList", false);


	public Choix_representation()
	{
		matriceButton.addActionListener(this);
		arrayButton.addActionListener(this);

		ButtonGroup groupe = new ButtonGroup();
		groupe.add(matriceButton);
		groupe.add(arrayButton);


		this.setLayout(new GridLayout(1,5));
		this.add(new JLabel("Représentation du graphe :	"));
		this.add(matriceButton);
		this.add(arrayButton);
		this.add(new JLabel());
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == matriceButton)
		{
			RechercheClique.typeRepresentationGraphe = "matrice";
		} else if (e.getSource() == arrayButton)
		{
			RechercheClique.typeRepresentationGraphe = "arraylist";
		}
	}
}
