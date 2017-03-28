import java.util.Random;


public class LottoTicket {

	private final double COST;
	private final boolean MULTIPLIER;
	private final int[] TICKET;
	
	
	// 6 lottery numbers and multiplier 
	public LottoTicket(int n1, int n2, int n3, int n4, int n5, int pball, boolean mul)
	{
		TICKET = new int[]{ n1, n2, n3, n4, n5, pball };
		if(mul)
		{
			COST = 3;
		}
		else
		{
			COST = 2;
		}
		MULTIPLIER = mul;
	}
	
	public static int calcMultiplier() {
		int randomNum = new Random().nextInt(42) + 1; // [ 1 .. 43 ]
		
		// 1/43 chance of 10X multiplier
		if(randomNum == 40)
		{
			return 10;
		}
		
		// 1/21 chance of 5X
		else if(randomNum == 14 || randomNum == 15)
		{
			return 5;
		}
		else if(randomNum > 15 && randomNum <= 18)
			return 4;
		// 1/3 chance of 3X
		else if(randomNum >= 1 && randomNum < 14)
		{
			return 3;
		}
		
		return 2;
	}

	public int[] getTicket()
	{
		return TICKET;
	}
	
	public boolean getMultiplier()
	{
		return MULTIPLIER;
	}
	
	public double getCost()
	{
		return COST;
	}
}
