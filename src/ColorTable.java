public class ColorTable {
    private byte[][] colors;

    public ColorTable() {
        this.colors = new byte[64][3];
    }

    byte[][] getColors() {
        return colors;
    }
}
