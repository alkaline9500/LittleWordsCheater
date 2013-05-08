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
	 * Main Method
	 * Usage: java LittleWordsCheater dictionary.txt cd {sd re tr ...}
	 */
	public static void main(String[] args)
	{
		// Must be at least dict and stubs
		if (args.length < 2)
		{
			System.out.println("Usage: java LittleWordsCheater dictionary.txt cd {sd re tr ...}");
		}

		// Load the dictionary from the file
		Scanner dictFile = new Scanner(args[0]);
		while (dictFile.hasNext())
		{
			dict.add(dictFile.next());
		}


	}
}
