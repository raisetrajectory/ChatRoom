package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BusinessLogic;

/**
 * Servlet implementation class DeleteMessage
 */
@WebServlet("/DeleteMessage")
public class DeleteMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public DeleteMessage() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//レスポンス（出力データ）の文字コードを設定
				response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定
				//リクエスト（受信データ）の文字コードを設定
				request.setCharacterEncoding("UTF-8");



				BusinessLogic logic = new BusinessLogic();
				boolean successInput = logic.deleteMessage();

				if(successInput) {
					System.out.println("成功");
					response.sendRedirect("ShowAllMessage");
				}else {
					System.out.println("失敗");
					response.sendRedirect("ShowAllMessage");
				}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");

		String messageId = request.getParameter("message_id");
		BusinessLogic logic = new BusinessLogic();
		boolean successDelete = logic.deleteUserMessage(messageId);
		if(successDelete) {
			response.sendRedirect("ShowAllMessage");
		}else {
			response.sendRedirect("htmls/error.html");
		}
	}

}
