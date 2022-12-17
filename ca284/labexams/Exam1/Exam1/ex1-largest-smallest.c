/*
 ex1-largest-smallest.c
 author <Fionn Hourican>
 requirements: largest or smallest,  array of float numbers.
 description: outputs smallest or largest number in arry depending on requirements.
 date: 06/10/2022
 */

/*
 my apprach: create the array in the main function and then call the function to find min or max value.
*/

/* includes*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h> /* contains functions we may need*/

float smallest_largest(char *size, float array_n[], int length);

int main(int argc, char *argv[])
{
    int length = argc - 1;
    float nums[length];
    char *size = argv[1];
    float number;

    /* For loop creates array of numbers from command line */
    for(unsigned int i = 1; i < length; ++i)
    {
        nums[i] = atof(argv[i+1]);
    }

    /* Calls function */
    number = smallest_largest(size, nums, length);

    return 0;
}

float smallest_largest(char *size, float nums[], int length)
{
    float temp;

    /* Orders nums list from smallest to largest*/
    for(int i = 0; i < length; i++)
    {
        for (int j = i+1; j < length; j++)
        {
             if(nums[i] > nums[j])
             {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
             }

        }
    }

    /* If larges called, prints last element which is the largest*/
    if(strlen(size) == 7)
    {
        printf("%.2f\n", nums[(length - 1)]);
    }
    else if(strlen(size) == 8)
    /* If smallest called, prints first element which is largest*/
    {
        printf("%.2f\n", nums[1]);
    }
    return 0;
} /* exit program */