
/*
 * CMP342: Operating Systems
 * Project #1
 * 
 * Collaborators: 
 * Krirk Pongsema
 * Larry Blanco
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineTest {

	
	public static void main(String[] args) {
		
		//standard cases where array length is 0, 1, 2
		if (args.length == 0) {
			//if length is 0, then no args passed, quit program
			Runtime.getRuntime().exit(0);
		} else if (args.length == 1) {
			//if length is 1, only option is an int signifying how often to print "middle"
			int count = Integer.parseInt(args[0]);	
			printMiddle(count);
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
		} else if (args.length == 3) {
			//sends to 3 param version of regexChecker
			regexChecker3(args);
		} else if (args.length == 4) {
			//sends to 4 param version of regexChecker
			regexChecker4(args);
		}
				
	}

	/*
	 * just prints middle the specific number of times
	 */
	
	private static void printMiddle(int num) {
		for (int i = 0; i < num; i++) {
			System.out.println("middle");
		}
	}

	/*
	 * just prints end the specific number of times
	 */

	private static void printEnd(int num) {
		for (int i = 0; i < num; i++) {
			System.out.println("Ending...");
		}
	}
	
	/*
	 * places all of the arguments into 1 string to run through regex
	 */
	private static String createString(String[] args) {
		String stringToCheck = "";
		for (int i = 0; i < args.length; i++) {
			stringToCheck = stringToCheck.concat(" " + args[i]);
		}
		return stringToCheck;
	}
	
	/*
	 * 3 param version of regex
	 * I feel like I could've had separate checks for each of the flags
	 * I would've needed global/class variables to accomplish this; which seems like bad practice...
	 * in order to do this, I would've had to use a loop to run multiple flag checks
	 * then depending on the check, I could discern the location of the numbers that I would need to convert from a string
	 * This seemed wasteful, so I went with a method of trying to figure out possible patterns I could see determined by the 
	 * number of parameters...
	 */
	private static void regexChecker3(String[] args) {
		
		//place all of the elements of args into 1 string to run through regex matcher
		String stringToCheck = createString(args);

		//possible patterns we are looking for
		/*
		 * 3 param patterns
		 */
		//-s -e #
		String twoFlags = "\\-[es]\\s\\-[es]\\s\\d+";
		
		//-s # #
		String oneFlag = "\\-[es]\\s\\d+\\s\\d+";
		
		//create pattern objects
		Pattern patTwoFlags = Pattern.compile(twoFlags);
		Pattern patOneFlag = Pattern.compile(oneFlag);

		//matcher objects
		Matcher matTwoFlags = patTwoFlags.matcher(stringToCheck);
		Matcher matOneFlag = patOneFlag.matcher(stringToCheck);

		
		if ( matTwoFlags.find() ) {
			//matched two flags, therefore the only integer is the midCount...
			int midCount = Integer.parseInt( args[2] );
			System.out.println("Starting...");
			printMiddle(midCount);
			System.out.println("Ending...");
			
		} else if ( matOneFlag.find() ) {
			//just need to discern if the flag is an -s or an -e
			if ( args[0].equals("-s") ) {
				System.out.println("Starting...");
				int midCount = Integer.parseInt( args[1] );
				printMiddle(midCount);
			} else if ( args[0].equals("-e") ) {
				int midCount = Integer.parseInt( args[1] );
				printMiddle(midCount);
				int endCount = Integer.parseInt( args[2] );
				printEnd(endCount);
			}
	
		}

		
	}
	private static void regexChecker4(String[] args) {
		//place all of the elements of args into 1 string to run through regex matcher
		String stringToCheck = createString(args);
		/*
		 * 4 param patterns
		 */
		//-s # -e #
		String alternating = "\\-[es]\\s\\d+\\s\\-[es]\\s\\d+";
		
		//-s -e # #
		String flagsFirst = "\\-[es]\\s\\-[es]\\s\\d+\\s\\d+";
		
		//create pattern objects
		Pattern patAlt = Pattern.compile(alternating);
		Pattern patFlagsFirst = Pattern.compile(flagsFirst);
		
		//matcher objects
		Matcher matAlt = patAlt.matcher(stringToCheck);
		Matcher matFlagsFirst = patFlagsFirst.matcher(stringToCheck);
		
		if ( matAlt.find() ) {
			System.out.println("Starting...");
			//need to figure out which spot the -e flag is located in...
			//this will determine the location of the endCount
			int midCount = 0, endCount = 0;
			if ( args[0].equals("-e") ) {
				 midCount = Integer.parseInt( args[3] );
				 endCount = Integer.parseInt( args[1] );
				 
			} else if ( args[2].equals("-e") ) {
				 midCount = Integer.parseInt( args[1] );
				 endCount = Integer.parseInt( args[3] );
				 
			}

			 printMiddle(midCount);
			 printEnd(endCount);
			
		} else if ( matFlagsFirst.find() ) {
			//if flags come first...
			//the -e flag must be 2nd because it must have a parameter by pigeon hole principle
			//therefore endCount will be in args[2]
			System.out.println("Starting...");
			int midCount = Integer.parseInt( args[3] );
			int endCount = Integer.parseInt( args[2] );
			 printMiddle(midCount);
			 printEnd(endCount);
			
		}
	}
	
	
}




