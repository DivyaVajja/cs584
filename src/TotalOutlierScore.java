import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Sameera Bammidi
 * Created On : 12/09/2017
 */
public class TotalOutlierScore
{

	public static void main(String[] args) throws IOException
	{

		PrintWriter pwrps = new PrintWriter(new File("purchase_sim_total_outlierscores.csv"));
		pwrps.println("InsiderID,NodeCount,EdgeCount,outlierscore,LOFScore,TotalOutlierScore");
		BufferedReader fr =  new BufferedReader(new FileReader(new File("purchase_sim_outlierscores_Big.csv")));
		String line = fr.readLine(); // read and ignore first line
		
		while((line = fr.readLine()) != null)
		{
			String[] tokens = line.split(",");

			int insiderId = Integer.parseInt(tokens[1]);
			int nodeCount = Integer.parseInt(tokens[2]);
			int edgeCount = Integer.parseInt(tokens[3]);
			double outlierscore = Double.parseDouble(tokens[4]);
			double lofScore = Double.parseDouble(tokens[5]);
			double totalOutlierScore = outlierscore+(-1)*lofScore;
			
			pwrps.println(insiderId + "," + nodeCount + "," + edgeCount + "," + outlierscore + "," + lofScore + "," + totalOutlierScore);
		}
		fr.close();
		pwrps.close();
		
		PrintWriter pwrss = new PrintWriter(new File("sale_sim_total_outlierscores.csv"));
		pwrss.println("InsiderID,NodeCount,EdgeCount,outlierscore,LOFScore,TotalOutlierScore");
		BufferedReader frs =  new BufferedReader(new FileReader(new File("sale_sim_outlierscores_Big.csv")));
		line = frs.readLine(); // read and ignore first line
		
		while((line = frs.readLine()) != null)
		{
			String[] tokens = line.split(",");

			int insiderId = Integer.parseInt(tokens[1]);
			int nodeCount = Integer.parseInt(tokens[2]);
			int edgeCount = Integer.parseInt(tokens[3]);
			double outlierscore = Double.parseDouble(tokens[4]);
			double lofScore = Double.parseDouble(tokens[5]);
			double totalOutlierScore = outlierscore+(-1)*lofScore;
			
			pwrss.println(insiderId + "," + nodeCount + "," + edgeCount + "," + outlierscore + "," + lofScore + "," + totalOutlierScore);
		}
		frs.close();
		pwrss.close();
		
		PrintWriter pwrpl = new PrintWriter(new File("purchase_LCS_total_outlierscores.csv"));
		pwrpl.println("InsiderID,NodeCount,EdgeCount,outlierscore,LOFScore,TotalOutlierScore");
		BufferedReader frpl =  new BufferedReader(new FileReader(new File("purchase_LCS_outlierscores_Big.csv")));
		line = frpl.readLine(); // read and ignore first line
		
		while((line = frpl.readLine()) != null)
		{
			String[] tokens = line.split(",");

			int insiderId = Integer.parseInt(tokens[1]);
			int nodeCount = Integer.parseInt(tokens[2]);
			int edgeCount = Integer.parseInt(tokens[3]);
			double outlierscore = Double.parseDouble(tokens[4]);
			double lofScore = Double.parseDouble(tokens[5]);
			double totalOutlierScore = outlierscore+(-1)*lofScore;
			
			pwrpl.println(insiderId + "," + nodeCount + "," + edgeCount + "," + outlierscore + "," + lofScore + "," + totalOutlierScore);
		}
		frpl.close();
		pwrpl.close();

		PrintWriter pwrsl = new PrintWriter(new File("sale_LCS_total_outlierscores.csv"));
		pwrsl.println("InsiderID,NodeCount,EdgeCount,outlierscore,LOFScore,TotalOutlierScore");
		BufferedReader frsl =  new BufferedReader(new FileReader(new File("sale_LCS_outlierscores_Big.csv")));
		line = frsl.readLine(); // read and ignore first line
		
		while((line = frsl.readLine()) != null)
		{
			String[] tokens = line.split(",");

			int insiderId = Integer.parseInt(tokens[1]);
			int nodeCount = Integer.parseInt(tokens[2]);
			int edgeCount = Integer.parseInt(tokens[3]);
			double outlierscore = Double.parseDouble(tokens[4]);
			double lofScore = Double.parseDouble(tokens[5]);
			double totalOutlierScore = outlierscore+(-1)*lofScore;
			
			pwrsl.println(insiderId + "," + nodeCount + "," + edgeCount + "," + outlierscore + "," + lofScore + "," + totalOutlierScore);
		}
		frsl.close();
		pwrsl.close();
	}

}
