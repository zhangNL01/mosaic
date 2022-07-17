import java.util.Scanner;

public class Main {
    private static final int OUTPUTWIDTH = 80;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ColorTable colorTable = new ColorTable();
        Processor processor = new Processor(colorTable);
        processor.setOutputWidth(OUTPUTWIDTH);
        View view = new View();
        Controller controller = new Controller(processor, view, sc);

        controller.run();
    }
}
