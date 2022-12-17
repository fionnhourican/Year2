/*
 Author: Fionn Hourican
 Date: 13/12/2022

 name: ex4-order-linked-list.c
 requirements: 
    - Array of int
 returns: 0 or 1
 Description:
    - check whether the list of numbers is in descending numerical order.
    - If so, print 1, otherwise 0.
 My approach:
    - Create structure for linked list
    - Main function initilises pointer to linked list
    - Call function to create linked list
    - Call function to check if in decending order and print 1 if it is and 0 if not
*/

/* includes */
#include <stdio.h> // For input and output
#include <stdlib.h> // For the atof() function

/* Struct for linked list */
typedef struct Node Node;

struct Node
{
    int value;
    Node *next;  // pointer to address of next value
    Node *prev;  // Pointer to address of previous value
};

/* Function prototypes */
Node* make_LList(int length, char * argv[]); // Makes linked list
void printDecending(Node *first); // Checks if Linked list in decending order and prints result

/*
 name: Main function
 description:
    - initilaises pointer to linked list
    - Calls function to add items to linked list
    - calls function to print 1 if linked list in decending order
    - releases memory when finished using
    - exits function
 returns: 0
*/
int main(int argc, char *argv[])
{
    /* initialisation */
    Node *first = NULL;
    int length = argc - 1;

    first = make_LList(length, argv);  // Adding items to linked list
    printDecending(first); // Printing 1 if in decending, 0 otherwise

    /* Release memory */
    free(first);
    first = NULL;

    return 0; // Exiting the function correctly
}

/*
 name: make_LList
 description:
    - Creates first node in linked list
    - itterates throught Array adding elements to linked list
 returns: Node pointer to first element in linked list
*/
Node* make_LList(int length, char * argv[])
{
    /* initialisation */
    Node *current, *first, *prev; // Declaring three pointers.

    first = (Node*)calloc(1, sizeof(Node)); // Creating the first node.
    current = first;  // Current pointer points to the first node now.

    /* Set data for first node. */
    current->value = atof(argv[1]);
    current->prev = NULL;

    /* Putting the rest of the values in the linked list. */
    for(int i = 2; i < length + 1; i++)
    {
        current->next = (Node*)calloc(1, sizeof(Node));  // Dynamically Allocating memory
        prev = current;  // Moving previous pointer to current element
        current = current->next;  // Moving current pointer to next element

        /* Set data for current node */
        current->value = atof(argv[i]); // Using atof to convert string into integer
        current->prev = prev;
    }

    current->next = NULL; // Last element in linked list points to Null
    
    return first; // Return pointer to first element in linked list
}

/*
 name: printResult
 description:
    - Traverses linked list
    - If previous element smaller then current, Linked list not in decending order
        - Print 0
        - exit function correctly
    - if end of linked list reached and all in decending order
        - print 1
        - exit function correctly
 returns: Node pointer to first element in linked list
*/
void printDecending(Node *first)
{
    /* initialisation */
    Node* p = first;  // Pointer used to traverse linked list

    
    for (p = p->next; p != NULL; p = p->next)  // Traverses until last element reached (points to Null)
    {
        if(p->prev->value < p->value)
        {
            printf("0\n");  // If the previous item is smaller than the current, print 0
            exit(0); // Exit function succesfully
        }
    }
    printf("1\n"); // Print 1 as linked list in decending order

    return; // exit function properly
}