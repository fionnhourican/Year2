/*
Name: Fionn Hourican
Student Number: 21477216
I, Fionn Hourican, acknowledge all of DCU's Academic Integrity Policies.
*/

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>                            // for chdir()
#include <string.h>
#include <stdbool.h>
#define MAX_BUFFER 1024                        // max line buffer
#define MAX_ARGS 64                            // max # args
#define SEPARATORS " \t\n"                     // token separators
extern char **environ;                          // declare the environ variable

// Function declarations
void cd(char *args[]);
void clr();
void dir(char *args[]);
void function_environ();
void echo(char *args[]);
void help();
void pause();
int quit();

// cd command
void cd(char *args[]) { 
    // if an argument is provided
    // changes the current working directory using chdir
    if (args[1]) {
        if (chdir(args[1]) != 0) { 
            // attempt to change directory
            perror("cd");
        }
    }
    // no argument provided, print current working directory
    else {
        char cwd[MAX_BUFFER];
        if (getcwd(cwd, sizeof(cwd)) != NULL) {
            printf("%s\n", cwd);
        }
        else {
            perror("getcwd");
        }
    }
}

// "clear" command
void clr() {
    // clears the terminal window by calling the clear command using system function.
    system("clear");
}

// "dir" command
void dir(char *args[]) {
    if (args[1]) {             // if a directory is provided
        char* cmd = (char*)malloc(strlen(args[0]) + strlen(args[1]) + 2);
        sprintf(cmd, "%s %s", args[0], args[1]);
        system(cmd);
        free(cmd);
    } else {                    // no directory provided, list current directory
        system("ls -al .");
    }
}

// environ command: list environment variables.
void function_environ() {
    char **env = environ;
    while (*env) {
        printf("%s\n", *env++);
    }
}

// echo command: print arguments to stdout
void echo(char *args[]) {
    char ** arg;
    arg = args + 1;
    while (*arg) {
        fprintf(stdout, "%s ", *arg++);
    }
    fputs("\n", stdout);
}

// help command: Get User Manual
void help(){
    // display the readme file using the cat command and more filter
    system("cat ../manual/readme.md | more");
}

// pause command: wait for user to press enter
void pause() {
    printf("Shell paused. Press enter to continue.\n");
    getchar();
}

// quit command
int quit() {
    // Exit programme
    exit(0);
}