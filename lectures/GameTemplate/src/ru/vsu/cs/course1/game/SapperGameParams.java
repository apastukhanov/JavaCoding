package ru.vsu.cs.course1.game;

/**
 * Класс для хранения параметров игры
 */
public class SapperGameParams {
    private int rowCount;
    private int colCount;
    private int mineCount;

    public SapperGameParams(int rowCount, int colCount, int mineCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.mineCount = mineCount;
    }

    public SapperGameParams() {
        this(10, 10, 10);
    }

    /**
     * @return the colCount
     */
    public int getColCount() {
        return colCount;
    }

    /**
     * @param colCount the colCount to set
     */
    public void setColCount(int colCount) {
        this.colCount = colCount;
    }

    /**
     * @return the rowCount
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * @param rowCount the rowCount to set
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * @return the mineCount
     */
    public int getMineCount() {
        return mineCount;
    }

    /**
     * @param mineCount the mineCount to set
     */
    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }
}
