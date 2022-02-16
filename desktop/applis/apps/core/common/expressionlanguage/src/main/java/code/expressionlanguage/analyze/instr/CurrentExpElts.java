package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.blocks.FileBlock;

public final class CurrentExpElts {
    private final String packageName;
    private final FileBlock file;
    private final int instrLoc;

    public CurrentExpElts(String _packageName, FileBlock _file, int _instrLoc) {
        this.packageName = _packageName;
        this.file = _file;
        this.instrLoc = _instrLoc;
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
}
