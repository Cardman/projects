package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.files.ParsedFctHeader;

public final class AnonymousResult {
    private ParsedFctHeader results;
    private int index;
    private int until;
    private int length;
    private Block type;

    public ParsedFctHeader getResults() {
        return results;
    }

    public void setResults(ParsedFctHeader _results) {
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

    public Block getType() {
        return type;
    }

    public void setType(Block _type) {
        this.type = _type;
    }
}
