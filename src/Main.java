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
            System.out.println("File not found");
            return;
        }

        try {
            String expression = sc.nextLine();
            String[] s = sc.nextLine().split(" ");
            double a = 0, b = 0;
            boolean flag = true;

            try {
                a = Double.parseDouble(s[0]);
                b = Double.parseDouble(s[2]);
            } catch (NumberFormatException ex) {
                System.out.println("Error! Not number");
                flag = false;
                return;
            }
            if ("+-*/".contains(s[1]) && flag) {
                switch (s[1]) {
                    case "+": {
                        System.out.println(a + b);
                        break;
                    }
                    case "-": {
                        System.out.println(a - b);
                        break;
                    }
                    case "*": {
                        System.out.println(a * b);
                        break;
                    }
                    case "/": {
                        try {
                            if (b == 0.0)
                                System.out.println("Error! Division by zero");
                            else
                                System.out.println(a / b);
                        } catch (ArithmeticException ex) {
                            System.out.println("Error! Division by zero");
                        }
                        break;
                    }
                }
            } else
                System.out.println("Operation Error!");
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Error! Empty File or Invalid expression");
        }

        finally {
            if (sc != null){
                sc.close();
            }
        }

    }
}