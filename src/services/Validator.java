package services;

import exception.ValidatorException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {


    public static void isKeyValid(int key) {
        boolean isKeyValid = (key != 0);
        if (!isKeyValid) {
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
