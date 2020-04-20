/**
 */
package fr.tpt.mem4csd.dag.model.dag.impl;

import fr.tpt.mem4csd.dag.model.dag.Channel;
import fr.tpt.mem4csd.dag.model.dag.DagPackage;
import fr.tpt.mem4csd.dag.model.dag.DagSpecification;
import fr.tpt.mem4csd.dag.model.dag.NamedElement;
import fr.tpt.mem4csd.dag.model.dag.Task;
import fr.tpt.mem4csd.dag.sort.TopologicalSort;
import fr.tpt.mem4csd.dag.sort.model.Arc;
import fr.tpt.mem4csd.dag.sort.model.Graph;
import fr.tpt.mem4csd.dag.sort.model.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.tpt.mem4csd.dag.model.dag.impl.DagSpecificationImpl#getName <em>Name</em>}</li>
 *   <li>{@link fr.tpt.mem4csd.dag.model.dag.impl.DagSpecificationImpl#getOwnedTasks <em>Owned Tasks</em>}</li>
 *   <li>{@link fr.tpt.mem4csd.dag.model.dag.impl.DagSpecificationImpl#getSortedTasks <em>Sorted Tasks</em>}</li>
 *   <li>{@link fr.tpt.mem4csd.dag.model.dag.impl.DagSpecificationImpl#getOwnedChannels <em>Owned Channels</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DagSpecificationImpl extends IdentifiedElementImpl implements DagSpecification {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOwnedTasks() <em>Owned Tasks</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedTasks()
	 * @generated
	 * @ordered
	 */
	protected EList<Task> ownedTasks;

	/**
	 * The cached value of the '{@link #getOwnedChannels() <em>Owned Channels</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedChannels()
	 * @generated
	 * @ordered
	 */
	protected EList<Channel> ownedChannels;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DagSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DagPackage.Literals.DAG_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DagPackage.DAG_SPECIFICATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Task> getOwnedTasks() {
		if (ownedTasks == null) {
			ownedTasks = new EObjectContainmentEList<Task>(Task.class, this, DagPackage.DAG_SPECIFICATION__OWNED_TASKS);
		}
		return ownedTasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Task> getSortedTasks() {
		// TODO: implement this method to return the 'Sorted Tasks' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
		// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
		
		List<Node> nodeList = new ArrayList<Node>();
		EList<Task> tasks = getOwnedTasks();
		EList<Channel> channels = getOwnedChannels();
		
	    for(int i = 0; i < tasks.size(); i++)
	    {
	    	Node node = new Node();
	    	node.setName(tasks.get(i).getQualifiedName());
	    	nodeList.add(node);
	    }
	    
		Graph dag = new Graph(nodeList);
	    
	    for (int i = 0; i < channels.size(); i++) {
	    	Task task_src = channels.get(i).getSourceTask();
	    	Task task_dst = channels.get(i).getDestTask();
	    	if (task_src == null || task_dst == null) {
	    		continue;
	    	}
	
	    	Node source_node = dag.findNodeByName(task_src.getQualifiedName());
	    	Node dest_node = dag.findNodeByName(task_dst.getQualifiedName());
	    	if (dest_node == null || source_node == null) {
	    		continue;	
	    	}
	    	new Arc(source_node, dest_node);
	    }
		
		Stack<Node> stack = new Stack<Node>();
		if (TopologicalSort.sort(dag, stack)) {
			return new BasicEList<Task>();
		}
		
		EList<Task> sorted_list = new BasicEList<Task>();
		
		while (!stack.isEmpty()) {
			Node n = stack.pop();
			Task task = tasks.get(0);
			for (int i = 0; i < tasks.size(); i++) {
				task = tasks.get(i);
				if (task.getQualifiedName().equals(n.getName())) {
					break;
				}
			}
			
			sorted_list.add(task);
		}
		return sorted_list;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Channel> getOwnedChannels() {
		if (ownedChannels == null) {
			ownedChannels = new EObjectContainmentEList<Channel>(Channel.class, this, DagPackage.DAG_SPECIFICATION__OWNED_CHANNELS);
		}
		return ownedChannels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DagPackage.DAG_SPECIFICATION__OWNED_TASKS:
				return ((InternalEList<?>)getOwnedTasks()).basicRemove(otherEnd, msgs);
			case DagPackage.DAG_SPECIFICATION__OWNED_CHANNELS:
				return ((InternalEList<?>)getOwnedChannels()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DagPackage.DAG_SPECIFICATION__NAME:
				return getName();
			case DagPackage.DAG_SPECIFICATION__OWNED_TASKS:
				return getOwnedTasks();
			case DagPackage.DAG_SPECIFICATION__SORTED_TASKS:
				return getSortedTasks();
			case DagPackage.DAG_SPECIFICATION__OWNED_CHANNELS:
				return getOwnedChannels();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DagPackage.DAG_SPECIFICATION__NAME:
				setName((String)newValue);
				return;
			case DagPackage.DAG_SPECIFICATION__OWNED_TASKS:
				getOwnedTasks().clear();
				getOwnedTasks().addAll((Collection<? extends Task>)newValue);
				return;
			case DagPackage.DAG_SPECIFICATION__OWNED_CHANNELS:
				getOwnedChannels().clear();
				getOwnedChannels().addAll((Collection<? extends Channel>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DagPackage.DAG_SPECIFICATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DagPackage.DAG_SPECIFICATION__OWNED_TASKS:
				getOwnedTasks().clear();
				return;
			case DagPackage.DAG_SPECIFICATION__OWNED_CHANNELS:
				getOwnedChannels().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DagPackage.DAG_SPECIFICATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case DagPackage.DAG_SPECIFICATION__OWNED_TASKS:
				return ownedTasks != null && !ownedTasks.isEmpty();
			case DagPackage.DAG_SPECIFICATION__SORTED_TASKS:
				return !getSortedTasks().isEmpty();
			case DagPackage.DAG_SPECIFICATION__OWNED_CHANNELS:
				return ownedChannels != null && !ownedChannels.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				case DagPackage.DAG_SPECIFICATION__NAME: return DagPackage.NAMED_ELEMENT__NAME;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				case DagPackage.NAMED_ELEMENT__NAME: return DagPackage.DAG_SPECIFICATION__NAME;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

	@Override
	public String getQualifiedName() {
		return getName();
	}
} //DagSpecificationImpl
