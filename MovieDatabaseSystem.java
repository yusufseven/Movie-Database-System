import java.util.ArrayList;
import java.util.Collections;


public class MovieDatabaseSystem
{
    SystemDatabase sysDB = new SystemDatabase();
    FileWrite fileWrite = new FileWrite();

    // Reads, creates objects and fills sysDB witch created object
    public void readPeople(String fileName)
    {
        FileRead fileRead = new FileRead();
        String[] peopleLineArray = fileRead.readFile(fileName);
        for (String line : peopleLineArray)
        {
            String[] currentLine = line.split("\t");
            switch (currentLine[0])
            {
                case ("User:"):
                    User user = new User(currentLine[1], currentLine[2], currentLine[3], currentLine[4]); // id name surname country
                    sysDB.getPersonArrayList().add(user);
                    break;
                case ("Director:"):
                    Director director = new Director(currentLine[1], currentLine[2], currentLine[3], currentLine[4], currentLine[5]); // id name surname country agent
                    sysDB.getPersonArrayList().add(director);
                    break;
                case ("Writer:"):
                    Writer writer = new Writer(currentLine[1], currentLine[2], currentLine[3], currentLine[4], currentLine[5]);   // id name surname country writingStyle
                    sysDB.getPersonArrayList().add(writer);
                    break;
                case ("Actor:"):
                    Actor actor = new Actor(currentLine[1], currentLine[2], currentLine[3], currentLine[4], currentLine[5]);  // id name surname country height
                    sysDB.getPersonArrayList().add(actor);
                    break;
                case ("ChildActor:"):
                    ChildActor childActor = new ChildActor(currentLine[1], currentLine[2], currentLine[3], currentLine[4], currentLine[5]);  // id name surname country age
                    sysDB.getPersonArrayList().add(childActor);
                    break;
                case ("StuntPerformer:"):
                    StuntPerformer stuntPerformer = new StuntPerformer(currentLine[1], currentLine[2], currentLine[3], currentLine[4], currentLine[5], currentLine[6]);  // id name surname country height realActorIds
                    sysDB.getPersonArrayList().add(stuntPerformer);
                    break;
            }
        }
        sysDB.fillIdArrayList();
    }

    // Reads, creates objects and fills sysDB witch created object
    public void readFilm(String fileName)
    {
        FileRead fileRead = new FileRead();
        String[] filmLineArray = fileRead.readFile(fileName);

        for (String line : filmLineArray)
        {
            String[] currentLine = line.split("\t");
            switch (currentLine[0])
            {
                case ("FeatureFilm:"):
                    FeatureFilm featureFilm = new FeatureFilm(currentLine[1], currentLine[2], currentLine[3], readDirector(currentLine[4]), currentLine[5], currentLine[6], readCast(currentLine[7]), readGenre(currentLine[8]), currentLine[9], readWriter(currentLine[10]), currentLine[11]);
                    sysDB.getFilmArrayList().add(featureFilm);
                    break;
                case ("ShortFilm:"):
                    ShortFilm shortFilm = new ShortFilm(currentLine[1], currentLine[2], currentLine[3], readDirector(currentLine[4]), currentLine[5], currentLine[6], readCast(currentLine[7]), readGenre(currentLine[8]), currentLine[9], readWriter(currentLine[10]));

                    if (Integer.parseInt(shortFilm.getRuntime()) > 40)
                    {
                        System.out.println("Short film cannot be longer than 40 mins.");
                    } else
                    {
                        sysDB.getFilmArrayList().add(shortFilm);
                    }
                    break;
                case ("TVSeries:"):
                    TVSeries tvSeries = new TVSeries(currentLine[1], currentLine[2], currentLine[3], readDirector(currentLine[4]), currentLine[5], currentLine[6], readCast(currentLine[7]), readGenre(currentLine[8]), readWriter(currentLine[9]), currentLine[10], currentLine[11], currentLine[12], currentLine[13]);
                    sysDB.getFilmArrayList().add(tvSeries);
                    break;
                case ("Documentary:"):
                    Documentary documentary = new Documentary(currentLine[1], currentLine[2], currentLine[3], readDirector(currentLine[4]), currentLine[5], currentLine[6], readCast(currentLine[7]), currentLine[8]);
                    sysDB.getFilmArrayList().add(documentary);
                    break;
            }

        }

    }

