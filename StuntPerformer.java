public class StuntPerformer extends Performer
{
    private String height;
    private String realActorId;

    public StuntPerformer(String id,
                          String name,
                          String surname,
                          String country,
                          String height,
                          String realActorId)
    {
        super(id, name, surname, country);
        this.height = height;
        this.realActorId = realActorId;
        personType = "StuntPerformer";
    }

    public String getHeight()
    {
        return height;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }

    public String getRealActorId()
    {
        return realActorId;
    }

    public void setRealActorId(String realActorId)
    {
        this.realActorId = realActorId;
    }
}
