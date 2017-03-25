package com.guanghua.edms.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.domain.Spec;
public interface SpecDao {
	
	/**
	 * 1.添加专业
	 * @param spec
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int addSpec(Spec spec);
	/**
	 * 2.修改专业
	 * @param spec
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int editSpec(Spec spec);
	/**
	 * 3.批量移除专业
	 * @param specs
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int removeSpecs(List<Spec> specs);
}
