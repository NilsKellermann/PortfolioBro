package db_objects;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Portfolio {
	/* private */ int portfolio_id;
	/* private */ String name;
	/* private */ int user_id;
	/* private */ double capital;
	/* private */ double share_dist;
	/* private */ double comm_dist;
	/* private */ double curr_dist;
	/* private */ double estate_dist;
	/* private */ double bond_dist;
	/* private */ double sigma_full;
	/* private */ double risk_full;
	/* private */ double sigma_share;
	/* private */ double risk_share;
	/* private */ double sigma_comm;
	/* private */ double risk_comm;
	
	public Portfolio(int portfolio_id, String name, int user_id, double capital, double share_dist, double comm_dist,
			double curr_dist, double estate_dist, double bond_dist, double sigma_full, double risk_full,
			double sigma_share, double risk_share, double sigma_comm, double risk_comm) {
		super();
		this.portfolio_id = portfolio_id;
		this.name = name;
		this.user_id = user_id;
		this.capital = capital;
		this.share_dist = share_dist;
		this.comm_dist = comm_dist;
		this.curr_dist = curr_dist;
		this.estate_dist = estate_dist;
		this.bond_dist = bond_dist;
		this.sigma_full = sigma_full;
		this.risk_full = risk_full;
		this.sigma_share = sigma_share;
		this.risk_share = risk_share;
		this.sigma_comm = sigma_comm;
		this.risk_comm = risk_comm;
	}
	
	
	
	
//    public int getNextPortfolioId() {
//        int i = 1;
//        for (; i < 10000; i++) {
//            if (!pictureMap.keySet().contains(i)) {
//                break;
//            }
//        }
//        return i;
//    }
}
