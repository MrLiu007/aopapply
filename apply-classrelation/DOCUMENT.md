# aopapply
笔记工程：类关系

通过多对如下类进行相关改造，引入不同类关系（继承，实现，依赖，关联4种），另外2种（组合，聚合）在 `relation`目录。
```java
package com.magic.liuzm.question.one;

import lombok.extern.log4j.Log4j2;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:47
 * @description 普通用户角色
 */
@Log4j2
public class User {
   
    public void delete(Integer id) {
        log.info("删除用户，id="+id);
    }
}
```