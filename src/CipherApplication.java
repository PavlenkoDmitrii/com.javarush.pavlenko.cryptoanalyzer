import exception.ValidatorException;
import services.CipherAndDecipherFiles;
import services.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CipherApplication {

    private static String command = null;
    private static final String INCORRECT_PATH = "Некорректный путь или расширение файла!";
    private static final String INCORRECT_KEY = "Некорректный ключ";
    private static final String INPUT_KEY = "Введите целое положительное число больше 0 (ключ): ";
    private static final String SAVE_RESULT = "Введите путь к файлу с расширением \".txt\" для сохранения результата.\n" +
            "Если пути к файлу или самого файла не существует - он будет создан автоматически:";
    private static final String CREATE_NEW_FILE_ERROR = "Ошибка при создании нового файла и/или дирректории!";
    private static final String COMPLETED = "Выполнение завершено!";

    private static void coder() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите путь к исходному файлу с расширением \".txt\" для шифрования:");
            String path = scanner.nextLine();
            try {
                Validator.isInputFileGood(path);
            } catch (ValidatorException ve) {
                System.out.println(INCORRECT_PATH);
                Thread.sleep(1000);
                continue;
            }
            System.out.print(INPUT_KEY);
            int key;
            try {
                key = scanner.nextInt();
                Validator.isKeyValid(key, Alphabet.getALPHABET());
            } catch (InputMismatchException | ValidatorException ex) {
                System.out.println(INCORRECT_KEY);
                scanner.nextLine();
                Thread.sleep(1000);
                continue;
            }
            scanner.nextLine();
            System.out.println(SAVE_RESULT);
            String pathAfterCiphered = scanner.nextLine();
            try {
                Validator.isOutputPathGood(pathAfterCiphered);
            } catch (ValidatorException ve) {
                System.out.println(INCORRECT_PATH);
                Thread.sleep(1000);
                continue;
            }
            if (!Validator.isOutputFileExist(pathAfterCiphered)) {
                Path absolutePath = Path.of(pathAfterCiphered);
                Path parent = absolutePath.getParent();
                try {
                    Files.createDirectories(parent);
                    Files.createFile(absolutePath);
                } catch (IOException e) {
                    System.out.println(CREATE_NEW_FILE_ERROR);
                    Thread.sleep(1000);
                    continue;
                }
            }
            long l1 = System.currentTimeMillis();
            CipherAndDecipherFiles cadf = new CipherAndDecipherFiles(Alphabet.getALPHABET());
            cadf.encryptFile(path, key, pathAfterCiphered);
            long l2 = System.currentTimeMillis();
            System.out.println(l2-l1);
            break;
        }
        System.out.println(COMPLETED);
        Thread.sleep(1000);
    }

    private static void decoder() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите путь к зашифрованому файлу с расширением \".txt\" для дешифрования:");
            String path = scanner.nextLine();
            try {
                Validator.isInputFileGood(path);
            } catch (ValidatorException ve) {
                System.out.println(INCORRECT_PATH);
                Thread.sleep(1000);
                continue;
            }
            System.out.print(INPUT_KEY);
            int key;
            try {
                key = scanner.nextInt();
                Validator.isKeyValid(key, Alphabet.getALPHABET());
            } catch (InputMismatchException | ValidatorException ex) {
                System.out.println(INCORRECT_KEY);
                scanner.nextLine();
                Thread.sleep(1000);
                continue;
            }
            scanner.nextLine();
            System.out.println(SAVE_RESULT);
            String pathAfterDeciphered = scanner.nextLine();
            try {
                Validator.isOutputPathGood(pathAfterDeciphered);
            } catch (ValidatorException ve) {
                System.out.println(INCORRECT_PATH);
                Thread.sleep(1000);
                continue;
            }
            if (!Validator.isOutputFileExist(pathAfterDeciphered)) {
                Path absolutePath = Path.of(pathAfterDeciphered);
                Path parent = absolutePath.getParent();
                try {
                    Files.createDirectories(parent);
                    Files.createFile(absolutePath);
                } catch (IOException e) {
                    System.out.println(CREATE_NEW_FILE_ERROR);
                    Thread.sleep(1000);
                    continue;
                }
            }
            long l1 = System.currentTimeMillis();
            CipherAndDecipherFiles cadf = new CipherAndDecipherFiles(Alphabet.getALPHABET());
            cadf.decryptFile(path, key, pathAfterDeciphered);
            long l2 = System.currentTimeMillis();
            System.out.println(l2-l1);
            break;
        }
        System.out.println(COMPLETED);
        Thread.sleep(1000);
    }

    private static void bruteForce() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите путь к зашифрованому файлу с расширением \".txt\" для дешифрования методом Brute Force:");
            String path = scanner.nextLine();
            try {
                Validator.isInputFileGood(path);
            } catch (ValidatorException ve) {
                System.out.println(INCORRECT_PATH);
                Thread.sleep(1000);
                continue;
            }
            System.out.println("Введите путь к папке для сохранения результата.\n" +
                    "Если пути к папке или самой папки не существует - они будут созданы автоматически: ");
            String pathAfterDeciphered = scanner.nextLine();
            try {
                Validator.isOutputDirectoryGood(pathAfterDeciphered);
            } catch (ValidatorException ve) {
                System.out.println(INCORRECT_PATH);
                Thread.sleep(1000);
                continue;
            }
            if (!Validator.isOutputDirectoryExist(pathAfterDeciphered)) {
                Path absolutePath = Path.of(pathAfterDeciphered);
                try {
                    Files.createDirectories(absolutePath);
                } catch (IOException e) {
                    System.out.println(CREATE_NEW_FILE_ERROR);
                    Thread.sleep(1000);
                    continue;
                }
            }
            long l1 = System.currentTimeMillis();
            CipherAndDecipherFiles cipherAndDecipherFiles = new CipherAndDecipherFiles(Alphabet.getALPHABET());
            cipherAndDecipherFiles.decryptWithBruteForceFile(path, pathAfterDeciphered);
            long l2 = System.currentTimeMillis();
            System.out.println(l2-l1);
            break;
        }
        System.out.println(COMPLETED);
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (command == null) {
                System.out.println();
                System.out.println("************");
                System.out.println("CaesarCipher");
                System.out.println("************");
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
