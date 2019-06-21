package sokoban;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class GameMain {

	public static void main(String[] args) {
			World game;
			boolean isMultiplayer;
			boolean isHost;
			String ip;
			LevelReader reader;
			String worldStr = "";
			Server server = null;
			Client client = null;

			try(Scanner sc = new Scanner(System.in)) {
				System.out.println("Multiplayer? (1):  ");

				isMultiplayer = (sc.nextInt() == 1);


				if (isMultiplayer) {
					System.out.println("Willst du das Spiel hosten? (1): ");
					isHost = (sc.nextInt() == 1);

					if (!isHost) {
						System.out.println("IP des Servers: ");
						ip = sc.next();
						try {
							client = new Client(ip, 4444);
							worldStr = client.read();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						server = new Server();
						try {
							//Level einlesen
							reader = new LevelReader(sc, true);
							worldStr = reader.toString();
							server.connect();
							server.write(worldStr);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					game = new World(worldStr, true, client, server);
				} else {
					//Level einlesen
					reader = new LevelReader(sc, false);
					worldStr = reader.toString();
					game = new World(worldStr, false);
				}



				game.run(sc);
			}
		}
	}
