package model;

import java.util.Random;

public class RamdomIdCreate {// ƒ‰ƒ“ƒ_ƒ€‚È•¶š—ñ‚ğì¬ia-z,A-Z,0-9j
	public String createId() {
		String id = null;
		int i1, i2, i3, i4, i5, i6;
		Random random = new Random();
		String dict = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		i1 = random.nextInt(dict.length());
		i2 = random.nextInt(dict.length());
		i3 = random.nextInt(dict.length());
		i4 = random.nextInt(dict.length());
		i5 = random.nextInt(dict.length());
		i6 = random.nextInt(dict.length());
		id = dict.substring(i1, i1 + 1) + dict.charAt(i2) + dict.charAt(i3) + dict.charAt(i4) + dict.charAt(i5)
				+ dict.charAt(i6);
		return id;
	}
}
