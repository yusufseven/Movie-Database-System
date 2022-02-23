public class Main
{
    public static void main(String[] args)
    {
        MovieDatabaseSystem movieDatabaseSystem = new MovieDatabaseSystem();
        movieDatabaseSystem.readPeople(args[0]);
        movieDatabaseSystem.readFilm(args[1]);
        movieDatabaseSystem.runCommands(args[2], args[3]);
    }
}
