package com.ktwl.graph.service.impl;

import com.ktwl.graph.common.IdWorker;
import com.ktwl.graph.common.ajaxResponse.CustomException;
import com.ktwl.graph.common.ajaxResponse.CustomExceptionType;
import com.ktwl.graph.dao.GraphMapper;
import com.ktwl.graph.entity.GraphVo;
import com.ktwl.graph.model.Hypergraph;
import com.ktwl.graph.service.GraphService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;
import java.util.List;
import java.util.zip.CheckedOutputStream;

/**
 * @author yuxingxing
 * @date 2020/5/13 12:12
 */
@Slf4j
@Service
public class GraphServiceImpl implements GraphService {
	@Autowired
	GraphMapper graphMapper;
	
	
	
	/**
	 * 查询全部不包含已逻辑删除
	 */
	@Override
	@Transactional
	public List<Hypergraph> selectAll() {
		List<Hypergraph> hypergraphs = graphMapper.selectAll();
		for(Hypergraph hypergraph:hypergraphs){
			if(hypergraph.getGraphType().equals("2")){
				//查询时,当图形时旗帜时,修改相同户编号的图形户姓名和图例并拿到图形的总面积
				Example example = new Example(Hypergraph.class);
				Example.Criteria criteria = example.createCriteria();
				if(!StringUtil.isEmpty(hypergraph.getAccountNo())){
					criteria.andNotEqualTo("graphType", "2");
					criteria.andEqualTo("accountNo",hypergraph.getAccountNo());
				}
				Hypergraph hypergraph1 = new Hypergraph();
				hypergraph1.setTuglie(hypergraph.getTuglie());
				hypergraph1.setAccountName(hypergraph.getAccountName());
				graphMapper.updateByExampleSelective(hypergraph1,example);
				List<Hypergraph> hypergraphList = graphMapper.selectByExample(example);
				double areas =0.00 ;
				for (Hypergraph hypergraph2:hypergraphList){
					areas +=hypergraph2.getGraphAreas();
				}
				hypergraph.setGraphAreas(areas);
				graphMapper.updateByPrimaryKey(hypergraph);
			}
		}
		return hypergraphs;
	}
	
	/**
	 * 增加图形或者旗帜
	 */
	@Override
	@Transactional
	public void insertGraph(GraphVo graphVo){
		IdWorker idWorker = new IdWorker(0, 0);
		int count= 0;
		for (Hypergraph hypergraph : graphVo.getHypergraphs()){
			Date date = new Date();
			hypergraph.setCreateTime(date);
			if(hypergraph.getGraphId()!=null && hypergraph.getGraphId()!=0){
				int i = graphMapper.updateByPrimaryKey(hypergraph);
				count +=i;
				if (i>0){
					updateGraphByG(hypergraph);
				}
			}else {
					hypergraph.setGraphId(idWorker.nextId());
					int i = graphMapper.insertSelective(hypergraph);
					if (i>0 && hypergraph.getGraphType().equals("1")){
						updateGraphByG(hypergraph);
					}
					count +=i;
					if (count<=0){
						throw new CustomException(CustomExceptionType.SYSTEM_ERROR,"添加/更改失败");
					}
				}
		}
		
	}
	
	/*
	* 根据图形更新棋子面积和图形户名称和图例
	* */
	public  void updateGraphByG(Hypergraph hypergraph){
		Example example = new Example(Hypergraph.class);
		Example.Criteria criteria = example.createCriteria();
		Hypergraph hy = new Hypergraph();
		hy.setAccountNo(hypergraph.getAccountNo());
		hy.setGraphType("2");
		Hypergraph hyOne = graphMapper.selectOne(hy);
		if(hyOne!=null){
			if(hypergraph.getGraphAreas()>0){
				hyOne.setGraphAreas(hyOne.getGraphAreas()+hypergraph.getGraphAreas());
				graphMapper.updateByPrimaryKey(hyOne);
			}
			hypergraph.setAccountName(hyOne.getAccountName());
			hypergraph.setTuglie(hyOne.getTuglie());
			graphMapper.updateByPrimaryKey(hypergraph);
		}
	}
	/**
	 * 根据图形或者旗帜id物理删除图形或者旗帜
	 */
	@Override
	public void delete(Long id) {
		int i = graphMapper.deleteByPrimaryKey(id);
		if(i<=0){
			throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "输入图形或者旗帜的id不存在");
		}
	}
	
	/**
	 * 根据图形或者旗帜逻辑删除图形或者旗帜
	 */
	@Override
	public void PhysicalDeleteByGraph(Hypergraph hypergraph) {
		int i = graphMapper.updateByPrimaryKey(hypergraph);
		if(i<=0){
			throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "请输入正确的图形或者旗帜");
		}
	}
	
	/**
	 * 根据图形或者旗帜修改图形或者旗帜
	 */
	@Override
	public void update(Hypergraph hypergraph) {
		int i = graphMapper.updateByPrimaryKeySelective(hypergraph);
		if (i<=0){
			throw new CustomException(CustomExceptionType.SYSTEM_ERROR,"修改失败");
		}
	}
	
	/**
	 * 点击旗帜时查询
	 */
	@Override
	public List<Hypergraph> selectGraphsByGraph(Hypergraph hypergraph) {
		Example example = new Example(Hypergraph.class);
		Example.Criteria criteria = example.createCriteria();
		if(hypergraph.getGraphType().equals("2")){
			if(hypergraph.getAccountNo()!=null ){
				criteria.andEqualTo("accountNo",hypergraph.getAccountNo());
			}else {
				throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "请输入户编号");
			}
			return graphMapper.selectByExample(example);
		}else {
			if(hypergraph.getGraphId()!=null){
				criteria.andEqualTo("graphId",hypergraph.getGraphId());
			}
			return graphMapper.selectByExample(example);
		}
	}
	
	/**
	 * 条件生成方法
	 * */
	public Example createExample(Hypergraph hypergraph){
		Example example = new Example(Hypergraph.class);
		Example.Criteria criteria = example.createCriteria();
		if(hypergraph!=null){
//			if (!StringUtil.isEmpty(Hypergraph.getType())) {
//				criteria.andLike("Hypergraph","%"+Hypergraph.getType()+"%");
//			}
//			if (!StringUtil.isEmpty(Hypergraph.getGraphId())) {
//				criteria.andLike("typeName","%"+Hypergraph.getTypeName()+"%");
//			}
//			if (hypergraph.getLogicalDelete()!=1){
//				criteria.andEqualTo("logicalDelete", hypergraph.getLogicalDelete());
//			}
//			if (hypergraph.getAccountName() !=null) {
//				criteria.andEqualTo("accountName", hypergraph.getAccountName());
//			}
		}
		return example;
	}
}
