

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class main {
	
	//method for scanning the file and creating criminals with using previous datas.
	public static Criminal[] transferCriminals(Criminal[] criminals, File file){

		int i = 0;
		try(Scanner input = new Scanner(file)){
			while(input.hasNext()) {
				int id = input.nextInt();
				String name = input.next();
				int age = input.nextInt();
				String crime = input.next();
				String status = input.next();
				criminals[i] = new Criminal(name, id, age, crime, status);
				i++;
				numberOfCriminals++;
				}
		}
		catch(FileNotFoundException ex) {
			System.out.println("Please check your file path!");
			System.exit(1);
		}
		return criminals;
	}
	
	//saving method, basically it takes all the criminal datas and print them to the file.
	public static void saveCriminals(Criminal[] criminals,File file){
	
		    String text = "";
		    try(PrintWriter output = new PrintWriter(file)){
		    	for(int i = 0; i < numberOfCriminals ; i++){
			        text += String.format("%s %s %d %s %s",
			        		criminals[i].getId(),criminals[i].getName(), criminals[i].getAge(), criminals[i].getCrime(), criminals[i].getStatus());
			        text += "\n";
			    }
		    	output.format(text);	
		    }
		    catch(FileNotFoundException ex) {
		    	System.out.println("Please check your file path!");
		    	System.exit(2);
		    }
	}
	
	// Void Method for visual affect. it is basically slow downs the println, nothing special. Check references for link.
	public static void typewriterAffect(String text,int v) {
		
		for(int i = 0; i < text.length(); i++){
		    System.out.printf("%c", text.charAt(i));
		    try{
		        Thread.sleep(v);//if v = 100 , 0.1s pause between characters
		    }catch(InterruptedException ex){
		        Thread.currentThread().interrupt();
		    }
		}
		System.out.println("");
	}
	
	// Basic print menu method in order to prevent the code from overwriting and make it looks more clear.
	public static void printMenu() {
	
		System.out.println("+----------------------------+");
		System.out.println("|         Main Menu          |");
		System.out.println("+----------------------------+");
		System.out.println("| 1- Show all criminals      |");
		System.out.println("| 2- Search for criminal     |");
		System.out.println("| 3- Add criminal record     |");
		System.out.println("| 4- Change criminal record  |");
		System.out.println("| 5- Delete criminal         |");
		System.out.println("| 6- Save and Exit           |");
		System.out.println("+----------------------------+");
		
	}
	
	// Void Method with one Criminal class array parameter for printing all the array elements in given array parameter.
	public static void printAllCriminals(Criminal[] criminals) {
		System.out.print("                         ");
		typewriterAffect("LIST OF ALL CRIMINALS",100);
		// printf preferred in order to print the criminals in table
		System.out.println("---------------------------------------------------------------------------");
	    System.out.printf("%5s %15s %15s %20s %15s\n", "ID", "NAME", "AGE", "CRIME", "STATUS");
	    System.out.println("---------------------------------------------------------------------------");
	    // Basic for loop for reaching every element in the given array one by one.
		// initial value i goes up to numberOfCriminals. And printing all the array elements with using getter methods.
	    for (int i = 0 ; i < numberOfCriminals ; i++) {
	    	System.out.format("%5s %15s %15s %20s %15s",
	   				 criminals[i].getId(),criminals[i].
	   				 getName(),criminals[i].getAge(), criminals[i].
	   				 getCrime(), criminals[i].getStatus());
				     System.out.println();
		    }
	    System.out.println("---------------------------------------------------------------------------");
		}
		
	// Basic void method with two parameter for printing just one element in array.No differences from printAllCriminals
	public static void printOneCriminal(Criminal[] criminals, int index) {
		System.out.println("---------------------------------------------------------------------------");
	    System.out.printf("%5s %15s %15s %20s %15s\n", "ID", "NAME", "AGE", "CRIME", "STATUS");
	    System.out.println("---------------------------------------------------------------------------");
	    	System.out.format("%5s %15s %15s %20s %15s",
   				 criminals[index].getId(),criminals[index].
   				 getName(),criminals[index].getAge(), criminals[index].
   				 getCrime(), criminals[index].getStatus());
			     System.out.println();

	}
	
	// Searching method for finding the criminal with given name and id in given array.
	public static int findCriminalIndex(Criminal[] criminals, String name, int id) {
		
		int indexOfCriminal = 0;
		// This for loop searches every element in array and if the name and id matches, it returns index of the match.
		for (int i = 0 ; i < numberOfCriminals ; i++) {
			if ( criminals[i].getName().equalsIgnoreCase(name) && criminals[i].getId() == id) {
				indexOfCriminal = i;
			}
		}
		return indexOfCriminal;
	}

	// Method for deleting the array element with using given array and given index. It returns new array in Criminal class.
	public static Criminal[] deleteCriminal(Criminal[] criminals, int index) {
		Criminal[] proxyArray = new Criminal[criminals.length - 1]; 
		
	        // copy all the elements in the original to proxy array except the one at index 
	        for (int i = 0, k = 0; i < criminals.length; i++) { 
	 
	            // check if index is crossed, continue without copying 
	            if (i == index) { 
	                continue; 
	            } 
	 
	            // else copy the element
	            proxyArray[k++] = criminals[i]; 
	        }
	        return proxyArray;
	}
	
	// Boolean method for checking that criminal exist or not with specific id.
	public static boolean hasCriminal(Criminal[] criminal,int id) {
		boolean b = false;
		
		// Check every element in array, if some of the ID's matches with given ID, set boolean true.
		for(int i = 0 ; i < numberOfCriminals ; i++) {
			if(criminal[i].getId() == id)
				b = true;
		}
		return b;
	}
	
	// static integer value for number of criminals.
	static int numberOfCriminals = 0;
	
	public static void main(String args[]) {
		
		// invoking Scanner object
		Scanner sc = new Scanner(System.in);
		
		// file object, please copy your file path when you get database.txt
		File file = new File("C:\\Users\\user\\eclipse-workspace\\criminal-database-manangement-system\\src\\database");
		
		// Array in Criminal class for holding the objects and computing them.
		Criminal[] criminals = new Criminal[200];
		criminals = transferCriminals(criminals,file);
		
		// HOW INITIAL 100 RANDOM CRIMINALS CREATED?
//		
//		// Array for criminal's id numbers. And assigning random value for each of one.
//		int[] ids = new int[100];
//		for (int i = 0 ; i < ids.length ; i++) {
//			ids[i] = i+1;
//		}
//		
//		// Shuffle
//		for (int i = 0 ; i < ids.length ; i++) {
//			int j = (int)(Math.random() * ids.length);
//			
//			int temp = ids[i];
//			ids[i] = ids[j];
//			ids[j] = temp;
//		}
//		
//		// Criminal characteristics in string arrays.
//		String[] status = {"In-Jail","WANTED","In-Jail","In-Jail","In-Jail","In-Jail","In-Jail"};
//		
//		String[] crimes = {"Abduction","Arson","Assault","Burglary",
//						"Drugs","Firearms","Fraud","Vehicle-related",
//						"Murder","Miscellaneous","Robbery","Sex-offence"};
//		
//		String[] names = {"Jeremy","Jhonny","Randy","Terry",
//				"Bobby","Billy","Timothy","Jason","Jerry","Jashua",
//				"Justin","Jose","Brandon","Jesse","Christoper","Roy",
//				"Juan","Bryan","Larry","Michael"};
//		
//		// Array for criminal's ages. And assigning random value for each of one.
//		int[] ages = new int[100];
//		for (int i =  0 ; i < ages.length ; i++) {
//			ages[i] =  (int)(Math.random() * 42) + 18;
//		}
//		
//		// For loop for matching random data's with each elements in Criminal array.
//		for (int i = 0 ; i < 100 ; i++) {
//			int random_crimes = (int)(Math.random() * 12);
//			int random_names = (int)(Math.random() * 20);
//			int random_ages = (int)(Math.random() * 50);
//			int random_status= (int)(Math.random() * 7);
//			
//			// Create criminals
//			criminals[i] = new Criminal(names[random_names],
//					ids[i],ages[random_ages],crimes[random_crimes],
//					status[random_status]);
//		}
		
		//ID and PASSWORD check and same visual stuffs.
		typewriterAffect("        WELCOME TO FBI CRIMINAL DATABASE SYSTEM        ",100);
		typewriterAffect("LOADING ",100);
		typewriterAffect("■ ■ ■ ■ ■ ■ %100",200);
		System.out.println("----------------------------------");
		
		// Scanners inside for loop for checking the id and password.
		while(true) {
		typewriterAffect("FEDERAL ID: ",100);
		int federalId = sc.nextInt();
		typewriterAffect("PASSWORD: ",100);
		int password = sc.nextInt();
		System.out.println("----------------------------------");
		typewriterAffect("Checking",100);
		typewriterAffect("........\n",500);
		
		// if both are true, let the user in
		if (federalId == 150318000 && password == 12345) {
			typewriterAffect("ID and PASSWORD approved!",100);
			System.out.println("\n");
			break;
		}
		// else ask again
		else 
			typewriterAffect("Try again!",100);
			System.out.println("----------------------------------");
			System.out.println("\n");	
		}
		typewriterAffect(".......",100);
		
		// while loop for main menu
		while(true) {
			printMenu();
			
			// Ask for option input to user
			System.out.println("Enter option: ");
			int  option = sc.nextInt();
			
			// OPTION 1 - SHOW ALL CRIMINALS
			if (option == 1) {
				typewriterAffect(".......",100);
				
				// invokes printAllCriminals() method
				printAllCriminals(criminals);
				typewriterAffect(".......",100);
				
				// while loop for switching between menu and sub menu
				while(true) {
					System.out.println("Enter 9 to back");
					int option2 = sc.nextInt();
					if (option2 == 9) 
						break;
					else 
						typewriterAffect("Please enter again! (unvalid entry)",100);
					}
			}
			
			// OPTION 2 - SEARCH FOR CRIMINAL
			else if (option == 2) {
				typewriterAffect(".......",100);
				outerloop:
				while(true) {
					
				// Get the criminals datas in order to search him.
				typewriterAffect("Enter criminal's Name and ID",100);
				typewriterAffect("Name: ",100);
				String name = sc.next();
				typewriterAffect("ID: ",100);
				int id = sc.nextInt();
				typewriterAffect("Searching ......",100);
				
				// invokes findCriminalIndex() method for finding the index of criminal
				int index = findCriminalIndex(criminals, name, id);
				
				// if it matches, show the criminal
				if (name.equalsIgnoreCase(criminals[index].getName()) && id == criminals[index].getId()) {
					typewriterAffect("   *** CRIMINAL FOUND *** ",100);
					
					// invokes printOneCriminal() method.
					printOneCriminal(criminals, index);
					System.out.println("\n");
				}
				// else, print that there is no such criminal.
				else {
					typewriterAffect("   *** COULDNT FOUND *** ",100);
					System.out.printf("There is no criminal which has Name <%s> and ID <%d>",name,id);
					System.out.println("\n");
				}
				
				// while loop for switching between menu and sub menu
				while(true) {
					typewriterAffect("Enter 9 to go main menu 0 to search another criminal",50);
					int answer = sc.nextInt();
					if(answer == 9)
						break outerloop;
					else if(answer == 0) 
						break ;
					else
						System.out.println("Please enter 9 or 0");
					}
				}
				
			}
			// OPTION 3 - ADD CRIMINAL
			else if (option == 3) {
				typewriterAffect(".......",100);
				
				// Get the criminal data in order to create new one.
				outerloop:
				while(true) {
				System.out.println("Enter criminal records.");

				System.out.println("NAME : ");
				String newName = sc.next();
			
				System.out.println("AGE : ");
				int newAge = sc.nextInt();
				
				System.out.println("CRIME : ");
				String newCrime = sc.next();
				
				// criminal IDs can not be same. in order to prevent this, there is a loop and if statements
				// that checks that the given id whether already taken or not.
					int newId = 0;
					while(true) {
						System.out.println("ID : ");
						newId = sc.nextInt();
						typewriterAffect(".......",100);

						// invokes hasCriminal() method which is gives boolean type return.
						// if there is criminal which has same ID, enter your ID again.
						if(hasCriminal(criminals,newId) == true) {
							System.out.println("Sorry ID " + newId + " has been given to another criminal.");
							typewriterAffect(".......",100);
						}
						
						// if id is unique, let the user go.
						else
							break;
					}
				System.out.println("STATUS : ");
				String newStatus = sc.next();
				
				// create the criminal at index at numberOfCriminals with using Constructor of Criminal class 
				criminals[numberOfCriminals] = new Criminal(newName, newId, newAge, newCrime, newStatus);
				
				// increase the numberOfCriminals by one.
				numberOfCriminals++;
				typewriterAffect("Criminal record successfully added to the system.",50);
				
				//  loop for switching between menu and sub menu
					while(true) {
						typewriterAffect("Enter 9 to go main menu 0 to add another criminal",50);
						int answer = sc.nextInt();
						if(answer == 9)
							break outerloop;
						else if(answer == 0) 
							break ;
						else
							System.out.println("Please enter 9 or 0");
						}
					}
			}
			
			// OPTION 4 - CHANGE CRIMINAL RECORDS
			else if (option == 4) {
				typewriterAffect(".......",100);
				System.out.println("Enter the ID(2d) of criminal whose records you want to change.");
				outerloop:
				while(true) {
					
				// Get the criminal's data in order to find him.
				typewriterAffect("Name: ",100);
				String name = sc.next();
				typewriterAffect("ID: ",100);
				int id = sc.nextInt();
				typewriterAffect("Searching ......",100);
				
				// invokes findCriminalIndex() method for finding the index of criminal
				int index = findCriminalIndex(criminals, name, id);
				
				// if it matches  show the criminal
				if (name.equalsIgnoreCase(criminals[index].getName()) && id == criminals[index].getId()) {
					typewriterAffect("   *** CRIMINAL FOUND *** ",100);
					
					// invokes printOneCriminal() method.
					printOneCriminal(criminals, index);
					System.out.println("\n");
					
					// general loop in order to provide multiple operations
					// without going back and forth between menu and sub menus.
					innerloop:
					while(true) {
					typewriterAffect("What you want to change about criminal?",100);
					System.out.println("[ID, NAME, AGE, CRIME, STATUS]");
					String input = sc.next();
					if(input.equalsIgnoreCase("id")) {
						
						// Criminal IDs can not be same. in order to prevent this there is a loop and if statements
						// that checks that id is smaller than 50 or id have already taken or not.
							int newId = 0;
							while(true) {
								System.out.println("Please enter new ID");
								newId = sc.nextInt();
								typewriterAffect(".......",100);
					
								// invokes hasCriminal() method which is gives boolean type return.
								if(hasCriminal(criminals,newId) == true) {
									System.out.println("Sorry ID " + newId + " has been given to another criminal.");
									typewriterAffect("Enter again",100);
								}
								
								// if id is unique, let the user go.
								else
									break;
							}
						
						// change the criminal data with new one, using Criminal class's setter method.
						criminals[index].setId(newId);
						typewriterAffect("Criminal ID successfully changed.",100);
						typewriterAffect("......",100);
						break innerloop;
					}
				else if(input.equalsIgnoreCase("name")) {
						System.out.println("Please enter new NAME");
						String newName = sc.next();
						
						// change the criminal data with new one, using Criminal class's setter method.
						criminals[index].setName(newName);
						typewriterAffect("Criminal NAME successfully changed.",100);
						typewriterAffect("......",100);
						break innerloop;
					}
				else if(input.equalsIgnoreCase("age")) {
						System.out.println("Please enter new AGE");
						int newAge = sc.nextInt();
						
						// change the criminal data with new one, using Criminal class's setter method.
						criminals[index].setAge(newAge);
						typewriterAffect("Criminal AGE successfully changed.",100);
						typewriterAffect("......",100);
						break innerloop;
					}
				else if(input.equalsIgnoreCase("crime")) {
						System.out.println("Please enter new CRIME");
						String newCrime = sc.next();
						
						// change the criminal data with new one, using Criminal class's setter method.
						criminals[index].setCrime(newCrime);
						typewriterAffect("Criminal CRIME successfully changed.",100);
						typewriterAffect("......",100);
						break innerloop;
					}
				else if(input.equalsIgnoreCase("status")) {
						System.out.println("Please enter new STATUS");
						String newStatus = sc.next();
						
						// change the criminal data with new one, using Criminal class's setter method.
						criminals[index].setStatus(newStatus);
						typewriterAffect("Criminal STATUS successfully changed.",100);
						typewriterAffect("......",100);
						break innerloop;
					}
				else 
					typewriterAffect("Enter again",100);
					}
				}
				// else, print that there is no such criminal.
				else {
					typewriterAffect("   *** COULDNT FOUND *** ",100);
					System.out.printf("There is no criminal which has Name <%s> and ID <%d>",name,id);
					System.out.println("\n");
				}
				
				//  loop for switching between menu and sub menu
				while(true) {
					typewriterAffect("Enter 9 to go main menu 0 to search another criminal",100);
					int answer = sc.nextInt();
					if(answer == 9)
						break outerloop;
					else if(answer == 0) 
						break ;
					else
						System.out.println("Please enter 9 or 0");
					}
				}
		
			}
			
			// OPTION 5 - DELETE CRIMINAL
			else if (option == 5) {
				typewriterAffect(".......",100);
				outerloop:
				while(true) {
					
				// Get the criminals data in order to find him and delete him.
				System.out.println("Enter criminal's Name and ID( last two digits )");
				typewriterAffect("Name: ",100);
				String name = sc.next();
				typewriterAffect("ID: ",100);
				int id = sc.nextInt();
				typewriterAffect("Searching ......",100);
				
				// invoke findCriminalIndex() method to find the criminal.
				int index = findCriminalIndex(criminals, name, id);
				
				// if the inputs match.
				if (name.equalsIgnoreCase(criminals[index].getName()) && id == criminals[index].getId()) {
					typewriterAffect("   *** CRIMINAL FOUND *** ",100);
					
					// Print the found criminal with printOneCriminal() method.
					printOneCriminal(criminals, index);
					System.out.println("\n");
					typewriterAffect(".......",100);
					
					// Ask to user that if he sure or not, in a while loop.
					while(true) {
					System.out.println("Enter <delete> if you sure. <no> to forgo ");
					String answer = sc.next();
					if (answer.equalsIgnoreCase("delete")) {
						
						// if user types delete, invoke deleteCriminal() method and change the new array with the old array.
						criminals = deleteCriminal(criminals, index);
						
						// decrease numberOfCriminals by one.
						numberOfCriminals--;
						typewriterAffect(".......",100);
						typewriterAffect("Criminal successfully deleted.",100);
						break;
					}
					
					// if enters no, do not delete the criminal and break the while loop.
					else if (answer.equals("no")) {
						typewriterAffect(".......",100);
						typewriterAffect("Criminal not deleted.",100);
						break;
					}
					else
						System.out.println("Please enter <delete> or <no> !");
					}
	
				}
				// if the inputs doesn't match.
				else {
					System.out.printf("There is no criminal which has Name <%s> and ID <%d>",name,id);
					System.out.println("\n");
				}
				
				// loop for switching between menu and sub menu.
				while(true) {
					typewriterAffect("Enter 9 to go main menu 0 to delete anohter criminal",100);
					int answer = sc.nextInt();
					if(answer == 9)
						break outerloop;
					else if(answer == 0) 
						break ;
					else
						System.out.println("Please enter 9 or 0");
					}
				}	
			}
			// OPTION 6 - SAVE and EXIT
			else if (option == 6) {
				typewriterAffect("All criminals successfully saved.",100);
				System.out.println("#StayHome, #StaySafe");
				saveCriminals(criminals,file);
				break;
			}
			// wrong entry, try again.
			else
				typewriterAffect("Please enter again! (1,2,3,4,5 or 6!)",100);
		}
	}
}
