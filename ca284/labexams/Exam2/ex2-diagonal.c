/*
 ex2-diagonal.c
 author <Fionn Hourican>
 Date: 03/11/22
 requirements: int Array with length of first number + 1
 description: Adds the anti diagnal of square matrix

 My approach:
 - create 2D Array in main function based of size of n
 - Call a function to add anti-diagnal of matrix
 */

/* includes*/
#include<stdio.h>
#include<stdlib.h>

int addDiagnalRev(int (*pMatrix)[512], int n);

int main(int argc, char *argv[])
{
    int n = atoi(argv[1]); 

    int matrix[512][512]; /* Declare matrix of max size */
    int (*pMatrix)[512] = matrix; /* Create pointer to the first element in the 2D Array*/

    /* Creating the matrix based off the size of n, using pointers. */
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            *(*(pMatrix + i)+j) = atoi(argv[i*n + j + 2]);
        }
    }

    printf("%d\n", addDiagnalRev(pMatrix, n));

    return 0;
}

/* Adds anti-diagnal and returns a pointer total*/
int addDiagnalRev(int (*pMatrix)[512], int n)
{
    int total = 0;
    int *ptotal = &total;
    int j = n - 1;

    for (int i = 0; i < n; i++)
    {
        *ptotal += *(*(pMatrix + j) + i);
        j--;
    }

    return *ptotal;
}