import exception.ValidatorException;
import services.CipherAndDecipherFiles;
import services.Validator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CipherApplication {

    private static String command = null;

    private static void coder() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите путь к исходному файлу с расширением \".txt\" для шифрования:");
            String path = scanner.nextLine();
            try {
                Validator.isInputFileGood(path);
            } catch (ValidatorException ve) {
                System.out.println("Некорректный путь или расширение файла!");
                Thread.sleep(1000);
                continue;
            }
            System.out.print("Введите целое число (ключ): ");
            int key = 0;
            try {
                key = scanner.nextInt();
                Validator.isKeyValid(key, Alphabet.getALPHABET());
            } catch (InputMismatchException | ValidatorException ex) {
                System.out.println("Ошибочный ввод");
                scanner.nextLine();
                Thread.sleep(1000);
                continue;
            }
            scanner.nextLine();
            System.out.println("Введите путь к файлу с расширением \".txt\" для сохранения результата шифрования:");
            String pathAfterCiphered = scanner.nextLine();
            try {
                Validator.isOutputFileGood(pathAfterCiphered);
            } catch (ValidatorException ve) {
                System.out.println("Некорректный путь или расширение файла!");
                Thread.sleep(1000);
                continue;
            }
            CipherAndDecipherFiles cadf = new CipherAndDecipherFiles(Alphabet.getALPHABET());
            cadf.encipherFile(path, key, pathAfterCiphered);

            break;
        }
        System.out.println("Шифрование завершено");
    }

    private static void decoder() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите путь к зашифрованому файлу с расширением \".txt\" для дешифрования:");
            String path = scanner.nextLine();
            try {
                Validator.isInputFileGood(path);
            } catch (ValidatorException ve) {
                System.out.println("Некорректный путь или расширение файла!");
                Thread.sleep(1000);
                continue;
            }
            System.out.print("Введите целое число (ключ): ");
            int key = 0;
            try {
                key = scanner.nextInt();
                Validator.isKeyValid(key, Alphabet.getALPHABET());
            } catch (InputMismatchException | ValidatorException ex) {
                System.out.println("Ошибочный ввод");
                scanner.nextLine();
                Thread.sleep(1000);
                continue;
            }
            scanner.nextLine();
            System.out.println("Введите путь к файлу с расширением \".txt\" для сохранения результата дешифрования:");
            String pathAfterCiphered = scanner.nextLine();
            try {
                Validator.isOutputFileGood(pathAfterCiphered);
            } catch (ValidatorException ve) {
                System.out.println("Некорректный путь или расширение файла!");
                Thread.sleep(1000);
                continue;
            }
            CipherAndDecipherFiles cadf = new CipherAndDecipherFiles(Alphabet.getALPHABET());
            cadf.decipherFile(path, key, pathAfterCiphered);
            break;
        }
        System.out.println("Дешифровка завершена");
    }

    private static void bruteForce() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите путь к зашифрованому файлу с расширением \".txt\" для дешифрования методом Brute Force:");
            String path = scanner.nextLine();
            try {
                Validator.isInputFileGood(path);
            } catch (ValidatorException ve) {
                System.out.println("Некорректный путь или расширение файла!");
                Thread.sleep(1000);
                continue;
            }
            System.out.println("brute force");
            //метод брутфорса
            break;
        }
        System.out.println("Дешифровка завершена");
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

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
                coder();
                command = null;
                Thread.sleep(1000);
            } else if (command.equals("2") || command.equalsIgnoreCase("Дешифрование")) {
                decoder();
                command = null;
                Thread.sleep(1000);
            } else if (command.equals("3") || command.equalsIgnoreCase("Brute Force")) {
                bruteForce();
                command = null;
                Thread.sleep(1000);
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
