package hw7;
import java.io.*;


public class CampusPaths {
    private static CampusPathLogic c;


    // Abstraction Function:Equals
    // CampusPaths represents the User Interface for finds paths between buildings on the RPI Campus.
    //
    // if CampusPathsLogic c == NULL :
    //     The graph was not initialized correctly and will not perform as expects
    //
    // In other words:
    //  * c must be initialized to a CampusPathsLogic argument (it can be an empty object but not NULL


    /**
     * Constructor for CampusPaths
     * @effects initializes the CampusPathsLogic variable to a new CampusPathsLogic object
     */
    public CampusPaths() {
        c = new CampusPathLogic();
        checkRep();
    }

    /**
     * Checks the validity of the nodes, then starts shortest path calculation is valid
     * @param n1 - source node
     * @param n2 - destination node
     * @effects checks that nodes exist in graph and converts to id's (if needed)
     */
    public static void path_helper(String n1, String n2){
        if(!c.isValid(n1)){
            System.out.print("Unknown building: [" + n1 + "]\n");
            return;
        }
        if(!c.isValid(n2)){
            System.out.print("Unknown building: [" + n2 + "]\n");
            return;
        }


        String i1 = n1, i2 = n2;
        if(!n1.matches("-?\\d+")){ i1 = c.getID(n1); }
        if(!n2.matches("-?\\d+")){ i2 = c.getID(n2); }

        c.ShortestPath(i1, i2);
    }

    /**
     * Function that makes up the Command Line User Interface for RPI Paths - runs until user enters 'q' or if they
     *          interact incorrectly and it throws an IOException.
     * @effects Takes in commands from the user and routes to the correct function for that operation.
     * @throws IOException - if the user enters a command that is invalid or at the incorrect time
     */
    public static void UI() throws IOException {
        // Starting the user input part
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String cmd = reader.readLine();
        while(!cmd.equals("q")) {
            if ("b".equals(cmd)) {
                c.listBuildings();

            } else if ("r".equals(cmd)) {
                System.out.print("First building id/name, followed by Enter: ");
                String n1 = reader.readLine();
                System.out.print("Second building id/name, followed by Enter: ");
                String n2 = reader.readLine();
                path_helper(n1, n2);

            } else if ("m".equals(cmd)) {
                String cmds = "b -- lists all buildings\nr -- allows search for shortest path between buildings\nq -- quits the program\nm -- lists all commands\n";
                System.out.println(cmds);
            }else {
                System.out.print("Unknown option\n");
            }

            cmd = reader.readLine();
        }
    }

    /**
     * Main function for RPI Campus Paths
     * @param o null object to deal with instructor cases
     * @modifies c - adds the nodes and edges from the file to the CampusPathLogic object b
     * @effects constructs the CampusPathsLogic object and start the UI experience for the user.
     * @throws Exception - if the file cannot be found.
     */
    public static void main(Object o) {
        c = new CampusPathLogic();
        String node_file = "data/RPI_map_data_Nodes.csv";
        String edge_file = "data/RPI_map_data_Edges.csv";

        try {
            c.NodeParser(node_file);
            c.EdgeParser(edge_file);
            UI();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * CheckRep function to ensure that the CampusLogicPaths object is never null.
     * @throws RuntimeException if the CampusLogicPaths object is null
     */
    private void checkRep() throws RuntimeException{
        if(c==null){
            throw new RuntimeException("Initialized Incorrectly: c == null");
        }
    }

}


