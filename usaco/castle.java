
/*
ID: alethe3
LANG: JAVA
TASK: castle
 */

import java.util.*;
import java.io.*;

public class castle {
	public static Block[][] castle;
	public static PrintWriter pw;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("castle.in"));
		pw = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		castle = new Block[height][width];

		for (int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < width; j++) {
				castle[i][j] = new Block(Integer.parseInt(st.nextToken()));
			}
		}

		int numComponents = findComponents();
		System.out.println(numComponents);
		pw.println(numComponents);

		int[] roomSizes = findRoomSizes(numComponents);
		int[] roomSizesCopy = Arrays.copyOf(roomSizes, roomSizes.length);
		Arrays.sort(roomSizes);
		System.out.println(roomSizes[numComponents - 1]);
		pw.println(roomSizes[numComponents - 1]);
		
		combineRoomAndRemoveWall(roomSizesCopy);

		pw.flush();
		pw.close();
		br.close();
	}

	private static void combineRoomAndRemoveWall(int[] roomSizes) {
		int max = 0;
		String coord = "nothing";
		for (int j = 0; j < castle[0].length; j++) {
			for (int i = castle.length - 1; i >= 0; i--) {
				Block curr = castle[i][j];
				if (!curr.neighbors[1] && i - 1 >= 0 && curr.component != castle[i - 1][j].component)
					if (roomSizes[curr.component - 1] + roomSizes[castle[i - 1][j].component - 1] > max) {
						max = roomSizes[castle[i][j].component - 1] + roomSizes[castle[i - 1][j].component - 1];
						coord = (i + 1) + " " + (j + 1) + " " + "N";
					}
				if (!curr.neighbors[2] && j  + 1 < castle[0].length && curr.component != castle[i][j + 1].component)
					if (roomSizes[curr.component - 1] + roomSizes[castle[i][j + 1].component - 1] > max) {
						max = roomSizes[curr.component - 1] + roomSizes[castle[i][j + 1].component - 1];
						coord = (i + 1) + " " + (j + 1) + " " + "E";
					}
			}
		}
		
		pw.println(max);
		System.out.println(max);
		pw.println(coord);
		System.out.println(coord);
	}

	private static int[] findRoomSizes(int numRooms) {
		int[] roomSizes = new int[numRooms];
		for (int i = 0; i < castle.length; i++) {
			for (int j = 0; j < castle[i].length; j++) {
				roomSizes[castle[i][j].component - 1]++;
			}
		}

		return roomSizes;
	}

	private static int findComponents() {
		int numComponents = 0;
		for (int i = 0; i < castle.length; i++) {
			for (int j = 0; j < castle[i].length; j++) {
				if (castle[i][j].component == -1) {
					numComponents++;
					castle[i][j].component = -2;
					floodFill(numComponents);
				}
			}
		}

		return numComponents;
	}

	private static void floodFill(int numComponents) {
		int numVisited;
		do {
			numVisited = 0;
			for (int i = 0; i < castle.length; i++) {
				for (int j = 0; j < castle[i].length; j++) {
					if (castle[i][j].component == -2) {
						Block curr = castle[i][j];
						numVisited++;
						curr.component = numComponents;
						if (curr.neighbors[0] && castle[i][j - 1].component == -1)
							castle[i][j - 1].component = -2;
						if (curr.neighbors[1] && castle[i - 1][j].component == -1)
							castle[i - 1][j].component = -2;
						if (curr.neighbors[2] && castle[i][j + 1].component == -1)
							castle[i][j + 1].component = -2;
						if (curr.neighbors[3] && castle[i + 1][j].component == -1)
							castle[i + 1][j].component = -2;
					}
				}
			}

		} while (numVisited > 0);

	}

	public static class Block {
		int num, component;
		boolean[] neighbors;

		public Block(int number) {
			num = number;
			component = -1;
			neighbors = new boolean[4];
			
			for (int i = 0; i < 4; i++) {
				if ((num >> i) % 2 == 0)
					neighbors[i] = true;
			}
		}
	}

}
