/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.rockpaperscissors;

/**
 *
 * @author Saujanya
 */
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] choices = {"rock", "paper", "scissors"};
        
        while (true) {
            System.out.print("Enter rock, paper, or scissors (or 'quit' or 'exit' to stop): ");
            String userChoice = scanner.nextLine().toLowerCase();
            
            if (userChoice.equals("quit") || userChoice.equals("exit")) {
                System.out.println("Thanks for playing! Goodbye.");
                break;
            }
            
            if (!userChoice.equals("rock") && !userChoice.equals("paper") && !userChoice.equals("scissors")) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            
            String computerChoice = choices[random.nextInt(3)];
            System.out.println("Computer chose: " + computerChoice);
            
            if (userChoice.equals(computerChoice)) {
                System.out.println("It's a tie!");
            } else if (
                (userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                (userChoice.equals("scissors") && computerChoice.equals("paper"))
            ) {
                System.out.println("You win!");
            } else {
                System.out.println("Computer wins!");
            }
        }
        
        scanner.close();
    }
}

