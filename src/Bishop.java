public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int startLine, int startColumn,
                                     int endLine, int endColumn) {
        if (startLine != endLine && startColumn != endColumn &&
                getMax(startLine, endLine) - getMin(startLine, endLine) == getMax(startColumn, endColumn)
                        - getMin(startColumn, endColumn) && checkpos(startLine) && checkpos(startColumn)
                && checkpos(endLine) && checkpos(endColumn) &&(chessBoard.board[endLine][endColumn]
                == null || !chessBoard.board[endLine][endColumn].color.equals(this.color)) &&
                chessBoard.board[startLine][startColumn] != null) {
            if (!chessBoard.board[startLine][startColumn].equals(this)) {
                return false;
            }

            if ((startColumn == getMin(startColumn, endColumn) && startLine == getMax(startLine, endLine)) ||
                    (endColumn == getMin(startColumn, endColumn) && endLine == getMax(startLine, endLine))) {
                int fromL = getMax(startLine, endLine);
                int fromC = getMin(startColumn, endColumn);
                int toL = getMin(startLine, endLine);
                int toC = getMax(startColumn, endColumn);
                int[][] positions = new int[toC - fromC][1];
                for (int i = 1; i < toC - fromC; i++) {
                    if (chessBoard.board[fromL - i][fromC + i] == null) {
                        positions[i - 1] = new int[]{fromL - i, fromC + i};
                    } else if (!chessBoard.board[fromL - i][fromC + i].color.equals(this.color) && fromL
                            - i == endLine) {
                        positions[i - 1] = new int[]{fromL - i, fromC + i};
                    } else {
                        return false;
                    }
                }
                return true;
            } else {
                int fromL = getMin(startLine, endLine);
                int fromC = getMin(startColumn, endColumn);
                int toL = getMax(startLine, endLine);
                int toC = getMax(startColumn, endColumn);
                int[][] positions = new int[toC - fromC][1];
                for (int i = 1; i < toC - fromC; i++) {
                    if (chessBoard.board[fromL + i][fromC + i] == null) {
                        positions[i - 1] = new int[]{fromL + i, fromC + i};
                    } else if (!chessBoard.board[fromL + i][fromC + i].color.equals(this.color) && fromL + i
                            == endLine) {
                        positions[i - 1] = new int[]{fromL + i, fromC + i};
                    } else {
                        return false;
                    }
                }
                return true;
            }
        } else return false;
    }

    private int getMin(int startLine, int endLine) {
        return Math.min(startLine, endLine);
    }

    private int getMax(int startLine, int endLine) {
        return Math.max(startLine, endLine);
    }

    @Override
    public String getSymbol() {
        return "B";
    }
    public boolean checkpos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
