package model;

import java.util.ArrayList;
import java.util.List;


public class BusinessLogic {
	boolean dbConnection = false;

	public UserInfoDto checkUserInfo(String userId, String password) {

		UserInfoDto userDto = new UserInfoDto();
		Dao dao = new Dao();
		userDto = dao.doCheckUserInfo(userId,password);

		return userDto;
	}

	public List<MessageDto> ShowAllMessage(){
		List<MessageDto> dtoList = new ArrayList<MessageDto>();
		Dao dao = new Dao();
		dtoList = dao.doShowAllMessage();
		return dtoList;
	}

	public boolean inputMessage(MessageDto dto) {
		MessageDto inputDto = new MessageDto();
		inputDto = dto;
		Dao dao = new Dao();
		boolean successInput = dao.doInputMessage(inputDto);
		return successInput;
	}

	public boolean deleteMessage() {
		Dao dao = new Dao();
		boolean successDelete	= dao.doDeleteMessage();
		return successDelete;
	}

	public boolean deleteUserMessage(String messageId) {
		Dao dao = new Dao();
		boolean successDelete	= dao.doDeleteUserMessage(messageId);
		return successDelete;
	}
	public boolean editUserMessage(String messageId,String editMessageText) {
		Dao dao = new Dao();
		boolean successEdit = dao.doEditUserMessage(messageId,editMessageText);
		return successEdit;
	}
	public boolean newUserRegist(String userId,String password,String userName) {
		Dao dao = new Dao();
		boolean successRegist = dao.doNewUserRegist(userId,password,userName);
		return successRegist;
	}
}
