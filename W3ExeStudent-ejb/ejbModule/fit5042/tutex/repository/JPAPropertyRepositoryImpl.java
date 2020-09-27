package fit5042.tutex.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;

import fit5042.tutex.repository.constants.CommonInstance;
import fit5042.tutex.repository.entities.ContactPerson;
import fit5042.tutex.repository.entities.Property;

@Stateless
public class JPAPropertyRepositoryImpl implements PropertyRepository {
	private ArrayList<Property> propertyList;
	
	public JPAPropertyRepositoryImpl() {
    	propertyList = new ArrayList<Property>();
    	this.initialisePropertyList();
    }
	
	public void initialisePropertyList() {
    	propertyList.clear(); //like remove the old table each time the user invoke the system
    	
    	propertyList.add(CommonInstance.PROPERTY_FIRST);
    	propertyList.add(CommonInstance.PROPERTY_SECOND);
    	propertyList.add(CommonInstance.PROPERTY_THIRD);
    	propertyList.add(CommonInstance.PROPERTY_FOURTH);
    }

	public ArrayList<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(ArrayList<Property> propertyList) {
		this.propertyList = propertyList;
	}
	
	public void removeProperty(int propertyId) {
    	for (Property p : propertyList) {
    		if (p.getPropertyId() == propertyId) {
    			propertyList.remove(p);
    			break;
    		}
    	}
    	
    }
    
    public void addProperty(Property property) {
    	propertyList.add(property);
    }
    
    public void editProperty(Property property) {
    	for (Property p : propertyList) {
    		int id = property.getPropertyId();
    		if (p.getPropertyId() == id) {
    			propertyList.set(id, property);
    			break;
    		}
    	}
    }

	public int getPropertyId() {
		return propertyList.get(propertyList.size() - 1).getPropertyId();
	}
	
	public Property searchPropertyById(int propertyId) {
		for (Property p : propertyList) {
    		if (p.getPropertyId() == propertyId) {
    			return p;
    		}
    	}
		return null;
	}
	
	
	
	
	/**
	 * Set is to ignore the duplicate contact person in propertyList
	 * Add the unique contact person into list and return
	 * 
	 * @return List<ContactPerson>
	 */
	public List<ContactPerson> getAllContactPeople() {
		Set<ContactPerson> contactPersonSet = new HashSet<>();
		List<ContactPerson> contactPersonList = new ArrayList<>();
		
		for (Property p : propertyList) {
			contactPersonSet.add(p.getContactPerson());
		}
		for (ContactPerson cp : contactPersonSet) {
			contactPersonList.add(cp);
		}		
		return contactPersonList;
	}
	
	/**
	 * Get each property in property list, and then get its contact person
	 * Compare it with the contact person we want to search
	 * Add the matched properties into property set and return 
	 * 
	 * @return Set<Property>
	 * @param ContactPerson contactPerson 
	 */
	public Set<Property> searchPropertyByContactPerson(ContactPerson contactPerson) {
		Set<Property> propertySet = new HashSet<>();
		
		for (Property p : propertyList) {
			if (p.getContactPerson().equals(contactPerson)) {
				propertySet.add(p);
			}
    	}
		
		return propertySet;
	}
	
	/**
	 * Get each property in property list, and then get its budget
	 * Find which is lower than the budget the client gave
	 * Add the matched properties into property set and return 
	 * 
	 * @return List<Property>
	 * @param Double budget 
	 */
	public List<Property> searchPropertyByBudget(double budget) {
		List<Property> properties = new ArrayList<>();
		
		for (Property p : propertyList) {
			if (p.getPrice() <= budget) {
				properties.add(p);
			}
		}
		
		return properties;
    }

}
