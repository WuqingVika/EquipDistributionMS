package com.guanghua.edms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * 2017/2/25 15:17
 * @author wuqingvika
 *
 */
public interface CabinetDao {
	/**
	 * 显示局站
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Map<String, String>> selJuZhan();
}
