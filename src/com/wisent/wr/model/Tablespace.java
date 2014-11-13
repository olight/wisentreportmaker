package com.wisent.wr.model;

public class Tablespace {
	private String ip;
	private String sysName;
	private String tableName;
	private String yearMonth;
	private String timeStamp;
	private String avgValue;

	public Tablespace() {
		// TODO 自动生成的构造函数存根
		super();
	}
	public Tablespace(String ip,String tableName ,String yearMonth) {
		// TODO 自动生成的构造函数存根
		super();
		this.ip =ip;
		this.tableName = tableName;
		this.yearMonth =yearMonth;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getAvgValue() {
		return avgValue;
	}
	public void setAvgValue(String avgValue) {
		this.avgValue = avgValue;
	}

}
