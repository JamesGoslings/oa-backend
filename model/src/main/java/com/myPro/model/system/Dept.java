package com.myPro.model.system;

import com.baomidou.mybatisplus.annotation.*;
import com.myPro.model.base.BaseEntity;
import com.myPro.vo.system.SysUserWebVo;
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

	@TableField(exist = false)
	private Long myCount; // 记录本部门的直接工作人员

	@TableField(exist = false)
	private Long totalCount; // 记录该部门及其所有子部门的工作人员

	@TableField("leader_id")
	private Long leaderId;

	@TableField("is_add_children_count")
	private Integer isAddChildrenCount;

	@TableField(exist = false)
	private SysUserWebVo leader;// 存负责人信息

	@ApiModelProperty(value = "状态（1正常 0停用）")
	@TableField("status")
	private Integer status;

	@TableField(exist = false)
	private List<Dept> children;

}