import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class ClientHandler implements Runnable
{
	private String midiPort;
	private ClientListener clientListener;
	private PrintWriter writer;
	private BufferedReader reader;
	
	public ClientHandler(PrintWriter writer,
			BufferedReader reader, String midiPort,
			ClientListener clientListener)
	{
		this.writer = writer;
		this.reader = reader;
		this.midiPort = midiPort;
		this.clientListener = clientListener;
	}
	
	@Override
	public void run()
	{
		try
		{
			String rawData = null;
			String[] data = null;
			while ((rawData = reader.readLine()) != null)
			{
				data = rawData.split(" ");
				switch (data[0])
				{
					case "start_recording":
						if (clientListener.isRecording())
						{
							writer.println("error already_recording");
						}
						else if (data.length == 2)
						{
							data[1] = data[1].replace("`", " ");
							clientListener.setProcess(Runtime.getRuntime().exec(
									new String[]{"arecordmidi", "--port=" +
											midiPort, data[1]}));
							clientListener.setActiveFile(data[1]);
							writer.println("ok");
						}
						else
						{
							writer.println("error not_enough_arguments");
						}
						break;
					case "stop_recording":
						if (clientListener.isRecording())
						{
							clientListener.getProcess().destroy();
							byte[] fileData = Files.readAllBytes(new File(
									clientListener.getActiveFile()).toPath());
							writer.println("ok " + bytesToString(fileData));
						}
						else
						{
							writer.println("error not_recording");
						}
						break;
				}
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	private String bytesToString(byte[] bytes)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++)
		{
			sb.append((int)bytes[i]);
			if (i < bytes.length-1)
			{
				sb.append("_");
			}
		}
		return sb.toString();
	}
}
