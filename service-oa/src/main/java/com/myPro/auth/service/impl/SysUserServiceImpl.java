package com.myPro.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.mapper.SysUserMapper;
import com.myPro.auth.service.SysUserService;
import com.myPro.common.utils.FileUtil;
import com.myPro.model.system.SysUser;
import com.myPro.vo.app.SysUserVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

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
        String base64Str = FileUtil.convertImageToBase64Str(FileUtil.classpath + imgPath);
        String suffix = FileUtil.getFileSuffix(imgPath).substring(1);
        if(!suffix.equals("gif")){
            suffix = "png";
        }
        return "data:image/" + suffix + ";base64," + base64Str;
    }

    @Override
    public SysUserVo getUserVoById(Long userId) {
        SysUser user = getById(userId);
        SysUserVo userVo = new SysUserVo();
        userVo.setName(user.getName());
        userVo.setUserId(userId);
        userVo.setAvatarUrl(getAvatarBase64StrByUser(user));

        return userVo;
    }

}
