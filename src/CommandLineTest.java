
public class CommandLineTest {
	public static void main(String[] args) {
		if (args.length == 0) {
			Runtime.getRuntime().exit(0);
		} else if (args.length == 1) {
			int count = Integer.parseInt(args[0]);	
			for (int i = 0; i < count; i++) {
				System.out.println((i+1) + ": middle");
			}
		} else if (args.length == 3) {
			System.out.println("Starting...");
			int count = Integer.parseInt(args[2]);	
			for (int i = 0; i < count; i++) {
				System.out.println((i+1) + ": middle");
			}
			System.out.println("Ending...");
		} else if (args.length == 2) {
			String flag = args[0];
			if ( flag.equals("-s") ) {
				System.out.println("Starting...");
				int count = Integer.parseInt(args[1]);	
				for (int i = 0; i < count; i++) {
					System.out.println((i+1) + ": middle");
				}
			} else if ( flag.equals("-e") ) {
				int count = Integer.parseInt(args[1]);	
				for (int i = 0; i < count; i++) {
					System.out.println((i+1) + ": middle");
				}
				System.out.println("Ending...");
			}
		} 
		

	}
}




