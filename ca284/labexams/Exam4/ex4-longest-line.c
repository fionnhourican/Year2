/*
 Author: Fionn Hourican
 Date: 13/12/2022

 name: ex4-longest-line.c
 requirements: Paragraph, shortest/longest
 returns: shortest/longest line (no. characters)
 Description:
    - displays the length of the sentence in the first line
    -  sentence in the second line

 My approach:
    - 
*/

/* includes */
#include <stdio.h> // For input and output
#include <stdlib.h> // For the atof() function
#include <string.h>

#define MAX_SENTENCE_LEN 200

// Data structure for storing a sentence in the paragraph.
struct Sentence {
    char content[MAX_SENTENCE_LEN + 1]; // Sentence content.
    int length; // Length of sentence (including ending punctuation).
    struct Sentence* next; // Pointer to the next sentence in the paragraph.
};
 
// Function prototypes.
void get_shortest_sentence(const struct Sentence* sentences);
void get_longest_sentence(const struct Sentence* sentences);
struct Sentence* parse_paragraph(const char* paragraph);
void short_long(char(*pSelection)(char), const struct Sentence* sentences);

int main(int argc, char* argv[]) 
{
    // Check if the correct number of arguments were provided.
    if (argc != 3) 
    {
        printf("Invalid number of arguments.\n");
        return 1;
    }
 
    // Parse the paragraph into a linked list of sentences.
    struct Sentence* sentences = parse_paragraph(argv[1]);

    // Check the second argument and call the appropriate function.
    if (strcmp(argv[2], "shortest") == 0) 
    {
        get_shortest_sentence(sentences);
    } else if (strcmp(argv[2], "longest") == 0) 
    {
        get_longest_sentence(sentences);
    } else 
    {
        printf("Not valid!\n");
    }
 
    // Free the allocated memory.
    while (sentences != NULL) 
    {
        struct Sentence* next = sentences->next;
        free(sentences);
        sentences = next;
    }

    return 0;
}

// Function that prints the shortest sentence in the paragraph.
void get_shortest_sentence(const struct Sentence* sentences) 
{
    // Initialize variables to store the length and content of the shortest sentence.
    int shortest_length = MAX_SENTENCE_LEN + 1;
    char shortest_content[MAX_SENTENCE_LEN + 1];

    // Iterate through the linked list of sentences and update the
    // shortest sentence if a shorter one is found.
    while (sentences != NULL) 
    {
        if (sentences->length < shortest_length) 
        {
            shortest_length = sentences->length;
            // Use strncpy to safely copy the sentence content.
            strncpy(shortest_content, sentences->content, shortest_length);
            shortest_content[shortest_length] = '\0'; // Add the null terminator.
        }
        sentences = sentences->next;
    }
    // Print the length and content of the shortest sentence.
    printf("%d\n%s\n",shortest_length, shortest_content);
}

// Function that prints the longest sentence in the paragraph.
void get_longest_sentence(const struct Sentence* sentences) 
{
    // Initialize variables to store the length and content of the longest sentence.
    int longest_length = 0;
    char longest_content[MAX_SENTENCE_LEN + 1];

    // Iterate through the linked list of sentences and update the
    // longest sentence if a longer one is found.
    while (sentences != NULL) 
    {
        if (sentences->length > longest_length) 
        {
            longest_length = sentences->length;
            // Use strncpy to safely copy the sentence content.
            strncpy(longest_content, sentences->content, longest_length);
            longest_content[longest_length] = '\0'; // Add the null terminator.
        }
        sentences = sentences->next;
    }

    // Print the length and content of the longest sentence.
    printf("%d\n%s\n", longest_length - 1, longest_content);
}

// Function for parsing a paragraph into a linked list of sentences.
struct Sentence* parse_paragraph(const char* paragraph) 
{
    // Create a linked list to store the sentences.
    struct Sentence* sentences = NULL;
    struct Sentence* last = NULL;

    // Create a buffer for storing the current sentence.
    char buffer[MAX_SENTENCE_LEN + 1];
    int buffer_index = 0;

    // Iterate through the characters in the paragraph and parse
    // the sentences into the linked list.
    for (int i = 0; i < strlen(paragraph); i++) 
    {
        char c = paragraph[i];
        buffer[buffer_index++] = c; // Add the character to the buffer.

        // Check if the current character is an ending punctuation
        // mark or if the buffer is full.
        if (c == '.' || c == '?' || c == '!') 
        {
            // Create a new sentence structure and copy the contents
            // of the buffer into it.
            struct Sentence* s = malloc(sizeof(struct Sentence));
            strcpy(s->content, buffer);
            s->length = buffer_index;
            s->next = NULL;

            // Add the sentence to the linked list.
            if (sentences == NULL) 
            {
                sentences = s;
            } else 
            {
                last->next = s;
            }
            last = s;

            // Reset the buffer.
            buffer_index = 0;
            memset(buffer, 0, MAX_SENTENCE_LEN + 1);
        }
    }
    // Return the linked list of sentences.
    return sentences;
}

void short_long(char(*pSelection)(char), const struct Sentence* sentences)
{
    return pSelection(sentences);
}