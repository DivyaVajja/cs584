/**
 * @author Sameera Bammidi
 * Created On: 12/01/2017
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class SNDA
{

	public static void main(String[] args) throws SQLException, IOException, ParseException
	{
		/*
		 * Correlational Analysis of Transaction and Stock Prices
		 * 
		 * Return: Insiders with a significant statistical result
		 * 1: T = {}
		 * 2: for each insider I do
		 * 3: SI = {}
		 * 4: for each transaction of insider I for company C do
		 * 5: TD, TT, TP, Sum(ST) -> transaction date, type, price, sum of shares traded in all the transactions with the same TD, TT, and TP
		 * 6: CP = market closing price for company C’s stock on date TD
		 * 7: DV = dollar volume for company C’s stock on date TD
		 * 8: R = T P × Sum(ST)/DV
		 * 9: if T T = sale then
		 * 10: if CP < T P then
		 * 11: SI = SI U R
		 * 12: else
		 * 13: SI = SI U -R
		 * 14: if T T = purchase then
		 * 15: if CP > T P then
		 * 16: SI = SI U R
		 * 17: else
		 * 18: SI = SI U -R
		 * 19: T = T U {SI}
		 * 20: alpha-Bonferroni = 0.01/|T|
		 * 21: for each sample SI in T do
		 * 22: a = p-value from one tailed t-test with Ha : µSI > 0
		 * 23: if a < alpha-Bonferroni then
		 * 24: return I
		 * */

		// Get all insiders from the purchase_LCS_Egonets_NodeAndEdge_Count.csv.		
		File iinput = new File ("purchase_LCS_Egonets_NodeAndEdge_Count.csv");

		if(!iinput.exists())
		{
			System.out.println("file not found");
		}

		BufferedReader ibr = new BufferedReader(new FileReader(iinput));

		String iline;
		iline = ibr.readLine();// ignore first
		HashSet<Integer> insiderIdLCSPurchases = new HashSet<Integer>();

		while ((iline = ibr.readLine()) != null)
		{
			String[] tokens = iline.split(",");

			insiderIdLCSPurchases.add(Integer.parseInt(tokens[0]));
		}
		ibr.close();

		/* For each insider id from the above file if the insider Id from the purchases_trade_Price_SNDA_all_Data.csv file matches it,
		 * Write it to purchases_trade_Price_LCS_Data.csv.
		 */
		File input = new File ("purchases_trade_Price_SNDA_all_Data.csv");

		if(!input.exists())
		{
			System.out.println("file not found");
		}


		PrintWriter pwrp = new PrintWriter(new File("purchases_trade_Price_LCS_Data.csv"));
		pwrp.println("ID, Date, insider_id, stock_id, NumberOfShares, PurchasePrice, ClosingPrice, StockVolume");

		BufferedReader br = new BufferedReader(new FileReader(input));

		String line;
		line = br.readLine();// ignore first

		while ((line = br.readLine()) != null)
		{
			//System.out.println(line);
			String[] tokens = line.split(",");
			if(insiderIdLCSPurchases.contains(Integer.parseInt(tokens[2])))
			{
					pwrp.println(line);
			}

		}
		pwrp.close();
		br.close();

		/*
		 * For each purchase transaction compute the total purchase price = no.of shares purchased x purchase price.
		 * For each purchase transaction compute dollar volume = closingPrice x stock volume.
		 * For each date, for each insider Compute sdna = total purchase price/dollar volume.
		 * If closingPrice > purchase price the store +sdna else store -sdna. 
		 * Save all of the above to a file purchases_SDNA_Data.csv.
		 * */
		//HashMap<Integer, Set<Date>> uniquePurchaseDates = new HashMap<Integer, Set<Date>>();
		
		//Get all the stock Ids and store them in a hash set.
		/*HashSet<Integer> stockids = new HashSet<Integer>();
		File inputStockIds = new File ("StockIds.csv");
		if(!inputStockIds.exists())
		{
			System.out.println("file not found");
		}
		br = new BufferedReader(new FileReader(input));

		line = br.readLine();// ignore first
		while ((line = br.readLine()) != null)
		{
			stockids.add(Integer.parseInt(line.toString()));			
		}*/
		
		File inputLCS = new File ("purchases_trade_Price_LCS_Data.csv");

		if(!inputLCS.exists())
		{
			System.out.println("file not found");
		}
		br.close();
		
		br = new BufferedReader(new FileReader(input));

		line = br.readLine();// ignore first
		// For each insider in insiderIdLCSPurchases, if the line has this insider, get the set of all dates he/she traded on, repetitive ones too.
		// Then compute totalTransactionPrice of each insider for that date.
		// If per transaction, close price > trade price, then snda = snda + ratio. Else snda = snda - ratio.
		double sdna = 0;
		HashMap<Integer, Transaction> insiderTransactions_Purchases = new HashMap<Integer, Transaction>();
		HashMap<Integer, Double> insider_sdna_purchase_Scores = new HashMap<Integer, Double>();
		while ((line = br.readLine()) != null)
		{
			String[] tokens = line.split(",");
			/*double totalTransactionPrice = 4*5;
			double dollarVolumePerTransaction = 6*7;*/
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 

			   String dateString = tokens[1];
		    Date date = df.parse(dateString);/*
		    String newDateString = df.format(date);
		    System.out.println(newDateString);*/
			
			Transaction transaction = new Transaction(Integer.parseInt(tokens[2]), date, Integer.parseInt(tokens[3]), 
					Integer.parseInt(tokens[4]), Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]), Integer.parseInt(tokens[7]));
			
			insiderTransactions_Purchases.put(Integer.parseInt(tokens[0]), transaction);
		}

		for(Integer insider : insiderIdLCSPurchases)
		{
			for(Integer transactionId : insiderTransactions_Purchases.keySet())
			{
				Transaction t = insiderTransactions_Purchases.get(transactionId);
				if(t.InsiderId == insider)
				{
					double value = 0;
					
					double ratio = (t.transactionPrice * t.numberOfShares)/(t.closingPrice*t.stockVolume);
					//For each transaction made by an insider
					if (t.closingPrice > t.closingPrice)
					{
						sdna = sdna + ratio;
					}
					else
					{
						sdna = sdna - ratio;
					}
					// If the insider id is in the map, add the score to existing insider's score.
					if(insider_sdna_purchase_Scores.containsKey(t.InsiderId))
					{
						value = insider_sdna_purchase_Scores.get(t.InsiderId);
						value = value + sdna;
					}
					// If the insider id is not in the map, add the insider id and his/her score to the map
					else
					{
						insider_sdna_purchase_Scores.put(t.InsiderId, sdna);
					}
				}
			}
		}
		br.close();
		System.exit(1);

		// Get all insiders from the sale_LCS_Egonets_NodeAndEdge_Count.csv.		
		File sinput = new File ("sale_LCS_Egonets_NodeAndEdge_Count.csv");

		if(!sinput.exists())
		{
			System.out.println("file not found");
		}

		BufferedReader sbr = new BufferedReader(new FileReader(sinput));

		String sline;
		sline = sbr.readLine();// ignore first
		HashSet<Integer> insiderIdLCSSales = new HashSet<Integer>();

		while ((sline = sbr.readLine()) != null)
		{
			String[] tokens = sline.split(",");

			insiderIdLCSSales.add(Integer.parseInt(tokens[0]));
		}
		sbr.close();

		/* For each insider id from the above file if the insider Id from the sale_trade_Price_SNDA_all_Data.csv file matches it,
		 * Write it to sale_trade_Price_LCS_Data.csv.
		 */
		input = new File ("sale_trade_Price_SNDA_all_Data.csv");

		if(!input.exists())
		{
			System.out.println("file not found");
		}


		PrintWriter pwrs = new PrintWriter(new File("sale_trade_Price_LCS_Data.csv"));
		pwrs.println("ID, Date, insider_id, stock_id, NumberOfShares, PurchasePrice, ClosingPrice, StockVolume");

		br = new BufferedReader(new FileReader(input));

		line = br.readLine();// ignore first

		while ((line = br.readLine()) != null)
		{
			//System.out.println(line);
			String[] tokens = line.split(",");
			for(int insiderIdLCSale : insiderIdLCSSales)
			{
				if(insiderIdLCSale == Integer.parseInt(tokens[2]))
				{
					pwrs.println(line);
				}
			}

		}
		pwrs.close();
		br.close();

	}

}
