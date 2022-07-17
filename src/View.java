import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class View {
    private final String PATH = "output";
    private final String FILENAME = "output.png";
    private final String FINISHEDTEXT = "Successful transformed. output stored as \'output/output.png\'. You can enter another filename or enter \'q\' or \'quit\' to exit.";

    // Resource: https://stackoverflow.com/questions/1626735/how-can-i-display-a-bufferedimage-in-a-jframe
    void showImg(BufferedImage img) {
        try {
            JFrame frame = new JFrame();
            frame.getContentPane().setLayout(new FlowLayout());
            frame.getContentPane().add(new JLabel(new ImageIcon(img)));
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    void saveImg(BufferedImage img) throws IOException {
        try {
            File directory = new File(PATH);

            if (!directory.exists()) { directory.mkdir();}

            File outFile = new File(directory + "/" + FILENAME);
            ImageIO.write(img, "png", outFile);
        } catch (Exception e) {
            throw e;
        }

        System.out.println(FINISHEDTEXT);
    }
}
