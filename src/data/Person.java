package data;

import com.sun.istack.NotNull;
import exceptions.IllegalValueException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.time.ZonedDateTime;
import java.util.Random;

/**
 * main data class
 */
@XmlRootElement
//@XmlType(propOrder = {"id","name","coordinates","height","passportID","eyeColor","hairColor","location"})
public class Person implements Comparable<Person> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull
    @XmlElement(name="name",required = true)
    private String name;
    @NotNull
    @XmlElement(name="coordinates",required = true)
    private Coordinates coordinates;
    @XmlTransient
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @XmlElement(name = "height",required = true)
    private double height;
    @NotNull
    @XmlElement(name="passportID",required = true)
    private String passportID;
    @XmlElement(name="eyeColor",required = true)
    private Color eyeColor;

    @XmlElement(name="hairColor",required = true)
    private Color hairColor;

    @XmlElement(name="location",required = true)
   private Location location;
    public final static int PERSON_MIN_HEIGHT=0;
    public final static int PERSON_MAX_HEIGHT=300;


    public Person() {
        this.id = new Random().nextInt(Integer.MAX_VALUE);
        this.creationDate = java.time.ZonedDateTime.now();
    }

    /**
     * создаём новый объект, id и creation date генерируются
     * @param name name of person (cannot be empty and null)
     * @param coordinates coordinates of person (cannot be null)
     * @param height height of person (value can be > 0 and < 300)
     * @param passportID passport id of person (can be uniq and cannot be empty and null)
     * @param eyeColor eye color of person
     * @param hairColor hair color of person
     * @param location location of person
     */
    @SuppressWarnings("parameternumber")
    public Person(String name, Coordinates coordinates,double height, String passportID, Color eyeColor, Color hairColor, Location location) throws IllegalValueException {
            this.id = new Random().nextInt(Integer.MAX_VALUE);
            this.setName(name);
            this.setCoordinates(coordinates);
            this.creationDate = java.time.ZonedDateTime.now();
            this.setHeight(height);
            this.setPassportID(passportID);
            this.setEyeColor(eyeColor);
            this.setHairColor(hairColor);
            this.setLocation(location);

    }
    @XmlTransient
    public Integer getId() {
        return id;
    }

    @XmlTransient
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }
    /**
     * set name of person
     * @param name must be a string
     * @throws IllegalValueException if the name does not start with a capital letter, it contains characters other than letters
     */
    public void setName(String name) throws IllegalValueException {
        if(name!=null && name.matches("([A-Z]|[А-Я])([a-z]|[а-я])+"))
        this.name = name;
        else throw new IllegalValueException("имя не может быть null, должно начинаться с заглавной буквы и содержать только буквы, содержать больше одной буквы");
    }
    @XmlTransient
    public Coordinates getCoordinates() {
        return coordinates;
   }

    public void setCoordinates(Coordinates coordinates) throws IllegalValueException {
        if(coordinates!=null){
        this.coordinates = coordinates;}
        else throw new IllegalValueException("Координаты не могут быть null");
    }

    /**
     * set height of person
     * @param height must be a number
     * @throws IllegalValueException if value < 0 and value > 300
     */
    public void setHeight(double height) throws IllegalValueException{
        if(height>PERSON_MIN_HEIGHT & height<PERSON_MAX_HEIGHT)
        this.height = height;
        else throw new IllegalValueException("Рост должен быть больше 0 и меньше 300");

    }

    /**
     * set eye color of person
     * @param eyeColor must be a constant of enum Color or null
     * @throws IllegalValueException if type of eyeColor is not Color
     */
    public void setEyeColor(Color eyeColor) throws IllegalValueException {
        try {
            this.eyeColor = eyeColor;
        }catch(IllegalArgumentException e){
            throw new IllegalValueException("Такого цвета глаза не могут быть.");
        }

    }
    /**
     * set hair color of person
     * @param hairColor must be a constant of enum Color or null
     * @throws IllegalValueException if type of hairColor is not Color
     */
    public void setHairColor(Color hairColor) throws IllegalValueException{
        try {
            this.hairColor = hairColor;
        }catch(IllegalArgumentException e){
            throw new IllegalValueException("Такого цвета волосы не могут быть.");
        }

    }
    @XmlTransient
    public String getPassportID() {
        return passportID;
    }

    /**
     * set passport if of person
     * @param passportID must be a string
     * @throws IllegalValueException if line is empty and line is null
     */
    public void setPassportID(String passportID) throws IllegalValueException {
        if(passportID==null || passportID.trim().isEmpty())
        {
            throw new IllegalValueException("Passport Id не может быть пустой строкой");
        }
        else this.passportID = passportID;
    }
    @XmlTransient
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) throws IllegalValueException {
        this.location=location;
        if(location!=null) {
            this.location.setY(location.getY());
            this.location.setX(location.getX());
            this.location.setZ(location.getZ());
        }
    }
    @XmlTransient
    public String getName() {
        return name;
    }
    @XmlTransient
    public double getHeight() {
        return height;
    }
    @XmlTransient
    public Color getEyeColor() {
        return eyeColor;
    }
    @XmlTransient
    public Color getHairColor() {
        return hairColor;
    }

    /**
     *
     * @return string which includes values of all fields (id, name, coordinates, creation date, height, passportID, eye color, hair color and location)
     */
    @Override
    public String toString() {
        if(this.location!=null) {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", " + coordinates.toString() +
                    ", creationDate=" + creationDate +
                    ", height=" + height +
                    ", passportID='" + passportID + '\'' +
                    ", eyeColor=" + eyeColor +
                    ", hairColor=" + hairColor +
                    ", " + location.toString() +
                    '}';
        }else return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", " + coordinates.toString() +
                ", creationDate=" + creationDate +
                ", height=" + height +
                ", passportID='" + passportID + '\'' +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", location=" + null +
                '}';
    }

    /**
     * compares objects by id number
     * @param o the object to be compared.
     * @return difference between this object's and other's id
     */
    @Override
    public int compareTo(Person o) {
        return (int) (this.height-o.getHeight());
    }

}


