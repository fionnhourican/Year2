/*
 ex1-is-symmetric.c
 author <Fionn Hourican>
 requirements: int number
 description: checks if a word is symetrical
 date: 06/10/2022
 */

/*
 my apprach: check last letter with first and so on untill one character different or word is complete
*/

/* includes*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void symmetric(char word[]);

int main(int argc, char *argv[])
{
    char *word = argv[1];
    
    symmetric(word);

    return 0;
}

void symmetric(char word[])
{
    int first = 0;
    int last = (strlen(word) - 1);

    /* compares last and first and so on */
    while (last > first)
    {
        if (word[first++] != word[last--])
        {
            printf("%s\n", "no");
            return;
        }
    }
    printf("%s\n", "yes");
}