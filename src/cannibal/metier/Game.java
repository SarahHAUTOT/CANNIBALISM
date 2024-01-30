package cannibal.metier;

import java.util.ArrayList;


public class Game 
{



	/*   VARIABLES SUR JOUR/MOMENT DE LA PARTIE    */

	public int nbJour;     // Nombre de jour écoulé depuis le début
	public int numJourFin; // Numéro de la journée ou la partie prend fin [15;50]

	public int etatJour; // 0 = nuit, 1 = matin, 2 = midi, 3 = soir.

	public int tourJoueur;


	/*   LIST DES JOUEURS ET DE LEUR ETATS    */
	private ArrayList<Player> lstJoueur;
	private ArrayList<Player> lstJoueurEnVie;


	public Game ()
	{
		this.lstJoueur      = new ArrayList<Player>();
		this.lstJoueurEnVie = new ArrayList<Player>();
	}

	public void addPlayer    (Player p) { this.lstJoueur.add(p);}
	public void removePlayer (Player p) { this.lstJoueur.add(p);}

	public void playerDead   (Player p) { this.lstJoueurEnVie.remove(p);}

	public void start()
	{
		this.nbJour = 0;
		this.numJourFin = (int) (Math.random() * (50-15) + 15);

		this.etatJour   = 1;
		this.tourJoueur = 0;

		
	}



}
