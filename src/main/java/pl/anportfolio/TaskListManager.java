package pl.anportfolio;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TaskListManager {

    public static ArrayList<String> taskList = new ArrayList<>();
    public static int menuNumber;
    public static final String DATE_REGEX = "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";

    public static void main(String[] args) {
        updateList();
        showMenu();
    }

    public static void updateList() {
        Path path = Paths.get("tasks.csv");
        try {
            taskList.addAll(Files.readAllLines(path));
        } catch (IOException e) {
            System.out.println("Plik nie istnieje");
        }
    }

    public static void showMenu() {
        System.out.println(ConsoleColors.BLUE + "Please type option number");
        System.out.println(ConsoleColors.BLUE + "1" + ConsoleColors.RESET + ". Show Task List");
        System.out.println(ConsoleColors.BLUE + "2" + ConsoleColors.RESET + ". Add Task to List");
        System.out.println(ConsoleColors.BLUE + "3" + ConsoleColors.RESET + ". Remove Task");
        System.out.println(ConsoleColors.BLUE + "4" + ConsoleColors.RESET + ". Save tasks & exit");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Type a number from list");
        }
        menuNumber = scanner.nextInt();
        menuOption();
    }

    public static void menuOption() {

        switch (menuNumber) {
            case 1:
                showTaskList();
                break;
            case 2:
                addTaskToList();
                break;
            case 3:
                removeTask();
                break;
            case 4:
                exit();
                break;
            default:
                System.out.println("Type '1', '2', '3' or '4'");
                showMenu();
        }
    }

    public static void showTaskList() {
        for (String task : taskList) {
            System.out.println(taskList.indexOf(task) + " " + task);
        }
        showMenu();
    }

    public static void addTaskToList() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type task name");
        String task = scanner.nextLine();
        System.out.println("Type task date ('YYYY-MM-DD')");
        String date = scanner.nextLine();
        while (!date.matches(DATE_REGEX)) {
            System.out.println("Date is in wrong format or does not exist in calendar");
            scanner.nextLine();
        }
        System.out.println("Is Your task important? ('true'/'false')");
        while (!scanner.hasNextBoolean()) {
            scanner.nextLine();
            System.out.println("Type 'true' or 'false'");
        }
        boolean importance = scanner.nextBoolean();
        Task task1 = new Task(task, date, importance);
        taskList.add(task1.toString());
        showMenu();
    }

    public static void removeTask() {
        System.out.println("Type task number to remove:");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Type number");
        }
        int indexToRemove = scanner.nextInt();
        try {
            taskList.remove(indexToRemove);
            System.out.println("Task " + ConsoleColors.PURPLE_BOLD + indexToRemove + ConsoleColors.RESET + " removed.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task does not exist");
        }
        showMenu();
    }

    public static void exit() {
        try (FileWriter fileWriter = new FileWriter("tasks.csv", false)) {
            for (String task : taskList) {
                fileWriter.append(task).append("\n");
            }
            System.out.println(ConsoleColors.RED + "Bye, bye");
        } catch (IOException e) {
            System.out.println("Error writing to the file");
        }
    }
}


