/**
 * LittleWordsCheater Class and main
 * @author Nic Manoogian
 */
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class LittleWordsCheater
{
	// Dictionary of words
	private HashSet<String> dict;
	// List of stubs
	private ArrayList<String> stubs;

	//Max word size
	public static int MAXSIZE = 7;
	
	/**
	 * Constructs a Cheater
	 */
	public LittleWordsCheater()
	{
		stubs = new ArrayList<String>();
		dict = new HashSet<String>();
	}

	/**
	 * Gets all possible permutations from the given stubs
	 * @param s ArrayList of String stubs
	 * @return a ArrayList of all permutations
	 */
	public ArrayList<String> getPermutations(ArrayList<String> s)
	{
		ArrayList<String> words = new ArrayList<String>();
		if (s.size() > 1)
		{
			for (String x : s)
			{
				ArrayList<String> listWithout = new ArrayList<String>(s);
				listWithout.remove(x);
				ArrayList<String> temp = getPermutations(listWithout);
				for (String y : temp)
				{
					words.add(x+y);
					words.add(y);
				}

			}
		}
		else
		{
			words = new ArrayList<String>(s);
		}
		return words;
	}

	public void addWordIfWord( HashSet<String> words, String word ) {
		if( isWord( word ) ) {
			words.add(word);
		}
	}

	public void printWordForMacro( String word ) {
		for (char c : word.toCharArray())
		{
			System.out.println("KeyStrPress " + c);
			System.out.println("KeyStrRelease " + c);
			try {
			Thread.sleep(10);
			} catch( Exception e ) {
				e.printStackTrace();
			}
		}
		System.out.println("KeyStrPress Return");
		System.out.println("KeyStrRelease Return");
	}

	public void printWordIfWord( String word ) {
		if( isWord( word ) ) {
			printWordForMacro( word );
		}
	}

	public HashSet<String> getPermutationsMike( String s, ArrayList<String> stubs ) {
		HashSet<String> new_set = new HashSet<String>();
		if( stubs.size() > 0 && s.length() <= MAXSIZE ) {
			
			for( String current_stub : stubs ) {
				ArrayList<String> current_stubs = new ArrayList<String>( stubs ); // Make a copy
				current_stubs.remove( current_stub ); // Remove the current one
				
				printWordIfWord( s + current_stub );

				HashSet<String> ret = getPermutationsMike( s + current_stub, current_stubs );
				new_set.addAll( ret );
			}
		}
		return new_set;
	}

	/**
	 * Gets the word stubs
	 * @return ArrayList of String stubs
	 */
	public ArrayList<String> getStubs()
	{
		return stubs;
	}

	/**
	 * Verifies if a particular word is in the dictionary
	 * @param str String possibility
	 * @return boolean true if string is in the dictionary
	 */
	public boolean isWord(String str)
	{
		return dict.contains(str);
	}
	
	/**
	 * Loads the dictionary from a specific file
	 * @param filename the file from which to load
	 */
	public void loadDict(String filename)
	{
		try
		{
			Scanner dictFile = new Scanner(new File(filename));
			while (dictFile.hasNext())
			{
				dict.add(dictFile.next().toLowerCase());
			}
		}
		catch (java.io.FileNotFoundException e)
		{
			System.err.println("File not found");
		}
	}
	
	/**
	 * Adds all stubs to the stub ArrayList
	 * @param args command line arguments (first is the list)
	 */
	public void loadStubs(String [] args)
	{
		for (int i = 1; i < args.length; i++)
		{
			stubs.add(args[i]);
		}
	}

	/**
	 * Main Method
	 * Usage: java LittleWordsCheater dictionary.txt cd {sd re tr ...}
	 * @param args {input-file} FirstStub {SecondStub ThirdStub ...}
	 */
	public static void main(String[] args)
	{
		LittleWordsCheater cheat = new LittleWordsCheater();
		// Must be at least dict and stubs
		if (args.length < 2)
		{
			System.out.println("Usage: java LittleWordsCheater dictionary.txt cd {sd re tr ...}");
			System.exit(1);
		}
		// Load the dictionary file into a HashSet
		cheat.loadDict(args[0]);
		// Load the stubs into an ArrayList
		cheat.loadStubs(args);

		//HashSet<String> permutations = new HashSet<String>(cheat.getPermutations(cheat.getStubs()));
		HashSet<String> permutations = cheat.getPermutationsMike("", cheat.getStubs());
		
		for (String p : permutations)
		{
			if (cheat.isWord(p))
			{
				// Print all permutations of the stubs that are contained in the dictionary 
				//System.out.println(p);

				// Macro out
//				for (char c : p.toCharArray())
//				{
//					System.out.println("KeyStrPress " + c);
//					System.out.println("KeyStrRelease " + c);
//				}
//				System.out.println("KeyStrPress Return");
//				System.out.println("KeyStrRelease Return");
			}
		}
	}
}
