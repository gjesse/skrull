package skrull.server;


public class HiWorld {
	public static void main(String[] args){
		HiWorld hi = new HiWorld();
		System.out.println(hi.getMessage());
	}
	
	public String getMessage(){
		return "my msg server";
	}
}
