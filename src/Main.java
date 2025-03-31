import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = null;
        FileWriter writer = null;  // Declare FileWriter here

        try {
            // Attempt to read from file "input.txt"
            File file = new File("input.txt");
            sc = new Scanner(file);

            // Create FileWriter to write to output.txt
            writer = new FileWriter("output.txt");


            if (!sc.hasNextLine()) {
                String errorMessage = "Error! Empty File.";
                System.out.println(errorMessage);
                writeToFile(writer, errorMessage);
                return;
            }

            String expression = sc.nextLine(); // Read one line from file

            //Check if the expression consists only of white spaces
            if (expression.trim().isEmpty()) {
                String errorMessage = "Error! Empty Line.";
                System.out.println(errorMessage);
                writeToFile(writer, errorMessage);
                return;
            }
            String[] s = expression.split(" "); // Split by spaces
            double a = 0, b = 0;
            boolean flag = true;

            try {
                a = Double.parseDouble(s[0]);
                b = Double.parseDouble(s[2]);
            } catch (NumberFormatException ex) {
                String errorMessage = "Error! Not number";
                System.out.println(errorMessage);
                writeToFile(writer, errorMessage);
                flag = false;
                return;
            } catch (ArrayIndexOutOfBoundsException ex){
                String errorMessage = "Error! Invalid expression format. Missing operand.";
                System.out.println(errorMessage);
                writeToFile(writer, errorMessage);
                return;
            }

            if ("+-*/".contains(s[1]) && flag) {
                double result = 0;
                String operationString = "";
                switch (s[1]) {
                    case "+":
                        result = a + b;
                        operationString = String.valueOf(a + b);
                        break;
                    case "-":
                        result = a - b;
                        operationString = String.valueOf(a - b);
                        break;
                    case "*":
                        result = a * b;
                        operationString = String.valueOf(a * b);
                        break;
                    case "/":
                        if (b == 0.0){
                            String errorMessage = "Error! Division by zero";
                            System.out.println(errorMessage);
                            writeToFile(writer, errorMessage);
                            return;
                        }
                        else {
                            result = a / b;
                            operationString = String.valueOf(a / b);
                        }
                        break;
                }
                System.out.println(operationString);
                writeToFile(writer, operationString);

            } else {
                String errorMessage = "Operation Error!";
                System.out.println(errorMessage);
                writeToFile(writer, errorMessage);
            }


        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found.");

        }   catch (java.util.NoSuchElementException e) {
            System.out.println("Error! Empty File or No line to Read");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        finally {
            if (sc != null) {
                sc.close(); // Close the scanner to release resources
            }
            if (writer != null) {
                try {
                    writer.close(); // Close the writer
                } catch (IOException e) {
                    System.out.println("Error closing writer: " + e.getMessage());
                }
            }
        }
    }

    //Helper Method
    private static void writeToFile(FileWriter writer, String text) {
        try {
            writer.write(text + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}