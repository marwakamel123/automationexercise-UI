package fileReaderManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ReadFromFiles {

    public static Properties getAllProperties(String fileName) {
        Properties fileProperties = null;
        try {
            FileReader fileReader = new FileReader( "./src/main/resources/propertyFiles/" + fileName) ;
            fileProperties=new Properties();
            fileProperties.load(fileReader);
        }
        catch (IOException ioException)
        {
            System.out.println("There is io exception happened");
        }
        return fileProperties;
    }
    public static Object getPropertyByKey(String fileName, String propertyKey) {
        Properties fileProperties = getAllProperties (fileName);
        return fileProperties.getProperty(propertyKey);
    }

    public static JSONObject getJsonObject(String fileName)
    {
        JSONParser jsonParser = new JSONParser();
        Object obj = null ;
        try (FileReader reader = new FileReader(".\\src\\test\\java\\testData\\" + fileName))
        {
             obj = jsonParser.parse(reader);
           // obj = parser.parse(new FileReader("Absolute Path" + fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }
    public static Object getJsonValueByKey(String fileName, String jsonKey) {
        JSONObject jsonObject = getJsonObject(fileName);
        String value = (String) jsonObject.get(jsonKey);
        return value;
    }
    public static Object getJsonObjByKey(String fileName, String jsonKey) {
        JSONObject jsonObject = getJsonObject(fileName);
        return jsonObject.get(jsonKey);
    }

    public static JSONArray getJsonArrValueByKey(String fileName, String jsonKey) {
        JSONObject jsonObject = getJsonObject(fileName);
        JSONArray companyList = (JSONArray) jsonObject.get(jsonKey);
        return companyList;
    }

}
