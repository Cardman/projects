package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.linkage.ExportCst;
import code.util.*;
import code.util.core.StringUtil;

public final class SwitchBlock extends LabelledOtherBlock implements BreakableBlock,BuildableElMethod,AnalyzedSwitch {

    private final String value;
    private final int valueOffset;

    private AnaClassArgumentMatching result;

    private boolean instance;
    private boolean forceInstance;
    private String instanceTest = "";

    private final ResultExpression res = new ResultExpression();

    private String err = "";

    private int conditionNb;

    public SwitchBlock(OffsetStringInfo _value, OffsetStringInfo _label, int _offset) {
        super(_offset,_label);
        value = _value.getInfo();
        valueOffset = _value.getOffset();
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setSumOffset(res.getSumOffset());
        _page.zeroOffset();
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, Calculation.staticCalculation(_page.getStaticContext()), _page));
        err = _page.getCurrentEmptyPartErr();
        result = res.getRoot().getResultClass();
        processAfterEl(_page);
    }

    private void processAfterEl(AnalyzedPageEl _page) {
        _page.setSumOffset(valueOffset);
        _page.zeroOffset();
        processAfterEl(result,this,_page);
        processChildren(this,_page);
    }

    public static void processAfterEl(AnaClassArgumentMatching _result,AnalyzedSwitch _braced,AnalyzedPageEl _page) {
        String type_ = _result.getSingleNameOrEmpty();
        if (type_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_braced.getFile());
            un_.setIndexFile(_page);
            //one char => change to first left par
            un_.buildError(_page.getAnalysisMessages().getUnknownType(),
                    type_);
            _page.addLocError(un_);
            _braced.addErrorBlock(un_.getBuiltError());
        } else {
            String id_ = StringExpUtil.getIdFromAllTypes(type_);
            AnaGeneType classBody_ = _page.getAnaGeneType(id_);
            boolean final_ = true;
            if (classBody_ != null) {
                final_ = ContextUtil.isHyperAbstract(classBody_);
            } else if (type_.startsWith("#")||type_.startsWith("[")) {
                final_ = false;
            }
            if (!AnaTypeUtil.isPrimitiveOrWrapper(id_, _page) && !StringUtil.quickEq(id_, _page.getAliasString()) && !(classBody_ instanceof EnumBlock)) {
                if (!final_) {
                    _braced.setInstanceTest(true,type_);
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_braced.getFile());
                    un_.setIndexFile(_page);
                    //one char => change to first left par
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            id_);
                    _page.addLocError(un_);
                    _braced.addErrorBlock(un_.getBuiltError());
                }
            } else {
                _braced.setInstanceTest(_braced.isForceInstance(),type_);
            }
        }
    }
    public static void processChildren(BracedBlock _braced,AnalyzedPageEl _page) {
        AbsBk first_ = _braced.getFirstChild();
        while (first_ != null) {
            AbsBk elt_ = first_;
            if (elt_ instanceof CaseCondition || elt_ instanceof DefaultCondition) {
                first_ = first_.getNextSibling();
                continue;
            }
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_braced.getFile());
            un_.setIndexFile(_braced.getOffset());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedSwitch(),
                    _page.getKeyWords().getKeyWordSwitch(),
                    StringUtil.join(
                            new StringList(
                                    _page.getKeyWords().getKeyWordCase(),
                                    _page.getKeyWords().getKeyWordDefault()
                            ),
                            ExportCst.JOIN_BLOCK));
            _page.addLocError(un_);
            first_.addErrorBlock(un_.getBuiltError());
            first_ = first_.getNextSibling();
        }
    }
    public AnaClassArgumentMatching getResult() {
        return result;
    }

    public ResultExpression getRes() {
        return res;
    }

    public OperationNode getRoot() {
        return res.getRoot();
    }

    public String getErr() {
        return err;
    }

    public boolean isInstance() {
        return instance;
    }

    public String getInstanceTest() {
        return instanceTest;
    }

    @Override
    public void setInstanceTest(boolean _instance,String _instanceTest) {
        instance = _instance;
        this.instanceTest = _instanceTest;
    }

    public boolean isForceInstance() {
        return forceInstance;
    }

    public void setForceInstance(boolean _f) {
        this.forceInstance = _f;
    }
    public int getConditionNb() {
        return conditionNb;
    }

    public void setConditionNb(int _conditionNb) {
        conditionNb = _conditionNb;
    }

}
