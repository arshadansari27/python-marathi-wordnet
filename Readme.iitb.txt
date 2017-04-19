Readme File for the Hindi Wordnet version 1.3

Purpose:
--------------
The Hindi Wordnet 1.3, copyright IIT Bombay, has been developed for the purpose of
facilitating Natural Language Processing applications. The whole system contains:

1. A browser (Offline Java Swing based browser)
2. The database (text files in similar format as Princeton English WordNet)

Browser:
------------
The Hindi Wordnet Browser is a Graphical User Interface to access the Hindi Wordnet lexical database. The users input words in Unicode and the results are also shown in Unicode. The results that are displayed are the synsets i.e. synonyms of the entered word. The semantic relations are displayed in the drop down boxes.

Please follow this link to enable Unicode on Windows:
http://www.microsoft.com/globaldev/handson/user/xpintlsupp.mspx

REQUIREMENTS:
-----------------------------------------

This software needs the JAVA's JRE 6 version or higher to function properly. This can be downloaded for free from the internet.
Windows 2000/XP and above with Indian Language Pack
Linux with Indian Language Pack(Unicode support for Devanagari)
50 MB Disk Space
128MB RAM or higher


HOW TO USE:
-----------------------------------------
1. Decompress the HindiWN_1_3.zip in a directory of your choice
2. To open the interface, double-click on HWNBrowser.jar If the interface does not appear, double-click on RUN.BAT (or run.sh if you are using Linux/Unix) Java JRE should be properly installed and in the PATH shell variable should include path to java.exe . 
4. Select or Deselect Enable Morphology checkbox in File Menu to use Morphological Processing.
5. Line wrapping can be enabled or disabled in the same manner.

DIRECTORY STRUCTURE:
-----------------------------------------
1. HWNBrowser.jar executable jar
2. Readme.txt file
3. 'database' directory containing Hindi Wordnet data files
4. 'MorpHIN' directory containing Morphological Processor related files
5. 'config' directory containing the configuration options of the Browser and Wordnet

CHANGELOG:
-----------------------------------------
1. Morphological processor incorporated in the Wordnet.
2. Improved and faster JHWNL API makes the Wordnet load faster.
3. Updated Wordnet database with several new and updated synsets.
4. Improvements made in the GUI to avail wordwrap etc and some actions/buttons moved to more appropriate locations.

DEVELOPED AT:
-----------------------------------------
CFILT Lab,
Dept of Computer Science & Engineering,
IIT Bombay,
Powai, Mumbai 400076.

Send bug-reports etc to:
pb[at]cse.iitb.ac.in,   
dkanojia[at]iitb.ac.in
