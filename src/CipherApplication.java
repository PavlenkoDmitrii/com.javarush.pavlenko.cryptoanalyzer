import exception.ValidatorException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CipherApplication {


    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        Validator validator = new Validator();
        String command = null;
        while (true) {
            if (command == null) {
                System.out.println("ШИФРОВАЛКА");
                System.out.println("Введите команду:");
                System.out.println("1 \"Шифрование\"");
                System.out.println("2 \"Дешифрование\"");
                System.out.println("3 \"Brute Force\"");
                System.out.println("4 \"Выход\"");
                command = scanner.nextLine();
            }
            if (command.equals("1") || command.equalsIgnoreCase("Шифрование")) {
                System.out.println("Введите путь к исходному файлу с расширением \".txt\" для шифрования:");
                String path = scanner.nextLine();
                try {
                    validator.isInputFileGood(path);
                } catch (ValidatorException ve) {
                    System.out.println("Некорректный путь или расширение файла!");
                    Thread.sleep(1000);
                    continue;
                }
                System.out.print("Введите целое число (ключ): ");
                int key = 0;
                try {
                    key = scanner.nextInt();
                    validator.isKeyValid(key, Alphabet.getALPHABET());
                } catch (InputMismatchException ime) {
                    System.out.println("Ошибочный ввод");
                    scanner.nextLine();
                    Thread.sleep(1000);
                    continue;
                }
                scanner.nextLine();
                System.out.println("Введите путь к файлу с расширением \".txt\" для сохранения результата шифрования:");
                String pathAfterCiphered = scanner.nextLine();
                try {
                    validator.isOutputFileGood(pathAfterCiphered);
                } catch (ValidatorException ve) {
                    System.out.println("Некорректный путь или расширение файла!");
                    Thread.sleep(1000);
                    continue;
                }
                    //метод шифровки

            } else if (command.equals("2") || command.equalsIgnoreCase("Дешифрование")) {
                System.out.println("Введите путь к зашифрованому файлу с расширением \".txt\" для дешифрования:");
                String path = scanner.nextLine();
                try {
                    validator.isInputFileGood(path);
                } catch (ValidatorException ve) {
                    System.out.println("Некорректный путь или расширение файла!");
                    Thread.sleep(1000);
                    continue;
                }
                System.out.print("Введите целое число (ключ): ");
                int key = 0;
                try {
                    key = scanner.nextInt();
                    validator.isKeyValid(key, Alphabet.getALPHABET());
                } catch (InputMismatchException ime) {
                    System.out.println("Ошибочный ввод");
                    scanner.nextLine();
                    Thread.sleep(1000);
                    continue;
                }
                scanner.nextLine();
                System.out.println("Введите путь к файлу с расширением \".txt\" для сохранения результата дешифрования:");
                String pathAfterCiphered = scanner.nextLine();
                try {
                    validator.isOutputFileGood(pathAfterCiphered);
                } catch (ValidatorException ve) {
                    System.out.println("Некорректный путь или расширение файла!");
                    Thread.sleep(1000);
                    continue;
                }
                    //метод дешифровки

            } else if (command.equals("3") || command.equalsIgnoreCase("Brute Force")) {
                System.out.println("Введите путь к зашифрованому файлу с расширением \".txt\" для дешифрования методом Brute Force:");
                String path = scanner.nextLine();
                try {
                    validator.isInputFileGood(path);
                } catch (ValidatorException ve) {
                    System.out.println("Некорректный путь или расширение файла!");
                    Thread.sleep(1000);
                    continue;
                }
                System.out.println("brute force");


            } else if (command.equals("4") || command.equalsIgnoreCase("Выход")) {
                break;

            } else {
                System.out.println("Неизвестная команда, повторите ввод!");
                command = null;
                Thread.sleep(1000);
            }
        }
    }
}
