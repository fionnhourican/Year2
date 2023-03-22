import java.time.LocalDate;
import java.time.Year;

/**
 * The FixItFelix class is the main class that contains the main method to run the program.
 * It tests the Book, PublishedBook, and ReadBook classes by creating instances of each class
 * and calling their respective methods to print out their information.
 * @author Fionn Hourican
 */

public class FixItFelix {

/**
 * The main method creates instances of Book, PublishedBook, and ReadBook classes,
 * prints out their information, and calls their respective methods to get and set
 * their attributes.
 * @param args - the command-line arguments
 */

    public static void main(String[] args) {

        // First Tests
        Book b1 = new Book("Children of Time", "Adrian Tchaikovsky", BookGenre.Fiction);
        System.out.println(b1);
        Book b2 = new PublishedBook("The Fifth Season", "N. K. Jemesin", BookGenre.Fiction, BookMedium.EBook, 2015);
        System.out.println(b2);
        Book b3 = new ReadBook("Perdido Street Station", "China Mieville",
            BookGenre.Fiction, 2000, BookMedium.EBook, 2020, BookRating.Rating5);
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
        if (b2 instanceof PublishedBook) {
            System.out.println(((PublishedBook) b2).getPublicationDate());
            System.out.println(((PublishedBook) b2).getMedium());
        }
        if (b3 instanceof ReadBook) {
            System.out.println(((ReadBook) b3).getReadDate());
            System.out.println(((ReadBook) b3).getRating());
        }

        /*
            TEST OUTPUT
        Children of Time
        Adrian Tchaikovsky
        Fiction
        2015
        EBook
        2020
        Rating5
        */
    }

    /**
     * Class Book is the parent class
     */
    static public class Book {

        /**
         * Variables declared in Book
         */
        private String title;
        private String author;
        private BookGenre genre;

        /**
         * The constructor Book creates a new instance of a Book object with the given title, author, and genre.
         * @param title - the title of the book
         * @param author - the author of the book
         * @param genre - the genre of the book
         */
        Book(String title, String author, BookGenre genre) {
            setTitle(title);
            setAuthor(author);
            setGenre(genre);
        }

        /**
         * setTitle method declared in class Book
         * @param title - the title of the book
         * @see Class Book
         */
        public void setTitle(String title) {
            this.title = title;
        }
        /**
         * getTitle method declared in class Book
         * @return String - Title of the book
         * @see Class Book
         */
        public String getTitle() {
            return this.title;
        }

        /**
         * setAuthor method declared in class Book
         * @param author - The author of the Book
         * @see Class Book
         */
        public void setAuthor(String author) {
            this.author = author;
        }
        /**
         * getAuthor method declared in class Book
         * @return String - The author of the Book
         * @see Class Book
         */
        public String getAuthor() {
            return this.author;
        }

        /**
         * setGenre method declared in class Book
         * @param genre - The genre of the Book
         * @see Class Book
         */
        public void setGenre(BookGenre genre) {
            this.genre = genre;
        }
        /**
         * getGenre method declared in class Book
         * @return BookGenre - genre of the Book
         * @see Class Book
         */
        public BookGenre getGenre() {
            return this.genre;
        }

        /**
         * toSring method declared in class Book
         * @return String - title by author
         * @see Class Book
         */
        public String toString() {
            return title + " by " + author;
            }
    }

    /**
     * class PublishedBook is the child class of Book
     */
    static public class PublishedBook extends Book {

        /**
         * variables declared in PublishedBook
         */
        private int publicationDate;
        private BookMedium medium;

        /**
         * The constructor PublishedBook creates a new instance of a PublishedBook object with the given title, author, genre, medium, and publication date.
         * @param title - the title of the book
         * @param author - the author of the book
         * @param genre - the genre of the book
         * @param medium - the medium of the book
         * @param publicationDate - the publication date of the book
         */
        public PublishedBook(String title, String author, BookGenre genre, BookMedium medium, int publicationDate) {
            super(title, author, genre);
            setPublicationDate(publicationDate);
            setMedium(medium);
        }

        /**
         * setPublicationDate method declared in class PublishedBook
         * @param publicationDate - The publication date of the PublishedBook
         * @see Class PublishedBook
         */
        public void setPublicationDate(int publicationDate) {
            this.publicationDate = publicationDate;
        }
        /**
         * getPublicationDate method declared in class PublishedBook
         * @return int - The Publication Date of the PublishedBook
         * @see Class PublishedBook
         */
        public int getPublicationDate() {
            return this.publicationDate;
        }

        /**
         * setMedium method declared in class PublishedBook
         * @param medium - The medium date of the PublishedBook
         * @see Class PublishedBook
         */
        public void setMedium(BookMedium medium) {
            this.medium = medium;
        }
        /**
         * getMedium method declared in class PublishedBook
         * @return BookMedium - The medium of the PublishedBook
         * @see Class PublishedBook
         */
        public BookMedium getMedium() {
            return this.medium;
        }

        /**
         * toSring method declared in class Book
         * @return String - Book tostring() + " (" + publicationDate + ")"
         * @see Class PublishedBook
         * @see Class Book
         */
        @Override
        public String toString() {
            return super.toString() + " (" + publicationDate + ")";
        }
    }

    /**
     * class ReadBook is the child class of PublishedBook
     */
    static public class ReadBook extends PublishedBook {

        /**
         * variables declared in ReadBook
         */
        private int readDate;
        private BookRating rating;

        /**
         * The constructor ReadBook creates a new instance of a ReadBook object with the given title, author, genre, medium, and publication date.
         * @param title - the title of the ReadBook
         * @param author - the author of the ReadBook
         * @param genre - the genre of the ReadBook
         * @param medium - the medium of the ReadBook
         * @param publicationDate - the publication date of the ReadBook
         * @param readDate - the read date of the ReadBook
         * @param rating - the rating of the ReadBook
         */
        public ReadBook(String title, String author, BookGenre genre, int publicationDate, BookMedium medium, int readDate, BookRating rating) {
            super(title, author, genre, medium, publicationDate);
            setReadDate(readDate);
            setRating(rating);
        }

        /**
         * setReadDate method declared in class ReadBook
         * @param readYear - The read date of the ReadBook
         * @see Class ReadBook
         */
        public void setReadDate(int readYear) {
            this.readDate = readYear;
        }
        /**
         * getReadDate method declared in class ReadBook
         * @return int - The read date of the ReadBook
         * @see Class ReadBook
         */
        public int getReadDate() {
            return this.readDate;
        }

        /**
         * setRating method declared in class ReadBook
         * @param rating - The rating of the ReadBook
         * @see Class ReadBook
         */
        public void setRating(BookRating rating) {
            this.rating = rating;
        }
        /**
         * getReadDate method declared in class ReadBook
         * @return BookRating - The rating of the ReadBook
         * @see Class ReadBook
         */
        public BookRating getRating() {
            return this.rating;
        }

        /**
         * toSring method declared in class Book
         * @return String - PublishedBook tostring() + " - read in " + readDate + ", rated " + srating
         * @see Class ReadBook
         * @see Class PublishedBook
         * @see Class Book
         */
        @Override
        public String toString() {
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
            return super.toString() + " - read in " + readDate + ", rated " + srating;
        }
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