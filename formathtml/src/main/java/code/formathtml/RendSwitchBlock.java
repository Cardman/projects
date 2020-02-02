package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.errors.custom.UnexpectedTypeError;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.EnumerableStruct;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendRemovableVars;
import code.formathtml.stacks.RendSwitchBlockStack;
import code.util.CustList;
import code.util.StringList;

public final class RendSwitchBlock extends RendParentBlock implements RendBreakableBlock,RendReducableOperations,RendBuildableElMethod {

    private String label;
    private int labelOffset;

    private final String value;
    private int valueOffset;

    private CustList<RendDynOperationNode> opValue;

    private boolean enumTest;

    RendSwitchBlock(OffsetStringInfo _value, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        value = _value.getInfo();
        valueOffset = _value.getOffset();
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    @Override
    public String getRealLabel() {
        return getLabel();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        _cont.getAnalyzingDoc().setAttribute(ATTRIBUTE_VALUE);
        opValue = RenderExpUtil.getAnalyzedOperations(value,0, _cont, Calculation.staticCalculation(_doc.getStaticContext()));
        RendDynOperationNode op_ = opValue.last();
        ClassArgumentMatching clArg_ = op_.getResultClass();
        if (clArg_.matchVoid(_cont)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(valueOffset);
            un_.setType(clArg_);
            _cont.getClasses().addError(un_);
        }
        String type_ = clArg_.getSingleNameOrEmpty();
        if (type_.isEmpty()) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(valueOffset);
            un_.setType(clArg_);
            _cont.getClasses().addError(un_);
        } else {
            String id_ = Templates.getIdFromAllTypes(type_);
            if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(id_, _cont)) {
                if (!StringList.quickEq(id_, _cont.getStandards().getAliasString())) {
                    if (!(_cont.getClassBody(id_) instanceof EnumBlock)) {
                        UnexpectedTypeError un_ = new UnexpectedTypeError();
                        un_.setFileName(_cont.getCurrentFileName());
                        un_.setIndexFile(valueOffset);
                        un_.setType(clArg_);
                        _cont.getClasses().addError(un_);
                    } else {
                        enumTest = true;
                    }
                }
            }
        }
        RendBlock first_ = getFirstChild();
        while (first_ != null) {
            RendBlock elt_ = first_;
            if (elt_ instanceof RendCaseCondition) {
                first_ = first_.getNextSibling();
                continue;
            }
            if (elt_ instanceof RendPossibleEmpty) {
                first_ = first_.getNextSibling();
                continue;
            }
            if (elt_ instanceof RendDefaultCondition) {
                first_ = first_.getNextSibling();
                continue;
            }
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _cont.getClasses().addError(un_);
            first_ = first_.getNextSibling();
        }
    }

    @Override
    public void reduce(Configuration _context) {
        RendDynOperationNode r_ = opValue.last();
        opValue = RenderExpUtil.getReducedNodes(r_);
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont);
            return;
        }
        ip_.setOffset(valueOffset);
        ip_.setProcessingAttribute(ATTRIBUTE_VALUE);
        Argument arg_ =  RenderExpUtil.calculateReuse(opValue,_cont);
        if (_cont.getContext().hasExceptionOrFailInit()) {
            return;
        }
        RendSwitchBlockStack if_ = new RendSwitchBlockStack();
        RendBlock n_ = getFirstChild();
        CustList<RendParentBlock> children_;
        children_ = new CustList<RendParentBlock>();
        while (n_ != null) {
            if (n_ instanceof RendParentBlock) {
                children_.add((RendParentBlock)n_);
                if_.setLastVisitedBlock((RendParentBlock) n_);
            }
            n_ = n_.getNextSibling();
        }
        if_.setBlock(this);
        RendParentBlock def_ = null;
        RendParentBlock found_ = null;
        if (arg_.isNull()) {
            for (RendParentBlock b: children_) {
                if (!(b instanceof RendCaseCondition)) {
                    def_ = b;
                    continue;
                }
                RendCaseCondition c_ = (RendCaseCondition) b;
                Argument argRes_ = c_.getOpValue().last().getArgument();
                if (argRes_ == null) {
                    continue;
                }
                if (argRes_.isNull()) {
                    found_ = c_;
                    break;
                }
            }
        } else if (enumTest) {
            EnumerableStruct en_ = (EnumerableStruct) arg_.getStruct();
            for (RendParentBlock b: children_) {
                if (!(b instanceof RendCaseCondition)) {
                    def_ = b;
                    continue;
                }
                RendCaseCondition c_ = (RendCaseCondition) b;
                RendDynOperationNode op_ = c_.getOpValue().last();
                if (op_.getArgument() != null) {
                    continue;
                }
                if (StringList.quickEq(c_.getValue().trim(), en_.getName())) {
                    found_ = c_;
                    break;
                }
            }
        } else {
            for (RendParentBlock b: children_) {
                if (!(b instanceof RendCaseCondition)) {
                    def_ = b;
                    continue;
                }
                RendCaseCondition c_ = (RendCaseCondition) b;
                Argument argRes_ = c_.getOpValue().last().getArgument();
                if (argRes_.getStruct().sameReference(arg_.getStruct())) {
                    found_ = c_;
                    break;
                }
            }
        }
        if (found_ == null) {
            if (def_ != null) {
                rw_.setRead(def_);
                if_.setCurrentVisitedBlock(def_);
            } else {
                if_.setCurrentVisitedBlock(this);
            }
        } else {
            rw_.setRead(found_);
            if_.setCurrentVisitedBlock(found_);
        }
        ip_.addBlock(if_);
    }

    public CustList<RendDynOperationNode> getOpValue() {
        return opValue;
    }
}
