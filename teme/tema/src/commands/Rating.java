package commands;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

public class Rating {

    public void vote(final Input input, final ActionInputData inputData, final JSONArray arrayResult, final Writer fileWriter)
            throws java.io.IOException {
        for (UserInputData user : input.getUsers()) {  // cautam utilizatorl nostru
            if (user.getUsername().compareTo(inputData.getUsername()) == 0) {
                // vedem daca a vizionat videoul
                if (user.getHistory().containsKey(inputData.getTitle())) {
                    if (inputData.getSeasonNumber() == 0) {
                        // cazul in care este vorba de un film
                        Float grade = (float) inputData.getGrade();
                        if (user.getRatedMovies() == null) {
                            user.makeRatedMovies();
                        }
                        user.rateMovie(inputData.getTitle(), grade);
                    } else {
                        // cazul in care este vorba de un serial
                        Float grade = (float) inputData.getGrade();
                        if (user.getRatedShows() == null) {
                            user.makeRatedShows();
                        }
                        user.rateShow(inputData.getTitle(), grade);
                    }
                    // cazul in care videoclipul este vazut
                    arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", "success -> " + inputData.getTitle() + " was rated with " + inputData.getGrade() + " by " + inputData.getUsername()));
                } else {
                    // cazul in care videoclipul inca nu a fost vizionat
                    arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", "error -> " + inputData.getTitle() + " is not seen"));
                }
            }
        }
    }
}