package code.gui;

public final class TextAnswerValue {
    private final int answer;
    private final String typedText;

    public TextAnswerValue(int _a, String _t) {
        this.answer = _a;
        this.typedText = _t;
    }

    public int getAnswer() {
        return answer;
    }

    public String getTypedText() {
        return typedText;
    }
}
