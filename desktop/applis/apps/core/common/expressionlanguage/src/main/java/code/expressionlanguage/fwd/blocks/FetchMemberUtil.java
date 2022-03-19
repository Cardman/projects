package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.Members;
import code.expressionlanguage.fwd.opers.AnaNamedFieldContent;
import code.expressionlanguage.fwd.opers.ExecNamedFieldContent;
import code.util.CustList;

public final class FetchMemberUtil {
    private FetchMemberUtil() {
    }

    public static void setImplicits(AnaClassArgumentMatching _ana, ImplicitMethods _implicitsOp, ImplicitMethods _implicitsTestOp, Forwards _forwards) {
        CustList<AnaFormattedRootBlock> implicits_ = _ana.getImplicits();
        ExecTypeFunction conv_ = null;
        ExecFormattedRootBlock formattedType_ = null;
        if (!implicits_.isEmpty()) {
            formattedType_ = fwdFormatType(implicits_.first(), _forwards);
            conv_ = fetchOvTypeFunction(_ana.getMemberId(), _forwards);
        }
        if (conv_ != null) {
            _implicitsOp.getConverter().add(conv_);
            _implicitsOp.setOwnerClass(formattedType_);
        }
        CustList<AnaFormattedRootBlock> implicitsTest_ = _ana.getImplicitsTest();
        ExecTypeFunction convTest_ = null;
        ExecFormattedRootBlock formattedTypeTest_ = null;
        if (!implicitsTest_.isEmpty()) {
            formattedTypeTest_ = fwdFormatType(implicitsTest_.first(), _forwards);
            convTest_ = fetchOvTypeFunction(_ana.getMemberIdTest(), _forwards);
        }
        if (convTest_ != null) {
            _implicitsTestOp.getConverter().add(convTest_);
            _implicitsTestOp.setOwnerClass(formattedTypeTest_);
        }
    }

    public static ImplicitMethods fetchImplicits(ClassMethodIdMemberIdTypeFct _id, Forwards _forwards) {
        return fetchImplicits(_id.getImplicit(),_id.getMemberId(),_forwards);
    }
    public static ImplicitMethods fetchImplicits(AnaFormattedRootBlock _clMet, MemberId _id, Forwards _forwards) {
        ExecTypeFunction conv_ = null;
        ExecFormattedRootBlock formattedType_ = null;
        if (_clMet != null) {
            formattedType_ = fwdFormatType(_clMet, _forwards);
            conv_ = fetchOvTypeFunction(_id, _forwards);
        }
        if (conv_ != null) {
            ImplicitMethods converter_ = new ImplicitMethods();
            converter_.getConverter().add(conv_);
            converter_.setOwnerClass(formattedType_);
            return converter_;
        }
        return null;
    }
    public static CustList<ExecNamedFieldContent> namedFieldsContent(CustList<AnaNamedFieldContent> _ana, Forwards _fwd) {
        CustList<ExecNamedFieldContent> list_ = new CustList<ExecNamedFieldContent>();
        for (AnaNamedFieldContent a: _ana) {
            list_.add(new ExecNamedFieldContent(a,FetchMemberUtil.fetchType(a.getDeclaring(), _fwd)));
        }
        return list_;
    }

    public static String formatType(AnaFormattedRootBlock _format,String _dest) {
        return AnaInherits.quickFormat(_format,_dest);
    }
    public static MethodId formatType(AnaFormattedRootBlock _format, MethodId _dest) {
        return _dest.quickFormat(AnaInherits.getVarTypes(_format));
    }
    public static CustList<ExecFormattedRootBlock> fwdFormatTypes(CustList<AnaFormattedRootBlock> _in,Forwards _forwards) {
        CustList<ExecFormattedRootBlock> l_ = new CustList<ExecFormattedRootBlock>();
        for (AnaFormattedRootBlock s: _in) {
            l_.add(FetchMemberUtil.fwdFormatType(s, _forwards));
        }
        return l_;
    }
    public static ExecFormattedRootBlock fwdFormatType(AnaFormattedRootBlock _format,Forwards _forwards) {
        return new ExecFormattedRootBlock(_forwards.getMember(_format.getRootBlock()).getRootBlock(),_format.getFormatted());
    }
    public static ExecRootBlock fetchType(int _nbRoot, Forwards _forwards) {
        if (_forwards.isMember(_nbRoot)) {
            return _forwards.getMember(_nbRoot).getRootBlock();
        }
        return null;
    }

