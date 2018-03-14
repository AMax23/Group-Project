package project1;

import java.util.Scanner;

public class UserContact {

	//Ask the user for the pathname (command line)
	public String getPathname(Scanner keyboard) {
		System.out.print("Please enter the directory pathname: ");			
		String pathname = keyboard.nextLine();							
		return pathname;
	}
	
	//Ask the user for the pathname (command line)
	public String getTargetType(Scanner keyboard) {
		System.out.print("Please enter a fully qualified name of a java type: ");
		String targetType = keyboard.nextLine();
		return targetType;
	}

	//Ask the user for the pathname for the jar files (command line)
	public String[] getJarFiles(Scanner keyboard) {
		System.out.print("Please enter the pathname for the directory in which your JDT DOM jar files are kept: ");
		String[] sources = {keyboard.nextLine()};
		return sources;
	}
}
