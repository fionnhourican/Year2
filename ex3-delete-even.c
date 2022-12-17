/*
 Author: Fionn Hourican
 Date: 30/11/22
 requirements: Array of positive integer numbers from the command line.
 description:
    - Reads all input data, finds all even numbers and delete them from the Array.
    - Calculates the sum of the remaining odd numbers. 
    - Pushes result into the end of the Array. 
    - Displays all elements of the Array line by line.

 My approach: 
    - Use function 'makeList' to make doubly linkedList.
    - Return pointer of first node in Linked List.
    use Function 'printLinkedList' to remove even, adds remaining odd numbers, prints total & linkedList.
*/

/* includes*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/* Struct */ 
typedef struct Node Node;

struct Node
{
    int value;
    Node *next;
    Node *prev;
};

/* Function Prototypes */
Node* makeList(char *argv[], int length);
void printLinkedList(Node *LinkedList);

int main(int argc, char*argv[]) {
    Node *LinkedList = NULL;
    LinkedList = makeList(argv, argc);
    printLinkedList(LinkedList);
    return 0;
}

/*
Name of function: makeList
Description:
    - Creates first Node in Doubly LinkedList.
    - For loop Adds remained of command line arguments.

Arguments:
    - Array (from Command line).
    - Length of Array.

Returns: pointer type Node for first Node of linked list.
*/
Node* makeList(char *argv[], int length) 
{
	Node *current, *first, *prev;
    first = (Node*)calloc(1,sizeof(Node)); /*create the first node */
	current = first;
    current->value = atoi(argv[1]);
    current->prev = NULL;

    for (int i = 2; i < length; i++) 
    {
        current->next = (Node*)calloc(1,sizeof(Node)); // Allocate new node.
        prev = current; // Get previous Node before moving the current pointer to next Node.
	    current = current->next; // Move current node to the next Node.
        current->value = atoi(argv[i]); // Fill in New Node.
        current->prev = prev;
    }
    current->next = NULL; // Incase last Node.
    return first;
}

/*
Name of function: printLinkedList
Description:
    - Removes even numbers from Doubly LinkedList.
    - Adds remaining odd numbers in linked list.
    - prints LinkedList.
    - Prints Total.

Arguments:
    - Pointer to LinkedList.

Returns: Function does not return (void).
*/
void printLinkedList(Node *LinkedList) 
{
	Node* current = NULL;
    Node* temp = NULL;
    int total = 0;
	for(current = LinkedList; current != NULL; current = current->next)
	{
        if (current->value % 1 != 0) 
        {
            current->prev->next = current->next; // Link previous item with next item.
            current->next->prev = current->prev;
            temp = current->prev; // Used to mark new current after deletion of old.

            free(current); // Delete old current.
            current = temp; // New Position of Current.
        }

        else if (current->value % 2 != 0)
        {
            total += current->value;
            printf("%d\n", current->value);            
        }
	}
    printf("%d\n", total);
}