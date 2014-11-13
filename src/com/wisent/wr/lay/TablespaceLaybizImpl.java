package com.wisent.wr.lay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.wisent.wr.dao.TablespaceDAO;
import com.wisent.wr.dao.TablespaceDAOImpl;
import com.wisent.wr.model.Tablespace;

public class TablespaceLaybizImpl implements TablespaceLaybiz {
	
	
  private	TablespaceDAO dao = new TablespaceDAOImpl();
  List<Tablespace> tblspclst = new ArrayList<Tablespace>();
  
  //私有方法，DAO中取出数据放入sheet对象中进行遍历布局
  private void layTbspc(WritableSheet sheet,String ip, String yearMonth, String[] tableNames){
	  try {
			for(int i=0;i<tableNames.length;i++){
				  tblspclst= dao.selectList(ip, tableNames[i], yearMonth);
				  for(int j=0;j<=tblspclst.size()-1;j++){
					  
					  Label lb0 =new Label(i*4+2, j,tblspclst.get(j).getTableName() );
					  sheet.addCell(lb0);	
					  Label lb1 =new Label(i*4+1+2, j,tblspclst.get(j).getTimeStamp() );
					  sheet.addCell(lb1);
					  Number nb2 =new Number(i*4+2+2,j,Double.parseDouble(tblspclst.get(j).getAvgValue()));
					  sheet.addCell(nb2);		
					  
				  }
				  
			  }
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	  
  };


  public void tbspcBySnYmTns(WritableSheet sheet,String sysName, String yearMonth, String[] tableNames) {
	  SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
	  
	  
	  
	// 电子商务
      if(sysName.equals("dzsw")){
    	  String ip ="10.143.10.181";
    	  if(yearMonth.equals("")||yearMonth.equals(" ")||yearMonth==null){
    		  Calendar c = Calendar.getInstance();
    		  c.add(Calendar.MONTH, -1);
    		  yearMonth = df.format(c.getTime());
    	  }
    	  layTbspc(sheet, ip, yearMonth, tableNames);
    	  

         
	//投递系统	  
	  }else if(sysName.equals("tdxt")){
		  
		  String ip ="10.143.10.67"; 
		  if(yearMonth.equals("")||yearMonth.equals(" ")||yearMonth==null){
    		  Calendar c = Calendar.getInstance();
    		  c.add(Calendar.MONTH, -1);
    		  yearMonth = df.format(c.getTime());
    	  }
		  layTbspc(sheet, ip, yearMonth, tableNames);
		  
		 
		  
	  }else if(sysName.equals("11185")){
		  String ip ="10.143.11.3";
		  if(yearMonth.equals("")||yearMonth.equals(" ")||yearMonth==null){
    		  Calendar c = Calendar.getInstance();
    		  c.add(Calendar.MONTH, -1);
    		  yearMonth = df.format(c.getTime());
    	  }
		  layTbspc(sheet, ip, yearMonth, tableNames);
		  
	  }
	
 }


 public void tbspcBySnYm(WritableSheet sheet, String sysName, String yearMonth) {
	 
	// TODO 自动生成的方法存根

	 if(sysName.equals("dzsw")){
   	  String ip ="10.143.10.181";
      String[] tableNames ={"FAPDBSPACE","PCPOSTDB","SAMS_01"};  	  
   	 
   	  layTbspc(sheet, ip, yearMonth, tableNames);
   	  

        
	//投递系统	  
	  }else if(sysName.equals("tdxt")){
	      String[] tableNames ={"TDXTDATA","TDXTBIGDATA","YZZJBIGDATA","ZJDADATA"};  
		  String ip ="10.143.10.67"; 
		
		  layTbspc(sheet, ip, yearMonth, tableNames);
		  
		 
		  
	  }else if(sysName.equals("11185")){
		  String ip ="10.143.11.3";
	      String[] tableNames ={"POST_KF"};  
		 
		  layTbspc(sheet, ip, yearMonth, tableNames);
		  
	  }
	 
	
 }


  public void tbspcBySn(WritableSheet sheet, String sysName) {
	  
	// TODO 自动生成的方法存根
	  SimpleDateFormat df = new SimpleDateFormat("yyyyMM");

	  if(sysName.equals("dzsw")){
		  
	   	  String ip ="10.143.10.181";
	      String[] tableNames ={"FAPDBSPACE","PCPOSTDB","SAMS_01"}; 	      
	   	  Calendar c = Calendar.getInstance();
	   	  c.add(Calendar.MONTH, -1);
	      String yearMonth =df.format(c.getTime());
	      
	   	  layTbspc(sheet, ip,yearMonth , tableNames);
	   	  

	        
		//投递系统	  
		}else if(sysName.equals("tdxt")){
			String[] tableNames ={"TDXTDATA","TDXTBIGDATA","YZZJBIGDATA","ZJDADATA"};  
			String ip = "10.143.10.67";
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String yearMonth = df.format(c.getTime());

			layTbspc(sheet, ip, yearMonth, tableNames);
		   	  

			
		}else if(sysName.equals("11185")){
			String ip ="10.143.11.3";
			String[] tableNames = { "POST_KF" };
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String yearMonth = df.format(c.getTime());

			layTbspc(sheet, ip, yearMonth, tableNames);
			
		}
	  
	
  }

 


	
	
	


	

}
