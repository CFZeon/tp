package ui.diet.dietsession;

import java.util.Scanner;

public class DietSessionUi {

    public void printOpening() {
        System.out.println("Starting Diet Session!");
    }

    public void printExit() {
        System.out.println("you have exited Diet Session.");
    }

    public String getInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
