package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.files.SegmentStringPart;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.util.CustList;

public final class CurrentExpElts {
    private final String packageName;
    private final FileBlock file;
    private final int instrLoc;
    private final ResultExpression res;

    public CurrentExpElts(String _packageName, FileBlock _file, int _instrLoc, ResultExpression _rs) {
        this.packageName = _packageName;
        this.file = _file;
        this.instrLoc = _instrLoc;
        res = _rs;
    }

    public FileBlock getFile() {
        return file;
    }

    public int getInstrLoc() {
        return instrLoc;
    }

    public String getPackageName() {
        return packageName;
    }

    public CustList<SegmentStringPart> getStringParts() {
        return getRes().getPartsAbs();
    }

    public ResultExpression getRes() {
        return res;
    }
}
