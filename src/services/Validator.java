package services;

import exception.ValidatorException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Validator {

    public static void isKeyValid(int key, List<Character> list) {
        boolean isKeyNotNull = (key > 0);
        boolean isKeyValid = (key % list.size() != 0);
        if (!isKeyNotNull || !isKeyValid) {
            throw new ValidatorException("Key is incorrect");
        }
    }

    public static void isInputFileGood(String filePath) {
        Path path = Path.of(filePath);

        boolean isExists = Files.exists(path);
        boolean isAbsolute = path.isAbsolute();
        boolean isValidFilenameExtension = path.toString().endsWith(".txt");
        if (!isExists || !isAbsolute || !isValidFilenameExtension) {
            throw new ValidatorException("Input file or path error");
        }
    }

    public static void isOutputPathGood(String filePath) {
        Path path = Path.of(filePath);

        boolean isAbsolute = path.isAbsolute();
        boolean isValidFilenameExtension = path.toString().endsWith(".txt");
        if (!isAbsolute || !isValidFilenameExtension) {
            throw new ValidatorException("Output path error");
        }
    }

    public static void isOutputDirectoryGood(String filePath) {
        Path path = Path.of(filePath);

        boolean isAbsolute = path.isAbsolute();
        if (!isAbsolute) {
            throw new ValidatorException("Output path error");
        }
    }

    public static boolean isOutputDirectoryExist(String filePath) {
        Path path = Path.of(filePath);
        return Files.exists(path);
        }

    public static boolean isOutputFileExist(String filePath) {
        Path path = Path.of(filePath);
        return Files.exists(path);
    }
}
