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

	public Personality(int test) {
		openness = 0.4;
		conscientiousness = 0.7;
		extraversion = 0.2;
		agreeableness = 0.5;
		neuroticism = -0.3;

		if (test == 1) { // "normal"
			operational = 1;
			numberConceptual = 0.9;
			numberComparative = 0.8;
			abstractSymbolic = 0.7;
			graphical = 0.85;
			spatialTemporal = 0.9;

		} else if (test == 2) { // dyslexic
			operational = 0.6;
			numberConceptual = 0.7;
			numberComparative = 0.7;
			abstractSymbolic = 0.6;
			graphical = 0.8;
			spatialTemporal = 0.6;
		} else if (test == 3) {// dyscalculic
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

	public boolean isDyslexic() {
		return dyslexic;
	}

	public boolean isDyscalculic() {
		return dyscalculic;
	}

	public double[] getOCEAN() {
		double[] ocean = { openness, conscientiousness, extraversion,
				agreeableness, neuroticism };

		return ocean;
	}

	public double getOpenness() {
		return openness;
	}

	public void setOpenness(double openness) {
		this.openness = openness;
	}

	public double getConscientiousness() {
		return conscientiousness;
	}

	public void setConscientiousness(double conscientiousness) {
		this.conscientiousness = conscientiousness;
	}

	public double getExtraversion() {
		return extraversion;
	}

	public void setExtraversion(double extraversion) {
		this.extraversion = extraversion;
	}

	public double getAgreeableness() {
		return agreeableness;
	}

	public void setAgreeableness(double agreeableness) {
		this.agreeableness = agreeableness;
	}

	public double getNeuroticism() {
		return neuroticism;
	}

	public void setNeuroticism(double neuroticism) {
		this.neuroticism = neuroticism;
	}

	public double getOperational() {
		return operational;
	}

	public void setOperational(double operational) {
		this.operational = operational;
	}

	public double getNumberConceptual() {
		return numberConceptual;
	}

	public void setNumberConceptual(double numberConceptual) {
		this.numberConceptual = numberConceptual;
	}

	public double getNumberComparative() {
		return numberComparative;
	}

	public void setNumberComparative(double numberComparative) {
		this.numberComparative = numberComparative;
	}

	public double getAbstractSymbolic() {
		return abstractSymbolic;
	}

	public void setAbstractSymbolic(double abstractSymbolic) {
		this.abstractSymbolic = abstractSymbolic;
	}

	public double getGraphical() {
		return graphical;
	}

	public void setGraphical(double graphical) {
		this.graphical = graphical;
	}

	public double getSpatialTemporal() {
		return spatialTemporal;
	}

	public void setSpatialTemporal(double spatialTemporal) {
		this.spatialTemporal = spatialTemporal;
	}
}
