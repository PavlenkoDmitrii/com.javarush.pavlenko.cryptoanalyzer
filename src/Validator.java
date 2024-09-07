public class Validator {
    public static int key;
    public static String pathToOriginalFile;
    public static String pathToCipheredFile;

    public static void setKey(int key) {
        Validator.key = key;
    }

    public static void setPathToOriginalFile(String pathToOriginalFile) {
        Validator.pathToOriginalFile = pathToOriginalFile;
    }

    public static void setPathToCipheredFile(String pathToCipheredFile) {
        Validator.pathToCipheredFile = pathToCipheredFile;
    }
}
