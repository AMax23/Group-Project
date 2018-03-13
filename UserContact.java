import java.util.Scanner;

public class UserContact {

	//Ask the user for the pathname (command line)
	public static String getPathname() {
		System.out.print("Please enter the directory pathname: ");
		Scanner keyboard = new Scanner(System.in);			
		String pathname = keyboard.nextLine();							
		return pathname;
	}
	//Ask the user for the pathname (command line)
	public static String getTargetType() {
		System.out.print("Please enter a fully qualified name of a java type: ");
		Scanner keyboard = new Scanner (System.in);
		String targetType = keyboard.nextLine();
		return targetType;
	}

	//Ask the user for the pathname for the jar files (command line)
	public static String[] getJarFiles() {
		System.out.print("Please enter the pathname for the directory in which your JDT DOM jar files are kept: ");
		Scanner keyboard = new Scanner (System.in);
		String[] sources = {keyboard.nextLine()};
		keyboard.close();
		return sources;
	}
	
}
