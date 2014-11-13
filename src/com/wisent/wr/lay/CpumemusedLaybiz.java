package com.wisent.wr.lay;

import jxl.write.WritableSheet;

public interface CpumemusedLaybiz {
	//void cpumemBySnYmAttrs(WritableSheet sheet,String sysName,String yearMonth,String[] attrNames);
	void cpumemBySnYm(WritableSheet sheet,String sysName,String yearMonth);
	void cpumemBySn(WritableSheet sheet,String sysName);

}
