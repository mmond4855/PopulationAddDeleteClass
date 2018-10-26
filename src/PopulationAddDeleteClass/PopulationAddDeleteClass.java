package PopulationAddDeleteClass;


public class PopulationAddDeleteClass
{

    public static void main(String[] args)
    {
        //For connecting to database.
       String dbDirectory = "E:/CIS2206 - Business Programming I/";
       String dbName = "PopulationDB2";
       
       
       int TownNumberIn = 19;
       
       //Connects to database.
       PopulationDatabaseOperations.setConnection(dbDirectory, dbName);
       //Retrieves all towns in database
       Towns towns = PopulationDatabaseOperations.retrieveAllTowns();
       
       //Prints the towns
       printTowns(towns);
       
       //Deletes a town
       PopulationDatabaseOperations.deleteTown(TownNumberIn);
       //Retrieves town after query executed. 
       towns = PopulationDatabaseOperations.retrieveAllTowns();
       //Prints towns.
       printTowns(towns);
       
        System.out.println("DATABASE COMPLETE");
       
        
    }
    
    static void printTowns(Towns towns)
    {
        for(int i = 0; i < towns.size(); i++)
        {
            System.out.println((towns.get(i)).getTownNumber());
            System.out.println((towns.get(i)).getTownName());
            System.out.println((towns.get(i)).getCountyNumber());
            System.out.println((towns.get(i)).getRegionNumber());
            System.out.println((towns.get(i)).getPopulation());
            System.out.println();
        }
    
    }
}