    // Helper method that uses unsplit ids and returns them in an ArrayList
    private ArrayList<Director> readDirector(String rawDirectorId)
    {
        ArrayList<Director> directorArrayList = new ArrayList<>();
        String[] directorIdArray = rawDirectorId.split(",");
        for (String id : directorIdArray)
        {
            for (Person person : sysDB.getPersonArrayList())
            {
                if (id.equals(person.getId()))
                {
                    if (id.equals(person.getId()))
                    {
                        directorArrayList.add((Director) person);
                    }
                }
            }
        }
        return directorArrayList;
    }

    // Helper method that uses unsplit ids and returns them in an ArrayList
    private ArrayList<Writer> readWriter(String rawWriterId)
    {
        ArrayList<Writer> writerArrayList = new ArrayList<>();
        String[] writerIdArray = rawWriterId.split(",");
        for (String id : writerIdArray)
        {
            for (Person person : sysDB.getPersonArrayList())
            {
                if (id.equals(person.getId()))
                {
                    writerArrayList.add((Writer) person);
                }
            }
        }
        return writerArrayList;
    }

    // Helper method that uses unsplit ids and returns them in an ArrayList
    private ArrayList<Performer> readCast(String rawCastId)
    {
        ArrayList<Performer> castArrayList = new ArrayList<>();
        String[] castIdArray = rawCastId.split(",");
        for (String id : castIdArray)
        {
            for (Person person : sysDB.getPersonArrayList())
            {
                if (id.equals(person.getId()))
                {
                    switch (person.getPersonType())
                    {
                        case ("Actor"):
                            castArrayList.add((Actor) person);
                            break;
                        case ("ChildActor"):
                            castArrayList.add((ChildActor) person);
                            break;
                        case ("StuntPerformer"):
                            castArrayList.add((StuntPerformer) person);
                            break;
                    }
                }
            }
        }
        return castArrayList;
    }

    // Helper method that uses unsplit ids and returns them in an ArrayList
    private ArrayList<String> readGenre(String rawGenreName)
    {
        ArrayList<String> genreArrayList = new ArrayList<>();

        String[] genreArray = rawGenreName.split(",");
        for (String genre : genreArray)
        {
            genreArrayList.add(genre);
        }
        return genreArrayList;
    }

    private String outputFileName;

