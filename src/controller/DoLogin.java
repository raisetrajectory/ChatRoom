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


@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public DoLogin() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");                  //文字コードをUTF-8で設定

		//セッションからユーザーデータを取得
		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		//ログイン状態によって表示画面を振り分ける
		// ※ログイン状態はセッション上からユーザーデータを取得できたか否かで判断
		//    ユーザーデータを取得できた　　　→既にログインされている
		//    ユーザーデータを取得できなかった→まだログインされていない
		if (userInfoOnSession != null) {
			//ログイン済：メッセージ画面に転送
			response.sendRedirect("ShowAllMessage");
		} else {
			//未ログイン：ログイン処理を実施

			//リクエストパラメータからユーザー入力値を取得
			String userId   = request.getParameter("userId");      //リクエストパラメータ（USER_ID）
			String password = request.getParameter("password");     //リクエストパラメータ（PASSWORD）
			String sha256 = "";
			try {
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            byte[] result = digest.digest(password.getBytes());
	            sha256 = String.format("%040x", new BigInteger(1, result));
	        } catch (Exception e){
	            e.printStackTrace();
	        }

			//「user_info」テーブルからユーザー入力値と合致するユーザーデータ（UserInfoDto型）を抽出
			// ※合致するデータがなかった場合、各フィールドがnullのDTOを得る
			BusinessLogic logic = new BusinessLogic();
			UserInfoDto   dto   = logic.checkUserInfo(userId, sha256);

			//ユーザーデータの抽出成功/失敗に応じて表示させる画面を振り分ける
			if (dto.getUserId() != null) {
				//ユーザーデータの抽出に成功：ログインOKとしてセッションにユーザーデータをセット＆ホーム画面へ
				System.out.print(dto.getUserName());
				//DBから抽出したユーザデータをセッションにセット
				session.setAttribute("LOGIN_INFO", dto);

				//メッセージ画面へ転送
				response.sendRedirect("ShowAllMessage");

			} else {
				System.out.println("失敗");
				//ユーザーデータの抽出に失敗：ログインNGとしてログイン画面へ転送
				response.sendRedirect("htmls/error.html");
			}
		}


	}

}
