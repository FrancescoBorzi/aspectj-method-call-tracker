import java.io.*;

public aspect Tracker {
	
	String path;
	File file;
	FileWriter fw;
	BufferedWriter bw;

	String position[];
	int index;
	String currentMethod;

	public Tracker() {
		position = new String[254];
		index = 0;
		try {
			path = "output.txt";
			file = new File(path);
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public pointcut testMethodFlow() : 
		cflowbelow(call(* SampleTest.*(..))) && !within(Tracker) &&
		!call(* java.io..*(*)) &&
		!call(* java.lang..*(..)) &&
		!initialization(*.new(..)) && 
		!preinitialization(*.new(..)) &&
		!handler(*);
	public pointcut testMethodCall() : call(* SampleTest.*(..));
	
	Object around() : testMethodCall() {
		position[0] = "\t\"test method\"";
		try {
			bw.write("digraph output {\n");
	    }
	    catch(IOException e) {
	    	e.printStackTrace();
	    }
		
		Object returnValue = proceed();
		
		try {
			bw.write("\n}\n");
		    bw.flush();
		    bw.close();
	    }
	    catch(IOException e) {
	    	e.printStackTrace();
	    }
		
		return returnValue;
	}

	Object around() : testMethodFlow() {
		if (thisJoinPoint.getKind().equals("method-call"))
		{
			currentMethod = thisJoinPoint.getSignature().toString();
			System.out.println("START:\t" + currentMethod);

			index++;
			position[index] = "\t\"" + currentMethod + "\"";

			try {
				bw.write(position[index - 1] + " -> " + position[index] + ";\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Object returnValue = proceed();
		
		if (thisJoinPoint.getKind().equals("method-call"))
		{
			currentMethod = thisJoinPoint.getSignature().toString();
			System.out.println("FINISH:\t" + currentMethod);
			index--;
		}
		
		return returnValue;
	}

}
