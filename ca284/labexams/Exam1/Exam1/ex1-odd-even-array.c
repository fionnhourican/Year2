/*
 ex1-odd-even-array.c
 author <Fionn Hourican>
 requirements: array of numbers
 description: adds odd numbers together, subtracts even numbers from eachother, prints on seperate lines
 date: 06/10/2022
 */

/*
 my apprach:
 Create a list with numbers from command line, start value of odd set to 0.
 for value of even add first then take away the rest within a for loop.
 add a count to the even to allow for double entry eg. 12, 12

*/

/* includes*/
#include <stdio.h>
#include <stdlib.h>

int odd_even(int nums[], int length);

int main(int argc, char *argv[])
{
    int length = argc;
    int nums[length];
    int sums;

    /* For loop creates array of numbers from command line */
    for(unsigned int i = 0; i < length; ++i)
    {
        nums[i] = atoi(argv[i]);
    }

    /* Calls function */
    sums = odd_even(nums, length);

    return 0;
}


/* function takes evens away from eachother and adds odds together*/
int odd_even(int nums[], int length)
{
    int odds = 0;
    int even = 0;
    int count = 0;

    /* for every element in array, if even take away from even start, if odd add to odd start value */
    for(unsigned int i = 1; i < length; ++i)
    {
        if(nums[i] % 2 == 1)
        {
            odds += nums[i];
        }
        /* If even value is 0 add the first even, after it subtracts all remaining evens*/
        else if(nums[i] % 2 == 0)
        {
            if(even == 0 && count == 0)
            {
                count += 1;
                even += nums[i];
            }
            else if(even != 0)
            {
            even = even - nums[i];
            }
        }
    }
    printf("%d\n", odds);
    printf("%d\n", even);

    return 0;
} /* end program */