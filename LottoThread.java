import java.util.ArrayList;
import java.util.Random;


public class LottoThread extends Thread {

	private LottoMonitor monitor;
	private ArrayList<Integer> winningTicket;
	private int numMatch;
	private boolean pballMatch;
	private int ticketNum;
	private ArrayList<Integer> numberPool;
	private int numTicketsToProcess;
	private final int MIN_NUMBER = 1;
	private final int MAX_NUMBER = 69;
	private final int MAX_PBALL_NUMBER = 26;

	public LottoThread(int ticketNum, LottoMonitor monitor, int numTicketsToProcess)
	{		
		this.ticketNum = ticketNum;
		this.monitor = monitor;
		this.numTicketsToProcess=numTicketsToProcess;
		init();
	}
	
	private void init() {
		this.winningTicket = new ArrayList<Integer>();
		this.pballMatch = false;
		numberPool = new ArrayList<Integer>();
		for(int i = 1; i <= 69; i++)
		{
			numberPool.add(i);
		}
	}

	@Override
	public void run()
	{	
		for(int i = 0; i < numTicketsToProcess; i++)
		{
			buildTicket();
			checkTicket();
			
			if(monitor.getJackpotHit()) break;

			// reset
			this.winningTicket = new ArrayList<Integer>();
			numberPool = new ArrayList<Integer>();
			for(int j = MIN_NUMBER; j <= MAX_NUMBER; j++)
			{
				numberPool.add(j);
			}
			numMatch = 0;
			pballMatch = false;
			
		}	
	}
	
	public void checkTicket()
	{
		int[] ticketToCheck = monitor.getTicket().getTicket();
		// check first five numbers
		for(int i = 0; i < ticketToCheck.length-1; i++)
		{
			if(winningTicket.contains(ticketToCheck[i]))
			{
				if(winningTicket.get(winningTicket.size()-1)!=ticketToCheck[i])numMatch++;
			}
		}
		// check powerball number
		if(ticketToCheck[ticketToCheck.length-1] == winningTicket.get(winningTicket.size()-1))
			pballMatch = true;
		
		monitor.increasePayout(numMatch, pballMatch);			
	}

	public void buildTicket()
	{
		
		int num1 = numberPool.remove(new Random().nextInt(MAX_NUMBER-1)); // [0 .. 68] (index of numberPool)
		int num2 = numberPool.remove(new Random().nextInt(MAX_NUMBER-2));
		int num3 = numberPool.remove(new Random().nextInt(MAX_NUMBER-3));
		int num4 = numberPool.remove(new Random().nextInt(MAX_NUMBER-4));
		int num5 = numberPool.remove(new Random().nextInt(MAX_NUMBER-5));
		int pballNumber = new Random().nextInt(MAX_PBALL_NUMBER-1) + MIN_NUMBER; // [1 .. 26]
		
		winningTicket.add(num1);
		winningTicket.add(num2);
		winningTicket.add(num3);
		winningTicket.add(num4);
		winningTicket.add(num5);
		winningTicket.add(pballNumber);
		
		monitor.playLotto();
	}
	
	public String printTicket()
	{
		String result = "";
		for(int i : winningTicket)
		{
			result += (i + ", ");
		}
		return result;
	}
}
