package fit5042.tutex.calculator;

import fit5042.tutex.repository.entities.Property;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.CreateException;
import javax.ejb.Stateful;


@Stateful
public class ComparePropertySessionBean implements CompareProperty{
	
	private List<Property> propertyList;
	
	public ComparePropertySessionBean(){
		propertyList = new ArrayList<>();
	}
	
	/**
	 * override the addProperty method in compareProperty
	 * add the property given into property list
	 */
	@Override
	public void addProperty(Property property) {
		propertyList.add(property);
	}
	
	/**
	 * Override the bestPerRoom method in compareProperty
	 * If there is nothing in the list, return 0
	 * Get the first property's room ID, number of rooms and price
	 * Add the ID for the best room ID and use price divide the number of rooms to get the price of the best per room
	 * 
	 * Compare the first one with the others
	 * If the price per room of the property is lower than the best one, replace it and record this property ID
	 * Return the best room ID
	 */
	@Override
	public int bestPerRoom() {
		if (propertyList.size() == 0) {
			return 0;
		}
		int bestRoomID = propertyList.get(0).getPropertyId();
		int numberRooms = propertyList.get(0).getNumberOfBedrooms();
		double price = propertyList.get(0).getPrice();
		double bestPerRoom = price / numberRooms;
		for (Property p : propertyList) {
			numberRooms = p.getNumberOfBedrooms();
			price = p.getPrice();
			if (price / numberRooms < bestPerRoom) {
				bestPerRoom = price / numberRooms;
				bestRoomID = p.getPropertyId();
			}
		}		
		return bestRoomID;
	}
	
	/**
	 * Get each property from property list
	 * compare it with the property given
	 * remove the matched property from the property list and break the loop
	 */
	@Override 
	public void removeProperty(Property property) {
		for (Property p : propertyList) {
			if (p.getPropertyId() == property.getPropertyId() ) {
				propertyList.remove(p);
				break;
			}
		}		
	}
    
    /**
    *
    * I am not sure why do we need to create a new compare property  and ejb here
    * 
    * @return 
    * @throws javax.ejb.CreateException
    * @throws java.rmi.RemoteException
    */
   @PostConstruct
   public void init() {
	   propertyList=new ArrayList<>();
   }

   public CompareProperty create() throws CreateException, RemoteException {
       return null;
   }

   public void ejbCreate() throws CreateException {
   }
	
}
