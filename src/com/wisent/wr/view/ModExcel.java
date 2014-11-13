package com.wisent.wr.view;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.wisent.wr.lay.CpumemusedLaybiz;
import com.wisent.wr.lay.CpumemusedLaybizImpl;
import com.wisent.wr.lay.DiskioLaybiz;
import com.wisent.wr.lay.DiskioLaybizImpl;
import com.wisent.wr.lay.TablespaceLaybiz;
import com.wisent.wr.lay.TablespaceLaybizImpl;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ModExcel {

	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		if(args.length>0){
			String yearMonth = "";
			String sysName = "";
			String inXlsPath = "";
			String arg ;
			
			//固定参数为三个，第一个参数必须是sysname，第二个是日期，第三个是路径
			if(args.length==1){
				sysName = args[0];
			}else if(args.length==2){
				sysName = args[0];
				arg=args[1];
				
				//如果参数2包含斜杠和反斜杠则认定为文件路径格式参数
				if(arg.indexOf("\\")>=0||arg.indexOf("/")>=0){
					inXlsPath = args[1];
				//如果参数2用正则匹配为全部是数字且长度为6（201403）
				}else if(arg.length()==6&&arg.matches("\\d*")){
					yearMonth = args[1];
				}else{
					System.err.println("参数格式错误，参数2必须为6位年月如201405"
							+ "或者是要写入数据的xls文件路径，路径必须以双引号包围"
							+ "|wrong args e.g. 'dzsw' or 'dzsw 201401'");
					System.exit(1);
				}
				
			}else if(args.length==3){
				sysName = args[0];
				if(args[1].length()==6&&args[1].matches("\\d*")){yearMonth=args[1];}
				else{
					System.err.println("参数2格式错误|wrong arg2 ,should like. '201405'");
					System.exit(1);
				}
				if(args[2].indexOf("\\")>=0||args[2].indexOf("/")>=0){inXlsPath=args[2];}
				else{
					System.err.println("参数3格式错误|wrong arg3 ,should like. 'd://myFile.xls'");
					System.exit(1);
				}
				
			}else{
				System.err.println("参数格式错误|wrong args e.g. 'dzsw' or 'dzsw 201401'");
				System.exit(1);
			}
			
			/*for(int i=0;i<args.length;i++){
				arg = args[i++];
				System.out.println(arg);
				if(args[i].equals("-n")&&(!arg.equals("")||arg!=null)&&!arg.startsWith("-")){
					sysName = args[i+1];	
				}
				if(args[i].equals("-d")&&(!arg.equals("")||arg!=null)&&!arg.startsWith("-")){
					yearMonth = args[i+1]; 
				}
				if(args[i].equals("-h")){
					System.out.println("e.g. '-n dzsw' or '-n dzsw -d 201401'");
					System.exit(0);
				}
				
			}*/
			
			
			if(!sysName.equals("")){
				if(sysName.equals("dzsw")){
					//System.out.println(yearMonth+sysName);
					makeExcel(sysName,yearMonth,inXlsPath);
					System.exit(0);
					
				}else if(sysName.equals("tdxt")){
					//System.out.println(yearMonth+sysName);
					makeExcel(sysName,yearMonth,inXlsPath);
					System.exit(0);
					
				}else if(sysName.equals("11185")){
					//System.out.println(yearMonth+sysName);
					makeExcel(sysName,yearMonth,inXlsPath);
					System.exit(0);
					
				}else{
					System.err.println("参数1系统名称不存在|theres no name arg1");
					System.exit(1);
					
				}
				
			}

			
		}else{
			System.err.println("无参数|there r no args");
			System.exit(1);
		}
		
		
		
	}//end main	
	
	
    private static void makeExcel(String sysName,String yearMonth,String inXlsPath){
    	
    	//如果yearMonth是空值，则默认为上一个月日期
    	if(yearMonth.equals("")||yearMonth.equals(" ")||yearMonth==null){
    		  SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
     		  Calendar c = Calendar.getInstance();
     		  c.add(Calendar.MONTH, -1);
     		  yearMonth = df.format(c.getTime());
     	  }
    	
    	
		
        try {
        	
        	if(inXlsPath.equals("")){
                System.out.println("没有获得到模板文件，将自动生成....");
        		inXlsPath = "templateFile.xls";
        		WritableWorkbook mf = Workbook.createWorkbook(new File(inXlsPath));       		
        		WritableSheet sht1 = mf.createSheet("空主页", 0);
        		WritableSheet sht2 = mf.createSheet("表空间", 1);
        		WritableSheet sht3 = mf.createSheet("CPUMEM", 2);
        		WritableSheet sht4 = mf.createSheet("IO", 3);
        		mf.write();
        		mf.close();
        	}
			
			Workbook workBook =Workbook.getWorkbook(new File(inXlsPath));
			System.out.println("获取模板成功"); 
			WritableWorkbook copy2 =Workbook.createWorkbook(new File(sysName+"_"+yearMonth+"_output.xls"),workBook);
			
			WritableSheet sheet2 =copy2.getSheet(1);
			WritableSheet sheet3 =copy2.getSheet(2);
			WritableSheet sheet4 =copy2.getSheet(3);
			
			TablespaceLaybiz tlb = new TablespaceLaybizImpl();
			tlb.tbspcBySnYm(sheet2, sysName,yearMonth);
			CpumemusedLaybiz clb = new CpumemusedLaybizImpl();
			clb.cpumemBySnYm(sheet3, sysName,yearMonth);
			DiskioLaybiz dlb = new DiskioLaybizImpl();
			dlb.diskioBySnYm(sheet4, sysName,yearMonth);
			
			copy2.write();
			copy2.close();
			System.out.println("更新成功");
			
			
		} catch (BiffException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  } catch (IOException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  } catch (WriteException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		}
	}
		

}


