package HelloWorldPackage;

public class HelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello World");
		// comment
	 
		String s1 = new String();
		s1 = "hello".concat(" ").concat("world");
		String s2 = s1.toUpperCase();
		
		System.out.println(s2);
	}
}
