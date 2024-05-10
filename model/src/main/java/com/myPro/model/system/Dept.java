package com.myPro.model.system;

import com.baomidou.mybatisplus.annotation.*;
import com.myPro.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "部门")
@TableName("dept")
public class Dept extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	private Long id;

	@TableField("create_time")
	private Date createTime;

	@TableField("update_time")
	private Date updateTime;

	@TableLogic
	@TableField("is_deleted")
	private Integer isDeleted;

	@ApiModelProperty(value = "部门名称")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "上级部门id")
	@TableField("parent_id")
	private Long parentId;

	@TableField("dept_code")
	private String deptCode;

//	@ApiModelProperty(value = "树结构")
//	@TableField("tree_path")
//	private String treePath;
//
//	@ApiModelProperty(value = "排序")
//	@TableField("sort_value")
//	private Integer sortValue;

//	@ApiModelProperty(value = "负责人")
//	@TableField("leader")
//	private String leader;
//
//	@ApiModelProperty(value = "电话")
//	@TableField("phone")
//	private String phone;

	@TableField(exist = false)
	private Long myCount; // 记录本部门的直接工作人员

	@TableField(exist = false)
	private Long totalCount; // 记录该部门及其所有子部门的工作人员

	@TableField("leader_id")
	private Long leaderId;

	@ApiModelProperty(value = "状态（1正常 0停用）")
	@TableField("status")
	private Integer status;


	@ApiModelProperty(value = "下级部门")
	@TableField(exist = false)
	private List<Dept> children;

}