package com.myPro.auth.service.utils;

import com.myPro.model.web.Dept;

import java.util.ArrayList;
import java.util.List;

/***
 *帮dept列表构建成树型部门工具类
 */
public class DeptHelper {
    /**
     * 用递归建层级部门
     * */
    public static List<Dept> buildTree(List<Dept> originList){
        List<Dept> trees = new ArrayList<>();

        // 遍历部门
        for (Dept dept : originList) {
            if(dept.getParentId() == 0){
                trees.add(getChildren(dept, originList));
            }
        }
        return trees;
    }

    public static Dept getChildren(Dept dept, List<Dept> deptList){
        dept.setChildren(new ArrayList<Dept>());
        for (Dept it : deptList) {
            if(dept.getChildren() == null){
                dept.setChildren(new ArrayList<>());
            }
            if(dept.getId().longValue() == it.getParentId().longValue()){
                dept.getChildren().add(getChildren(it,deptList));
            }
        }
        return dept;
    }
}
