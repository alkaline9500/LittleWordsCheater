/**
 * LittleWordsCheater Class and main
 * @author Nic Manoogian
 */
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Scanner;
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
		Scanner dictFile = new Scanner(filename);
		while (dictFile.hasNext())
		{
			dict.add(dictFile.next());
		}
	}
	
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

		cheat.loadDict(args[0]);
		cheat.loadStubs(args);

		System.out.println("Listing Words");
		ArrayList<String> permutations = cheat.getPermutations(cheat.getStubs());
		System.out.println(permutations);
		//for (String p : permutations)
		//{
		//	if (cheat.isWord(p))
		//	{
		//		System.out.println(p);
		//	}
		//}
	}
}
