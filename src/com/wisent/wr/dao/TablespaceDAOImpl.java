package com.wisent.wr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;









import com.wisent.wr.model.Tablespace;
import com.wisent.wr.util.JdbcUtil;

public class TablespaceDAOImpl implements TablespaceDAO {
	
	
	private List<Tablespace> getTblspc(ResultSet rs) throws SQLException {
		List<Tablespace> tblspclst = new ArrayList<Tablespace>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		while(rs.next()){
			Tablespace tblspc = new Tablespace();
			
			tblspc.setTableName(rs.getString("KEYCOLSVALUE"));
			//tblspc.setTimeStamp(rs.getString("METRICTIMESTAMP"));
			tblspc.setTimeStamp(dateFormat.format(rs.getDate("METRICTIMESTAMP")));
			tblspc.setAvgValue(rs.getString("METRICAVGVALUE"));
			
			tblspclst.add(tblspc);
		}
		
		return tblspclst;
		
	}

	
	
	

	public  List<Tablespace> selectList(String ip, String tableName, String yearMonth) {
		// TODO 自动生成的方法存根
		List<Tablespace> tblspclist = new ArrayList<Tablespace>();
		//con,ps,rs
	    Connection con = null;
	    PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JdbcUtil.createConnection();
			String sql = "select t.devicepath,t.metrictimestamp,t.metricavgvalue,t.keycolsvalue,t.mixattrdisplay,t.devicedisplay "
					+ "from tblmetriclogday t where "
					+ "t.attrname = 'TablespaceAttr'  "
					+ "and t.devicepath = (select t.devicepath from tblnmdevice t where t.ipaddress = '"
					+ ip
					+ "' and t.name = 'Oracle') "
					+ "and t.keycolsvalue = '{"
					+ tableName
					+ "}' "
					+ "and to_char(t.metrictimestamp,'YYYYMM')='"
					+ yearMonth
					+ "' order by t.metrictimestamp";
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			tblspclist = getTblspc(rs);
			System.out.println("db->rs->list:tblspc list success.............");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			JdbcUtil.close(con);
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
			
		}
		return tblspclist;
	}

}
