
public class SampleApplication {
	
	public int A() {
		
		System.out.println("Running method A...");
		
		B();
		
		return 1;
	}
	
	public int B() {
		
		System.out.println("Running method B...");
		
		C();
		D();
		
		return 1;
	}

	public int C() {
		
		System.out.println("Running method C...");
		
		E();
		
		return 1;
	}
	
	public int D() {
		
		System.out.println("Running method D...");
		
		F();
		
		return 1;
	}
	
	public int E() {
		
		System.out.println("Running method E...");
		
		
		
		return 1;
	}
	
	public int F() {
		
		System.out.println("Running method F...");
		
		
		
		return 1;
	}

}
