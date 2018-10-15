package com.lingtoo.wechat.dao.mip;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.mip.MIPAccount;
import com.lingtoo.wechat.pojo.mip.MIPMessage;

@MyBatisRepository
public interface MIPDAO {
	MIPAccount getManagerByID(@Param("id") String id);
	
	List<MIPMessage> getMIPMessageListByID(@Param("id") String id);
}
