package WordGenV1;

public class Runner {
	public static void main(String[] args) {
		int generationCount = 0;
		
		Collect test = new Collect("$tr4ng3 Chars", 1000);
		displayFittest(test);
		
		while (test.getFittest().getFitness() != 1) {
			test.runSelection();
			test.runCrossover();
			displayFittest(test);
			generationCount++;
		}
		
		System.out.println("After " + generationCount + " generations, the target phrase was matched.");
	}
	
	public static void display(Collect in) {
		Single[] disp = in.getCurrentArray();
		for (int i = 0; i < in.getCurrentArray().length; i++) {
			System.out.println(disp[i].getSelf());
		}
	}
	
	public static void displayFittest(Collect in) {
		System.out.println("\"" + in.getFittest().getSelf() 
				+ "\" is the most fit.\nIts fitness is: " 
				+ in.getFittest().getFitness() + "\n");
	}
}
