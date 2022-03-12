import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //имя JSON-файла с исходными данными о сотрудниках
        String fileName = "new_data.json";
        //Преобразование JSON-файла в JSON-строку
        String json = readString(fileName);

        //Преобразование JSON-строки в список объектов Employee
        List<Employee> list = jsonToList(json);
        //Вывод списка объектов Employee(сотрудников) в консоль
        printList(list);

    }

    //Преобразование JSON-файла fileName в JSON-строку
    private static String readString(String fileName) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONArray jsonObjects = (JSONArray) obj;
            //преобразование информации о массиве JSON-объектов в строку
            // и возврат этой строки
            return jsonObjects.toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Преобразование JSON-строки jsonText в список объектов Employee
    private static List<Employee> jsonToList(String jsonText) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Employee> employeeList = gson.fromJson(jsonText, new TypeToken<List<Employee>>() {
        }.getType());
        return employeeList;
    }

    //Вывод списка объектов Employee(сотрудников) в консоль
    private static void printList(List<Employee> list) {
        for (Employee emp : list) {
            System.out.println(emp.toString());
        }
    }
}
