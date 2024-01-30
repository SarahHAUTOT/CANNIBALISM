package cannibal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

public class ihm implements NativeKeyListener
{
	private String[] choix;
	private String   currentScreen;

	private int      cursor;

	public ihm ()
	{
		this.cursor = 0;
		this.choix = new String[] {"Start", "Règles", "Quitter"};
		this.currentScreen = "menuPrincipal";

		this.menuprincipal();
	}
	
	

	// MENU PRINCIPALE

	public void menuprincipal()
	{
		this.effacerEcran();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./src/data/title.txt"), "UTF-8"))) {

			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < this.choix.length; i++)
		{
			if (i == this.cursor) System.out.print("\033[4m");
			else                  System.out.print("\033[0m");

			System.out.println(choix[i].toUpperCase());
		}

	}

	public void quitter ()
	{
		this.effacerEcran();
		try {
			GlobalScreen.unregisterNativeHook();
		} catch (NativeHookException nativeHookException) {
			nativeHookException.printStackTrace();
		}

		System.exit(0);
	}


	private void effacerEcran()
	{
		System.out.print("\u001b[?25l");
		System.out.println("\u001b[H\u001b[2J" + "\033[0m");
		System.out.flush();
	}

	
















	public static void main(String[] args) {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("Ca marche pas vas niquer ta mère");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new ihm());
	}

	public void nativeKeyReleased(NativeKeyEvent e) {

		//Si on bouge dans la nav
		if (e.getKeyCode() == NativeKeyEvent.VC_DOWN && this.cursor < this.choix.length - 1) 
		{
			this.cursor++;
			this.menuprincipal();
		}

		if (e.getKeyCode() == NativeKeyEvent.VC_UP   && this.cursor >                     0) 
		{
			this.cursor--;
			this.menuprincipal();
		}

		if (e.getKeyCode() == NativeKeyEvent.VC_ENTER)
		{
			this.currentScreen = this.choix[this.cursor].toLowerCase();

			try 
			{
				Method method = this.getClass().getMethod(this.currentScreen);
				method.invoke(this);
			}

			catch (Exception ex) { System.out.println("Méthode manquante pour " + this.currentScreen);}

		}
	}

}
