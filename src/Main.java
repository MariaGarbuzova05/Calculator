import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        Scanner sc = null;
        try {
            File file = new File("input.txt");
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found.");
            return; // Exit if the file is not found
        }

        try {
            if (!sc.hasNextLine()) {
                System.out.println("Error! Empty File.");
                return;
            }

            String expression = sc.nextLine(); // Read one line from file

            //Check if the expression consists only of white spaces
            if (expression.trim().isEmpty()) {
                System.out.println("Error! Empty Line.");
                return;
            }
            String[] s = expression.split(" "); // Split by spaces
            double a = 0, b = 0;
            boolean flag = true;

            try {
                a = Double.parseDouble(s[0]);
                b = Double.parseDouble(s[2]);
            } catch (NumberFormatException ex) {
                System.out.println("Error! Not number");
                flag = false;
                return;
            }  catch (ArrayIndexOutOfBoundsException ex){
                System.out.println("Error! Invalid expression format. Missing operand.");
                return;
            }

            if ("+-*/".contains(s[1]) && flag) {
                switch (s[1]) {
                    case "+":
                        System.out.println(a + b);
                        break;
                    case "-":
                        System.out.println(a - b);
                        break;
                    case "*":
                        System.out.println(a * b);
                        break;
                    case "/":
                        if (b == 0.0)
                            System.out.println("Error! Division by zero");
                        else
                            System.out.println(a / b);
                        break;
                }
            } else
                System.out.println("Operation Error!");

        } catch (java.util.NoSuchElementException e) {
            System.out.println("Error! Empty File or No line to Read");
        }

        finally {
            if (sc != null) {
                sc.close(); // Close the scanner to release resources
            }
        }
    }
}