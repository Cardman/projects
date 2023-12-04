package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.MemberId;
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
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.util.CustList;

public final class FetchMemberUtil {
    private FetchMemberUtil() {
    }

    public static void setImplicits(AnaClassArgumentMatching _ana, ImplicitMethods _implicitsOp, ImplicitMethods _implicitsTestOp, Forwards _forwards) {
        impls(_implicitsOp, _forwards, _ana.getImplicits(), _ana.getMemberId());
        impls(_implicitsTestOp, _forwards, _ana.getImplicitsTest(), _ana.getMemberIdTest());
    }

    private static void impls(ImplicitMethods _implicits, Forwards _forwards, CustList<AnaFormattedRootBlock> _impl, MemberId _id) {
        impls(_implicits, _forwards, _id, formatted(_impl));
    }

    public static void impls(ClassMethodIdMemberIdTypeFct _f,ImplicitMethods _implicits, Forwards _forwards) {
        impls(_implicits,_forwards, _f.getMemberId(), _f.getImplicit().getFormatted());
    }
    private static void impls(ImplicitMethods _implicits, Forwards _forwards, MemberId _id, String _f) {
        ExecTypeFunction convTest_ = conv(_f, _id, _forwards);
        if (convTest_ != null) {
            update(_implicits, convTest_, _f);
        }
    }

    private static String formatted(CustList<AnaFormattedRootBlock> _i) {
        if (_i.isEmpty()) {
            return "";
        }
        return _i.first().getFormatted();
    }

    private static void update(ImplicitMethods _converter, ExecTypeFunction _fct, String _formatted) {
        _converter.getConverter().add(_fct);
        _converter.setOwnerClass(new ExecFormattedRootBlock(_fct.getType(), _formatted));
    }

    private static ExecTypeFunction conv(String _formatted, MemberId _id, Forwards _forwards) {
        ExecTypeFunction conv_ = null;
        if (!_formatted.isEmpty()) {
            conv_ = fetchOvTypeFunction(_id, _forwards);
        }
        return conv_;
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
            l_.add(ExecStaticEltContent.build(s, _forwards));
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
