import java.util.ArrayList;

public class TVSeries extends Film
{
    private String startDate;
    private String endDate;
    private String numberOfEpisodes;
    private String numberOfSeasons;
    private ArrayList<String> genres;
    private ArrayList<Writer> writers;

    public TVSeries(String id,
                    String title,
                    String language,
                    ArrayList<Director> directors,
                    String runtime,
                    String country,
                    ArrayList<Performer> cast,
                    ArrayList<String> genres,
                    ArrayList<Writer> writers,
                    String startDate,
                    String endDate,
                    String numberOfSeasons,
                    String numberOfEpisodes)
    {
        super(id, title, language, runtime, country, directors, cast);
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfEpisodes = numberOfEpisodes;
        this.numberOfSeasons = numberOfSeasons;
        this.genres = genres;
        this.writers = writers;
        filmType = "TVSeries";
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getNumberOfEpisodes()
    {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(String numberOfEpisodes)
    {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public String getNumberOfSeasons()
    {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(String numberOfSeasons)
    {
        this.numberOfSeasons = numberOfSeasons;
    }

    public ArrayList<String> getGenres()
    {
        return genres;
    }

    public void setGenres(ArrayList<String> genres)
    {
        this.genres = genres;
    }

    public ArrayList<Writer> getWriters()
    {
        return writers;
    }

    public void setWriters(ArrayList<Writer> writers)
    {
        this.writers = writers;
    }
}
