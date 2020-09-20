package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.blocks.EnumBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.structs.EnumerableStruct;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendSwitchBlockStack;
import code.formathtml.util.AnalyzingDoc;
import code.util.CustList;
import code.util.StringList;

public final class RendSwitchBlock extends RendParentBlock implements RendBreakableBlock,RendReducableOperations,RendWithEl {

    private String label;
    private int labelOffset;

    private final String value;
    private int valueOffset;

    private CustList<RendDynOperationNode> opValue;
    private AnaClassArgumentMatching result;

    private boolean enumTest;
    private String instanceTest = "";

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
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(valueOffset);
        _page.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrValue());
        opValue = RenderExpUtil.getAnalyzedOperations(value,valueOffset,0, _cont, _anaDoc, _page);
        result = _page.getCurrentRoot().getResultClass();
        AnaClassArgumentMatching clArg_ = _page.getCurrentRoot().getResultClass();
        String type_ = clArg_.getSingleNameOrEmpty();
        if (type_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(valueOffset);
            un_.buildError(_page.getAnalysisMessages().getUnknownType(),
                    type_);
            Configuration.addError(un_, _anaDoc, _page);
        } else {
            String id_ = StringExpUtil.getIdFromAllTypes(type_);
            AnaGeneType classBody_ = _page.getAnaGeneType(id_);
            boolean final_ = true;
            if (classBody_ != null) {
                final_ = ContextUtil.isFinalType(classBody_);
            } else if (type_.startsWith("[")) {
                final_ = false;
            }
            if (!AnaTypeUtil.isPrimitiveOrWrapper(id_, _page)) {
                if (!StringList.quickEq(id_, _cont.getStandards().getAliasString())) {
                    if (!(classBody_ instanceof EnumBlock)) {
                        if (!final_) {
                            instanceTest = type_;
                        } else {
                            FoundErrorInterpret un_ = new FoundErrorInterpret();
                            un_.setFileName(_anaDoc.getFileName());
                            un_.setIndexFile(valueOffset);
                            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                                    id_);
                            Configuration.addError(un_, _anaDoc, _page);
                        }
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
            _page.setGlobalOffset(getOffset().getOffsetTrim());
            _page.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            un_.buildError(_page.getAnalysisMessages().getUnexpectedSwitch(),
                    _page.getKeyWords().getKeyWordSwitch(),
                    StringList.join(
                            new StringList(
                                    _page.getKeyWords().getKeyWordCase(),
                                    _page.getKeyWords().getKeyWordDefault()
                            ),
                            OR_ERR));
            Configuration.addError(un_, _anaDoc, _page);
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
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrValue());
        Argument arg_ =  RenderExpUtil.calculateReuse(opValue,_cont);
        if (_cont.getContext().hasException()) {
            return;
        }
        if (!instanceTest.isEmpty()) {
            RendSwitchBlockStack if_ = new RendSwitchBlockStack();
            RendBlock n_ = getFirstChild();
            CustList<RendParentBlock> children_;
            children_ = new CustList<RendParentBlock>();
            while (n_ != null) {
                if (n_ instanceof RendParentBlock) {
                    children_.add((RendParentBlock)n_);
                }
                n_ = n_.getNextSibling();
            }
            if_.setBlock(this);
            RendParentBlock found_ = null;
            if (arg_.isNull()) {
                for (RendParentBlock b: children_) {
                    if (b instanceof RendCaseCondition) {
                        RendCaseCondition c_ = (RendCaseCondition) b;
                        if (c_.getImportedClassName().isEmpty()) {
                            if_.setLastVisitedBlock(b);
                            found_ = b;
                            break;
                        }
                    }
                }
            } else {
                for (RendParentBlock b: children_) {
                    if (b instanceof RendCaseCondition) {
                        RendCaseCondition c_ = (RendCaseCondition) b;
                        if (!c_.getImportedClassName().isEmpty()) {
                            RendCaseCondition b_ = (RendCaseCondition) b;
                            found_ = fetch(_cont,if_,arg_,found_,b_);
                        }
                    }
                }
            }
            if (found_ == null) {
                for (RendParentBlock b: children_) {
                    if (b instanceof RendDefaultCondition) {
                        RendDefaultCondition b_ = (RendDefaultCondition) b;
                        found_ = fetch(_cont,if_,arg_,found_,b_);
                    }
                }
            }
            if (found_ == null) {
                if_.setCurrentVisitedBlock(this);
            } else {
                rw_.setRead(found_);
                if_.setCurrentVisitedBlock(found_);
            }
            ip_.addBlock(if_);
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
        RendParentBlock found_ = null;
        if (arg_.isNull()) {
            for (RendParentBlock b: children_) {
                if (!(b instanceof RendCaseCondition)) {
                    found_ = b;
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
                    found_ = b;
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
                    found_ = b;
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
            if_.setCurrentVisitedBlock(this);
        } else {
            rw_.setRead(found_);
            if_.setCurrentVisitedBlock(found_);
        }
        ip_.addBlock(if_);
    }
    private static RendParentBlock fetch(Configuration _cont, RendSwitchBlockStack if_, Argument arg_,
                                         RendParentBlock _found, RendSwitchPartCondition _s) {
        if (_found != null) {
            return _found;
        }
        String type_ = _s.getImportedClassName();
        ImportingPage ip_ = _cont.getLastPage();
        if (ExecTemplates.safeObject(type_, arg_, _cont.getContext()) == ErrorType.NOTHING) {
            String var_ = _s.getVariableName();
            ip_.putValueVar(var_,LocalVariable.newLocalVariable(arg_.getStruct(),type_));
            if_.setLastVisitedBlock(_s);
            return _s;
        }
        return null;
    }

    public String getInstanceTest() {
        return instanceTest;
    }

    public AnaClassArgumentMatching getResult() {
        return result;
    }
}
