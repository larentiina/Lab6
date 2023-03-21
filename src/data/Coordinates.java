package data;

/**
 * data class
 */
//@XmlType(propOrder = {"x","y"})
public class Coordinates {
    private int x;
    private double y;
    //@XmlElement
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    //@XmlElement
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

    /**
     *
     * @return string which includes values of x and y
     */
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
