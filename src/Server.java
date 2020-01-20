
public class Server
{
	private ClientListener clientListener;
	
	public Server(int serverPort, String midiPort)
	{
		start(serverPort, midiPort);
	}
	
	public void start(int serverPort, String midiPort)
	{
		if (clientListener == null)
		{
			clientListener = new ClientListener(serverPort, midiPort);
		}
	}
}
