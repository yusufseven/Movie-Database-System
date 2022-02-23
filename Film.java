import java.util.ArrayList;

public class Film implements Comparable<Film>
{
    protected String id;
    protected String title;
    protected String language;
    protected String runtime;
    protected String country;
    protected ArrayList<Director> directors;
    protected ArrayList<Performer> cast;

    protected String filmType;
    protected ArrayList<String> ratings;

    public ArrayList<String> getRatings()
    {
        return ratings;
    }

    public void setRatings(ArrayList<String> ratings)
    {
        this.ratings = ratings;
    }

    public Film(String id,
                String title,
                String language,
                String runtime,
                String country,
                ArrayList<Director> directors,
                ArrayList<Performer> cast)
    {
        this.id = id;
        this.title = title;
        this.language = language;
        this.runtime = runtime;
        this.country = country;
        this.directors = directors;
        this.cast = cast;
        this.ratings = new ArrayList<>();
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getRuntime()
    {
        return runtime;
    }

    public void setRuntime(String runtime)
    {
        this.runtime = runtime;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public ArrayList<Director> getDirectors()
    {
        return directors;
    }

    public void setDirectors(ArrayList<Director> directors)
    {
        this.directors = directors;
    }

    public ArrayList<Performer> getCast()
    {
        return cast;
    }

    public void setCast(ArrayList<Performer> cast)
    {
        this.cast = cast;
    }

    public String getFilmType()
    {
        return filmType;
    }

    public void setFilmType(String filmType)
    {
        this.filmType = filmType;
    }

    protected String ratingSum = "";

    public int getVoterCount()
    {
        return voterCount;
    }

    public void setVoterCount(int voterCount)
    {
        this.voterCount = voterCount;
    }

    protected int voterCount = 0;
    protected double realRatingValue;

    public double getRealRatingValue()
    {
        return realRatingValue;
    }

    public void setRealRatingValue(double realRatingValue)
    {
        this.realRatingValue = realRatingValue;
    }

    // calculates average rating with using current values
    public void calculateRating()
    {
        voterCount = ratings.size();

        double ratingDouble = 0;
        for (String rating : ratings)
        {
            ratingDouble += Integer.parseInt(rating);
        }
        if (ratingDouble == 0)
        {
            ratingSum = "0";
            return;
        }
        if(voterCount == 0)
        {
            return;
        }
        ratingDouble = ratingDouble / voterCount;
        realRatingValue = ratingDouble;
        if ((int) ratingDouble == ratingDouble)
        {
            ratingSum = String.valueOf((int) ratingDouble);
        } else
        {
            ratingSum = String.valueOf(String.format("%.1f", ratingDouble)).replace('.', ',');
        }
    }

    public String getRatingSum()
    {
        return ratingSum;
    }

    public void setRatingSum(String ratingSum)
    {
        this.ratingSum = ratingSum;
    }

    @Override
    public int compareTo(Film o)
    {
        if (this.getRealRatingValue() > o.getRealRatingValue())
        {
            return -1;
        } else if (o.getRealRatingValue() > this.getRealRatingValue())
        {
            return 1;
        } else
        {
            return 0;
        }
    }
}
