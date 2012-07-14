package skrull.client;

public class Hello {
	public static void main(String[] args){
		Hello hi = new Hello();
		System.out.println(hi.getMessage());
	}
	
	public String getMessage(){
		return "my msg";
	}
}