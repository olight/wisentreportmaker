package com.wisent.wr.lay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.wisent.wr.dao.DiskioDAO;
import com.wisent.wr.dao.DiskioDAOImpl;
import com.wisent.wr.model.Diskio;


import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DiskioLaybizImpl implements DiskioLaybiz {
	
	  private	DiskioDAO dao = new DiskioDAOImpl();
	  List<Diskio> dskiolsts = new ArrayList<Diskio>();
	
	 //˽�з�����DAO��ȡ�����ݷ���sheet�����н��б�������
	  private void layDskio(WritableSheet sheet,String[] ips, String[] diskNames,String yearMonth){
		  try {
				for(int i=0;i<ips.length;i++){
					dskiolsts= dao.selectList(ips[i], diskNames[i], yearMonth);
					  for(int j=0;j<=dskiolsts.size()-1;j++){
						  
						  Label lb0 =new Label(i*4+2, j,dskiolsts.get(j).getDisplayName() );
						  sheet.addCell(lb0);	
						  Label lb1 =new Label(i*4+1+2, j,dskiolsts.get(j).getTimeStamp() );
						  sheet.addCell(lb1);
						  Number nb2 =new Number(i*4+2+2,j,Double.parseDouble(dskiolsts.get(j).getAvgValue()));
						  sheet.addCell(nb2);		
						  
					  }
					  
				  }
			} catch (NumberFormatException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (RowsExceededException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		  
	  };

	public void diskioBySnYm(WritableSheet sheet, String sysName,
			String yearMonth) {
		// TODO �Զ����ɵķ������

		 if(sysName.equals("dzsw")){			 
			 String[] ips ={"10.143.10.181","10.143.10.163"};
			 String[] diskNames ={"sda","sda"};
			 layDskio(sheet,ips,diskNames,yearMonth);
	   	  	        
		//Ͷ��ϵͳ	  
		  }else if(sysName.equals("tdxt")){
		      String[] ips ={"10.143.10.77","10.143.10.96"};
		      String[] diskNames={"sda3","cciss/c0d0"};
			  layDskio(sheet,ips,diskNames,yearMonth);
			  			 
		//11185	  
		  }else if(sysName.equals("11185")){
		      String[] ips ={"10.143.11.3","10.143.11.5"};  
			  String[] diskNames ={"sda","sda"};
			  layDskio(sheet,ips,diskNames,yearMonth);
			  
		  }
		 
		
	}

	public void diskioBySn(WritableSheet sheet, String sysName) {
		// TODO �Զ����ɵķ������
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
	   	Calendar c = Calendar.getInstance();
	   	c.add(Calendar.MONTH, -1);
	   	String	yearMonth = df.format(c.getTime());


	   	if(sysName.equals("dzsw")){			 
	   		String[] ips ={"10.143.10.181","10.143.10.163"};
			 String[] diskNames ={"sda","sda"};
			 layDskio(sheet,ips,diskNames,yearMonth);
	   	  	        
		//Ͷ��ϵͳ	  
		  }else if(sysName.equals("tdxt")){
			  String[] ips ={"10.143.10.77","10.143.10.96"};
		      String[] diskNames={"sda3","cciss/c0d0"};
			  layDskio(sheet,ips,diskNames,yearMonth);
			  			 
		//11185	  
		  }else if(sysName.equals("11185")){
			  String[] ips ={"10.143.11.3"};  
			  String[] diskNames ={"sda"};
			  layDskio(sheet,ips,diskNames,yearMonth);
			  
		  }
		 
		 
		
	}
	

}
