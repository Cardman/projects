package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.InterfaceBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.*;

public final class IdOperation extends AbstractUnaryOperation {

    private boolean standard = true;

    public IdOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        CustList<OperationNode> children_ = getChildrenNodes();
        if (children_.size() > 1) {
            MethodOperation par_ = getParent();
            getPartOffsetsChildren().add(new CustList<PartOffset>());
            if (!(par_ instanceof CastOperation)) {
                CustList<PartOffset> parts_ = new CustList<PartOffset>();
                IntTreeMap<String> operators_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(1), _page);
                int i_ = _page.getLocalizer().getCurrentLocationIndex();
                LgNames stds_ = _page.getStandards();
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_page.getLocalizer().getCurrentFileName());
                un_.setIndexFile(i_);
                //first comma
                un_.buildError(_page.getAnalysisMessages().getSplitDiff(),
                        Integer.toString(1),
                        Integer.toString(children_.size()));
                _page.getLocalizer().addError(un_);
                parts_.add(new PartOffset("<a title=\""+un_.getBuiltError()+"\" class=\"e\">",i_));
                parts_.add(new PartOffset("</a>",i_+1));
                getPartOffsetsChildren().add(parts_);
                setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            String base_ = ((CastOperation) par_).getClassName();
            String id_ = StringExpUtil.getIdFromAllTypes(base_);
            RootBlock rBase_ = _page.getAnaClassBody(id_);
            if (!(rBase_ instanceof InterfaceBlock)) {
                CustList<PartOffset> parts_ = new CustList<PartOffset>();
                IntTreeMap<String> operators_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(1), _page);
                int i_ = _page.getLocalizer().getCurrentLocationIndex();
                LgNames stds_ = _page.getStandards();
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_page.getLocalizer().getCurrentFileName());
                un_.setIndexFile(i_);
                //first comma
                un_.buildError(_page.getAnalysisMessages().getSplitDiff(),
                        Integer.toString(1),
                        Integer.toString(children_.size()));
                _page.getLocalizer().addError(un_);
                parts_.add(new PartOffset("<a title=\""+un_.getBuiltError()+"\" class=\"e\">",i_));
                parts_.add(new PartOffset("</a>",i_+1));
                getPartOffsetsChildren().add(parts_);
                setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            int len_ = children_.size();
            StringList previousInts_ = new StringList();
            boolean existAll_ = true;
            for (int i = 1; i < len_; i++) {
                int index_ = getPartOffsetsChildren().size();
                IntTreeMap<String> operators_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(index_), _page);
                int i_ = _page.getLocalizer().getCurrentLocationIndex();
                CustList<PartOffset> parts_ = new CustList<PartOffset>();
                OperationNode op_ = children_.get(i);
                if (!(op_ instanceof InterfaceFctConstructor)){
                    LgNames stds_ = _page.getStandards();
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_page.getLocalizer().getCurrentFileName());
                    un_.setIndexFile(i_);
                    //i comma
                    un_.buildError(_page.getAnalysisMessages().getSplitDiff(),
                            Integer.toString(1),
                            Integer.toString(children_.size()));
                    _page.getLocalizer().addError(un_);
                    setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
                    parts_.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",i_));
                    parts_.add(new PartOffset("</a>",i_+1));
                    getPartOffsetsChildren().add(parts_);
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
                getPartOffsetsChildren().add(parts_);
            }
            if (!existAll_) {
                LgNames stds_ = _page.getStandards();
                setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            StringList all_ = new StringList(rBase_.getAllSuperTypes());
            all_.add(id_);
            all_.removeAllString(_page.getStandards().getAliasObject());
            if (!StringList.equalsSet(all_,previousInts_)) {
                LgNames stds_ = _page.getStandards();
                IntTreeMap<String> operators_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.lastKey(), _page);
                int i_ = _page.getLocalizer().getCurrentLocationIndex();
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_page.getLocalizer().getCurrentFileName());
                un_.setIndexFile(i_);
                //i comma
                un_.buildError(_page.getAnalysisMessages().getSplitDiff(),
                        Integer.toString(1),
                        Integer.toString(children_.size()));
                _page.getLocalizer().addError(un_);
                getPartOffsetsEnd().add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",i_));
                getPartOffsetsEnd().add(new PartOffset("</a>",i_+1));
                setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            standard = false;
            LgNames stds_ = _page.getStandards();
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (children_.isEmpty()) {
            LgNames stds_ = _page.getStandards();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //left par
            un_.buildError(_page.getAnalysisMessages().getSplitDiff(),
                    Integer.toString(1),
                    Integer.toString(0));
            _page.getLocalizer().addError(un_);
            getErrs().add(un_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        setResultClass(AnaClassArgumentMatching.copy(children_.first().getResultClass(), _page.getStandards()));
    }

    private static void checkInherits(OperationNode _op, StringList _previousInts, String _cl, AnalyzedPageEl _page) {
        if (!_previousInts.isEmpty()) {
            String sup_ = _previousInts.last();
            RootBlock supType_ = _page.getAnaClassBody(sup_);
            if (supType_ != null &&supType_.isSubTypeOf(_cl, _page)) {
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFileName(_page.getLocalizer().getCurrentFileName());
                undef_.setIndexFile(0);
                //current type len
                undef_.buildError(_page.getAnalysisMessages().getCallCtorIntInherits(),
                        sup_,
                        _cl
                );
                _page.getLocalizer().addError(undef_);
                _op.getErrs().add(undef_.getBuiltError());
            }
        }
    }
    public boolean isStandard() {
        return standard;
    }

    @Override
    boolean isFirstKo() {
        return false;
    }

    @Override
    public void quickCalculate(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setSimpleArgumentAna(chidren_.first().getArgument(), _page);
    }
}
