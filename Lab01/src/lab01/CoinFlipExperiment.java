package lab01;

public class CoinFlipExperiment {
	
	static public int coinFlipExperiment() {
		
		//System.out.println("Hello, World");
		
		int winnings = 0;
		
		for(int i = 0; i < 100; i++) {
			double flip = Math.random();
			if(flip < 0.505) {
				System.out.println("Heads");
				++winnings;
			}
			else {
				System.out.println("Tails");
				--winnings;
			}
		}//end of for loop
		return winnings;
	} //end of coinFlipExperiment
	
	public static void main(String[] args) {
		int amount = coinFlipExperiment();
		System.out.println("Win/Loss amount: " + amount);
	} //end of main
} //end of class
