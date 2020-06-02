package code.expressionlanguage.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.InterfaceBlock;
import code.expressionlanguage.methods.Line;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.exec.ExecAbstractInvokingConstructor;
import code.expressionlanguage.opers.exec.ExecInterfaceInvokingConstructor;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.exec.IdOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.*;

public final class IdOperation extends AbstractUnaryOperation implements IdOperable {

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
            if (!(par_ instanceof CastOperation)) {
                LgNames stds_ = _conf.getStandards();
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //first comma
                un_.buildError(_conf.getAnalysisMessages().getSplitDiff(),
                        Integer.toString(1),
                        Integer.toString(children_.size()));
                _conf.getAnalyzing().getLocalizer().addError(un_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            String base_ = ((CastOperation) par_).getClassName();
            String id_ = Templates.getIdFromAllTypes(base_);
            RootBlock rBase_ = _conf.getClasses().getClassBody(id_);
            if (!(rBase_ instanceof InterfaceBlock)) {
                LgNames stds_ = _conf.getStandards();
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //first comma
                un_.buildError(_conf.getAnalysisMessages().getSplitDiff(),
                        Integer.toString(1),
                        Integer.toString(children_.size()));
                _conf.getAnalyzing().getLocalizer().addError(un_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            int len_ = children_.size();
            StringList previousInts_ = new StringList();
            for (int i = 1; i < len_; i++) {
                OperationNode op_ = children_.get(i);
                if (!(op_ instanceof InterfaceFctConstructor)){
                    LgNames stds_ = _conf.getStandards();
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    //i comma
                    un_.buildError(_conf.getAnalysisMessages().getSplitDiff(),
                            Integer.toString(1),
                            Integer.toString(children_.size()));
                    _conf.getAnalyzing().getLocalizer().addError(un_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    return;
                }
                ConstructorId cid_ = ((InterfaceFctConstructor) op_).getConstId();
                if (cid_ == null) {
                    LgNames stds_ = _conf.getStandards();
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    //i comma
                    un_.buildError(_conf.getAnalysisMessages().getSplitDiff(),
                            Integer.toString(1),
                            Integer.toString(children_.size()));
                    _conf.getAnalyzing().getLocalizer().addError(un_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    return;
                }
                String cl_ = cid_.getName();
                cl_ = Templates.getIdFromAllTypes(cl_);
                checkInherits(_conf, previousInts_, cl_);
                previousInts_.add(cl_);
            }
            StringList all_ = new StringList(rBase_.getAllSuperTypes());
            all_.add(id_);
            all_.removeAllString(_conf.getStandards().getAliasObject());
            if (!StringList.equalsSet(all_,previousInts_)) {
                LgNames stds_ = _conf.getStandards();
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //i comma
                un_.buildError(_conf.getAnalysisMessages().getSplitDiff(),
                        Integer.toString(1),
                        Integer.toString(children_.size()));
                _conf.getAnalyzing().getLocalizer().addError(un_);
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
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        setResultClass(children_.first().getResultClass());
    }

    private static void checkInherits(ContextEl _conf, StringList _previousInts, String _cl) {
        if (!_previousInts.isEmpty()) {
            String sup_ = _previousInts.last();
            RootBlock supType_ = _conf.getClasses().getClassBody(sup_);
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
