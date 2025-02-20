/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.randompasswordgenerator;

/**
 *
 * @author Saujanya
 */
import java.util.Random;
import java.util.Scanner;

public class RandomPasswordGenerator {
    public static String generatePassword(int length, boolean useUppercase, boolean useLowercase, boolean useDigits, boolean useSpecial) {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:\\\"',.<>?/";

        
        String charPool = "";
        if (useUppercase) charPool += upperCaseLetters;
        if (useLowercase) charPool += lowerCaseLetters;
        if (useDigits) charPool += digits;
        if (useSpecial) charPool += specialCharacters;
        
        if (charPool.isEmpty()) {
            System.out.println("Error: No character types selected!");
            return null;
        }
        
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(charPool.charAt(random.nextInt(charPool.length())));
        }
        return password.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter password length: ");
        int length = scanner.nextInt();
        
        if (length <= 0) {
            System.out.println("Error: Length must be a positive integer.");
            return;
        }
        
        System.out.print("Include uppercase letters? (y/n): ");
        boolean useUppercase = scanner.next().trim().equalsIgnoreCase("y");
        
        System.out.print("Include lowercase letters? (y/n): ");
        boolean useLowercase = scanner.next().trim().equalsIgnoreCase("y");
        
        System.out.print("Include numbers? (y/n): ");
        boolean useDigits = scanner.next().trim().equalsIgnoreCase("y");
        
        System.out.print("Include special characters? (y/n): ");
        boolean useSpecial = scanner.next().trim().equalsIgnoreCase("y");
        
        String password = generatePassword(length, useUppercase, useLowercase, useDigits, useSpecial);
        
        if (password != null) {
            System.out.println("Generated Password: " + password);
        }
        
        scanner.close();
    }
}

