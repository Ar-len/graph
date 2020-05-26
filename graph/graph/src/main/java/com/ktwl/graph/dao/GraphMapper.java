package com.ktwl.graph.dao;

import com.ktwl.graph.model.Hypergraph;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author yuxingxing
 * @date 2020/5/13 12:10
 */
@Component
public interface GraphMapper extends Mapper<Hypergraph> {
}
