package fr.tpt.mem4csd.dag.model.dag.presentation;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;

import fr.tpt.mem4csd.dag.model.dag.DagSpecification;
import fr.tpt.mem4csd.dag.simulator.CodeGenerator;

public class GenerateSimulationCodeHandler extends AbstractDagHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final DagSpecification dagSpecification = getDagSpecification( event );
		
		if ( dagSpecification != null ) {
			final URI codeFileUri = dagSpecification.eResource().getURI().trimFileExtension().appendFileExtension( "c" );
			final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile( new Path( codeFileUri.toPlatformString( true ) ) );
			
			final CodeGenerator codeGen = new CodeGenerator( dagSpecification, file.getLocation().toString() );
			codeGen.generateDagCode();
			
			try {
				file.refreshLocal( 0 , null );
			} 
			catch ( final CoreException ex ) {
				throw new ExecutionException( "", ex );
			}
		}
		
		return null;
	}
}
