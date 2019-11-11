package mazeSolver;

import maze.Maze;
import maze.Cell;

import java.util.Collections;
import java.util.ArrayList;

/**
 * Implements the recursive backtracking maze solving algorithm.
 */
public class RecursiveBacktrackerSolver implements MazeSolver {

	Maze maze = null;
	boolean solved = false;
	boolean[][] visited = null;

	/**
	 * constructor
	 */
	public RecursiveBacktrackerSolver() {
	}

	/**
	 * check if a cell is valid
	 * @param cell
	 * @return
	 */
	private boolean validCell(Cell cell){
		// if cell is null return false
		if (cell == null)
			return false;
		//if cell is not in maze return false
		if (cell.r < 0 || cell.r >= this.maze.sizeR || cell.c < 0 && cell.c >= this.maze.sizeC)
			return false;
		//if cell is visited return false
		if (visited[cell.r][cell.c])
			return false;
		//else return true for a valid cell
		return true;
	}

	/**
	 * DFS search for the exit cell
	 * @param maze
	 * @param cell
	 * @param path solve path
	 * @return
	 */
	private boolean DFSsearch(Maze maze, Cell cell, ArrayList<Cell> path){
		// if the cell is not valid return false
		if (!validCell(cell))
			return false;

		path.add(cell);//add cell to the path list
		maze.drawFtPrt(cell);
		visited[cell.r][cell.c] = true;//set the certain visited position to true
		// if the cell is exit return true
		if (cell.equals(maze.exit)){
			return true;
		}

		// if the cell is a tunnel then go to the tunnelTo cell
		if(maze.type == 1 && cell.tunnelTo != null && !visited[cell.tunnelTo.r][cell.tunnelTo.c]){
			Cell nextCell = cell.tunnelTo;
			return DFSsearch(maze,nextCell,path);
		}

		// random search into four directions with DFS approach
		ArrayList<Integer> num = new ArrayList<>();
		num.add(0);
		num.add(1);
		num.add(2);
		num.add(3);
		num.add(4);
		num.add(5);
		Collections.shuffle(num);

		// int[][] direction = createdirection();
		for (int i = 0; i<6; i++){
			Cell nextCell = cell.neigh[i];
			//check if the next cell doesn't exist or there is a wall or has been visited then continue
			if (nextCell == null || cell.wall[i].present || visited[nextCell.r][nextCell.c])
				continue;
			// DFS search, if solved return true
			if (DFSsearch(maze,nextCell,path)){
				return true;
			}
		}
		// if the current cell can't reach exit, delete the cell from path list and back track
		path.remove(path.size() - 1);
		if (path.size() == 0)
			return false;
		// back track to the last cell in path list
		return DFSsearch(maze,path.get(path.size()-1),path);
	}

	@Override
	public void solveMaze(Maze maze) {
		this.maze = maze;

		// create a visited cell record
		if (maze.type == 2) {
			this.visited = new boolean[maze.sizeR][maze.sizeC + (maze.sizeR + 1) / 2];
		} else {
			this.visited = new boolean[maze.sizeR][maze.sizeC];
		}

		ArrayList<Cell> path = new ArrayList<>();// path list
		Cell cell = maze.entrance;//entrance cell, the start of DFS search

		// if the DFS search solve the maze set solved to true
		if (DFSsearch(maze,cell,path)){
			this.solved = true;
		}
		// TODO Auto-generated method stub

	} // end of solveMaze()


	@Override
	public boolean isSolved() {
		if (this.solved)
			return true;
		// TODO Auto-generated method stub
		return false;
	} // end if isSolved()


	@Override
	public int cellsExplored() {
		int cells = 0;
		for(int i = 0; i < this.maze.sizeR; i++){
			for (int j = 0; j < this.maze.sizeC; j++){
				if (visited[i][j]) {
					cells++;
				}
			}
		}
		return cells;
		// TODO Auto-generated method stub
//		return 0;
	} // end of cellsExplored()

} // end of class RecursiveBackTrackerSolver
