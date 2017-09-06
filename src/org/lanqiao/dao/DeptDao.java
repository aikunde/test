package org.lanqiao.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.lanqiao.entity.Dept;

public class DeptDao {
	
	private	SqlSessionFactory factory=null;
	public DeptDao(SqlSessionFactory factory){
		this.factory=factory;
	}
	public Dept ById(String deptno){
		SqlSession session=factory.openSession();
		return session.selectOne("ns.getDeptByDeptno", deptno);
	}
}
