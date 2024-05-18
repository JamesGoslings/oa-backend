package com.myPro.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.mapper.DeptMapper;
import com.myPro.auth.mapper.PostMapper;
import com.myPro.auth.mapper.SysUserMapper;
import com.myPro.auth.service.SysRoleService;
import com.myPro.auth.service.SysUserService;
import com.myPro.common.utils.FileUtil;
import com.myPro.model.system.SysUser;
import com.myPro.vo.app.SysUserVo;
import com.myPro.vo.system.SysUserWebVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private SysRoleService roleService;

    //更新状态
    @Override
    public void updateStatus(Long id, Integer status) {
        SysUser sysUser = baseMapper.selectById(id);
        sysUser.setStatus(status);
        baseMapper.updateById(sysUser);
    }

    @Override
    public SysUser getUserByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<SysUser>().
                eq(SysUser::getUsername, username));
    }

    @Override
    public String getAvatarBase64StrById(Long id) {
        return getAvatarBase64StrByUser(getById(id));
    }

    public String getAvatarBase64StrByUser(SysUser user){
        String imgPath = user.getHeadUrl();
        if(StringUtils.isEmpty(imgPath)){
            return "";
        }
        String base64Str = FileUtil.convertImageToBase64Str(FileUtil.rootPath + imgPath);
        String suffix = FileUtil.getFileSuffix(imgPath).substring(1);
        if(!suffix.equals("gif")){
            suffix = "png";
        }
        return "data:image/" + suffix + ";base64," + base64Str;
    }

    @Override
    public SysUserVo getUserVoById(Long userId,boolean hasId) {
        SysUser user = getById(userId);
        SysUserVo userVo = new SysUserVo();
        userVo.setName(user.getName());
        if(hasId) {
            userVo.setUserId(userId);
        }
        userVo.setCreateTime(user.getCreateTime());
        userVo.setAvatarUrl(getAvatarBase64StrByUser(user));
        userVo.setPost(postMapper.selectById(user.getPostId()).getName());
        userVo.setDept(deptMapper.selectById(user.getDeptId()).getName());
        userVo.setPhone(user.getPhone());

        return userVo;
    }

    @Override
    public SysUserVo getUserVoById(Long userId) {
        return getUserVoById(userId, true);
    }

    @Override
    public SysUserWebVo getUserWebVoById(Long userId) {
        return getUserWebVoByUser(getById(userId));
    }

    @Override
    public Page<SysUserWebVo> getUserWebVoPage(Page<SysUser> page) {
        // user列表
        List<SysUser> users = page.getRecords();
        // 待装webVo的列表
        List<SysUserWebVo> webVos = new ArrayList<>();
        for (SysUser user : users) {
            SysUserWebVo userWebVo = getUserWebVoByUser(user);
            webVos.add(userWebVo);
        }
        Page<SysUserWebVo> webVoPage = new Page<>();
        // 将原始的page的元素值封装到vo的page中
        webVoPage.setRecords(webVos);
        webVoPage.setCurrent(page.getCurrent());
        webVoPage.setPages(page.getPages());
        webVoPage.setSize(page.getSize());
        webVoPage.setTotal(page.getTotal());
        return webVoPage;
    }

    @Override
    public List<SysUserWebVo> getAllUsersWebVo() {
        List<SysUser> users = list();
        ArrayList<SysUserWebVo> webVos = new ArrayList<>();
        for (SysUser user : users) {
            SysUserWebVo webVo = getUserWebVoByUser(user);
            webVos.add(webVo);
        }
        return webVos;
    }

    @Override
    public SysUserWebVo getUserWebVoByUser(SysUser user) {
        SysUserWebVo webVo = new SysUserWebVo();
        webVo.setDeptId(user.getDeptId());
        webVo.setPostId(user.getPostId());
        webVo.setName(user.getName());
        webVo.setPhone(user.getPhone());
        webVo.setCreateTime(user.getCreateTime());
        webVo.setUpdateTime(user.getUpdateTime());
        webVo.setRoleList(user.getRoleList());
        webVo.setStatus(user.getStatus());
        webVo.setUsername(user.getUsername());
        webVo.setUserId(user.getId());
        webVo.setAvatarUrl(getAvatarBase64StrByUser(user));
        webVo.setPost(postMapper.selectById(user.getPostId()).getName());
        webVo.setDept(deptMapper.selectById(user.getDeptId()).getName());
        // 封装角色列表
        webVo.setRoleList(roleService.getRolesByUserId(user.getId()));
        return webVo;
    }


}
