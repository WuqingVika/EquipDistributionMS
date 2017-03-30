package com.guanghua.edms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.brick.db.SQLUtil;
import com.guanghua.edms.domain.AddCabinet;
import com.guanghua.edms.domain.JfzsBoardCardManage;
import com.guanghua.edms.domain.JfzsEquipment;
import com.guanghua.edms.domain.JfzsSubRack;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository
public class CabinetDaoImpl implements CabinetDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Map<String, String>> selJuZhan() {
		//查询局站
		Connection conn = this.sessionFactory.getCurrentSession().connection();
		StringBuffer sql = new StringBuffer();
		sql.append("select region_id,region_name from  region where district=20");
		try {
			List<Map<String, String>> list = SQLUtil
					.query(conn, sql.toString());
			System.out.println("list size ----dao---"+list.size());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Map<String, String>> selJiFangByJuZhanId(int regionId) {
		//根据局站查询机房
		Connection conn = this.sessionFactory.getCurrentSession().connection();
		StringBuffer sql = new StringBuffer();
		sql.append("select room_id,room_no from room where district=20 and region_id=").append(regionId);
		try {
			List<Map<String, String>> list = SQLUtil
					.query(conn, sql.toString());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Map<String, String>> selZhuanYes() {
		//显示机柜专业
		Connection conn = this.sessionFactory.getCurrentSession().connection();
		StringBuffer sql = new StringBuffer();
		sql.append("select spec_id,spec_name from  jfzs_spec_manage");
		try {
			List<Map<String, String>> list = SQLUtil
					.query(conn, sql.toString());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public JSONObject selJiGuiByQuery(int pageSize, int rows, String juZhan, String jiFang, String zhuanYe,
			String bianOrMc) {
		System.out.println("查询机柜-信息dao");
		System.out.println("psize:"+pageSize+"  rows:"+rows+"  juzhan:"+juZhan+" jiFang:"+jiFang+" zhuany:"+zhuanYe+" bOm:"+bianOrMc);
		StringBuffer sql=new StringBuffer();
		StringBuffer countSql=new StringBuffer();
		sql.append(" select  a.cabinet_id,b.room_no,a.CABINET_NUM,a.CABINET_NAME,a.CABINET_SURFACE from jfzs_cabinet_manage a,room b where a.room_id=b.room_Id and b.district=20");
		countSql.append(" select count(*) from (select  a.cabinet_id,b.room_no,a.CABINET_NUM,a.CABINET_NAME,a.CABINET_SURFACE  from jfzs_cabinet_manage a,room b where a.room_id=b.room_Id and b.district=20 ");
		if(!"".equals(juZhan)){
			int regionId=Integer.parseInt(juZhan.trim());
			//
			//sql.append(" and remark like '%"+juZhan+"%'");
			sql.append(" and a.room_id in (select room_id from room where district=20 and region_id ="+regionId+")");
			countSql.append(" and a.room_id in (select room_id from room where district=20 and region_id ="+regionId+")");
		}
		if(!"".equals(jiFang)){
			int roomId=Integer.parseInt(jiFang.trim());
			sql.append(" and a.room_id="+roomId );
			//countSql.append(" and attachment like '%"+jiFang+"%'");
			countSql.append(" and a.room_id="+roomId);
		}
		if(!"".equals(zhuanYe)){
			int specId=Integer.parseInt(zhuanYe.trim());
			sql.append(" and a.spec_id="+specId);
			countSql.append(" and a.spec_id="+specId);
		}
		if(!"".equals(bianOrMc)){
			//and (a.CABINET_NUM like '%F0%' or a.CABINET_NAME like '%cc%')
			sql.append(" and (a.CABINET_NUM like '%"+bianOrMc+"%' or a.CABINET_NAME like '%"+bianOrMc+"%')");
			countSql.append("  and (a.CABINET_NUM like '%"+bianOrMc+"%' or a.CABINET_NAME like '%"+bianOrMc+"%')");
		}
		
		//org.hibernate.classic.Session session = this.sessionFactory.openSession();
		List<Object[]> list=sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setFirstResult((pageSize - 1) * rows).setMaxResults(rows).list();
		countSql.append(") aa");
		JSONObject result = new JSONObject();
		if(pageSize==1){
			System.out.println();
			String str=sessionFactory.getCurrentSession().createSQLQuery(countSql.toString()).uniqueResult().toString();
			int count=Integer.parseInt(str);
			result.put("total", count);
		}
		JSONArray jsonArray=new JSONArray();
		for (Object[] objects  : list) {
			//a.cabinet_id,b.room_no,a.CABINET_NUM,a.CABINET_NAME,a.CABINET_SURFACE,a.CABINET_SURFACE
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("ID", objects[0]);
			jsonObject.put("ROOM_NO", objects[1]);
			jsonObject.put("CABINET_NUM", objects[2]);
			jsonObject.put("CABINET_NAME", objects[3]);
			jsonObject.put("CABINET_SURFACE", objects[4]);
			jsonObject.put("CABINET_SURFACE", objects[4]);
			jsonArray.add(jsonObject);
		}
		result.put("rows", jsonArray);
		result.put("assSql", sql.toString());
		return result;
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int addCabinetList(List<AddCabinet> cbs) {
		//查找roomId,posx,posy是否有重复的记录
				for(int i=0;i<cbs.size();i++){
					List<Object[]> list=sessionFactory.getCurrentSession().createSQLQuery(" select * from  jfzs_cabinet_manage where room_id="+ cbs.get(i).getRoomId()+" and pos_x="+Integer.parseInt(cbs.get(i).getPos_x())+" and pos_y="+Integer.parseInt(cbs.get(i).getPos_y())).list();
							if(list.size()==1){
								//已经有了一条数据不做任何处理
								System.out.println("is has");
								continue;
							}else{
								//设置机柜Id
								int cabinet_id=Integer.parseInt(sessionFactory.getCurrentSession().createSQLQuery("select max(cabinet_id)+1 from jfzs_cabinet_manage").uniqueResult().toString());
								//插入数据库
								int res = sessionFactory.getCurrentSession().createSQLQuery("insert into jfzs_cabinet_manage values ("+cabinet_id+","+cbs.get(i).getRoomId()+",0,'"
								+cbs.get(i).getCompany()+"','"+cbs.get(i).getCabinet_num()+"','"+cbs.get(i).getCabinet_name()+"','"+cbs.get(i).getCabinet_surface()+
										"',"+cbs.get(i).getSpec_id()+",'"+cbs.get(i).getAssert_no()+"','"+cbs.get(i).getPower_a()+"','"
								+cbs.get(i).getPower_b()+"',"+cbs.get(i).getLayerCount()+","+cbs.get(i).getPos_x()+","+cbs.get(i).getPos_y()+
								",'"+cbs.get(i).getLabel()+"'"+")").executeUpdate();
								return res;
							}
				}
				return 0;
	}
	@Override
	public List<Map<String, String>> selCabinetByJiFangId(int roomId) {
		//根据机房 显示机柜
		Connection conn = sessionFactory.getCurrentSession().connection();
		StringBuffer sql = new StringBuffer();
		sql.append("select cabinet_id,cabinet_name from jfzs_cabinet_manage where room_id=").append(roomId).append(" order by cabinet_name");;
		try {
			List<Map<String, String>> list = SQLUtil
					.query(conn, sql.toString());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public JSONObject selEquipmentsByQuery(int pageSize, int rows, String juZhan, String jiFang, String cabinetId,
			String gridId, String equipmentName) {
		// TODO Auto-generated method stub
		System.out.println("查询设备-信息dao");
		System.out.println("psize:"+pageSize+"  rows:"+rows+"  juzhan:"+juZhan+" jiFang:"+jiFang+" cabinetId:"+cabinetId+" gridId:"+gridId+" equipmentName:"+equipmentName);
		StringBuffer sql=new StringBuffer();
		StringBuffer countSql=new StringBuffer();
		sql.append(" select a.equip_id,c.room_no,b.cabinet_num,a.cabinet_surface,a.nu_num,a.equip_name,d.spec_name,a.Manufacturer,a.CATEGORY,a.model,a.SUB_RACK_COUNT "+
				" from jfzs_equipment_manage a,jfzs_cabinet_manage b,room c,jfzs_spec_manage d "+
				"where a.cabinet_id=b.cabinet_id and b.room_id=c.room_id and a.spec_id=d.spec_id and c.district=20");
		
		countSql.append("  select count(*) from (select a.equip_id,c.room_no,b.cabinet_num,a.cabinet_surface,a.nu_num,a.equip_name,d.spec_name,a.Manufacturer,a.CATEGORY,a.model,a.SUB_RACK_COUNT "+
				" from jfzs_equipment_manage a,jfzs_cabinet_manage b,room c,jfzs_spec_manage d "+
				"where a.cabinet_id=b.cabinet_id and b.room_id=c.room_id and a.spec_id=d.spec_id and c.district=20 ");
		if(!"".equals(juZhan)){
			int regionId=Integer.parseInt(juZhan.trim());
			sql.append(" and c.region_id="+regionId);
			countSql.append(" and c.region_id="+regionId);
		}
		if(!"".equals(jiFang)){
			int roomId=Integer.parseInt(jiFang.trim());
			sql.append(" and c.room_id="+roomId );
			countSql.append(" and c.room_id="+roomId);
		}
		if(!"".equals(cabinetId)){
			int caId=Integer.parseInt(cabinetId.trim());
			sql.append(" and a.cabinet_id="+caId);
			countSql.append("  and a.cabinet_id="+caId);
		}
		if(!"".equals(gridId)){
			sql.append("  and a.nu_num like '%"+gridId+"%' ");
			countSql.append("  and a.nu_num like '%"+gridId+"%' ");
		}
		if(!"".equals(equipmentName)){
			sql.append(" and a.equip_name like '%"+equipmentName+"%' ");
			countSql.append("  and a.equip_name like '%"+equipmentName+"%' ");
		}
		
		List<Object[]> list=sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setFirstResult((pageSize - 1) * rows).setMaxResults(rows).list();
		countSql.append(") aa");
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
			jsonObject.put("EQUIP_ID", objects[0]);
			jsonObject.put("ROOM_NO", objects[1]);
			jsonObject.put("CABINET_NUM", objects[2]);
			jsonObject.put("CABINET_SURFACE", objects[3]);
			jsonObject.put("NU_NUM", objects[4]);
			jsonObject.put("EQUIP_NAME", objects[5]);
			
			jsonObject.put("SPEC_NAME", objects[6]);
			jsonObject.put("MANUFACTURER", objects[7]);
			jsonObject.put("CATEGORY", objects[8]);
			jsonObject.put("MODEL", objects[9]);
			jsonObject.put("SUB_RACK_COUNT", objects[10]);
			jsonArray.add(jsonObject);
		}
		result.put("rows", jsonArray);
		result.put("assSql", sql.toString());
		return result;
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public JSONObject selCardByequipID(int mypageSize, int myrows, int equipId) {
		StringBuffer sql=new StringBuffer();
		StringBuffer countSql=new StringBuffer();
		sql.append(" select card_id,sub_rack_id,occupy_slot_num,manufacturer,purPose,CATEGORY,model,ASSET_NO,POS_IDX,CHANGE_DATE from JFZS_BOARD_CARD_MANAGE where SUB_RACK_ID in (select SUB_RACK_ID from JFZS_SUB_RACK_MANAGE where equip_id="+equipId+")");
		countSql.append("  select count(*) from (select card_id,sub_rack_id,occupy_slot_num,manufacturer,purPose,CATEGORY,model,ASSET_NO,POS_IDX,CHANGE_DATE from JFZS_BOARD_CARD_MANAGE where SUB_RACK_ID in (select SUB_RACK_ID from JFZS_SUB_RACK_MANAGE where equip_id="+equipId+")) aa");
		List<Object[]> list=sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setFirstResult((mypageSize - 1) * myrows).setMaxResults(myrows).list();
		JSONObject result = new JSONObject();
		if(mypageSize==1){
			System.out.println();
			String str=sessionFactory.getCurrentSession().createSQLQuery(countSql.toString()).uniqueResult().toString();
			int count=Integer.parseInt(str);
			result.put("total", count);
		}
		JSONArray jsonArray=new JSONArray();
		for (Object[] objects  : list) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("CARD_ID", objects[0]);
			jsonObject.put("SUB_RACK_ID", objects[1]);
			jsonObject.put("OCCUPY_SLOT_NUM", objects[2]);
			jsonObject.put("MANUFACTURER", objects[3]);
			jsonObject.put("PURPOSE", objects[4]);
			jsonObject.put("CATEGORY", objects[5]);
			
			jsonObject.put("MODEL", objects[6]);
			jsonObject.put("ASSET_NO", objects[7]);
			jsonObject.put("POS_IDX", objects[8]);
			jsonObject.put("CHANGE_DATE", objects[9]);
			
			jsonArray.add(jsonObject);
		}
		result.put("rows", jsonArray);
		result.put("myassSql", sql.toString());
		return result;
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addEquipmentList(List<String[]> equips, int cabinetId) {
		Long equipId=Long.parseLong(sessionFactory.getCurrentSession().createSQLQuery("select max(equip_id)+1 from jfzs_equipment_manage").uniqueResult().toString());
		Long subRackId=1L;
		//Long subRackId=Long.parseLong(sessionFactory.getCurrentSession().createSQLQuery("select max(sub_rack_id)+1 from Jfzs_Sub_Rack_Manage").uniqueResult().toString());
		List<Object> list  =  sessionFactory.getCurrentSession()
				.createSQLQuery("select max(sub_rack_id)+1 from Jfzs_Sub_Rack_Manage").list();
		if(list.get(0)!=null){
			 subRackId=Long.parseLong(sessionFactory.getCurrentSession()
					 .createSQLQuery("select max(sub_rack_id)+1 from Jfzs_Sub_Rack_Manage").uniqueResult().toString());
		}
		
		for(int i=0;i<equips.size();i++){
				//设置设备Id
				//插入数据库
				String[] a=equips.get(i);
				JfzsEquipment ep=new JfzsEquipment();
				ep.setCabinetLayerNum(Integer.parseInt(a[0]));
				ep.setOccupyLayerNum(Integer.parseInt(a[1]));
				ep.setNuNum(a[2]);
				ep.setEquipName(a[3]);
				ep.setManufacturer(a[4]);
				ep.setCabinetSurface(a[5]);
				ep.setSpecId(Integer.parseInt(a[6]));
				/**
				 * 更新需求：
				 * 如果设备导入：
				 * 1. 专业跟当前机柜不一致，则需要更新当前机柜的专业；
				 * 2. 机柜面不在当前机柜的机柜面里的需要扩充当前机柜的机柜面；
				 * 	       如（设备插入A面，机柜只有B面，则机柜需要更新为A面，B面）
				 */
				String spName=(sessionFactory.getCurrentSession().createSQLQuery(" select cabinet_surface from jfzs_cabinet_manage where cabinet_id="+cabinetId).uniqueResult().toString());
				//插入数据库
				if(spName.indexOf(a[5])!=-1){//包含
					sessionFactory.getCurrentSession().createSQLQuery("update jfzs_cabinet_manage set spec_id="+Integer.parseInt(a[6])+" where cabinet_id="+cabinetId).executeUpdate();
				}else{
					//不包含
					spName+=","+a[5];
					sessionFactory.getCurrentSession().createSQLQuery("update jfzs_cabinet_manage set cabinet_surface='"+spName+"',spec_id="+Integer.parseInt(a[6])+" where cabinet_id="+cabinetId).executeUpdate();
				}
				//end 2017/2/13
				ep.setCategory(a[7]);
				ep.setModel(a[8]);
				ep.setAssertNo(a[9]);
				ep.setVersionInfo(a[10]);
				ep.setSubRackCount(Integer.parseInt(a[11]));//子框数
				
				ep.setCabinetId(Long.parseLong(cabinetId+""));
				ep.setEquipId(equipId+i);
				
				//1.插入设备数据信息
				sessionFactory.getCurrentSession().save(ep);
				
				int c=Integer.parseInt(a[11]);//子框数
				int flag=4;
				if(a[13].equals("ABCD")){
					flag=1;
				}else if(a[13].equals("1234")){
					flag=2;
				}else if(a[13].equals("01234")){//01234
					flag=3;
				}
				//2.根据子框数n添加n条子框记录
				int myoder=c;
				for(int j=0;j<c;j++){
					JfzsSubRack sr=new JfzsSubRack();
					sr.setEquipId(equipId+i);
					sr.setSlotCount(Integer.parseInt(a[12]));
					if(flag==1){
						int x=65+j;
						char lb=(char)x;
						sr.setLabel(lb+"");
						sr.setOrderNo(myoder--);
						
					}else if(flag==2){
						sr.setLabel((j+1)+"");
						sr.setOrderNo(j+1);
					}else if(flag==3){
						sr.setLabel(j+"");
						sr.setOrderNo(j+1);
					}
					System.out.println("Label:"+sr.getLabel()+""+"----order:"+sr.getOrderNo());
					//设置设备Id
					sr.setSubRackId(subRackId++);
					System.out.println("small---"+sr.toString());
					sessionFactory.getCurrentSession().save(sr);
				}//for end
				System.out.println("big---"+ep.toString());
			}
		return ;
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public JSONObject selCardsByQuery(int pageSize, int rows, String juZhan, String jiFang, String cabinetId,
			String gridId, String equipmentName) {
		StringBuffer sql=new StringBuffer();
		StringBuffer countSql=new StringBuffer();
		sql.append("  select a.card_id,e.room_no,d.cabinet_num,c.cabinet_surface,c.nu_num,c.equip_name,a.manufacturer,a.category,a.model,a.purpose,c.sub_rack_count,b.label,a.pos_idx"+
		 " from jfzs_board_card_manage a,jfzs_sub_rack_manage b,jfzs_equipment_manage c,jfzs_cabinet_manage d,room e"+
		" where a.sub_rack_id =b.sub_rack_id and b.equip_id=c.equip_id and c.cabinet_id=d.cabinet_id and d.room_id=e.room_id and e.district=20");
		countSql.append(" select count(*) from ( select a.card_id,e.room_no,d.cabinet_num,c.cabinet_surface,c.nu_num,c.equip_name,a.manufacturer,a.category,a.model,a.purpose,c.sub_rack_count,b.label,a.pos_idx"+
				 " from jfzs_board_card_manage a,jfzs_sub_rack_manage b,jfzs_equipment_manage c,jfzs_cabinet_manage d,room e"+
				" where a.sub_rack_id =b.sub_rack_id and b.equip_id=c.equip_id and c.cabinet_id=d.cabinet_id and d.room_id=e.room_id and e.district=20");
		System.out.println("psize:"+pageSize+"  rows:"+rows+"  juzhan:"+juZhan+" jiFang:"+jiFang+" cabinetId:"+cabinetId+" gridId:"+gridId+" equipmentName:"+equipmentName);
		if(!"".equals(juZhan)){
			int regionId=Integer.parseInt(juZhan.trim());
			sql.append(" and e.region_id="+regionId);
			countSql.append(" and e.region_id="+regionId);
		}
		if(!"".equals(jiFang)){
			int roomId=Integer.parseInt(jiFang.trim());
			sql.append(" and e.room_id="+roomId );
			countSql.append(" and e.room_id="+roomId);
		}
		if(!"".equals(cabinetId)){
			int caId=Integer.parseInt(cabinetId.trim());
			sql.append(" and d.cabinet_id="+caId);
			countSql.append("  and d.cabinet_id="+caId);
		}
		if(!"".equals(gridId)){
			sql.append("  and c.nu_num like '%"+gridId+"%' ");
			countSql.append("  and c.nu_num like '%"+gridId+"%' ");
		}
		if(!"".equals(equipmentName)){
			sql.append(" and c.equip_name like '%"+equipmentName+"%' ");
			countSql.append("  and c.equip_name like '%"+equipmentName+"%' ");
		}
		
		List<Object[]> list=sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setFirstResult((pageSize - 1) * rows).setMaxResults(rows).list();
		countSql.append(") aa");
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
			jsonObject.put("CARD_ID", objects[0]);
			jsonObject.put("ROOM_NO", objects[1]);
			jsonObject.put("CABINET_NUM", objects[2]);
			jsonObject.put("CABINET_SURFACE", objects[3]);
			jsonObject.put("NU_NUM", objects[4]);
			jsonObject.put("EQUIP_NAME", objects[5]);
			
			jsonObject.put("MANUFACTURER", objects[6]);
			jsonObject.put("CATEGORY", objects[7]);
			jsonObject.put("MODEL", objects[8]);
			jsonObject.put("PURPOSE", objects[9]);
			jsonObject.put("SUB_RACK_COUNT", objects[10]);
			
			jsonObject.put("LABEL",objects[11]);
			jsonObject.put("POS_X", objects[12]);
			jsonArray.add(jsonObject);
		}
		result.put("rows", jsonArray);
		result.put("assSql", sql.toString());
		return result;
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Map<String, String>> selEquipmentByCabinetId(int cabinetId) {
		//根据机柜查询设备
		/*Connection conn = this.session.connection();*/
		Connection conn = this.sessionFactory.getCurrentSession().connection();
		StringBuffer sql = new StringBuffer();
		sql.append("select equip_id,concat(equip_name,concat('--',cabinet_surface)) as \"equipname\"  from jfzs_equipment_manage where cabinet_id=").append(cabinetId);
		try {
			List<Map<String, String>> list = SQLUtil
					.query(conn, sql.toString());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 批量导入板卡信息
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addCardList(List<String[]> cards, int equipId) {
		//Long cardId=Long.parseLong(sessionFactory.getCurrentSession().createSQLQuery("select max(card_id)+1 from jfzs_board_card_manage").uniqueResult().toString());
		Long cardId=1L;
		//Long subRackId=Long.parseLong(sessionFactory.getCurrentSession().createSQLQuery("select max(sub_rack_id)+1 from Jfzs_Sub_Rack_Manage").uniqueResult().toString());
		List<Object> list  =  sessionFactory.getCurrentSession()
				.createSQLQuery("select max(card_id)+1 from jfzs_board_card_manage").list();
		if(list.get(0)!=null){
			cardId=Long.parseLong(sessionFactory.getCurrentSession().createSQLQuery("select max(card_id)+1 from jfzs_board_card_manage").uniqueResult().toString());
		}
		for(int i=0;i<cards.size();i++){
				//设置设备Id
				//插入数据库
				String[] a=cards.get(i);
					JfzsBoardCardManage bcm=new JfzsBoardCardManage();
					bcm.setCardId(cardId+i);//板卡Id
					
					//查询子框Id(根据标志和设备Id得到子框Id)
					Long subRackId=Long.parseLong(sessionFactory.getCurrentSession().createSQLQuery("select sub_rack_id from JFZS_SUB_RACK_MANAGE where equip_id="+equipId+" and label='"+a[0]+"'").uniqueResult().toString());
					bcm.setSubRackId(subRackId);//子框Id
					
					bcm.setOccupySlotNum(Integer.parseInt(a[1]));//所占槽位数
					bcm.setManufacturer(a[2]);
					bcm.setPurpose(a[3]);
					bcm.setCategory(a[4]);
					bcm.setModel(a[5]);
					bcm.setAssetNo("");
					bcm.setPosIdx(Integer.parseInt(a[6]));
					bcm.setChangeDate(a[7]);
					//1.插入设备数据信息
					sessionFactory.getCurrentSession().save(bcm);
		}
			return ;
	}
    //根据用户输入的子框标志和传入的设备Id，查询对应的子框Id
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public int getSubrackId(int equipId, String label) {
		System.out.println("hahha"+equipId+label);
		List<Object[]> list=sessionFactory.getCurrentSession().createSQLQuery("select sub_rack_id from JFZS_SUB_RACK_MANAGE where equip_id="+equipId+" and label='"+label+"'").list();
		if(list.size()>0){
			return 1;
		}
		return 0;
	}
	//4-1.查询专业列表
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public JSONObject selSpecsByQuery(int pageSize, int rows,String specName) {
		StringBuffer sql=new StringBuffer();
		StringBuffer countSql=new StringBuffer();
		sql.append(" select  spec_id,spec_name from jfzs_spec_manage ");
		countSql.append(" select count(*) from (select  spec_id,spec_name from jfzs_spec_manage");
		
		if(!"".equals(specName)){
			sql.append(" where  spec_name  like '%"+specName+"%' ");
			countSql.append(" where spec_name like '%"+specName+"%' ");
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
			jsonObject.put("specId", objects[0]);
			jsonObject.put("specName", objects[1]);
			jsonArray.add(jsonObject);
		}
		result.put("rows", jsonArray);
		result.put("specSql", sql.toString());
		return result;
	}
}
