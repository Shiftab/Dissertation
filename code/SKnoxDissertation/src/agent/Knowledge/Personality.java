package agent.Knowledge;

import java.util.Random;

/**
 * the agents particular personality
 * 
 * @author shiftab
 * 
 */
public class Personality {

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
}
