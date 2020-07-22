package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.InterfaceBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.ExecInterfaceBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;
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
    public void analyzeUnary(ContextEl _conf) {
        CustList<OperationNode> children_ = getChildrenNodes();
        if (children_.size() > 1) {
            MethodOperation par_ = getParent();
            getPartOffsetsChildren().add(new CustList<PartOffset>());
            if (!(par_ instanceof CastOperation)) {
                CustList<PartOffset> parts_ = new CustList<PartOffset>();
                IntTreeMap<String> operators_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(1), _conf);
                int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
                LgNames stds_ = _conf.getStandards();
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                un_.setIndexFile(i_);
                //first comma
                un_.buildError(_conf.getAnalysisMessages().getSplitDiff(),
                        Integer.toString(1),
                        Integer.toString(children_.size()));
                _conf.getAnalyzing().getLocalizer().addError(un_);
                parts_.add(new PartOffset("<a title=\""+un_.getBuiltError()+"\" class=\"e\">",i_));
                parts_.add(new PartOffset("</a>",i_+1));
                getPartOffsetsChildren().add(parts_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            String base_ = ((CastOperation) par_).getClassName();
            String id_ = StringExpUtil.getIdFromAllTypes(base_);
            RootBlock rBase_ = _conf.getAnalyzing().getAnaClassBody(id_);
            if (!(rBase_ instanceof InterfaceBlock)) {
                CustList<PartOffset> parts_ = new CustList<PartOffset>();
                IntTreeMap<String> operators_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(1), _conf);
                int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
                LgNames stds_ = _conf.getStandards();
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                un_.setIndexFile(i_);
                //first comma
                un_.buildError(_conf.getAnalysisMessages().getSplitDiff(),
                        Integer.toString(1),
                        Integer.toString(children_.size()));
                _conf.getAnalyzing().getLocalizer().addError(un_);
                parts_.add(new PartOffset("<a title=\""+un_.getBuiltError()+"\" class=\"e\">",i_));
                parts_.add(new PartOffset("</a>",i_+1));
                getPartOffsetsChildren().add(parts_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            int len_ = children_.size();
            StringList previousInts_ = new StringList();
            for (int i = 1; i < len_; i++) {
                int index_ = getPartOffsetsChildren().size();
                IntTreeMap<String> operators_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(index_), _conf);
                int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
                CustList<PartOffset> parts_ = new CustList<PartOffset>();
                OperationNode op_ = children_.get(i);
                if (!(op_ instanceof InterfaceFctConstructor)){
                    LgNames stds_ = _conf.getStandards();
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    un_.setIndexFile(i_);
                    //i comma
                    un_.buildError(_conf.getAnalysisMessages().getSplitDiff(),
                            Integer.toString(1),
                            Integer.toString(children_.size()));
                    _conf.getAnalyzing().getLocalizer().addError(un_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    parts_.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",i_));
                    parts_.add(new PartOffset("</a>",i_+1));
                    getPartOffsetsChildren().add(parts_);
                    return;
                }
                ConstructorId cid_ = ((InterfaceFctConstructor) op_).getConstId();
                if (cid_ == null) {
                    //already error
                    LgNames stds_ = _conf.getStandards();
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    return;
                }
                String cl_ = cid_.getName();
                cl_ = StringExpUtil.getIdFromAllTypes(cl_);
                checkInherits(_conf, op_,previousInts_, cl_);
                previousInts_.add(cl_);
                getPartOffsetsChildren().add(parts_);
            }
            StringList all_ = new StringList(rBase_.getAllSuperTypes());
            all_.add(id_);
            all_.removeAllString(_conf.getStandards().getAliasObject());
            if (!StringList.equalsSet(all_,previousInts_)) {
                LgNames stds_ = _conf.getStandards();
                IntTreeMap<String> operators_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.lastKey(), _conf);
                int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                un_.setIndexFile(i_);
                //i comma
                un_.buildError(_conf.getAnalysisMessages().getSplitDiff(),
                        Integer.toString(1),
                        Integer.toString(children_.size()));
                _conf.getAnalyzing().getLocalizer().addError(un_);
                getPartOffsetsEnd().add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",i_));
                getPartOffsetsEnd().add(new PartOffset("</a>",i_+1));
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            standard = false;
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (children_.isEmpty()) {
            LgNames stds_ = _conf.getStandards();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //left par
            un_.buildError(_conf.getAnalysisMessages().getSplitDiff(),
                    Integer.toString(1),
                    Integer.toString(0));
            _conf.getAnalyzing().getLocalizer().addError(un_);
            getErrs().add(un_.getBuiltError());
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        setResultClass(ClassArgumentMatching.copy(children_.first().getResultClass()));
    }

    private static void checkInherits(ContextEl _conf, OperationNode _op, StringList _previousInts, String _cl) {
        if (!_previousInts.isEmpty()) {
            String sup_ = _previousInts.last();
            RootBlock supType_ = _conf.getAnalyzing().getAnaClassBody(sup_);
            if (supType_.isSubTypeOf(_cl,_conf)) {
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                undef_.setIndexFile(0);
                //current type len
                undef_.buildError(_conf.getAnalysisMessages().getCallCtorIntInherits(),
                        sup_,
                        _cl
                );
                _conf.getAnalyzing().getLocalizer().addError(undef_);
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
    public void quickCalculate(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setSimpleArgumentAna(chidren_.first().getArgument(), _conf);
    }
}
