import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.PrintWriter;

public class GameServer 
{
    private ArrayList<PrintWriter> writerUser;

    public GameServer() 
    {
        this.writerUser = new ArrayList<>();

        try 
        {
            ServerSocket ss = new ServerSocket(9000);
            
            while (true) 
            {
                Socket s = ss.accept();
                GerantPlayer processUser = new GerantPlayer(s, this);
                Thread thread = new Thread(processUser);
                thread.start();

                this.writerUser.add(processUser.getWriter());
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void messageRecus(String messUser, PrintWriter outUser)
    {
        for (PrintWriter out: this.writerUser)
            if (out != outUser)
                out.println(messUser);   
    }

    public void userDisconect (PrintWriter outUser, String username)
    {
        this.writerUser.remove(outUser);
        
        for (PrintWriter out: this.writerUser)
        {
            out.println("User " + username +" disconnected");
        }
    }

    public static void main(String[] args) 
    {
        new MainServer();
    }
}


