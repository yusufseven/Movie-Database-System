import java.util.ArrayList;

public class ShortFilm extends Film
{
    //runtime must be shorter than 40 mins

    private String releaseDate;
    private ArrayList<Writer> writers;
    private ArrayList<String> genres;

    public ShortFilm(String id,
                     String title,
                     String language,
                     ArrayList<Director> directors,
                     String runtime,
                     String country,
                     ArrayList<Performer> cast,
                     ArrayList<String> genres,
                     String releaseDate,
                     ArrayList<Writer> writers)
    {
        super(id, title, language, runtime, country, directors, cast);
        this.releaseDate = releaseDate;
        this.writers = writers;
        this.genres = genres;

        filmType = "ShortFilm";
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public ArrayList<Writer> getWriters()
    {
        return writers;
    }

    public void setWriters(ArrayList<Writer> writers)
    {
        this.writers = writers;
    }

    public ArrayList<String> getGenres()
    {
        return genres;
    }

    public void setGenres(ArrayList<String> genres)
    {
        this.genres = genres;
    }

}
