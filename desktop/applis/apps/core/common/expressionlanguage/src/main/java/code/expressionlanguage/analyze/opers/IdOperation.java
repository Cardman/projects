package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.blocks.InterfaceBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.functionid.ConstructorId;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.*;
import code.util.core.StringUtil;

public final class IdOperation extends AbstractUnaryOperation {

    private boolean standard = true;

    private final int delta;
    public IdOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op, int _delta) {
        super(_index, _indexChild, _m, _op);
        delta = _delta;
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        CustList<OperationNode> children_ = getChildrenNodes();
        if (children_.size() > 1) {
            standard = false;
            MethodOperation par_ = getParent();
            getPartOffsetsChildren().add(new InfoErrorDto(""));
            if (!(par_ instanceof CastOperation)) {
                StrTypes operators_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(1), _page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page);
                //first comma
                un_.buildError(_page.getAnalysisMessages().getSplitDiff(),
                        Long.toString(1),
                        Long.toString(children_.size()));
                _page.getLocalizer().addError(un_);
                getPartOffsetsChildren().add(new InfoErrorDto(un_,_page,1));
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            String base_ = ((CastOperation) par_).getClassName();
            String id_ = StringExpUtil.getIdFromAllTypes(base_);
            RootBlock rBase_ = _page.getAnaClassBody(id_);
            if (!(rBase_ instanceof InterfaceBlock)) {
                StrTypes operators_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(1), _page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page);
                //first comma
                un_.buildError(_page.getAnalysisMessages().getSplitDiff(),
                        Long.toString(1),
                        Long.toString(children_.size()));
                _page.getLocalizer().addError(un_);
                getPartOffsetsChildren().add(new InfoErrorDto(un_,_page,1));
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            int len_ = children_.size();
            StringList previousInts_ = new StringList();
            boolean existAll_ = true;
            for (int i = 1; i < len_; i++) {
                int index_ = getPartOffsetsChildren().size();
                StrTypes operators_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(index_), _page);
                OperationNode op_ = children_.get(i);
                if (!(op_ instanceof InterfaceFctConstructor)){
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_page.getCurrentFile());
                    un_.setIndexFile(_page);
                    //i comma
                    un_.buildError(_page.getAnalysisMessages().getSplitDiff(),
                            Long.toString(1),
                            Long.toString(children_.size()));
                    _page.getLocalizer().addError(un_);
                    setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                    getPartOffsetsChildren().add(new InfoErrorDto(un_,_page,1));
                    return;
                }
                ConstructorId cid_ = ((InterfaceFctConstructor) op_).getConstId();
                String cl_ = "";
                if (cid_ != null) {
                    cl_ = cid_.getName();
                    cl_ = StringExpUtil.getIdFromAllTypes(cl_);
                } else {
                    existAll_ = false;
                }
                checkInherits(op_,previousInts_, cl_, _page);
                previousInts_.add(cl_);
                getPartOffsetsChildren().add(new InfoErrorDto(""));
            }
            if (!existAll_) {
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            StringList all_ = new StringList(rBase_.getAllSuperTypes());
            all_.add(id_);
            all_.removeAllString(_page.getAliasObject());
            if (!StringUtil.equalsSet(all_,previousInts_)) {
                StrTypes operators_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.lastKey(), _page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page);
                //i comma
                un_.buildError(_page.getAnalysisMessages().getSplitDiff(),
                        Long.toString(1),
                        Long.toString(children_.size()));
                _page.getLocalizer().addError(un_);
                setPartOffsetsEnd(new InfoErrorDto(un_,_page,1));
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (children_.isEmpty()) {
            standard = false;
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page);
            //left par
            un_.buildError(_page.getAnalysisMessages().getSplitDiff(),
                    Long.toString(1),
                    Long.toString(0));
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        setResultClass(AnaClassArgumentMatching.copy(children_.first().getResultClass(), _page.getPrimitiveTypes()));
    }

    private static void checkInherits(OperationNode _op, StringList _previousInts, String _cl, AnalyzedPageEl _page) {
        if (!_previousInts.isEmpty()) {
            String sup_ = _previousInts.last();
            RootBlock supType_ = _page.getAnaClassBody(sup_);
            if (supType_ != null &&supType_.isSubTypeOf(_cl, _page)) {
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFile(_page.getCurrentFile());
                undef_.setIndexFile(_page);
                //current type len
                undef_.buildError(_page.getAnalysisMessages().getCallCtorIntInherits(),
                        sup_,
                        _cl
                );
                _page.getLocalizer().addError(undef_);
                _op.addErr(undef_.getBuiltError());
            }
        }
    }
    public boolean isStandard() {
        return standard;
    }
    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        vs_.remove(0);
        getChildren().addAllEntries(vs_);
    }
    @Override
    boolean isFirstKo() {
        return false;
    }

    public int getDelta() {
        return delta;
    }
}
