package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.files.SegmentStringPart;
import code.util.CustList;

public final class CurrentExpElts {
    private final String packageName;
    private final FileBlock file;
    private final int instrLoc;
    private final CustList<SegmentStringPart> stringParts;

    public CurrentExpElts(String _packageName, FileBlock _file, int _instrLoc, CustList<SegmentStringPart> _strParts) {
        this.packageName = _packageName;
        this.file = _file;
        this.instrLoc = _instrLoc;
        stringParts = _strParts;
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
        return stringParts;
    }
}
