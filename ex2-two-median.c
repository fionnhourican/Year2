/*
 ex2-two-median.c
 author <Fionn Hourican>
 Date: 03/11/22
 requirements: int Array with even length
 description: Prints median values

 My approach:
 - Use a function to first order the Array using bubble sort.
 - write a function to print median values by;
 - Divide the length by 2, this s the upper mrdian, and divide the length by 2 and subtract one, this is lower median.
 */

/* includes*/
#include <stdio.h>
#include <stdlib.h>

/* function prototypes */
int orderArray(int a[], int length);
int findMiddle(int a[], int length);
int printArray(int numbers[], int length);

/* main function */
int main(int argc, char *argv[])
{
    int length = (argc - 1); /* declare the length of array */
    int numbers[length];  /* Declare array*/

    /* Using for loop to create Array*/
    for(unsigned int i = 0; i < length; i ++)
    {
        numbers[i] = atoi(argv[i + 1]);
    }

    /* Calling functions*/
    orderArray(numbers, length);
    findMiddle(numbers, length);

    return 0;
}

/* Orders array from smallest to largest using bubble sort*/
int orderArray(int a[], int length)
{
	for(int i = 0; i < length; ++i)
	{
		for(int j = i + 1; j < length; ++j)
		{
			if(a[i] > a[j])
			{
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
	}
    return *a;
}

/* Prints median values*/
int findMiddle(int a[], int length)
{
	printf("%d\n%d\n", a[(length / 2) - 1], a[length / 2]);
    return 0;
}
