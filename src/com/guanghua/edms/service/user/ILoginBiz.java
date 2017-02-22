package com.guanghua.edms.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.common.UserInvalidException;
import com.guanghua.edms.common.web.IAuthenticated;


/**
 * 用户登录接口
 * @author Administrator
 *
 */
public interface ILoginBiz {
	@Transactional(readOnly = true, propagation = Propagation.NEVER)
	public IAuthenticated login(String no, String password) throws UserInvalidException, Exception;
	
	@Transactional(readOnly = true, propagation = Propagation.NEVER)
	public IAuthenticated login(String no) throws UserInvalidException, Exception;
	
	@Transactional(readOnly = true, propagation = Propagation.NEVER)
	public String usernameCheck(String account) throws Exception;
	
	@Transactional(readOnly = true, propagation = Propagation.NEVER)
	public List<Map<String, Object>> JudgeUserOrgType() throws Exception;
}
