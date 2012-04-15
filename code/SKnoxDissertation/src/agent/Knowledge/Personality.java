package agent.Knowledge;

import java.util.Random;

/**
 * the agents particular personality
 * 
 * @author shiftab
 * 
 */
public class Personality {

	private static final int WAIT_TIME = 100;

	// Multiplier grid
	private static final double[] SHY_PROB = { 0, -0.1, -0.5, -0.2, 0.1 };
	private static final double[] DISTRACT_PROB = { 0, -0.3, 0.2, -0.2, 0.3 };
	private static final double[] FOCUS_PROB = { 0, 0, 0, 0, 0 };
	private static final double[] IGNORE_PROB = { 0, 0.5, -0.3, -0.1, 0.1 };
	private static final double[] AGREE_PROB = { 0.1, 0.3, 0.1, 0.5, -0.1 };
	private static final double[] DISAGREE_PROB = { 0.1, 0.3, 0.1, -0.5, 0.5 };
	private static final double[] ARGUE_PROB = { 0, 0, 0, -0.3, 0.3 };
	private static final double[] CHATTER_PROB = { 0, -0.3, 0.2, 0.2, 0.3 };

	// e-nums for decision case statement
	public static final int SHY = 1;
	public static final int DISTRACT = 2;
	public static final int FOCUS = 4;
	public static final int IGNORE = 5;
	public static final int AGREE = 6;
	public static final int DISAGREE = 7;
	public static final int ARGUE = 8;
	public static final int CHATTER = 0;

	/**
	 * all stats 0 is avg openness: + = open to new things, likes science and
	 * abstract thinking - = closed minded, likes things to be the same as
	 * normal
	 * 
	 * conscientiousness: + = hardworking, carfull with a large attention to
	 * detail. perfectionists - = easly distracted, lazy, less goal oriented
	 * 
	 * extraversion: + = works well with others, social and assertive - = shy
	 * and quiet, strugles with larger groups
	 * 
	 * agreeableness: + = accommidating, friendly, helpfull - = less social,
	 * argumentitive untrusting
	 * 
	 * Neuroticism: + = easly stressed, very emotional, self-conscious - =
	 * lazyfair, calm, even-tempered
	 */
	private double openness, conscientiousness, extraversion, agreeableness,
			neuroticism;

	/**
	 * all stats 0.5 is avg operational: ability to manipulate operations and
	 * sequences
	 * 
	 * numberConceptual: the atributation of meaning to numbers, effects symbol
	 * understanding as well as sequence understanding
	 * 
	 * number Comparative: the understanding of how numbers relate, including
	 * sequencing
	 * 
	 * abstractSymbolic: the understanding of abstract concepts and ideas
	 * 
	 * graphica: the ability to manipulate or understand graphical
	 * interpretations of problems
	 * 
	 * spatialTemporal: the ability to understand space and time intuitivly
	 */
	private double operational, numberConceptual, numberComparative,
			abstractSymbolic, graphical, spatialTemporal, tempAvg;

	/**
	 * 0 is avg
	 */
	private double selfEsteam = 0, shyness = 1;

	/**
	 * random within 10 percent, more likly with certan traits low
	 * numberConceptual low spacialTemporal mid-high graphics
	 * 
	 * dyscalculic is purly for output
	 */
	private boolean dyslexic = false, dyscalculic = false;

	Random r = new Random();

	/**
	 * constructor to setup the personality of an agent
	 * 
	 * type options:
	 * 
	 * Standard = 1 Dyslexic = 2 Dyscalculic = 3 Random(No default) = -1
	 * 
	 * @param type
	 */
	public Personality(int type) {
		openness = (r.nextGaussian()*0.6);
		conscientiousness = (r.nextGaussian()*0.6);
		extraversion = (r.nextGaussian()*0.6);
		agreeableness = (r.nextGaussian()*0.6);
		neuroticism = (r.nextGaussian()*0.6);

		if (type == 1) { // "normal"
			operational = 1;
			numberConceptual = 0.9;
			numberComparative = 0.8;
			abstractSymbolic = 0.7;
			graphical = 0.85;
			spatialTemporal = 0.9;

		} else if (type == 2) { // dyslexic
			operational = 0.6;
			numberConceptual = 0.7;
			numberComparative = 0.7;
			abstractSymbolic = 0.6;
			graphical = 0.8;
			spatialTemporal = 0.6;
		} else if (type == 3) {// dyscalculic
			operational = 0.1;
			numberConceptual = 0.2;
			numberComparative = 0.3;
			abstractSymbolic = 0.1;
			graphical = 0.4;
			spatialTemporal = 0.4;
		} else { // no test
			operational = ((r.nextGaussian()*0.6)-(-1.5))/((1.5)+(1.5));
			numberConceptual = ((r.nextGaussian()*0.6)-(-1.5))/((1.5)+(1.5));
			numberComparative = ((r.nextGaussian()*0.6)-(-1.5))/((1.5)+(1.5));
			abstractSymbolic = ((r.nextGaussian()*0.6)-(-1.5))/((1.5)+(1.5));
			graphical = ((r.nextGaussian()*0.6)-(-1.5))/((1.5)+(1.5));
			spatialTemporal = ((r.nextGaussian()*0.6)-(-1.5))/((1.5)+(1.5));
		}

		tempAvg = (operational + numberConceptual + numberComparative
				+ abstractSymbolic + graphical + spatialTemporal) / 6;

		if (tempAvg <= 0.62)
			dyscalculic = true;
		else if (tempAvg <= 0.78)
			dyslexic = true;

		selfEsteam = (extraversion + (-neuroticism)) * 0.1;
	}

