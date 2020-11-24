package com.inet.code.realize;

import com.inet.code.utlis.Result;
/**
* 管理页面的控制层
* @author HCY
* @since 2020/11/22 下午 09:28
*/
public interface AdminBaseService {
    /**
    * 新增标签
    * @author HCY
    * @since 2020/11/22 下午 09:28
    * @param labelName: 类别名称
    * @param path: URL路径
    * @return com.inet.code.utlis.Result
    */
    Result getAppendLabel(String labelName, String path);

    /**
     * 修改标签
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
    /**
     * 通过标签的uuid删除标签(删除操作)
     * @author HCY
     * @since 2020/11/24 9:00 上午
     * @param labelUuid: 标签的uuid
     * @param path: URL路径
     * @return com.inet.code.utlis.Result
    */
    Result removeLabel(String labelUuid, String path);
    /**
     * 新增类别
     * @author HCY
     * @since 2020/11/24 9:52 上午
     * @param typeName: 类别名称
     * @param path: URL路径
     * @return com.inet.code.utlis.Result
    */
    Result getAppendType(String typeName, String path);

    /**
     * 通过类别的uuid查找到类别实体类，进行修改
     * @author HCY
     * @since 2020/11/24 10:51 上午
     * @param typeUuid: 类别序号
     * @param typeName: 类别名字
     * @param path: URL路径
     * @return com.inet.code.utlis.Result
    */
    Result getAmendType(String typeUuid, String typeName, String path);
}
