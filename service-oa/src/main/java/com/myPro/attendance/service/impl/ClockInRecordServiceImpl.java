package com.myPro.attendance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.Do.ClockInRecordDo;
import com.myPro.attendance.mapper.ClockInRecordMapper;
import com.myPro.attendance.service.ClockInRecordService;
import com.myPro.auth.service.DeptService;
import com.myPro.auth.service.SysUserService;
import com.myPro.common.utils.DateUtil;
import com.myPro.model.app.ClockInRecord;
import com.myPro.vo.attendance.ClockInRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClockInRecordServiceImpl extends ServiceImpl<ClockInRecordMapper, ClockInRecord> implements ClockInRecordService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private DeptService deptService;

    @Override
    public List<ClockInRecord> getListByUserIdDesc(Long userId) {
        LambdaQueryWrapper<ClockInRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClockInRecord::getUserId, userId);
        wrapper.orderByDesc(ClockInRecord::getClockInTime);
        return list(wrapper);
    }

    @Override
    public ClockInRecord getFirstRecord(Long userId) {
        return baseMapper.selectFirstRecord(userId);
    }

    @Override
    public int getCountThisMonth(Long userId) {
        return baseMapper.selectCountThisMonth(userId);
    }

    @Override
    public List<ClockInRecordVo> getRadiusByDays(Integer days) {
        // TODO 拿到一定时间内所有的记录数据（时间降序排）
        List<ClockInRecordDo> records = baseMapper.getRecordsByDays(days);
//        records.forEach(System.out::println);
        // 获取公司总人数
        long userNum = userService.count();
        List<ClockInRecordVo> recordVos = new ArrayList<>();
        Date date = DateUtil.clearTime(new Date());

        int j = 0;
        for (int i = 0; i < days; i++) {
            // 本次待封装数据对象
            ClockInRecordVo myVo = new ClockInRecordVo();
                // 没有缺少天数，直接添加数据
            if(j < records.size() && DateUtil.isCommonDay(records.get(j).getClockInRecordDate(), date)){
                Date recordDate = records.get(j).getClockInRecordDate();
                myVo.setClockInRecordDate(recordDate);
                myVo.setRecordRadius(records.get(j).getRecordNum() / ((double)userNum * 2.0));
                j++;
            } else{
                // 缺天数的时候，填上0%的数据
                myVo.setRecordRadius(0.0);
                // 进行深拷贝再添加
                myVo.setClockInRecordDate(new Date(date.getTime()));
            }
            date.setDate(date.getDate() - 1);
            recordVos.add(myVo);
        }
        // 导入数据进入
        return recordVos;
    }

    @Override
    public List<ClockInRecordVo> getDeptRedius(Long days, List<Long> idList) {
        ArrayList<ClockInRecordVo> recordVos = new ArrayList<>();
        // TODO 获取每个部门的打卡数目和名称封装到vo中
        for (Long id : idList) {
            ClockInRecordDo deptDo = baseMapper.getOneDeptCountByDays(days, id);
            ClockInRecordVo vo = new ClockInRecordVo();
            vo.setDeptName(deptDo.getDeptName());
            // TODO 根据数目获取打卡率
            Long deptNum = deptService.getDeptNum(id);
            vo.setRecordRadius(deptDo.getRecordNum() / ((double) deptNum * 2.0 * days));
            recordVos.add(vo);
        }
        return recordVos;
    }
}
