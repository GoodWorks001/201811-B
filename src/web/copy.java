package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class copy extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		String name =req.getParameter("name");
		int stock =Integer.parseInt(req.getParameter("order_no"));

		String url = "jdbc:mysql://localhost/Shopping";
		String id = "root";
		String pass = "password";
		Connection cnct = null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		ResultSet rs = null;

		PrintWriter out = resp.getWriter();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnct = DriverManager.getConnection(url, id, pass);

			String sel="SELECT * from product where pro_name=?";

			pstmt=cnct.prepareStatement(sel);

			pstmt.setString(1,name);

			rs =pstmt.executeQuery();

			rs.next();

			int stock2 = rs.getInt("order_no");

			int newstock = stock-stock2;

			String upd="UPDATE product set stock_no=? where pro_name=?";
			pstmt2 = cnct.prepareStatement(upd);

			pstmt2.setInt(1,newstock);
			pstmt2.setString(2,name);

			pstmt.executeUpdate();


			out.println("<html>");
			out.println("<head><title></title></head>");
			out.println("<body>");
			out.println("<center><font size=\"+4\">お買い上げありがとうございました。</font></center>");


			out.println("<center><a href=\"http://localhost:8080/Space/Search.jsp\"><input type=\"submit\" value=\"買い物を続ける\"></a>");

			out.println("<form action=\"Login.jsp\" method=\"Post\">");
			out.println("<input type=\"submit\"value=\"ログアウト\">");
			out.println("</form></center>");
			out.println("</body>");
			out.println("</html>");

		}catch(ClassNotFoundException ex) {
				ex.printStackTrace();
		}catch(SQLException ex) {
				ex.printStackTrace();
		}finally {
			try {
				if (rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(cnct!=null) cnct.close();
		}catch(Exception ex) {

		}
	}

	}
	}






