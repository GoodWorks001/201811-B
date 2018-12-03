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
import javax.servlet.http.HttpSession;

public class Order1130 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			//パラメーターを受け取る
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");
			String code=req.getParameter("pro_cd");

			HttpSession session=req.getSession(true);
			session.setAttribute("pro_cd",code);

			///データベース
			String url = "jdbc:mysql://localhost/shopping";
			String id = "root";
			String pass = "password";
			Connection cnct = null;
			PreparedStatement pst = null;
			ResultSet rs = null;

			PrintWriter out = resp.getWriter();

			try {
				Class.forName("com.mysql.jdbc.Driver");
				cnct = DriverManager.getConnection(url, id, pass);
				String query="SELECT * from product where pro_cd =?";
				pst = cnct.prepareStatement(query);
				pst.setString(1,code);
				rs = pst.executeQuery();

				out.println("<html>");
				out.println("<head><title></title></head>");
				out.println("<body>");
				out.println("<div align=\"center\">");

				out.println("<h1>購入してもよろしいでしょうか？？</h1>");
				out.println("<table border=1 width=\"450\">");

				out.println("<th>商品名</th><th>単価</th><th>数量</th>");

				while (rs.next()) {

					String pro_name= rs.getString("pro_name");
				    int pro_price = rs.getInt("pro_price");
				    int stock_no = rs.getInt("stock_no");

					int tax = (int)((pro_price * 1.08)-pro_price);

					out.println("<tr>");
					out.println("<td>"+pro_name+"</td>");
					out.println("<td>"+"￥"+pro_price+"</td>");
					out.println("<td>"+stock_no+"</td>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<th colspan=2>消費税</th>");
					out.println("<th>"+"￥"+ stock_no*tax +"</th>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<th colspan=2>合計金額</th>");
					out.println("<th>"+"￥"+ ((pro_price*stock_no)+tax*stock_no)+"</th>");
					out.println("</tr>");

				}

				out.println("</table>");

				out.println("<form action=http://localhost:8080/Space/Search.jsp>");
				out.println("<input type=\"submit\" value=\"いいえ\">");
				out.println("</form>");

				out.println("<form action=http://localhost:8080/Space/stock>");
				out.println("<input type=\"submit\" value=\"はい\">");
				out.println("</form>");

				out.println("</div>");
				out.println("</body>");
				out.println("</html>");

			}catch(ClassNotFoundException ex) {
					ex.printStackTrace();
			}catch(SQLException ex) {
					ex.printStackTrace();
			}finally {
				try {
					if (rs!=null) rs.close();
					if(pst!=null) pst.close();
					if(cnct!=null) cnct.close();
			}catch(Exception ex) {

			}
		}


		}


	}


