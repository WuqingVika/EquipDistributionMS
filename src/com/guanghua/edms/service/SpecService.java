package com.guanghua.edms.service;
import java.util.List;
import com.guanghua.edms.domain.Spec;

public interface SpecService {
	/**
	 * 1.添加专业
	 * @param spec
	 * @return
	 */
	public int addSpec(Spec spec);
	/**
	 * 2.修改专业
	 * @param spec
	 * @return
	 */
	public int editSpec(Spec spec);
	/**
	 * 3.批量移除专业
	 * @param specs
	 * @return
	 */
	public int removeSpecs(List<Spec> specs);
}
