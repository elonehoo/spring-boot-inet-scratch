package com.inet.code.realize;

import com.inet.code.utlis.Result;
/**
* 管理页面的控制层
* @author HCY
* @since 2020/11/22 下午 09:28
*/
public interface AdminBaseService {
    /**
    * 新增类别
    * @author HCY
    * @since 2020/11/22 下午 09:28
    * @param labelName: 类别名称
    * @param path: URL路径
    * @return com.inet.code.utlis.Result
    */
    Result getAppendLabel(String labelName, String path);

    /**
     * 修改类别
     * @author HCY
     * @since 2020/11/23 7:58 上午
     * @param labelUuid: 标签的序号
     * @param labelName: 标签的名字
     * @param path: URL路径
     * @return com.inet.code.utlis.Result
    */
    Result getAmendLabel(String labelUuid, String labelName, String path);
    /**
     * 通过标签的uuid删除标签(前提操作)
     * @author HCY
     * @since 2020/11/23 11:16 下午
     * @param labelUuid: 标签的uuid
     * @param path: URL路径
     * @return com.inet.code.utlis.Result
    */
    Result removePremiseLabel(String labelUuid, String path);
}
