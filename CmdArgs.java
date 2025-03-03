public class CmdArgs {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Немає переданих аргументів.");
        } else {
            System.out.println("Передані аргументи:");
            for (int i = 0; i < args.length; i++) {
                System.out.println("Аргумент " + (i + 1) + ": " + args[i]);
            }
        }
    }
}