package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//パラメーターを受け取る
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		String product=req.getParameter("order");

		String url = "jdbc:mysql://localhost/Shopping";
		String id = "root";
		String pw = "password";
		Connection cnct = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			//データベース接続
			Class.forName("com.mysql.jdbc.Driver");
			cnct = DriverManager.getConnection(url,id,pw);
			String query="SELECT * from product where pro_name = ?";
			pstmt = cnct.prepareStatement(query);
			pstmt.setString(1, product);
			rs = pstmt.executeQuery();

			//データベースから商品情報取得
			while (rs.next()) {
				String name=rs.getString("pro_name");
				int category=rs.getInt("cat_id");
				int price=rs.getInt("pro_price");
				int stock=rs.getInt("stock_no");
				String msg=rs.getString("pro_msg");

				HttpSession session=req.getSession(true);
				session.setAttribute("name",name);
				session.setAttribute("category",category);
				session.setAttribute("price",price);
				session.setAttribute("stock",stock);
				session.setAttribute("msg",msg);
				RequestDispatcher rd = req.getRequestDispatcher("Product.jsp");
				rd.forward(req, resp);
			}

		} catch(ClassNotFoundException ex){
			ex.printStackTrace();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (cnct!=null) cnct.close();
			} catch(Exception ex) { } }
	}

}
