import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws incorrectDataEntered {
        Scanner inputValue = new Scanner(System.in);
        System.out.println("Введите математическое выражение: ");
        String input = inputValue.nextLine();
        System.out.println("Результат математического выражения: " + calc(input));
    }

    public static String calc(String input) throws incorrectDataEntered {

        int result;
        int valueOne;
        int valueTwo;
        boolean isArabicNumber = true;
        String romesResult = "";

        // Создаю и проверяю длину массива, она должна быть строго равна 3

        String[] inputToArr = input.split(" ");
        if (inputToArr.length != 3) {
            throw new incorrectDataEntered("Введено больше или меньше 2-х чисел");
        }

        // Пытаюсь из 1 и 3 элемента массива получить арабские цифры
        try {
            valueOne = Integer.parseInt(inputToArr[0]);
            valueTwo = Integer.parseInt(inputToArr[2]);

            //Если получить не удалось, пробую узнать, может там Римские?

        } catch (NumberFormatException e) {
            isArabicNumber = false;
            String romeValueOne = inputToArr[0];
            String romeValueTwo = inputToArr[2];
            valueOne = ProcessingOfRomanNumerals.convert(romeValueOne);// конвертируем Римское число в Арабское
            valueTwo = ProcessingOfRomanNumerals.convert(romeValueTwo);// конвертируем Римское число в Арабское
        }

        // проверяю, числа должны быть в диапазоне 1...10

        if ((valueOne < 1 || valueOne > 10) || (valueTwo < 1 || valueTwo > 10)) {
            throw new incorrectDataEntered("Вы ввели недопустимое число, разрешен диапазон 1...10");
        }

        String arithmeticOperation = inputToArr[1];

        // Получаю результат математического выражения

        result = calculating(valueOne, valueTwo, arithmeticOperation);

        //проверяю, если были введены Римские числа, и результат <= 0, то выбрасывается ошибка
        if (isArabicNumber) {
            return String.valueOf(result);
        } else if ((!isArabicNumber) && (result <= 0)) {
            throw new incorrectDataEntered("Результат математического выражения с Римскими числами не может быть равен 0 или отрицательному числу");
        } else if (result == 100) { // если результат 100, то сразу вывожу в консоль.
            return "C";
        } else {
            //результат от 1 до 99 перевожу в римские с помощью метода
            romesResult = ProcessingOfRomanNumerals.convertingComplexArabicResultToRomesResult(result);
            return romesResult;
        }
    }

    static int calculating(int valueOne, int valueTwo, String arithmeticOperation) throws incorrectDataEntered {
        int result = 0;

        switch (arithmeticOperation) {
            case "-":
                return result = valueOne - valueTwo;
            case "+":
                return result = valueOne + valueTwo;
            case "*":
                return result = valueOne * valueTwo;
            case "/":
                return result = valueOne / valueTwo;
            default:
                throw new incorrectDataEntered("Вы ввели недопустимый оператор");
        }
    }

    static class incorrectDataEntered extends Exception {
        public incorrectDataEntered(String description) {
            super(description);
        }
    }
}

class ProcessingOfRomanNumerals {


    public static int convert(String arabicValue) throws Main.incorrectDataEntered {
        return switch (arabicValue) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> {
                throw new Main.incorrectDataEntered("Вы ввели недопустимое или неверное римское число, или сочетание арабского и римского числа.");
            }
        };
    }

    static String convertingSimpleArabicResultToRomesResult(int result) {
        String romesResult = "";
        switch (result) {
            case 1:
                romesResult = "I";
            case 2:
                romesResult = "II";
            case 3:
                romesResult = "III";
            case 4:
                romesResult = "IV";
            case 5:
                romesResult = "V";
            case 6:
                romesResult = "VI";
            case 7:
                romesResult = "VII";
            case 8:
                romesResult = "VIII";
            case 9:
                romesResult = "IX";
        }
        return romesResult;
    }

    static String convertingComplexArabicResultToRomesResult(int result) {
        String simpleRomesResult = "";
        if (result < 10) {
            switch (result) {
                case 1 -> simpleRomesResult = "I";
                case 2 -> simpleRomesResult = "II";
                case 3 -> simpleRomesResult = "III";
                case 4 -> simpleRomesResult = "IV";
                case 5 -> simpleRomesResult = "V";
                case 6 -> simpleRomesResult = "VI";
                case 7 -> simpleRomesResult = "VII";
                case 8 -> simpleRomesResult = "VIII";
                case 9 -> simpleRomesResult = "IX";
            }
            return simpleRomesResult;
        }
        String arabicNumberToString = Integer.toString(result);
        char[] arabicChar = arabicNumberToString.toCharArray();
        String arabicPartOne = String.valueOf(arabicChar[0]);
        String arabicPartTwo = String.valueOf(arabicChar[1]);

        String romesResult = "";
        String romesResultPartOne = "";
        String romesResultPartTwo = "";

        switch (arabicPartOne) {
            case "1" -> romesResultPartOne = "X";
            case "2" -> romesResultPartOne = "XX";
            case "3" -> romesResultPartOne = "XXX";
            case "4" -> romesResultPartOne = "XL";
            case "5" -> romesResultPartOne = "L";
            case "6" -> romesResultPartOne = "LX";
            case "7" -> romesResultPartOne = "LXX";
            case "8" -> romesResultPartOne = "LXXX";
            case "9" -> romesResultPartOne = "XC";
        }

        switch (arabicPartTwo) {
            case "0" -> romesResultPartTwo = "0";
            case "1" -> romesResultPartTwo = "I";
            case "2" -> romesResultPartTwo = "II";
            case "3" -> romesResultPartTwo = "III";
            case "4" -> romesResultPartTwo = "IV";
            case "5" -> romesResultPartTwo = "V";
            case "6" -> romesResultPartTwo = "VI";
            case "7" -> romesResultPartTwo = "VII";
            case "8" -> romesResultPartTwo = "VIII";
            case "9" -> romesResultPartTwo = "IX";
        }
        if (romesResultPartTwo == "0") {
            romesResult = romesResultPartOne;
        } else {
            romesResult = romesResultPartOne + romesResultPartTwo;
        }
        return romesResult;
    }

}
