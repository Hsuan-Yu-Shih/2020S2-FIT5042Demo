package fit5042.tutex;

import fit5042.tutex.repository.PropertyRepository;
import fit5042.tutex.repository.PropertyRepositoryFactory;
import fit5042.tutex.repository.entities.Property;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;	

/**
 *
 * TODO Exercise 1.3 Step 3 Complete this class. Please refer to tutorial instructions.
 * This is the main driver class. This class contains the main method for Exercise 1A
 * 
 * This program can run without the completion of Exercise 1B.
 * 
 * @author Junyang
 * @author Hsuan-Yu Shih
 */
public class RealEstateAgency {
    private String name;
    private final PropertyRepository propertyRepository;

    public RealEstateAgency(String name) throws Exception {
        this.name = name;
        this.propertyRepository = PropertyRepositoryFactory.getInstance();
    }
    
    public int askID() {
    	int id = -1;
    	try (Scanner input = new Scanner(System.in)) {
			System.out.println("Enter the ID of the property you want to search: ");
			try {
				id = input.nextInt();
			}catch(Exception e) {
				System.out.print("The ID must be integer!");
			}
		}
    	return id;
    }
    
    // this method is for initializing the property objects
    // complete this method
    public void createProperty() {
        Property property1 = new Property(1, "24 Boston Ave, Malvern East VIC 3145, Australia", 2, 150, 420000);
        Property property2 = new Property(2, "11 Bettina St, Clayton VIC 3168, Australia", 3, 352, 360000);
        Property property3 = new Property(3, "3 Wattle Ave, Glen Huntly VIC 3163, Australia", 5, 800, 650000);
        Property property4 = new Property(4, "3 Hamilton St,Bentleigh VIC 3204, Australia", 2, 170, 435000);
        Property property5 = new Property(5, "82 Spring Rd, Hampton East VIC 3188, Australia", 1, 60, 820000);
		
        try {
        	propertyRepository.addProperty(property1);
        	propertyRepository.addProperty(property2);
        	propertyRepository.addProperty(property3);
        	propertyRepository.addProperty(property4);
        	propertyRepository.addProperty(property5);
        	System.out.println("5 properties added successfully");
        }catch(Exception e) {
        	System.out.println("Fail to add properties");
        }
    }
    
    // this method is for displaying all the properties
    // complete this method
    public void displayProperties() throws Exception {
        for (Property p : propertyRepository.getAllProperties()) {
        	System.out.println(p.toString());
        }
    }
    
    // this method is for searching the property by ID
    // complete this method
    public void searchPropertyById() throws Exception {
        int id = askID();
        Property property = propertyRepository.searchPropertyById(id);
        System.out.println(property.toString());
    }
    
    public void run() throws Exception {
        createProperty();
        System.out.println("********************************************************************************");
        displayProperties();
        System.out.println("********************************************************************************");
        searchPropertyById();
    }
    
    public static void main(String[] args) {
        try {
            new RealEstateAgency("FIT5042 Real Estate Agency").run();
        } catch (Exception ex) {
            System.out.println("Application fail to run: " + ex.getMessage());
        }
    }
}
