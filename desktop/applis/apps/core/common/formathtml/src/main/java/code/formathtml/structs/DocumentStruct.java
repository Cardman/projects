package code.formathtml.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecAnonymousFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.AbsAnnotatedStruct;
import code.expressionlanguage.structs.AnnotatedStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.util.CustList;
import code.util.core.StringUtil;

public final class DocumentStruct extends AbsAnnotatedStruct implements AnnotatedStruct {
    private final ExecFormattedRootBlock formatted;
    private final String aliasDoc;
    private final CustList<ExecAnonymousFunctionBlock> anon;
    private final CustList<ExecAbstractSwitchMethod> sw;
    private final RendDocumentBlock docInfo;

    public DocumentStruct(String _doc, RendDocumentBlock _doInfo) {
        super(AccessEnum.PUBLIC, _doInfo.getFileName());
        formatted = _doInfo.getDecl();
        aliasDoc = _doc;
        anon = _doInfo.getAnonymousLambda();
        sw = _doInfo.getSwitchMethods();
        docInfo = _doInfo;
    }

    public RendDocumentBlock getDocInfo() {
        return docInfo;
    }

    @Override
    public ExecFormattedRootBlock getFormatted() {
        return formatted;
    }

    @Override
    public ExecBlock getBl() {
        return null;
    }

    @Override
    public CustList<ExecAnonymousFunctionBlock> getAnonymousLambda() {
        return anon;
    }

    @Override
    public CustList<ExecAbstractSwitchMethod> getSwitchMethods() {
        return sw;
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(getFileName());
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return aliasDoc;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof DocumentStruct)) {
            return false;
        }
        return StringUtil.quickEq(getFileName(),((DocumentStruct)_other).getFileName());
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(getFileName());
    }
}