    //
    public void runCommands(String fileName, String oFileName)
    {
        outputFileName = oFileName;

        FileRead fileRead = new FileRead();
        fileWrite.deleteFile(outputFileName);   // deleting outputfile if exists before
        fileWrite.createFile(outputFileName);   // creating outputfile
        String[] commandArray = fileRead.readFile(fileName);
        for (String line : commandArray)
        {
            String[] splitLine = line.split("\t");
            switch (splitLine[0])
            {
                case ("RATE"):
                    //rate command
                    fileWrite.write(outputFileName, line + "\n\n");
                    userRate(splitLine[1], splitLine[2], splitLine[3]);
                    fileWrite.write(outputFileName, "\n" + "\n-----------------------------------------------------------------------------------------------------\n");
                    break;
                case ("ADD"):
                    //add command
                    fileWrite.write(outputFileName, line + "\n\n");
                    addFeatureFilm(splitLine[2], splitLine[3], splitLine[4], splitLine[5], splitLine[6], splitLine[7], splitLine[8], splitLine[9], splitLine[10], splitLine[11], splitLine[12]);
                    fileWrite.write(outputFileName, "\n" + "\n-----------------------------------------------------------------------------------------------------\n");
                    break;
                case ("VIEWFILM"):
                    //viewfilm command
                    fileWrite.write(outputFileName, line + "\n\n");
                    viewFilm(splitLine[1]);
                    fileWrite.write(outputFileName, "\n" + "\n-----------------------------------------------------------------------------------------------------\n");
                    break;
                case ("LIST"):
                    //list main command
                    switch (splitLine[1])
                    {
                        case ("USER"):
                            //list user ratings
                            fileWrite.write(outputFileName, line + "\n\n");
                            listUserRates(splitLine[2]);
                            fileWrite.write(outputFileName, "\n" + "-----------------------------------------------------------------------------------------------------\n");
                            break;
                        case ("FILM"):
                            //list tv series
                            fileWrite.write(outputFileName, line + "\n\n");
                            listTVSeries();
                            fileWrite.write(outputFileName, "-----------------------------------------------------------------------------------------------------\n");
                            break;
                        case ("FILMS"):
                            //list films either by country or rate
                            switch (splitLine[3])
                            {
                                case ("COUNTRY"):
                                    // list by country
                                    fileWrite.write(outputFileName, line + "\n\n");
                                    listFilmsByCountry(splitLine[4]);
                                    fileWrite.write(outputFileName, "-----------------------------------------------------------------------------------------------------\n");
                                    break;
                                case ("RATE"):
                                    // list by rating
                                    fileWrite.write(outputFileName, line + "\n\n");
                                    listFilmsByRateDegree();
                                    fileWrite.write(outputFileName, "\n" + "-----------------------------------------------------------------------------------------------------\n");
                                    break;
                            }
                            break;
                        case ("FEATUREFILMS"):
                            //list films either before year or after year
                            switch (splitLine[2])
                            {
                                case ("BEFORE"):
                                    // list feature films before given year
                                    fileWrite.write(outputFileName, line + "\n\n");
                                    listFeatureFilmsBefore(splitLine[3]);
                                    fileWrite.write(outputFileName, "-----------------------------------------------------------------------------------------------------\n");
                                    break;
                                case ("AFTER"):
                                    // list feature films after given year
                                    fileWrite.write(outputFileName, line + "\n\n");
                                    listFeatureFilmsAfter(splitLine[3]);
                                    fileWrite.write(outputFileName, "-----------------------------------------------------------------------------------------------------\n");

                                    break;
                            }
                            break;
                        case ("ARTISTS"):
                            //list artists from country
                            fileWrite.write(outputFileName, line + "\n\n");
                            listArtistsFromCountry(splitLine[3]);
                            fileWrite.write(outputFileName, "\n-----------------------------------------------------------------------------------------------------\n");
                            break;
                    }
                    break;
                case ("EDIT"):
                    //edit user rating
                    fileWrite.write(outputFileName, line + "\n\n");
                    editUserRate(splitLine[2], splitLine[3], splitLine[4]);
                    fileWrite.write(outputFileName, "\n" + "\n-----------------------------------------------------------------------------------------------------\n");
                    break;
                case ("REMOVE"):
                    //remove user rating
                    fileWrite.write(outputFileName, line + "\n\n");
                    removeUserRate(splitLine[2], splitLine[3]);
                    fileWrite.write(outputFileName, "\n" + "\n-----------------------------------------------------------------------------------------------------\n");
                    break;
            }
        }
    }

    // user rates a movie, result is stored both in user and film
    private void userRate(String userId, String filmId, String rating)
    {
        boolean success = false;
        for (Person person : sysDB.getPersonArrayList())
        {
            if (person.getPersonType().equals("User") && person.getId().equals(userId))
            {
                User user = (User) person;
                for (Film film : sysDB.getFilmArrayList())
                {
                    if (film.getId().equals(filmId))
                    {
                        if (user.getUserRatingMap().containsKey(film))
                        {
                            fileWrite.write(outputFileName, "This film was earlier rated");
                            return;
                        }
                        user.getUserRatingMap().put(film, rating);
                        film.getRatings().add(rating);
                        film.calculateRating();
                        fileWrite.write(outputFileName, "Film rated successfully" + '\n' + "Film type: " + film.getFilmType() + '\n' + "Film title: " + film.getTitle());
                        success = true;
                    }
                }
            }
        }
        if (!success)
        {
            // an error other than the film being rated by this user
            fileWrite.write(outputFileName, "Command Failed" + '\n' + "User ID: " + userId + '\n' + "Film ID: " + filmId);
        }
    }

