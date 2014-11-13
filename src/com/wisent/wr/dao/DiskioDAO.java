package com.wisent.wr.dao;
import java.util.List;
import com.wisent.wr.model.Diskio;

public interface DiskioDAO {
	List<Diskio> selectList(String ip,String diskName,String yearMonth);
	

}
