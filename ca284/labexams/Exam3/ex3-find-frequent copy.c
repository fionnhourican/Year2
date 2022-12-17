/*
 Author: Fionn Hourican
 Date: 30/11/22
 requirements: Array of int with atleast one frequent number.
 description: Finds and displays frequent elements from a list of integers. 

 My approach:
    - Use Dynamic allocation to make Array in main.
    - Increase memory allocation by one int everytime one added.
    - Push Array to function 'frequent' to count number of times element occures.
    - use function 'sortNumbers' to sort Array in acnding order.
    - use function 'printNumbers' to print Array
*/

/* includes*/
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>

/* Function Prototypes */
void frequent(int *pNumbers, int length);
void printNumbers(int *pNumbers, int length);

/* main function uses dynamic memory allocation to make Array */
int main(int argc, char*argv[])
{
    int length = argc - 1;
    int *pNumbers = (int*)malloc(sizeof(int) * (5)); // Allocates enough memory for 5 integers only.

    for (int i = 0; i < length; i++)
    {
        *(pNumbers + i) = *(int*)malloc(sizeof(int) * (1)); // Allocate memory for the next element
        *(pNumbers + i) = atoi(argv[i + 1]);
    }

    frequent(pNumbers, length); // Calls 'frequent' function
    free(pNumbers);
    return 0;
}

/*
Name of function: frequent
Description:
    - Pushes Array to 'sortNumbers' function to put elements in order.
    - Loops through Array and counts itterations of each element.
    - If count 3 or greater, adds to New Array.
    - pushes new Array to be printed, 'printNumbers' function.

Arguments:
    - Pointer to Array.
    - length of Array.

Returns: Function does not return (void).
*/
void frequent(int *pNumbers, int length)
{
    int *pNewNumbers = (int*)malloc(sizeof(int) * (5));
    int i, newNumbersLength = 0;

    for (i = 0; i < length; i++ ){
        int count = 1, j = i + 1;

        while (pNumbers[j] == pNumbers[i]) {
            count++;
            j++;
        }

        if (count > 3)
        {
            for (int k = 0; k < count; k++)
            {
                *(pNewNumbers + (newNumbersLength + k)) = *(int*)malloc(sizeof(int) * (1)); // Allocate memory for the next element
                *(pNewNumbers + (newNumbersLength + k)) = pNumbers[i];
            }
            newNumbersLength += count;
            i += count;
        }
    }
    printNumbers(pNewNumbers, newNumbersLength);
    free(pNewNumbers);
}

/*
Name of function: printNumbers
Description:
    - Use for loop to print every element in Array.

Arguments:
    - Pointer to Array.
    - length of Array.

Returns: Function does not return (void).
*/
void printNumbers(int *pNumbers, int length) {
    for (int i = 0; i < length; i++ ){
        printf("%d\n", pNumbers[i]);
    }
}