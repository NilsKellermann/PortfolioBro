package p0_model.db_objects;

import java.util.Date;
import java.util.HashMap;

public class Aktie { 
private int share_id;
private String name;
private String industry;
private String index;
private double yield;
private double risk;

private HashMap <Date, Double> hashOfHalfYear;


public Aktie(int share_id, String name, String industry, String index, Double sigma, Double risk,
			HashMap<Date, Double> hashOfHalfYear) {
		super();
		this.share_id = share_id;
		this.name = name;
		this.industry = industry;
		this.index = index;
		this.yield = sigma;
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
	 
	this.hashOfHalfYear
	.put(date1, double1);
	 
	return;
}

@Override
public String toString() {
	return "Aktie [share_id=" + share_id + ", name=" + name + ", industry=" + industry + ", index=" + index + ", sigma="
			+ yield + ", risk=" + risk + ", hashOfHalfYear=" + hashOfHalfYear + "]";
}
public int getShare_id() {
	return share_id;
}
public String getName() {
	return name;
}
public String getIndustry() {
	return industry;
}
public String getIndex() {
	return index;
}
public double getSigma() {
	return yield;
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
	this.industry = industry;
}
public void setIndex(String index) {
	this.index = index;
}
public void setSigma(double sigma) {
	this.yield = sigma;
}
public void setRisk(double risk) {
	this.risk = risk;
}

}
