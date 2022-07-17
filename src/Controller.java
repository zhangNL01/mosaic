import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

public class Controller {
    private Processor processor;
    private View view;
    private Scanner sc;
    private final String INPUTTEXT = "Please put the file into the folder \'images\'. What the file name you want to process is? i.e. image.png";

    public Controller(Processor processor, View view, Scanner sc) {
        this.processor = processor;
        this.view = view;
        this.sc = sc;
    }

    void run() {
        String name = "";
        System.out.println(INPUTTEXT);
        while (sc.hasNextLine()) {
            name = sc.nextLine();

            if (name.equals("quit") || name.equals("q")) System.exit(0);

            try {
                BufferedImage img = ImageIO.read(new File("images/" + name));
//                view.showImg(img);
                processor.setImage(img);
                processor.toMosaic();
                BufferedImage resultImg = processor.getResult();
//                view.showImg(resultImg);
                view.saveImg(resultImg);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}
