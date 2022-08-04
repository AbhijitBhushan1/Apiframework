import Utils.ActionMethods;
import Utils.Common;
import Utils.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.PrintWriter;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

@Listeners(Common.class)
public class UserTest {
    @Test
    @Description("User Test for all the user")
    @Severity(SeverityLevel.BLOCKER)
    public void getUser() throws IOException {
        Map<String,String> header=new HashMap<>();
        header.put("Content-Type","application/json");
        Response response= ActionMethods.getMethod(header);
        writeToCSV(response.getResponseBody());
        writingToCSVFile(response.getResponseBody());
        JSONArray jsonArray=new JSONArray(response.getResponseBody());
        for (Object object:jsonArray)
        {
            if(object instanceof JSONObject)
            {
                System.out.println(((JSONObject) object).get("gender"));
            }
        }
        Assert.assertEquals(200,response.getStatusCode()," failed");
    }
    public void writeToCSV(String response) throws IOException {
        JsonNode jsonTree = new ObjectMapper().readTree(response);
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(new File("/Users/abhijitbhushan/Documents/vauldAutomation/ApiFramework/src/main/java/Utils/data.csv"), jsonTree);

    }
    public void writingToCSVFile(String response) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
       JsonNode data=objectMapper.readTree(response);
       Iterator<JsonNode> fields=data.elements();
       while (fields.hasNext())
       {
          JsonNode jsonNod=fields.next();
         if(jsonNod.isObject())
         {
             JSONObject jsonObject=new JSONObject(jsonNod);
             if(jsonObject instanceof JSONObject)
             {
                 System.out.println(jsonObject);
             }
         }
         else if(jsonNod.isArray())
         {
             JSONArray jsonArray=new JSONArray(jsonNod);
             for (Object object:jsonArray)
             {
                 if (object instanceof JSONObject)
                 {
                     System.out.println("objects are : "+ ((JSONObject) object).get("gender"));
                 }
             }
         }
       }
    }

}
