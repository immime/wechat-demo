package cc.wechat.openapi;

import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "122";
		System.err.println(str.matches("[0-9]"));
		
		
		String menuCode = "0_1_1_";
		StringTokenizer tokenizer = new StringTokenizer(menuCode, "_");
		while (tokenizer.hasMoreElements()) {
			String menuNode = (String) tokenizer.nextElement();
			System.err.println(menuNode);
		}
	}

}
