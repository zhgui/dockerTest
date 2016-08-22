/**
 *
 */
package com.shark.common.plugin.entity;

/**
 * <p>实体实现该接口表示想要调整数据的顺序
 * <p>优先级值越大则展示时顺序越靠前 比如 2 排在 1 前边
 * <p/>
 * <p>Date: 13-1-12 下午4:09
 * <p>Version: 1.0
 */
public interface Movable {

    public Integer getWeight();

    public void setWeight(Integer weight);

}
