package com.wisent.wr.lay;

import jxl.write.WritableSheet;

public interface DiskioLaybiz {
	
	void diskioBySnYm(WritableSheet sheet,String sysName,String yearMonth);
	void diskioBySn(WritableSheet sheet,String sysName);

}