    // adds feature film to the system if valid
    private void addFeatureFilm(String id, String title, String language, String rawDirectorId, String length, String country, String rawCastId, String rawGenres, String releaseDate, String rawWriterId, String budget)
    {
        boolean errorCheck = false;
        ArrayList<Director> directors = readDirector(rawDirectorId);
        ArrayList<Performer> cast = readCast(rawCastId);
        ArrayList<Writer> writers = readWriter(rawWriterId);

        String[] directorIdArray = rawDirectorId.split(",");
        String[] castIdArray = rawCastId.split(",");
        String[] writerIdArray = rawWriterId.split(",");

        // such performer does not exist
        for (String perfId : castIdArray)
        {
            if (!sysDB.getPersonIdArrayList().contains(perfId))
            {
                errorCheck = true;
            }
        }

        // such writer does not exist
        for (String wrtId : writerIdArray)
        {
            if (!sysDB.getPersonIdArrayList().contains(wrtId))
            {
                errorCheck = true;
            }
        }

        // such director does not exist
        for (String dirId : directorIdArray)
        {
            if (!sysDB.getPersonIdArrayList().contains(dirId))
            {
                errorCheck = true;
            }
        }

        // at least one of the artist arrays are empty which is not possible
        if (directors.isEmpty() || cast.isEmpty() || writers.isEmpty())
        {
            errorCheck = true;
        }
        for (Director director : directors)
        {
            if (!sysDB.getPersonArrayList().contains(director))
            {
                errorCheck = true;
                break;
            }
        }
        for (Performer performer : cast)
        {
            if (!sysDB.getPersonArrayList().contains(performer))
            {
                errorCheck = true;
                break;
            }
        }
        for (Writer writer : writers)
        {
            if (!sysDB.getPersonArrayList().contains(writer))
            {
                errorCheck = true;
                break;
            }
        }

        // if any of the error checks are true, then the bool errorCheck is true which causes the program to print the message below and cancel the process
        if (errorCheck)
        {
            fileWrite.write(outputFileName, "Command Failed" + '\n' + "Film ID: " + id + '\n' + "Film title: " + title);
            return;
        }

        for (Film film : sysDB.getFilmArrayList())
        {
            if (film.getId().equals(id))
            {
                fileWrite.write(outputFileName, "Command Failed" + '\n' + "Film ID: " + id + '\n' + "Film title: " + title);
                return;
            }
        }
        FeatureFilm featureFilm = new FeatureFilm(id, title, language, directors, length, country, cast, readGenre(rawGenres), releaseDate, writers, budget);
        sysDB.getFilmArrayList().add(featureFilm);
        fileWrite.write(outputFileName, "FeatureFilm added successfully" + '\n' + "Film ID: " + featureFilm.getId() + '\n' + "Film title: " + featureFilm.getTitle());
    }

