import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;
import com.csvreader.CsvReader;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

// Task I read data from csv and reformat it

public class DataReader {
    private String csv_file;
    private Vector<ArrayList<String>> result = new Vector<ArrayList<String>>();
    public DataReader(String csv_file) throws IOException {
        this.csv_file = csv_file;
        this.result = new Vector<ArrayList<String>>();
        this.read(this.csv_file);
    }
    private void read(String csv) throws IOException {
        CsvReader cr = new CsvReader(csv);
        String[] l = {};
        while(cr.readRecord()){
            if(cr.getCurrentRecord() > 0){
                l = cr.getValues();
                this.result.add(new ArrayList<String>(Arrays.asList(l)));
            }
        }

    }

    public HashMap<String, Vector<String>> getFormatResult(){
        HashMap<String, Vector<String>> formatResult = new HashMap<>();
        for(ArrayList<String> item: this.result){
            String title = item.get(1);
            Vector<String> players = new Vector<>();
            String cast = item.get(2);
            ArrayList Cast = (ArrayList) JSONValue.parse(cast);
            for(int i=0; i<Cast.size(); i++){
                if(!Cast.get(i).equals("[]")){
                    JSONObject character = (JSONObject) JSONValue.parse(Cast.get(i).toString());
                    String name = character.get("name").toString().toLowerCase();  // toLowerCase: for regardless of upperCase/lowerCase
                    players.add(name);
                }
            }
            formatResult.put(title, players);
        }
        return formatResult;
    }
}
