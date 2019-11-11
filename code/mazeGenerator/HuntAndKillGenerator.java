package mazeGenerator;

import maze.Maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HuntAndKillGenerator implements MazeGenerator {

    /**
     * walk step
     * @param maze
     * @param x
     * @param y
     * @param visited
     */
    private void walk(Maze maze, int x, int y, boolean visited[][]) {
        // mark x,y as visited
        visited[x][y] = true;

        if (maze.map[x][y].tunnelTo != null && !visited[maze.map[x][y].tunnelTo.r][maze.map[x][y].tunnelTo.c]){
            int toX = maze.map[x][y].tunnelTo.r;
            int toY = maze.map[x][y].tunnelTo.c;
            x = toX;
            y = toY;
            visited[toX][toY] = true;
        }

        // create 4 directions list n/s/w/e
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        ArrayList<Integer> num = new ArrayList<>();
        num.add(0);
        num.add(1);
        num.add(2);
        num.add(3);

        boolean flag = true;

        while (flag) {
            flag = false;
            Collections.shuffle(num);
            int index = 0;

            while (index < 4 && !flag) {
                if (maze.map[x][y].tunnelTo != null && !visited[maze.map[x][y].tunnelTo.r][maze.map[x][y].tunnelTo.c]){
                    int toX = maze.map[x][y].tunnelTo.r;
                    int toY = maze.map[x][y].tunnelTo.c;
                    x = toX;
                    y = toY;
                    visited[toX][toY] = true;
                }

                int newX = x + direction[num.get(index)][0];
                int newY = y + direction[num.get(index)][1];
                if (newX >= 0 && newX < maze.sizeR && newY >= 0 && newY < maze.sizeC && !visited[newX][newY]) {
                    //check the direction and unpresent the wall
                    if (num.get(index) == 0) {
                        maze.map[x][y].wall[0].present = false;
                    }

                    if (num.get(index) == 1) {
                        maze.map[x][y].wall[3].present = false;
                    }

                    if (num.get(index) == 2) {
                        maze.map[x][y].wall[2].present = false;
                    }

                    if (num.get(index) == 3) {
                        maze.map[x][y].wall[5].present = false;
                    }
                    flag = true;
                    x = newX;
                    y = newY;
                    visited[x][y] = true;
                }
                index += 1;
            }
        }
    }

    /**
     * hunt step
     * @param maze
     * @param visited
     * @return the found cell for walk step, return [-1,-1] if no more unvisited cell
     */
    private int[] hunt(Maze maze, boolean visited[][]){
        int[] point = {-1,-1}; // the point that fullfill the require
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; //four directions
        for (int i = 0; i < maze.sizeR; i++) {
            for (int j = 0; j < maze.sizeC; j++) {
                if (!visited[i][j]){
                    for (int k = 0; k < 4; k++){
                        int newX = i + direction[k][0];
                        int newY = j + direction[k][1];
                        // check if the cell valid
                        if (newX >= 0 && newX < maze.sizeR && newY >= 0 && newY < maze.sizeC && visited[newX][newY]){

                            //check the direction and delete the wall
                            if (k == 0) {
                                maze.map[i][j].wall[0].present = false;
                            }

                            if (k == 1) {
                                maze.map[i][j].wall[3].present = false;
                            }

                            if (k == 2) {
                                maze.map[i][j].wall[2].present = false;
                            }

                            if (k == 3) {
                                maze.map[i][j].wall[5].present = false;
                            }
                            visited[i][j] = true;
                            point[0] = i;
                            point[1] = j;
                            return point;
                        }
                    }
                }
            }
        }

        return point;
    }


    @Override
    public void generateMaze(Maze maze) {
        //		record the visited cell
        boolean visited[][] = new boolean[maze.sizeR][maze.sizeC];
        for (int i = 0; i < maze.sizeR; i++) {
            for (int j = 0; j < maze.sizeC; j++) {
                visited[i][j] = false;
            }
        }

        // the random cell that started with
        Random random = new Random();
        int x = random.nextInt(maze.sizeR);
        int y = random.nextInt(maze.sizeC);

        // start the walk & hunt loop
        while (x != -1 && y != -1) {
            walk(maze,x,y,visited);
            int[] point = hunt(maze,visited);
            x = point[0];
            y = point[1];
        }


        // TODO Auto-generated method stub

    } // end of generateMaze()

} // end of class HuntAndKillGenerator