	/**
	 * getter for quering if an agent is dyslexic
	 * 
	 * @return
	 */
	public boolean isDyslexic() {
		return dyslexic;
	}

	/**
	 * getter for quering if an agent is dyscalculic
	 * 
	 * @return
	 */
	public boolean isDyscalculic() {
		return dyscalculic;
	}

	/**
	 * getter for ocean values array structure: 0=openness 1=conscientiousness
	 * 2=extraversion 3=agreeableness 4=neuroticism
	 * 
	 * @return
	 */
	public double[] getOCEAN() {
		double[] ocean = { openness, conscientiousness, extraversion,
				agreeableness, neuroticism };

		return ocean;
	}
	
	public void setOCEAN(double[] ocean){
		openness = ocean[0];
		conscientiousness = ocean[1]; 
		extraversion = ocean[2];
		agreeableness = ocean[3];
		neuroticism = ocean[4];
	}
	
	public double[] getAbility(){
		double[] ability = {operational, numberConceptual, numberComparative,
				abstractSymbolic, graphical, spatialTemporal};
		return ability;
	}
	public void setAbility(double[] ability){
		operational = ability[0];
		numberConceptual = ability[1]; 
		numberComparative = ability[2];
		abstractSymbolic = ability[3];
		graphical = ability[4];
		spatialTemporal = ability[5];
	}

	/**
	 * method for returning the speed at wich a pupil compleates a task
	 * 
	 * @return
	 */
	public int getSpeed() {
		double multiplyer = operational + numberComparative + numberConceptual
				+ abstractSymbolic;
		return (int) (WAIT_TIME * multiplyer) + r.nextInt(WAIT_TIME);
	}

	public double getSelfEsteam() {
		return selfEsteam;
	}

	public void incSelfEsteam() {
		if (this.selfEsteam < 1)
			this.selfEsteam += 0.2;
	}

	public void decSelfEsteam() {
		if (this.selfEsteam > -1)
			this.selfEsteam -= 0.2;
	}

	public double getShyness() {
		return shyness;
	}

	/**
	 * method to handle the action decisions based on the personality of the
	 * agent
	 * 
	 * @param decision
	 * @param prior
	 * @param ocean
	 * @return
	 */
	@SuppressWarnings("null")
	// remove when reimplementing personalitys
	public boolean decide(int decision, double prior) {
		double SE = 0;
		double[] question = null;
		switch (decision) {
		case (SHY):
			question = SHY_PROB;
			SE = selfEsteam;
			break;
		case (DISTRACT):
			question = DISTRACT_PROB;
			SE = selfEsteam * 0.5;
			return false;// temporary override
			// break;
		case (FOCUS):
			question = FOCUS_PROB;
			return false;// temporary override
			// break;
		case (IGNORE):
			question = IGNORE_PROB;
			return false;// temporary override
			// break;
		case (AGREE):
			question = AGREE_PROB;
			return true;// temporary override
			// break;
		case (DISAGREE):
			question = DISAGREE_PROB;
			return true;// temporary override
			// break;
		case (ARGUE):
			question = ARGUE_PROB;
			return false;// temporary override
			// break;
		case (CHATTER):
			question = CHATTER_PROB;
			return false;// temporary override
			// break;
		}

		double ans = prior;
		double[] ocean = getOCEAN(); // TODO: get rid of this, get ocean
		for (int x = 0; x < ocean.length; x++)
			ans += (ocean[x] * question[x]);

		ans += SE;

		ans = (ans + 1) / 2;
		System.out.println(" " + ans);
		if (r.nextDouble() < ans)
			return true;
		else
			return false;
	}

	public void resetSelfEsteam() {
		selfEsteam = (extraversion + (-neuroticism)) * 0.1;
	}

	public boolean isShy() {
		double personality = 0;
		double[] ocean = getOCEAN();
		double[] question = SHY_PROB;
		for (int x = 0; x < ocean.length; x++)
			personality += (ocean[x] * question[x]);

		personality = (1 - (personality / 0.8)) / 2;
		double SE = (1 + selfEsteam) / 2;
		if (((personality / 2) + (SE / 2)) < 0.4) {
			return true;
		} else
			return false;
	}
	
	public boolean distract(){
		double personality = 0;
		double[] ocean = getOCEAN();
		double[] question = DISTRACT_PROB;
		for (int x = 0; x < ocean.length; x++)
			personality += (ocean[x] * question[x]);

		personality = (1 + (personality / 0.8)) / 2;
		return ((personality / 2) + (shyness / 2)) < r.nextDouble();
	}

	public boolean isChatty() {

		double personality = 0;
		double[] ocean = getOCEAN();
		double[] question = SHY_PROB;
		for (int x = 0; x < ocean.length; x++)
			personality += (ocean[x] * question[x]);

		personality = (1 - (personality / 0.8)) / 2;
		double SE = (1 + selfEsteam) / 2;
		return (((personality / 2) + (SE / 2)) < r.nextDouble());
	}
}