    // displays the film
    private void viewFilm(String id)
    {
        for (Film film : sysDB.getFilmArrayList())
        {
            if (film.getId().equals(id))
            {
                if (film.getFilmType().equals("FeatureFilm"))
                {
                    FeatureFilm currentFilm = (FeatureFilm) film;
                    // calculates rating just in case if it is not calculated
                    currentFilm.calculateRating();
                    fileWrite.write(outputFileName, film.getTitle() + " (" + currentFilm.getReleaseDate().split("\\.")[2] + ")\n"
                            + genresToString(currentFilm.getGenres()) + "\n"
                            + "Writers: " + writersToString(currentFilm.getWriters()) + "\n"
                            + "Directors: " + directorsToString(currentFilm.getDirectors()) + "\n"
                            + "Stars: " + performersToString(currentFilm.getCast()) + "\n"
                    );
                    if (film.getRatings().isEmpty())
                    {
                        fileWrite.write(outputFileName, "Awaiting for votes");
                    } else
                    {
                        fileWrite.write(outputFileName, "Ratings: " + currentFilm.getRatingSum() + "/10" + " from " + currentFilm.getVoterCount() + " users");
                    }
                    return;

                } else if (film.getFilmType().equals("ShortFilm"))
                {
                    ShortFilm currentFilm = (ShortFilm) film;
                    // calculates rating just in case if it is not calculated
                    currentFilm.calculateRating();
                    fileWrite.write(outputFileName, currentFilm.getTitle() + " (" + currentFilm.getReleaseDate().split("\\.")[2] + ")\n"
                            + genresToString(currentFilm.getGenres()) + "\n"
                            + "Writers: " + writersToString(currentFilm.getWriters()) + "\n"
                            + "Directors: " + directorsToString(currentFilm.getDirectors()) + "\n"
                            + "Stars: " + performersToString(currentFilm.getCast()) + "\n"
                    );
                    if (film.getRatings().isEmpty())
                    {
                        fileWrite.write(outputFileName, "Awaiting for votes");
                    } else
                    {
                        fileWrite.write(outputFileName, "Ratings: " + currentFilm.getRatingSum() + "/10" + " from " + currentFilm.getVoterCount() + " users");
                    }
                    return;
                } else if (film.getFilmType().equals("Documentary"))
                {
                    Documentary currentFilm = (Documentary) film;
                    // calculates rating just in case if it is not calculated
                    currentFilm.calculateRating();
                    fileWrite.write(outputFileName, film.getTitle() + " (" + currentFilm.getReleaseDate().split("\\.")[2] + ")\n\n"
                            + "Directors: " + directorsToString(currentFilm.getDirectors()) + "\n"
                            + "Stars: " + performersToString(currentFilm.getCast()) + "\n"
                    );
                    if (film.getRatings().isEmpty())
                    {
                        fileWrite.write(outputFileName, "Awaiting for votes");
                    } else
                    {
                        fileWrite.write(outputFileName, "Ratings: " + currentFilm.getRatingSum() + "/10" + " from " + currentFilm.getVoterCount() + " users");
                    }
                    return;
                } else if (film.getFilmType().equals("TVSeries"))
                {
                    TVSeries currentFilm = (TVSeries) film;
                    // calculates rating just in case if it is not calculated
                    currentFilm.calculateRating();
                    fileWrite.write(outputFileName, currentFilm.getTitle() + " (" + currentFilm.getStartDate().split("\\.")[2] + "-" + currentFilm.getEndDate().split("\\.")[2] + ")\n"
                            + currentFilm.getNumberOfSeasons() + " seasons, " + currentFilm.getNumberOfEpisodes() + " episodes\n"
                            + genresToString(currentFilm.getGenres()) + "\n"
                            + "Writers: " + writersToString(currentFilm.getWriters()) + "\n"
                            + "Directors: " + directorsToString(currentFilm.getDirectors()) + "\n"
                            + "Stars: " + performersToString(currentFilm.getCast()) + "\n"
                    );
                    if (film.getRatings().isEmpty())
                    {
                        fileWrite.write(outputFileName, "Awaiting for votes");
                    } else
                    {
                        fileWrite.write(outputFileName, "Ratings: " + currentFilm.getRatingSum() + "/10" + " from " + currentFilm.getVoterCount() + " users");
                    }
                    return;
                }
            }
        }
        fileWrite.write(outputFileName, "Command Failed");
        fileWrite.write(outputFileName, "Film ID: " + id);
    }

    // listing user's ratings if there are any
    private void listUserRates(String id)
    {
        boolean userHasRating = false;
        for (Person person : sysDB.getPersonArrayList())
        {
            if (id.equals(person.getId()) && person.getPersonType().equals("User"))
            {
                User currentUser = (User) person;
                if (currentUser.getUserRatingMap().isEmpty())
                {
                    fileWrite.write(outputFileName, "Command Failed\n" + "User ID: " + id + "\n");
                    return;
                }
                for (Film entry : currentUser.getUserRatingMap().keySet())
                {
                    fileWrite.write(outputFileName, entry.getTitle() + ": " + currentUser.getUserRatingMap().get(entry) + "\n");
                    userHasRating = true;
                }
            }
        }
        if (!userHasRating)
        {
            fileWrite.write(outputFileName, "Command Failed\n" + "User ID: " + id + "\n");
        }
    }

    // edits user's rating
    private void editUserRate(String userId, String filmId, String newRatingPoint)
    {
        boolean filmFound = false;
        Film currentFilm = null;
        for (Film film : sysDB.getFilmArrayList())
        {
            if (filmId.equals(film.getId()))
            {
                currentFilm = film;
                filmFound = true;
            }
        }
        if (!filmFound)
        {
            fileWrite.write(outputFileName, "Command Failed\n" + "User ID: " + userId + "\nFilm ID: " + filmId);
            return;
        }
        for (Person person : sysDB.getPersonArrayList())
        {
            if (userId.equals(person.getId()))
            {
                User currentUser = (User) person;
                if (!currentUser.getUserRatingMap().containsKey(currentFilm))
                {
                    fileWrite.write(outputFileName, "Command Failed\n" + "User ID: " + userId + "\nFilm ID: " + filmId);
                    return;
                }
                String voteValue = currentUser.getUserRatingMap().get(currentFilm);
                currentFilm.getRatings().remove(voteValue);
                currentFilm.getRatings().add(newRatingPoint);
                currentUser.getUserRatingMap().put(currentFilm, newRatingPoint);
                currentFilm.calculateRating();
                fileWrite.write(outputFileName, "New ratings done successfully\n" + "Film title: " + currentFilm.getTitle() + "\n" + "Your rating: " + newRatingPoint);
                return;
            }
        }
        fileWrite.write(outputFileName, "Command Failed\n" + "User ID: " + userId + "\nFilm ID: " + filmId);
    }

