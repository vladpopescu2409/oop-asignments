package commands;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

public class View {

    public void watch(final Input input, final ActionInputData inputData, final JSONArray arrayResult, final Writer fileWriter)
            throws java.io.IOException {
        for (UserInputData user: input.getUsers()) {  // cautam utilizatorul
            if (user.getUsername().compareTo(inputData.getUsername()) == 0) {
                // daca a vazut deja videoul, adaugam 1 la numarul de vizionari
                if (user.getHistory().containsKey(inputData.getTitle())) {
                    int number = user.getHistory().get(inputData.getTitle());
                    number++;
                    user.getHistory().put(inputData.getTitle(), number);
                    arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", "success -> " + inputData.getTitle() + " was viewed with total views of " + number));
                } else {
                    // daca nu a vazut videoul, il adaugam la lista de vizionate
                    user.getHistory().put(inputData.getTitle(), 1);
                    arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", "success -> " + inputData.getTitle() + " was viewed with total views of 1"));
                }
            }

        }
    }
}