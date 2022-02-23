import java.util.ArrayList;

public class Documentary extends Film
{
    private String releaseDate;

    public Documentary(String id,
                       String title,
                       String language,
                       ArrayList<Director> directors,
                       String runtime,
                       String country,
                       ArrayList<Performer> cast,
                       String releaseDate)
    {
        super(id, title, language, runtime, country, directors, cast);
        this.releaseDate = releaseDate;
        filmType = "Documentary";
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }
}
