/*
 ex1-is-triangular.c
 author <Fionn Hourican>
 requirements: int number
 description: checks if number is triangular
 date: 06/10/2022
 */

/*
 my apprach: find all triangular numbers until they reach the value n, if equal numbers triangular.
*/

/* includes*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char tri(int n);

int main(int argc, char *argv[])
{
    int n = atoi(argv[1]);
    tri(n);

    return 0;
}

/* finds triangular number up to value of n*/
char tri(int n)
{
    int count = 0;
    for(int i = 0; count < n; ++i)
    {
        count =  count + i;
        if(count == n)
            return printf("%d is a triangular number\n", n);       
    }
    printf("%d is not a triangular number\n", n);
}