import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Введите что-то там");
        String input = s.nextLine();
        Calc calc = new Calc();
        calc.starter(input);
    }

        static class Calc {
            private int count = 0;
            private int result = 0;
            private int countForNumbers = 0;
            private int num1Number = 0;
            private int num2Number = 0;
            final String[] allRoman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

            void starter(String input) {
                String[] in = input.split(" ");
                if (in.length !=3) {
                    throw new IndexOutOfBoundsException();
                }
                String x = checkNumber(in);
                if (x.equals("arabian")) {
                    System.out.println(arabianCalc(in));
                }
                else if (x.equals("roman")) {
                    System.out.println(romanCalc(in));
                } else {
                    throw new IllegalArgumentException();
                }
            }

            boolean trueArabianNumber(String[] in) {
                try {
                    Integer.parseInt(in[0]);
                    Integer.parseInt(in[2]);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }

            // Метод проверки введенных значений
            String checkNumber(String[] in) throws IllegalArgumentException {
                boolean tr = trueArabianNumber(in);
                if (tr && Integer.parseInt(in[0]) <=10 && Integer.parseInt(in[2]) <= 10) {
                    if (Integer.parseInt(in[0]) >= 0 && Integer.parseInt(in[2]) >= 0) {
                        return "arabian";
                    }
                }
                for (String y : allRoman) {
                    countForNumbers += 1;
                    if (in[0].equals(y)) {
                        count += 1;
                        num1Number += countForNumbers;
                    }
                    if (in[2].equals(y)) {
                        count += 1;
                        num2Number += countForNumbers;
                    }
                }
                if (count == 2) {
                    return "roman";
                } else {
                    return "Введены неверные данные";
                }
            }

            // Метод вычисления арабских цифр
            int arabianCalc(String[] in) {
                switch (in[1]) {
                    case "+":
                        result = Integer.parseInt(in[0]) + Integer.parseInt(in[2]);
                        break;
                    case "-":
                        result = Integer.parseInt(in[0]) - Integer.parseInt(in[2]);
                        break;
                    case "*":
                        result = Integer.parseInt(in[0]) * Integer.parseInt(in[2]);
                        break;
                    case "/":
                        result = Integer.parseInt(in[0]) / Integer.parseInt(in[2]);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                return result;
            }

            String romanCalc (String[] in) {
                String romanNumb = "";
                int romanNumbInteger = 0;

                switch (in[1]) {
                    case "+":
                        romanNumbInteger = num1Number + num2Number;
                        break;
                    case "-":
                        romanNumbInteger = num1Number - num2Number;
                        break;
                    case "*":
                        romanNumbInteger = num1Number * num2Number;
                        break;
                    case "/":
                        romanNumbInteger = num1Number / num2Number;
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                if (romanNumbInteger < 0) {
                    throw new UnsupportedOperationException();
                }
                romanNumb = romanConverter(romanNumbInteger, in);
                return romanNumb;
            }

            String romanConverter(int num, String[] in) {
                String convertNum = "";
                if (num <= 10) {
                    convertNum = allRoman[num -  1];
                    }
                else if (num <= 39) {
                    for (int i = 0; i < num/10; i++) {
                        convertNum+="X";
                    }
                } else if (num <= 49) {
                    convertNum = "XL";
                } else if (num <= 89) {
                    convertNum = "L";
                    for (int i = 0; i < (num/10) - 5; i++) {
                        convertNum+="X";
                    }
                } else if (num <100) {
                    convertNum = "XC";
                } else {
                    convertNum = "C";
                }
                if (num%10 != 0 && num > 10) {
                    convertNum += allRoman[(num % 10) - 1];
                }
                return convertNum;
            }
        }
    }

