# COMS228_hw4_Fall2023
Archived Message Reconstruction using Binary Trees

Using Binary Trees to decode encoded messages
Takes an input file
Set encoding method using 0s and 1s, which is mapped to the alphabet and other characters

Basic Tree Traversal Logic
 - start at the root
 - Repeat until at leaf
     - Scan one bit
     - Go to left chile if 0 else go right
 - Print leaf payload
