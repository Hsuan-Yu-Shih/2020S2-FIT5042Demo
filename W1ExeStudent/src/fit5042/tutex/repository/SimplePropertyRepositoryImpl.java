/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.repository;

import fit5042.tutex.repository.entities.Property;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

/**
 * TODO Exercise 1.3 Step 2 Complete this class.
 * 
 * This class implements the PropertyRepository class. You will need to add the keyword
 * "implements" PropertyRepository. 
 * 
 * @author Junyang
 * @author Hsuan-Yu Shih
 */
public class SimplePropertyRepositoryImpl implements PropertyRepository{
	
	private ArrayList<Property> managedList;
	
    public SimplePropertyRepositoryImpl() {
        managedList = new ArrayList<>();
    }
    
    public void addProperty(Property property) {
    	managedList.add(property);
    }
    
    public List<Property> getAllProperties(){
    	List<Property> mList = managedList;
    	sortListById(mList);
    	return mList;
    }
    
    public Property searchPropertyById(int id) {
    	for (Property p : managedList) {
    		if (id == p.getId()) {
    			return p;
    		}
    	}
    	return null;
    }
    
    private void sortListById(List<Property> list) {
    	Collections.sort(list, new Comparator<Property>() {
    		public int compare(Property p1, Property p2) {
    			return p1.getId() - p2.getId();
    		}
    	});
    }
}
