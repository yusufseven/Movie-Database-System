public class ChildActor extends Performer
{
    private String age;

    public ChildActor(String id,
                      String name,
                      String surname,
                      String country,
                      String age)
    {
        super(id, name, surname, country);
        this.age = age;
        personType = "ChildActor";
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }
}