    public static ExecRootBlock fetchType(MemberId _id, Forwards _forwards) {
        int rootNumber_ = _id.getRootNumber();
        if (_forwards.isMember(rootNumber_)) {
            return _forwards.getMember(rootNumber_).getRootBlock();
        }
        return null;
    }

    public static ExecRootBlock fetchType(RootBlock _id, Forwards _forwards) {
        if (_id != null) {
            int rootNumber_ = _id.getNumberAll();
            return _forwards.getMember(rootNumber_).getRootBlock();
        }
        return null;
    }

    public static ExecInfoBlock fetchField(MemberId _id, Forwards _forwards) {
        int rootNumber_ = _id.getRootNumber();
        int memberNumber_ = _id.getMemberNumber();
        if (_forwards.isMember(rootNumber_) && _forwards.getMember(rootNumber_).isField(memberNumber_)) {
            return _forwards.getMember(rootNumber_).getField(memberNumber_);
        }
        return null;
    }

    public static ExecTypeFunction fetchFunctionOpPair(ClassMethodIdMemberIdTypeFct _id, Forwards _forwards) {
        return fetchFunctionOpPair(_id.getMemberId(),_forwards);
    }

    public static ExecTypeFunction fetchFunctionOpPair(MemberId _id, Forwards _forwards) {
        ExecRootBlock decl_ = fetchType(_id,_forwards);
        ExecNamedFunctionBlock fct_ = fetchFunctionOp(decl_,_id, _forwards);
        return new ExecTypeFunction(decl_,fct_);
    }

    public static ExecNamedFunctionBlock fetchFunctionOp(ExecRootBlock _declaring,MemberId _id, Forwards _forwards) {
        return fetchFunctionOrOp(_declaring,_id.getRootNumber(),_id.getMemberNumber(),_id.getMemberNumber(), _forwards);
    }

    private static ExecNamedFunctionBlock fetchFunctionOrOp(ExecRootBlock _declaring,int _rootNumber, int _memberNumber, int _operatorNumber, Forwards _forwards) {
        if (_forwards.isMember(_rootNumber)) {
            Members mem_ = _forwards.getMember(_rootNumber);
            if (_declaring instanceof ExecAnnotationBlock) {
                return mem_.getNamed(_memberNumber);
            }
            if (mem_.isOvNamed(_memberNumber)) {
                return mem_.getOvNamed(_memberNumber);
            }
            return null;
        }
        return fetchOperator(_operatorNumber, _forwards);
    }

    public static ExecOperatorBlock fetchOperator(int _operatorNumber, Forwards _forwards) {
        if (_forwards.isOperator(_operatorNumber)) {
            return _forwards.getOperator(_operatorNumber);
        }
        return null;
    }

    public static ExecNamedFunctionBlock fetchCtorFunction(Members _member, int _nbMember) {
        if (_member.isCtor(_nbMember)) {
            return _member.getCtor(_nbMember);
        }
        return null;
    }

    public static ExecTypeFunction fetchOvTypeFunction(MemberId _id, Forwards _forwards) {
        int rootNumber_ = _id.getRootNumber();
        int memberNumber_ = _id.getMemberNumber();
        if (_forwards.isMember(rootNumber_)) {
            Members mem_ = _forwards.getMember(rootNumber_);
            if (mem_.isOvNamed(memberNumber_)) {
                return new ExecTypeFunction(mem_.getRootBlock(),mem_.getOvNamed(memberNumber_));
            }
        }
        return null;
    }

    public static ExecTypeFunction fetchPossibleTypeCtor(MemberId _id, Forwards _forwards) {
        int rootNumber_ = _id.getRootNumber();
        if (_forwards.isMember(rootNumber_)) {
            Members mem_ = _forwards.getMember(rootNumber_);
            int memberNumber_ = _id.getMemberNumber();
            return new ExecTypeFunction(mem_.getRootBlock(),fetchCtorFunction(mem_,memberNumber_));
        }
        return null;
    }

    public static ExecTypeFunction fetchTypeCtor(MemberId _id, Forwards _forwards) {
        int rootNumber_ = _id.getRootNumber();
        Members mem_ = _forwards.getMember(rootNumber_);
        int memberNumber_ = _id.getMemberNumber();
        return new ExecTypeFunction(mem_.getRootBlock(),fetchCtorFunction(mem_,memberNumber_));
    }
    public static ExecClassArgumentMatching toExec(AnaClassArgumentMatching _cl) {
        return new ExecClassArgumentMatching(_cl.getNames(),_cl.getUnwrapObjectNb(),
                _cl.isCheckOnlyNullPe(),_cl.isConvertToString());
    }

}
