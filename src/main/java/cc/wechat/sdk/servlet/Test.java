package cc.wechat.sdk.servlet;

public class Test {
	public static void main(String[] args) {
		for(int i = 0; i < 10; i ++) {
			if(i == 3) {
				System.out.println("hit");
				break;
			}
			System.out.println(i);
		}
	}
}
