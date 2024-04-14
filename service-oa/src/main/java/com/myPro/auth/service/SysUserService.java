package com.myPro.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.system.SysUser;
import com.myPro.vo.app.SysUserVo;
import org.springframework.web.multipart.MultipartFile;

public interface SysUserService extends IService<SysUser>{
    void updateStatus(Long id, Integer status);

    SysUser getUserByUsername(String username);

    /**
     * 根据id获取头像路径，并根据路径将图片文件转成Base64字符串
     * @param id 查找的用户名id
     * @return 头像文件转成的Base64字符串
     * */
    String getAvatarBase64StrById(Long id);


    /**
     * 根据userId获取user对象并封装成SysUserVo对象,也用于获取移动端的初始化信息
     * @param userId 待进行操作的id
     * @param hasId 确定是否需要封装userId进去
     * @return 封装好的UserVo
     * */
    SysUserVo getUserVoById(Long userId,boolean hasId);


    /**
     * 根据userId获取user对象并封装成SysUserVo对象,也用于获取移动端的初始化信息
     * 要封装id
     * @param userId 待进行操作的id
     * @return 封装好的UserVo
     * */
    SysUserVo getUserVoById(Long userId);


}
