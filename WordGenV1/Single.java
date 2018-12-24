package WordGenV1;

public class Single {
	private String target;
	private String self = "";
	private float fitness;
	
	public Single(String s) {
		target = s;
		
		for (int wordChar = 0; wordChar < target.length(); wordChar++) {
			self += (char) ((Math.random() * 94) + 32);
		}
		
		fitness = genFitness();
	}
	
	public float genFitness() {
		int correctCount = 0;
		
		for (int wordLength = 0; wordLength < self.length(); wordLength++) {
			int multiplier = 1;
			if (self.charAt(wordLength) == target.charAt(wordLength)) 
				multiplier = 2;
			for (int wordChar = 0; wordChar < target.length(); wordChar++) {
				if (self.charAt(wordLength) == target.charAt(wordChar)) {
					correctCount += (1 * multiplier);
					break;
				}
			}
		}
		
		fitness = ((float) correctCount) / (target.length() * 2);
		return ((float) correctCount) / (target.length() * 2);
	}
	
	public void setSelf(String newSelf) {
		self = newSelf;
	}
	
	public float getFitness() {
		return fitness;
	}
	
	public String getSelf() {
		if (self != null)
			return self;
		else
			return null;
	}
}
