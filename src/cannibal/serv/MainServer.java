package cannibal.serv;

import java.net.ServerSocket;
import java.net.Socket;

public class MainServer 
{
	public static final int PORT_SERVMAIN = 9000;
	public static void main(String[] args) 
	{
		try 
		{
			ServerSocket ss = new ServerSocket(MainServer.PORT_SERVMAIN);

			while (true)
			{
				//Attend une personne
				Socket s = ss.accept();

				//Le renvoyer vers un gars
				GerantPlayer gdc = new GerantPlayer(s);

				//S'en débarasser en le donner au gerant
				Thread tgdc = new Thread(gdc);
				tgdc.start();

				// ... et on recommence...
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Erreur du serveur. Nous nous excusons pour la gêne occasionnée.");
		}
	}
}
