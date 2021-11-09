public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int startLine, int startColumn, int endLine, int endColumn) {
        if (checkpos(startLine) && checkpos(startColumn) && checkpos(endLine) && checkpos(endColumn) &&
                chessBoard.board[startLine][startColumn] != null) {
            if (startColumn == endColumn) {
                int dir;
                int start;

                if (color.equals("White")) {
                    dir = 1;
                    start = 1;
                } else {
                    dir = -1;
                    start = 6;
                }

                if (startLine + dir == endLine) {
                    return chessBoard.board[endLine][endColumn] == null;
                }

                if (startLine == start && startLine + 2 * dir == endLine) {
                    return chessBoard.board[endLine][endColumn] == null && chessBoard.board[startLine + dir][startColumn] == null;
                }

            } else {

                if ((startColumn - endColumn == 1 || startColumn - endColumn == -1) && (startLine - endLine == 1 ||
                        startLine - endLine == -1) && chessBoard.board[endLine][endColumn] != null) {
                    return !chessBoard.board[endLine][endColumn].getColor().equals(color);
                } else return false;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    public boolean checkpos(int pos) {

        return pos >= 0 && pos <= 7;
    }
}
