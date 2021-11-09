public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int startLine, int startColumn, int endLine, int endColumn) {
        if (checkpos(startLine) && checkpos(startColumn) && checkpos(endLine) && checkpos(endColumn)) {
            if (Math.abs(startLine - endLine) > 1 || Math.abs(startColumn - endColumn) > 1) return false;

            if (isUnderAttack(chessBoard, endLine, endColumn)) return false;

            if (chessBoard.board[endLine][endColumn] != null) {
                return !chessBoard.board[endLine][endColumn].getColor().equals(color);
            }

            return true;
        } else return false;

    }

    boolean isUnderAttack(ChessBoard chessBoard, int endLine, int endColumn) {
        if (checkpos(endLine) && checkpos(endColumn)) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if (chessBoard.board[i][j] != null) {
                        if (!chessBoard.board[i][j].getColor().equals(color) && chessBoard.board[i][j].canMoveToPosition(chessBoard,
                                i, j, endLine, endColumn)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else return false;

    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean checkpos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
