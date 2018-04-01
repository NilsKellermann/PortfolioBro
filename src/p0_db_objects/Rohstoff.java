package p0_db_objects;

import java.util.Date;
import java.util.HashMap;

public class Rohstoff { 
private int share_id;
private String name;
private String category;

private double sigma;
private double risk;

private HashMap <Date, Double> hashOfHalfYear;


public Rohstoff(int share_id, String name, String industry, Double sigma, Double risk,
			HashMap<Date, Double> hashOfHalfYear) {
		super();
		this.share_id = share_id;
		this.name = name;
		this.category = industry;

		this.sigma = sigma;
		this.risk = risk;
		this.hashOfHalfYear = new HashMap<Date, Double>();
	}
public HashMap<Date, Double> getHashOfHalfYear() {
	return hashOfHalfYear;
}
//public void setHashOflfYear(HashMap<Date, Double> hashOfHalfYear) {
//	this.hashOfHalfYear = new HashMap<Date, Double>(DeepClone.deepClone(hashOfHalfYear));
//
//}
public void addToHashOfHalfYear(Date date1, Double double1) {
	System.out.println("abc");
	this.hashOfHalfYear
	.put(date1, double1);
	System.out.println("abc");
	return;
}

@Override
public String toString() {
	return "Aktie [share_id=" + share_id + ", name=" + name + ", industry=" + category +  ", sigma="
			+ sigma + ", risk=" + risk + ", hashOfHalfYear=" + hashOfHalfYear + "]";
}
public int getShare_id() {
	return share_id;
}
public String getName() {
	return name;
}
public String getIndustry() {
	return category;
}

public double getSigma() {
	return sigma;
}
public double getRisk() {
	return risk;
}
public void setShare_id(int share_id) {
	this.share_id = share_id;
}
public void setName(String name) {
	this.name = name;
}
public void setIndustry(String industry) {
	this.category = industry;
}

public void setSigma(double sigma) {
	this.sigma = sigma;
}
public void setRisk(double risk) {
	this.risk = risk;
}

}
