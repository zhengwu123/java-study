import java.util.Scanner;

/**
 * Created by zheng wu on 10/9/16.
 */
public class Connect4 {
    private char [][] board = new char[6][7];
    int [] rows = {5,5,5,5,5,5,5};
    //initialize the board
    public Connect4(){
        for(int i = 0;i<6;i++){
            for(int j=0;j<7;j++){
                board[i][j] = ' ';
            }
        }

    }

    public char[][] getBoard(){
        char[][] result = new char[6][7];
        for(int i = 0;i<6;i++){
            for(int j=0;j<7;j++){
                result[i][j] = board[i][j];
            }
        }
        return result;

    }

    public int putPiece(int column,char color) {
        do {
            if (column >= 0 && column < 7) {
                if (color == 'X') {
                    int row = rows[column];
                    board[row][column] = 'X';
                    rows[column]--;
                } else {
                    int row = rows[column];
                    board[row][column] = 'O';
                    rows[column]--;
                }

            }
            else{
                System.out.println("column must between 0-6");
                continue;
            }
            return rows[column]+1;
        }
        while(true);

    }


    public char checkAlignment(int row,int column){
        //'O' for red or 'X' for yellow player or ''
        char a = board[row][column];
        String siblings = String.format("%c%c%c%c", a, a, a, a);
        if(checkHorizontal(row,column).contains(siblings)||
                checkVertical(row,column).contains(siblings) ||
                slashDiagonal(row,column).contains(siblings) ||
                backslashDiagonal(row,column).contains(siblings)
                )
            return a;

        return ' ';
    }

    public void printScreen(){
        System.out.println("    0  1  2  3  4  5  6  ");
        for(int i = 0;i<6;i++){
            System.out.println("  -----------------------");
            switch (i) {
                case 0:
                    System.out.print('A');
                    break;
                case 1:
                    System.out.print('B');
                    break;
                case 2:
                    System.out.print('C');
                    break;
                case 3:
                    System.out.print('D');
                    break;
                case 4:
                    System.out.print('E');
                    break;
                case 5:
                    System.out.print('F');
                    break;
                    default:
                        break;
            }
            for(int j=0;j<7;j++){
                    System.out.print(" |" + board[i][j] );
                if(j==6)
                    System.out.print(" |");
                }
            System.out.println();
            }

        }


    public String checkHorizontal(int row,int column){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<7;i++) {
            sb.append(board[row][i]);
        }
        return sb.toString();
    }

    public String checkVertical(int row,int column){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<6;i++){
            sb.append(this.board[i][column]);
        }
        return sb.toString();
    }

    public String slashDiagonal(int row,int column){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<6;i++){
            int w = column+ row -i;
            if(w>=0 && w<7){
                sb.append(board[i][w]);
            }
        }
        return sb.toString();
    }

    public String backslashDiagonal(int row,int column){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<6;i++){
            int w = column-row +i;
            if(w>=0 && w<7){
                sb.append(board[i][w]);
            }
        }
        return sb.toString();
    }
    //play method for two players.
    //red start first
    //check if there is an alignment
    public void play() {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        char player1 = 'O';
        char player2 = 'X';
        int turn = 0;
        int column;
        int row;
        while (true) {
            if (turn % 2 == 0) {
                printScreen();
                System.out.println("Current player: '" + player1+"'");
                System.out.println("Choose a column: ");
                column = scanner.nextInt();
                row = putPiece(column, player1);

                if (checkAlignment(row, column) == 'O') {
                    printScreen();
                    System.out.println("!!! Winner is Player 'O' !!!");

                    break;
                }
                if (checkAlignment(row, column) == 'X') {
                    printScreen();
                    System.out.println("!!! Winner is Player 'X' !!!");
                    break;
                }



            }
            else if(turn %2!=0){
                printScreen();
                System.out.println("Current player: '" + player2+"'");
                System.out.println("Choose a column: ");
                column = scanner.nextInt();
                row = putPiece(column, player2);

                if (checkAlignment(row, column) == 'O') {
                    printScreen();
                    System.out.println("!!! Winner is Player 'O' !!!");
                    break;
                }
                if (checkAlignment(row, column) == 'X') {
                    printScreen();
                    System.out.println("!!! Winner is Player 'X' !!!");
                    break;
                }

            }
            turn++;
        }
    }
}
