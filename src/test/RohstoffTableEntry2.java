package test;

public class RohstoffTableEntry2 {

private int comm_id;
private String name;
private String category;
private double sigma;
private double risk;

private int courses[][];

public RohstoffTableEntry2(int comm_id, String name, String category, double sigma, double risk, int[][] courses) {
		super();
		this.comm_id = comm_id;
		this.name = name;
		this.category = category;
		this.sigma = sigma;
		this.risk = risk;
		this.courses = courses;
	}

public int getComm_id() {
	return comm_id;
}

public String getName() {
	return name;
}

public String getCategory() {
	return category;
}

public double getSigma() {
	return sigma;
}

public double getRisk() {
	return risk;
}

public int[][] getCourses() {
	return courses;
}

public void setComm_id(int comm_id) {
	this.comm_id = comm_id;
}

public void setName(String name) {
	this.name = name;
}

public void setCategory(String category) {
	this.category = category;
}

public void setSigma(double sigma) {
	this.sigma = sigma;
}

public void setRisk(double risk) {
	this.risk = risk;
}

public void setCourses(int[][] courses) {
	this.courses = courses;
}

}
