package com.company;

import com.company.util.SwingUtils;

import static com.company.ArrayUtils.ArrayUtil.*;
import static com.company.FileSysObjUtils.FileSysObj.readLinesFromFile;
import static com.company.FileSysObjUtils.FileSysObj.writeArrayToFile;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTable tableInput;
    private JButton SaveButton;
    private JSpinner rowCount;
    private JSpinner columnCount;
    private JButton resetTable;
    private JButton genRandomTable;
    private JButton getArrFromFile;
    private JButton cutArray;

    private JFileChooser fileChooserSave;
    private JFileChooser fileChooserOpen;

    private int Row;
    private int Col;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public FrameMain() {
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        fileChooserSave = new JFileChooser();
        fileChooserOpen = new JFileChooser();
        fileChooserSave.setCurrentDirectory(new File("."));
        fileChooserOpen.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserSave.addChoosableFileFilter(filter);
        fileChooserOpen.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        rowCount.addChangeListener( new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // handle click
                Row = (int) rowCount.getValue();
                if (Row < 0 ) {
                    Row = 0;
                    rowCount.setValue(new Object[] {"0"});
                }
            }
        });

        columnCount.addChangeListener( new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // handle click
                Col = (int) columnCount.getValue();
                if (Col < 0 ) {
                    Col = 0;
                    columnCount.setValue(new Object[] {"0"});
                }
            }
        });

        resetTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    createTable(Row, Col);
                    getTableSize(tableInput);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        genRandomTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genRandValues();
            }
        });

        getArrFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] arr = toIntArray(readLinesFromFile(
                            fileChooserOpen.getSelectedFile().getPath()));
                    setArrToJTable(arr);
                    }
            } catch (Exception e1) {
                    SwingUtils.showErrorMessageBox(e1);
                }
            }
        });

        cutArray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int[][] arr  = getArrFromJTable();
                    int[][]slice = findMaxMinElementLocations(arr);

                    setArrToJTable(slice);

                } catch (Exception e2) {
                    SwingUtils.showErrorMessageBox(e2);
                }
            }
        });

        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    writeArrayToFile(getArrFromJTable(), file);
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        }});

    };



    public JTableSize getTableSize( JTable table) {
        JTableSize size = new JTableSize(table.getRowCount(),
                table.getColumnCount());
        return size;
    }


    public void createTable(int countRows, int countCols) {
        tableInput.setModel( new DefaultTableModel());
        DefaultTableModel tableModel = (DefaultTableModel) tableInput.getModel();
                tableModel.setRowCount((countRows > 0) ? countRows : tableInput.getRowCount());
        tableModel.setColumnCount((countCols > 0) ? countCols : tableInput.getColumnCount());
    }

    public int[][] getArrFromJTable() {
        JTableSize size = getTableSize(tableInput);
        int[][] outputArr = new int[size.rows][size.columns];
        DefaultTableModel tableModel = (DefaultTableModel) tableInput.getModel();
        for(int i = 0 ; i < size.rows; i++) {
            for (int j = 0; j < size.columns; j++) {
                outputArr[i][j] = Integer.parseInt((String) tableModel.getValueAt(i,j));
            }
        }
        return outputArr;
    }

    public void setArrToJTable(int[][] arr) {
        int rows = arr.length;
        int columns = arr[0].length;
        if (rows>0 & columns>0) {
            createTable(rows, columns);
            JTableSize size = getTableSize(tableInput);
            DefaultTableModel tableModel = (DefaultTableModel) tableInput.getModel();
            for(int i = 0 ; i < size.rows; i++) {
                for (int j = 0; j < size.columns; j++) {
                    tableModel.setValueAt(String.format("%d",arr[i][j]),i,j);
                }
            }
        }
    }

    public void genRandValues() {
        Random rnd = new Random();
        JTableSize size = getTableSize(tableInput);
        DefaultTableModel tableModel = (DefaultTableModel) tableInput.getModel();
        for(int i = 0 ; i < size.rows; i++) {
            for (int j = 0; j < size.columns; j++) {
                tableModel.setValueAt(String.format("%d",rnd.nextInt(100)), i,j);
            }
        }
    }
}


class JTableSize {
    int rows;
    int columns;

    public void setXY(int x, int y) {
        this.rows = x;
        this.columns =y;
    }
    JTableSize (int rows, int columns) {
        this.setXY(rows,columns);
    }
}