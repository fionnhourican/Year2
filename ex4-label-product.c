/*
 Author: Fionn Hourican
 Date: 13/12/2022

 name: ex4-label-product.c
 requirements: 
    - Information of a list of items:
        - Item Code (max 20)
        - Price (a floating number)
        - Number of item sold (a positive integer)

 returns: status (int), Country (string)

 Description:
    - The program read the input data from the command line.
    - The item whose sales is greater than or equal to the average sales of all items will have the Status = 1, otherwise the Status = 0.
    - The program prints the status and country of origin of each item line by line.

 My approach:
    - Create Cart struct data type
    - Use sepeate functions to do the following
        - Adds items to linked list
        - Find the products' average sales
        - Updates items' status' to 1 if their sales is above the average sales
        - Print items
*/

/* includes */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Struct */
typedef struct Cart Cart; // Define Cart as a type name.

struct Cart //Structure type definition.
{
  char item_code[20]; // max length 20
  float price;
  unsigned int amount_sold;
  int status;  // 0 if product's sales are below the average sales, 1 otherwise.
  Cart *next; // Pointer to next element in linked list
  Cart *prev; // Pointer to previous element in linked list.
};

/* Function prototypes */
Cart *add_items(char *argv[], int length);  // Adds nodes to linked list.
float get_average_sales(Cart *first, int product_amount);  // Find the products' average sales/
Cart *update_status(Cart *start, float average_sales);  // Updates items' status' to 1 if their sales is above the average sales.
void print_items(Cart *start); // Prints items

/*
  name: Main function
 description:
    - Intitilaises start of linked list
    - Calls function to create linked list
    - calls fuction to find average sales
    - Calls function to print items line by line
    - Frees memory used when finished using

 returns: 0
*/
int main(int argc, char * argv[])
{
    /* initialisation */
    Cart* first = NULL;
    int length = (argc - 1);
    int product_amount = (length / 3); // 3 pieces of info per product
    int average_sales;

    first = add_items(argv, length); // Adding items to linked list from command line
    average_sales = get_average_sales(first, product_amount); // find average sales
    update_status(first, average_sales); // Update status to 0 or 1
    print_items(first); // Print items line by line

    /* Release memory */
    free(first);
    first = NULL;

    return 0; // Exiting the function correctly
}

/*
  name: add_items
 description:
    - Creates linked list

 returns: Cart pointer to first element in linked list
*/
Cart *add_items(char *argv[], int length)
{
    /* initialisation */
    Cart *current, *first, *prev;  // Declaring three pointers.

    first = (Cart*)calloc(1, sizeof(Cart));  // Creating the first node.
    current = first;  // Current pointer points to the first node now.

    /* Input data for first node. */
    strcpy(current->item_code, argv[1]);
    current->price = atof(argv[2]);
    current->amount_sold = atoi(argv[3]);
    current->status = 0;

    /* Putting the rest of the values in the linked list. */
    for(int i = 3; i < length - 1; i += 3)
    {
        current->next = (Cart*)calloc(1, sizeof(Cart));
        prev = current;  // Moving previous pointer to current element
        current = current->next;  // Moving current pointer to next element

        /* Input data for current node */
        strcpy(current->item_code, argv[i + 1]);
        current->price = atof(argv[i + 2]);
        current->amount_sold = atoi(argv[i + 3]);
        current->status = 0;
        current->prev = prev;
    }

    current->next = NULL; // Last element in linked list points to Null
    return first; // Return pointer to first element in linked list
}

/*
  name: get_average_sales
 description:
    - Traverse through cart linked list, finding average sales of items in linked list.

 returns: average sales (float)
*/
float get_average_sales(Cart *first, int product_amount)
{
    /* initialisation */
    float sales = 0.0;
    
    Cart *p = NULL; // Pointer used to traverse linked list

    for(p = first; p != NULL; p = p->next) // Traverses until last element reached (points to Null)
    {
        sales += p->price * p->amount_sold;
    }

    return sales / product_amount; // Returns aveage sales (float)
}

/*
  name: update_status
 description:
    - Traverse through cart linked list
    - If aveage of item > aveage sales, Status = 1

 returns: Cart pointer to first element of linked list
*/
Cart *update_status(Cart *first, float average_sales)
{
    Cart *p = NULL;  // Pointer used to traverse linked list
    float sales = 0.0;

    for(p = first; p != NULL; p = p->next)  // Traverses until last element reached (points to Null)
    {
        sales = p->price * p->amount_sold;  // Calculation of sale
        if(sales >= average_sales)
        {
            p->status = 1; // If sale above average update status to 1
        }
    }

    return first;  // Return pointer to first element in linked list
}


/*
  name: print_items
 description:
    - Traverses through linked list
    - Prints items line by line, status of item followed by country

 returns: Void does not return value
*/
void print_items(Cart *first)
{
    Cart *p = NULL;  // Pointer used to traverse linked list
    for(p = first; p != NULL; p = p->next)  // Traverses until last element reached (points to Null)
    {
        if(strncmp(p->item_code, "IE", 2) == 0)
        {
            printf("%d\n", p->status);
            printf("Ireland\n");
        }
        else if(strncmp(p->item_code, "FR", 2) == 0)
        {
            printf("%d\n", p->status);
            printf("France\n");
        }
        else if(strncmp(p->item_code, "SP", 2) == 0)
        {
            printf("%d\n", p->status);
            printf("Spain\n");
        }
        else if(strncmp(p->item_code, "US", 2) == 0)
        {
            printf("%d\n", p->status);
            printf("USA\n");
        }
        else if(strncmp(p->item_code, "RU", 2) == 0)
        {
            printf("%d\n", p->status);
            printf("Russia\n");
        }
    }
}