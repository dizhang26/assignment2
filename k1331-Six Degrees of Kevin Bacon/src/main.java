import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    public static void main(String[] args) throws IOException {
        System.out.println("Reading csv file...");
        DataReader dr = new DataReader(args[0]);
        System.out.println("Initializing Data Structrue, This may take some time...");
        BFS bfs = new BFS(dr.getFormatResult());
        System.out.println("------------------start----------------");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("Actor 1 name(enter q to exit): ");
            String actor1 = br.readLine().strip().toLowerCase();
            if(actor1.equals("q")){
                break;
            }
            if(bfs.hasPlayer(actor1)){
                System.out.println("Actor 2 name(enter q to exit): ");
                String actor2 = br.readLine().strip().toLowerCase();
                if(actor2.equals("q")){
                    break;
                }
                if(bfs.hasPlayer(actor2)){
                    bfs.path(actor1, actor2);
                }
                else{
                    System.out.println("No such actor");
                }
            }
            else{
                System.out.println("No such actor");
            }
        }
    }
}
