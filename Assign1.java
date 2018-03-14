package project1;

import org.eclipse.jdt.core.dom.ASTNode;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Assign1 {

	private static int decCount = 0;
	private static int refCount = 0;
	
	public static void main (String [] args) throws FileNotFoundException, IOException {
	
		/** ENTER TARGET DIRECTORY, TARGET TYPE, AND SOURCE DIRECTORY MANUALLY
		 	Uncomment the following and enter the specified strings: **/
//		String pathname = "C:\Users\teale\Documents\School\SENG 300\TestCode";  // TEALE				
//		String pathname = "/Users/ahmed/Downloads/test2";						// AHMED			
//		String [] sources = {"C:\\Users\\teale\\Documents\\eclipse-java-oxygen-2-win32-x86_64\\eclipse\\plugins"};	// TEALE
//		String [] sources = {"/Users/ahmed/Downloads/jar"};															// AHMED
//		String targetType = "Foo";
		
//		String pathname = "<ENTER TARGET DIRECTORY HERE>"; 		
//		String targetType = "<ENTER TYPE HERE>";		
//		String [] sources = {"<ENTER JAR FILES DIRECTORY HERE>"};				
								
		
		/** USER INPUT **/
		Scanner keyboard = new Scanner(System.in); 
		UserContact uc = new UserContact();
		String pathname = uc.getPathname(keyboard);			 											// Ask user for directory path
		String [] sources = uc.getJarFiles(keyboard);													// Ask user for the directory containing their jar files 		
		String targetType = uc.getTargetType(keyboard);													// Ask user for type they're looking for

		TreeBuilder builder = new TreeBuilder();
		String sourceString = builder.filesToString(pathname);									// Put contents of directory into one string
		String [] classpath = {pathname};														// Where the files are located
		ASTNode cu = builder.makeSyntaxTree(sourceString.toCharArray(),classpath, sources, sourceString); // Build syntax tree from the string file content
		
		TypeCounter counter = new TypeCounter();
		decCount = counter.countDec(cu, targetType);
		refCount = counter.countRef(cu, targetType);

		System.out.printf("Type: %2s\t\tDeclarations found: %1d\tReferences found: %d \n",targetType,decCount,refCount);

	}
}
