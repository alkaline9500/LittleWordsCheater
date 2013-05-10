/**
 * ScrabbleComparator Class
 * Compares Strings based on their Scrabble value
 */

import java.util.Comparator;

public class ScrabbleComparator implements Comparator<String>
{

	/**
	 * Get the Scrabble score for a string
	 * @param word word to get score
	 * @return score
	 */
	public int getScore(String word)
	{
		int sum = 0;
		for (char c : word.toCharArray())
		{
			switch (c)
			{
				case 'e':
				case 'a':
				case 'i':
				case 'o':
				case 'n':
				case 'r':
				case 't':
				case 'l':
				case 's':
				case 'u':
					sum += 1;
					break;
				case 'd':
				case 'g':
					sum += 2;
					break;
				case 'b':
				case 'c':
				case 'm':
				case 'p':
					sum += 3;
					break;
				case 'f':
				case 'h':
				case 'v':
				case 'w':
				case 'y':
					sum += 4;
					break;
				case 'k':
					sum += 5;
					break;
				case 'j':
				case 'x':
					sum += 8;
					break;
				case 'q':
				case 'z':
					sum += 10;
					break;
			}
		}
		return sum;
    }

    /**
     * Compares two Strings based on their Scrabble score
     */
    public int compare(String o1, String o2)
    {
        return getScore(o2)-getScore(o1);
    }


}
