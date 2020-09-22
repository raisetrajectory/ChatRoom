package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BusinessLogic;
import model.UserInfoDto;


@WebServlet("/NewAccount")
public class NewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public NewAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");                  //文字コードをUTF-8で設定

		String userId = request.getParameter("new_userId");
		String password = request.getParameter("new_password");
		String userName = request.getParameter("new_username");
		String sha256 = "";
		try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] result = digest.digest(password.getBytes());
            sha256 = String.format("%040x", new BigInteger(1, result));
        } catch (Exception e){
            e.printStackTrace();
        }

		BusinessLogic logic = new BusinessLogic();
		boolean success   = logic.newUserRegist(userId, sha256,userName);

		//ユーザーデータの抽出成功/失敗に応じて表示させる画面を振り分ける
		if (success) {
			//ユーザーデータの登録に成功：ログインOKとしてセッションにユーザーデータをセット＆ホーム画面へ
			HttpSession session           = request.getSession();
			UserInfoDto dto = new UserInfoDto();
			dto.setUserId(userId);
			dto.setUserName(userName);
			dto.setPassWord(password);
			//DBから抽出したユーザデータをセッションにセット
			session.setAttribute("LOGIN_INFO", dto);

			//メッセージ画面へ転送
			response.sendRedirect("ShowAllMessage");

		} else {
			System.out.println("失敗");
			//ユーザーデータの抽出に失敗：ログインNGとしてログイン画面へ転送
			response.sendRedirect("./view/Login.jsp");

		}
	}


}
