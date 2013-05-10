/**
 * LittleWordsCheater Class and main
 * @author Nic Manoogian
 * @author Mike Lyons
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
	// If the output should be macro
	private boolean macromode;
	// Max word size
	public static int MAXSIZE;

	/**
	 * Constructs a LittleWordsCheater
	 * Set default maxsize and macro
	 */
	public LittleWordsCheater()
	{
		stubs = new ArrayList<String>();
		dict = new HashSet<String>();
		MAXSIZE = 10;
		macromode = false;
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
	 * Adds the word to the hash if it is a word
	 * @param words HashSet of words
	 * @param word possible word string
	 */
	public void addWordIfWord(HashSet<String> words, String word)
	{
		if(isWord(word))
		{
			words.add(word);
		}
	}

	/**
	 * Prints the xmacro commands for the word
	 * @param word possbile word string
	 */
	public void printWordForMacro(String word)
	{
		for (char c : word.toCharArray())
		{
			System.out.println("KeyStrPress " + c);
			System.out.println("KeyStrRelease " + c);
		}
		System.out.println("KeyStrPress Return");
		System.out.println("KeyStrRelease Return");
		// Clear out the word list if the word is large
//		if (word.length() >= MAXSIZE-1)
//		{
			for (int i = 0; i < word.length(); i++)
			{
				System.out.println("KeyStrPress BackSpace\nKeyStrRelease BackSpace");
			}
//		}
			
	}

	/**
	 * Prints the word if it is in the HashSet
	 * @param word possbile word string
	 */
	public void printWordIfWord(String word)
	{
		if (isWord(word))
		{
			if (macromode)
			{
				printWordForMacro( word );
			}
			else
			{
				System.out.println(word);
			}
		}
	}

	/**
	 * Gets complete permutations for a stub list
	 * @param s Current permutation
	 * @param stubs String stubs to permute
	 */
	public HashSet<String> getPermutationsMike( String s, ArrayList<String> stubs )
	{
		HashSet<String> new_set = new HashSet<String>();
		if (stubs.size() > 0 && s.length() < MAXSIZE)
		{
			// For each stub
			for(String current_stub : stubs)
			{
				// Make a copy
				ArrayList<String> current_stubs = new ArrayList<String>(stubs);
				// Remove the current one
				current_stubs.remove(current_stub);
				// If the current permutation plus the stub is a word, add it
				printWordIfWord(s + current_stub);
				// Recurse with the new string plus the stub, and the stubs list without the current stub
				HashSet<String> ret = getPermutationsMike(s + current_stub, current_stubs);
				// Add all to the set
				new_set.addAll(ret);
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
	 * Gets various things from the user
	 * Adds all stubs to the stub ArrayList
	 * Sets maxsize
	 * Sets macro or regular
	 */
	public void getInput()
	{
		//System.out.println("Enter word stubs (end with 0):");
		Scanner input = new Scanner(System.in);
		String s = "";
		while (input.hasNext())
		{
			s = input.next();
			if (Character.isLetter(s.charAt(0)))
			{
				stubs.add(s);
			}
			else
			{
				MAXSIZE = Integer.parseInt(s);
				break;
			}
		}
		
		//System.out.println("Enter max word size:");
		//System.out.println("Macro Mode?:");
		s = input.next();
		if (s.equals("y") || s.equals("Y"))
		{
			macromode = true;
			System.out.println("Delay 3");
		}
	}

	/**
	 * Main Method
	 * Usage: java LittleWordsCheater dictionary.txt
	 * @param args dictionary
	 */
	public static void main(String[] args)
	{
		LittleWordsCheater cheat = new LittleWordsCheater();
		// Must be at least dict and stubs
		if (args.length != 1)
		{
			System.out.println("Usage: java LittleWordsCheater dictionary.txt");
			System.exit(1);
		}
		// Load the dictionary file into a HashSet
		cheat.loadDict(args[0]);
		cheat.getInput();

		//HashSet<String> permutations = new HashSet<String>(cheat.getPermutations(cheat.getStubs()));
		HashSet<String> permutations = cheat.getPermutationsMike("", cheat.getStubs());
	}
}
