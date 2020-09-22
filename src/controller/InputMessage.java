package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.BusinessLogic;
import model.MessageDto;
import model.UserInfoDto;


@WebServlet("/InputMessage")
@MultipartConfig(maxFileSize = 2147483647)
public class InputMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public InputMessage() {
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

		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		//セッションからのユーザー名とリクエストパラメータの取得
		String userName = userInfoOnSession.getUserName();
		String message = request.getParameter("message").replaceAll("\n", "indention");
		Part filePart = request.getPart("image");

		//メッセージidの生成
		UUID uuid = UUID.randomUUID();
		String messageId = uuid.toString();

		//メッセージ格納用クラス
		MessageDto mDto = new MessageDto();

		mDto.setUserName(userName);
		mDto.setMessage(message);
		mDto.setTime(new Timestamp(System.currentTimeMillis()));
		mDto.setMessageId(messageId);

		//getPartで画像を取得していれば画像をbyte[]に変換してMessageDtoに格納
		if (filePart.getSize() != 0) {
				InputStream is = filePart.getInputStream();
		        byte[] image;
				image = toBase64(is);
		        mDto.setByte(image);
		}

		BusinessLogic logic = new BusinessLogic();
		boolean successInput = logic.inputMessage(mDto);

		if(successInput) {
			response.sendRedirect("ShowAllMessage");
		}else {
			response.sendRedirect("htmls/error.html");
		}


	}
	public  byte[] toBase64(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);

        BufferedImage bufferedImage = ImageIO.read(inputStream);
        ImageIO.write(bufferedImage, "png", bos);
        bos.flush();
        bos.close();

        byte[] bImage = baos.toByteArray();

        return bImage;
    }

}
