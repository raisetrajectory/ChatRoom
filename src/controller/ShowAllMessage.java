package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BusinessLogic;
import model.MessageDto;
import model.UserInfoDto;
/**
 * Servlet implementation class ShowAllMessage
 */
@WebServlet("/ShowAllMessage")
@MultipartConfig(maxFileSize = 2147483647)
public class ShowAllMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ShowAllMessage() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		if(userInfoOnSession != null) {
		BusinessLogic logic = new BusinessLogic();
		List<MessageDto> dtoList = new ArrayList<MessageDto>();
		dtoList = logic.ShowAllMessage();

		request.setAttribute("ShowAllMessage", dtoList);

		RequestDispatcher dispatch = request.getRequestDispatcher("./view/Message.jsp");

		dispatch.forward(request, response);

		}else {
			//未ログインの場合、ログイン画面へ遷移
			response.sendRedirect(request.getContextPath()+"/view/Login.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
