package org.lanqiao.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.lanqiao.dao.DeptDao;
import org.lanqiao.entity.Dept;
import org.lanqiao.mapper.DeptMapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xml.internal.utils.NSInfo;

public class test {
	private static SqlSessionFactory factory =null;
	static{
		String confiString="mybatis-cof.xml";
		try {
			InputStream is =Resources.getResourceAsStream(confiString);
			factory=new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void tt() throws IOException{
		//加载mybatis
		String confiString="mybatis-cof.xml";
		InputStream is =Resources.getResourceAsStream(confiString);
		//根据核心配置根据创建工厂对象
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		//创建session对象
		SqlSession  session =factory.openSession();
		//取数据
		Dept dept =session.selectOne("ns.getDeptByDeptno", "10");
		//关闭对象
		session.close();
		System.out.println(dept);
	}
	@Test
	public void tt1() throws IOException{
		String confiString="mybatis-cof.xml";
		InputStream is =Resources.getResourceAsStream(confiString);
		//根据核心配置根据创建工厂对象
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		//创建session对象
		SqlSession  session =factory.openSession();
		System.out.println(session.selectList("ns.getDepts"));
		session.close();
	}
	@Test
	public void tt2() throws IOException{
		String confiString="mybatis-cof.xml";
		InputStream is =Resources.getResourceAsStream(confiString);
		//根据核心配置根据创建工厂对象
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		//创建session对象
		SqlSession  session =factory.openSession();
		System.out.println(session.selectOne("ns.getCount"));
		session.close();
	}
	@Test
	public void tt3() throws IOException{
		String confiString="mybatis-cof.xml";
		InputStream is =Resources.getResourceAsStream(confiString);
		//根据核心配置根据创建工厂对象
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		//创建session对象
		SqlSession  session =factory.openSession();
		List<Dept> list =session.selectList("ns.getDeptByDeptName","S");
			System.out.println(list);
		session.close();
	}
	@Test
	public void tt5() throws IOException{
		String confiString="mybatis-cof.xml";
		InputStream is =Resources.getResourceAsStream(confiString);
		//根据核心配置根据创建工厂对象
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		//创建session对象
		SqlSession  session =factory.openSession();
		Dept dept1=new Dept(15, "1", "1");
		int retResult = session.insert("ns.addUser", dept1);
		session.commit();
		session.close();
		System.out.println(retResult);
	}
	@Test
	public void tt6() throws IOException{
		String confiString="mybatis-cof.xml";
		InputStream is =Resources.getResourceAsStream(confiString);
		//根据核心配置根据创建工厂对象
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		//创建session对象
		SqlSession  session =factory.openSession();
		int rr =session.delete("ns.removeUser", 15);
		session.commit();
		session.close();
		System.out.println(rr);
	}
	@Test
	public void tt7() throws IOException{
		String confiString="mybatis-cof.xml";
		InputStream is =Resources.getResourceAsStream(confiString);
		//根据核心配置根据创建工厂对象
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		//创建session对象
		SqlSession  session =factory.openSession();
		Dept dept1=new Dept(21, "2", "1");
		int i =session.update("ns.updateUser", dept1);
		session.commit();
		session.close();
		System.out.println(i);
		
	}@Test
	public void tt8() throws IOException{
		DeptDao dao=new DeptDao(factory);
		System.out.println(dao.ById("10"));
	}
	@Test
	public void tt9() throws IOException{
		SqlSession session=factory.openSession();
		
		DeptMapper deptMapper =session.getMapper(DeptMapper.class);
		
		System.out.println(deptMapper.getDeptByDeptno(10));
	}
	@Test
	public void tt10(){
		SqlSession session=factory.openSession();
		DeptMapper deptMapper =session.getMapper(DeptMapper.class);
		System.out.println(deptMapper.getAll());
	}
	@Test
	public void tt11(){
		SqlSession session=factory.openSession();
		DeptMapper deptMapper =session.getMapper(DeptMapper.class);
		Dept dept1=new Dept(23, "2", "1");
		deptMapper.add(dept1);
		session.commit();
	}
	@Test
	public void tt12(){
		SqlSession session=factory.openSession();
		DeptMapper deptMapper =session.getMapper(DeptMapper.class);
		Page<Dept> paper=PageHelper.startPage(2, 2);
		List<Dept> list =deptMapper.getAll();
		PageInfo<Dept> info=new PageInfo<Dept>(list);
		System.out.println(list.get(3));
		System.out.println(info.getList().get(0));
	}
}
