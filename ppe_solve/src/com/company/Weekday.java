package com.company;

import java.lang.Math;
import java.util.Arrays;

import java.util.Scanner;


public class Weekday {

    public static boolean calcweekday(int day, int month) {

        // задаем  2021
        // так как праздники только на 2021 год указаны
        int year = 2021;

        // кусок кода из инета
        // https://www.hackerearth.com/blog/developers/how-to-find-the-day-of-a-week/
        int[] t = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
        if (month < 3) {--year;}
        int dayoftheweek = (year + Math.round(year / 4) -
                Math.round(year / 100) + Math.round(year / 400) + t[month - 1] + day) % 7;

        // массив дней недели, чтобы вывести день недели (для проверки удобно)
        String[] weekdays = {"Вск", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"};

        // сначала проверяем - выпадает ли день на сб или на вск
        if (dayoftheweek == 6 || dayoftheweek == 0) {
            System.out.println("выходной, " + weekdays[dayoftheweek]);
            // если выпадает то выводим результат
            // и заканчиваем выполнение программы
            return true;  // завершаем выполнение кода если попала дата
        }

        // проверяем какой месяц задан и выпадает ли день на праздник
        switch (month) {
            //первый месяц - январь
            // праздники НГ и Рождество
            case 1:
                int[] holidays = {1, 4, 5, 6, 7, 8};
                if (Arrays.stream(holidays).anyMatch(n -> n == day)) {
                    System.out.println("выходной, " + weekdays[dayoftheweek]);
                    return true; // завершаем выполнение кода если попала дата
                }

                //второй месяц - февраль
                // выходные 22 и 23 февраля
            case 2:
                holidays = new int[]{22, 23};
                if (Arrays.stream(holidays).anyMatch(n -> n == day)) {
                    System.out.println("выходной, " + weekdays[dayoftheweek]);
                    return true; // завершаем выполнение кода если попала дата
                }
                //март - 8 марта праздник
            case 3:
                if (day == 8) {
                    System.out.println("выходной, " + weekdays[dayoftheweek]);
                    return true; // завершаем выполнение кода если попала дата
                }
                // май - майские празники
            case 5:
                holidays = new int[]{3, 10};
                if (Arrays.stream(holidays).anyMatch(n -> n == day)) {
                    System.out.println("выходной, " + weekdays[dayoftheweek]);
                    return true; // завершаем выполнение кода если попала дата
                }

                //июнь - день флага
            case 6:
                if (day == 14) {
                    System.out.println("выходной, " + weekdays[dayoftheweek]);
                    return true; // завершаем выполнение кода если попала дата
                }
                // ноябрь - день народного едниства
            case 11:
                holidays = new int[]{4, 5};
                if (Arrays.stream(holidays).anyMatch(n -> n == day)) {
                    System.out.println("выходной, " + weekdays[dayoftheweek]);
                    return true; // завершаем выполнение кода если попала дата
                }
                // декарбрь новый год
            case 12:
                if (day == 31) {
                    System.out.println("выходной, " + weekdays[dayoftheweek]);
                    return true; // завершаем выполнение кода если попала дата
                }

        }

        // если ни одна из предыдущих проверок не прошла
        // то значит день - рабочий
        System.out.println("рабочий день, " + weekdays[dayoftheweek]);

        //завершаем выполнение фукнции
        return false;
    }

    public static void main(String[] args) {

        // класс чтобы считать ввод консоли
        Scanner in = new Scanner(System.in);

        // стоп слово для завершения цикла y - да (завершаем цикл),
        // n - нет (продолжаем цикл)
        String stop = "y";

        // цикл для проверки нескольких дат
        while (!stop.equals("n")) {

            // приглашаем юзера ввести дату и месяц
            System.out.println("Введи дату и месяц через пробел, например: 31 12");

            // записываем ввод из консоли в переменную s
            String s = in.nextLine();

            // парсим введеные через пробел дату и месяц
            String[] strArray1 = s.split(" ");

            // в задании нужно было сделать в виде функции
            // функция возвращает boolean значение - true or false
            calcweekday(Integer.parseInt(strArray1[0]), Integer.parseInt(strArray1[1]));

            // спрашиваем далее выполнять или нет
            System.out.print("Продолжить? y/n ");
            // записываем ввод из консоли в переменную stop
            stop = in.nextLine();
        }
    }
}