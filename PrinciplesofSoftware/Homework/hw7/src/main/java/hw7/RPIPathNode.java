package hw7;
import java.lang.Math;

public class RPIPathNode {
     private final String name;
     private final Integer id;
     private final Double x;
     private final Double y;

    // Abstraction Function:
    // RPIPathNode represents a building at RPI's campus. It has a location (x, y), name and ID
    // None of these variable should be null, as that would indicate that it is not a true point at RPI.
    //
    // if name, id, x, or y == null:
    //      it is missing vital information needed to register the node
    //
    // In other words:
    //  * the nodes should contain all the information that identifies buildings at RPI



    /**
     * Constructor fof the RPI Path node - a node to store everything specifically for RPI paths
     * @param n - name of the building
     * @param building - id of the building
     * @param xc - x-coordinate of the building
     * @param yc - y-coordinate of the building
     * @effects the private variable above by initializing them to the specified values
     */
     public RPIPathNode(String n, String building, String xc, String yc){
         name = n;
         id = Integer.valueOf(building);
         x = Double.valueOf(xc);
         y = Double.valueOf(yc);
         checkRep();
     }

    /**
     * @effects calculates the Euclidean distance between two buildings
     * @param n2 - second RPIPathNode we are calculating the distance between
     * @return d - the double value of the distance between the two buildings
     */
     public double distance(RPIPathNode n2){
         double xDiff = Math.pow((n2.getX() - this.x), 2);
         double yDiff = Math.pow((n2.getY() - this.y), 2);
         double d = Math.sqrt(yDiff+xDiff);
         return d;
     }

    /**
     * @effects Calculates the angle between two buildings
     * @param n2 - the second node we are calcualting distnace between
     * @return angle - double value of the angle
     */
     public double getAngle(RPIPathNode n2){
         double angle = Math.toDegrees(Math.atan2(n2.getY()-this.y, n2.getX()-this.x));
         if(angle < 0){
             angle += 360;
         }
         return angle;
     }

    /**
     * @effects calculates the direction to travel from this node to n2
     * @param n2 - the destination node we are traveling to
     * @return the string of the direction one needs to travel
     */
     public String getDirection(RPIPathNode n2){
         double a = this.getAngle(n2);
         String directions[] = {"East", "SouthEast", "South", "SouthWest", "West", "NorthWest", "North", "NorthEast"};

         Long d = Math.round( ((a % 360) / 45));
         return directions[d.intValue() % 8];
     }

    /**
     * Getter function for the x-coordinate
     * @return the double value of the x-coordinate
     */
     public double getX(){
         return this.x;
     }

    /**
     * Getter function for the y-coordinate
     * @return the double value of the y-coordinate
     */
     public double getY(){
        return this.y;
     }

    /**
     * Getter function for the name of the node
     * @return the string of the name of this node
     */
     public String getName(){
         return this.name;
     }

    /**
     * Checks the representation Invariant for the node class - ensures that everything is defined.
     */
     private void checkRep(){
         if(name == null){
             throw new RuntimeException("Name is Null");
         }
         if(id == null){
             throw new RuntimeException("Building ID is Null");
         }
         if(x == null){
             throw new RuntimeException("x-coordinate is null");
         }
         if(y == null){
             throw new RuntimeException("y-coordinate is Null");
         }
     }
}
