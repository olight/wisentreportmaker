package com.wisent.wr.dao;

import java.util.List;

import com.wisent.wr.model.Cpumemused;

public interface CpumemusedDAO {
	List<Cpumemused> selectList(String ip,String attrName,String yearMonth);

}
