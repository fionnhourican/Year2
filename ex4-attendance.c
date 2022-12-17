/*
 Author: Fionn Hourican
 Date: 13/12/2022

 name: ex4-attendance.c
 requirements: Array of letters {A, P, L} and of length n.
 returns: int

 Description: This program accepts an attendance record and outputs 1 if the record contains greater than or equal
 to three absent-equivalent checks. Each 'A' refers to an absent check, Three consecutive 'L' are equvalent to one
 absent check. Otherwise the attendance status will be 0.

 My approach:
    - Main function used to create Array to be checked
    - Array initilised using Calloc, Dynamic memory allocation
    - Calls functions which use pointers to check Attendance
    - Pointers used to change value in Array to 1 or 0
*/

/* includes*/
#include <stdio.h> // Used for input and output
#include <string.h> // Used for strlen function
#include <stdlib.h> // used for calloc function

/* Function prototypes */
void attendance_check(char *record, int *status_array, int j);
void count_result(int final_count, int *status_array, int j);
void attendance_print(int *status_array, int size);

/*
 name: Main function
 description:
    - Define variables
    - Use Calloc to initialise Array
    - Call function to check attendance
    - Call function to print attendance status line by line.

 returns: 0
*/
int main(int argc, char * argv[])
{
    /* Defining valiables */
    int length = argc - 1;
    int *pStatus_array = calloc(length, sizeof(int)); // Allocate memory for length of Array, Memory initilised to 0.
    if(!pStatus_array) // Check if Allocation was successful
    {
        //Print if failed to allocate memory, e.g.:
        printf("Not enough memory!");
        exit(0); // Exit if no memory
    }

    int j = 0;

    // Looping through the argument line and calling function to count attendance.
    for(int i = 1; i < argc; i++)
    {
        attendance_check(argv[i], pStatus_array, j);
        j++; // index the attendance status should be put in the status_array.
    }

    attendance_print(pStatus_array, length); // Call function to print attendance record. 

    /* Release memory */
    free(pStatus_array);
    pStatus_array = NULL;

    return 0; // Exit main function
}

/* actual implementation of the functions */

/*
 name: attendance_check
 description:
    - Will count the number of A's in the record & if theres 3 consecutive 'L's
    - then will call the count_result function

 returns: Void function does not return a value.
*/
void attendance_check(char *record, int *status_array, int j)
{
    /* Defining variables */
    int count = 0;
    char *pA = "A"; // Using pointer to store value "A"
    char *pL = "L"; // Using pointer to store value "L"

    // Checks if character is an "A" or 3 consecutive "L"s
    for(int i = 0; i < strlen(record); i++)
    {
        char current_char = record[i];
        if(*pA == current_char)
        {
            count++; // count increments by 1 if "A" has been found
        }
        if(record[i] == *pL && record[i + 1] == *pL && record[i + 2] == *pL) // Checking if theres 3 consecutive "L"s
        {
            count++; // count increments by 1 if there are 3 consecutive "L"
        }
    }
    count_result(count, status_array, j); // Call function to send final count to function.
    return; // Exit fuction, Void does not return a value.
}

/*
 name: count_result
 description:
    - Uses pointers to add the correct status to a status array
    - If count was 3 or more for student 1 is returned.
    - else 0 is returned.
    - Pointers used to change value at index to 1 or 0.

 returns: Void function does not return a value.
*/
void count_result(int final_count, int *status_array, int j)
{
    if(final_count >= 3)
    {
        *(status_array + j) = 1; // Using an array with pointers
    }
    else
    {
        *(status_array + j) = 0; // Using an array with pointers
    }

    return; // Exit fuction, Void does not return a value.
}

/*
 name: attendance_print
 description:
    - Prints line by line the status for each students record

 returns: Void function does not return a value.
*/
void attendance_print(int *status_array, int size)
{
    // Going through each value line by line and using pointers to print the students status
    for(int i = 0; i < size; i++)
    {
        printf("%d\n", *(status_array + i));
    }

    return; // Exit fuction, Void does not return a value.
}