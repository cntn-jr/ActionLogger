package model;

import java.util.regex.*;

public class InputChecker {

	public static String checkLongInput(String data) throws InputCheckException {
		if (data.length() <= 256) {// 256�����ȉ��ł����OK
			return data;
		}
		// 255�����ȏ�ł���΁A�G���[
		throw new InputCheckException();
	}

	public static String checkPhoneNumber(String phone) throws InputCheckException {

		// ���[���A�h���X�̐��K�\��
		String regularExpression = "^[0-9]{2,4}-[0-9]{2,4}-[0-9]{3,4}$";
		Pattern pattern = Pattern.compile(regularExpression);
		Matcher matcher = pattern.matcher(phone);
		// �p�^�[�����}�b�`��������������̂܂ܕԂ�
		if (matcher.find()) {
			return phone;
		}
		// �}�b�`���Ȃ������̂ŁA��O������
		throw new InputCheckException();
	}

	public static String checkMailAddress(String address) throws InputCheckException {

		// ���[���A�h���X�̐��K�\��
		String regularExpression = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		Pattern pattern = Pattern.compile(regularExpression);
		Matcher matcher = pattern.matcher(address);

		if (matcher.find()) {
			return address;
		}

		throw new InputCheckException();

	}

	public static boolean checkPassword(String pass) {

		// �p�X���[�h�̐��K�\��
		String regularExpression = "^[a-zA-Z0-9_-]{3,30}$";
		Pattern pattern = Pattern.compile(regularExpression);
		Matcher matcher = pattern.matcher(pass);

		if (matcher.find()) {
			return true;
		}

		return false;

	}

}
