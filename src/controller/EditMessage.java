package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BusinessLogic;

//投稿されたメッセージの編集用サーブレット
@WebServlet("/EditMessage")
public class EditMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public EditMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");

		String messageId = request.getParameter("message_id");
		String editMessageText = request.getParameter("edit_message_text");

		BusinessLogic logic = new BusinessLogic();
		boolean successEdit = logic.editUserMessage(messageId,editMessageText);

		if(successEdit) {
			response.sendRedirect("ShowAllMessage");
		}else {
			response.sendRedirect("htmls/error.html");
		}
	}


}
