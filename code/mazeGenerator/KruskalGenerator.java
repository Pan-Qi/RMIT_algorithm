package mazeGenerator;

import maze.Maze;
import maze.Cell;
import java.util.Collections;
import java.util.ArrayList;

public class KruskalGenerator implements MazeGenerator {

	/**
	 * find out which tree the cell belongs
	 * @param tree
	 * @param r
	 * @param c
	 * @return the root node of the tree that the cell belongs
	 */
	private int[] findCellBelongs(int[][][] tree, int r, int c){
		if(tree[r][c][0] == r && tree[r][c][1] == c){
			int[] belongs = {r,c};
			return belongs;
		}
		return findCellBelongs(tree, tree[r][c][0], tree[r][c][1]);
	}

	@Override
	public void generateMaze(Maze maze) {

		// add all walls into an array list
		ArrayList<int[]> walls = new ArrayList<>();

		for (int i = 0; i < maze.sizeR; i++) {
			for (int j = 0; j < maze.sizeC; j++) {
				for (int k = 0; k < 6; k++){
					if (maze.map[i][j].neigh[k] != null){
						int[] aWall = {i,j,k};
						walls.add(aWall);
					}
				}
			}
		}

        // create a matrix with all cells tree set
		int[][][] cellTree = new int[maze.sizeR][maze.sizeC][2];
		// create an array list for cell that have tunnel to cell
		ArrayList<Cell> tunnelList = new ArrayList<>();

        // initialise the cell tree set
		for (int i = 0;i < maze.sizeR; i++){
			for (int j = 0;j < maze.sizeC; j++){
				int[] cell = new int[2];
				cell[0] = i;
				cell[1] = j;

				// if the cell have tunnel, make them in same tree set
				if (maze.map[i][j].tunnelTo != null){
					if (!tunnelList.contains(maze.map[i][j].tunnelTo)){
						cell[0] = maze.map[i][j].tunnelTo.r;
						cell[1] = maze.map[i][j].tunnelTo.c;
						tunnelList.add(maze.map[i][j]);
					}
				}

				cellTree[i][j] = cell;
			}
		}

		Collections.shuffle(walls); // shuffle the walls array list to make it random

		while(walls.size() > 0){
//			//After shuffle, select wall one by one same as random select wall
			int[] wall = walls.remove(0);

			// extract the row and column number of the resource and destination cell of the wall
			int resR = wall[0];
			int resC = wall[1];
			int destR = maze.map[resR][resC].neigh[wall[2]].r;
			int destC = maze.map[resR][resC].neigh[wall[2]].c;

			//check if two cell in the same tree
			int[] resTree = findCellBelongs(cellTree,resR,resC);
			int[] destTree = findCellBelongs(cellTree,destR,destC);

			if (resTree[0] == destTree[0] && resTree[1] == destTree[1]){
				// two cells in the same tree
				// ignore
			}
			else{
				// two cell not in the same tree, delete the wall
				maze.map[resR][resC].wall[wall[2]].present = false;

				// combine the resource tree and destination tree into a same tree
				cellTree[resTree[0]][resTree[1]] = destTree;
			}
		}

		// TODO Auto-generated method stub

	} // end of generateMaze()

} // end of class KruskalGenerator
