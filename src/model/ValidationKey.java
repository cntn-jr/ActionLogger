package model;

import java.io.Serializable;

//�t�H�[���̐������m�F�L�[
public class ValidationKey implements Serializable {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}