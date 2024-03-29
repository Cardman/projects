package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnaBlockCounts;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.FileResolverContext;
import code.expressionlanguage.analyze.files.FileAliases;
import code.expressionlanguage.analyze.files.SegmentStringPart;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.util.CustList;

public final class CurrentExpElts {
    private final FileResolverContext cont;
    private final String packageName;
    private final FileBlock file;
    private final int instrLoc;
    private final ResultExpression res;
    private final AccessedBlock rootBlock;

    public CurrentExpElts(FileResolverContext _ctx, String _packageName, FileBlock _file, int _instrLoc, ResultExpression _rs, AccessedBlock _r) {
        cont = _ctx;
        this.packageName = _packageName;
        this.file = _file;
        this.instrLoc = _instrLoc;
        res = _rs;
        rootBlock = _r;
    }

    public AccessedBlock getRootBlock() {
        return rootBlock;
    }

    public AnaBlockCounts getCounts() {
        return cont.getCounts();
    }
    public FileAliases getFileAliases() {
        return cont.getFileAliases();
    }

    public FileResolverContext getCont() {
        return cont;
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
