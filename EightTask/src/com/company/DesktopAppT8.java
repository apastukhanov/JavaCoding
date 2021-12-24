package com.company;

import com.company.util.SwingUtils;

import javax.swing.*;

import java.util.Locale;

public class DesktopAppT8 {

     /*   31.	Создать новый двумерный массив чисел,
                исключив из переданного двумерного массива все строки и столбцы,
                содержащие максимальные и минимальные элементы
                среди всех элементов переданного массива.

                2) в виде оконного приложения, где двумерный массив можно задать в JTable.

      */

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        //SwingUtils.setLookAndFeelByName("Windows");
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }
}
