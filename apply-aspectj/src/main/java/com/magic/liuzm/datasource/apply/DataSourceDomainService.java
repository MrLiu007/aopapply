package com.magic.liuzm.datasource.apply;

import com.magic.liuzm.cache.apply.example.UserOutputDTO;
import com.magic.liuzm.datasource.annotation.DynamicDataSource;
import com.magic.liuzm.datasource.enums.DataSourceEnum;
import com.magic.liuzm.datasource.enums.WriteReadEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author zemin.liu
 * @date 2020/5/28 13:54
 * @description 用户领域服务
 */
@Repository
@Slf4j
public class DataSourceDomainService {

    /**
     * 添加信息
     */
    @DynamicDataSource(dataSource = DataSourceEnum.DRUID,writeRead = WriteReadEnum.MASTER)
    public boolean addUser(String userName) {
        // 业务逻辑
        boolean result = true;

        return result;
    }

    /**
     * 查询信息
     */
    @DynamicDataSource(dataSource = DataSourceEnum.DRUID,writeRead = WriteReadEnum.SLAVE1)
    public UserOutputDTO queryUser(String userId) {
        // 业务逻辑
        UserOutputDTO result = null;

        return result;
    }

    /**
     * 删除信息
     */
    @DynamicDataSource(dataSource = DataSourceEnum.DRUID,writeRead = WriteReadEnum.MASTER)
    public boolean removeUser(String userId) {
        // 业务逻辑
        boolean result = true;

        return result;
    }


}