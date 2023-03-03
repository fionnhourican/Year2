import java.time.LocalDate;
import java.time.Year;

public class Book {

    private String title;
    private String author;
    private BookGenre genre;
    private int publicationDate;
    private int readDate;
    private BookMedium readMedium;
    private BookRating rating;

    // These are the mandatory options
    Book(String title, String author, BookGenre genre) {
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
    }

    // If Publication date provided
    Book(String title, String author, BookGenre genre, int publicationYear) {
        this(title, author, genre);
        this.publicationDate = publicationYear;
    }

    // If readdate, Medium and rating provided
    Book(String title, String author, BookGenre genre, int publicationYear, int readDate, BookMedium readMedium, BookRating rating) {
        this(title, author, genre, publicationYear);
        setReadDate(readDate);
        setMedium(readMedium);
        setRating(rating);
    }

    // Setter and Getter for title
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    // Setter and Getter for author
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor() {
        return this.author;
    }

    // Setter and Getter for genre
    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }
    public BookGenre getGenre() {
        return this.genre;
    }

    // Setter and Getter for publicationDate
    public void setPublishedDate(int publicationYear) {
        this.publicationDate = publicationYear;
    }
    public int getPublishedDate() {
        return this.publicationDate;
    }

    // Setter and Getter for readDate
    public void setReadDate(int readYear) {
        this.readDate = readYear;
    }
    public int getReadDate() {
        return this.readDate;
    }    

    // Setter and Getter for readMedium
    public void setMedium(BookMedium readMedium) {
        this.readMedium = readMedium;
    }
    public BookMedium getMedium() {
        return this.readMedium;
    }

    // Setter and Getter for Rating
    public void setRating(BookRating rating) {
        this.rating = rating;
    }
    public BookRating getRating() {
        return this.rating;
    }

    // To turn data into a string.
    public String toString() {
        if (rating != null) {

            String srating = "invalid";
            switch(rating) {
                case Rating1:
                    srating = "1/5";
                case Rating2:
                    srating = "2/5";
                  break;
                case Rating3:
                    srating = "3/5";
                case Rating4:
                    srating = "4/5";
                  break;
                case Rating5:
                    srating = "5/5";
            }

            return title + " by " + author + " (" + publicationDate + ") - read in " + readDate + ", rated " + srating;
        }
        if (publicationDate != 0) {
            return title + " by " + author + " (" + publicationDate + ")";
        }
        return title + " by " + author;
        }

    public static void main(String[] args) {

        // First Tests
        Book b1 = new Book("Children of Time", "Adrian Tchaikovsky", BookGenre.Fiction);
        System.out.println(b1);
        Book b2 = new Book("The Fifth Season", "N. K. Jemesin", BookGenre.Fiction, 2015);
        System.out.println(b2);
        Book b3 = new Book("Perdido Street Station", "China Mieville",
            BookGenre.Fiction, 2000, 2020, BookMedium.EBook, BookRating.Rating5);
        System.out.println(b3);

        /*
            TEST OUTPUT
        Children of Time by Adrian Tchaikovsky
        The Fifth Season by N. K. Jemesin (2015)
        Perdido Street Station by China Mieville (2000) - read in 2020, rated 5/5
        */

        System.out.println(b1.getTitle());
        System.out.println(b1.getAuthor());
        System.out.println(b1.getGenre());
        System.out.println(b2.getPublishedDate());
        System.out.println(b3.getReadDate());
        System.out.println(b3.getMedium());
        System.out.println(b3.getRating());

        /*
            TEST OUTPUT
        Children of Time
        Adrian Tchaikovsky
        Fiction
        2015
        2020
        EBook
        Rating5
        */
    }
}

enum BookGenre {
    Fiction, NonFiction;
}

enum BookMedium {
    PhysicalBook, EBook, AudioBook;
}

enum BookRating {
    Rating1, Rating2, Rating3, Rating4, Rating5;
}