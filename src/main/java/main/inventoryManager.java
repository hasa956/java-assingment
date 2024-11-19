
package main;
import java.awt.List;

import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import main.Item;
import main.Supplier;
  // Import the FileWriter class
  // Import the FileWriter class

/**
 *
 * @author Mahmoud Farah
 */
public class inventoryManager {
    Item item=new Item();
    Supplier sp=new Supplier();
    public String name;
    private Item[] items;
    
    
    
    
    
    
    protected void Addsupplier(int supplierid,String suppliername,int itemsupplied) throws IOException{
      
        
       
       
            String contentToAdd= supplierid +" "+suppliername +" "+itemsupplied + "\n";


            BufferedWriter write=new BufferedWriter(new FileWriter("Allsupplier.txt"));


            try {
                BufferedWriter writer=new BufferedWriter(new FileWriter("Allsupplier.txt")); // 'true' enables append mode
                writer.append(contentToAdd);

                writer.close();  // Close the writer
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            
            
        }
    
    public String[] findSupplierByID(String supplierID) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Allsupplier.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3 && parts[0].equals(supplierID)) {
                    return new String[] {parts[0], parts[1], parts[2] }; // Return name and items if ID matches
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Return null if no match is found
    }
    
     public boolean deleteSupplier(String supplierID, String supplierName, String itemsSupplied) {
        var lines = new ArrayList<>();  // List to hold the file content
        boolean found = false;

        // Read all lines from the file and add to the list, excluding the line with the matching data
        try (BufferedReader reader = new BufferedReader(new FileReader("Allsupplier.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                // Debug: Print each line and split parts to verify the format
                System.out.println("Read line: " + line); 
                
                if (parts.length == 3) {
                    String fileSupplierID = parts[0].trim();
                    String fileSupplierName = parts[1].trim();
                    String fileItemsSupplied = parts[2].trim();
                    
                    // Debug: Print values from the file and the input to check if they match
                    System.out.println("Comparing to: ID: " + fileSupplierID + " Name: " + fileSupplierName + " Items: " + fileItemsSupplied);
                    
                    // Check if all parts match 
                    if (fileSupplierID.equalsIgnoreCase(supplierID.trim()) &&
                        fileSupplierName.equalsIgnoreCase(supplierName.trim()) &&
                        fileItemsSupplied.equalsIgnoreCase(itemsSupplied.trim())) {
                        found = true;  // Mark as found, but don't add this line (delete it)
                        System.out.println("Supplier found .");
                    } else {
                        lines.add(line);  // Keep the other lines
                    }
                } else {
                    // In case the file contains lines that don't follow the expected format
                    System.out.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // If the supplier was found, overwrite the file with the remaining lines
        if (found) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("Allsupplier.txt"))) {
                for (var line : lines) {
                    writer.println(line);  // Write remaining lines back to the file
                }
                System.out.println("Supplier deleted and file updated.");
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Supplier not found.");
        }

        return found;  // Return true if supplier was found and deleted, false otherwise
    }
    protected void AddItem(int itemid,int supplierid,String itemname,int itemReorderLevel,String category) throws IOException{
      
        
       
       
            String contentToAdd= itemid +" "+supplierid +" "+itemname+" "+" "+itemReorderLevel+" "+" "+category+ "\n";


            //BufferedWriter write=new BufferedWriter(new FileWriter("Allitems.txt"));


            try {
                BufferedWriter writers=new BufferedWriter(new FileWriter("Allitems.txt")); // 'true' enables append mode
                writers.append(contentToAdd);

                writers.close();  // Close the writer
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            
            
        }
    protected void savestocks(int itemid,int supplierid,String itemname,int itemReorderLevel,String category,int quantity) throws IOException{
      
        
       
       
            String contentToAdd= itemid +" "+supplierid +" "+itemname+" "+itemReorderLevel+" "+category+" "+quantity+"\n";


            //BufferedWriter write=new BufferedWriter(new FileWriter("Allitems.txt"));


            try {
                BufferedWriter writers=new BufferedWriter(new FileWriter("allstocks.txt")); // 'true' enables append mode
                writers.append(contentToAdd);

                writers.close();  // Close the writer
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            
            
        }
    
    public boolean updateStock(int itemID,int supplierid,String itemname,int itemReorderLevel,String category, int Quantity) {
        if (Quantity <= 0) {
            System.out.println("Invalid quantity.");  // Output message if invalid
            return false; // Return false if the quantity is invalid
        }

        // Loop through items to find the item by ID
        for (Item item : items) {  // Iterating over the properly initialized items list
            if (itemID==item.getItemid()) {  // Compare itemID
                // Update the stock by adding the additional quantity
                item.setItemStocks(item.getItemStocks() + Quantity);  // Add stock directly
                System.out.println("Updated stock for item: " + item.getItemname());
                System.out.println("New stock for " + item.getItemname() + ": " + item.getItemStocks()); // Output new stock value

                // Save the updated inventory to a new file after updating the stock
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("generalitemsstocks.txt"))) {
                    // Loop through the list of items and write each item's details to the file
                    for (Item i : items) {
                        writer.write(i.getItemid() + " " + i.getSupplieid() + " " + i.getItemname() +" " + i.getItemReorderLevel() + " " +i.getCategory()+" "+ i.getItemStocks() +"\n");
                    }
                    System.out.println("Inventory saved to file: " );
                } catch (IOException e) {
                    System.out.println("Error saving inventory to file.");
                    e.printStackTrace();
                }

                return true; // Return true if stock is updated
            }
        }

        // If item is not found
        System.out.println("Item not found."); // Output if item not found
        return false; // Return false if item is not found
    }
//    public String[] findItemID(String itemid) {
//        try (BufferedReader reader = new BufferedReader(new FileReader("Allitems.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                // Split by space or tab to ensure proper matching
//                String[] parts = line.split("\\s+");
//
//                // Check if the split line contains enough parts (5 or 6 elements depending on your file structure)
//                if (parts.length == 5 && parts[0].equals(itemid)) {
//                    // Return the item details as an array
//                    return new String[] {parts[0], parts[1], parts[2], parts[3], parts[4]}; 
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    return null;  // Return null if no match is found
   public String[] finditemByID(int itemid) {
    try (BufferedReader reader = new BufferedReader(new FileReader("Allitems.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            // Log the line for debugging
            System.out.println("Reading line: " + line);
            
            // Split using regular expression to handle multiple spaces
            String[] parts = line.trim().split("\\s+"); // "\\s+" matches one or more spaces
            
            // Debugging: log the parts after split
            System.out.println("Parsed parts: " + Arrays.toString(parts));
            
            if (parts.length == 5) { // Ensure there are exactly 5 parts
                if (Integer.parseInt(parts[0]) == itemid) {
                    return parts; // Return the parts if the ID matches
                }
            } else {
                System.out.println("Line does not have exactly 5 parts: " + Arrays.toString(parts));
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (NumberFormatException e) {
        System.err.println("Error: Invalid item ID format in file - " + e.getMessage());
    }
    return null; // Return null if no match is found
}
}
   
     

     
    
    
    
   

    
  
        
        
     
    
    
        
    
  



