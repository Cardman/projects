package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.CastOperation;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.*;

public final class ReachCastOperation extends ReachMethodOperation implements ReachCalculable {
    private String className;
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
        Argument arg_ = getFirstChild().getArgument();
        if (AnaTypeUtil.isPrimitive(className, _page)) {
            checkNull(arg_, _page);
        }
        AnaClassArgumentMatching cl_ = new AnaClassArgumentMatching(className, _page.getPrimitiveTypes());
        Argument after_ = new Argument(NumParsers.convertObject(cl_.getUnwrapObjectNb(), arg_.getStruct()));
        Mapping m_= new Mapping();
        m_.setParam(className);
        m_.setArg("");
        if (after_.getStruct() instanceof StringStruct) {
            m_.setArg(_page.getAliasString());
        }
        if (after_.getStruct() instanceof DoubleStruct) {
            m_.setArg(_page.getAliasDouble());
        }
        if (after_.getStruct() instanceof FloatStruct) {
            m_.setArg(_page.getAliasFloat());
        }
        if (after_.getStruct() instanceof LongStruct) {
            m_.setArg(_page.getAliasLong());
        }
        if (after_.getStruct() instanceof IntStruct) {
            m_.setArg(_page.getAliasInteger());
        }
        if (after_.getStruct() instanceof ShortStruct) {
            m_.setArg(_page.getAliasShort());
        }
        if (after_.getStruct() instanceof CharStruct) {
            m_.setArg(_page.getAliasCharacter());
        }
        if (after_.getStruct() instanceof ByteStruct) {
            m_.setArg(_page.getAliasByte());
        }
        if (after_.getStruct() instanceof BooleanStruct) {
            m_.setArg(_page.getAliasBoolean());
        }
        if (after_.getStruct() instanceof ReplacementStruct) {
            m_.setArg(_page.getAliasReplacement());
        }
        if (after_.getStruct() instanceof ClassMetaInfo) {
            m_.setArg(_page.getAliasClassType());
        }
        if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
            return;
        }
        setSimpleArgumentAna(after_);
    }

}
