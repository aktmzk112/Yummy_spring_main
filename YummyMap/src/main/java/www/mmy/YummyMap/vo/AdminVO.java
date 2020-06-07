package www.mmy.YummyMap.vo;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class AdminVO {
	private int mno, rno;
	private String mname , mid , mpw, mtel  ,issue , memail , sDate;
	private Date joindate;
	private Time jointime;
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
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
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate() {
		
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat form2 = new SimpleDateFormat("HH:mm");
		
		this.sDate = form1.format(joindate) + " " + form2.format(jointime);
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	
	
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	public Time getJointime() {
		return jointime;
	}
	public void setJointime(Time jointime) {
		this.jointime = jointime;
	}
	
	
}
