/**
 * class to construct a hypergraph for the stockId 32570 using lcs threshold >= 5
 */

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import edu.uci.ics.jung.graph.SetHypergraph;

/**
 * @author Divya Vajja
 * 		   G00996243
 *
 */
public class SaleHGraph7 {
	
	public static void main(String[] args) throws SQLException, FileNotFoundException {

		// get jdbc connection
		Connection connection = JDBCMySQLConnection.getConnection();

		Statement statement = connection.createStatement();
		
		HashMap<Integer, TreeSet<Date>>  inid_date_map = new HashMap <Integer, TreeSet<Date>>();
		String query = "select InsiderId,Date from insidertrades2_demo where Action = 'Sell'  AND  StockId = 32570 " ;
		ResultSet rs = statement.executeQuery(query);
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
		connection.close();
		
		HashMap<HashSet<Integer>,TreeSet<Date>> lcs2ids = new HashMap<HashSet<Integer>,TreeSet<Date>>();
		HashMap<HashSet<Integer>,TreeSet<Date>> lcs3ids = new HashMap<HashSet<Integer>,TreeSet<Date>>();
		HashMap<HashSet<Integer>,TreeSet<Date>> lcs4ids = new HashMap<HashSet<Integer>,TreeSet<Date>>();
		HashMap<HashSet<Integer>,TreeSet<Date>> lcs5ids = new HashMap<HashSet<Integer>,TreeSet<Date>>();
		HashMap<HashSet<Integer>,TreeSet<Date>> lcs6ids = new HashMap<HashSet<Integer>,TreeSet<Date>>();
		HashMap<HashSet<Integer>,TreeSet<Date>> lcs7ids = new HashMap<HashSet<Integer>,TreeSet<Date>>();
		HashMap<HashSet<Integer>,TreeSet<Date>> lcs8ids = new HashMap<HashSet<Integer>,TreeSet<Date>>();
		HashMap<HashSet<Integer>,TreeSet<Date>> lcs9ids = new HashMap<HashSet<Integer>,TreeSet<Date>>();
		HashMap<HashSet<Integer>,TreeSet<Date>> lcs10ids = new HashMap<HashSet<Integer>,TreeSet<Date>>();
		HashMap<HashSet<Integer>,TreeSet<Date>> lcs11ids = new HashMap<HashSet<Integer>,TreeSet<Date>>();
		HashMap<HashSet<Integer>,TreeSet<Date>> lcs12ids = new HashMap<HashSet<Integer>,TreeSet<Date>>();
		HashMap<HashSet<Integer>,TreeSet<Date>> lcs13ids = new HashMap<HashSet<Integer>,TreeSet<Date>>();
		HashMap<HashSet<Integer>,TreeSet<Date>> lcs14ids = new HashMap<HashSet<Integer>,TreeSet<Date>>();
		HashMap<HashSet<Integer>,TreeSet<Date>> lcs15ids = new HashMap<HashSet<Integer>,TreeSet<Date>>();
		
		//-----------------------------------------Comparing dates for single insiders------------------------------------------------------------------
		
		ArrayList<Integer> allInsiders = new ArrayList<Integer> ();
		allInsiders.addAll(inid_date_map.keySet());
		for(int i=0; i<(allInsiders.size()-1); i++)
		{
			for(int j=i+1; j< allInsiders.size(); j++)
			{
				TreeSet<Date> d1 = inid_date_map.get(allInsiders.get(i));
				TreeSet<Date> d2 = inid_date_map.get(allInsiders.get(j));
				
				
				ArrayList<Date> d1Array = new ArrayList<Date>();
				d1Array.addAll(d1);
				//System.out.println(d1Array.toString()+d1Array.size());
				ArrayList<Date> d2Array = new ArrayList<Date>();
				d2Array.addAll(d2);
				
				//System.out.print("lcs for "+allInsiders.get(i)+" and "+allInsiders.get(j)+ " is: ");
				//Longest common subsequence of two d1 and d2
				TreeSet<Date> result=(getLongestCommonSubsequence(d1Array,d2Array));
				
				//adding pairs and lcs>=10 to a HashMap.
				HashSet<Integer> insider2ids = new HashSet<Integer>();
				TreeSet<Date> date2ids = new TreeSet<Date>();
				
				if(result.size()>= 5)
				{
					insider2ids.add(allInsiders.get(i));
					insider2ids.add(allInsiders.get(j));
					date2ids.addAll(result);
					lcs2ids.put(insider2ids, date2ids);
					
				}
				
				
			}
			
		}
		
		
		
		
		HashMap<HashSet<Integer>,TreeSet<Date>> finalmap = new HashMap<HashSet<Integer>,TreeSet<Date>>(lcs2ids);
		
		
		
		SetHypergraph<Integer, Integer> HGraph1 = new SetHypergraph<Integer,Integer>();
		
		//adding vertices to the hypergraph
		for (int i = 0; i < allInsiders.size(); i++) 
		{ 		      
	          HGraph1.addVertex(allInsiders.get(i));		
	    }
		
		for(HashSet<Integer> finalset : finalmap.keySet())
		{
			TreeSet<Date> finaldates = finalmap.get(finalset);
			System.out.println("Insider Set "+finalset);
			System.out.println("lcs dates length "+finaldates.size());
			HGraph1.addEdge(finaldates.size(), finalset);
		}
		
		System.out.println(HGraph1.getEdges());
		
		
		
	}
	

	


