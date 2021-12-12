package ru.vsu.cs.course1.sapper;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import ru.vsu.cs.util.DrawUtils;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

public class MainForm extends JFrame {
    private JPanel panelMain;
    private JTable tableGameField;
    private JLabel labelTime;
    private JButton buttonNewGame;
    private JLabel labelMineCount;
    private JPanel panelTop;
    private JLabel labelStatus;
    private JPanel panelTime;
    private JPanel panelMineCount;

    private static final int DEFAULT_COL_COUNT = 20;
    private static final int DEFAULT_ROW_COUNT = 15;
    private static final int DEFAULT_MINE_COUNT = 40;

    private static final int DEFAULT_GAP = 10;
    private static final int DEFAULT_CELL_SIZE = 30;
    // private static final int DEFAULT_NEW_GAME_BUTTON_SIZE = 50;

    private static final Color[] MINE_COUNT_COLORS = {
            Color.BLUE,
            new Color(0, 128, 0),    // green
            Color.RED,
            new Color(0, 0, 128),    // navy
            new Color(128, 0, 0),    // maroon
            new Color(0, 128, 128),  // teal
            Color.BLACK,
            Color.LIGHT_GRAY
    };

    private static final SapperGame.SapperCell
            NULL_CELL = new SapperGame.SapperCell(SapperGame.CellState.CLOSED, false, 0);

    private SapperGameParams params = new SapperGameParams(DEFAULT_ROW_COUNT, DEFAULT_COL_COUNT, DEFAULT_MINE_COUNT);
    private SapperGame game = new SapperGame();

    private int time = 0;
    private Timer timer = new Timer(1000, e -> {
        if (game.getState() == SapperGame.GameState.PLAYING) {
            time++;
            this.labelTime.setText("" + time);
        }
    });

    private ParamsDialog dialogParams;


