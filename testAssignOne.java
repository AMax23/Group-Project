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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class testAssignOne {
	
	/**
	 * test for method getPathname
	 */
	 @Test
	 public void testGetPathname(){
		 new Assign1();
		 Assign1.getPathname();
		 //test
		 
		 
	 }
	 
	 /**
	  * test for method getTargetType
	  */
	 @Test
	 public void testGetTargetType() {
		 //set
		 new Assign1();
		 Assign1.getTargetType();
		 //test
		 
	 }
	 
	 /**
	  * test for method getJarFiles
	  */
	 @Test
	 public void testGetJarFiles() {
		 //set
		 new Assign1();
		 Assign1.getJarFiles();
		 //test
	 }
	 
	 
	 /**
	 * test for method filesToSTring
	 */
	 @Test
	 public void testFilesToString(){
		 //set
		 new Assign1();
		 try {
			Assign1.filesToString("test");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
	 /**
	 * test for method makeSyntaxTree
	 */
	 @Test
	 public void testMakeSyntaxTree(){
		 //set
		 new Assign1();
		 //testCase.makeSyntaxTree( );
		 //test
	 }
	 
	 /**
	 * test for method count
	 */
	 @Test
	 public void testCount(){
		new Assign1();
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		ASTNode aTest = parser.createAST(null);
		Assign1.count(aTest ,"test");
		//test
		
		
		
	 }
	
}