    // remove user ratings
    private void removeUserRate(String userId, String filmId)
    {
        boolean filmFound = false;
        Film currentFilm = null;
        for (Film film : sysDB.getFilmArrayList())
        {
            if (filmId.equals(film.getId()))
            {
                currentFilm = film;
                filmFound = true;
            }
        }
        if (!filmFound)
        {
            fileWrite.write(outputFileName, "Command Failed\n" + "User ID: " + userId + "\nFilm ID: " + filmId);
            return;
        }
        for (Person person : sysDB.getPersonArrayList())
        {
            if (userId.equals(person.getId()))
            {
                User currentUser = (User) person;
                if (!currentUser.getUserRatingMap().containsKey(currentFilm))
                {
                    fileWrite.write(outputFileName, "Command Failed\n" + "User ID: " + userId + "\nFilm ID: " + filmId);
                    return;
                }
                String voteValue = currentUser.getUserRatingMap().get(currentFilm);
                currentFilm.getRatings().remove(voteValue);
                currentUser.getUserRatingMap().remove(currentFilm);
                currentFilm.calculateRating();
                fileWrite.write(outputFileName, "Your film rating was removed successfully\n" + "Film title: " + currentFilm.getTitle());
                return;
            }
        }
        fileWrite.write(outputFileName, "Command Failed\n" + "User ID: " + userId + "\nFilm ID: " + filmId);
    }

    // list all tv series and information about them.
    private void listTVSeries()
    {
        boolean filmFound = false;
        for (Film film : sysDB.getFilmArrayList())
        {
            if (film.getFilmType().equals("TVSeries"))
            {
                TVSeries currentFilm = (TVSeries) film;
                fileWrite.write(outputFileName, currentFilm.getTitle() + " (" + currentFilm.getStartDate().split("\\.")[2] + "-" + currentFilm.getEndDate().split("\\.")[2] + ")\n"
                        + currentFilm.getNumberOfSeasons() + " seasons and " + currentFilm.getNumberOfEpisodes() + " episodes\n\n");
                filmFound = true;
            }
        }
        if (!filmFound)
        {
            fileWrite.write(outputFileName, "No result");
        }
    }

    // lists and groups films by their kinds by specified country
    private void listFilmsByCountry(String country)
    {
        boolean filmFound = false;
        for (Film film : sysDB.getFilmArrayList())
        {
            if (film.getCountry().equals(country))
            {
                fileWrite.write(outputFileName, "Film title: " + film.getTitle() + "\n" + film.getRuntime() + " min\n" + "Language: " + film.getLanguage() + "\n\n");
                filmFound = true;
            }
        }
        if (!filmFound)
        {
            fileWrite.write(outputFileName, "No result\n\n");
        }
    }

    // lists films before given year
    private void listFeatureFilmsBefore(String year)
    {
        boolean foundFilm = false;
        for (Film film : sysDB.getFilmArrayList())
        {
            if (film.getFilmType().equals("FeatureFilm"))
            {
                FeatureFilm currentFilm = (FeatureFilm) film;
                if (Integer.parseInt(currentFilm.getReleaseDate().split("\\.")[2]) < Integer.parseInt(year))
                {
                    fileWrite.write(outputFileName, "Film title: " + currentFilm.getTitle() + " (" + currentFilm.getReleaseDate().split("\\.")[2] + ")\n"
                            + currentFilm.getRuntime() + " min\n"
                            + "Language: " + currentFilm.getLanguage() + "\n\n");
                    foundFilm = true;
                }
            }
        }
        if (!foundFilm)
        {
            fileWrite.write(outputFileName, "No result\n\n");
        }
    }

