package com.myPro.model.process;

import com.myPro.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "ProcessRecord")
@TableName("process_record")
public class ProcessRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "审批流程id")
	@TableField("process_id")
	private Long processId;

	@ApiModelProperty(value = "状态")
	@TableField("status")
	private Integer status;

	@ApiModelProperty(value = "操作用户id")
	@TableField("doing_user_id")
	private Long doingUserId;

}