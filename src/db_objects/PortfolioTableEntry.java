package db_objects;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class PortfolioTableEntry {
	/* private */ IntegerProperty portfolio_id;
	/* private */ StringProperty name;
	/* private */ IntegerProperty user_id;
	/* private */ DoubleProperty capital;
	/* private */ DoubleProperty share_dist;
	/* private */ DoubleProperty comm_dist;
	/* private */ DoubleProperty curr_dist;
	/* private */ DoubleProperty estate_dist;
	/* private */ DoubleProperty bond_dist;
	/* private */ DoubleProperty sigma_full;
	/* private */ DoubleProperty risk_full;
	/* private */ DoubleProperty sigma_share;
	/* private */ DoubleProperty risk_share;
	/* private */ DoubleProperty sigma_comm;
	/* private */ DoubleProperty risk_comm;
	
	public class Share_In_Portf{
		public int share_id;
		public double percent;
		}
	public class Commodity_In_Portf{
		public int _id;
		public double percent;
	}
	private ObservableList<Share_In_Portf> sharelist;
	private ObservableList<Commodity_In_Portf> commlist;
	
	
//	public PortfolioTableEntry() {
//		super();
//		// TODO Auto-generated constructor stub
//	}


	public PortfolioTableEntry(int portfolio_id, String name, int user_id, double capital, double share_dist,
			double comm_dist, double curr_dist, double estate_dist, double bond_dist
			) {
		super();
		this.portfolio_id = new SimpleIntegerProperty(portfolio_id);
		this.name = new SimpleStringProperty(name);
		this.user_id = new SimpleIntegerProperty(user_id);
		this.capital = new SimpleDoubleProperty(capital);
		this.share_dist = new SimpleDoubleProperty(share_dist);	
		this.comm_dist = new SimpleDoubleProperty(comm_dist);
		this.curr_dist = new SimpleDoubleProperty(curr_dist);
		this.estate_dist = new SimpleDoubleProperty(estate_dist);
		this.bond_dist = new SimpleDoubleProperty(bond_dist);
		this.sigma_full = new SimpleDoubleProperty(0);
		this.risk_full = new SimpleDoubleProperty(0);
		this.sigma_share = new SimpleDoubleProperty(0);
		this.risk_share = new SimpleDoubleProperty(0);
		this.sigma_comm = new SimpleDoubleProperty(0);
		this.risk_comm = new SimpleDoubleProperty(0);
	}


	
	@Override
	public String toString() {
		return "PortfolioTableEntry [portfolio_id=" + portfolio_id + ", name=" + name + ", user_id=" + user_id
				+ ", capital=" + capital + ", share_dist=" + share_dist + ", comm_dist=" + comm_dist + ", curr_dist="
				+ curr_dist + ", estate_dist=" + estate_dist + ", bond_dist=" + bond_dist + ", sigma_full=" + sigma_full
				+ ", risk_full=" + risk_full + ", sigma_share=" + sigma_share + ", risk_share=" + risk_share
				+ ", sigma_comm=" + sigma_comm + ", risk_comm=" + risk_comm + ", sharelist=" + sharelist + ", commlist="
				+ commlist + "]";
	}



	public int get2Portfolio_id() {
		return portfolio_id.get();
	}


	public String get2Name() {
		return name.get();
	}


	public int get2User_id() {
		return user_id.get();
	}


	public double get2Capital() {
		return capital.get();
	}


	public double get2Share_dist() {
		return share_dist.get();
	}


	public double get2Comm_dist() {
		return comm_dist.get();
	}


	public double get2Curr_dist() {
		return curr_dist.get();
	}


	public double get2Estate_dist() {
		return estate_dist.get();
	}


	public double get2Bond_dist() {
		return bond_dist.get();
	}


	public double get2Sigma_full() {
		return sigma_full.get();
	}


	public double get2Risk_full() {
		return risk_full.get();
	}


	public double get2Sigma_share() {
		return sigma_share.get();
	}


	public double get2Risk_share() {
		return risk_share.get();
	}


	public double get2Sigma_comm() {
		return sigma_comm.get();
	}


	public double get2Risk_comm() {
		return risk_comm.get();
	}


	public void set2Portfolio_id(int portfolio_id) {
		this.portfolio_id.set(portfolio_id);
	}


	public void set2Name(String name) {
		this.name.set(name);
	}


	public void set2User_id(int user_id) {
		this.user_id.set(user_id);
	}


	public void set2Capital(double capital) {
		this.capital.set(capital);
	}


	public void set2Share_dist(double share_dist) {
		this.share_dist.set(share_dist);
	}


	public void set2Comm_dist(double comm_dist) {
		this.comm_dist.set(comm_dist);
	}


	public void set2Curr_dist(double curr_dist) {
		this.curr_dist.set(curr_dist);
	}


	public void set2Estate_dist(double estate_dist) {
		this.estate_dist.set(estate_dist);
	}


	public void set2Bond_dist(double bond_dist) {
		this.bond_dist.set(bond_dist);
	}


	public void set2Sigma_full(double sigma_full) {
		this.sigma_full.set(sigma_full);
	}


	public void set2Risk_full(double risk_full) {
		this.risk_full.set(risk_full);
	}


	public void set2Sigma_share(double sigma_share) {
		this.sigma_share.set(sigma_share);
	}


	public void set2Risk_share(double risk_share) {
		this.risk_share.set(risk_share);
	}


	public void set2Sigma_comm(double sigma_comm) {
		this.sigma_comm.set(sigma_comm);
	}
//

	public void set2Risk_comm(double risk_comm) {
		this.risk_comm.set(risk_comm);
	}


	public IntegerProperty getPortfolio_id() {
		return portfolio_id;
	}


	public StringProperty getName() {
		return name;
	}


	public IntegerProperty getUser_id() {
		return user_id;
	}


	public DoubleProperty getCapital() {
		return capital;
	}


	public DoubleProperty getShare_dist() {
		return share_dist;
	}


	public DoubleProperty getComm_dist() {
		return comm_dist;
	}


	public DoubleProperty getCurr_dist() {
		return curr_dist;
	}


	public DoubleProperty getEstate_dist() {
		return estate_dist;
	}


	public DoubleProperty getBond_dist() {
		return bond_dist;
	}


	public DoubleProperty getSigma_full() {
		return sigma_full;
	}


	public DoubleProperty getRisk_full() {
		return risk_full;
	}


	public DoubleProperty getSigma_share() {
		return sigma_share;
	}


	public DoubleProperty getRisk_share() {
		return risk_share;
	}


	public DoubleProperty getSigma_comm() {
		return sigma_comm;
	}


	public DoubleProperty getRisk_comm() {
		return risk_comm;
	}


	public void setPortfolio_id(IntegerProperty portfolio_id) {
		this.portfolio_id = portfolio_id;
	}


	public void setName(StringProperty name) {
		this.name = name;
	}


	public void setUser_id(IntegerProperty user_id) {
		this.user_id = user_id;
	}


	public void setCapital(DoubleProperty capital) {
		this.capital = capital;
	}


	public void setShare_dist(DoubleProperty share_dist) {
		this.share_dist = share_dist;
	}


	public void setComm_dist(DoubleProperty comm_dist) {
		this.comm_dist = comm_dist;
	}


	public void setCurr_dist(DoubleProperty curr_dist) {
		this.curr_dist = curr_dist;
	}


	public void setEstate_dist(DoubleProperty estate_dist) {
		this.estate_dist = estate_dist;
	}


	public void setBond_dist(DoubleProperty bond_dist) {
		this.bond_dist = bond_dist;
	}


	public void setSigma_full(DoubleProperty sigma_full) {
		this.sigma_full = sigma_full;
	}


	public void setRisk_full(DoubleProperty risk_full) {
		this.risk_full = risk_full;
	}


	public void setSigma_share(DoubleProperty sigma_share) {
		this.sigma_share = sigma_share;
	}


	public void setRisk_share(DoubleProperty risk_share) {
		this.risk_share = risk_share;
	}


	public void setSigma_comm(DoubleProperty sigma_comm) {
		this.sigma_comm = sigma_comm;
	}


	public void setRisk_comm(DoubleProperty risk_comm) {
		this.risk_comm = risk_comm;
	}

	

	
	
}
