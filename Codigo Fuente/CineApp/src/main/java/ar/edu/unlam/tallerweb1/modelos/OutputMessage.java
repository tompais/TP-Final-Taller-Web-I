package ar.edu.unlam.tallerweb1.modelos;

public class OutputMessage {
    private String from;
    private String text;
    private String timeStamp;

    public OutputMessage(String from, String text, String timeStamp) {
        this.from = from;
        this.text = text;
        this.timeStamp = timeStamp;
    }

    // getters and setters


    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
