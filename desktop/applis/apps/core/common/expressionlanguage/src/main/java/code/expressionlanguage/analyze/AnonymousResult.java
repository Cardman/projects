package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.files.ParsedFctHeaderResult;

public final class AnonymousResult {
    private ParsedFctHeaderResult results;
    private int index;
    private int until;
    private int length;
    private int next;
    private AbsBk type;
    private String retSwitch = "";

    public ParsedFctHeaderResult getResults() {
        return results;
    }

    public void setResults(ParsedFctHeaderResult _results) {
        this.results = _results;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        this.index = _index;
    }

    public int getUntil() {
        return until;
    }

    public void setUntil(int _until) {
        until = _until;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int _length) {
        this.length = _length;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int _next) {
        this.next = _next;
    }

    public AbsBk getType() {
        return type;
    }

    public void setType(AbsBk _type) {
        this.type = _type;
    }

    public String getRetSwitch() {
        return retSwitch;
    }

    public void setRetSwitch(String _r) {
        this.retSwitch = _r;
    }
}
