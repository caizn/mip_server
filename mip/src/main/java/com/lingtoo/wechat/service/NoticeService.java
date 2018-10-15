package com.lingtoo.wechat.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.lingtoo.wechat.components.WechatClient;
import com.lingtoo.wechat.dao.DecorationWorkerDAO;
import com.lingtoo.wechat.dao.UserDAO;
import com.lingtoo.wechat.message.TemplateBuilder;
import com.lingtoo.wechat.message.TemplateData;
import com.lingtoo.wechat.message.WechatTemplate;
import com.lingtoo.wechat.pojo.DecorationItem;
import com.lingtoo.wechat.pojo.DecorationOrder;
import com.lingtoo.wechat.pojo.DecorationWorker;
import com.lingtoo.wechat.pojo.WechatUser;

/**
 * Created by shenzh on 2018/1/9.
 */
@Service
public class NoticeService {

    @Value("${t1t.wechat.template.noticeUserNewOrder}")
    private String noticeUserNewOrderTemplateId;

    @Value("${t1t.wechat.template.noticeAdminNewOrder}")
    private String noticeAdminNewOrderTemplateId;

    @Value("${t1t.wechat.template.noticeWorkerNewOrder}")
    private String noticeWorkerNewOrderTemplateId;

    @Value("${t1t.wechat.template.noticeWorkerGetOrder}")
    private String noticeWorkerGetOrderTemplateId;

    @Value("${t1t.wechat.template.noticeAdminTheWorkerGetOrder}")
    private String noticeAdminTheWorkerGetOrderTemplateId;

    @Value("${t1t.wechat.template.noticeWorkerExitOrder}")
    private String noticeWorkerExitOrderTemplateId;

    @Value("${t1t.wechat.template.noticeWorkerAddPrice}")
    private String noticeWorkerAddPriceTemplateId;

    @Value("${t1t.wechat.template.noticeOrderFinish}")
    private String noticeOrderFinishTemplateId;

    @Value("${t1t.wechat.template.userApplyChangeWorker}")
    private String noticeUserApplyChangeWorkerTemplateId;

    @Value("${t1t.wechat.template.workerApplyPass}")
    private String noticeWorkerApplyPassTemplateId;

    private static final String COLOR_GREEN = "#27bf8c";
    private static final String COLOR_RED = "#f35778";
    private static final String COLOR_BLACK = "#000000";

    private static final String FIRST = "first";
    private static final String KEYWORD_1 = "keyword1";
    private static final String KEYWORD_2 = "keyword2";
    private static final String KEYWORD_3 = "keyword3";
    private static final String KEYWORD_4 = "keyword4";
    private static final String KEYWORD_5 = "keyword5";
    private static final String REMARK = "remark";

    public static final String NOTICE_TEAM_MEMBER_TITLE = "{0} ({1})邀请你加入比赛";
    public static final String NOTICE_TEAM_CAPTAIN_TITLE = "{0} ({1})发布了一场约战，等你来应战";

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

    @Autowired
    private DecorationItemService decorationItemService;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private DecorationWorkerDAO dWorkerDao;

