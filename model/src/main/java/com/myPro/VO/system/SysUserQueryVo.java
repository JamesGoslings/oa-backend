//
//
package com.myPro.VO.system;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户查询实体
 */
@Data
public class SysUserQueryVo implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	private String keyword;

//	private String createTimeBegin;
//	private String createTimeEnd;

	private Long roleId;
	private Long postId;
	private Long deptId;

}

