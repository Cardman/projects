package code.gui;
import javax.swing.Timer;

public final class ResultsHtml {

    private String htmlText;

    private String errors;

    private Timer timer;

    public String getHtmlText() {
        return htmlText;
    }

    public void setHtmlText(String _htmlText) {
        htmlText = _htmlText;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String _errors) {
        errors = _errors;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer _timer) {
        timer = _timer;
    }
}
