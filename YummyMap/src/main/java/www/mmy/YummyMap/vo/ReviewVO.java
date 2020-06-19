package www.mmy.YummyMap.vo;

public class ReviewVO {
	private int rev_no, rating_upso;
	private String mid, res_id, rev_txt, cr_date;
	public int getRev_no() {
		return rev_no;
	}
	public void setRev_no(int rev_no) {
		this.rev_no = rev_no;
	}
	public int getRating_upso() {
		return rating_upso;
	}
	public void setRating_upso(int rating_upso) {
		this.rating_upso = rating_upso;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getRes_id() {
		return res_id;
	}
	public void setRes_id(String res_id) {
		this.res_id = res_id;
	}
	public String getRev_txt() {
		return rev_txt;
	}
	public void setRev_txt(String rev_txt) {
		this.rev_txt = rev_txt;
	}
	public String getCr_date() {
		return cr_date;
	}
	public void setCr_date(String cr_date) {
		this.cr_date = cr_date;
	}
	@Override
	public String toString() {
		return "ReviewVO [rev_no=" + rev_no + ", rating_upso=" + rating_upso + ", mid=" + mid + ", res_id=" + res_id
				+ ", rev_txt=" + rev_txt + ", cr_date=" + cr_date + "]";
	}
}
