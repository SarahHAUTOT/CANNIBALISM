package cannibal.metier;

public class Player 
{
	public static int nbPlayers = 0;

	private String name;

	/**
	 * Create a player with the default name "User" + the numer of the user.
	 */
	public Player ()
	{
		// So that everyone have a unique name even if they don't change it.
		this.name = "User" + String.format("%05d", ++Player.nbPlayers);
	}

	/**
	 * Change the name of the player.
	 * @param newName
	 */
	public void setName (String newName) { this.name = newName; }
	
}
