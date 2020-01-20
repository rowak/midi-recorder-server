# Midi Recorder Server
This is a very simple companion server for [Midi Recorder](https://github.com/rowak/midi-recorder) that records data from a MIDI device and sends it to the client Android device.

## Installation
This program will only run on Linux and it requires the ```arecordmidi``` package in order to function. You can download the latest version of the server [here.](https://github.com/rowak/midi-recorder-server/releases/latest) The bash script is optional, but makes it easier to run (no need for "java -jar ...").

## Usage
Connect the MIDI device to your computer. Run the command ```arecordmidi -l``` to find the port of your MIDI device, then run the .jar executable using the option ```--midi-port <PORT>``` where &lt;PORT&gt; is the port or your MIDI device. For example: ```java -jar midi-recorder-server.jar --midi-port 20:0```. The server should start and you should be able to connect to it through the Android app. You can also optionally specify the port for the server socket using the option ```--server-port <PORT>``` where &lt;PORT&gt; is the port for the socket.
