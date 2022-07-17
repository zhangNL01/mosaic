public class ColorTable {
    private byte[][] colors;

    public ColorTable() {
        byte[] values = {0, (byte) (255 / 4), (byte) (255 / 2), (byte) 255};
        this.colors = new byte[64][3];
        int index = 0;
        for (byte r : values) {
            for (byte g : values) {
                for (byte b : values) {
                    colors[index][0] = r;
                    colors[index][1] = g;
                    colors[index][2] = b;
                    index++;
                }
            }
        }
    }

    byte[][] getColors() {
        return colors;
    }
}
