/*
 * CMP342: Operating Systems
 * Project #1
 * 
 * Collaborators: 
 * Krirk Pongsema
 * Larry Blanco
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MyShell {

	public static void main(String[] args) {
		Process p = null;
		BufferedReader inStream = new BufferedReader( new InputStreamReader( System.in ) );
		BufferedReader outStream;
		while ( true ) {
			System.out.println("prompt>");
			String input = null;

			/*
			 * Get input from user
			 */
			input = getInput(inStream);
			if (input == null) {
//					System.out.println(input);
				continue;
			}
			/*
			 * Check if exit condition exists
			 */
			exitCheck(input);

			/*
			 * If we get here, exit condition was not found...
			 * moving on to running the command
			 */
			p = runProcess(input, p);
			if ( p == null ) {
				continue;
			}
			/*
			 * Extract data from process object
			 */
			outStream = new BufferedReader( new InputStreamReader( p.getInputStream() ) );
			extract(p, outStream);
			

		}
	}
	/*
	 * Gathers the input from the user, then puts it into variable strInput.  
	 * If the try fails, method will return NULL
	 */
	public static String getInput(BufferedReader inStream) {
//		BufferedReader inStream = new BufferedReader( new InputStreamReader( System.in ) );
//		try {
//			
//			System.out.println(inStream.ready());
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		try {
			String userInput = inStream.readLine().trim();
			if (userInput == null || userInput.length() == 0) {
//				return null;
//				System.out.println("if block");
				
			} else {
//				System.out.println("else block");
				
				return userInput;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("...");
			Runtime.getRuntime().exit(0);
			return null;
		}
//		try {
//			inStream.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Stuff");
//			e.printStackTrace();
//			
//		}
		return null;
		
		
		
//		Scanner scanner = new Scanner(System.in);
//		String userInput = null;
//		if ( scanner.hasNextLine() ) {
//			userInput = scanner.nextLine();
//		}
//		
//		if (userInput.length() != 0 && userInput != null) {
//			
//			return userInput;
//		}
//		scanner.close();
//		return null;

	}
	
	/*
	 * Checks through the String to see if it is either
	 * a) equal to "exit" which is the command to quit the shell
	 * b) the String is empty
	 * c) the String is NULL from the getInput method.
	 */
	public static void exitCheck(String input) {
		if ( input.toLowerCase().equals("exit") ) {
			System.out.println("Exiting...");
			Runtime.getRuntime().exit(0);
		} /*else if ( input.length() == 0 || input == null ) {
			System.out.println("Exiting...");
			Runtime.getRuntime().exit(0);
		} */
		
		
	}
	
	/*
	 * Creates the process and waits for the child process to complete before exiting the try
	 */
	public static Process runProcess(String input, Process p) {
		try {
			p = Runtime.getRuntime().exec(input);
			p.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error executing: " + input);
			p = null;
//				e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Error executing: " + input);
			p = null;
//				e.printStackTrace();
		}
		return p;
	}
	
	/*
	 * Takes the process as a parameter
	 * uses a buffered reader to extract the information from the process object
	 */
	public static void extract(Process p, BufferedReader outStream) {
		String extract;
		try {
			while ((extract = outStream.readLine()) != null) {
				  System.out.println(extract);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
//				e.printStackTrace();
			}

	}
		

}
