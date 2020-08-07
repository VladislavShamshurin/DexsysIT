package ru.test.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final List<Integer> rem_of_3_x = new ArrayList<>();
    public static final List<Integer> rem_of_7_s = new ArrayList<>();
    public static final List<Integer> rem_of_21_m = new ArrayList<>();
    public boolean isIncluded = false;

    /**
     * @param array - массив, инициализирующий списки.
     * Инициализация списоков набором значений array
     */
    public void init(int[] array) {
        for(Integer i : array) {
            if (i % 3 == 0) rem_of_3_x.add(i);
            if (i % 7 == 0) rem_of_7_s.add(i);
            if (i % 21 == 0) rem_of_21_m.add(i);
            if (i % 3 != 0 && i % 7 != 0) isIncluded = true;
        }
        Collections.sort(rem_of_3_x);
        Collections.sort(rem_of_7_s);
        Collections.sort(rem_of_21_m);
        System.out.println("Списки инициализированы!");
    }

    /**
     * Реализация цикла с командами.
     */
    public Main() {
        try {
            String command = " ";
            while(!command.equals("")) {
                command = reader.readLine().toLowerCase();
               if (command.contains("help")) {
                   help();
               } else if (command.contains("init")) {
                   //Реализовал на своё усмотрение. Вводить любые целочисленные переменные.
                   init(new int[] {5, 12, 13, 14, 15, 18, 63, 11, 88, 33, 99});
               } else if (command.contains("print")) {
                   commandPrint(command);
               } else if (command.contains("anymore")) {
                   anyMore();
               } else if (command.contains("clear")) {
                   commandClear(command);
               } else if (command.contains("merge")) {
                   merge();
               }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }


    }

    /**
     * @param command - строка, содержащаю команду "clear"
     * Метод, реализующий команду "clear"
     */
    public void commandClear(String command) {
        String[] stringGap = command.split(" ");
        switch (stringGap[1].toLowerCase()) {
            case "x":
                clear(rem_of_3_x);
                break;
            case "s":
                clear(rem_of_7_s);
                break;
            case "m":
                clear(rem_of_21_m);
                break;
        }
    }

    /**
     * @param command - строка, содержащая команду "print"
     * Метод реализует команду "print"
     */
    public void commandPrint(String command) {
        String[] stringGap = command.split(" ");
        if (stringGap.length == 2) {
            switch (stringGap[1].toLowerCase()) {
                case "x":
                    print(rem_of_3_x, "X");
                    break;
                case "s":
                    print(rem_of_7_s, "S");
                    break;
                case "m":
                    print(rem_of_21_m, "M");
                    break;
            }
        } else {
            print();
        }
    }

    /**
     * Слияние всех списков.
     */
    public void merge() {
        List<Integer> merged = new ArrayList<>();
        merged.addAll(rem_of_3_x);
        merged.addAll(rem_of_7_s);
        merged.addAll(rem_of_21_m);
        Collections.sort(merged);
        print(merged, "Merge");
        clear(rem_of_3_x);
        clear(rem_of_7_s);
        clear(rem_of_21_m);
    }

    /**
     * @param type - список, который нужно вывести. Принимает значения X, S, M
     * @param name - имя списка.
     */
    public void print(List<Integer> type, String name) {
        if (type.size() != 0) {
            System.out.println(type);
        } else {
            System.out.println("Список " + name + " пуст!");
        }
    }

    /**
     * Выводит сообщение о том, были ли значения, не вошедшие ни в один список
     */
    public void anyMore() {
        System.out.println(isIncluded);
    }

    /**
     * @param type - список, который нужно отчистить.
     */
    public void clear(List<Integer> type) {
        type.clear();
        System.out.println("Список очищен!");
    }

    public void print() {
        print(rem_of_3_x, "X");
        print(rem_of_7_s, "S");
        print(rem_of_21_m, "M");
    }

    /**
     * Вывод справки по командам.
     */
    public void help() {
        System.out.println("init array - инициализация всех списков набором значений array.");
        System.out.println("print - печать всех списков.");
        System.out.println("print type - печать конкретного списка, где type принимает значения X, S, M.");
        System.out.println("anyMore - выводит на экран: были ли значения, не вошедшие ни в один список. (true/false)");
        System.out.println("clear type - чистка списка, где type принимает значения X, S, M");
        System.out.println("merge - слить все списки в один, вывести на экран и отчистить");
    }

    public static void main(String[] args) {
        new Main();
    }
}
