import java.util.Scanner;
public class Calc {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            try {
                String result = calc(input);
                System.out.println(result);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка " + e.getMessage());
            }
        }
        public static String calc(String input) {
            String[] parts = input.split(" ");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Неверный формат выражения");
            }
            boolean isRomanA = isRoman(parts[0]);
            boolean isRomanB = isRoman(parts[2]);

            if (isRomanA != isRomanB) {
                throw new IllegalArgumentException("Неверный формат выражения");
            }

            int a = parseNumber(parts[0]);
            int b = parseNumber(parts[2]);

            checkRange(a);
            checkRange(b);

            int result;
            switch (parts[1]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    result = a / b;
                    break;
                default:
                    throw new IllegalArgumentException("Недопустимая операция");
            }

            return formatResult(result, isRoman(parts[0]) && isRoman(parts[2]));
        }
        private static int parseNumber(String input) {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                return parseRoman(input);
            }
        }
        private static int parseRoman(String input) {
            switch (input) {
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
                default:
                    throw new IllegalArgumentException("Недопустимое число: " + input);
            }
        }
        private static void checkRange(int number) {
            if (number < 1 || number > 10) {
                throw new IllegalArgumentException("Число не входит в допустимый диапазон: " + number);
            }
        }
        private static boolean isRoman(String input) {
            return input.matches("[IVX]+");
        }
        private static String formatResult(int number, boolean isRoman) {
            if (isRoman) {
                if (number <= 0) {
                    throw new IllegalArgumentException("Отрицательное или нулевое римское число");
                }
                return toRoman(number);
            } else {
                return String.valueOf(number);
            }
        }
        private static String toRoman(int number) {
            String[] romanSymbols = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
            int[] romanValues = {1, 4, 5, 9, 10, 40, 50, 90, 100};

            StringBuilder result = new StringBuilder();
            int i = romanValues.length - 1;
            while (number > 0) {
                if (number >= romanValues[i]) {
                    result.append(romanSymbols[i]);
                    number -= romanValues[i];
                } else {
                    i--;
                }
            }
            return result.toString();
        }
}