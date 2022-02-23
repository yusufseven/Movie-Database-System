public class Actor extends Performer
{
    private String height;

    public Actor(String id,
                 String name,
                 String surname,
                 String country,
                 String height)
    {
        super(id, name, surname, country);
        this.height = height;
        personType = "Actor";
    }

    public String getHeight()
    {
        return height;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }
}
