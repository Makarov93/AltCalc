
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите выражение в формате \"Операнд1 Действие Операнд2\": ");
        String input = scanner.nextLine();

        String[] tokens = input.split(" ");

        if (tokens.length != 3) {
            throw new IllegalArgumentException("Неверный формат ввода. Пожалуйста, введите выражение в формате: \"Операнд1 Действие Операнд2\"");
        }

        String operand1 = tokens[0];
        String operator = tokens[1];
        String operand2 = tokens[2];

        String result = "";
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1.replace(operand2, "");
                break;
            case "*":
                result = multiply(operand1, operand2);
                break;
            case "/":
                result = div(operand1, operand2);
                break;
            default:
                throw new IllegalArgumentException("Неверный оператор. Поддерживаемые операторы: +, -, *, и /.");
        }

        if (result.length() > 40) {
            result = result.substring(0, 40) + "...";
        }

        System.out.println("Результат: " + result);
    }

    static String multiply(String operand1, String operand2) {
        int digital = 1;

        String word = operand1;
        String result = word;

        try {
            digital = Integer.parseInt(operand2);

        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i < digital; i++) {
            result += word;
        }
        return result;
    }

    static String div(String operand1, String operand2) {
        int digital = 1;
        char[] wordArr = operand1.toCharArray();
        try {
            digital = Integer.parseInt(operand2);

        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        int size = wordArr.length / digital;
        String result = "";
        for (int i = 0; i < size; i++) {
            result += wordArr[i];
        }
        return result;
    }
}