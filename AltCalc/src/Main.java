import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Введите выражение");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String output = calculate(input);
            System.out.println(output);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    private static String calculate(String input) {
        input = input.trim();
        Pattern pattern = Pattern.compile("\"([^\"]*)\"\\s*([-+*/])\\s*(\"[^\"]*\"|\\d+)");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Некорректный формат ввода");
        }

        String str1 = matcher.group(1);
        String operator = matcher.group(2);
        String str2OrNumber = matcher.group(3);

        if (str1.length() > 10) {
            throw new IllegalArgumentException("Первая строка не должна быть длиннее 10 символов");
        }

        String result;

        switch (operator) {
            case "+":
                if (!str2OrNumber.startsWith("\"") || !str2OrNumber.endsWith("\"")) {
                    throw new IllegalArgumentException("Вторая строка должна быть в кавычках");
                }
                String str2 = str2OrNumber.substring(1, str2OrNumber.length() - 1);
                result = str1 + str2;
                break;
            case "-":
                if (!str2OrNumber.startsWith("\"") || !str2OrNumber.endsWith("\"")) {
                    throw new IllegalArgumentException("Вторая строка должна быть в кавычках");
                }
                str2 = str2OrNumber.substring(1, str2OrNumber.length() - 1);
                result = str1.replace(str2, "");
                break;
            case "*":
                int number = Integer.parseInt(str2OrNumber);
                if (number < 1 || number > 10) {
                    throw new IllegalArgumentException("Число должно быть между 1 и 10");
                }
                result = str1.repeat(number);
                break;
            case "/":
                number = Integer.parseInt(str2OrNumber);
                if (number < 1 || number > 10) {
                    throw new IllegalArgumentException("Число должно быть между 1 и 10");
                }

                result = str1.substring(0, str1.length() / number);
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция: " + operator);
        }

        return "\"" + truncateResult(result) + "\"";
    }

    private static String truncateResult(String result) {
        if (result.length() > 40) {
            return result.substring(0, 40) + "...";
        }
        return result;
    }
}