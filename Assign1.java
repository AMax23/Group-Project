
import java.util.HashSet;
import java.util.Set;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;


public class Assign1 {

	public static void main (String [] args) {

		ASTParser parser = ASTParser.newParser(AST.JLS9);

		parser.setSource ("public class A {String i = \"astring\";  \\n int j; \\n int i = 9; \\n ArrayList<Integer> al = new ArrayList<Integer>();}".toCharArray());
		
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		parser.setResolveBindings(true);
		
		ASTNode cu = parser.createAST(null);	// cu contains one node
		
//		AST tree = cu.getAST();					// tree contains the tree from cu node 
		
//		ASTNode root = cu.getRoot();			// root contains root node from cu

//		System.out.println(root.toString());
		
		cu.accept(new ASTVisitor() {
 
			Set names = new HashSet();
 
			// This is from that website I sent Max
			public boolean visit(VariableDeclarationFragment node) {
				SimpleName name = node.getName();
//				this.names.add(name.getIdentifier());			// Dont know what this line was for
				System.out.println("Declaration of "+name);
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

	public boolean visit(VariableDeclarationFragment node) {
		SimpleName name = node.getName();
		//this.names.add(name.getIdentifier());
		System.out.println("Declaration of '"+name+"' at line");

		return true;

	}
}
