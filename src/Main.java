import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args){

        double balance = 30;
        double betAmount;
        String[] row;
        String yesOrNo;

        do {
            System.out.println("*********************");
            System.out.println("Welcome to Java Slots");
            System.out.println("*********************");
            betAmount = startGame(balance);
            row = generateSlot();
            balance += getPayout(row, betAmount);

            while(true) {
                scanner.nextLine();
                System.out.print("Do you want to play again? (yes/no): ");
                yesOrNo = scanner.nextLine();
                if(yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("no")){
                    break;
                } else{
                    System.out.println("Invalid answer!");
                }
            }
        }while(yesOrNo.equalsIgnoreCase("yes"));

        System.out.println("\nThanks for playing!");

        scanner.close();
    }

    static double startGame(double balance) {

        System.out.println("Current balance: $" + balance);

        double betAmount;
        while (true) {

            System.out.print("Place your bet amount: ");

            try {
                betAmount = scanner.nextDouble();

                if (betAmount <= 0){
                    System.out.println("Invalid amount of bet!");
                } else if (betAmount > balance){
                    System.out.println("Insufficient balance!");
                } else{
                    System.out.println("Spinning...");
                    break;
                }

            } catch (InputMismatchException e){
                System.out.println("Invalid input!");
                scanner.next();
            }
        }
        return betAmount;
    }

    static String[] generateSlot(){

        String[] symbols = {"🍒", "🍉", "🍋", "🔔", "⭐"};
        String[] row = {symbols[random.nextInt(0,5)],
                symbols[random.nextInt(0,5)],
                symbols[random.nextInt(0,5)]};

        System.out.println("***********");
        System.out.println(row[0] + " | " + row[1] + " | " + row[2]);
        System.out.println("***********");

        return row;
    }

    static double getPayout(String[] row, double betAmount){

        if (row[0].equals(row[1]) && row[1].equals(row[2])){
            switch (row[0]){
                case "🍒" -> betAmount *= 4;
                case "🍋" -> betAmount *= 9;
                case "🍉" -> betAmount *= 14;
                case "🔔" -> betAmount *= 19;
                case "⭐" -> betAmount *= 49;
            }
            System.out.println("You won $" + betAmount);
        } else if (row[0].equals(row[1]) || row[1].equals(row[2])){
            switch (row[1]){
                case "🍒" -> betAmount *= 1;
                case "🍋" -> betAmount *= 2;
                case "🍉" -> betAmount *= 4;
                case "🔔" -> betAmount *= 9;
                case "⭐" -> betAmount *= 19;
            }
            System.out.println("You won $" + betAmount);
        } else if (row[0].equals(row[2]) && row[1].equals("⭐")){
            switch (row[0]){
                case "🍒" -> betAmount *= 4;
                case "🍋" -> betAmount *= 9;
                case "🍉" -> betAmount *= 14;
                case "🔔" -> betAmount *= 19;
            }
            System.out.println("You won $" + betAmount);
        } else{
            System.out.println("You lost!");
            betAmount = -betAmount;
        }

        return betAmount;
    }
}
