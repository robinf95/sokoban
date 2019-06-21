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

			System.out.println("Multiplayer? (1):  ");

			try(Scanner sc = new Scanner(System.in)) {

				isMultiplayer = (sc.nextInt() == 1);

				if (isMultiplayer) {
					System.out.println("Willst du das Spiel hosten? (1): ");
					isHost = (sc.nextInt() == 1);

					if (!isHost) {
						System.out.println("IP des Servers: ");
						ip = sc.next();
						Client client;
						Socket socket = null;
						try {
							socket = new Socket(ip, 4444);
						} catch (IOException e) {
							e.printStackTrace();
						}
						client = new Client(socket);
						try {
							client.write(socket, "Connected");
							worldStr = client.read(socket);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						Server server = new Server();
						reader = new LevelReader(sc);
						worldStr = reader.toString();
						try {
							server.connect();
							server.write(worldStr);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					reader = new LevelReader(sc);
					worldStr = reader.toString();
				}

				game = new World(worldStr, isMultiplayer);

				game.run(sc);
			}
		}
	}
