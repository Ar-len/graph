package com.ktwl.graph.controller;

import com.ktwl.graph.common.ajaxResponse.AjaxResponse;
import com.ktwl.graph.common.ajaxResponse.CustomException;
import com.ktwl.graph.common.ajaxResponse.CustomExceptionType;
import com.ktwl.graph.entity.GraphVo;
import com.ktwl.graph.model.Hypergraph;
import com.ktwl.graph.service.GraphService;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * @author yuxingxing
 * @date 2020/5/13 11:48
 */
@Api(value = "超图接口api")
@RestController
@RequestMapping("/graph/")
@CrossOrigin
public class GraphController {
	@GetMapping("test")
	public String test(){
		return "testing";
	}
	@Autowired
	GraphService graphService;
	
	@ApiOperation(value = "查询所有图形或者旗帜")
	@GetMapping("graphs")
	public AjaxResponse selectGraphs(){
		List<Hypergraph> hypergraphs = graphService.selectAll();
		return AjaxResponse.success(hypergraphs);
	}
	
	@ApiOperation(value = "添加更新图形或者旗帜")
	@PostMapping("graph")
	public AjaxResponse insertGraph(@RequestBody GraphVo graphVo){
		if(graphVo ==null || graphVo.getHypergraphs().size() <=0){
			throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "请传入修改或者添加的图形或者旗帜");
		}
		graphService.insertGraph(graphVo);
		return AjaxResponse.success("添加更新成功");
	}
	
	@ApiOperation(value = "根据id逻辑删除图形或者旗帜")
	@DeleteMapping("graph/{id}")
	@ApiParam(name="",value="",required=true)
	public AjaxResponse deleteGraphById(@PathVariable(value = "id") Long id){
		graphService.delete(id);
		return AjaxResponse.success("逻辑删除成功");
	}
	
	@ApiOperation(value = "根据新的图形或者旗帜修改旧的图形或者旗帜")
	@PutMapping("graph")
	public AjaxResponse updateGraph(@RequestBody Hypergraph hypergraph){
		if(hypergraph == null){
			throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "请传入修改的图形或者旗帜");
		}
		graphService.update(hypergraph);
		return AjaxResponse.success("修改成功");
	}
	
//	@ApiOperation(value = "根据id物理删除图形或者旗帜")
//	@DeleteMapping("physicalGraph")
//	public AjaxResponse PhysicalDeleteGraph(@RequestBody Hypergraph hypergraph){
//		if(hypergraph ==null){
//			throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "请传入删除的图形或者旗帜");
//		}
//		graphService.PhysicalDeleteByGraph(hypergraph);
//		return AjaxResponse.success("物理删除成功");
//	}
	
	@ApiOperation(value = "根据图形或者旗帜查询户中的所有图形或者旗帜")
	@PostMapping("graphs")
	public AjaxResponse selectGraphsByGraph(@RequestBody Hypergraph hypergraph){
		if(hypergraph.getGraphId()==null ||StringUtil.isEmpty(hypergraph.getGraphType())){
			throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"输入图形或者旗帜参数不全,请修正");
		}
		return AjaxResponse.success(graphService.selectGraphsByGraph(hypergraph));
	}
}