    // lists films after given year
    private void listFeatureFilmsAfter(String year)
    {
        boolean foundFilm = false;
        for (Film film : sysDB.getFilmArrayList())
        {
            if (film.getFilmType().equals("FeatureFilm"))
            {
                FeatureFilm currentFilm = (FeatureFilm) film;
                if (Integer.parseInt(currentFilm.getReleaseDate().split("\\.")[2]) >= Integer.parseInt(year))
                {
                    fileWrite.write(outputFileName, "Film title: " + currentFilm.getTitle() + " (" + currentFilm.getReleaseDate().split("\\.")[2] + ")\n"
                            + currentFilm.getRuntime() + " min\n"
                            + "Language: " + currentFilm.getLanguage() + "\n\n");
                    foundFilm = true;
                }
            }
        }
        if (!foundFilm)
        {
            fileWrite.write(outputFileName, "No result\n\n");
        }
    }

    private void listFilmsByRateDegree()
    {
        ArrayList<FeatureFilm> featureFilms = new ArrayList<>();
        ArrayList<ShortFilm> shortFilms = new ArrayList<>();
        ArrayList<Documentary> documentaries = new ArrayList<>();
        ArrayList<TVSeries> tvSeries = new ArrayList<>();

        for (Film film : sysDB.getFilmArrayList())
        {
            film.calculateRating();
            if (film.getFilmType().equals("FeatureFilm"))
            {
                featureFilms.add((FeatureFilm) film);
            } else if (film.getFilmType().equals("ShortFilm"))
            {
                shortFilms.add((ShortFilm) film);
            } else if (film.getFilmType().equals("Documentary"))
            {
                documentaries.add((Documentary) film);
            } else if (film.getFilmType().equals("TVSeries"))
            {
                tvSeries.add((TVSeries) film);
            }
        }
        Collections.sort(featureFilms);
        Collections.sort(shortFilms);
        Collections.sort(documentaries);
        Collections.sort(tvSeries);

        if (featureFilms.isEmpty())
        {
            fileWrite.write(outputFileName, "No result");
        } else
        {
            fileWrite.write(outputFileName, "FeatureFilm:\n");
            for (FeatureFilm featureFilm : featureFilms)
            {
                fileWrite.write(outputFileName, featureFilm.getTitle() + " (" + featureFilm.getReleaseDate().split("\\.")[2] + ") " + "Ratings: " + featureFilm.getRatingSum() + "/10 from " + featureFilm.getVoterCount() + " users\n");
            }
        }

        if (shortFilms.isEmpty())
        {
            fileWrite.write(outputFileName, "\nNo result");
        } else
        {
            fileWrite.write(outputFileName, "\nShortFilm:\n");
            for (ShortFilm shortFilm : shortFilms)
            {
                fileWrite.write(outputFileName, shortFilm.getTitle() + " (" + shortFilm.getReleaseDate().split("\\.")[2] + ") " + "Ratings: " + shortFilm.getRatingSum() + "/10 from " + shortFilm.getVoterCount() + " users\n");
            }
        }

        if (documentaries.isEmpty())
        {
            fileWrite.write(outputFileName, "\nNo result");
        } else
        {
            fileWrite.write(outputFileName, "\nDocumentary:\n");
            for (Documentary documentary : documentaries)
            {
                fileWrite.write(outputFileName, documentary.getTitle() + " (" + documentary.getReleaseDate().split("\\.")[2] + ") " + "Ratings: " + documentary.getRatingSum() + "/10 from " + documentary.getVoterCount() + " users\n");
            }
        }

        if (tvSeries.isEmpty())
        {
            fileWrite.write(outputFileName, "No result");
        } else
        {
            fileWrite.write(outputFileName, "\nTVSeries:\n");
            for (TVSeries series : tvSeries)
            {
                fileWrite.write(outputFileName, series.getTitle() + " (" + series.getStartDate().split("\\.")[2] + "-" + series.getEndDate().split("\\.")[2] + ") " + "Ratings: " + series.getRatingSum() + "/10 from " + series.getVoterCount() + " users\n");
            }
        }
    }

