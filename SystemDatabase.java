import java.util.ArrayList;

public class SystemDatabase
{
    private ArrayList<Person> personArrayList = new ArrayList<>();
    private ArrayList<Film> filmArrayList = new ArrayList<>();
    private ArrayList<String> personIdArrayList = new ArrayList<>();

    public ArrayList<Person> getPersonArrayList()
    {
        return personArrayList;
    }

    public void fillIdArrayList()
    {
        for (Person person : personArrayList)
        {
            personIdArrayList.add(person.getId());
        }
    }

    public ArrayList<Film> getFilmArrayList()
    {
        return filmArrayList;
    }

    public ArrayList<String> getPersonIdArrayList()
    {
        return personIdArrayList;
    }
}
