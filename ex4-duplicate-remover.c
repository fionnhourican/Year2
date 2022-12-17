/*
 Author: Fionn Hourican
 Date: 13/12/2022

 name: ex4-duplicate-remover.c
 requirements: 
    - Array of int
 returns: Array with all duplicates removed
 Description:
    - removes all duplicated numbers and print all non-duplicated elements. 
    - duplicated values are removed completely, including the value itself.
    - uses one Array to store values.
 My approach:
    - Add values into an Array
    - Search through Array to find duplicates
    - remove duplicate
        - Move to Duplicate value
        - Copy the next element to the current element of array
        - Repeat above step till last element of array
        - decrement the size of array by one
    - move back one position in search as value changed
    - print final Array
*/

/* includes */
#include <stdio.h> // For input and output
#include <stdlib.h> // For the atof() function

/* Function prototypes */
void removeDupPrint(int * array, int size); // Removes duplicates and prints array

/*
 name: main function
 description:
    - Creates array from command line
    - Calls function to remove duplicates from array and print
 returns: 0
*/
int main(int argc, char * argv[])
{
    /* statments */
    int lst[argc - 1], j = 0;
    int length = argc - 1;

    /* Add values to Array */
    for(int i = 1; i < argc; i++)
    {
        lst[j] = atoi(argv[i]);
        j++;
    }

    removeDupPrint(lst, length); // Removes duplicates and prints remaining values

    return 0; // Exit function properly
}

/*
 name: removeDupPrint
 description:
    - loops through Array to find duplicates
    - if duplicate found
        - remove duplicate
        - recheck remaining values
        - remove first instance of duplicate
    - print remaining elements in Array
 returns: void does not return Value
*/
void removeDupPrint(int * array, int length)
{
    /* initialisation */
    int count; // count if element duplicated

    /* loop to compare every item in list */
    for (int i = 0; i < length; i++)
    {
        count = 0; // Setting count to 0 (used to signify a duplicate)

        for (int j = i + 1; j < length; j++)
        {
            if (array[i] == array[j])  // If we have found a duplicate
            {
                count = 1; // Duplicate found

                /* Shifting consequent items up one position */
                for (int k = j; k < length - 1; k++)
                {
                    array[k] = array[k + 1]; // Setting current item to the next item
                }
                length--; // reduce length of Array by one
                j--; // Move position back one to ensure all elements checked
            }
        }

        /* Remove first instance of duplicated number*/
        if (count == 1)
        {
            /* Shifting consequent items up one position */
            for (int k = i; k < length - 1; k++)
            {
                array[k] = array[k + 1]; // Setting current item to the next item
            }
            length--; // reduce length of Array by one
            i--; // i recheked as value changed
        }
    }

    /* Print remaining Array */
    for(int i = 0; i < length; i++)
    {
        printf("%d\n", array[i]);
    }

    return; // exit function properly
}