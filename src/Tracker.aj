
public aspect Tracker {

	public pointcut testMethodCall() : cflowbelow(call(* SampleTest.*(..))) && !within(Tracker) && !call(* java.io..*(*)) && !call(* java.lang..*(..));
	
	before() : testMethodCall() {
		if (thisJoinPoint.getKind().equals("method-call"))
		{
			System.out.println("START:\t" + thisJoinPoint.getSignature());
		}
	}
	
	after() : testMethodCall() {
		if (thisJoinPoint.getKind().equals("method-call"))
		{
			System.out.println("FINISH:\t" + thisJoinPoint.getSignature());
		}
	}
}
