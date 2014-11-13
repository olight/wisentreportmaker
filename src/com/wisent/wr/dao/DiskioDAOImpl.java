package com.wisent.wr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.wisent.wr.model.Diskio;
import com.wisent.wr.util.JdbcUtil;

public class DiskioDAOImpl implements DiskioDAO {
	
	
	private List<Diskio> getDiskio(ResultSet rs) throws SQLException {
		List<Diskio> Dskiolsts = new ArrayList<Diskio>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		while(rs.next()){
			Diskio dskio = new Diskio();
			
			dskio.setDisplayName(rs.getString("DISPLAYNAME"));
			dskio.setTimeStamp(dateFormat.format(rs.getDate("METRICTIMESTAMP")));
			dskio.setAvgValue(rs.getString("METRICAVGVALUE"));
			
			Dskiolsts.add(dskio);
		}
		
		return Dskiolsts;
		
	}


	public List<Diskio> selectList(String ip, String diskName, String yearMonth) {
		// TODO 自动生成的方法存根
		List<Diskio> dskiolsts = new ArrayList<Diskio>();
		//con,ps,rs
	    Connection con = null;
	    PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.createConnection();
			String sql = " select a.displayname,t.metrictimestamp,t.metricavgvalue from tblmetriclogday t ,tblnmdevice a "
					+ "where t.devicepath = a.devicepath "
					+ "and t.attrname = 'DiskIo' "
					+ "and a.ipaddress = '"+ip+"' "
					+ "and a.name = 'Linux' "
					+ "and t.keycolsvalue = '{"+diskName+"}' "
					+ "and to_char(t.metrictimestamp,'YYYYMM')='"+yearMonth+"' "
					+ "order by t.metrictimestamp ";
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			dskiolsts = getDiskio(rs);
			System.out.println("db->rs->list:diskio list success.............");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			JdbcUtil.close(con);
			JdbcUtil.close(ps);
			JdbcUtil.close(rs);
			
		}
		
		return dskiolsts;
	}


	

}
