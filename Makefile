build-run: build
	java BoggleCheater dictionary.fulllist

build: BoggleCheater.java LittleWordsCheater.java ScrabbleComparator.java
	javac BoggleCheater.java LittleWordsCheater.java ScrabbleComparator.java

list:
	java LittleWordsCheater dictionary.fulllist

macro:
	java LittleWordsCheater dictionary.fulllist | xmacroplay $DISPLAY
