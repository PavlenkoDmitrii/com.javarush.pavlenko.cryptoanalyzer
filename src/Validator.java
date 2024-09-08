import exception.ValidatorException;

import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {


    public boolean isKeyValid(int key, char[] alphabet) {
        boolean isKeyNotNull = (key != 0);
        boolean isShiftNotNull = false;
        for (int i = 0; i < alphabet.length; i++) {
            if ((i + key) % alphabet.length != 0) {
                isShiftNotNull = true;

                //???

            }
        }
        return (isShiftNotNull && isKeyNotNull);
    }

    public void isInputFileGood(String filePath) {
        Path path = Path.of(filePath);
        boolean exists = Files.exists(path);
        boolean absolute = path.isAbsolute();
        boolean validFilenameExtension = path.toString().endsWith(".txt");
        if (!exists || !absolute || !validFilenameExtension) {
            throw new ValidatorException("Файл не существует. Или некорректный путь и/или расширение файла");
        }
    }

    public void isOutputFileGood(String filePath) {
        Path path = Path.of(filePath);
        boolean absolute = path.isAbsolute();
        boolean validFilenameExtension = path.toString().endsWith(".txt");
        if (!absolute || !validFilenameExtension) {
            throw new ValidatorException("Некорректный путь и/или расширение файла");
        }
    }
}
