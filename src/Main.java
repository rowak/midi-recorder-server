
public class Main
{
	private static final int DEFAULT_SERVER_PORT = 1967;
	
	public static void main(String[] args) throws Exception
	{
		int serverPort = getIntArg("--server-port", "-s", DEFAULT_SERVER_PORT, args);
		String midiPort = getStringArg("--midi-port", "-m", null, args);
		boolean help = hasArg("--help", null, false, args);
		if (help)
		{
			System.out.println("Usage: exec [-s server_port] -m midi_port");
			System.out.println("Companion server for Midi Recorder that " +
					"communicates with a MIDI device.");
			System.out.println("\nOptions:");
			System.out.println("  -s, --server-port     specifies the port for the server to run on");
			System.out.println("  -m, --midi-port       specifies the port for the MIDI device");
			System.out.println("      --help            display this help and exit");
			System.out.println("\nExit status:");
			System.out.println("1  MIDI port not specified");
			System.exit(0);
		}
		if (midiPort == null)
		{
			System.out.println("error - midi port must be " +
					"specified using '--midi-port PORT'");
			System.exit(1);
		}
		System.out.println(serverPort + " " + midiPort);
		Server server = new Server(serverPort, midiPort);
	}
	
	private static int getIntArg(String arg, String shortArg,
			int defaultArg, String[] args)
	{
		for (int i = 0; i < args.length; i++)
		{
			if ((arg != null && args[i].equals(arg)) || args[i].equals(shortArg))
			{
				if (i < args.length-1)
				{
					try
					{
						int argInt = Integer.parseInt(args[i+1]);
						return argInt;
					}
					catch (NumberFormatException nfe)
					{
						return defaultArg;
					}
				}
			}
		}
		return defaultArg;
	}
	
	private static String getStringArg(String arg, String shortArg,
			String defaultArg, String[] args)
	{
		for (int i = 0; i < args.length; i++)
		{
			if ((arg != null && args[i].equals(arg)) || args[i].equals(shortArg))
			{
				if (i < args.length-1)
				{
					return args[i+1];
				}
			}
		}
		return defaultArg;
	}
	
	private static boolean hasArg(String arg, String shortArg,
			boolean defaultArg, String[] args)
	{
		for (String a : args)
		{
			if ((arg != null && a.equals(arg)) || a.equals(shortArg))
			{
				return true;
			}
		}
		return defaultArg;
	}
}
