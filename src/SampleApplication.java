
public class SampleApplication {
	
	public int A() {
		
		System.out.println("Running method A...");
		
		B();
		
		return 1;
	}
	
	public float B() {
		
		System.out.println("Running method B...");
		
		C(3);
		D();
		
		return 1.0f;
	}

	public int C(int x) {
		
		
		System.out.println("Running method C...");
		
		if (x > 2)
		{
			G();
		}
		
		E();
		
		return 1;
	}
	
	public String D() {
		
		System.out.println("Running method D...");
		
		F();
		
		return "test";
	}
	
	public int E() {
		
		System.out.println("Running method E...");
		
		
		
		return 1;
	}
	
	public void F() {
		
		System.out.println("Running method F...");
		
	}
	
public double G() {
		
		System.out.println("Running method G...");
		
		return 1.0;
	}

}
