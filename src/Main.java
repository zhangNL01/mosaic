import java.util.Scanner;

public class Main {
    static final int outputWidth = 80;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ColorTable colorTable = new ColorTable();
        Processor processor = new Processor(colorTable);
        processor.setOutputWidth(outputWidth);
        View view = new View();
        Controller controller = new Controller(processor, view, sc);

        controller.run();
    }
}
