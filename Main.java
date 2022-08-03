import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static int number1, number2;
    static int result;
    static char operation;
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите значения операции от 1 до 10 или от I до X и знак операции: +, -, *, /, плюс Enter");
        String input = reader.nextLine();
        reader.close();
        System.out.println(calc(input));
    }
    public static String calc(String input) throws ArrayIndexOutOfBoundsException{
        char[] userChar = new char[10];
        for (int a = 0; a < input.length(); a++) {
            userChar[a] = input.charAt(a);
            if (userChar[a] == '+') {
                operation = '+';
            }
            if (userChar[a] == '-') {
                operation = '-';
            }
            if (userChar[a] == '*') {
                operation = '*';
            }
            if (userChar[a] == '/') {
                operation = '/';
            }
        }
        String userCharString = String.valueOf(userChar);
        String[] blocks = userCharString.split("[+-/*]");
        String primitive0 = blocks[0];
        String exception = "Строка не является математической операцией";
        try {
            blocks[1] = String.valueOf(blocks[1]);
        }catch (ArrayIndexOutOfBoundsException e){
            return exception;
        }
        String primitive1 = blocks[1];
        String stringGap = primitive1.trim();
        number1 = convertRomanToArab(primitive0);
        number2 = convertRomanToArab(stringGap);
        if (blocks.length > 2) {
            throw new ArrayIndexOutOfBoundsException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (number1 > 0 && number2 == -1)
            throw new NumberFormatException("Используются одновременно разные системы счисления");
        if (number2 > 0 && number1 == -1)
            throw new NumberFormatException("Используются одновременно разные системы счисления");
        if (number1 == -1 || number2 == -1) {
            number1 = Integer.parseInt(primitive0);
            number2 = Integer.parseInt(stringGap);
            result = calculate(number1, number2, operation);
            System.out.println("Результат для арабских цифр");
            return Integer.toString(result);
        } else {
            result = calculate(number1, number2, operation);
            if (result <= 0)
                throw new ArrayIndexOutOfBoundsException("В римской системе нет отрицательных чисел");
            else System.out.println("Результат для римских цифр");
            return convertNumberToRoman(result);
        }
    }
    private static String convertNumberToRoman (int numberArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        return roman[numberArabian];
    }
    static int convertRomanToArab (String roman) {
        try {
            switch (roman) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат значений");
        }
        return -1;
    }
    static int calculate (int number01, int number02, char operation01) {
        if (number01 <= 0 || number01 > 10 || number02 <= 0 || number02 > 10)
            throw new IllegalArgumentException("Невенрый формат значений");
        int result = 0;
        switch (operation01) {
            case '+':
                result = number01 + number02;
                break;
            case '-':
                result = number01 - number02;
                break;
            case '*':
                result = number01 * number02;
                break;
            case  '/':
                try {
                    result = number01 / number02;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Исключение : ");
                    System.out.println("Принимаются только целые числа");
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Неверный знак");
        }
        return result;
    }
}
