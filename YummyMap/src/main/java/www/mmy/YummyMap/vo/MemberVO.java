package www.mmy.YummyMap.vo;

public class MemberVO {
	private int mno;
	private String mname, mid, mpw, mtel, meMail, joinDate;
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getMtel() {
		return mtel;
	}
	public void setMtel(String mtel) {
		this.mtel = mtel;
	}
	public String getMeMail() {
		return meMail;
	}
	public void setMeMail(String meMail) {
		this.meMail = meMail;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	@Override
	public String toString() {
		return "MemberVO [mno=" + mno + ", mname=" + mname + ", mid=" + mid + ", mtel=" + mtel + ", meMail=" + meMail
				+ ", joinDate=" + joinDate + "]";
	}
	
	
}
