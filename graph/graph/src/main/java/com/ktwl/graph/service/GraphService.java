package com.ktwl.graph.service;

import com.ktwl.graph.entity.GraphVo;
import com.ktwl.graph.model.Hypergraph;
import java.util.List;

public interface GraphService {
	
	List<Hypergraph> selectAll();
	
	void insertGraph(GraphVo graphVo);
	
	void delete(Long id);
	
	void update(Hypergraph hypergraph);
	
	List<Hypergraph> selectGraphsByGraph(Hypergraph hypergraph);
	
	void PhysicalDeleteByGraph(Hypergraph hypergraph);
	
}
