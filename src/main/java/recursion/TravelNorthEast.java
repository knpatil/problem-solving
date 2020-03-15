package main.java.recursion;
//
// backtracking example
//
public class TravelNorthEast {
    public static void main(String[] args) {
        int x = 2;
        int y = 3;
        travel(x,y);
    }

    private static void travel(int targetX, int targetY) {
        int startX = 0;
        int startY = 0;
        String path ="moves: ";
        explore(targetX, targetY, startX, startY, path);
    }

    private static void explore(int targetX, int targetY, int currX, int currY, String path) {
        if (currX == targetX && currY == targetY) {
            System.out.println(path); // this is a solution, report it
        } else if (currX <= targetX && currY <= targetY) {
            // this is not a dead end, explore all possibilities
            explore(targetX, targetY, currX, currY + 1, path + " N");
            explore(targetX, targetY, currX + 1, currY, path + " E");
            explore(targetX, targetY, currX + 1, currY + 1, path + " NE");
        }
    }
}
