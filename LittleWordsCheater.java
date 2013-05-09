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
				dict.add(dictFile.next());
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

		System.out.println("Listing Words");
		HashSet<String> permutations = new HashSet<String>(cheat.getPermutations(cheat.getStubs()));
		for (String p : permutations)
		{
			if (cheat.isWord(p))
			{
				// Print all permutations of the stubs that are contained in the dictionary 
				System.out.println(p);
			}
		}
	}
}
