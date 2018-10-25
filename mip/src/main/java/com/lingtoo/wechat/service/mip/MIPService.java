package com.lingtoo.wechat.service.mip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingtoo.wechat.dao.mip.MIPDAO;
import com.lingtoo.wechat.pojo.mip.MIPAccount;
import com.lingtoo.wechat.pojo.mip.MIPMessage;
import com.lingtoo.wechat.pojo.mip.MIPMessageType;

@Service
public class MIPService {
	@Autowired
	private MIPDAO mipDao;
	
	public List<MIPAccount> getMIPAccountList(){
		return mipDao.selectMIPAccountList();
	}
	
	public MIPAccount getMIPAccountByAccountId(Integer accountId) {
		return mipDao.selectMIPAccountByAccountId(accountId);
	}
	
	public MIPAccount getMIPAccountBySpCode(String spCode) {
		return mipDao.selectMIPAccountBySpCode(spCode);
	}
	
	public List<MIPMessageType> getMIPMsgType(){
		return mipDao.selectMIPMessageTypeList();
	}
	
	public List<MIPMessage> getMIPMsgByAcountId(Integer accountId){
		return mipDao.selectMIPMessageListByAccountId(accountId);
	}
	
	public MIPMessage getMIPMsgByMsgId(Integer msgId) {
		return mipDao.selectMIPMessageByMsgId(msgId);
	}
	
	public void addMIPMsg(MIPMessage mipMsg) {
		mipDao.insertMIPMessage(mipMsg);
	}
	
	public void updateMIPMsg(MIPMessage mipMsg) {
		mipDao.updateMIPMessage(mipMsg);
	}
	
	public void deleteMIPMsg(Integer mipMsgId) {
		mipDao.deleteMIPMessageByPrimaryKey(mipMsgId);
	}
}
