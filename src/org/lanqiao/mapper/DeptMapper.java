package org.lanqiao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.lanqiao.entity.Dept;

public interface DeptMapper {
	public Dept getDeptByDeptno(Integer deptno);
	@Select("select * from dept")
	public List<Dept> getAll();
	@Insert("insert into dept(deptno,dname,loc) values(#{deptno},#{dname},#{loc})")
	public void add(Dept dept);
	
	public List<Dept> deptlist();
	
	
}
