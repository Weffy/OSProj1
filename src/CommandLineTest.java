/*
 * CMP342: Operating Systems
 * Project #1
 * 
 * Collaborators: 
 * Krirk Pongsema
 * Larry Blanco
 */

public class CommandLineTest {
	
	
	public static void main(String[] args) {
		if (args.length == 0) {
			//There is no arguments passed to main
			//quit program
			Runtime.getRuntime().exit(0);
		} else if (args.length == 1) {
			//if there is only 1 parameter passed
			//the parameter MUST be an integer
			//which signifies how often to print the string
			int count = Integer.parseInt(args[0]);	
			printMiddle(count);
		} else if (args.length == 3) {
			//if there are three parameters
			//both flags will exist, and there is an integer to signal how often to print middle
			System.out.println("Starting...");
			int count = Integer.parseInt(args[2]);	
			printMiddle(count);
			System.out.println("Ending...");
		} else if (args.length == 2) {
			//if there are only two parameters
			//need to ascertain which flag is present...s or e.
			String flag = args[0];
			if ( flag.equals("-s") ) {
				//handles the s flag
				System.out.println("Starting...");
				int count = Integer.parseInt(args[1]);	
				printMiddle(count);
			} else if ( flag.equals("-e") ) {
				//handles the e flag
				int count = Integer.parseInt(args[1]);	
				printMiddle(count);
				System.out.println("Ending...");
			}
		} 
		

	}
	public static void printMiddle(int num) {
		for (int i = 0; i < num; i++) {
			System.out.println("middle");
		}
	}
	
}




