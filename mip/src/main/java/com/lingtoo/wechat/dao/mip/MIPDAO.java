package com.lingtoo.wechat.dao.mip;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.mip.MIPAccount;
import com.lingtoo.wechat.pojo.mip.MIPMessage;
import com.lingtoo.wechat.pojo.mip.MIPMessageType;

@MyBatisRepository
public interface MIPDAO {
	List<MIPAccount> selectMIPAccountList();

	MIPAccount selectMIPAccountByAccountId(@Param("accountId") Integer accountId);
	
	MIPAccount selectMIPAccountBySpCode(@Param("spCode") String spCode);
	
	List<MIPMessageType> selectMIPMessageTypeList();
	
	List<MIPMessage> selectMIPMessageListByAccountId(@Param("accountId") Integer accountId);
	
	MIPMessage selectMIPMessageByMsgId(@Param("msgId")Integer msgId);
	
	void updateMIPMessage(MIPMessage mipMessage);
	
	void insertMIPMessage(MIPMessage mipMessage);
	
	void deleteMIPMessageByPrimaryKey(Integer mipMessageId);
}
