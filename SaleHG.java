import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.*;
import java.util.*;

public class SaleHG {
	
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

		HashMap<Integer, HashSet<Pair>> sales_company_insiderPair_map = new HashMap<Integer, HashSet<Pair>>(); 
		HashMap<Integer, HashMap<Integer, TreeSet<Date>>> sales_company_insiderTradeDates_map = new HashMap<Integer,HashMap<Integer, TreeSet<Date>>>();

		// for each stock id get all the PURCHASE trades and populate key=InsiderID val=Set of all his/her trade dates
		for(Integer stockid : stockids)
		{
			HashMap<Integer, TreeSet<Date>>  inid_date_map = new HashMap <Integer, TreeSet<Date>>();
			String query2 = "select InsiderId,Date from insidertrades2_demo where Action = 'Sell'  AND  StockId = " + stockid;
			rs = statement.executeQuery(query2);
			while(rs.next())
			{
				int id = rs.getInt("InsiderId");
				Date d = rs.getDate("Date");
				if(inid_date_map.containsKey(id))
				{
					inid_date_map.get(id).add(d);
				}
				else
				{
					TreeSet<Date> hsd = new TreeSet<Date>();
					hsd.add(d);
					inid_date_map.put(id, hsd);
				}
			}
			sales_company_insiderTradeDates_map.put(stockid, inid_date_map);
		}
		connection.close();
		
		for(Integer companySale : sales_company_insiderTradeDates_map.keySet())
		{
			System.out.println("------------------------------------------------------------------------------------");
			System.out.println(companySale);
			System.out.println("------------------------------------------------------------------------------------");
			HashMap<Integer, TreeSet<Date>> insiderMap = sales_company_insiderTradeDates_map
						.get(companySale);
			
				compareSimilaritys(insiderMap);
		}

	
}


public static void compareSimilaritys(
		HashMap<Integer, TreeSet<Date>> inid_date_map) {
	int duplicateDates=1 ;
	int noDuplicateDates;
	
	ArrayList<Integer> allInsiders1 = new ArrayList<Integer>();
	allInsiders1.addAll(inid_date_map.keySet());

	HashMap<Date, String> dateCount = new HashMap<Date, String>();
	for (Iterator iterator = allInsiders1.iterator(); iterator.hasNext();) {
		int count = 1;
		int insiderId= (Integer) iterator.next();
		TreeSet<Date> currentDate = inid_date_map.get(insiderId);
		for (Iterator dateIterator = currentDate.iterator(); dateIterator
				.hasNext();) {
			Date dateTemp= (Date) dateIterator.next();
			if (dateCount.containsKey(dateTemp)) {
				String[] countTemp = dateCount.get(dateTemp).split("-");
				int countInc =Integer.parseInt(countTemp[0]);
				int countIncTemp =++countInc;

				String insiderIds = countTemp[1]+","+insiderId;
				
				dateCount.put(dateTemp, countIncTemp+"-"+insiderIds);
				
			} else {
				
				dateCount.put(dateTemp, count+"-"+insiderId);
			}
		}
		
	}
	
	for (Date name: dateCount.keySet()){

        String key =name.toString();
        String value = dateCount.get(name).toString();  
        System.out.println(key + " " + value);  


	} 
}

}
