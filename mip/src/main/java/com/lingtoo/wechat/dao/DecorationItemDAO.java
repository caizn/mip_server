
package com.lingtoo.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingtoo.wechat.persistence.annotation.MyBatisRepository;
import com.lingtoo.wechat.pojo.DecorationItem;

@MyBatisRepository
public interface DecorationItemDAO {
	void insert(DecorationItem di);

	void update(DecorationItem di);

	DecorationItem selectDecorationItemById(@Param("decorationItemId") Integer decorationItemId);

	Integer selectDecorationItemsCount(@Param("type") Integer type);

	List<DecorationItem> selectDecorationItems(@Param("type") Integer type, @Param("pageStart") Integer pageStart,
			@Param("maxResult") Integer maxResult);
}
