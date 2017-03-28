import java.util.ArrayList;


public class Simulation {
	
	public void doSimulation() throws InterruptedException
	{
		doTestPowerplay();
	}
	
	// buying 2 tickets per week - no multiplier
	public void doTest1Year() throws InterruptedException
	{
		double highest = Double.MIN_VALUE;
		double lowest = Double.MAX_VALUE;
		ArrayList<Double> payouts = new ArrayList<Double>();

		for(int j = 0; j < 1000; j++)
		{
			LottoTicket myTicket = new LottoTicket(1,2,3,4,5,6,false);
			LottoMonitor myMonitor = new LottoMonitor(myTicket);
			
			ArrayList<LottoThread> threads = new ArrayList<LottoThread>();
			for(int i=0; i < 2; i++)
			{
				LottoThread myThread = new LottoThread(i,myMonitor,104);
				threads.add(myThread);
				myThread.start();
			}
					
			for(LottoThread thread : threads)
			{
				thread.join();
			}
			
			double payout = myMonitor.getPayout();
			if(payout < lowest) lowest = payout;
			if(payout > highest) highest = payout;
			payouts.add(payout);
		
		}

		int count = 1;
		for(double pout : payouts)
		{
			System.out.println(count + "\t" + pout);
			count++;
		}		
		
		System.out.println("\nlowest winning=" + lowest + " highest winning=" + highest);
	}
	
	// buying 2 tickets per week for 10 years - no multiplier
		public void doTest10Year() throws InterruptedException
		{
			double highest = Double.MIN_VALUE;
			double lowest = Double.MAX_VALUE;
			ArrayList<Double> payouts = new ArrayList<Double>();
			for(int j = 0; j < 1000; j++)
			{
				LottoTicket myTicket = new LottoTicket(1,2,3,4,5,6,false);
				LottoMonitor myMonitor = new LottoMonitor(myTicket);
				
				ArrayList<LottoThread> threads = new ArrayList<LottoThread>();
				for(int i=0; i < 20; i++)
				{
					LottoThread myThread = new LottoThread(i,myMonitor,104);
					threads.add(myThread);
					myThread.start();
				}
						
				for(LottoThread thread : threads)
				{
					thread.join();
				}
				
				double payout = myMonitor.getPayout();
				if(payout < lowest) lowest = payout;
				if(payout > highest) highest = payout;
				payouts.add(payout);
			}
			
			int count = 1;
			for(double pout : payouts)
			{
				System.out.println(count + "\t" + pout);
				count++;
			}
			
			System.out.println("\nlowest winning=" + lowest + " highest winning=" + highest);
		}
		// buying 2 tickets per week for 10 years - no multiplier
				public void doTest60Year() throws InterruptedException
				{
					double highest = Double.MIN_VALUE;
					double lowest = Double.MAX_VALUE;
					ArrayList<Double> payouts = new ArrayList<Double>();
					for(int j = 0; j < 1000; j++)
					{
						LottoTicket myTicket = new LottoTicket(1,2,3,4,5,6,false);
						LottoMonitor myMonitor = new LottoMonitor(myTicket);
						
						ArrayList<LottoThread> threads = new ArrayList<LottoThread>();
						for(int i=0; i < 120; i++)
						{
							LottoThread myThread = new LottoThread(i,myMonitor,104);
							threads.add(myThread);
							myThread.start();
						}
								
						for(LottoThread thread : threads)
						{
							thread.join();
						}
						
						double payout = myMonitor.getPayout();
						if(payout < lowest) lowest = payout;
						if(payout > highest) highest = payout;
						payouts.add(payout);
					}
					
					int count = 1;
					for(double pout : payouts)
					{
						System.out.println(count + "\t" + pout);
						count++;
					}
					
					System.out.println("\nlowest winning=" + lowest + " highest winning=" + highest);
				}
	
	
	// try and get a jackpot, simulate 1 billion tickets or stop at jackpot
    public void doTestJackpot() throws InterruptedException
    {
    	LottoTicket myTicket = new LottoTicket(1,2,3,4,5,6,false);
		LottoMonitor myMonitor = new LottoMonitor(myTicket);
				
		double startTime = System.currentTimeMillis();
		ArrayList<LottoThread> threads = new ArrayList<LottoThread>();
		for(int i=0; i < 1000; i++)
		{
			LottoThread myThread = new LottoThread(i,myMonitor,1000000); 
			threads.add(myThread);
			myThread.start();		
		}
				
		for(LottoThread thread : threads)
		{
			thread.join();
		}
		double endTime = System.currentTimeMillis();
		System.out.println(myMonitor.summary());
		
		System.out.println("Simulation time: " + (endTime-startTime));
		if(myMonitor.getJackpotHit())System.out.println("Jackpot hit at ticket #" + myMonitor.jackpotHitLocation());
    }
    
 // simulation of 1million tickets - with multiplier
  	public void doTest10million() throws InterruptedException
  	{
  		double highest = Double.MIN_VALUE;
  		double lowest = Double.MAX_VALUE;
  		ArrayList<Double> payouts = new ArrayList<Double>();

  			LottoTicket myTicket = new LottoTicket(1,2,3,4,5,6,false);
  			LottoMonitor myMonitor = new LottoMonitor(myTicket);

  			ArrayList<LottoThread> threads = new ArrayList<LottoThread>();
  			for(int i=0; i < 10; i++)
  			{
  				LottoThread myThread = new LottoThread(i,myMonitor,1000000);
  				threads.add(myThread);
  				myThread.start();
  			}
  					
  			for(LottoThread thread : threads)
  			{
  				thread.join();
  			}
  			
  			double payout = myMonitor.getPayout();
  			if(payout < lowest) lowest = payout;
  			if(payout > highest) highest = payout;
  			payouts.add(payout);
  		
  			System.out.println(myMonitor.summary());
  			System.out.println(myMonitor.getMultiplierCount());
  		
 		
  	}
    
    // simulation of 10million tickets - with multiplier
 	public void doTestPowerplay() throws InterruptedException
 	{
 		double highest = Double.MIN_VALUE;
 		double lowest = Double.MAX_VALUE;
 		ArrayList<Double> payouts = new ArrayList<Double>();

 			LottoTicket myTicket = new LottoTicket(1,2,3,4,5,6,true);
 			LottoMonitor myMonitor = new LottoMonitor(myTicket);

 			ArrayList<LottoThread> threads = new ArrayList<LottoThread>();
 			for(int i=0; i < 10; i++)
 			{
 				LottoThread myThread = new LottoThread(i,myMonitor,1000000);
 				threads.add(myThread);
 				myThread.start();
 			}
 					
 			for(LottoThread thread : threads)
 			{
 				thread.join();
 			}
 			
 			double payout = myMonitor.getPayout();
 			if(payout < lowest) lowest = payout;
 			if(payout > highest) highest = payout;
 			payouts.add(payout);
 		
 			System.out.println(myMonitor.summary());
 			System.out.println(myMonitor.getMultiplierCount());
 		
		
 	}
	
	public static void main(String[] args) throws InterruptedException
	{
		Simulation simulation = new Simulation();
		simulation.doSimulation();
	}
}
