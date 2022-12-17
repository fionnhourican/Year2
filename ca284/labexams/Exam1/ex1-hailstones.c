/*
 ex1-hailstones.c
 author <Fionn Hourican>
 requirements: int number
 description: prints hailstone sequance till reaches one
 date: 06/10/2022
 */

/*
 my apprach: divide n by 2 if even otherwise multiply by 3 and add one, repeat this untill one is reached
*/

/* includes*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int hailstones(int n);

int main(int argc, char *argv[])
{
    int n = atoi(argv[1]);

    hailstones(n); /* call function */

    return 0;
}

int hailstones(int n)
{
    printf("%d", n);

    while(n > 1)
    {
        if(n % 2 ==0)
            n = (n / 2);
        else
            n = (3 * n) + 1;
            printf(" %d", n);
    }
    printf("\n");
    return 0;
}