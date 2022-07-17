import java.awt.image.BufferedImage;

public class Processor {
    private ColorTable colorTable;
    private BufferedImage image;
    private BufferedImage result;
    private int outputWidth;

    public Processor(ColorTable colorTable) {
        this.colorTable = colorTable;
        this.outputWidth = outputWidth;
    }

    void setOutputWidth(int outputWidth) {
        this.outputWidth = outputWidth;
    }
}
