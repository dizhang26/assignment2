import java.util.*;

// Task II search path

public class BFS {
    // Graph: player is V, and movie is E
    private HashMap<String, Vector<String>> players;  // key:player, value:movies
    private HashMap<String, Vector<String>> movies;   // key:movie, value:players
    private Queue<String> searchQueue;  // BFS use queue
    private ArrayList<String> hasSearched;  // mark the V witch had been searched
    private HashMap<String, String> vev;  // child -> parent ; the relationship between V, which can track path between P1 and P2
    public BFS(HashMap<String, Vector<String>> v){  // HashMap<movie, players>
        this.players = new HashMap<String, Vector<String>>();
        this.movies = v;
        this.searchQueue = new LinkedList<String>();
        this.hasSearched = new ArrayList<>();
        this.vev = new HashMap<>();
        this.init(v);
    }
    private void init(HashMap<String, Vector<String>> v){
        for(Map.Entry<String, Vector<String>> e: v.entrySet()){
            String title = e.getKey();
            Vector<String> players = e.getValue();
            for(String player: players){
                if(this.players.containsKey(player)){
                    if(!this.players.get(player).contains(title)){
                        this.players.get(player).add(title);
                    }
                }
                else{
                    Vector<String> m = new Vector<>();
                    m.add(title);
                    this.players.put(player, m);
                }
            }
        }
    }
    // return all the given player'movies in a Vector
    public Vector<String> getMovies(String player){
        return this.players.get(player);
    }
    // return all the players in a movie
    public Vector<String> getPlayers(String movie){
        return this.movies.get(movie);
    }
    public void path(String player1, String player2){
        this.searchQueue.offer(player1);
        while(!this.searchQueue.isEmpty()){
            String parent = this.searchQueue.poll();
            this.hasSearched.add(parent);
            Vector<String> has_movie = this.getMovies(parent);
            for(String one_movie: has_movie){
                Vector<String> other_actors = this.getPlayers(one_movie);
                if(other_actors.contains(player2)){
                    this.searchQueue.clear();
                    this.vev.put(player2, parent);
                    break;
                }
                else{
                    for(String child: other_actors){
                        if(!this.hasSearched.contains(child)&&(!this.vev.containsKey(child))){
                            this.vev.put(child, parent);
                            this.searchQueue.offer(child);
                        }
                    }
                }
            }
        }
        // below , tracking path between player1 and player2
        if(this.vev.containsKey(player2)){
            String parent = player2;
            System.out.println("path between "+player1+" and "+player2+" is: ");
            Stack<String> path = new Stack<>();
            while(!parent.equals(player1)){
                path.push(parent);
                System.out.println(parent);
                parent = this.vev.get(parent);
            }
            path.push(player1);
            // and then print the path to terminal
            while(!path.isEmpty()){
                String item = path.pop();
                if(!item.equals(player2)){
                    System.out.print(item+" --> ");
                }
                else{
                    System.out.println(item);
                }
            }
        }
        else{
            System.out.println("No path");
        }
    }
    public boolean hasPlayer(String player){
        return this.players.containsKey(player);
    }
}
