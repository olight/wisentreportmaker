package com.wisent.wr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.wisent.wr.model.Cpumemused;
import com.wisent.wr.util.JdbcUtil;

public class CpumemusedDAOImpl implements CpumemusedDAO {
	private List<Cpumemused> getCpMe(ResultSet rs) throws SQLException {
		List<Cpumemused> cpumemlsts = new ArrayList<Cpumemused>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		while(rs.next()){
			Cpumemused cpumem = new Cpumemused();
			
			cpumem.setDisplayName(rs.getString("DISPLAYNAME"));
			cpumem.setAttrName(rs.getString("ATTRNAME"));
			cpumem.setTimeStamp(dateFormat.format(rs.getDate("METRICTIMESTAMP")));
			cpumem.setAvgValue(rs.getString("METRICAVGVALUE"));
			
			cpumemlsts.add(cpumem);
		}
		
		return cpumemlsts;
		
	}


	public List<Cpumemused> selectList(String ip, String attrName,
			String yearMonth) {
		// TODO 自动生成的方法存根
		List<Cpumemused> cpumemlsts = new ArrayList<Cpumemused>();
		//con,ps,rs
	    Connection con = null;
	    PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.createConnection();
			String sql = "select a.displayname, t.attrname,t.metrictimestamp,t.metricavgvalue from tblmetriclogday t ,tblnmdevice a "
					+ "where "
					+ "t.devicepath = a.devicepath "
					+ "and t.attrname = '"+attrName+"' "
					+ "and a.ipaddress ='"+ip+"' "
					+ "and a.name = 'Linux' "
					+ "and to_char(t.metrictimestamp,'YYYYMM')='"+yearMonth+"' "
					+ "order by t.metrictimestamp ";
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			cpumemlsts = getCpMe(rs);
			System.out.println("db->rs->list:cpumem list success.............");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			JdbcUtil.close(con);
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
			
		}
		
		return cpumemlsts;
	}

}
