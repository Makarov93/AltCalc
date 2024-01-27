
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        var operands = getOperands(sc.nextLine());

        String result = "";
        switch (operands[1]) {
            case "+":
                result = addition(operands[0], operands[2]);
                break;
            case "-":
                result = subtraction(operands[0], operands[2]);
                break;
            case "*":
                result = multiple(operands[0], operands[2]);
                break;
            case "/":
                result = div(operands[0], operands[2]);
                break;
            default:
                throw new IllegalArgumentException("Неверный оператор. Поддерживаемые операторы: +, -, *, и /.");
        }

        System.out.println(result);

    }

    static String[] getOperands(String input) {
        char[] arr = input.toCharArray();
        String first = "";
        String second = "";
        String operation = "";
        boolean trigger = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '-' && arr[i - 2] == '\"') {
                operation = "-";
                trigger = true;
                continue;
            }
            if (arr[i] == '+') {
                operation = "+";
                trigger = true;
                continue;
            }
            if (arr[i] == '*') {
                operation = "*";
                trigger = true;
                continue;
            }
            if (arr[i] == '/') {
                operation = "/";
                trigger = true;
                continue;
            } else if (!trigger) {
                first += arr[i];
            } else {
                second += arr[i];
            }
        }
        if (first.length() > 13 || second.length() > 13) {
            throw new IllegalArgumentException("Длина операндов не должна превышать 10 символов.");
        }
        return new String[]{first.replaceAll("\" ", "\""), operation, second.replaceAll(" \"", "\"")};
    }

    static String div(String operand1, String operand2) {
        int digital = 1;
        char[] wordArr = operand1.toCharArray();
        try {
            digital = Integer.parseInt(operand2.trim());

        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        int size = wordArr.length / digital;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(wordArr[i]);
        }
        String finalResult = result.toString().replaceAll("\"", "");
        if (finalResult.length() > 40) {
            finalResult = finalResult.substring(0, 40) + "...";
        }
        return "\"" + finalResult + "\"";
    }

    static String multiple(String operand1, String operand2) {
        int digital = 1;

        String word = operand1;
        String result = word;

        try {
            digital = Integer.parseInt(operand2.strip());

        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i < digital; i++) {
            result += word;
        }
        String finalResult = result.replaceAll("\"", "");
        if (finalResult.length() > 40) {
            finalResult = finalResult.substring(0, 40) + "...";
        }
        return "\"" + finalResult + "\"";
    }

    static String addition(String operand1, String operand2) {
        String f = operand1.replaceAll("\"", "");
        String s = operand2.replaceAll("\"", "");
        String result = f + s;
        if (result.length() > 40) {
            result = result.substring(0, 40) + "...";
        }
        return "\"" + result + "\"";
    }

    static String subtraction(String operand1, String operand2) {
        String[] arrF = operand1.split("");
        String[] arrS = operand2.split("");
        for (int i = 0; i < arrS.length; i++) {
            for (int j = 0; j < arrF.length; j++) {
                if (arrF[j].equals(arrS[i])) {
                    arrF[j] = "";
                }
            }
        }
        String res = "";
        for (int i = 0; i < arrF.length; i++) {
            res += arrF[i];
        }
        String finalResult = res.replaceAll("\"", "");
        if (finalResult.length() > 40) {
            finalResult = finalResult.substring(0, 40) + "...";
        }
        return "\"" + finalResult + "\"";
    }
}