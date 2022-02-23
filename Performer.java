public class Performer extends Artist
{
    public String performerName;
    public Performer(String id,
                     String name,
                     String surname,
                     String country)
    {
        super(id, name, surname, country);
        performerName = name;
    }

}
