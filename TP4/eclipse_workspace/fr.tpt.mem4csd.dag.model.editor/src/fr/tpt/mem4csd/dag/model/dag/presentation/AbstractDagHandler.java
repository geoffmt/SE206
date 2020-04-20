package fr.tpt.mem4csd.dag.model.dag.presentation;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.tpt.mem4csd.dag.model.dag.DagSpecification;

public abstract class AbstractDagHandler extends AbstractHandler {


	protected DagSpecification getDagSpecification( final ExecutionEvent p_event ) {
		final URI modelUri = extractSelectedUri( p_event );
		
		if ( modelUri == null ) {
			return null;
		}
		
		final ResourceSet resSet = new ResourceSetImpl();
		final Resource modelRes = resSet.getResource( modelUri , true );
		
		if ( modelRes.getContents().isEmpty() ) {
			return null;
		}
		
		return (DagSpecification) modelRes.getContents().get( 0 );
	}
	
	private URI extractSelectedUri( final ExecutionEvent p_event ) {
		final ISelection currentSelection = HandlerUtil.getCurrentSelection( p_event );
	
		if ( currentSelection instanceof ITreeSelection ) {
			for (final Object element : ( (ITreeSelection) currentSelection ).toList() ) {
				if ( element instanceof IFile) {
					return URI.createPlatformResourceURI( ( (IFile) element ).getFullPath().toString(), true );
				}
				
				if ( element instanceof DagSpecification ) {
					return ( (DagSpecification) element ).eResource().getURI();
				}
			}
		}
		
		return null;
	}
}
