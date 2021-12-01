package commands;

import fileio.ActionInputData;
import fileio.Input;
import fileio.Writer;
import org.json.simple.JSONArray;

public class Command {
    private Input input;
    public Command(final Input input) {
        this.input = input;
    }

    public void run(final ActionInputData inputData, final JSONArray arrayResult, final Writer fileWriter) throws java.io.IOException {
        if (inputData.getType().compareTo("rating") == 0) {
            Rating rate = new Rating();
            rate.vote(this.input, inputData, arrayResult, fileWriter);
        }
        if (inputData.getType().compareTo("view") == 0) {
            View viz = new View();
            viz.watch(this.input, inputData, arrayResult, fileWriter);
        }
        if (inputData.getType().compareTo("favorite") == 0) {
            Favorite fav = new Favorite();
            fav.add(this.input, inputData, arrayResult, fileWriter);
        }
    }
}