    //派单通知 通知用户订单创建成功
    public void noticeUserNewOrder(String openId, String noticeUrl, DecorationOrder order) {
        DecorationItem item = decorationItemService.selectDItemById(order.getDecorationItemId());
        WechatClient.sendTemplateMessage(new TemplateBuilder<WechatTemplate>(openId, noticeUrl+"&openid="+openId) {
            @Override
            public Map<String, TemplateData> buildData() {
                HashMap<String, TemplateData> dataMap = Maps.newHashMap();
                dataMap.put(FIRST, new TemplateData(COLOR_BLACK, "新订单创建成功，订单号："+order.getOrderNo() + "\n"));
                dataMap.put(KEYWORD_1, new TemplateData(COLOR_GREEN, order.getReceiveName()+" "+order.getReceiveMobile()));
                if(order.getBookTime()!=null)
                    dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, SIMPLE_DATE_FORMAT.format(order.getBookTime())));
                else
                    dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, "紧急订单，即刻上门"));
                dataMap.put(KEYWORD_3, new TemplateData(COLOR_GREEN, order.getReceiveArea() + order.getReceiveAddress()));
                dataMap.put(KEYWORD_4, new TemplateData(COLOR_GREEN, item.getTypeShow()+"/"+item.getSubTitle()+"/"+item.getTitle()));
                dataMap.put(KEYWORD_5, new TemplateData(COLOR_GREEN, order.getRemark()));
                dataMap.put(REMARK, new TemplateData(COLOR_BLACK, "系统会尽快派单，可查看个人中心的订单状态变化"));
                return dataMap;
            }
        }.build(new WechatTemplate(noticeUserNewOrderTemplateId)));
    }

    //派单通知 通知管理员订单创建成功
    public void noticeAdminNewOrder(String noticeUrl, DecorationOrder order) {
        DecorationItem item = decorationItemService.selectDItemById(order.getDecorationItemId());
        List<WechatUser> adminList = userDAO.selectManagerUsers();
        for (WechatUser admin:adminList) {
            WechatClient.sendTemplateMessage(new TemplateBuilder<WechatTemplate>(admin.getOpenid(), noticeUrl+"&openid="+admin.getOpenid()) {
                @Override
                public Map<String, TemplateData> buildData() {
                    HashMap<String, TemplateData> dataMap = Maps.newHashMap();
                    dataMap.put(FIRST, new TemplateData(COLOR_BLACK, "新订单审核提醒，订单号："+order.getOrderNo() + "\n"));
                    dataMap.put(KEYWORD_1, new TemplateData(COLOR_GREEN, order.getReceiveName()+" "+order.getReceiveMobile()));
                    if(order.getBookTime()!=null)
                    	dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, SIMPLE_DATE_FORMAT.format(order.getBookTime())));
                    else
                    	dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, "紧急订单，即刻上门"));
                    dataMap.put(KEYWORD_3, new TemplateData(COLOR_GREEN, order.getReceiveArea() + order.getReceiveAddress()));
                    dataMap.put(KEYWORD_4, new TemplateData(COLOR_GREEN, item.getTypeShow()+"/"+item.getSubTitle()+"/"+item.getTitle()));
                    dataMap.put(KEYWORD_5, new TemplateData(COLOR_GREEN, order.getRemark()));
                    dataMap.put(REMARK, new TemplateData(COLOR_BLACK, "请尽快派单"));
                    return dataMap;
                }
            }.build(new WechatTemplate(noticeUserNewOrderTemplateId)));
        }
    }

    //派单通知    管理员审核完发到平台，师傅可抢单
    public void noticeWorkerNewOrder(String openId, String noticeUrl, DecorationOrder order) {
        DecorationItem item = decorationItemService.selectDItemById(order.getDecorationItemId());
        WechatClient.sendTemplateMessage(new TemplateBuilder<WechatTemplate>(openId, noticeUrl+"&openid="+openId) {
            @Override
            public Map<String, TemplateData> buildData() {
                HashMap<String, TemplateData> dataMap = Maps.newHashMap();
                dataMap.put(FIRST, new TemplateData(COLOR_BLACK, "新订单提醒，订单号："+order.getOrderNo() + "\n"));
                dataMap.put(KEYWORD_1, new TemplateData(COLOR_GREEN, order.getReceiveName()+" "+order.getReceiveMobile()));
                if(order.getBookTime()!=null)
                    dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, SIMPLE_DATE_FORMAT.format(order.getBookTime())));
                else
                    dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, "紧急订单，即刻上门"));
                dataMap.put(KEYWORD_3, new TemplateData(COLOR_GREEN, order.getReceiveArea() + order.getReceiveAddress()));
                dataMap.put(KEYWORD_4, new TemplateData(COLOR_GREEN, item.getTypeShow()+"/"+item.getSubTitle()+"/"+item.getTitle()));
                dataMap.put(KEYWORD_5, new TemplateData(COLOR_GREEN, order.getRemark()));
                dataMap.put(REMARK, new TemplateData(COLOR_BLACK, "请尽快抢单，手快有手慢无"));
                return dataMap;
            }
        }.build(new WechatTemplate(noticeUserNewOrderTemplateId)));
    }

    //接单成功通知  师傅接单后收到
    public void noticeWorkerGetOrder(String openId, String noticeUrl, DecorationOrder order) {
        DecorationItem item = decorationItemService.selectDItemById(order.getDecorationItemId());
        WechatClient.sendTemplateMessage(new TemplateBuilder<WechatTemplate>(openId, noticeUrl+"&openid="+openId) {
            @Override
            public Map<String, TemplateData> buildData() {
                HashMap<String, TemplateData> dataMap = Maps.newHashMap();
                dataMap.put(FIRST, new TemplateData(COLOR_BLACK, "您已经成功接单，上门前请提前1小时联系客户。"));
                dataMap.put(KEYWORD_1, new TemplateData(COLOR_GREEN, order.getReceiveName()+" "+order.getReceiveMobile()));
                if(order.getBookTime()!=null)
                    dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, SIMPLE_DATE_FORMAT.format(order.getBookTime())));
                else
                    dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, "紧急订单，即刻上门"));
                dataMap.put(KEYWORD_3, new TemplateData(COLOR_GREEN, order.getReceiveArea() + order.getReceiveAddress()));
                dataMap.put(KEYWORD_4, new TemplateData(COLOR_GREEN, item.getTypeShow()+"/"+item.getSubTitle()+"/"+item.getTitle()));
                dataMap.put(KEYWORD_5, new TemplateData(COLOR_GREEN, order.getRemark()));
                dataMap.put(REMARK, new TemplateData(COLOR_BLACK, "点击查看完整订单信息"));
                return dataMap;
            }
        }.build(new WechatTemplate(noticeWorkerGetOrderTemplateId)));
    }

    //派单通知      师傅接单后管理员收到通知
    public void noticeAdminTheWorkerGetOrder(String noticeUrl, DecorationOrder order) {
        DecorationItem item = decorationItemService.selectDItemById(order.getDecorationItemId());
        DecorationWorker worker=dWorkerDao.selectDWorkerById(order.getDecorationWorkerId());
        List<WechatUser> adminList = userDAO.selectManagerUsers();
        for (WechatUser admin:adminList) {
            WechatClient.sendTemplateMessage(new TemplateBuilder<WechatTemplate>(admin.getOpenid(), noticeUrl+"&openid="+admin.getOpenid()) {
                @Override
                public Map<String, TemplateData> buildData() {
                    HashMap<String, TemplateData> dataMap = Maps.newHashMap();
                    dataMap.put(FIRST, new TemplateData(COLOR_BLACK, worker.getName()+"已接单，订单号："+order.getOrderNo() + "\n"));
                    dataMap.put(KEYWORD_1, new TemplateData(COLOR_GREEN, order.getReceiveName()+" "+order.getReceiveMobile()));
                    if(order.getBookTime()!=null)
                        dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, SIMPLE_DATE_FORMAT.format(order.getBookTime())));
                    else
                        dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, "紧急订单，即刻上门"));
                    dataMap.put(KEYWORD_3, new TemplateData(COLOR_GREEN, order.getReceiveArea() + order.getReceiveAddress()));
                    dataMap.put(KEYWORD_4, new TemplateData(COLOR_GREEN, item.getTypeShow()+"/"+item.getSubTitle()+"/"+item.getTitle()));
                    dataMap.put(KEYWORD_5, new TemplateData(COLOR_GREEN, order.getRemark()));
                    dataMap.put(REMARK, new TemplateData(COLOR_BLACK, "点击查看完整订单信息"));
                    return dataMap;
                }
            }.build(new WechatTemplate(noticeUserNewOrderTemplateId)));
        }
    }

    /*
    退单通知      师傅退单 发退单通知给管理员
    * */
    public void noticeWorkerExitOrder(String openId, String noticeUrl, DecorationOrder order,String oldWorkerName) {
        WechatClient.sendTemplateMessage(new TemplateBuilder<WechatTemplate>(openId, noticeUrl+"&openid="+openId) {
            @Override
            public Map<String, TemplateData> buildData() {
                HashMap<String, TemplateData> dataMap = Maps.newHashMap();
                dataMap.put(FIRST, new TemplateData(COLOR_BLACK, oldWorkerName+"已退单，订单号："+order.getOrderNo() + "，系统已重新自动派单"));
                dataMap.put(KEYWORD_1, new TemplateData(COLOR_GREEN, ""));
                dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, SIMPLE_DATE_FORMAT.format(new Date())));
                dataMap.put(REMARK, new TemplateData(COLOR_BLACK, "点击查看完整订单信息"));
                return dataMap;
            }
        }.build(new WechatTemplate(noticeWorkerExitOrderTemplateId)));
    }

    //师傅另行加价   师傅另行加价，修改订单金额，同时推送给管理员
    public void noticeWorkerAddPrice(String noticeUrl, DecorationOrder order) {
        DecorationItem item = decorationItemService.selectDItemById(order.getDecorationItemId());
        List<WechatUser> adminList = userDAO.selectManagerUsers();
        for (WechatUser admin:adminList) {
            WechatClient.sendTemplateMessage(new TemplateBuilder<WechatTemplate>(admin.getOpenid(), noticeUrl+"&openid="+admin.getOpenid()) {
                @Override
                public Map<String, TemplateData> buildData() {
                    HashMap<String, TemplateData> dataMap = Maps.newHashMap();
                    dataMap.put(FIRST, new TemplateData(COLOR_BLACK, "师傅已经完成订单"));
                    dataMap.put(KEYWORD_1, new TemplateData(COLOR_GREEN, order.getOrderNo()));
                    dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, item.getTypeShow()+"/"+item.getSubTitle()+"/"+item.getTitle()));
                    dataMap.put(KEYWORD_3, new TemplateData(COLOR_GREEN, order.getReceiveName()+" "+order.getReceiveMobile()+"\n"+order.getReceiveArea() + order.getReceiveAddress()));
                    dataMap.put(REMARK, new TemplateData(COLOR_BLACK, "点击查看完整订单信息"));
                    return dataMap;
                }
            }.build(new WechatTemplate(noticeWorkerAddPriceTemplateId)));
        }
    }

    //订单确认通知     师傅点击订单完成，发通知到客户，客户支付金额，完成订单
    public void noticeUserOrderFinish(String openId, String noticeUrl, DecorationOrder order) {
        DecorationItem item = decorationItemService.selectDItemById(order.getDecorationItemId());
        WechatClient.sendTemplateMessage(new TemplateBuilder<WechatTemplate>(openId, noticeUrl+"&openid="+openId) {
            @Override
            public Map<String, TemplateData> buildData() {
                HashMap<String, TemplateData> dataMap = Maps.newHashMap();
                dataMap.put(FIRST, new TemplateData(COLOR_BLACK, "您好，订单已完成，请支付工单费用"));
                dataMap.put(KEYWORD_1, new TemplateData(COLOR_GREEN, order.getOrderNo()));
                dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, item.getTypeShow()+"/"+item.getSubTitle()+"/"+item.getTitle()));
                dataMap.put(KEYWORD_3, new TemplateData(COLOR_GREEN, order.getAllPrice()+""));
                dataMap.put(KEYWORD_4, new TemplateData(COLOR_GREEN, SIMPLE_DATE_FORMAT.format(new Date())));
                dataMap.put(REMARK, new TemplateData(COLOR_BLACK, "点击查看完整订单信息"));
                return dataMap;
            }
        }.build(new WechatTemplate(noticeOrderFinishTemplateId)));
    }

    //订单完成通知     客户支付订单，发送订单完成通知给管理员
    public void noticeAdminOrderFinish( String noticeUrl, DecorationOrder order) {
        DecorationItem item = decorationItemService.selectDItemById(order.getDecorationItemId());
        List<WechatUser> adminList = userDAO.selectManagerUsers();
        for (WechatUser admin:adminList) {
            WechatClient.sendTemplateMessage(new TemplateBuilder<WechatTemplate>(admin.getOpenid(), noticeUrl+"&openid="+admin.getOpenid()) {
                @Override
                public Map<String, TemplateData> buildData() {
                    HashMap<String, TemplateData> dataMap = Maps.newHashMap();
                    dataMap.put(FIRST, new TemplateData(COLOR_BLACK, "订单已完成"));
                    dataMap.put(KEYWORD_1, new TemplateData(COLOR_GREEN, order.getOrderNo()));
                    dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, item.getTypeShow()+"/"+item.getSubTitle()+"/"+item.getTitle()));
                    dataMap.put(KEYWORD_3, new TemplateData(COLOR_GREEN, order.getAllPrice()+""));
                    dataMap.put(KEYWORD_4, new TemplateData(COLOR_GREEN, SIMPLE_DATE_FORMAT.format(new Date())));
                    dataMap.put(REMARK, new TemplateData(COLOR_BLACK, "点击查看完整订单信息"));
                    return dataMap;
                }
            }.build(new WechatTemplate(noticeOrderFinishTemplateId)));
        }
    }

    //订单完成通知     客户支付订单，发送订单完成通知给师傅
    public void noticeWorkerOrderFinish(String openId,String noticeUrl, DecorationOrder order) {
        DecorationItem item = decorationItemService.selectDItemById(order.getDecorationItemId());
        WechatClient.sendTemplateMessage(new TemplateBuilder<WechatTemplate>(openId, noticeUrl+"&openid="+openId) {
            @Override
            public Map<String, TemplateData> buildData() {
                HashMap<String, TemplateData> dataMap = Maps.newHashMap();
                dataMap.put(FIRST, new TemplateData(COLOR_BLACK, "您好，订单已完成"));
                dataMap.put(KEYWORD_1, new TemplateData(COLOR_GREEN, order.getOrderNo()));
                dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, item.getTypeShow()+"/"+item.getSubTitle()+"/"+item.getTitle()));
                dataMap.put(KEYWORD_3, new TemplateData(COLOR_GREEN, (order.getDispatchPrice() + order.getAddPrice())+""));
                dataMap.put(KEYWORD_4, new TemplateData(COLOR_GREEN, SIMPLE_DATE_FORMAT.format(new Date())));
                dataMap.put(REMARK, new TemplateData(COLOR_BLACK, "点击查看完整订单信息"));
                return dataMap;
            }
        }.build(new WechatTemplate(noticeOrderFinishTemplateId)));
    }

    //用户申请更换师傅，推送给管理员
    public void noticeUserApplyChangeWorker(String noticeUrl, DecorationOrder order, String reason) {
        DecorationItem item = decorationItemService.selectDItemById(order.getDecorationItemId());
        List<WechatUser> adminList = userDAO.selectManagerUsers();
        for (WechatUser admin:adminList) {
            WechatClient.sendTemplateMessage(new TemplateBuilder<WechatTemplate>(admin.getOpenid(), noticeUrl+"&openid="+admin.getOpenid()) {
                @Override
                public Map<String, TemplateData> buildData() {
                    HashMap<String, TemplateData> dataMap = Maps.newHashMap();
                    dataMap.put(FIRST, new TemplateData(COLOR_BLACK, "用户申请更换师傅，"+reason));
                    dataMap.put(KEYWORD_1, new TemplateData(COLOR_GREEN, order.getOrderNo()));
                    dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, item.getTypeShow()+"/"+item.getSubTitle()+"/"+item.getTitle()));
                    dataMap.put(KEYWORD_3, new TemplateData(COLOR_GREEN, order.getReceiveName()+" "+order.getReceiveMobile()+"\n"+order.getReceiveArea() + order.getReceiveAddress()));
                    dataMap.put(REMARK, new TemplateData(COLOR_BLACK, "点击查看完整订单信息"));
                    return dataMap;
                }
            }.build(new WechatTemplate(noticeUserApplyChangeWorkerTemplateId)));
        }
    }

    /*
    申请通过提醒      师傅入驻
    * */
    public void noticeWorkerApplyPass(String openId, String noticeUrl,String workerName) {
        WechatClient.sendTemplateMessage(new TemplateBuilder<WechatTemplate>(openId, noticeUrl) {
            @Override
            public Map<String, TemplateData> buildData() {
                HashMap<String, TemplateData> dataMap = Maps.newHashMap();
                dataMap.put(FIRST, new TemplateData(COLOR_BLACK, "你好，你的入驻申请已通过"));
                dataMap.put(KEYWORD_1, new TemplateData(COLOR_GREEN, workerName));
                dataMap.put(KEYWORD_2, new TemplateData(COLOR_GREEN, "已通过"));
                dataMap.put(REMARK, new TemplateData(COLOR_BLACK, "谢谢关注本公众号，欢迎随时抢单。"));
                return dataMap;
            }
        }.build(new WechatTemplate(noticeWorkerApplyPassTemplateId)));
    }
}
