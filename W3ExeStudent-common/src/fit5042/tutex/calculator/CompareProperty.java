package fit5042.tutex.calculator;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.Remote;

import fit5042.tutex.repository.entities.Property;

/**
 * This is just an interface so there is no body in each method
 * I don't know the purpose that we need to add compareProperty create
 *
 */
@Remote
public interface CompareProperty  {
	
    public void addProperty(Property property);

    public int bestPerRoom();

    public void removeProperty(Property property);

    CompareProperty create() throws CreateException, RemoteException;
}
