
import java.util.HashSet;
import java.util.Set;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.WhileStatement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;


public class Assign1 {
	
	
	static int decCount = 0;
	static int refCount = 0;
	
	//Ask the user for the pathname (command line)
	public static String getPathname() {							
		Scanner kb = new Scanner(System.in);						// Instantiate a scanner
		System.out.print("Please enter the directory pathname: ");	// Ask user
		String pathname = kb.next();								// Take in what they entered
		kb.next();
		kb.close();													// Close scanner
		return pathname;											// Return the pathname 
		
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
	
	// Parse the syntax tree from the string of code
	public static ASTNode makeSyntaxTree(char[] sourceCode) {

		ASTParser parser = ASTParser.newParser(AST.JLS9);						
		parser.setSource (sourceCode);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
		return cu;
	}
	
	// Count class, enumeration, annotation, and interface type declarations
	// and increment the decCount global variable
	public static void countDeclarations(ASTNode cu, String targetType) {
		cu.accept(new ASTVisitor() {
			
			public boolean visit(TypeDeclaration node) {						// Visit any Type Declaration nodes in the directory: (this kind of node
				String nodeAsString = node.toString();							// includes Class Declarations and Interface Declarations.)
				System.out.println("THIS IS ONE NODE \n" +nodeAsString); 		//DEBUGGING	
				if (nodeAsString.contains("class "+ targetType) | nodeAsString.contains("interface "+ targetType) ) { // if this class or interface declaration includes the target type
					decCount++;													// Add to count
				}
				return true;
			}
			
			public boolean visit(EnumDeclaration node) {						// Visit any Enumeration Declaration nodes in the directory
				String nodeAsString = node.toString();							
				System.out.println("THIS IS ONE NODE \n" +nodeAsString); 		// DEBUGGING	
				if (nodeAsString.contains("enum "+ targetType)) { 				// if this enumeration declaration includes the target type
					decCount++;													// Add to count
				}
				return true;
			}
			
			public boolean visit(AnnotationTypeDeclaration node) {				// Visit any Annotation Declaration nodes in the directory
				String nodeAsString = node.toString();							
				System.out.println("THIS IS ONE NODE \n" +nodeAsString); 		// DEBUGGING	
				if (nodeAsString.contains("interface "+ targetType)) { 			// if this annotation declaration includes the target type
					decCount++;													// Add to count
				}
				return true;
			}
		});
	}

	public static void main (String [] args) throws FileNotFoundException, IOException {
		
//		String pathname = getPathname();										// Ask user for directory path
		String pathname = "C:\\Users\\teale\\Documents\\School\\SENG 300\\SENGTutorial1\\src";	// DEBUGGING: Just put the pathname here
//		String targetType = getType();											// Ask user for type they're looking for
		String targetType = ("Cuboid");											// DEBUGGING: Just put target type here	
		String sourceString = filesToString(pathname);							// Make a string of the files' code
		System.out.println("THIS IS THE ENTIRE SOURCE STRING"+sourceString);  	// FOR DEBUGGING
		ASTNode cu = makeSyntaxTree(sourceString.toCharArray()); 				// Build syntax tree from the string file content
		countDeclarations(cu, targetType);										// Traverse, count declarations of given type
		System.out.println(decCount); 											// DEBUG print the declaration count for a given type 
	}
}  
		
				/** Our previous shenanigans **/		
		
//	}
/*
		ASTParser parser = ASTParser.newParser(AST.JLS3);

		parser.setSource ("public class A {\\n String s = \"astring\";  \\n int j; \\n int i = 9; while(i>0){i--;}}".toCharArray());
		
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		parser.setResolveBindings(true);
		
		ASTNode cu = parser.createAST(null);	// cu contains one node
		
//		AST tree = cu.getAST();					// tree contains the tree from cu node 
		
//		ASTNode root = cu.getRoot();			// root contains root node from cu

//		System.out.println(root.toString());
		
		cu.accept(new ASTVisitor() {
			
//			Set names = new HashSet();
 
			// This is from that website I sent Max
			public boolean visit(WhileStatement node) {
				System.out.println(node.toString());
				int type = node.getNodeType(); 
				System.out.println(type);
				System.out.println(node.getParent().toString());
				System.out.println(node.getParent().getNodeType());
				
//				SimpleName name = node.getName();
//				this.names.add(name.getIdentifier());			// Dont know what this line was for
//				System.out.println("Declaration of "+name);
				return true; 
				
			
//			public boolean visit (VariableDeclarationFragment node) {
//				String stringofNode = root.toString();				//make string representation of node
//				System.out.println(stringofNode);					//print the current node in a string
//				System.out.println(root.getName());
//				if (root.getNodeType() == 15) {
//					System.out.println("hshdhs");
//				return true;
			}
//		boolean test = visit();

		});

//		ASTNode a = cu.getRoot();

//		System.out.println(a.getNodeType());

//		visit();




	}

/*	public boolean visit(VariableDeclarationFragment node) {
		SimpleName name = node.getName();
		//this.names.add(name.getIdentifier());
		System.out.println("Declaration of '"+name+"' at line");

		return true;
*/
	
	


