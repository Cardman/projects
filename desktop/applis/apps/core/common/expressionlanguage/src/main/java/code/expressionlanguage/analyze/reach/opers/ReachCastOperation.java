package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.CastOperation;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.*;

public final class ReachCastOperation extends ReachMethodOperation implements ReachCalculable {
    private final String className;
    ReachCastOperation(CastOperation _info) {
        super(_info);
        className = _info.getClassName();
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        if (className.contains("#")) {
            return;
        }
        Struct arg_ = getFirstChild().getArgument();
        if (AnaTypeUtil.isPrimitive(className, _page)) {
            checkNull(arg_, _page);
        }
        AnaClassArgumentMatching cl_ = new AnaClassArgumentMatching(className, _page.getPrimitiveTypes());
        Struct after_ = NumParsers.convertObject(cl_.getUnwrapObjectNb(), arg_);
        Mapping m_= new Mapping();
        m_.setParam(className);
        m_.setArg("");
        if (after_ instanceof StringStruct) {
            m_.setArg(_page.getAliasString());
        }
        if (after_ instanceof DoubleStruct) {
            m_.setArg(_page.getAliasDouble());
        }
        if (after_ instanceof FloatStruct) {
            m_.setArg(_page.getAliasFloat());
        }
        if (after_ instanceof LongStruct) {
            m_.setArg(_page.getAliasLong());
        }
        if (after_ instanceof IntStruct) {
            m_.setArg(_page.getAliasInteger());
        }
        if (after_ instanceof ShortStruct) {
            m_.setArg(_page.getAliasShort());
        }
        if (after_ instanceof CharStruct) {
            m_.setArg(_page.getAliasCharacter());
        }
        if (after_ instanceof ByteStruct) {
            m_.setArg(_page.getAliasByte());
        }
        if (after_ instanceof BooleanStruct) {
            m_.setArg(_page.getAliasBoolean());
        }
        if (after_ instanceof ReplacementStruct) {
            m_.setArg(_page.getAliasReplacement());
        }
        if (after_ instanceof ClassMetaInfo) {
            m_.setArg(_page.getAliasClassType());
        }
        if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
            return;
        }
        setSimpleArgumentAna(after_);
    }

}
