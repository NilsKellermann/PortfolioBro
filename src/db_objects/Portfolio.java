package db_objects;

public class Portfolio {
	private int portfolio_id;
	private String name;
	private int user_id;
	private double investment;
	private double share_dist;
	private double comm_dist;
	private double curr_dist;
	private double estate_dist;
	private double bond_dist;
	private double sigma_full;
	private double risk_full;
	private double sigma_share;
	private double risk_share;
	private double sigma_comm;
	private double risk_comm;
	private double risk_asset_preview;
	private double sigma_asset_preview;
	
	public double getInvestment() {
		return investment;
	}

	public double getRisk_asset_preview() {
		return risk_asset_preview;
	}

	public double getSigma_asset_preview() {
		return sigma_asset_preview;
	}

	public void setInvestment(double investment) {
		this.investment = investment;
	}

	public void setRisk_asset_preview(double risk_asset_preview) {
		this.risk_asset_preview = risk_asset_preview;
	}

	public void setSigma_asset_preview(double sigma_asset_preview) {
		this.sigma_asset_preview = sigma_asset_preview;
	}


	public Portfolio(int portfolio_id, String name, int user_id, double capital, double share_dist, double comm_dist,
			double curr_dist, double estate_dist, double bond_dist, double sigma_full, double risk_full,
			double sigma_share, double risk_share, double sigma_comm, double risk_comm) {
		super();
		this.portfolio_id = portfolio_id;
		this.name = name;
		this.user_id = user_id;
		this.investment = capital;
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
		this.risk_asset_preview = 0;
		this.sigma_asset_preview = 0;
	}

	@Override
	public String toString() {
		return "Portfolio [portfolio_id=" + portfolio_id + ", name=" + name + ", user_id=" + user_id + ", investment="
				+ investment + ", share_dist=" + share_dist + ", comm_dist=" + comm_dist + ", curr_dist=" + curr_dist
				+ ", estate_dist=" + estate_dist + ", bond_dist=" + bond_dist + ", sigma_full=" + sigma_full
				+ ", risk_full=" + risk_full + ", sigma_share=" + sigma_share + ", risk_share=" + risk_share
				+ ", sigma_comm=" + sigma_comm + ", risk_comm=" + risk_comm + "]";
	}

	public int getPortfolio_id() {
		return portfolio_id;
	}

	public String getName() {
		return name;
	}

	public int getUser_id() {
		return user_id;
	}

	public double getCapital() {
		return investment;
	}

	public double getShare_dist() {
		return share_dist;
	}

	public double getComm_dist() {
		return comm_dist;
	}

	public double getCurr_dist() {
		return curr_dist;
	}

	public double getEstate_dist() {
		return estate_dist;
	}

	public double getBond_dist() {
		return bond_dist;
	}

	public double getSigma_full() {
		return sigma_full;
	}

	public double getRisk_full() {
		return risk_full;
	}

	public double getSigma_share() {
		return sigma_share;
	}

	public double getRisk_share() {
		return risk_share;
	}

	public double getSigma_comm() {
		return sigma_comm;
	}

	public double getRisk_comm() {
		return risk_comm;
	}

	public void setPortfolio_id(int portfolio_id) {
		this.portfolio_id = portfolio_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setCapital(double capital) {
		this.investment = capital;
	}

	public void setShare_dist(double share_dist) {
		this.share_dist = share_dist;
	}

	public void setComm_dist(double comm_dist) {
		this.comm_dist = comm_dist;
	}

	public void setCurr_dist(double curr_dist) {
		this.curr_dist = curr_dist;
	}

	public void setEstate_dist(double estate_dist) {
		this.estate_dist = estate_dist;
	}

	public void setBond_dist(double bond_dist) {
		this.bond_dist = bond_dist;
	}

	public void setSigma_full(double sigma_full) {
		this.sigma_full = sigma_full;
	}

	public void setRisk_full(double risk_full) {
		this.risk_full = risk_full;
	}

	public void setSigma_share(double sigma_share) {
		this.sigma_share = sigma_share;
	}

	public void setRisk_share(double risk_share) {
		this.risk_share = risk_share;
	}

	public void setSigma_comm(double sigma_comm) {
		this.sigma_comm = sigma_comm;
	}

	public void setRisk_comm(double risk_comm) {
		this.risk_comm = risk_comm;
	}
}
