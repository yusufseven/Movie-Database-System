public class Writer extends Artist
{
    private String writingStyle;

    public String getWritingStyle()
    {
        return writingStyle;
    }

    public void setWritingStyle(String writingStyle)
    {
        this.writingStyle = writingStyle;
    }

    public Writer(String id,
                  String name,
                  String surname,
                  String country,
                  String writingStyle)
    {
        super(id, name, surname, country);
        this.writingStyle = writingStyle;
        personType = "Writer";
    }
}
