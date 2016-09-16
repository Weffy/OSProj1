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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;



public class MyShell {

	public static void main(String[] args) {
		Process p = null;
		BufferedReader inStream = new BufferedReader( new InputStreamReader( System.in ) );
		BufferedReader outStream;
		String [] batchParser;
		while ( true ) {
			System.out.println("prompt>");
			String input = null;

			/*
			 * Get input from user
			 */
			input = getInput(inStream);
			if (input == null) {
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
			/*
			 * batchParser will check if there is a batch file call
			 * splits it into two parts using the space as the delimeter
			 * first part is the potential batch command
			 * second is the potential file name
			 */
			batchParser = input.split(" ");
			if(batchParser.length == 2){
				batch(batchParser[0], batchParser[1], p);
				continue;
			}
			if ( p == null ) {
				//if the process is null, return to the top of the loop and start again
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
		try {
			String userInput = inStream.readLine().trim();
			if (userInput == null || userInput.length() == 0) {

				
			} else {
				return userInput;
			}
			
		} catch (IOException e) {

			e.printStackTrace();
			System.out.println("...");
			Runtime.getRuntime().exit(0);
			return null;
		}

		return null;

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
		} 
		
		
	}
	
	/*
	 * Creates the process and waits for the child process to complete before exiting the try
	 */
	public static Process runProcess(String input, Process p) {
		try {
			p = Runtime.getRuntime().exec(input);
			p.waitFor();
		} catch (IOException e) {
			System.out.println("Error executing: " + input);
			p = null;

		} catch (InterruptedException e) {

			System.out.println("Error executing: " + input);
			p = null;

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
			System.out.println("Failed to extract data from process object...");	
		}

	}
	
	/*
	 * if input = batch, then read the lines into a list
	 * for each line in the list lines, run all of the same commands we ran in the main...
	 */
	public static void batch(String input, String filename, Process p) {
		BufferedReader outStream;
		if(input.equals("batch")){
			List<String> lines;
			try {
				lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
				for( String line:lines){
					exitCheck(line);
					p = runProcess(line, p);
					if ( p == null ) {
						continue;
					}
					outStream = new BufferedReader( new InputStreamReader( p.getInputStream() ) );
					extract(p, outStream);
				}
				
			} catch (IOException e) {
				
			}
			
			
		}
	}	
	


}
