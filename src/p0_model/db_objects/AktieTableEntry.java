package p0_model.db_objects;

import java.util.Date;
import java.util.HashMap;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import test.DeepClone;

public class AktieTableEntry { 

private IntegerProperty share_id;
private StringProperty name;
private StringProperty industry;
private StringProperty index;
private DoubleProperty yield;
private DoubleProperty risk;

public int getShare_id() {
	return share_id.get();
}
public String getName() {
	return name.get();
}
public String getIndustry() {
	return industry.get();
}
public String getIndex() {
	 
	return index.get();
}
public double getSigma() {
	return yield.get();
}
public double getRisk() {
	return risk.get();
}
public IntegerProperty get2Share_id() {
	return share_id;
}
public StringProperty get2Name() {
	return name;
}
public StringProperty get2Industry() {
	return industry;
}
public StringProperty get2Index() {
	return index;
}
public DoubleProperty get2Sigma() {
	return yield;
}
public DoubleProperty get2Risk() {
	return risk;
}
///->?? date??
private HashMap <Date, Double> hashOfHalfYear;
public HashMap<Date, Double> getHashOfHalfYear() {
	return hashOfHalfYear;
}
public void setHashOfHalfYear(HashMap<Date, Double> hashOfHalfYear) {
	this.hashOfHalfYear = new HashMap<Date, Double>(DeepClone.deepClone(hashOfHalfYear));

}
public AktieTableEntry(int share_id, String name, String industry, String index, Double sigma, Double risk,
			HashMap<Date, Double> hashOfHalfYear) {
		super();
		this.share_id = new SimpleIntegerProperty(share_id);
		this.name = new SimpleStringProperty(name);
		this.industry = new SimpleStringProperty( industry);
		this.index = new SimpleStringProperty( index);
		this.yield = new SimpleDoubleProperty(sigma);
		this.risk = new SimpleDoubleProperty(risk);
		this.hashOfHalfYear = new HashMap<Date, Double>();
	}

public void addToHashOfHalfYear(Date date1, Double double1) {
	 
	this.hashOfHalfYear
	.put(date1
			, 
			double1);
	 
	return;
}

@Override
public String toString() {
	return "Aktie [share_id=" + share_id + ", name=" + name + ", industry=" + industry + ", index=" + index + ", sigma="
			+ yield + ", risk=" + risk + ", hashOfHalfYear=" + hashOfHalfYear + "]";
}

}
