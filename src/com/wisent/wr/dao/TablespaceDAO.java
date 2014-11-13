package com.wisent.wr.dao;

import java.util.List;

import com.wisent.wr.model.Tablespace;

public interface TablespaceDAO {
	
	List<Tablespace> selectList(String ip,String tableName,String yearMonth);

}
