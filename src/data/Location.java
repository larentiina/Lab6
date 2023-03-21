package data;

import exceptions.IllegalValueException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * data class
 */
@XmlType(propOrder = {"x","y","z"})
public class Location implements Comparable<Location> {
    private double x;
    private long y;
    private Long z; //Поле не может быть null

    public Location(double x, long y, Long z) throws IllegalValueException {
            this.x = x;
            this.y = y;
            this.setZ(z);

    }

    public Location() {
    }
    @XmlElement
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
    @XmlElement
    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }
    @XmlElement
    public Long getZ() {
        return z;
    }

    public void setZ(Long z) throws IllegalValueException {
        if(z!=null){
        this.z = z;}
        else throw new IllegalValueException("Локация z не может быть null");
    }

    /**
     *
     * @return string includes values of x, y, z
     */
    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    /**
     * compares objects by x and y
     * @param o the object to be compared.
     * @return difference between this object's and other's x and y
     */
    @Override
    public int compareTo(Location o) {
        double value = this.x-o.getX();
        if(value==0){
            value=this.y-o.getY();
        }
        return (int) value;
    }
}
