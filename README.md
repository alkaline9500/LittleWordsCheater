LittleWordsCheater
==================

Uses a dictionary to cheat at tiled word game

I suggest you go to a game like [Word Ruffle](http://www.wordgames.com/word-ruffle.html).
Then do something like this:

``
java LittleWordsCheater list.ls | xmacroplay $DISPLAY
``

After running, the program takes input:
1. Enter the list of stubs on separate lines
2. On the next line, enter the max size for the word
3. On the last line, enter y or n for macro mode
