package services;

import exception.ValidatorException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Validator {

    public static void isKeyValid(int key, List<Character> list) {
        boolean isKeyNotNull = (key != 0);
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
            throw new ValidatorException("File or path error");
        }
    }

    public static void isOutputFileGood(String filePath) {
        Path path = Path.of(filePath);
        File file = path.toFile();

        boolean absolute = path.isAbsolute();
        boolean validFilenameExtension = path.toString().endsWith(".txt");
        boolean isWrite = file.canWrite();
                if (!absolute || !validFilenameExtension || !isWrite) {
            throw new ValidatorException("File or path error");
        }
    }
}
