package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class RangeOperation extends MethodOperation {
    private int opOffset;
    private boolean okNum;
    private final boolean implicitMiddle;
    public RangeOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        implicitMiddle = _op.isImplMiddle();
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        okNum = true;
        if (chidren_.size() > 3) {
            okNum = false;
            _page.setOkNumOp(false);
            int in_ = NumberUtil.min(getOperators().size()-1,2);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ getOperators().getKey(in_), _page);
            FoundErrorInterpret badNb_ = new FoundErrorInterpret();
            badNb_.setFile(_page.getCurrentFile());
            badNb_.setIndexFile(_page);
            //first oper
            badNb_.buildError(_page.getAnalysisMessages().getOperatorNbDiff(),
                    Long.toString(3),
                    Long.toString(chidren_.size()),
                    "???"
            );
            _page.getLocalizer().addError(badNb_);
            for (int i = 0; i < in_;i++) {
                getPartOffsetsChildrenList().add(new CustList<InfoErrorDto>());
            }
            getPartOffsetsChildrenList().add(new CustList<InfoErrorDto>(new InfoErrorDto(badNb_,_page, getOperators().getValue(in_).length())));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasRange()));
            return;
        }
        opOffset= getOperators().firstKey();
        AnaClassArgumentMatching clMatchLeft_ = chidren_.first().getResultClass();
        if (chidren_.size() == 3||chidren_.size() == 2) {
            AnaClassArgumentMatching clMatchRight_ = chidren_.get(1).getResultClass();
            CustList<InfoErrorDto> err_ = new CustList<InfoErrorDto>();
            result(_page,clMatchLeft_,err_,StringUtil.join(new StringList(
                    StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES),
                    StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES)
            ),ExportCst.JOIN_OPERANDS),0,0);
            result(_page,clMatchRight_,err_,StringUtil.join(new StringList(
                    StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES),
                    StringUtil.join(clMatchRight_.getNames(),ExportCst.JOIN_TYPES)
            ),ExportCst.JOIN_OPERANDS),2,0);
            addIfNotEmpty(err_);
            if (chidren_.size() == 3) {
                if (getPartOffsetsChildrenList().isEmpty()) {
                    getPartOffsetsChildrenList().add(new CustList<InfoErrorDto>());
                }
                CustList<InfoErrorDto> errSupp_ = new CustList<InfoErrorDto>();
                AnaClassArgumentMatching clMatchStep_ = chidren_.last().getResultClass();
                result(_page,clMatchStep_,errSupp_,StringUtil.join(clMatchStep_.getNames(),ExportCst.JOIN_TYPES),2,1);
                addIfNotEmpty(errSupp_);
            }
        } else {
            CustList<InfoErrorDto> err_ = new CustList<InfoErrorDto>();
            result(_page,clMatchLeft_,err_,StringUtil.join(clMatchLeft_.getNames(),ExportCst.JOIN_TYPES),0,0);
            addIfNotEmpty(err_);
        }
        setResultClass(new AnaClassArgumentMatching(_page.getAliasRange()));
    }

    private void addIfNotEmpty(CustList<InfoErrorDto> _err) {
        if (!_err.isEmpty()) {
            getPartOffsetsChildrenList().add(_err);
        }
    }

    private void result(AnalyzedPageEl _page, AnaClassArgumentMatching _resCh,CustList<InfoErrorDto> _err, String _argMessage,int _off, int _index) {
        if (!_resCh.isNumericInt(_page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getAliasPrimInteger(), _resCh, _page);
            if (res_ != null) {
                _resCh.implicitInfos(res_);
            } else {
                okNum = false;
                _page.setOkNumOp(false);
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ getOperators().getKey(_index), _page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setIndexFile(_page);
                un_.setFile(_page.getCurrentFile());
                //oper
                un_.buildError(_page.getAnalysisMessages().getUnexpectedOperandTypes(),
                        _argMessage,
                        "???");
                _page.getLocalizer().addError(un_);
                _err.add(new InfoErrorDto(un_, _page,_off, 1));
            }
        }
        _resCh.setUnwrapObject(AnaTypeUtil.toPrimitive(_resCh, _page), _page.getPrimitiveTypes());
    }
    public int getOpOffset() {
        return opOffset;
    }

    public boolean isOkNum() {
        return okNum;
    }

    public boolean isImplicitMiddle() {
        return implicitMiddle;
    }
}
