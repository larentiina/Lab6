package utilities;

import exceptions.IllegalValueException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * class that converts xml file to a Hashtable type collection and a collection to xml file
 */
public class ParserXml {
    /**
     *converts xml-file to the collection person
     * @param file
     * @return collection person
     * @throws JAXBException if xml-file cannot be converted to java object
     */
    public static PersonCollection convertToHt(File file) throws JAXBException, IllegalValueException {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonCollection.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (PersonCollection) unmarshaller.unmarshal(file);
        } catch (JAXBException e){
            throw new JAXBException("Возникла ошибка при конвертации коллекции из xml-файла");
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Указанный файл в переменной окружения не найден");
        } catch (NullPointerException e){
            throw new IllegalValueException("Коллекция содержит неполные данные");
        }
    }

    /**
     * converts the person collection to the xml file
     * @param personCollection
     * @param filename
     * @throws JAXBException
     * @throws IOException
     */
    public static void convertToXml(PersonCollection personCollection, String filename) throws JAXBException, IOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PersonCollection.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            FileWriter fileWriter = new FileWriter(filename);
            marshaller.marshal(personCollection, fileWriter);
            fileWriter.close();
        }catch(JAXBException|IOException e){
            System.out.println("Возникла ошибка при конвертации в Xml файл");
        }




    }
}
