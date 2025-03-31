import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = null;
        FileWriter writer = null;

        try {
            // Attempt to read from file "input.txt"
            File file = new File("input.txt");
            sc = new Scanner(file);

            // Create FileWriter to write to output.txt
            writer = new FileWriter("output.txt");

            while(sc.hasNextLine()) {
                String expression = sc.nextLine(); // Read one line from file
                String outputString = "";

                //Check if the expression consists only of white spaces
                if (expression.trim().isEmpty()) {
                    outputString = expression + " = Error! Empty Line.";
                    System.out.println(outputString);
                }
                else {
                    String[] s = expression.split(" "); // Split by spaces
                    double a = 0, b = 0;
                    boolean flag = true;

                    try {
                        a = Double.parseDouble(s[0]);
                        b = Double.parseDouble(s[2]);
                    } catch (NumberFormatException ex) {
                        outputString = expression + " = Error! Not number";
                        System.out.println(outputString);
                        flag = false;

                    } catch (ArrayIndexOutOfBoundsException ex) {
                        outputString = expression + " = Error! Invalid expression format. Missing operand.";
                        System.out.println(outputString);

                    }

                    if ("+-*/".contains(s[1]) && flag) {
                        double result = 0;

                        switch (s[1]) {
                            case "+":
                                result = a + b;
                                outputString = expression + " = " + (a + b);
                                break;
                            case "-":
                                result = a - b;
                                outputString = expression + " = " + (a - b);
                                break;
                            case "*":
                                result = a * b;
                                outputString = expression + " = " + (a * b);
                                break;
                            case "/":
                                if (b == 0.0) {
                                    outputString = expression + " = Error! Division by zero";
                                } else {
                                    result = a / b;
                                    outputString = expression + " = " + (a / b);
                                }
                                break;
                        }
                        if(outputString.isEmpty()){
                            outputString = expression + " =  Operation Error!";
                        }
                        System.out.println(outputString);

                    } else {
                        outputString = expression + " = Operation Error!";
                        System.out.println(outputString);
                    }
                }
                writeToFile(writer, outputString);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found.");
        }   catch (java.util.NoSuchElementException e) {
            System.out.println("Error! No line to Read");
        }catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
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
    private static void writeToFile(FileWriter writer, String text) throws IOException {
        writer.write(text + "\n");

    }
}