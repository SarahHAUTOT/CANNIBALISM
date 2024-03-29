package cannibal.metier;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Role
 */
public record Role(String nameRole, String descRole, String descPower, String team, double coefResi, double coefRasa, double coefRepo, double coefFati, double coefRess)
{

	/**
	 * Create and return a list of roles depending on the file (tsv). Look at our documentation to see how to create a RoleFile.
	 * @param fic
	 * @return
	 */
	public static List<Role> createRoles (String fic)
	{
		List<Role> lstRoles = new ArrayList<>();

		try 
		{
			Scanner scanFic = new Scanner(new FileInputStream(fic));

			//On saute les headers du fichier
			scanFic.nextLine();
			scanFic.nextLine();

			//Lecture jusqu'à la fin du fichier
			while (scanFic.hasNextLine()) 
			{

				//On récupère les infos
				String[] ensInfo = scanFic.nextLine().split("\t");

				//Récupération des données
				String name  = ensInfo[0];
				String descR = ensInfo[1];

				String descP = ensInfo[2];
				String teamP = ensInfo[3];

				double coefResi = Double.parseDouble(ensInfo[4]);
				double coefRasa = Double.parseDouble(ensInfo[5]);
				double coefRepo = Double.parseDouble(ensInfo[6]);
				double coefFati = Double.parseDouble(ensInfo[7]);
				double coefRess = Double.parseDouble(ensInfo[8]);

				lstRoles.add(new Role(name, descR, descP, teamP, coefResi, coefRasa, coefRepo, coefFati, coefRess));
			}
		} catch (Exception e) {
			System.out.println("Erreur dans la lecture du fichier " + fic + ", message d'erreur :" + e);
		}

		return lstRoles;
	}




	/*------------------------------------------------*/
	/*                OTHER METHODS                   */
	/*------------------------------------------------*/

	public String toString ()
	{
		String sRep = "\\033[1m" + this.nameRole + "\\033[0m \n" +
		              this.descRole + "\n";

		if (!(this.descPower.equals("N/A") || this.descPower.isEmpty()))
			sRep += "\n" + this.descPower + "\n";

		return sRep;
	}


}
