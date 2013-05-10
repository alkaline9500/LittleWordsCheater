/**
 * LittleWordsCheater Class and main
 * @author Nic Manoogian
 */
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.Point;

public class BoggleCheater
{
	// Dictionary of words
	private HashSet<String> dict;

	private String[][] board;

	//Max word size
	public static int MAXSIZE = 8;
	//Board size
	public static int BOARDWIDTH = 5;
	public static int BOARDHEIGHT = 5;
	
	/**
	 * Constructs a Cheater
	 */
	public BoggleCheater()
	{
		dict = new HashSet<String>();
	}

	public void setBoard( String[][] board )
	{
		this.board = board;
	}

	public void addWordIfWord( HashSet<String> words, String word )
	{
		if(isWord(word))
		{
			words.add(word);
		}
	}

	public void printWordForMacro(String word)
	{
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

	/**
	 * Prints if the given word is in the dictionary
	 * @param word String to check
	 */
	public void printWordIfWord( String word )
	{
		if(isWord(word))
		{
			printWordForMacro(word);
		}
	}

	public ArrayList<Point> getNeighbors( int x, int y ) {
		ArrayList<Point> ret = new ArrayList<Point>();
		if( x > 0 ) {
			ret.add( new Point( x - 1, y ) );
		}
		if( x > 0 && y > 0 ) {
			ret.add( new Point( x - 1, y - 1 ) );
		}
		if( x > 0 && y < BOARDHEIGHT - 1 ) {
			ret.add( new Point( x - 1, y + 1 ) );
		}
		if( x < BOARDWIDTH-1 && y > 0 ) {
			ret.add( new Point( x + 1, y - 1) );
		}
		if( x < BOARDWIDTH-1 && y < BOARDHEIGHT-1 ) {
			ret.add( new Point( x + 1, y + 1) );
		}
		if( x < BOARDWIDTH-1 ) {
			ret.add( new Point( x + 1, y ) );
		}
		if( y > 0 ) {
			ret.add( new Point( x, y - 1 ) );
		}
		if( y < BOARDHEIGHT-1 ) {
			ret.add( new Point( x, y + 1 ) );
		}
		return ret;
	}

	public void printPermutations() {
		for( int i = 0; i < BOARDWIDTH; i++ ) {
			for( int j = 0; j < BOARDHEIGHT; j++ ) {
				getPermutationsMike( "", i, j );
			}
		}
	}

	public HashSet<String> getPermutationsMike( String s, int x, int y ) {
		HashSet<String> new_set = new HashSet<String>();

		if( s.length() <= MAXSIZE ) {

			ArrayList<Point> neighbors = getNeighbors( x, y );

			for( Point neighbor : neighbors ) {
				String current_stub = board[neighbor.x][neighbor.y];
				
				printWordIfWord( s + current_stub );

				HashSet<String> ret = getPermutationsMike( s + current_stub, neighbor.x, neighbor.y );
				new_set.addAll( ret );
			}
		}
		return new_set;
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
	 * Main Method
	 * Usage: java LittleWordsCheater dictionary.txt cd {sd re tr ...}
	 * @param args {input-file} FirstStub {SecondStub ThirdStub ...}
	 */
	public static void main(String[] args)
	{
		BoggleCheater cheat = new BoggleCheater();
		// Load the dictionary file into a HashSet
		cheat.loadDict(args[0]);

		InputStreamReader inp = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(inp);

		String[][] board = new String[BOARDWIDTH][BOARDHEIGHT];

		try {
			for( int i = 0; i < BOARDWIDTH; i++ ) {
				String line = in.readLine();
				
				String[] exploded = line.split(" ");
				for( int j = 0; j < exploded.length; j++ ) {
					board[i][j] = exploded[j];
				}
			}

			cheat.setBoard( board );

			cheat.printPermutations();
		} catch( Exception e ) {
			e.printStackTrace();
		}
//		HashSet<String> permutations = cheat.getPermutationsMike("", cheat.getStubs());
	}
}