    private void listArtistsFromCountry(String country)
    {
        ArrayList<Director> directors = new ArrayList<>();
        ArrayList<Writer> writers = new ArrayList<>();
        ArrayList<Actor> actors = new ArrayList<>();
        ArrayList<ChildActor> childActors = new ArrayList<>();
        ArrayList<StuntPerformer> stuntPerformers = new ArrayList<>();

        for (Person person : sysDB.getPersonArrayList())
        {
            if (person.getPersonType().equals("Director") && person.getCountry().equals(country))
            {
                directors.add((Director) person);
            } else if (person.getPersonType().equals("Writer") && person.getCountry().equals(country))
            {
                writers.add((Writer) person);
            } else if (person.getPersonType().equals("Actor") && person.getCountry().equals(country))
            {
                actors.add((Actor) person);
            } else if (person.getPersonType().equals("ChildActor") && person.getCountry().equals(country))
            {
                childActors.add((ChildActor) person);
            } else if (person.getPersonType().equals("StuntPerformer") && person.getCountry().equals(country))
            {
                stuntPerformers.add((StuntPerformer) person);
            }
        }
        if (directors.isEmpty())
        {
            fileWrite.write(outputFileName, "Directors:\n" + "No result");
        } else
        {
            fileWrite.write(outputFileName, "Directors:\n");
            for (Director director : directors)
            {
                fileWrite.write(outputFileName, director.getName() + " " + director.getSurname() + " " + director.getAgent() + "\n");
            }
        }
        if (writers.isEmpty())
        {
            fileWrite.write(outputFileName, "\nWriters:" + "\nNo result\n");
        } else
        {
            fileWrite.write(outputFileName, "\nWriters:\n");
            for (Writer writer : writers)
            {
                fileWrite.write(outputFileName, writer.getName() + " " + writer.getSurname() + " " + writer.getWritingStyle() + "\n");
            }
        }
        if (actors.isEmpty())
        {
            fileWrite.write(outputFileName, "\nActors:" + "\nNo result\n");
        } else
        {
            fileWrite.write(outputFileName, "\nActors:\n");
            for (Actor actor : actors)
            {
                fileWrite.write(outputFileName, actor.getName() + " " + actor.getSurname() + " " + actor.getHeight() + " cm" + "\n");
            }
        }
        if (childActors.isEmpty())
        {
            fileWrite.write(outputFileName, "\nChildActors:" + "\nNo result\n");
        } else
        {
            fileWrite.write(outputFileName, "\nChildActors:\n");
            for (ChildActor childActor : childActors)
            {
                fileWrite.write(outputFileName, childActor.getName() + " " + childActor.getSurname() + " " + childActor.getAge() + "\n");
            }
        }
        if (stuntPerformers.isEmpty())
        {
            fileWrite.write(outputFileName, "\nStuntPerformers:" + "\nNo result\n");
        } else
        {
            fileWrite.write(outputFileName, "\nStuntPerformers:\n");
            for (StuntPerformer stuntPerformer : stuntPerformers)
            {
                fileWrite.write(outputFileName, stuntPerformer.getName() + " " + stuntPerformer.getSurname() + " " + stuntPerformer.getHeight() + " cm" + "\n");
            }
        }
    }

    // Helper method, turns arrayList into printable data
    private String genresToString(ArrayList<String> genreArrayList)
    {
        StringBuilder genreString = new StringBuilder();
        for (String genre : genreArrayList)
        {
            genreString.append(genre + ", ");

        }
        genreString.delete(genreString.length() - 2, genreString.length());
        return genreString.toString();
    }

    // Helper method, turns arrayList into printable data
    private String writersToString(ArrayList<Writer> writerArrayList)
    {
        StringBuilder writerString = new StringBuilder();
        for (Writer writer : writerArrayList)
        {
            writerString.append(writer.getName() + " " + writer.getSurname() + ", ");
        }
        writerString.delete(writerString.length() - 2, writerString.length());
        return writerString.toString();
    }

    // Helper method, turns arrayList into printable data
    private String directorsToString(ArrayList<Director> directorArrayList)
    {
        StringBuilder directorString = new StringBuilder();
        for (Director director : directorArrayList)
        {
            directorString.append(director.getName() + " " + director.getSurname() + ", ");
        }
        directorString.delete(directorString.length() - 2, directorString.length());
        return directorString.toString();
    }

    // Helper method, turns arrayList into printable data
    private String performersToString(ArrayList<Performer> performerArrayList)
    {
        StringBuilder performerString = new StringBuilder();
        for (Performer performer : performerArrayList)
        {
            performerString.append(performer.getName() + " " + performer.getSurname() + ", ");
        }
        performerString.delete(performerString.length() - 2, performerString.length());
        return performerString.toString();
    }
}

