package commands;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

public class Favorite {

    public void add(final Input input, final ActionInputData inputData, final JSONArray arrayResult, final Writer fileWriter)
            throws java.io.IOException {
        for (UserInputData user: input.getUsers()) {  //cuatam utilizatorul nostru
            if (user.getUsername().compareTo(inputData.getUsername()) == 0) {
                if (!user.getHistory().containsKey(inputData.getTitle())) {
                    arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", "error -> " + inputData.getTitle() + " is not seen"));
                    // cazul in care videoul inca nu a fost vizionat
                } else {
                    if (user.getFavoriteMovies().contains(inputData.getTitle())) {
                        arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", "error -> " + inputData.getTitle() + " is already in favourite list"));
                        // cazul in care este deja in lista de favorite
                    } else {
                        user.adaugaFav(inputData.getTitle());
                        arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", "success -> " + inputData.getTitle() + " was added as favourite"));
                    }
                }
            }

        }

    }
}