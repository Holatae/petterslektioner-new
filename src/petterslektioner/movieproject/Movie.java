package petterslektioner.movieproject;

public class Movie {
    private final double rating;
    private final String title;
    private final String genre;

    public Movie(double rating, String title, String genre) {
        this.rating = rating;
        this.title = title;
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "rating=" + rating +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
