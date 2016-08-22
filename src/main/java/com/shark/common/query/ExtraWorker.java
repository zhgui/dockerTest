package com.shark.common.query;

import java.io.Serializable;
import java.util.Collection;

/**
 * User: Tony
 * Date: 11-9-3
 * Time: 下午4:54
 */
public interface ExtraWorker extends Serializable {

    /**
     * 补充第一次搜索的结果
     *
     * @param collection 组合产生的SQL所搜索到的记录，一般是指定的DTO的Collection集合。

     * @return 进一步补充后的结果。

     */
    public Collection process(Collection collection);

}
