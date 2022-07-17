import java.util.Scanner;

public class Controller {
    Processor processor;
    View view;
    Scanner sc;
    final String filenameRequest = "Please put the file into the folder \'images\'. What the file name you want to process is? i.e. image.png";

    public Controller(Processor processor, View view, Scanner sc) {
        this.processor = processor;
        this.view = view;
        this.sc = sc;
    }

    void run() {
    }
}
