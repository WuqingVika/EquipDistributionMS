package com.guanghua.edms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guanghua.edms.domain.Room;
import com.guanghua.edms.domain.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository
public class UserDaoImpl  implements UserDao{
	@Autowired
	private SessionFactory sessionFactory;
	public UserInfo getUserByUserName(String userName) {
		System.out.println("dao--userId------------"+userName);
		List<Object[]> list=sessionFactory.openSession().createSQLQuery("select USER_ID,USER_NAME,PASSWORD,work_no,sp,email,u_state from user_info where USER_NAME='"+userName+"'").list();
		System.out.println(list.size()+"------------");
		UserInfo u=new UserInfo();
		List<UserInfo> users=new ArrayList<UserInfo>();
		for(int i=0;i<list.size();i++){
			u.setUserId(Long.parseLong(list.get(i)[0].toString()));
			u.setUserName(list.get(i)[1].toString());
			u.setPassword(list.get(i)[2].toString());
			u.setWorkNo(list.get(i)[3].toString());
			u.setSp(Integer.parseInt(list.get(i)[4].toString()));
			u.setEmail(list.get(i)[5].toString());
			u.setuState(Integer.parseInt(list.get(i)[6].toString()));
			users.add(u);
		}
		if(list.size()!=0){
			return users.get(0);
		}
		
		return null;
		//return (UserInfo) sessionFactory.getCurrentSession().get(UserInfo.class, userName);
	}
	@Override
	public JSONObject listUsers(int pageSize, int rows, String username) {
		StringBuffer sql=new StringBuffer();
		StringBuffer countSql=new StringBuffer();
		sql.append(" select user_id,user_name,work_no,password,sp,email from user_info ");
		countSql.append(" select count(*) from (select user_id,user_name,work_no,password,sp,email from user_info");
		
		if(!"".equals(username)){
			sql.append(" where user_name like  '%"+username+"%' ");
			countSql.append(" where user_name like '%"+username+"%' ");
		}
		countSql.append(" ) aa");
		
		List<Object[]> list=sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setFirstResult((pageSize - 1) * rows).setMaxResults(rows).list();
		JSONObject result = new JSONObject();
		if(pageSize==1){
			System.out.println();
			String str=sessionFactory.getCurrentSession().createSQLQuery(countSql.toString()).uniqueResult().toString();
			int count=Integer.parseInt(str);
			result.put("total", count);
		}
		JSONArray jsonArray=new JSONArray();
		for (Object[] objects  : list) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("userId", objects[0]);
			jsonObject.put("userName", objects[1]);
			jsonObject.put("workNo", objects[2]);
			jsonObject.put("sp", objects[4]);
			jsonObject.put("email", objects[5]);
			jsonArray.add(jsonObject);
		}
		result.put("rows", jsonArray);
		result.put("vipSql", sql.toString());
		return result;
	}
	@Override
	public int addUser(UserInfo userInfo) {
		Long userId=Long.parseLong(sessionFactory.getCurrentSession().createSQLQuery("select max(user_id)+1 from user_info").uniqueResult().toString());
		userInfo.setUserId(userId);
		//判断username是否重复
		List<Object[]> list=sessionFactory.getCurrentSession().createSQLQuery(" select * from  user_info where user_name='"+ userInfo.getUserName()+"'").list();
		if(list.size()==1){
			//已经有了
			System.out.println("已经有了该用户");
			return 4;
		}else{
			//插入数据库
			int res = sessionFactory.getCurrentSession().createSQLQuery("insert into user_info (user_id,user_name,work_no,password,sp,u_state,email) values ("+userInfo.getUserId()+",'"+userInfo.getUserName()+"','"+userInfo.getWorkNo()
						+"','"+userInfo.getPassword()+"',"+userInfo.getSp()+",0,'"+userInfo.getEmail()+"')").executeUpdate();
			return res;
		}
	}
	
	@Override
	public int editMyUser(UserInfo userInfo) {
		List<Object[]> list=sessionFactory.getCurrentSession().createSQLQuery(" select * from  user_info where user_name='"+ userInfo.getUserName()+"'").list();
		if(list.size()==1){
			int res=sessionFactory.getCurrentSession().createSQLQuery("update user_info set work_no='"+userInfo.getWorkNo()+"',sp="+userInfo.getSp()+",email='"
					+ userInfo.getEmail()+"' where user_id="+userInfo.getUserId()).executeUpdate();
			return res;
		}else{
		//已经有了
		System.out.println("已经有了该用户");
		return 4;
		}
	}
	@Override
	public int resetPwd(String userName) {
		int res=sessionFactory.getCurrentSession().createSQLQuery("update user_info set password='123456' where user_name='"+userName+"'").executeUpdate();
		return res;
	}
	@Override
	public int updatePwd(UserInfo userInfo) {
		//修改密码成功后 需要将code置为空，并且U_state变为0
		int res=sessionFactory.getCurrentSession().createSQLQuery("update user_info set password='"+userInfo.getPassword()+"' where user_name='"+userInfo.getUserName()+"'").executeUpdate();
		return res;
	}
	@Override
	public int delUsers(List<UserInfo> userInfos,UserInfo bs) {
		Session currentSession = sessionFactory.getCurrentSession();
		int res=0;
		for(int i=0;i<userInfos.size();i++){
			Long bsId = bs.getUserId();
			Long tUserId=userInfos.get(i).getUserId();
			if(tUserId!=bsId){//删除管理员不是本身
				//可以删
				System.out.println("-------------这个管理员可以删！");
			  try {//saveOrUpdate
				 currentSession.createSQLQuery("delete from user_info where user_id="+userInfos.get(i).getUserId()).executeUpdate();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return res;
			}
				
			}else{
				System.out.println("不能删!");
				res=2;//代表不能删的
				continue;//跳过
			}
		}
		if(res==2){
			//说明有不能删的
			return res;
		}
		res=1;
		return res;
	}
	@Override
	public int updateState(UserInfo userInfo) {
		int res=0;
		if(userInfo.getCode()!=null){
			 res=sessionFactory.getCurrentSession().createSQLQuery("update user_info set u_state="+userInfo.getuState()+",code= '"+userInfo.getCode()+"' where user_name='"+userInfo.getUserName()+"'").executeUpdate();
		}else{
			 res=sessionFactory.getCurrentSession().createSQLQuery("update user_info set u_state="+userInfo.getuState()+",code= null  where user_name='"+userInfo.getUserName()+"'").executeUpdate();
		}
		return res;
	}
	@Override
	public UserInfo getUserByUserName(String userName, String code) {
		System.out.println("dao--userId------------"+userName);
		List<Object[]> list=sessionFactory.openSession().createSQLQuery("select USER_ID,USER_NAME,PASSWORD,work_no,sp,email,u_state from user_info where USER_NAME='"+userName+"' and code='"+code+"'").list();
		System.out.println(list.size()+"------------");
		UserInfo u=new UserInfo();
		List<UserInfo> users=new ArrayList<UserInfo>();
		for(int i=0;i<list.size();i++){
			u.setUserId(Long.parseLong(list.get(i)[0].toString()));
			u.setUserName(list.get(i)[1].toString());
			u.setPassword(list.get(i)[2].toString());
			u.setWorkNo(list.get(i)[3].toString());
			u.setSp(Integer.parseInt(list.get(i)[4].toString()));
			u.setEmail(list.get(i)[5].toString());
			u.setuState(Integer.parseInt(list.get(i)[6].toString()));
			users.add(u);
		}
		if(list.size()!=0){
			return users.get(0);
		}
		
		return null;
	}
	
}
