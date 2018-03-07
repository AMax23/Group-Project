package team1;

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

		parser.setSource ("public class A {String i = \"9\";}".toCharArray());

		parser.setKind(ASTParser.K_COMPILATION_UNIT);

//	parser.createAST(null);

		CompilationUnit cu = (CompilationUnit)parser.createAST(null);

		String a = "";

		cu.accept(new ASTVisitor() {

			public boolean visit() {
				if (cu.getNodeType() == 45) {
					System.out.println("hshdhs");
				};
				return false;
				}
			boolean test = visit();


		});

		ASTNode a = cu.getRoot();

		System.out.println(a.getNodeType());

		//visit();




	}

	public boolean visit(VariableDeclarationFragment node) {
		SimpleName name = node.getName();
		//this.names.add(name.getIdentifier());
		System.out.println("Declaration of '"+name+"' at line");

		return true;

	}
}
