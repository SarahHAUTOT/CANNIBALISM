package cannibal.serv;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import cannibal.metier.Player;

public class GerantPlayer implements Runnable 
{
	private Socket         socket;
	private PrintWriter    out;
	private BufferedReader in;

	private Player         player;

	public GerantPlayer(Socket s) 
	{
		this.socket = s;
		this.player = new Player();

		try 
		{
			this.out = new PrintWriter(s.getOutputStream(), true);
			this.in  = new BufferedReader(new InputStreamReader(s.getInputStream()));
		}
		catch (Exception e) {}
	}


	public void run() 
	{
		try 
		{
			this.out.println("Entrez un nom d'utilisateur :");
			String newName = this.in.readLine();

			//If their is a name, change it. If not, just leave it.
			if (!newName.equals(""))
				this.player.setName(newName);
				

			// Demande du nom et port 
			this.connection();

		}

	}

	public void connection()
	{
		try 
		{

			this.out.println("Entrez le code de la partie:");
			int port = Integer.parseInt(this.in.readLine());

			this.changePort(port);
		} 
		catch (Exception e) 
		{
			this.out.println("Une erreur est survenue... Veuillez resaisir vos informations");
			this.connection();
		}
	}


	public void changePort (int port)
	{
		if (port == MainServer.PORT_SERVMAIN || port <= 0) return ;

		Socket oldSocket = this.socket;
		try 
		{
			System.out.println("Connection au serveur...");

			this.socket.close();

			this.socket = new Socket( "localhost", port);
			this.out = new PrintWriter(this.socket.getOutputStream(), true);
			this.in  = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

			System.out.println("Connection au serveur réussie.");
		}
		catch (Exception e)
		{
			System.out.println("Erreur de la connection, veuillez recommencer.");
			this.socket = oldSocket;
			try 
			{
				this.out = new PrintWriter(this.socket.getOutputStream(), true);
				this.in  = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));	
			} catch (Exception e2) {
			}
			
			this.connection();
		}
	}
	
}