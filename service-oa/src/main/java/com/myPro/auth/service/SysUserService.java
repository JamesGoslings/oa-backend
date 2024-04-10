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
     * 获取移动端的初始化信息
     * */
    SysUserVo getUserVoById(Long userId);
}
