import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * 
 */

/**
 * @author Divya
 *
 */
public class InsiderSubsets {
	
	public static void main(String[] args) throws SQLException, FileNotFoundException {

		// get jdbc connection
		Connection connection = JDBCMySQLConnection.getConnection();

		// get stockids (distinct companies)
		HashSet<Integer> stockids = new HashSet<Integer>();
		Statement statement = connection.createStatement();
		String query = "select distinct StockId from insidertrades2_demo"; // get stock ids
		ResultSet rs = statement.executeQuery(query);

		while(rs.next())
		{
			stockids.add(rs.getInt("StockId"));
		}
		
		PrintWriter pwrp = new PrintWriter(new File("All_Subsets.csv"));
		pwrp.println("CompanyId,Insider_Subsets");
		for(Integer stockid : stockids)
		{
			HashSet<Integer> insiderids = new HashSet<Integer>();
			String query2 = "select distinct(InsiderId) from insidertrades2_demo where Action = 'Buy'  AND  StockId = " + stockid;
			rs = statement.executeQuery(query2);
			while(rs.next())
			{
				insiderids.add(rs.getInt("InsiderId"));
			}
			System.out.println(insiderids);
			ArrayList<Integer> allinsiders = new ArrayList<Integer>(insiderids);
			ArrayList<ArrayList<Integer>> lists = getSubsets(allinsiders);
			pwrp.println(stockid + "," + lists.toString());
		}
		pwrp.close();
		connection.close();
	}

	private static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> SubList) {

		if (SubList.size() > 0) {
		 ArrayList<ArrayList<Integer>> list = addToList(SubList.remove(0),SubList);
	        return list;
	    } else {
	        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	        list.add(SubList);
	        return list;
	    }
	}
	
	private static ArrayList<ArrayList<Integer>> addToList(
	        Integer firstElement, ArrayList<Integer> SubList) {
	    ArrayList<ArrayList<Integer>> listOfLists = getSubsets(SubList);
	    ArrayList<ArrayList<Integer>> superList = new ArrayList<ArrayList<Integer>>();
	    for (ArrayList<Integer> iList : listOfLists) {
	        superList.add(new ArrayList<Integer>(iList));
	        iList.add(firstElement);
	        superList.add(new ArrayList<Integer>(iList));
	    }
	    return superList;
	}
}
