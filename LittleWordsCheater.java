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
		ArrayList<String> stubs = new ArrayList<String>();
		ArrayList<String> dict = new ArrayList<String>();
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
				ArrayList<String> temp = new ArrayList<String>(s);
				temp.remove(x);
				for (String y : temp)
				{
					words.add(x+y);
				}

			}
		}
		return words;
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

		ArrayList<String> test = new ArrayList<String>();
		test.add("th");
		test.add("st");
		test.add("er");
		System.out.println(cheat.getPermutations(test));
	}
}
