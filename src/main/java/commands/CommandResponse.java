package commands;

public class CommandResponse {
    private final boolean successful;
    private final String output;

    public CommandResponse(boolean successful, String output) {
        this.successful = successful;
        this.output = output;
    }

    public CommandResponse(boolean successful) {
        this.successful = successful;
        this.output = "";
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getOutput() {
        return output;
    }

}