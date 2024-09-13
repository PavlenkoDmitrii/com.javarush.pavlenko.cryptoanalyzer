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

        boolean exists = Files.exists(path);
        boolean absolute = path.isAbsolute();
        boolean validFilenameExtension = path.toString().endsWith(".txt");
        if (!exists || !absolute || !validFilenameExtension) {
            throw new ValidatorException("Input file or path error");
        }
    }

    public static void isOutputPathGood(String filePath) {
        Path path = Path.of(filePath);

        boolean absolute = path.isAbsolute();
        boolean validFilenameExtension = path.toString().endsWith(".txt");
        if (!absolute || !validFilenameExtension) {
            throw new ValidatorException("Output path error");
        }
    }

    public static void isOutputFileExist(String filePath) {
        Path path = Path.of(filePath);

        boolean isExist = Files.exists(path);
        if (!isExist) {
            throw new ValidatorException("Output file error");
        }
    }
}
