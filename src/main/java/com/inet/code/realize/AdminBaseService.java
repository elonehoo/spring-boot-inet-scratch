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
}
