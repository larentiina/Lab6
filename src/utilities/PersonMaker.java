package utilities;

import data.Color;
import data.Coordinates;
import data.Location;
import data.Person;
import exceptions.IllegalValueException;

import java.util.Scanner;

/**
 * builds an object of type person
 */
public class PersonMaker {
    private final PersonCollection collection;
    private final InputManager inputManager;

    public PersonMaker(PersonCollection collection, InputManager inputManager) {
        this.collection = collection;
        this.inputManager = inputManager;

    }


    /**
     * build an object of type person
     * @param person
     * @return person
     * @throws IllegalValueException
     */
    public Person PersonMake(Person person) throws IllegalValueException {
        if (person != null) {
            Scanner in = inputManager.getScanner();
            this.makeName(in, person);
            this.makeCoordinates(in, person);
            this.makePassportId(in,person);
            person.setLocation(this.makeLocation(in, new Location()));

            this.makeEyeColor(in, person);
            this.makeHairColor(in, person);
            this.MakeHeight(in, person);
            return person;
        } else {
            throw new NullPointerException();
        }

    }

    /**
     * set correct values of name field in Person
     * @param in
     * @param person
     * @throws IllegalValueException if the value was incorrect
     */
    public void makeName(Scanner in,Person person) throws IllegalValueException {
        while(true) {
            try {
                if(!inputManager.isScriptMode()) {
                    System.out.println("Введите имя>>>");
                }
                String name = in.nextLine();
                person.setName(name);
                break;
                }
            catch (IllegalValueException e) {
                if (!inputManager.isScriptMode()) {
                    System.out.println(e.getMessage());
                }
                else throw e;
            }
        }
    }

    /**
     * set correct values of coordinates field in Person
     * @param in
     * @param person
     * @throws IllegalValueException if the values was incorrect
     */
    public void makeCoordinates(Scanner in,Person person) throws IllegalValueException {
        while (true) {
            try {
                Coordinates coordinates = new Coordinates();
                if (!inputManager.isScriptMode()) {
                    System.out.println("Введите координату x>>>");
                }
                String x = in.nextLine();
                try {
                    coordinates.setX(Integer.parseInt(x));
                } catch (NumberFormatException e) {
                    throw new IllegalValueException("У координаты должен быть тип Integer");
                }
                if (!inputManager.isScriptMode()) {
                    System.out.println("Введите координату y>>>");
                }
                String y = in.nextLine();
                try {
                    coordinates.setY(Double.parseDouble(y));
                } catch (NumberFormatException e) {
                    throw new IllegalValueException("У координаты должен быть тип Double");
                }
                person.setCoordinates(coordinates);
                break;
            }
            catch (IllegalValueException e) {
                if(!inputManager.isScriptMode()){
                    System.out.println(e.getMessage());
                }else throw e;
            }
        }
    }

    /**
     * selection correct values of passportId field in Person
     * @param in

     * @throws IllegalValueException if the values was incorrect
     */
    public void makePassportId(Scanner in,Person person) throws IllegalValueException {
        while (true) {
            try {
                if (!inputManager.isScriptMode()) {
                    System.out.println("Введите id паспорта>>>");
                }
                String pas = in.nextLine();
                if (collection.AllPassportId().contains(pas)) {
                    System.out.println(pas);
                    throw new IllegalValueException("Данный Passport ID уже существует");
                }
                person.setPassportID(pas);
                break;
            }
            catch (IllegalValueException e) {
                if (!inputManager.isScriptMode()) {
                    System.out.println(e.getMessage());
                }
                else throw e;
            }
        }
    }



    /**
     * build an object of type location
     * @param in
     * @param location
     * @return correct value location
     */
    public Location makeLocation(Scanner in,Location location) throws IllegalValueException {
        while(true) {
            try {
                if (!inputManager.isScriptMode()) {
                    System.out.println("Введите координату локации x>>>");
                }
                String x = in.nextLine();
                try {
                    if(x.isEmpty()){
                        return null;
                    }
                    location.setX(Double.parseDouble(x));
                } catch (NumberFormatException e) {
                        throw new IllegalValueException("Тип у локации x должен быть Double");
                }
                if (!inputManager.isScriptMode()) {
                    System.out.println("Введите координату локации y>>>");
                }
                String y = in.nextLine();
                try {
                    location.setY(Long.parseLong(y));
                } catch (NumberFormatException e) {
                    throw new IllegalValueException("Тип у локации y должен быть Long");
                }
                if (!inputManager.isScriptMode()) {
                    System.out.println("Введите координату локации z>>>");
                }
                String z = in.nextLine();
                try {
                    location.setZ(Long.valueOf(z));
                } catch (NumberFormatException e) {
                    throw new IllegalValueException("Тип у локации z должен быть Long");
                }
                break;
            } catch (IllegalValueException e) {
                if(!inputManager.isScriptMode()) {
                    System.out.println(e.getMessage());
                }else
                    throw e;
            }
        }
        return location;
    }

    /**
     * set correct values of eyeColor field in Person
     * @param in
     * @param person
     * @throws IllegalValueException if values is incorrect
     */
    public void makeEyeColor(Scanner in,Person person) throws IllegalValueException{
        while(true){
            try {
                if (!inputManager.isScriptMode()) {
                    System.out.println("Введите цвет глаз>>>");
                    Color.listColor();
                }
                String color = in.nextLine();
                try {
                    if(color.isEmpty()){
                        person.setEyeColor(null);}
                    else{
                    person.setEyeColor(Color.valueOf(color));}
                    break;
                }catch(IllegalArgumentException e){
                    throw new IllegalValueException("Такого цвета глаза не могут быть");
                }
            }
            catch(IllegalValueException e){
                if(!inputManager.isScriptMode()){
                    System.out.println(e.getMessage());
                }
                else throw e;
            }
        }
    }

    /**
     * set correct values of hairColor field in Person
     * @param in
     * @param person
     * @throws IllegalValueException if the values was incorrect
     */
    public void makeHairColor(Scanner in,Person person) throws IllegalValueException{
        while(true) {
            try {
                if (!inputManager.isScriptMode()) {
                    System.out.println("Введите цвет волос>>>");
                    Color.listColor();
                }
                String color = in.nextLine();
                try {
                    if(color.isEmpty()){
                        person.setHairColor(null);}
                    else
                    {person.setHairColor(Color.valueOf(color));}
                    break;
                }catch(IllegalArgumentException e){
                    throw new IllegalValueException("Такого цвета волосы не могут быть");
                }
            }
            catch (IllegalValueException e) {
                if (!inputManager.isScriptMode()) {
                    System.out.println(e.getMessage());
                }
                else throw e;
            }
        }
    }

    /**
     * set correct values of height field in Person
     * @param in
     * @param person
     * @throws IllegalValueException if value was incorrect
     */
    public void MakeHeight(Scanner in,Person person) throws IllegalValueException {
        while (true) {
            try {
                if (!inputManager.isScriptMode()) {
                    System.out.println("Введите рост>>>");
                }
                String height = in.nextLine();
                try {
                    person.setHeight(Double.parseDouble(height));
                    break;
                } catch (NumberFormatException e) {
                    throw new IllegalValueException("Рост должен иметь тип Double");
                }
            }
            catch (IllegalValueException e) {
                if (inputManager.isScriptMode()) {
                    throw e;
                }
                else {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
