/*
 Author: Fionn Hourican
 Date: 30/11/22
 requirements: 
    - Command line format, which can be repeated, of;
    - string(Product Code), string(country name), positve int(Price)
 description: 
    - It can store information about a list of products.
    - upgradates Ireland by 20%
    - Display all products line by line.

 My approach:
    - Use function 'fillList' to make doubly linkedList.
    - Return pointer of first Product in Linked List.
    -function 'printlist' prints product info, increase Ireland by 20%.
*/

/* includes*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/* Struct */ 
typedef struct Product Product;

struct Product {
    char Code[100];
    char Country[100];
    int Price;
    Product *next;
};

/* Function Prototypes */
Product* fillList(char *argv[], int length);
void printLinkedList(Product *LinkedList);

int main(int argc, char*argv[])
{
    int length = argc;
    Product *LinkedList;
    LinkedList = fillList(argv, length);
    printLinkedList(LinkedList);
    return 0;
}

/*
Name of function: fillList*
Description:
    - Creates first Product in Doubly LinkedList.
    - For loop Adds remained of command line arguments to LinkedList.

Arguments:
    - Array (from Command line).
    - Length of Array.

Returns: pointer type Product for first Product of linked list.
*/
Product* fillList(char *argv[], int length)
{
	Product *current, *first;
    first = (Product*)calloc(1,sizeof(Product)); /*create the first node */
	current = first;
    strcpy(current->Code, argv[1]);
    strcpy(current->Country, argv[2]);
    current->Price = atoi(argv[3]);

    for (int i = 1; (i * 3) + 1 < length - 1; i++)
    {
        current->next = (Product*)calloc(1,sizeof(Product)); // Allocate new Product.
	    current = current->next; // Move Current Product to next.
        strcpy(current->Code, argv[(3 * i) + 1]); // Fill in new Product.
        strcpy(current->Country, argv[(3 * i) + 2]);
        current->Price = atoi(argv[(3 * i) + 3]);
    }
    current->next = NULL; // Incase last node.
    return first;
}

/*
Name of function: printLinkedList
Description:
    - Print Product info.
    - If product country == Ireland, increase by 20%.

Arguments:
    - pointer to first product

Returns: Does not return (void).
*/
void printLinkedList(Product *LinkedList) 
{
	Product* current = NULL;
	for(current = LinkedList; current != NULL; current = current->next)
	{
        printf("%s\n", current->Code);
        printf("%s\n", current->Country);
        if (strcmp(current->Country, "Ireland") == 0) 
        {
            float price = current->Price * 1.20;
            printf("%.0f\n", price);
        } else 
        {
            printf("%d\n", current->Price);
        }
	}
}