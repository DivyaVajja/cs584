/**
 * @author SameeraBammidi
 * Created on: 12/02/2017
 */

import java.util.Date;

public class Transaction
{

	int InsiderId;
	Date transactionDate;
	int StockId;
	int numberOfShares;
	double transactionPrice;
	double closingPrice;
	double stockVolume;
	public Transaction(int insiderId, Date transactionDate, int stockId, int numberOfShares, double transactionPrice, double closingPrice, double stockVolume)
	{
		super();
		this.InsiderId = insiderId;
		this.transactionDate = transactionDate;
		this.StockId = stockId;
		this.numberOfShares = numberOfShares;
		this.transactionPrice = transactionPrice;
		this.closingPrice = closingPrice;
		this.stockVolume = stockVolume;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + InsiderId;
		result = prime * result + StockId;
		long temp;
		temp = Double.doubleToLongBits(closingPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + numberOfShares;
		temp = Double.doubleToLongBits(stockVolume);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		temp = Double.doubleToLongBits(transactionPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (InsiderId != other.InsiderId)
			return false;
		if (StockId != other.StockId)
			return false;
		if (Double.doubleToLongBits(closingPrice) != Double.doubleToLongBits(other.closingPrice))
			return false;
		if (numberOfShares != other.numberOfShares)
			return false;
		if (Double.doubleToLongBits(stockVolume) != Double.doubleToLongBits(other.stockVolume))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (Double.doubleToLongBits(transactionPrice) != Double.doubleToLongBits(other.transactionPrice))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Transaction [InsiderId=" + InsiderId + ", transactionDate=" + transactionDate + ", StockId=" + StockId
				+ ", numberOfShares=" + numberOfShares + ", transactionPrice=" + transactionPrice + ", closingPrice="
				+ closingPrice + ", stockVolume=" + stockVolume + "]";
	}
	
	
}
