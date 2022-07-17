import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Processor {
    private ColorTable colorTable;
    private int outputWidth;
    private BufferedImage image;
    private BufferedImage result;

    public Processor(ColorTable colorTable) {
        this.colorTable = colorTable;
    }

    void setOutputWidth(int outputWidth) {
        this.outputWidth = outputWidth;
    }

    void setImage(BufferedImage image) {
        this.image = image;
    }

    private int getAvg(ArrayList<Integer> values) {
        return (int) values.stream().mapToInt(a -> a).average().getAsDouble();
    }

    private int findNearestColor(ArrayList<Integer>[] values) {
        int avgRed = getAvg(values[0]);
        int avgGreen = getAvg(values[1]);
        int avgBlue = getAvg(values[2]);

        int colorIndex = 0;
        double minDiff = 0;
        for (int i = 0; i < 64; i++) {
            int r = colorTable.getColors()[i][0] & 0xff;
            int g = colorTable.getColors()[i][1] & 0xff;
            int b = colorTable.getColors()[i][2] & 0xff;
            var diff = Math.abs(r - avgRed) + Math.abs(g - avgGreen) + Math.abs(b - avgBlue);
            if (i == 0 || diff < minDiff) {
                minDiff = diff;
                colorIndex = i;
            }
        }
        return colorIndex;
    }


    private ArrayList<Integer>[] getValuesOfOneTile(int startX, int startY, int endX, int endY) {
        var result = new ArrayList[3];

        var redValues = new ArrayList<Integer>();
        var greenValues = new ArrayList<Integer>();
        var blueValues = new ArrayList<Integer>();

        result[0] = redValues;
        result[1] = greenValues;
        result[2] = blueValues;

        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                var color = new Color(image.getRGB(x, y));
                redValues.add(color.getRed());
                greenValues.add(color.getGreen());
                blueValues.add(color.getBlue());
            }
        }
        return result;
    }

    // Resource: https://stackoverflow.com/questions/9581530/converting-from-byte-to-int-in-java
    private void fillOnePixel(int x, int y, int colorIndex) {
        byte r = this.colorTable.getColors()[colorIndex][0];
        byte g = this.colorTable.getColors()[colorIndex][1];
        byte b = this.colorTable.getColors()[colorIndex][2];
        this.result.setRGB(x, y, new Color(r & 0xff, g & 0xff, b & 0xff).getRGB());
    }

    void toMosaic() {
        int width = image.getWidth();
        int height = image.getHeight();

        double ratio = (double) height / (double) width;
        int outHeight = (int) (ratio * outputWidth);

        this.result = new BufferedImage(outputWidth, outHeight, BufferedImage.TYPE_INT_RGB);

        int cellWidth = width / outputWidth;
        int cellHeight = height / outHeight;

        for (int col = 0; col < outputWidth; col++) {
            for (int row = 0; row < outHeight; row++) {
                int x = col * cellWidth;
                int y = row * cellHeight;
                var rgbValues = getValuesOfOneTile(x, y, x + cellWidth, y + cellHeight);
                int findColorIndex = findNearestColor(rgbValues);
                fillOnePixel(col, row, findColorIndex);
            }
        }
    }

    BufferedImage getResult() {
        return this.result;
    }
}
