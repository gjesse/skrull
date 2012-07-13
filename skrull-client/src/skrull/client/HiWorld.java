package skrull.client;

public class HiWorld {
	public static void main(String[] args){
		HiWorld hi = new HiWorld();
		System.out.println(hi.getMessage());
	}
	
	public String getMessage(){
		return "my msg";
	}
}