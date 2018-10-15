package com.lingtoo.wechat.dao;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by shenzh on 2016/8/19.
 */
@MyBatisRepository
public interface LogDAO {

    int insert(Log record);

    // 分页查询
    int findLogCount(@Param("name")String name,@Param("operateId")Integer operateId,@Param("beginDate")String beginDate,@Param("endDate")String endDate);
    List<Log> findLogList(@Param("name")String name,@Param("operateId")Integer operateId,@Param("beginDate")String beginDate,@Param("endDate")String endDate,@Param("pageStart") int pageStart, @Param("maxResult") int maxResult);
 // 分页查询
    int findLogCountByMerchant(@Param("name")String name,@Param("merchantId")Integer merchantId,@Param("beginDate")String beginDate,@Param("endDate")String endDate);
    List<Log> findLogListByMerchant(@Param("name")String name,@Param("merchantId")Integer merchantId,@Param("beginDate")String beginDate,@Param("endDate")String endDate,@Param("pageStart") int pageStart, @Param("maxResult") int maxResult);

    Log selectByPrimaryKey(Integer logId);

    int deleteByPrimaryKey(Integer logId);
}
