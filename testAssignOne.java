import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Map;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotatableType;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.LambdaExpression;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.PrimitiveType;

import java.awt.List;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

// all test are using locations based on my computer
// that being said, should tests be conducted on difference computers there must be new dummy files created
// and the path locations must be modified
public class testAssignOne {
	/**
	 * test for method getPathname
	 */
	@Test
	// all "get" test cases only require a mock input test
	public void testGetPathname() {
		//set
		UserContact ucTest = new UserContact();
		String testString = "C:\\Users\\Max\\Desktop\\BASEDIR";
		InputStream in = new ByteArrayInputStream(testString.getBytes());
		System.setIn(in);
		Scanner keyboard = new Scanner(System.in);
		//test
		ucTest.getPathname(keyboard);	 
	}
	 
	 /**
	  * test for method getTargetType
	  */
	@Test
	public void testGetTargetType() {
		//set
		UserContact ucTest = new UserContact();
		String testString = "int";
		InputStream in = new ByteArrayInputStream(testString.getBytes());
		System.setIn(in);
		Scanner keyboard = new Scanner(System.in);
		//test
		ucTest.getTargetType(keyboard);	
	 }
	
	/**
	 * test for method getJarFiles
	 */
	@Test
	public void testGetJarFiles() {
		//set
		UserContact ucTest = new UserContact();
		String[] testString = {"D:\\ADDITIONAL TOOLS\\eclipse\\plugins"};
		InputStream in = new ByteArrayInputStream(testString[0].getBytes());
		System.setIn(in);
		Scanner keyboard = new Scanner(System.in);
		//test
		ucTest.getJarFiles(keyboard);
	}
	 
	 /**
	 * test for method filesToSTring
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	 @Test
	 public void testFilesToString() throws FileNotFoundException, IOException{
		 //set
		 TreeBuilder builderTest = new TreeBuilder();
		 String pathname = "C:\\Users\\Max\\Desktop\\BASEDIR";
		 String pathnameFail = "test";
		 //test(found)
		 builderTest.filesToString(pathname);
		 //test(not found)
		 builderTest.filesToString(pathnameFail);		 
		 
	 }
	 
	 /**
	 * test for method makeSyntaxTree
	 */
	 @Test
	 public void testMakeSyntaxTree(){
		 //set
		 TreeBuilder builder = new TreeBuilder();
		 ASTParser parser = ASTParser.newParser(AST.JLS8);
		 ASTNode a = parser.createAST(null);
		 String[] sources = {"D:\\ADDITIONAL TOOLS\\eclipse\\plugins"};
		 String pathname = "C:\\Users\\Max\\Desktop\\BASEDIR";
		 String [] classpath = {pathname};
		 String sourceString = builder.filesToString(pathname);	
		 //test
		 ASTNode cu = builder.makeSyntaxTree(sourceString.toCharArray(),classpath, sources, sourceString);
	 
	 }
	 
	 
	 /**
	 * test for method countDec
	 */
	 @Test
	 public void testCountDec(){	
		 //set  for interface test
		 TypeCounter counterTest = new TypeCounter();
		 TreeBuilder builder = new TreeBuilder();
		 String testString = "interface";
		 ASTParser parser = ASTParser.newParser(AST.JLS8);
		 ASTNode a = parser.createAST(null);
		 String[] sources = {"D:\\ADDITIONAL TOOLS\\eclipse\\plugins"};
		 String pathname = "C:\\Users\\Max\\Desktop\\BASEDIR";
		 String [] classpath = {pathname};
		 String sourceString = builder.filesToString(pathname);	
		 //test
		 ASTNode cu = builder.makeSyntaxTree(sourceString.toCharArray(),classpath, sources, sourceString);
		 counterTest.countDec(cu, testString);
		 
		//set for enum test
		 TypeCounter counterTest2 = new TypeCounter();
		 TreeBuilder builder2 = new TreeBuilder();
		 String testString2 = "enum Quark{UP, DOWN}";
		 ASTParser parser2 = ASTParser.newParser(AST.JLS8);
		 ASTNode a2 = parser2.createAST(null);
		 String[] sources2 = {"D:\\ADDITIONAL TOOLS\\eclipse\\plugins"};
		 String pathname2 = "C:\\Users\\Max\\Desktop\\BASEDIR";
		 String [] classpath2 = {pathname};
		 String sourceString2 = builder2.filesToString(pathname2);	
		 //test2
		 ASTNode cu2 = builder2.makeSyntaxTree(sourceString2.toCharArray(),classpath2, sources2, sourceString2);
		 counterTest.countDec(cu2, testString2);
		 
		//set for int test
		 TypeCounter counterTest3 = new TypeCounter();
		 TreeBuilder builder3 = new TreeBuilder();
		 String testString3 = "int b;";
		 ASTParser parser3 = ASTParser.newParser(AST.JLS8);
		 ASTNode a3 = parser3.createAST(null);
		 String[] sources3 = {"D:\\ADDITIONAL TOOLS\\eclipse\\plugins"};
		 String pathname3 = "C:\\Users\\Max\\Desktop\\BASEDIR";
		 String [] classpath3 = {pathname};
		 String sourceString3 = builder3.filesToString(pathname3);	
		 //test
		 ASTNode cu3 = builder3.makeSyntaxTree(sourceString3.toCharArray(),classpath3, sources3, sourceString3);
		 counterTest.countDec(cu3, testString3);
		 
	 }
	
	 /**
	  * test for method countRef
	  */
	 @Test
	 public void testCountRef() {
		 //set for a string test
		 TypeCounter counterTest = new TypeCounter();
		 TreeBuilder builder = new TreeBuilder();
		 String testString = "String a = \"Hello\";";
		 ASTParser parser = ASTParser.newParser(AST.JLS8);
		 ASTNode a = parser.createAST(null);
		 String[] sources = {"D:\\ADDITIONAL TOOLS\\eclipse\\plugins"};
		 String pathname = "C:\\Users\\Max\\Desktop\\BASEDIR";
		 String [] classpath = {pathname};
		 String sourceString = builder.filesToString(pathname);	
		 //test
		 ASTNode cu = builder.makeSyntaxTree(sourceString.toCharArray(),classpath, sources, sourceString);
		 counterTest.countRef(cu, testString);
		 
	 }
}
