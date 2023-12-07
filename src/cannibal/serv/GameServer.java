package cannibal.serv;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import cannibal.metier.Player;

public class GameServer 
{
	private int port;

	private HashMap<Player, String> lstJoueur;

	public GameServer (int port)
	{
		this.port = port;

		try 
		{
			ServerSocket ss = new ServerSocket(port);
			
		} 
		catch (Exception e) 
		{
			
		}
	}
}
