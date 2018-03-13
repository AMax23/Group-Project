// Version: March 12 - Teale

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

/*
 * Java type:
 * Class declaration......DONE
 * Interface declaration.....DONE
 * Enum declaration....
 * Annotation declaration......DONE
 */

public class Assign1 {

	private static int decCount = 0;
	private static int refCount = 0;
	
	public static void main (String [] args) throws FileNotFoundException, IOException {
	
		/** ENTER TARGET DIRECTORY, TARGET TYPE, AND SOURCE DIRECTORY MANUALLY
		 	Uncomment the following and enter the specified strings: **/
//		String pathname = "C:\Users\teale\Documents\School\SENG 300\TestCode";  			// TEALE				
//		String pathname = "/Users/ahmed/Downloads/test";						// AHMED
//		String pathname = "D:\Javafiles\BASEDIR";							//MAX
//		String [] sources = {"C:\\Users\\teale\\Documents\\eclipse-java-oxygen-2-win32-x86_64\\eclipse\\plugins"};	// TEALE
//		String [] sources = {"/Users/ahmed/Downloads/jar"};								// AHMED
//		String [] sources = {"D:\ADDITIONAL TOOLS\eclipse\plugins"};							// MAX
		
//		String pathname = "<ENTER TARGET DIRECTORY HERE>"; 		
//		String targetType = "<ENTER TYPE HERE>";		
//		String [] sources = {"<ENTER JAR FILES DIRECTORY HERE>"};				
								
		
		/** USER INPUT **/
		UserContact uc = new UserContact();
		String pathname = uc.getPathname();														// Ask user for directory path
		String targetType = uc.getTargetType();													// Ask user for type they're looking for
		String [] sources = uc.getJarFiles();													// Ask user for the directory containing their jar files 		

		TreeBuilder builder = new TreeBuilder();
		String sourceString = builder.filesToString(pathname);									// Put contents of directory into one string
		String [] classpath = {pathname};														// Where the files are located
		ASTNode cu = builder.makeSyntaxTree(sourceString.toCharArray(),classpath, sources, sourceString); // Build syntax tree from the string file content
		
		TypeCounter counter = new TypeCounter();
		decCount = counter.countDec(cu, targetType);
		refCount = counter.countRef(cu, targetType);

		System.out.printf("Type: %2s\tDeclarations found: %1d\tReferences found: %d \n",targetType,decCount,refCount);

	}
}
