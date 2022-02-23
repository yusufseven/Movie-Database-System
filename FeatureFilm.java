import java.util.ArrayList;

public class FeatureFilm extends Film
{
    private String releaseDate;
    private String budget;
    private ArrayList<Writer> writers;
    private ArrayList<String> genres;

    public FeatureFilm(String id,
                       String title,
                       String language,
                       ArrayList<Director> directors,
                       String runtime,
                       String country,
                       ArrayList<Performer> cast,
                       ArrayList<String> genres,
                       String releaseDate,
                       ArrayList<Writer> writers,
                       String budget
    )
    {
        super(id, title, language, runtime, country, directors, cast);
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.writers = writers;
        this.genres = genres;

        filmType = "FeatureFilm";
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public String getBudget()
    {
        return budget;
    }

    public void setBudget(String budget)
    {
        this.budget = budget;
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
