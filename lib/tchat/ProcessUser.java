import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ProcessUser implements Runnable
{
    private MainServer      serv;
    private Socket          s;
    private PrintWriter     out;
    private BufferedReader  in;
    

    public ProcessUser(Socket socket, MainServer serv)
    { 
        this.s = socket;
        this.serv = serv;

        try
        {
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } 
        catch(IOException e) 
        { 
            e.printStackTrace(); 
        }
    }


    public void run()
    {
        this.out.println("You are connected, please enter any message.");
        this.serv.messageRecus("User " + this.s.getInetAddress() + " is connected", this.out);


        boolean isConnected = true;
        while (isConnected) 
        {
            try 
            {
                // this.out.print(this.s.getInetAddress() + " : ");
                String message = in.readLine();

                if (message == null)
                {
                    this.serv.userDisconect(this.out, this.s.getInetAddress() + "");
                    isConnected = false;
                }
                else
                    this.serv.messageRecus(this.s.getInetAddress() + " : " + message, this.out);
            } 
            catch (IOException e) 
            {

            }
        }
    }

    public PrintWriter getWriter () { return this.out; }
}