    public MainForm() {
        this.setTitle("Сапер");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        setJMenuBar(createMenuBar());
        this.pack();

        SwingUtils.setShowMessageDefaultErrorHandler();

        labelTime.setFont(new Font("Comic Sans MS", Font.PLAIN, labelTime.getFont().getSize()));
        labelTime.setForeground(new Color(0, 0, 128));
        panelTime.setBackground(Color.LIGHT_GRAY);
        labelMineCount.setFont(new Font("Comic Sans MS", Font.PLAIN, labelMineCount.getFont().getSize()));
        labelMineCount.setForeground(new Color(128, 0, 0));
        panelMineCount.setBackground(Color.LIGHT_GRAY);

        tableGameField.setRowHeight(DEFAULT_CELL_SIZE);
        JTableUtils.initJTableForArray(tableGameField, DEFAULT_CELL_SIZE, false, false, false, false);
        tableGameField.setIntercellSpacing(new Dimension(0, 0));
        tableGameField.setEnabled(false);

        tableGameField.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            final class DrawComponent extends Component {
                private int row = 0, column = 0;

                @Override
                public void paint(Graphics gr) {
                    Graphics2D g2d = (Graphics2D) gr;
                    int width = getWidth() - 2;
                    int height = getHeight() - 2;
                    paintCell(row, column, g2d, width, height);
                }
            }

            DrawComponent comp = new DrawComponent();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                comp.row = row;
                comp.column = column;
                return comp;
            }
        });

        newGame();

        updateWindowSize();
        updateView();
        buttonNewGame.addActionListener(e -> {
            newGame();
        });

        dialogParams = new ParamsDialog(params, tableGameField, e -> newGame());

        tableGameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = tableGameField.rowAtPoint(e.getPoint());
                int col = tableGameField.columnAtPoint(e.getPoint());
                if (SwingUtilities.isLeftMouseButton(e)) {
                    game.leftMouseClick(row, col);
                    updateView();
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    game.rightMouseClick(row, col);
                    updateView();
                }
            }
        });
    }

    private JMenuItem createMenuItem(String text, String shortcut, Character mnemonic, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(listener);
        if (shortcut != null) {
            menuItem.setAccelerator(KeyStroke.getKeyStroke(shortcut.replace('+', ' ')));
        }
        if (mnemonic != null) {
            menuItem.setMnemonic(mnemonic);
        }
        return menuItem;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBarMain = new JMenuBar();

        JMenu menuGame = new JMenu("Игра");
        menuBarMain.add(menuGame);
        menuGame.add(createMenuItem("Новая", "ctrl+N", null, e -> {
            newGame();
        }));
        menuGame.add(createMenuItem("Параметры", "ctrl+P", null, e -> {
            dialogParams.updateView();
            dialogParams.setVisible(true);
        }));
        menuGame.addSeparator();
        menuGame.add(createMenuItem("Выход", "ctrl+X", null, e -> {
            System.exit(0);
        }));

        JMenu menuView = new JMenu("Вид");
        menuBarMain.add(menuView);
        menuView.add(createMenuItem("Подогнать размер окна", null, null, e -> {
            updateWindowSize();
        }));
        menuView.addSeparator();
        SwingUtils.initLookAndFeelMenu(menuView);

        JMenu menuHelp = new JMenu("Справка");
        menuBarMain.add(menuHelp);
        menuHelp.add(createMenuItem("Правила", "ctrl+R", null, e -> {
            SwingUtils.showInfoMessageBox("Классический вариант игры «Сапер» ...", "Правила");
        }));
        menuHelp.add(createMenuItem("О программе", "ctrl+A", null, e -> {
            SwingUtils.showInfoMessageBox(
                    "Игра «Сапер»" +
                            "\n– пример логической игры для 1-го курса ФКН ВГУ" +
                            "\n\nАвтор: Соломатин Д.И." +
                            "\nE-mail: solomatin.cs.vsu.ru@gmail.com",
                    "О программе"
            );
        }));

        return menuBarMain;
    }

    private void updateWindowSize() {
        int menuSize = this.getJMenuBar() != null ? this.getJMenuBar().getHeight() : 0;
        SwingUtils.setFixedSize(
                this,
                tableGameField.getWidth() + 2 * DEFAULT_GAP + 60,
                tableGameField.getHeight() + panelMain.getY() + panelTop.getHeight() + labelStatus.getHeight() +
                        menuSize + 3 * DEFAULT_GAP + 2 * DEFAULT_GAP + 60
        );
        this.setMaximumSize(null);
        this.setMinimumSize(null);
    }

    private void updateView() {
        labelMineCount.setText("" + (game.getMineCount() - game.getFlagCount()));
        labelTime.setText("" + time);
        tableGameField.repaint();
        if (game.getState() == SapperGame.GameState.PLAYING) {
            labelStatus.setForeground(Color.BLACK);
            labelStatus.setText("Игра идет");
        } else {
            timer.stop();
            labelStatus.setText("");
            if (game.getState() == SapperGame.GameState.WIN) {
                labelStatus.setForeground(Color.RED);
                labelStatus.setText("Победа :-)");
            } else if (game.getState() == SapperGame.GameState.FAIL) {
                labelStatus.setForeground(Color.RED);
                labelStatus.setText("Поражение :-(");
            }
        }
    }


    private Font font = null;

    private Font getFont(int size) {
        if (font == null || font.getSize() != size) {
            font = new Font("Comic Sans MS", Font.BOLD, size);
        }
        return font;
    }

    private void paintCell(int row, int column, Graphics2D g2d, int cellWidth, int cellHeight) {
        SapperGame.SapperCell cell = game.getCell(row, column);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (cell == null) {
            cell = NULL_CELL;
        }
        int size = Math.min(cellWidth, cellHeight);

        Color backColor = Color.LIGHT_GRAY;
        if (cell.getState() == SapperGame.CellState.OPENED /*||
                (game.getState() == SapperGame.GameState.WIN || game.getState() == SapperGame.GameState.FAIL) &&
                        cell.isMine() && cell.getState() != SapperGame.CellState.FLAG*/) {
            backColor = Color.WHITE;
            if (cell.getState() == SapperGame.CellState.OPENED && cell.isMine()) {
                backColor = Color.RED;
            }
        }
        g2d.setColor(backColor);
        g2d.fillRect(0, 0, size, size);

        Color color = DrawUtils.getContrastColor(backColor);
        char ch = ' ';
        if (game.getState() == SapperGame.GameState.FAIL && cell.isMine()) {
            ch = '*';
        } else {
            if (cell.getState() == SapperGame.CellState.OPENED) {
                if (cell.getMineCountAround() > 0) {
                    ch = (char) ('0' + cell.getMineCountAround());
                    color = MINE_COUNT_COLORS[cell.getMineCountAround() - 1];
                }
            } else if (cell.getState() == SapperGame.CellState.FLAG) {
                ch = '!';
                color = Color.RED;
            } else if (cell.getState() == SapperGame.CellState.PROBLEM) {
                ch = '?';
                color = Color.RED;
            }
        }
        g2d.setColor(color);
        int bound = (int) Math.round(size * 0.1);
        Font font = getFont(size - 2 * bound);
        DrawUtils.drawStringInCenter(g2d, font, "" + ch, 0, 0, cellWidth, (int) Math.round(cellHeight * 0.95));
    }

    private void newGame() {
        game.newGame(params.getRowCount(), params.getColCount(), params.getMineCount());
        JTableUtils.resizeJTable(tableGameField,
                game.getRowCount(), game.getColCount(),
                tableGameField.getRowHeight(), tableGameField.getRowHeight()
        );
        time = 0;
        timer.start();
        updateView();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(3, 1, new Insets(10, 10, 10, 10), -1, 10));
        panelTop = new JPanel();
        panelTop.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelTop, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelTime = new JPanel();
        panelTime.setLayout(new GridLayoutManager(1, 1, new Insets(0, 5, 0, 5), -1, -1));
        panelTop.add(panelTime, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(100, -1), null, 0, false));
        panelTime.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        labelTime = new JLabel();
        Font labelTimeFont = this.$$$getFont$$$(null, -1, 36, labelTime.getFont());
        if (labelTimeFont != null) labelTime.setFont(labelTimeFont);
        labelTime.setHorizontalTextPosition(4);
        labelTime.setText("0");
        panelTime.add(labelTime, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelMineCount = new JPanel();
        panelMineCount.setLayout(new GridLayoutManager(1, 1, new Insets(0, 5, 0, 5), -1, -1));
        panelTop.add(panelMineCount, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(100, -1), null, 0, false));
        panelMineCount.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        labelMineCount = new JLabel();
        Font labelMineCountFont = this.$$$getFont$$$(null, -1, 36, labelMineCount.getFont());
        if (labelMineCountFont != null) labelMineCount.setFont(labelMineCountFont);
        labelMineCount.setHorizontalTextPosition(4);
        labelMineCount.setText("0");
        panelMineCount.add(labelMineCount, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonNewGame = new JButton();
        buttonNewGame.setFocusable(false);
        buttonNewGame.setText("Новая игра");
        panelTop.add(buttonNewGame, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelTop.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelTop.add(spacer2, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panelMain.add(scrollPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableGameField = new JTable();
        scrollPane1.setViewportView(tableGameField);
        labelStatus = new JLabel();
        labelStatus.setText("Label");
        panelMain.add(labelStatus, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}
