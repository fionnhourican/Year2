/*
 ex2-discount.c
 author <Fionn Hourican>
 Date: 03/11/22
 requirements: comand line arguments in order of item, amount, price, on sale
 description: Program outputs the amount to be paid.

 My approach:
 - Create new type Cart
 - In main first call function to add variables to cart
 - using indexing create Cart types of up to 100
 - Return to main and call function to add all

 */

/* includes*/
#include<stdio.h>
#include<stdlib.h>
#include <string.h>

typedef struct Cart Cart; /*Create Struct type.*/

struct Cart /*Define the Struct.*/
{
    char item[20];
    int amount;
    float price;
    int promotion;
};

/* function prototypes */
void initialiseCart(Cart cart[], int argc, char*argv[], int nItems);
void showCart(Cart cart[], int nItems);
float paid(Cart a[], int nItem);

int main(int argc, char*argv[])
{
    Cart cart[100]; /* Declare a variable type Cart. This variable has no initial values assignes to the members of the struct. */
    int nItems = (argc - 1)/4; /* Number of items on command line*/

    initialiseCart(cart, argc, argv, nItems);
    printf("%.2f\n", paid(cart, nItems));

    return 0;
}

/* Function used to add Variables to Cart structure */
void initialiseCart(Cart a[], int argc, char*argv[], int nItems)
{
    int j = 1;
    for(unsigned int i = 0; i < nItems; ++i)
    {
        strcpy(a[i].item, argv[j]);
        a[i].amount = atoi(argv[j + 1]);
        a[i].price = atof(argv[j + 2]);
        a[i].promotion = atoi(argv[j + 3]);
        j += 4;
    }
}

/* Function used to print Cart items */
void showCart(Cart a[], int nItems)
{
    for(int i = 0; i < nItems; ++i)
    {
        if (a[i].promotion == 0)
        {
            printf("%s,%d,%.2f,No Sale\n", a[i].item, a[i].amount, a[i].price);
        }
        else
        {
            printf("%s,%d,%.2f,On Sale\n", a[i].item, a[i].amount, a[i].price);
        }
    }
}

/* Function used to show total to paid*/
float paid(Cart a[], int nItem)
{
    float pay;
    float *Ppay = &pay;

    for (int i = 0; i < nItem; ++i)
    {
        if (a[i].promotion == 1)
        {
           *Ppay += (((a[i].amount / 3) * 2) * a[i].price) + ((a[i].amount % 3) * a[i].price);  
        }
        else
        {
            *Ppay += a[i].amount * a[i].price;
        }
    }
    return *Ppay;
}