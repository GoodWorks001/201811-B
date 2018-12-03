package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cart extends HttpServlet {

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			resp.setContentType("text/html; charset=UTF-8");

			String url = "jdbc:mysql://localhost/shopping";
			String id = "root";
			String pass = "password";
			Connection cnct = null;
			Statement st = null;
			ResultSet rs = null;

			PrintWriter out = resp.getWriter();

			try {
				Class.forName("com.mysql.jdbc.Driver");
				cnct = DriverManager.getConnection(url, id, pass);

				st = cnct.createStatement();

				rs = st.executeQuery("SELECT * from product where pro_cd=5");

				out.println("<html>");
				out.println("<head><title></title></head>");
				out.println("<body>");
				out.println("<div align=\"center\">");

				out.println("<h1>カート</h1>");
				out.println("<table border=1 width=\"450\">");

				out.println("<th>商品名</th><th>単価</th><th>数量</th>");

				rs.next(); {


					String pro_name= rs.getString(2);
				    int pro_price = rs.getInt(4);

					int tax = (int)(pro_price * 0.08);

					out.println("<tr>");
					out.println("<td>"+pro_name+"</td>");
					out.println("<td>"+"￥"+pro_price+"</td>");
					out.println("<td>"+1+"</td>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<th colspan=2>消費税</th>");
					out.println("<th>"+"￥"+ tax +"</th>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<th colspan=2>合計金額</th>");
					out.println("<th>"+"￥"+ (pro_price + tax)+"</th>");
					out.println("</tr>");

				}

				out.println("</table>");
				out.println("<a href = \"Search.jsp\"><input type=\"submit\" value=\"買い物を続ける\"></a>");
				out.println("<form action = http://localhost:8080/Space/order><input type=\"submit\" value=\"購入\"></form>");
				out.println("<input type =\"hidden\" name = \"pro_name\" value = \"商品名\"/>");
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
					if(st!=null) st.close();
					if(cnct!=null) cnct.close();
			}catch(Exception ex) {

			}
		}


		}


	}





