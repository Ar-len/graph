package com.ktwl.graph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.LogicDelete;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yuxingxing
 * @date 2020/5/13 11:54
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description="超图对象")
public class Hypergraph implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty(value = "图形id,自动生成--新增时可忽略")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long graphId;
	
	/**
	 * 户编号
	 */
	@ApiModelProperty(value = "户编号")
	private String accountNo;
	/**
	 * 计算层数
	 */
	@ApiModelProperty(value = "计算层数")
	private Integer layers;
	
	/**
	 * 用途
	 */
	@ApiModelProperty(value = "用途")
	private String useType;
	
	/**
	 * 图形名称
	 */
	@ApiModelProperty(value = "图形名称")
	private String graphName;
	
	@ApiModelProperty(value = "单层面积")
	private Double graphArea;
	
	@ApiModelProperty(value = "图形总面积")
	private Double graphAreas;
	
	@ApiModelProperty(value = "点集合")
	private String pointSet;
	
	@ApiModelProperty(value = "图形标记")
	private String graphType;
	
	@ApiModelProperty(value = "用户名称")
	private String accountName;
	
	@ApiModelProperty(value = "图例")
	private String tuglie;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@ApiModelProperty(value = "创建时间--有条件查询时有用")
	private Date createTime;
	
	@ApiModelProperty(value = "是否逻辑删除--有条件查询时有用")
	@LogicDelete
	private Integer logicalDelete;
	
	
}
