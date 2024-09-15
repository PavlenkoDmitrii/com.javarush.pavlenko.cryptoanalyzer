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
    private static final String INPUT_PATH = "Введите абсолютный путь к файлу с расширением \".txt\"\n" +
            "Или введите 0 для возвращения в главное меню: ";
    private static final String INPUT_KEY = "Введите целое положительное число больше 0 (ключ).\n" +
            "Или введите 0 для возвращения в главное меню: ";
    private static final String SAVE_RESULT = """
            Введите абсолютный путь к файлу с расширением ".txt" для сохранения результата.
            Если пути к файлу или самого файла не существует - он будет создан автоматически.
            Или введите 0 для возвращения в главное меню:\s""";
    private static final String CREATE_NEW_FILE_ERROR = "Ошибка при создании нового файла и/или дирректории!";
    private static final String COMPLETED = "Выполнение завершено!";
    private static final String CIPHERED = "Шифрование";
    private static final String DECIPHERED = "Дешифрование";
    private static final String BRUTE_FORCE = "Brute Force";
    private static final String EXIT = "Выход";

    private static String inputPath() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String path;
        path = scanner.nextLine();
        if (path.equals("0")) {
            return path;
        }
        try {
            Validator.isInputFileGood(path);
        } catch (ValidatorException ve) {
            System.out.println(INCORRECT_PATH);
            System.out.println();
            Thread.sleep(1000);
        }
        return path;
    }

    private static int inputKey() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String stringKey = scanner.nextLine();
        if (stringKey.equals("0")) {
            return 0;
        }
        int key = -1;
        try {
            key = Integer.parseInt(stringKey);
            Validator.isKeyValid(key, Alphabet.getALPHABET());
        } catch (NumberFormatException | ValidatorException ex) {
            System.out.println(INCORRECT_KEY);
            System.out.println();
            Thread.sleep(1000);
        }
        return key;
    }

    private static String inputPathForSaveResult() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String pathAfterExecution;
        pathAfterExecution = scanner.nextLine();
        try {
            Validator.isOutputPathGood(pathAfterExecution);
        } catch (ValidatorException ve) {
            System.out.println(INCORRECT_PATH);
            System.out.println();
            Thread.sleep(1000);
        }
        return pathAfterExecution;
    }

    private static void createNewDirectoriesAndFiles(String pathAfterExecution) throws InterruptedException {
        Path absolutePath = Path.of(pathAfterExecution);
        Path parent = absolutePath.getParent();
        try {
            Files.createDirectories(parent);
            Files.createFile(absolutePath);
        } catch (IOException e) {
            System.out.println(CREATE_NEW_FILE_ERROR);
            System.out.println();
            Thread.sleep(1000);
        }
    }

    private static void coder() throws InterruptedException {
        System.out.println(CIPHERED);
        while (true) {
            System.out.println(INPUT_PATH);
            String path = inputPath();
            if (path.equals("0")) {
                break;
            }
            try {
                Validator.isInputFileGood(path);
            } catch (ValidatorException ve) {
                continue;
            }
            System.out.print(INPUT_KEY);
            int key = inputKey();
            if (key == 0) {
                break;
            }
            try {
                Validator.isKeyValid(key, Alphabet.getALPHABET());
            } catch (InputMismatchException | ValidatorException ex) {
                continue;
            }
            System.out.println(SAVE_RESULT);
            String pathAfterExecution = inputPathForSaveResult();
            if (pathAfterExecution.equals("0")) {
                break;
            }
            try {
                Validator.isOutputPathGood(pathAfterExecution);
            } catch (ValidatorException ve) {
                continue;
            }
            if (!Validator.isOutputFileExist(pathAfterExecution)) {
                createNewDirectoriesAndFiles(pathAfterExecution);
            }
            CipherAndDecipherFiles cadf = new CipherAndDecipherFiles(Alphabet.getALPHABET());
            try {
                cadf.encryptFile(path, key, pathAfterExecution);
            } catch (IOException e) {
                System.out.println("Ошибка чтения/записи файла");
                e.printStackTrace();
                continue;
            }
            System.out.println(COMPLETED);
            break;
        }
    }

    private static void decoder() throws InterruptedException {
        System.out.println(DECIPHERED);
        while (true) {
            System.out.println(INPUT_PATH);
            String path = inputPath();
            if (path.equals("0")) {
                break;
            }
            try {
                Validator.isInputFileGood(path);
            } catch (ValidatorException ve) {
                continue;
            }
            System.out.print(INPUT_KEY);
            int key = inputKey();
            if (key == 0) {
                break;
            }
            try {
                Validator.isKeyValid(key, Alphabet.getALPHABET());
            } catch (InputMismatchException | ValidatorException ex) {
                continue;
            }
            System.out.println(SAVE_RESULT);
            String pathAfterExecution = inputPathForSaveResult();
            if (pathAfterExecution.equals("0")) {
                break;
            }
            try {
                Validator.isOutputPathGood(pathAfterExecution);
            } catch (ValidatorException ve) {
                continue;
            }
            if (!Validator.isOutputFileExist(pathAfterExecution)) {
                createNewDirectoriesAndFiles(pathAfterExecution);
            }
            CipherAndDecipherFiles cadf = new CipherAndDecipherFiles(Alphabet.getALPHABET());
            try {
                cadf.decryptFile(path, key, pathAfterExecution);
            } catch (IOException e) {
                System.out.println("Ошибка чтения/записи файла");
                e.printStackTrace();
                continue;
            }
            System.out.println(COMPLETED);
            break;
        }
    }

    private static void bruteForce() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(BRUTE_FORCE);
        while (true) {
            System.out.println(INPUT_PATH);
            String path = inputPath();
            if (path.equals("0")) {
                break;
            }
            try {
                Validator.isInputFileGood(path);
            } catch (ValidatorException ve) {
                continue;
            }
            System.out.println("""
                    Введите абсолютный путь к папке для сохранения результата.
                    Если пути к папке или самой папки не существует - они будут созданы автоматически.
                    Или введите 0 для возвращения в главное меню:\s""");
            String pathAfterExecution = scanner.nextLine();
            if (pathAfterExecution.equals("0")) {
                break;
            }
            try {
                Validator.isOutputDirectoryGood(pathAfterExecution);
            } catch (ValidatorException ve) {
                System.out.println(INCORRECT_PATH);
                System.out.println();
                Thread.sleep(1000);
                continue;
            }
            if (!Validator.isOutputDirectoryExist(pathAfterExecution)) {
                Path absolutePath = Path.of(pathAfterExecution);
                try {
                    Files.createDirectories(absolutePath);
                } catch (IOException e) {
                    System.out.println(CREATE_NEW_FILE_ERROR);
                    System.out.println();
                    Thread.sleep(1000);
                    continue;
                }
            }
            CipherAndDecipherFiles cipherAndDecipherFiles = new CipherAndDecipherFiles(Alphabet.getALPHABET());
            try {
                cipherAndDecipherFiles.decryptWithBruteForceFile(path, pathAfterExecution);
            } catch (IOException e) {
                System.out.println("Ошибка чтения/записи файла");
                e.printStackTrace();
                continue;
            }
            System.out.println(COMPLETED);
            break;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (command == null) {
                System.out.println();
                System.out.println("CaesarCipher");
                System.out.println("************");
                System.out.println("Введите команду цифрой или словом:");
                System.out.println("1) " + CIPHERED);
                System.out.println("2) " + DECIPHERED);
                System.out.println("3) " + BRUTE_FORCE);
                System.out.println("4) " + EXIT);
                command = scanner.nextLine();
            }
            if (command.equals("1") || command.equalsIgnoreCase(CIPHERED)) {
                coder();
                command = null;
            } else if (command.equals("2") || command.equalsIgnoreCase(DECIPHERED)) {
                decoder();
                command = null;
            } else if (command.equals("3") || command.equalsIgnoreCase(BRUTE_FORCE)) {
                bruteForce();
                command = null;
            } else if (command.equals("4") || command.equalsIgnoreCase(EXIT)) {
                break;
            } else {
                System.out.println("Неизвестная команда, повторите ввод!");
                command = null;
            }
        }
    }
}
