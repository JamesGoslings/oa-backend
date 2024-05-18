package com.myPro.auth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.system.SysUser;
import com.myPro.vo.app.SysUserVo;
import com.myPro.vo.system.SysUserWebVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     * 默认要封装userId
     * @param userId 待进行操作的id
     * @return 封装好的UserVo
     * */
    SysUserVo getUserVoById(Long userId);

    /**
     * 根据userId获取user对象并封装成SysUserWebVo对象,用于获取web端的初始化信息
     * @param userId 待进行操作的id
     * @return 封装好的UserWebVo
     * */
    SysUserWebVo getUserWebVoById(Long userId);

    /**
     * 将获得到的page对象中的record的user改成sysUserWebVo,再返回这个page
     * */
    Page<SysUserWebVo> getUserWebVoPage(Page<SysUser> page);

    /**
     * 拿到所有的用户并封装成webVo返回
     * */
    List<SysUserWebVo> getAllUsersWebVo();

    /**
     * 将user对象封装成userWebVo
     * @param user 待封装的user对象
     * @return 封装好的WebVo
     */
    SysUserWebVo getUserWebVoByUser(SysUser user);
}
