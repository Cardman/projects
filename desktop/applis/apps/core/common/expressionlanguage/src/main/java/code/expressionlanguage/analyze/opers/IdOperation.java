package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.blocks.InterfaceBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ConstructorId;
import code.util.CustList;
import code.util.StringList;
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
        if (children_.isEmpty()) {
            standard = false;
            addErr(error(_page, children_, -1).getMessage());
            return;
        }
        if (children_.size() <= 1) {
            setResultClass(AnaClassArgumentMatching.copy(children_.first().getResultClass(), _page.getPrimitiveTypes()));
            return;
        }
        standard = false;
        MethodOperation par_ = getParent();
        getPartOffsetsChildren().add(new InfoErrorDto(""));
        if (!(par_ instanceof CastOperation)) {
            getPartOffsetsChildren().add(error(_page, children_, 1));
            return;
        }
        String base_ = ((CastOperation) par_).getClassName();
        String id_ = StringExpUtil.getIdFromAllTypes(base_);
        RootBlock rBase_ = _page.getAnaClassBody(id_);
        if (!(rBase_ instanceof InterfaceBlock)) {
            getPartOffsetsChildren().add(error(_page, children_, 1));
            return;
        }
        int len_ = children_.size();
        StringList previousInts_ = new StringList();
        boolean existAll_ = true;
        for (int i = 1; i < len_; i++) {
            int index_ = getPartOffsetsChildren().size();
            OperationNode op_ = children_.get(i);
            if (!(op_ instanceof InterfaceFctConstructor)) {
                getPartOffsetsChildren().add(error(_page, children_, index_));
                return;
            }
            setRelativeOffsetPossibleAnalyzable(getIndexInEl() + getOperators().getKey(index_), _page);
            ConstructorId cid_ = ((InterfaceFctConstructor) op_).getConstId();
            String cl_ = feed(op_,_page,previousInts_,cid_);
            if (cl_.isEmpty()) {
                existAll_ = false;
            }
            getPartOffsetsChildren().add(new InfoErrorDto(""));
        }
        if (!existAll_) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        StringList all_ = new StringList(rBase_.getAllSuperTypes());
        all_.add(id_);
        all_.removeAllString(_page.getAliasObject());
        if (!StringUtil.equalsSet(all_, previousInts_)) {
            setPartOffsetsEnd(error(_page, children_, getOperators().size() - 1));
            return;
        }
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }

    private InfoErrorDto error(AnalyzedPageEl _page, CustList<OperationNode> _ch, int _index) {
        if (_index >= 0) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl() + getOperators().getKey(_index), _page);
        } else {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        }
        FoundErrorInterpret un_ = buildErrorAndAdd(_page, _ch.size());
        return new InfoErrorDto(un_, _page, 1);
    }

    private FoundErrorInterpret buildErrorAndAdd(AnalyzedPageEl _page, int _ch) {
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setFile(_page.getCurrentFile());
        un_.setIndexFile(_page);
        //first comma
        un_.buildError(_page.getAnalysisMessages().getSplitDiff(),
                Long.toString(1),
                Long.toString(_ch));
        _page.getLocalizer().addError(un_);
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
        return un_;
    }

    static String feed(OperationNode _current,AnalyzedPageEl _page, StringList _previousInts, ConstructorId _cid) {
        if (_cid != null) {
            String cl_ = _cid.getName();
            cl_ = StringExpUtil.getIdFromAllTypes(cl_);
            IdOperation.checkInherits(_current, _previousInts, cl_, _page);
            _previousInts.add(cl_);
            return cl_;
        }
        _previousInts.add("");
        return "";
    }

    static void checkInherits(OperationNode _op, StringList _previousInts, String _cl, AnalyzedPageEl _page) {
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
    boolean isFirstKo() {
        return false;
    }

    public int getDelta() {
        return delta;
    }
}
