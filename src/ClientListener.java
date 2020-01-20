import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientListener
{
	private String activeFile;
	private Process process;
	
	public ClientListener(int serverPort, String midiPort)
	{
		try
		{
			System.out.println("Starting server...");
			ServerSocket serverSocket = new ServerSocket(serverPort);
			System.out.println("Server started on port " + serverPort + ".");
			while (true)
			{
				Socket clientSocket = serverSocket.accept();
			    PrintWriter writer =
			        new PrintWriter(clientSocket.getOutputStream(), true);
			    BufferedReader reader = new BufferedReader(
			        new InputStreamReader(clientSocket.getInputStream()));
			    new Thread(new ClientHandler(writer, reader, midiPort, this)).start();
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	public String getActiveFile()
	{
		return activeFile;
	}
	
	public void setActiveFile(String activeFile)
	{
		this.activeFile = activeFile;
	}
	
	public Process getProcess()
	{
		return process;
	}
	
	public void setProcess(Process process)
	{
		this.process = process;
	}
	
	public boolean isRecording()
	{
		return process != null && process.isAlive();
	}
}
