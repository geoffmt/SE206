package fr.tpt.mem4csd.dag.simulator.tests;

import java.io.File;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import fr.tpt.mem4csd.dag.model.dag.DagPackage;
import fr.tpt.mem4csd.dag.model.dag.DagSpecification;
import fr.tpt.mem4csd.dag.simulator.CodeGenerator;

public class Dag1 {

	
	private static DagSpecification loadResource(String path)
	{
	// Create a resource set to hold the resources.
			//
			ResourceSet resourceSet = new ResourceSetImpl();
			
			// Register the appropriate resource factory to handle all file extensions.
			//
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
				(Resource.Factory.Registry.DEFAULT_EXTENSION, 
				 new XMIResourceFactoryImpl());

			// Register the package to ensure it is available during loading.
			//
			resourceSet.getPackageRegistry().put
				(DagPackage.eNS_URI, 
						DagPackage.eINSTANCE);
	
			
			// Construct the URI for the instance file.
			// The argument is treated as a file path only if it denotes an existing file.
			// Otherwise, it's directly treated as a URL.
			//
			File file = new File(path);
			URI uri = file.isFile() ? URI.createFileURI(file.getAbsolutePath()): URI.createURI(path);

			try {
				// Demand load resource for this file.
				//
				Resource resource = resourceSet.getResource(uri, true);
				System.out.println("Loaded " + uri);

				// Validate the contents of the loaded resource.
				//
				for (EObject eObject : resource.getContents()) {
					Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eObject);
					if (diagnostic.getSeverity() != Diagnostic.OK) {
						printDiagnostic(diagnostic, "");
					}
				}
				
				EObject eObj = resource.getContents().get(0);
				
				if( eObj instanceof DagSpecification ) {
					return (DagSpecification) eObj;
				}
				
				throw new IllegalArgumentException( "Unknown resource content: " + eObj );
				
			}
			catch (RuntimeException exception) {
				System.out.println("Problem loading " + uri);
				exception.printStackTrace();
			}
			return null;
	}
	
	protected static void printDiagnostic(Diagnostic diagnostic, String indent) {
		System.out.print(indent);
		System.out.println(diagnostic.getMessage());
		for (Diagnostic child : diagnostic.getChildren()) {
			printDiagnostic(child, indent + "  ");
		}
	}
	
	public static void main(String[] args) {
		String path1 = "tests/dag1/input/dag1.dag";
		
		DagSpecification dag1 = loadResource(path1);
		String outputPath = "tests/dag1/output/main.c";
		CodeGenerator cg = new CodeGenerator(dag1, outputPath);
		cg.generateDagCode();
	}

}
