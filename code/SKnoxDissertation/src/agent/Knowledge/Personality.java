package agent.Knowledge;

import java.util.Random;

/**
 * the agents particular personality
 * 
 * @author shiftab
 * 
 */
public class Personality {

	// Multiplier grid
		private static final double[] SHY_PROB = { -0.1, -0.1, -0.3, 0.2, 0.1 };
		private static final double[] DISTRACT_PROB = { 0, -0.3, 0.2, -0.2, 0.3 };
		private static final double[] ENCORAGE_PROB = { 0.1, 0.3, 0.3, 0.1, -0.3 };
		private static final double[] TENTITIVE_PROB = { 0, 0.3, 0.1, 0.3, -0.1 };
		private static final double[] IGNORE_PROB = { 0, 0.5, -0.3, -0.1, 0.1 };
		private static final double[] AGREE_PROB = { 0.1, 0.3, 0.1, 0.5, -0.1 };
		private static final double[] DISAGREE_PROB = { 0.1, 0.3, 0.1, -0.5, 0.5 };
		private static final double[] ARGUE_PROB = { 0, -0.1, 0.1, -0.5, 0.5 };
		private static final double[] PLACATE_PROB = { 0, 0.3, 0.3, 0.5, -0.1 };
		private static final double[] CHATTER_PROB = { 0, -0.3, 0.2, 0.2, 0.3 };

		// e-nums for decision case statement
		public static final int SHY = 1;
		public static final int DISTRACT = 2;
		public static final int ENCORIGE = 3;
		public static final int FOCUS = 4;
		public static final int IGNORE = 5;
		public static final int AGREE = 6;
		public static final int DISAGREE = 7;
		public static final int ARGUE = 8;
		public static final int PLACATE = 9;
		public static final int CHATTER = 0;
	
	private double openness, conscientiousness, extraversion, agreeableness,
			neuroticism;

	private double operational, numberConceptual, numberComparative,
			abstractSymbolic, graphical, spatialTemporal, tempAvg;

	private boolean dyslexic = false, dyscalculic = false;

	Random r = new Random();

	/**
	 * constructor to setup the personality of an agent
	 * 
	 * type options:
	 * 
	 * Standard = 1
	 * Dyslexic = 2
	 * Dyscalculic = 3
	 * Random(No default) = -1
	 * 
	 * @param type
	 */
	public Personality(int type) {
		openness = 0.4;
		conscientiousness = 0.7;
		extraversion = 0.2;
		agreeableness = 0.5;
		neuroticism = -0.3;

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
			operational = r.nextDouble();
			numberConceptual = r.nextDouble();
			numberComparative = r.nextDouble();
			abstractSymbolic = r.nextDouble();
			graphical = r.nextDouble();
			spatialTemporal = r.nextDouble();
		}

		tempAvg = (operational + numberConceptual + numberComparative
				+ abstractSymbolic + graphical + spatialTemporal) / 6;

		if (tempAvg <= 0.62)
			dyscalculic = true;
		else if (tempAvg <= 0.78)
			dyslexic = true;

