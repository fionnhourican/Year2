/*
Name: Fionn Hourican
Student Number: 21477216
I, Fionn Hourican, acknowledge all of DCU's Academic Integrity Policies.
*/

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>                            // for chdir()
#include <time.h>
#include <string.h>
#include <stdbool.h>
#include "utility.h"
#define MAX_BUFFER 1024                        // max line buffer
#define MAX_ARGS 64                            // max # args
#define SEPARATORS " \t\n"                     // token separators
extern char **environ;                          // declare the environ variable

int main (int argc, char ** argv)
{
    char buf[MAX_BUFFER];                      // line buffer
    char * args[MAX_ARGS];                     // pointers to arg strings
    char ** arg;                               // working pointer thru args
    char * prompt = "==> " ;                   // shell prompt
    FILE *input_file = stdin;                  // default to reading from stdin
    FILE *output_file = stdout;                // default to writing to stdout

    // check if a batch file is specified
    if (argc > 1) {
        input_file = fopen(argv[1], "r");
        if (!input_file) {
            perror("Failed to open batch file");
            exit(1);
        }
    }

    // check if input and output files are specified
    for (int i = 1; i < argc; i++) {
        if (!strcmp(argv[i], "<")) {
            input_file = fopen(argv[i+1], "r");
            if (!input_file) {
                perror("Failed to open input file");
                exit(1);
            }
            i++; // skip the input file argument
        } else if (!strcmp(argv[i], ">")) {
            output_file = fopen(argv[i+1], "w");
            if (!output_file) {
                perror("Failed to open output file");
                exit(1);
            }
            i++; // skip the output file argument
        } else if (!strcmp(argv[i], ">>")) {
            output_file = fopen(argv[i+1], "a");
            if (!output_file) {
                perror("Failed to open output file");
                exit(1);
            }
            i++; // skip the output file argument
        }
    }

    // redirect stdin and stdout
    dup2(fileno(input_file), fileno(stdin));
    dup2(fileno(output_file), fileno(stdout));


    /* keep reading input until "quit" command or eof of redirected input */
    while (!feof(stdin)) { 
        /* get command line from input */
        fputs (prompt, stdout); // write prompt

        if (fgets (buf, MAX_BUFFER, stdin )) { // read a line
            /* tokenize the input into args array */
            arg = args;
            *arg++ = strtok(buf,SEPARATORS);   // tokenise input

            while ((*arg++ = strtok(NULL,SEPARATORS)));

            // last entry will be NULL 
            if (args[0]) {     
                // if there's anything there
                /* check for internal/external command */

                
                if (!strcmp(args[0],"clr")) { // "clear" command
                    clr();      // Call clr function from utiity.h
                    continue;
                }

                // "quit" command
                if (!strcmp(args[0],"quit"))
                    quit();    // Call quit function from utiity.h                

                // cd command
                if (!strcmp(args[0], "cd")) { 
                    cd(args);       // Call cd function from utiity.h
                    continue;
                }

                // "dir" command
                if (!strcmp(args[0], "dir")) {
                    dir(args);      // Call dir function from utiity.h
                    continue;
                }

                // environ command: list environment variables
                if (!strcmp(args[0], "environ")) {
                    function_environ(); // Call function_environ function from utiity.h
                    continue;
                }

                // echo command: print arguments to stdout
                if (!strcmp(args[0], "echo")) {
                    echo(args);     // Call echo function from utiity.h
                    continue;
                }

                // pause command: wait for user to press enter
                if (!strcmp(args[0], "pause")) {
                    pause();        // Call pause function from utiity.h
                    continue;
                }

                // help command: Get User Manual
                if (!strcmp(args[0], "help")) {
                    help();     // Call help function from utiity.h
                    continue;
                }
            
                /* else pass command onto OS (or in this instance, print them out) */
                arg = args;
                while (*arg) {
                    fprintf(stdout,"No command: %s ",*arg++);
                    fputs ("\n", stdout);
                }
            }
        }
    }
    return 0; 
}