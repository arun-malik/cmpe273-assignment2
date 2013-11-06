/**
 * 
 */
package edu.sjsu.cmpe.procurement.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Maps;

import edu.sjsu.cmpe.procurement.domain.LostInventory;

/**
 * @author malik
 *
 */
public class LostInventoryDto {
	
	public static int key = 0;

	private final static Map<Integer, LostInventory> lostBooks = Maps.newLinkedHashMap();
	private final static List<LostInventory> newBooks = new ArrayList<LostInventory>();
	private final static Pattern decimalPattern = Pattern.compile("(\\d+)");
	private final static Pattern libraryNamePattern = Pattern.compile("[a-zA-Z]*-*[a-zA-Z]*");
	private  Matcher patternMatcher;
	

	public static int getKey() {
		return lostBooks.size();
	}
	
	public static int generateKey() {
		return key = lostBooks.size() + 1;
	}
	
	 public static List<LostInventory> getLostInventories() {
	     List<LostInventory> bookList = new ArrayList<LostInventory>(lostBooks.values());
	     return bookList;
    }
	 
	 public static List<Integer> getLostBooksISBN() {
	     List<LostInventory> bookList = new ArrayList<LostInventory>(lostBooks.values());
	     List<Integer> lostbooksISBN =  new ArrayList<Integer>();
	     
	     for(LostInventory item : bookList ){
	    	 lostbooksISBN.add(item.getBookISBN());  
	    	}
	     
	     return  lostbooksISBN;
    }
	 
	 public static void emptyInventory() {
	     lostBooks.clear();
    }
	 
	 public static int lostInventoryCount() {
	     return lostBooks.size();
    }
	 
	 @SuppressWarnings("SaveDataWarning Supress")
	public  void saveDatatoLostInventory(List<String> msgFromQueue)
	 {
		 
		 for(String item : msgFromQueue ){
			 
			 String libraryName="";
			 String bookISBN="";
			 	LostInventory lostbook = new LostInventory();
			 	
			 	patternMatcher =  decimalPattern.matcher(item);
				while(patternMatcher.find())
				{
					bookISBN += patternMatcher.group();
				}
				
				lostbook.setBookISBN(Integer.parseInt(bookISBN.toString()));
				
				
				patternMatcher =  libraryNamePattern.matcher(item);
				while(patternMatcher.find())
				{
					libraryName += patternMatcher.group();
				}
				lostbook.setLibraryName(libraryName.toString());
				
				int lostkey = generateKey();
				lostbook.setLostInventoryKey(lostkey);
				lostBooks.put(lostkey, lostbook);
				
				} 
	 }

	/**
	 * @return the newbooks
	 */
	public static List<LostInventory> getNewbooks() {
		return newBooks;
	}
	
	public static void setNewbooks(LostInventory booksFromPublisher) {
		newBooks.add(booksFromPublisher);
	}
	 
	 
	 
	 
}
