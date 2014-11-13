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
		// TODO �Զ����ɵķ������
		if(args.length>0){
			String yearMonth = "";
			String sysName = "";
			String inXlsPath = "";
			String arg ;
			
			//�̶�����Ϊ��������һ������������sysname���ڶ��������ڣ���������·��
			if(args.length==1){
				sysName = args[0];
			}else if(args.length==2){
				sysName = args[0];
				arg=args[1];
				
				//�������2����б�ܺͷ�б�����϶�Ϊ�ļ�·����ʽ����
				if(arg.indexOf("\\")>=0||arg.indexOf("/")>=0){
					inXlsPath = args[1];
				//�������2������ƥ��Ϊȫ���������ҳ���Ϊ6��201403��
				}else if(arg.length()==6&&arg.matches("\\d*")){
					yearMonth = args[1];
				}else{
					System.err.println("������ʽ���󣬲���2����Ϊ6λ������201405"
							+ "������Ҫд�����ݵ�xls�ļ�·����·��������˫���Ű�Χ"
							+ "|wrong args e.g. 'dzsw' or 'dzsw 201401'");
					System.exit(1);
				}
				
			}else if(args.length==3){
				sysName = args[0];
				if(args[1].length()==6&&args[1].matches("\\d*")){yearMonth=args[1];}
				else{
					System.err.println("����2��ʽ����|wrong arg2 ,should like. '201405'");
					System.exit(1);
				}
				if(args[2].indexOf("\\")>=0||args[2].indexOf("/")>=0){inXlsPath=args[2];}
				else{
					System.err.println("����3��ʽ����|wrong arg3 ,should like. 'd://myFile.xls'");
					System.exit(1);
				}
				
			}else{
				System.err.println("������ʽ����|wrong args e.g. 'dzsw' or 'dzsw 201401'");
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
					System.err.println("����1ϵͳ���Ʋ�����|theres no name arg1");
					System.exit(1);
					
				}
				
			}

			
		}else{
			System.err.println("�޲���|there r no args");
			System.exit(1);
		}
		
		
		
	}//end main	
	
	
    private static void makeExcel(String sysName,String yearMonth,String inXlsPath){
    	
    	//���yearMonth�ǿ�ֵ����Ĭ��Ϊ��һ��������
    	if(yearMonth.equals("")||yearMonth.equals(" ")||yearMonth==null){
    		  SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
     		  Calendar c = Calendar.getInstance();
     		  c.add(Calendar.MONTH, -1);
     		  yearMonth = df.format(c.getTime());
     	  }
    	
    	
		
        try {
        	
        	if(inXlsPath.equals("")){
                System.out.println("û�л�õ�ģ���ļ������Զ�����....");
        		inXlsPath = "templateFile.xls";
        		WritableWorkbook mf = Workbook.createWorkbook(new File(inXlsPath));       		
        		WritableSheet sht1 = mf.createSheet("����ҳ", 0);
        		WritableSheet sht2 = mf.createSheet("��ռ�", 1);
        		WritableSheet sht3 = mf.createSheet("CPUMEM", 2);
        		WritableSheet sht4 = mf.createSheet("IO", 3);
        		mf.write();
        		mf.close();
        	}
			
			Workbook workBook =Workbook.getWorkbook(new File(inXlsPath));
			System.out.println("��ȡģ��ɹ�"); 
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
			System.out.println("���³ɹ�");
			
			
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


