// Version: March 12

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

	static int decCount = 0;
	static int refCount = 0;
	
	public static void main (String [] args) throws FileNotFoundException, IOException {

//		String pathname = getPathname();																// Ask user for directory path
//		String pathname = "C:\\Users\\teale\\Documents\\School\\SENG 300\\SENGTutorial1\\src";			// DEBUGGING: Just put the pathname here
//		String targetType = getType();																// Ask user for type they're looking for

//		String pathname = "C:\\Users\\teale\\Documents\\School\\SENG 300\\SENGTutorial1\\src";			// Manual for now
		String pathname = "/Users/ahmed/Downloads/test";
		String targetType = "enum";																// DEBUGGING: Just put target type here
//		String [] sources = {"C:\\Users\\teale\\Documents\\eclipse-java-oxygen-2-win32-x86_64\\eclipse\\plugins"};			// Jar files
		String [] sources = {"/Users/ahmed/Downloads/jar"};
		
		//String pathname = getPathname();
		//String targetType = getTargetType();
		String sourceString = filesToString(pathname);												// Make a string of the files' code
		String [] classpath = {pathname};															// Where the files are located
		String unitName = sourceString;

		ASTNode cu = makeSyntaxTree(sourceString.toCharArray(),classpath, sources, unitName); 			// Build syntax tree from the string file content

		count(cu, targetType);																		// Traverse, count declarations of given type

		System.out.printf("Type: %2s\tDeclarations found: %1d\tReferences found: %d \n",targetType,decCount,refCount);

	}

//	//Ask the user for the pathname (command line)
//	public static String getPathname() {
//		System.out.print("Please enter the directory pathname: ");	// Ask user
//		Scanner keyboard = new Scanner(System.in);						// Instantiate a scanner
//		String pathname = keyboard.nextLine();								// Take in what they entered
//		//kb.next();
//		keyboard.close();													// Close scanner
//		return pathname;											// Return the pathname
//	}
	
	public static String getTargetType() {
		System.out.print("Please enter a fully qualified name of a java type: ");
		Scanner keyboard = new Scanner (System.in);
		String targetType = keyboard.nextLine();
		keyboard.close();
		return targetType;
	}

	// Convert all the content of the java files into one string to parse
	public static String filesToString(String pathname) throws IOException, FileNotFoundException {
		StringBuilder sb = new StringBuilder();							// Instantiate a String Builder
		File directory = new File(pathname);
		File[] allFiles = directory.listFiles();						// Put files from directory into a list

		for (File f : allFiles) {										// For each file in the given directory
			String fileName = f.getName().toLowerCase();				// Get the name of the file

			if (f.isFile() && fileName.endsWith(".java")) {				// If it's a java file
				BufferedReader reader = null;							// Read each line

				try {
					reader = new BufferedReader(new FileReader(f));		// Read the file
					String aLine;

					while ((aLine = reader.readLine()) != null) {		// While the line isn't empty
						sb.append(aLine);								// Append it to the string builder
						sb.append(System.lineSeparator());				// Append a line break
					}
				}
				finally {
					reader.close();										// Close the reader, on to next for-loop iteration...
				}
			}
		}
		return sb.toString();											// Return the built string of code
	}

	// Parse and traverse the syntax tree from the string of code
	public static ASTNode makeSyntaxTree(char[] sourceCode,String[] classpath, String[] sources, String unitName ) {

		ASTParser parser = ASTParser.newParser(AST.JLS9);
		parser.setSource (sourceCode);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setUnitName(unitName);																// Name of the file
		parser.setEnvironment(classpath, sources, new String[] {"UTF-8"}, true);

		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		return cu;
	}


	public static void count(ASTNode cu, String targetType) {
		cu.accept(new ASTVisitor() {
			
			// COUNT ANNOTATION DECLARATIONS
			public boolean visit(AnnotationTypeDeclaration node) {									// Visit any Annotation Declaration nodes in the directory
				System.out.println("<< Annotation Type ??>>");
				String nodeAsString = node.toString();
				//				System.out.println("THIS IS ONE NODE \n" +nodeAsString); 								// DEBUGGING
				//System.out.println("<<HAKUNA MATATA>>");
				if ( nodeAsString.contains("interface "+ targetType) )							// if this annotation declaration includes the target type
					decCount++;																		// Add to count
				
				return true;
			}
			
			// COUNT ENUMERATION DECLARATIONS
			public boolean visit(EnumDeclaration node) {												// Visit any Enumeration Declaration nodes in the directory
				String nodeAsString = node.toString();
				System.out.println("THIS IS ONE NODE \n" +nodeAsString); 								// DEBUGGING
				System.out.println("<< check enum?? >>");
				if (nodeAsString.contains("enum "+ targetType)) { 									// if this enumeration declaration includes the target type
					decCount++;																		// Add to count
				}
				return true;
			}

			// COUNT CLASS AND INTERFACE DECLARATIONS
			public boolean visit(TypeDeclaration node) {												// Visit any Type Declaration nodes in the directory: (this kind of node
				//System.out.println("<< type = "+ node.resolveBinding().getQualifiedName()+" >>");
				if ( node.resolveBinding().getQualifiedName().equals(targetType) ) {
					decCount++;		
					}																				// Add to count

				return true;
			}

			public boolean visit (NormalAnnotation node) {
			//	System.out.println("<< In annotation >>");
				if ( node.resolveTypeBinding().getQualifiedName().equals(targetType) ) {
					refCount++;
				}

				return true;
			}
			
//		public boolean visit(VariableDeclarationFragment node) {									// Visit any Type Declaration nodes in the directory:
////		System.out.println("THIS IS ONE NODE \n" +node.toString()); //DEBUGGING				// (this kind of node includes Class Declarations
//		SimpleName name = node.getName();
//		//IVariableBinding binding = node.resolveBinding();
//		//String nodeType = binding.getQualifiedName();
//		//String classType = binding.getDeclaringClass().getName();							// Trying to get class name
//		//System.out.println("<< Annotations type = "+binding.+" >>");								// Print class type. Might give null pointer error
//		//if ((nodeType).equals(targetType)){refCount++;}
//		System.out.printf("<< Variable = %s\t\tType = %s >>\n",name,node.resolveBinding().getType().getQualifiedName());
////		if ( nodeType.equals(targetType)) {
////			System.out.println("<<Test in VaribleDecFrag: refCount ++ >>");
//////			decCount++;
////			refCount++;
//		return true;
//		}
		
			public boolean visit (MarkerAnnotation node) {
			//	System.out.println("<< In annotation >>");
				if ( node.resolveTypeBinding().getQualifiedName().equals(targetType) ) {
				//	System.out.println("Count ++");
					refCount++;
				}

				return true;
			}

			public boolean visit(SimpleType node) { 
			//	System.out.println("<< In SimpleType >>" + node.resolveBinding().getQualifiedName());
				if ( node.resolveBinding().getQualifiedName().equals(targetType) ) {
				//	System.out.println("Count ++");
					refCount++;
				}

				return true;
			}


			public boolean visit(PrimitiveType node) {	
				//System.out.println("name = "+node.resolveBinding().getQualifiedName());
				if ( node.resolveBinding().getQualifiedName().equals(targetType) & !(node.resolveBinding().getQualifiedName().equals("void"))) {
					refCount++;
				}

				return true;
			}

		});
	}

}