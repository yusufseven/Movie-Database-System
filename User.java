import java.util.LinkedHashMap;

public class User extends Person
{
    private LinkedHashMap<Film, String> userRatingMap = new LinkedHashMap<>();

    public User(String id,
                String name,
                String surname,
                String country)
    {
        super(id, name, surname, country);
        personType = "User";
    }

    public LinkedHashMap<Film, String> getUserRatingMap()
    {
        return userRatingMap;
    }

    public void setUserRatingMap(LinkedHashMap<Film, String> userRatingMap)
    {
        this.userRatingMap = userRatingMap;
    }
}
