package com.wisent.wr.lay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.wisent.wr.dao.CpumemusedDAO;
import com.wisent.wr.dao.CpumemusedDAOImpl;
import com.wisent.wr.model.Cpumemused;


import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CpumemusedLaybizImpl implements CpumemusedLaybiz {
	  private	CpumemusedDAO dao = new CpumemusedDAOImpl();
	  List<Cpumemused> cpumemlsts = new ArrayList<Cpumemused>();
	
	  //私有方法，DAO中取出数据放入sheet对象中进行遍历布局
	private void layCpumem(WritableSheet sheet,String ip,String yearMonth,String[] attrNames){
		
		try {
			for(int i=0;i<attrNames.length;i++){
				cpumemlsts= dao.selectList(ip, attrNames[i], yearMonth);
				  for(int j=0;j<=cpumemlsts.size()-1;j++){
					  
					  Label lb0 =new Label(  i*4+0+2,j,cpumemlsts.get(j).getDisplayName() );
					  sheet.addCell(lb0);	
					  Label lb1 =new Label(  i*4+1+2,j,cpumemlsts.get(j).getAttrName() );
					  sheet.addCell(lb1);
					  Label lb2 =new Label(  i*4+2+2,j,cpumemlsts.get(j).getTimeStamp());
					  sheet.addCell(lb2);
					  Number nb2 =new Number(i*4+3+2,j,Double.parseDouble(cpumemlsts.get(j).getAvgValue()));
					  sheet.addCell(nb2);		
					  
				  }
				  
			  }
		} catch (RowsExceededException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
	};
	
private void layCpumemByIps(WritableSheet sheet,String yearMonth,String[] ips){
	  List<Cpumemused> cpulsts = new ArrayList<Cpumemused>();
	  List<Cpumemused> memlsts = new ArrayList<Cpumemused>();
		
		try {
			for(int i=0;i<ips.length;i++){
				cpulsts= dao.selectList(ips[i],"CPUInfo", yearMonth);
				memlsts=dao.selectList(ips[i], "MemInfo", yearMonth);
				  for(int j=0;j<=cpulsts.size()-1;j++){
					  
					  Label lb0 =new Label(  i*9+0+2,j,cpulsts.get(j).getDisplayName() );
					  sheet.addCell(lb0);	
					  Label lb1 =new Label(  i*9+1+2,j,cpulsts.get(j).getAttrName() );
					  sheet.addCell(lb1);
					  Label lb2 =new Label(  i*9+2+2,j,cpulsts.get(j).getTimeStamp());
					  sheet.addCell(lb2);
					  Number nb3 =new Number(i*9+3+2,j,Double.parseDouble(cpulsts.get(j).getAvgValue()));
					  sheet.addCell(nb3);
				  }
				  for(int j=0;j<=memlsts.size()-1;j++){
					  Label lb4 =new Label(  i*9+4+2,j,memlsts.get(j).getDisplayName() );
					  sheet.addCell(lb4);	
					  Label lb5 =new Label(  i*9+5+2,j,memlsts.get(j).getAttrName() );
					  sheet.addCell(lb5);
					  Label lb6 =new Label(  i*9+6+2,j,memlsts.get(j).getTimeStamp());
					  sheet.addCell(lb6);
					  Number nb7 =new Number(i*9+7+2,j,Double.parseDouble(memlsts.get(j).getAvgValue()));
					  sheet.addCell(nb7);	
					  
				  }
				  
			  }
		} catch (RowsExceededException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
	};



	public void cpumemBySnYm(WritableSheet sheet, String sysName,String yearMonth) {
		
		// TODO 自动生成的方法存根

		 if(sysName.equals("dzsw")){			 
			 String[] ips ={"10.143.10.181","10.143.10.163"};				   	
	   	     layCpumemByIps(sheet, yearMonth, ips);
	   	  	        
		//投递系统	  
		  }else if(sysName.equals("tdxt")){
		      String[] ips ={"10.143.10.77","10.143.10.96"};			
		   	  layCpumemByIps(sheet, yearMonth, ips);
			  			 
		//11185	  
		  }else if(sysName.equals("11185")){
		      String[] ips ={"10.143.11.3","10.143.11.5"};  
		   	  layCpumemByIps(sheet, yearMonth, ips);
			  
		  }
		 
		
	}

	public void cpumemBySn(WritableSheet sheet, String sysName) {
		
		// TODO 自动生成的方法存根
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
	   	Calendar c = Calendar.getInstance();
	   	c.add(Calendar.MONTH, -1);
	   	String	yearMonth = df.format(c.getTime());


		 if(sysName.equals("dzsw")){
			String[] ips ={"10.143.10.181","10.143.10.163"};		
		   	
	   	  layCpumemByIps(sheet, yearMonth, ips);
	   	  

	        
		//投递系统	  
		  }else if(sysName.equals("tdxt")){

		      String[] ips ={"10.143.10.77","10.143.10.96"};	
			  
		   	  layCpumemByIps(sheet, yearMonth, ips);
			  
			 
			  
		  }else if(sysName.equals("11185")){
		      String[] ips ={"10.143.11.3","10.143.11.5"};  
			  
		   	  layCpumemByIps(sheet, yearMonth, ips);
			  
		  }
		 
		
		
		
	}



}
