package com.ktwl.graph.entity;

import com.ktwl.graph.model.Hypergraph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yuxingxing
 * @date 2020/5/14 10:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphVo {
	private List<Hypergraph> hypergraphs;
}
