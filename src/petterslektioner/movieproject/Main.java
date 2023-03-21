package petterslektioner.movieproject;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Movie> movieArr = new ArrayList<>();
        createMovieArr(movieArr);

        for (Movie movie : movieArr
             ) {
            System.out.println(movie.toString());
        }

    }

    private static void createMovieArr(ArrayList<Movie> movieArr) {
        movieArr.add(new Movie(10, "Die Hard", "Action"));
        movieArr.add(new Movie(1, "Emoji Movie", "IDK "));
        movieArr.add(new Movie(6, "Never Gonna Give you up", "Rick Astly"));
        movieArr.add(new Movie(8, "Harry potter,", "Skräck"));
        movieArr.add(new Movie(9, "Avengers", "IDK"));
    }
}
