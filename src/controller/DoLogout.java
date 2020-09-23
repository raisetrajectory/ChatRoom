package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserInfoDto;

@WebServlet("/DoLogout")
public class DoLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DoLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");  //文字コードをUTF-8で設定

		//出力用のストリームの取得
		PrintWriter out = response.getWriter();

		//セッションからユーザーデータを取得
		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		//ログイン状態によって表示画面を振り分ける

		if (userInfoOnSession != null) {
			//ログイン済：ログアウト処理を実施

			//ログアウトに伴いセッション情報を破棄
			session.invalidate();

			response.sendRedirect("./view/Login.jsp");
		} else {
			//未ログイン：ログイン画面へ転送
			response.sendRedirect("./view/Login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
