package lesson03.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.tools.ant.Project;

import lesson03.util.DBConnectionPool;
import lesson03.vo.Member;

public interface MemberDao {
		List<Member> selectList() throws Exception;
		int insert(Member member) throws Exception;
}