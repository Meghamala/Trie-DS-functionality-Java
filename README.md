# Trie-using Linked structure

## Functionalities:

1. Add words of any ASCII
2. Find words based on substring
3. Displaying all words on Trie

The trie is built as following:

> Each character of word are taken and added into individual nodes of trie's root
> Character is searched starting from depth 1 ( below root)
> If there are nodes in depth 1, then list under depth 1 is iterated to find the character
> If not found, a new node is created with character and appended to list
> Print is performed via depth first search algorithm
> node is recursively traversed to child and nextParent until end of String is reached
> With End of String, the character is appended to a mutable string






 
