package cn.kgc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//提交到dev前修改
public class JdbcCase {
	public static void main(String[] args) {
		//1.声明jdbc接口
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//定义字符串
		String url = "jdbc:mysql://localhost:3306/mybatis";
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//创建数据库的连接
			conn = DriverManager.getConnection(url, "root", "root");
			//获取ps对象
			String sql = "select * from book where id=?";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, 1);
			rs = ps.executeQuery();
			//希望返回的是对象POJO/entity
			while(rs.next()){
				System.out.println(rs.getInt(1) + "/" + rs.getString(2) + "/" + rs.getDouble(3));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null){
					rs.close();
					rs = null;
				}
				if(ps != null){
					ps.close();
					ps = null;
				}
				if(conn != null){
					conn.close();
					conn = null;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
