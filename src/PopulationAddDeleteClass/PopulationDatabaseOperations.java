package PopulationAddDeleteClass;

import java.sql.*;

public class PopulationDatabaseOperations 
{
    
    //For accessing the database.
    private static String dbProtocol = "jdbc:derby:";
    private static String dbDirectory = "E:/CIS2206 - Business Programming I/";
    private static String dbName = "PopulationDB2"; //Database that is created. 
    private static String dbExtra = "";
    private static String userName = "Admin";
    private static String password = "MuCis";
    
    public static void setConnection(String dbDirectoryIn, String dbNameIn)
            //Passes in by Directory and Database Name
    {
    
        dbDirectory = dbDirectoryIn;
        dbName = dbNameIn;
    
    }
    
    public static void setConnection(String dbDirectoryIn, String dbNameIn, String userNameIn, String passWordIn)
            //Passes in by Directory, Database Name, User Name, and Password
    {
        dbDirectory = dbDirectoryIn;
        dbName = dbNameIn;
        userName = userNameIn;
        password = passWordIn;
    
    }
    
    public static void setConnection(String dbProtocolIn, String dbDirectoryIn, String dbNameIn,
                                    String dbExtraIn, String userNameIn, String passWordIn)
            //Passes in by all variables.
    {   
    
        dbProtocol = dbProtocolIn;
        dbDirectory = dbDirectoryIn;
        dbName = dbNameIn;
        dbExtra = dbExtraIn;
        userName = userNameIn;
        password = passWordIn;
    
    
    }
    
    private static Connection openConnection() throws SQLException
           //connects to database
    {
        String connectionURL = dbProtocol + dbDirectory + dbName + dbExtra;
        Connection connection1 = DriverManager.getConnection(connectionURL, userName, password);
        return connection1;
    }
    
    public static Towns retrieveAllTowns()
            //Retrieves all of towns in the datafile
    {
        String query1 = "SELECT * FROM POPULATION";
        //For query
        Towns towns = new Towns();
        try(Connection connection1 = openConnection())
        {
            Statement statement = connection1.createStatement();//connects to database
            ResultSet rs1 = statement.executeQuery(query1); //gets the results from the query
            
            while(rs1.next())//while there are results
            {
                Town townNext = new Town(); //for each town
                
                //Obtains values through each field
                townNext.setTownNumber(rs1.getInt(1));
                townNext.setTownName(rs1.getString(2));
                townNext.setCountyNumber(rs1.getInt(3));
                townNext.setRegionNumber(rs1.getInt(4));
                townNext.setPopulation(rs1.getInt(5));
                
                //after getting the values the record gets added to the town.
                towns.add(townNext);
            }
        }
        catch(SQLException f)
        {
            System.out.println(f.toString());
        }
         return towns; //Returns all of the towns in database.
    
    }
    
    public static Town retrieveTown(int townNumberIn)
            //Retrieves town by town number.
    {
    String query1 = "SELECT * FROM POPULATION WHERE TOWN_NUMBER = ? ";
    //SQL query for finding the town.
    //Since town number is the primary key you need to find it by that.
    //If you remember from Database Systems' class at RACC, primary keys
    //are the unique index.
    
    Town townNext = new Town();
    
    try(Connection connection1 = openConnection())
    {
        PreparedStatement statement = connection1.prepareStatement(query1);
        //We used the prepared statment object to find records we are passing in
        //by a specific field.
       
        
        statement.setInt(1, townNumberIn); //the value being search for. 
        //this statement means for the first argument, pass in the townNumber
        
        ResultSet rs1 = statement.executeQuery();
        
       
        if(rs1.next())
        {
            //Databases do not start with 0, they start with 1,
            //Go figure. XD
            //Anyway....this sets the record.
            townNext.setTownNumber(rs1.getInt(1));
            townNext.setTownName(rs1.getString(2));
            townNext.setCountyNumber(rs1.getInt(3));
            townNext.setRegionNumber(rs1.getInt(4));
            townNext.setPopulation(rs1.getInt(5));
            
        }
       
    }
    catch(SQLException e)
    {
        System.out.println(e.toString());
    }
    
    return townNext; //Returns the town selected
    }
    
    public static void insertTown(Town town)
            //Adding a new record
    {
        String insertStatementTown = "INSERT INTO POPULATION"
                + " (TOWN_NUMBER, TOWN_NAME, COUNTY_NUMBER, REGION_NUMBER, POPULATION)"
                + "VALUES(?,?,?,?,?)";
        
        try(Connection connection1 = openConnection())
        {
            PreparedStatement statement = connection1.prepareStatement(insertStatementTown);
            //For executing SQL command
            
            statement.setInt(1, town.getTownNumber());
            statement.setString(2, town.getTownName());
            statement.setInt(3, town.getCountyNumber());
            statement.setInt(4, town.getRegionNumber());
            statement.setInt(5, town.getPopulation());
            statement.executeUpdate();
        
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        
        }
    }
    
    public static void deleteTown(int townNumberIn)
            //Deleting a record
    {
        String deleteStatementTown = "DELETE FROM POPULATION WHERE TOWN_NUMBER = ? ";
        
        try(Connection connection1 = openConnection())
        {
            PreparedStatement statement = connection1.prepareStatement(deleteStatementTown);
            statement.setInt(1, townNumberIn);
            statement.executeUpdate();
        
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        
        }
    }
}