		System.out.print("**" + tempAvg + "**");
	}

	/**
	 * old constructor for personality, implemented OCEAN factors
	 * @deprecated
	 */
	public Personality() {
		if (r.nextBoolean())
			openness = -r.nextDouble();
		else
			openness = r.nextDouble();

		if (r.nextBoolean())
			conscientiousness = -r.nextDouble();
		else
			conscientiousness = r.nextDouble();

		if (r.nextBoolean())
			extraversion = -r.nextDouble();
		else
			extraversion = r.nextDouble();

		if (r.nextBoolean())
			agreeableness = -r.nextDouble();
		else
			agreeableness = r.nextDouble();

		if (r.nextBoolean())
			neuroticism = -r.nextDouble();
		else
			neuroticism = r.nextDouble();

		System.out.println("O=" + openness + " C=" + conscientiousness + " E="
				+ extraversion + " A=" + agreeableness + " N=" + neuroticism);
	}

	/**
	 * getter for quering if an agent is dyslexic
	 * @return
	 */
	public boolean isDyslexic() {
		return dyslexic;
	}

	/**
	 * getter for quering if an agent is dyscalculic
	 * @return
	 */
	public boolean isDyscalculic() {
		return dyscalculic;
	}

	/**
	 * getter for ocean values
	 * array structure:
	 * 0=openness
	 * 1=conscientiousness
	 * 2=extraversion
	 * 3=agreeableness
	 * 4=neuroticism
	 * @return
	 */
	public double[] getOCEAN() {
		double[] ocean = { openness, conscientiousness, extraversion,
				agreeableness, neuroticism };

		return ocean;
	}

	/**
	 * @return the openness
	 */
	public double getOpenness() {
		return openness;
	}

	/**
	 * @param openness the openness to set
	 */
	public void setOpenness(double openness) {
		this.openness = openness;
	}

	/**
	 * @return the conscientiousness
	 */
	public double getConscientiousness() {
		return conscientiousness;
	}

	/**
	 * @param conscientiousness the conscientiousness to set
	 */
	public void setConscientiousness(double conscientiousness) {
		this.conscientiousness = conscientiousness;
	}

	/**
	 * @return the extraversion
	 */
	public double getExtraversion() {
		return extraversion;
	}

	/**
	 * @param extraversion the extraversion to set
	 */
	public void setExtraversion(double extraversion) {
		this.extraversion = extraversion;
	}

	/**
	 * @return the agreeableness
	 */
	public double getAgreeableness() {
		return agreeableness;
	}

	/**
	 * @param agreeableness the agreeableness to set
	 */
	public void setAgreeableness(double agreeableness) {
		this.agreeableness = agreeableness;
	}

	/**
	 * @return the neuroticism
	 */
	public double getNeuroticism() {
		return neuroticism;
	}

	/**
	 * @param neuroticism the neuroticism to set
	 */
	public void setNeuroticism(double neuroticism) {
		this.neuroticism = neuroticism;
	}

	/**
	 * @return the operational
	 */
	public double getOperational() {
		return operational;
	}

	/**
	 * @param operational the operational to set
	 */
	public void setOperational(double operational) {
		this.operational = operational;
	}

	/**
	 * @return the numberConceptual
	 */
	public double getNumberConceptual() {
		return numberConceptual;
	}

	/**
	 * @param numberConceptual the numberConceptual to set
	 */
	public void setNumberConceptual(double numberConceptual) {
		this.numberConceptual = numberConceptual;
	}

	/**
	 * @return the numberComparative
	 */
	public double getNumberComparative() {
		return numberComparative;
	}

	/**
	 * @param numberComparative the numberComparative to set
	 */
	public void setNumberComparative(double numberComparative) {
		this.numberComparative = numberComparative;
	}

	/**
	 * @return the abstractSymbolic
	 */
	public double getAbstractSymbolic() {
		return abstractSymbolic;
	}

	/**
	 * @param abstractSymbolic the abstractSymbolic to set
	 */
	public void setAbstractSymbolic(double abstractSymbolic) {
		this.abstractSymbolic = abstractSymbolic;
	}

	/**
	 * @return the graphical
	 */
	public double getGraphical() {
		return graphical;
	}

	/**
	 * @param graphical the graphical to set
	 */
	public void setGraphical(double graphical) {
		this.graphical = graphical;
	}

	/**
	 * @return the spatialTemporal
	 */
	public double getSpatialTemporal() {
		return spatialTemporal;
	}

	/**
	 * @param spatialTemporal the spatialTemporal to set
	 */
	public void setSpatialTemporal(double spatialTemporal) {
		this.spatialTemporal = spatialTemporal;
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
	@SuppressWarnings("null") //remove when reimplementing personalitys
	public boolean decide(int decision, double prior) {

		double[] question = null;
		switch (decision) {
		case (SHY):
			question = SHY_PROB;
			return false;// temporary override
			// break;
		case (DISTRACT):
			question = DISTRACT_PROB;
			return false;// temporary override
			// break;
		case (FOCUS):
			question = TENTITIVE_PROB;
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
		case (PLACATE):
			question = PLACATE_PROB;
			return false;// temporary override
			// break;
		case (CHATTER):
			question = CHATTER_PROB;
			return false;// temporary override
			// break;
		case (ENCORIGE):
			question = ENCORAGE_PROB;
			return false; //temp override
			//break;
		}

		double ans = prior;
		double[] ocean = getOCEAN(); //TODO: get rid of this, get ocean
		for (int x = 0; x < ocean.length; x++)
			ans += (ocean[x] * question[x]);

		if (r.nextDouble() < ans)
			return true;
		else
			return false;
	}
}
