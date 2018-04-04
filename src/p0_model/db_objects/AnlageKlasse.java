package p0_model.db_objects;

public class AnlageKlasse {
	private int AC_ID;
	private String name;
	private double yield;
	private double risk;

	public AnlageKlasse(int aC_ID, String name, double sigma, double risk) {
		super();
		AC_ID = aC_ID;
		this.name = name;
		this.yield = sigma;
		this.risk = risk;
	}
	
	public int getAC_ID() {
		return AC_ID;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Assetclass [AC_ID=" + AC_ID + ", name=" + name + ", sigma=" + yield + ", risk=" + risk + "]";
	}

	public double getSigma() {
		return yield;
	}

	public double getRisk() {
		return risk;
	}

	public void setAC_ID(int aC_ID) {
		AC_ID = aC_ID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSigma(double sigma) {
		this.yield = sigma;
	}

	public void setRisk(double risk) {
		this.risk = risk;
	}
}
