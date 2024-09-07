import java.util.Scanner;

public class CipherApplication {


    public static void main(String[] args) {
        System.out.println("ШИФРОВАЛКА");
        System.out.println("Введите команду:");
        System.out.println("1 Шифрование");
        System.out.println("2 Дешифрование");
        System.out.println("3 Brute Force");
        System.out.println("Для выхода из программы напишите \"4\" или \"Выход\"");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (true) {
            if (command.equals("1") || command.equalsIgnoreCase("Шифрование")) {
                System.out.print("Введите путь к исходному файлу для шифрования: ");
                Validator.setPathToOriginalFile(scanner.nextLine());
                System.out.print("Введите целое число (ключ): ");
                Validator.setKey(scanner.nextInt());
            } else if (command.equals("2") || command.equalsIgnoreCase("Дешифрование")) {
                System.out.print("Введите путь к зашифрованому файлу для дешифрования: ");
                Validator.setPathToCipheredFile(scanner.nextLine());
                System.out.print("Введите целое число (ключ): ");
                Validator.setKey(scanner.nextInt());
            } else if (command.equals("3") || command.equalsIgnoreCase("Brute Force")) {
                System.out.print("Введите путь к зашифрованому файлу для взлома методом Brute Force: ");
                Validator.setPathToCipheredFile(scanner.nextLine());
            } else if (command.equals("4") || command.equalsIgnoreCase("Выход")) {
                break;
            } else {
                System.out.print("Неизвестная команда, повторите ввод: ");
                command = scanner.nextLine();
            }
        }
    }
}
