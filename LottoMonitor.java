import java.util.ArrayList;
import java.util.LinkedList;

public class LottoMonitor {

	private double sumMoney;
	private LottoTicket ticket;
	private double payout;
	private int numTicketsPurchased;
	private int numWinningTickets;
	private boolean jackpotHit = false;
	private int twoMul, threeMul, fiveMul, fourMul, tenMul;
	private int zeroMatch, oneMatch, twoMatch, threeMatch, fourMatch, fiveMatch;
	private int zeroMatchPball, oneMatchPball, twoMatchPball, threeMatchPball, fourMatchPball, fiveMatchPball;
	private ArrayList<Integer> jackpotTickets = new ArrayList<Integer>();
	
	public LottoMonitor(LottoTicket ticket)
	{
		sumMoney = 0;
		this.ticket = ticket;
		payout = 0;
		numTicketsPurchased = 0;
		numWinningTickets = 0;
		twoMul = threeMul = fourMul = fiveMul = tenMul =
		oneMatchPball = twoMatchPball = threeMatchPball = fourMatchPball = fiveMatchPball =
		oneMatch = twoMatch = threeMatch = fourMatch = fiveMatch = 0;
	}
	
	public synchronized LottoTicket getTicket()
	{
		return ticket;
	}
	
	public synchronized void playLotto()
	{
		numTicketsPurchased++;
		sumMoney += ticket.getCost();
	}
	
	public synchronized double getMoneySpent()
	{
		return sumMoney;
	}
	
	public synchronized void increasePayout(int numMatch, boolean pballMatch)
	{
		System.out.println("Ticket number " + numTicketsPurchased + ": " +numMatch + " numbers matched, powerball = " + pballMatch);
		int multiplier = 1;
		if(ticket.getMultiplier())
		{
			multiplier = LottoTicket.calcMultiplier();
			if(multiplier == 2)
			{
				twoMul++;
			}else if(multiplier == 3)
			{
				threeMul++;
			}else if(multiplier == 5)
			{
				fiveMul++;
			}else if(multiplier == 10) tenMul++;
			else if(multiplier == 4) fourMul++;
		}
		
		switch(numMatch)
		{
			case 0 :
				if(pballMatch)
				{
					payout += (4 * multiplier);
					numWinningTickets++;
					zeroMatchPball++;
				}
				zeroMatch++;
				break;
			
			case 1 :
				if(pballMatch)
				{
					payout += (4 * multiplier);
					numWinningTickets++;
					oneMatchPball++;
				}
				else oneMatch++;
				break;
			
			case 2 :
				if(pballMatch)
				{
					payout += (7 * multiplier);
					numWinningTickets++;
					twoMatchPball++;
				}
				else twoMatch++;
				break;

			case 3 :
				if(pballMatch)
					{
					threeMatchPball++;
					payout += (100 * multiplier);
					}
				else{
					threeMatch++;
					payout += (7 * multiplier);
				}
				
				numWinningTickets++;
				break;

			case 4 :
				if(pballMatch)
					{
					fourMatchPball++;
					payout += (50000 * multiplier);
					}
				else{
					fourMatch++;
					payout += (100 * multiplier);
				}
				numWinningTickets++;
				break;

			case 5 :
				if(pballMatch)
				{
					fiveMatchPball++;
					payout += (200000000);
					jackpotHit=true;
					jackpotTickets.add(numTicketsPurchased);
				}
				else
				{
					fiveMatch++;
					if(multiplier >= 2)
						payout += 2000000;	
					else payout += 1000000;			
				}
				numWinningTickets++;
				break;
		}
	}
	
	public synchronized boolean getJackpotHit()
	{
		return jackpotHit;
	}

	public String summary() {
		return "Results:\n\nTickets purchased: " + numTicketsPurchased + "\n"
			 + "Amount spent: $" + sumMoney + "\n"
			 + "Number of winners: " + numWinningTickets + "\n"
			 + "Amount won: $" + payout + "\n\n"
			 + "Percentage of winners: " + ((double)numWinningTickets/(double)numTicketsPurchased + "%" + "\n\n"
			 + "Number of matches without powerball: \n"
			 + "Zero: " + zeroMatch + "\n"
			 + "One: " + oneMatch + "\n"
			 + "Two: " + twoMatch + "\n"
			 + "Three: " + threeMatch + "\n"
			 + "Four: " + fourMatch + "\n"
			 + "Five: " + fiveMatch + "\n\n"
			 + "Number of matches with powerball: \n"
			 + "Zero: " + zeroMatchPball + "\n"
			 + "One: " + oneMatchPball + "\n"
			 + "Two: " + twoMatchPball + "\n"
			 + "Three: " + threeMatchPball + "\n"
			 + "Four: " + fourMatchPball + "\n"
			 + "Five: " + fiveMatchPball + "\n");
	}

	public int jackpotHitLocation() {
		return jackpotTickets.get(0); 
	}
	
	public String getMultiplierCount()
	{
		return ("2X: " + twoMul + " 3X: " + threeMul + " 5X: " + fiveMul + " 10X: " + tenMul);				
	}
	
	public double getPayout()
	{
		return payout;
	}

}
