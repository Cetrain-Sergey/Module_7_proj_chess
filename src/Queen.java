public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int startLine, int startColumn, int endLine, int endColumn) {
        if (startLine != endLine && startColumn != endColumn &&
                getMax(startLine, endLine) - getMin(startLine, endLine) == getMax(startColumn, endColumn) - getMin(startColumn, endColumn) &&
                checkpos(startLine) && checkpos(startColumn) && checkpos(endLine) &&
                checkpos(endColumn) &&
                (chessBoard.board[endLine][endColumn] == null || !chessBoard.board[endLine][endColumn].color.equals(this.color)) &&
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
                    } else if (!chessBoard.board[fromL - i][fromC + i].color.equals(this.color) && fromL - i == endLine) {
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
                    } else if (!chessBoard.board[fromL + i][fromC + i].color.equals(this.color) && fromL + i == endLine) {
                        positions[i - 1] = new int[]{fromL + i, fromC + i};
                    } else {
                        return false;
                    }
                }
                return true;
            }
        } else if (checkpos(startLine) && checkpos(startColumn) && checkpos(endLine) && checkpos(endColumn)) {
            if (startColumn == endColumn) {
                for (int i = getMin(startLine, endLine); i < getMax(startLine, endLine); i++) {
                    if (chessBoard.board[i][startColumn] != null) {
                        if (chessBoard.board[i][startColumn] == this && i == getMax(startLine, endLine)) return false;
                        else if (chessBoard.board[i][startColumn].getColor().equals(this.color) && i == endLine)
                            return false;
                        else if (!chessBoard.board[i][startColumn].getColor().equals(this.color) && i == endLine)
                            return true;
                        else if (i != endLine && i != startLine) return false;
                    }
                }

                if (chessBoard.board[endLine][startColumn] != null) {
                    if (chessBoard.board[endLine][startColumn].getColor().equals(this.color) &&
                            chessBoard.board[endLine][startColumn] != this)
                        return false;
                    else return !chessBoard.board[endLine][startColumn].getColor().equals(this.color) &&
                            chessBoard.board[endLine][startColumn] != this;
                } else return true;
            } else if (startLine == endLine) {
                for (int i = getMin(endColumn, startColumn); i < getMax(startColumn, endColumn); i++) {
                    if (chessBoard.board[startLine][i] != null) {
                        if (chessBoard.board[startLine][i] == this && i == getMax(startColumn, endColumn)) return false;
                        else if (chessBoard.board[startLine][i].getColor().equals(this.color) && i == endColumn)
                            return false;
                        else if (!chessBoard.board[startLine][i].getColor().equals(this.color) && i == endColumn)
                            return true;
                        else if (i != endLine && i != startColumn) return false;
                    }
                }

                if (chessBoard.board[endLine][endColumn] != null) {
                    if (chessBoard.board[endLine][endColumn].getColor().equals(this.color) &&
                            chessBoard.board[endLine][endColumn] != this)
                        return false;
                    else return !chessBoard.board[endLine][endColumn].getColor().equals(this.color) &&
                            chessBoard.board[endLine][endColumn] != this;
                } else return true;
            } else return false;
        } else return false;
    }

    private int getMin(int startLine, int endLine) {
        return Math.min(startLine, endLine);
    }

    public int getMax(int startLine, int endLine) {
        return Math.max(startLine, endLine);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    public boolean checkpos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
