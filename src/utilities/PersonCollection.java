package utilities;

import data.Person;
import exceptions.IllegalKeyException;
import exceptions.IllegalValueException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 * class that manages a collection of people
 */
@XmlRootElement(name = "persons")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonCollection {
    @XmlElement(name = "person")
    private Hashtable<Integer, Person> collection = new Hashtable<>();
    private final Date dateOfInitialization = new Date();

    public Hashtable<Integer, Person> getPersons() {
        return collection;
    }


    /**
     * add element with it's key into collection
     * @param value
     * @param person
     */
    public void addElement(Integer value, Person person) {
        if(collection.put(value,person)==null) {
            System.out.println("Элемент добавлен");
        }else System.out.println("Элемент заменён");

    }

    /**
     * deletes element from the collection by its key
     * @param key
     * @throws IllegalKeyException if element with such key does not exist
     */
    public void RemoveElement(Integer key) throws IllegalKeyException {
        if(collection.remove(key)!=null){
            System.out.println("Элемент удалён");
        } else throw new IllegalKeyException("Элемент с таким ключом отсутствует");

    }

    /**
     * changes an element of a certain key
     * @param key
     * @param value
     */

    public void Replace(Integer key, Person value) throws IllegalKeyException {
        if(collection.replace(key, value)!=null)
        {System.out.println("Элемент изменён");}
        else throw new IllegalKeyException("Данный ключ отсутствует");

    }

    /**
     *
     * @return an array of ID passports of all elements of the collection
     */
    public ArrayList<String> AllPassportId(){
        ArrayList<String> passportIds = new ArrayList<>();
        for (Person person:
             collection.values()) {
            passportIds.add(person.getPassportID());
        }
        return passportIds;
    }

    public Date getDateOfInitialization() {
        return dateOfInitialization;
    }

    /**
     *
     * @return sum of height fo all elements of the collection
     */
    public Double SumOfHeight(){
        double sumOfHeight = 0;
        for (Person p :
                collection.values()) {
            sumOfHeight += p.getHeight();
        }
        return sumOfHeight;
    }

    /**
     *
     * @param id
     * @return element by its key
     */
    public Person searchById(int id){
        for (Person p: collection.values()) {
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }


    /**
     * remove all elements from the collection
     */
    public void clearCollection(){
        collection.clear();
    }

    /**
     * set collection and check the data in the collection for validity
     * @param collection must be not null
     * @throws IllegalValueException
     */
    public void setCollection(Hashtable<Integer, Person> collection) throws IllegalValueException {
            for (Person person : collection.values()) {
                person.setName(person.getName());
                person.setPassportID(person.getPassportID());
                person.setCoordinates(person.getCoordinates());
                person.setLocation(person.getLocation());
                person.setHeight(person.getHeight());
            }
                this.collection = collection;


    }
}


