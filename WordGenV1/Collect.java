package WordGenV1;

public class Collect {
	private String target;
	private int population;
	private Single[] individuals;
	private Single[] refinedIndividuals;
	private final float mutationRate = 0.1f;
	private final int crossoverRate = 65;
	
	public Collect(String s, int pop) {
		target = s;
		population = pop;
		
		individuals = new Single[population];
		for (int fill = 0; fill < population; fill++) {
			individuals[fill] = new Single(target);
		}
		
		refinedIndividuals = new Single[population];
	}
	
	public void runSelection() {
		int pos = 0;
		float startPoint = 0;
		float totalFitness = 0;
		for (int currentFitness = 0; currentFitness < individuals.length; currentFitness++) {
			totalFitness += individuals[currentFitness].getFitness();}
		
		while (refinedIndividuals[refinedIndividuals.length - 1] == null) {
			for (int count = 0; count < 2; count++) {
				startPoint = ((float) Math.random()) * totalFitness;
				for (int popSize = 0; popSize < individuals.length; popSize++) {
					startPoint += individuals[popSize].getFitness();
					if (startPoint > totalFitness) {
						refinedIndividuals[pos] = individuals[popSize];
						break;
					}
				}
			}
			pos++;
		}
		
		individuals = refinedIndividuals;
		refinedIndividuals = new Single[population];
	}
	
	public void runCrossover() {
		// Go through list in sets of 2
		// Create two children every run at a chance of 65% per child
		// Mutate child if rolled
		// Place back into array taking the place of the parent (if a new child is created)
		
		Single[] children = new Single[2];
		
		for (int curInd = 0; curInd < individuals.length - 1; curInd += 2) {
			int breakPoint = (int) (Math.random() * target.length());
			children[0] = new Single(target);
			children[0].setSelf(individuals[curInd].getSelf().substring(0, breakPoint) 
					    + individuals[curInd + 1].getSelf().substring(breakPoint));
			children[0].genFitness();
			children[1] = new Single(target);
			children[1].setSelf(individuals[curInd + 1].getSelf().substring(0, breakPoint)
					    + individuals[curInd].getSelf().substring(breakPoint));
			children[1].genFitness();
			for (int twice = 0; twice < 2; twice++) {
				if (Math.random() * 100 <= crossoverRate) {
					individuals[curInd + twice] = children[twice];
					individuals[curInd + twice].genFitness();
				}
				if (((float) Math.random() * 100) <= mutationRate) {
					int replacePoint = (int) (Math.random() * target.length());
					String replaceString = individuals[curInd + twice].getSelf();
					// Can potentially produce an error if the random point is the last char in the string
					replaceString = replaceString.substring(0, replacePoint) 
							      + ((char) ((Math.random() * 94) + 32)) 
							      + replaceString.substring(replacePoint + 1);
					individuals[curInd + twice].setSelf(replaceString);
					individuals[curInd + twice].genFitness();
				}
			}
		}
	}
	
	public Single getFittest() {
		float maxFitness = 0;
		Single mostFit = new Single(target);
		
		for (int fitness = 0; fitness < individuals.length; fitness++) {
			if (individuals[fitness].getFitness() > maxFitness) {
				maxFitness = individuals[fitness].getFitness();
				mostFit = individuals[fitness];
			}
		}
		
		return mostFit;
	}
	
	public Single[] getCurrentArray() {
		return individuals;
	}
}