	//---------------------comparing dates for previous sets of insiders with single insider to get lcs for next sets of insiders------------------------------
	private static HashMap<HashSet<Integer>, TreeSet<Date>> insiderdatesets(HashMap<HashSet<Integer>, TreeSet<Date>> previds,HashMap<Integer, TreeSet<Date>> inid_date_map) 
	{
		HashMap<HashSet<Integer>,TreeSet<Date>> lcsids = new HashMap<HashSet<Integer>,TreeSet<Date>>();
		ArrayList<Integer> allInsiders = new ArrayList<Integer> ();
		allInsiders.addAll(inid_date_map.keySet());
		for(HashSet<Integer> insiderpair : previds.keySet())
		{
			ArrayList<Integer> pairids = new ArrayList<Integer>(insiderpair);
			ArrayList<Integer> allminuspair =new ArrayList<Integer>(allInsiders);
			allminuspair.removeAll(pairids);
			for(int i=0; i<allminuspair.size(); i++)
			{
				TreeSet<Date> d1 = previds.get(insiderpair);
				TreeSet<Date> d2 = inid_date_map.get(allminuspair.get(i));
				
				
				ArrayList<Date> d1Array = new ArrayList<Date>();
				d1Array.addAll(d1);
	
				ArrayList<Date> d2Array = new ArrayList<Date>();
				d2Array.addAll(d2);
				
				//Longest common subsequence of two d1 and d2
				TreeSet<Date> result=(getLongestCommonSubsequence(d1Array,d2Array));
				
				//adding pairs and lcs>=10 to a HashMap.
				HashSet<Integer> insider3ids = new HashSet<Integer>();
				TreeSet<Date> date3ids = new TreeSet<Date>();
				
				if(result.size()>= 5)
				{
					insider3ids.addAll(insiderpair);
					insider3ids.add(allminuspair.get(i));
					date3ids.addAll(result);
					lcsids.put(insider3ids, date3ids);
					
				}
			}
			
		}
		
		return lcsids;
	}
	
	//----------------------------------method to get the common sequence of dates----------------------------------------------------------------------
	private static TreeSet<Date> getLongestCommonSubsequence(ArrayList<Date> a, ArrayList<Date> b)
	{
		int m = a.size();
		int n = b.size();
		int[][] dp = new int[m+1][n+1];

		for(int i=0; i<=m; i++)
		{
			for(int j=0; j<=n; j++)
			{
				if(i==0 || j==0)
				{
					dp[i][j]=0;
				}
				else if(a.get(i-1).equals(b.get(j-1)))
				{
					dp[i][j] = 1 + dp[i-1][j-1];
				}
				else
				{
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		// Following code is used to print LCS
        int index = dp[m][n];
        int temp = index;
  
        // Create a character array to store the lcs string
        Date[] lcs = new Date[index+1];
        lcs[index] = null ; // Set the terminating character
  
        // Start from the right-most-bottom-most corner and
        // one by one store characters in lcs[]
        int i = m, j = n;
        while (i > 0 && j > 0)
        {
            // If current character in X[] and Y are same, then
            // current character is part of LCS
            if (a.get(i-1).equals(b.get(j-1)))
            {
                // Put current character in result
                lcs[index-1] = a.get(i-1); 
                 
                // reduce values of i, j and index
                i--; 
                j--; 
                index--;     
            }
  
            // If not same, then find the larger of two and
            // go in the direction of larger value
            else if (dp[i-1][j] > dp[i][j-1])
                i--;
            else
                j--;
        }
        //converting an array of dates to a TreeSet.
        List< Date > dates = Arrays.asList(lcs);
        TreeSet< Date > dateset = new TreeSet< Date >();
        for(int k = 0 ; k < dates.size(); k++)
        {
        	if(dates.get(k) != null)
        	{
        	dateset.add(dates.get(k));
        	}
        }
        
        return dateset;
	}
	
	//-----------------------------to delete lower order sets if they are a subset of higher order sets and lcs match-----------------------------------
	private static HashMap<HashSet<Integer>,TreeSet<Date>> highorder(HashMap<HashSet<Integer>, TreeSet<Date>> lcsbids, HashMap<HashSet<Integer>, TreeSet<Date>> lcsaids) 
	{
		HashMap<HashSet<Integer>, TreeSet<Date>> lcsaidscopy1 = new HashMap<HashSet<Integer>, TreeSet<Date>>(lcsaids);
		HashMap<HashSet<Integer>, TreeSet<Date>> lcsaidscopy2 = new HashMap<HashSet<Integer>, TreeSet<Date>>();
		for(HashSet<Integer> bid : lcsbids.keySet())
		{
			for(HashSet<Integer> aid : lcsaids.keySet())
			{
				TreeSet<Date> db = lcsbids.get(bid);
				TreeSet<Date> da = lcsaids.get(aid);
				if((bid.containsAll(aid)) && (da.size() == db.size()))
				{
					lcsaidscopy2.put(aid,lcsaids.get(aid));
				}
				
			}
		}
		
		lcsaidscopy1.entrySet().removeAll(lcsaidscopy2.entrySet());
		//System.out.println(lcsaidscopy1.keySet().size());
		return lcsaidscopy1;
	}

}
