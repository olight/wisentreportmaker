package com.wisent.wr.lay;



import jxl.write.WritableSheet;



public interface TablespaceLaybiz {
	//Tablespace chkByIpTbnmYM (String ip , String tableName,String yearMonth);
 
	void tbspcBySnYmTns(WritableSheet sheet,String sysName,String yearMonth,String[] tableNames);
	void tbspcBySnYm(WritableSheet sheet,String sysName,String yearMonth);
	void tbspcBySn(WritableSheet sheet,String sysName);
}
