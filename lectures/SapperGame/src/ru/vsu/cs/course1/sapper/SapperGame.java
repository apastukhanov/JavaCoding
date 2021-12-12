package ru.vsu.cs.course1.sapper;

import java.util.Random;

/**
 * Класс, реализующий логику игры
 */
public class SapperGame {

    public enum GameState {
        NOT_STARTED,
        PLAYING,
        WIN,
        FAIL
    }

    public enum CellState {
        CLOSED,
        OPENED,
        FLAG,
        PROBLEM
    }

    public static class SapperCell {
        private CellState state;
        private boolean mine;
        private int mineCountAround;


        public SapperCell(CellState state, boolean mine, int mineCountAround) {
            this.state = state;
            this.mine = mine;
            this.mineCountAround = mineCountAround;
        }


        public CellState getState() {
            return state;
        }

        public boolean isMine() {
            return mine;
        }

        public int getMineCountAround() {
            return mineCountAround;
        }
    }


    private static final Random RND = new Random();

    private GameState state = GameState.NOT_STARTED;
    private int mineCount = 0;
    private SapperCell[][] field = null;


    public SapperGame() {

    }


    private int getMineCountAround(int row, int col) {
        int rowCount = getRowCount(), colCount = getColCount();

        int mineCount = 0;
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if ((r != row || c != row) &&
                        0 <= r && r < rowCount &&
                        0 <= c && c < colCount) {
                    if (field[r][c].mine) {
                        mineCount++;
                    }
                }
            }
        }
        return mineCount;
    }

    private int openAround(int row, int col) {
        int rowCount = getRowCount(), colCount = getColCount();

        int opened = 0;
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if ((r != row || c != row) &&
                        0 <= r && r < rowCount &&
                        0 <= c && c < colCount) {
                    if (field[r][c].state == CellState.CLOSED) {
                        field[r][c].state = CellState.OPENED;
                        opened++;
                    }
                }
            }
        }
        return opened;
    }


    public void newGame(int rowCount, int colCount, int mineCount) {
        field = new SapperCell[rowCount][colCount];
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                field[r][c] = new SapperCell(CellState.CLOSED, false, 0);
            }
        }

        mineCycle:
        for (int m = 0; m < mineCount; m++) {
            int index = RND.nextInt(rowCount * colCount - m);
            int i = 0;
            for (int r = 0; r < rowCount; r++) {
                for (int c = 0; c < colCount; c++) {
                    if (!field[r][c].mine) {
                        if (i == index) {
                            field[r][c].mine = true;
                            continue mineCycle;
                        }
                        i++;
                    }
                }
            }
        }
        this.mineCount = mineCount;

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                field[r][c].mineCountAround = getMineCountAround(r, c);
            }
        }

        state = GameState.PLAYING;
    }


    public void leftMouseClick(int row, int col) {
        int rowCount = getRowCount(), colCount = getColCount();

        if (state != GameState.PLAYING ||
                row < 0 || row >= rowCount ||
                col < 0 || col >= colCount ||
                field[row][col].state == CellState.OPENED || field[row][col].state == CellState.FLAG) {
            return;
        }

        SapperCell cell = field[row][col];
        cell.state = CellState.OPENED;
        if (cell.mine) {
            state = GameState.FAIL;
            return;
        }

        int opened = 1;
        while (opened > 0) {
            opened = 0;
            for (int r = 0; r < rowCount; r++) {
                for (int c = 0; c < colCount; c++) {
                    cell = field[r][c];
                    if (cell.state == CellState.OPENED && cell.mineCountAround == 0) {
                        opened += openAround(r, c);
                    }
                }
            }
        }

        calcState();
    }

    public void rightMouseClick(int row, int col) {
        if (state != GameState.PLAYING ||
                row < 0 || row >= getRowCount() ||
                col < 0 || col >= getColCount() ||
                field[row][col].state == CellState.OPENED) {
            return;
        }

        SapperCell cell = field[row][col];
        if (cell.state == CellState.CLOSED) {
            cell.state = CellState.FLAG;
        } else if (cell.state == CellState.FLAG) {
            cell.state = CellState.PROBLEM;
        } else if (cell.state == CellState.PROBLEM) {
            cell.state = CellState.CLOSED;
        }
    }


    private void calcState() {
        int rowCount = getRowCount(), colCount = getColCount();

        int opened = 0;
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (field[r][c].state == CellState.OPENED) {
                    if (field[r][c].mine) {
                        state = GameState.FAIL;
                        return;
                    }
                    opened++;
                }
            }
        }
        state = (opened == rowCount * colCount - mineCount) ? GameState.WIN : GameState.PLAYING;
    }


    public GameState getState() {
        return state;
    }

    public int getRowCount() {
        return (field == null) ? 0 : field.length;
    }

    public int getColCount() {
        return (field == null) ? 0 : field[0].length;
    }

    public int getMineCount() {
        return mineCount;
    }

    public int getFlagCount() {
        int rowCount = getRowCount(), colCount = getColCount();

        int flagCount = 0;
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if (field[r][c].state == CellState.FLAG) {
                    flagCount++;
                }
            }
        }
        return flagCount;
    }

    public SapperCell getCell(int row, int col) {
        if (field == null ||
                row < 0 || row >= getRowCount() ||
                col < 0 || col >= getColCount()) {
            return null;
        }
        return field[row][col];
    }
}
