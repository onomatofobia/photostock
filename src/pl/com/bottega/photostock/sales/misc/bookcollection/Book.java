package pl.com.bottega.photostock.sales.misc.bookcollection;

import java.util.HashSet;
import java.util.Set;

public class Book {

    private String title;
    private Person author; // klasa Person taka sama jak ta z zajęć
    private Set<Genre> genres = new HashSet<>();

    public Book(String title, Person author, Set<Genre> genres) {
        if(title == null || author == null || genres == null || genres.isEmpty())
            throw new IllegalArgumentException("All data required");
        this.title = title;
        this.author = author;
        this.genres.addAll(genres);

    }

    @Override
    public String toString() {
        return "\nBook{" +
                "title='" + title + '\'' +
                ", author=" + author +
                ", genres=" + genres +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Person getAuthor() {
        return author;
    }

    public boolean hasGenre(Genre genre) {
        return genres.contains(genre);
    }

    public boolean hasGenres(Set<Genre> genres) {
        return this.genres.containsAll(genres);
    }

    public boolean isWrittenBy(Person author){
        return this.author.equals(author);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!title.equals(book.title)) return false;
        if (!author.equals(book.author)) return false;
        return genres.equals(book.genres);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + genres.hashCode();
        return result;
    }
}

