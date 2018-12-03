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

public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	req.setCharacterEncoding("UTF-8");
	resp.setContentType("text/html; charset=UTF-8");

	//入力画面で入力されたパラメーターを受け取る
	String user=req.getParameter("user");
	String pass=req.getParameter("pass");


	//データベースに接続
	String url="jdbc:mysql://localhost/Shopping";
	String id="root";
	String pw="password";
	Connection cnct=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;

	//名前とパスワードをデータベースに参照
	try {
		Class.forName("com.mysql.jdbc.Driver");
		cnct=DriverManager.getConnection(url,id,pw);
		String query="SELECT * from user WHERE (login_cd,login_pw)=(?,?)";
		pstmt=cnct.prepareStatement(query);
		pstmt.setString(1,user);
		pstmt.setString(2,pass);
		rs=pstmt.executeQuery();

				if (rs.next()==true) {
					//セッションへ格納
						HttpSession session=req.getSession(true);
						session.setAttribute("login","OK");

						//商品検索ページへとぶ
						RequestDispatcher rd=req.getRequestDispatcher("Search.jsp");
						rd.forward(req,resp);

					//以下エラーメッセージ
					} else if ((user=="") || (pass=="")){
						req.setAttribute("err1","名前またはパスワードを入力してください");
						RequestDispatcher rd = req.getRequestDispatcher("LoginError1.jsp");
						rd.forward(req, resp);

					} else {
						req.setAttribute("err2", "名前またはパスワードが一致しません");
						RequestDispatcher rd =req.getRequestDispatcher("LoginError2.jsp");
						rd.forward(req, resp);
					}

	} catch (ClassNotFoundException ex) {
		ex.printStackTrace();
	} catch (SQLException ex) {
		ex.printStackTrace();
	} finally {
		try {
			if (cnct!=null) cnct.close();
		} catch (Exception ex) { }
	}

	}

}
