package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.blocks.FieldBlock;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ReachOperationUtil {
    private ReachOperationUtil() {

    }

    public static void tryCalculate(FieldBlock _field, CustList<OperationNode> _ops, String _fieldName, AnalyzedPageEl _page) {
        CustList<ReachMethodOperation> list_ = getExecutableNodes(_ops);
        ReachMethodOperation root_ = list_.last();
        CustList<ReachMethodOperation> sub_;
        if (!(_ops.last() instanceof DeclaringOperation)) {
            sub_ = list_;
        } else {
            int index_ = StringUtil.indexOf(_field.getFieldName(),_fieldName);
            CustList<ReachOperationNode> ch_ = root_.getChildrenNodes();
            ReachOperationNode rootLoc_ = ch_.get(index_);
            int from_;
            int to_ = rootLoc_.getOrder() + 1;
            if (index_ == 0) {
                from_ = 0;
            } else {
                from_ = ch_.get(index_-1).getOrder() + 1;
            }
            sub_ = list_.sub(from_, to_);
        }
        int ind_ = 0;
        int len_ = sub_.size();
        while (ind_ < len_) {
            ReachOperationNode curr_ = sub_.get(ind_);
            curr_.setRelativeOffsetPossibleAnalyzable(_page);
            Argument a_ = curr_.getArgument();
            ReachOperationNode n_ = curr_.getNextSibling();
            processDot(n_,curr_,curr_.getParent());
            if (a_ != null) {
                ReachOperationNode.setNextArgument(curr_,a_);
                ind_ = getNextIndex(curr_, a_.getStruct());
                continue;
            }
            tryCalculateNode(_page, curr_);
            a_ = curr_.getArgument();
            if (a_ == null) {
                return;
            }
            ind_ = getNextIndex(curr_, a_.getStruct());
        }
    }

    public static Argument tryCalculate(OperationNode _list, AnalyzedPageEl _page) {
        CustList<ReachMethodOperation> list_ = getExecutableNodes(_list);
        tryCalculate(_page, list_);
        return _list.getArgument();
    }

    public static void tryCalculate(AnalyzedPageEl _page, CustList<ReachMethodOperation> _list) {
        int ind_ = 0;
        int len_ = _list.size();
        while (ind_ < len_) {
            ReachMethodOperation curr_ = _list.get(ind_);
            curr_.setRelativeOffsetPossibleAnalyzable(_page);
            ReachOperationNode n_ = curr_.getNextSibling();
            processDot(n_,curr_,curr_.getParent());
            Argument a_ = curr_.getArgument();
            if (a_ != null) {
                ReachOperationNode.setNextArgument(curr_,a_);
                ind_ = getNextIndex(curr_, a_.getStruct());
                continue;
            }
            tryCalculateNode(_page, curr_);
            a_ = curr_.getArgument();
            if (a_ == null) {
                ind_++;
                continue;
            }
            ind_ = getNextIndex(curr_, a_.getStruct());
        }
    }

    private static void tryCalculateNode(AnalyzedPageEl _page, ReachOperationNode _curr) {
        OperationNode info_ = _curr.getInfo();
        info_.setRelativeOffsetPossibleAnalyzable(info_.getIndexInEl(), _page);
        if (_curr instanceof ReachCalculable) {
            ((ReachCalculable) _curr).tryCalculateNode(_page);
        }
        CustList<ReachOperationNode> chidren_ = ((ReachMethodOperation)_curr).getChildrenNodes();
        if (!chidren_.isEmpty()&&info_ instanceof AbstractRefTernaryOperation) {
            ReachTernaryOperation.checkDeadCode(chidren_.first(),info_,_page);
        }
        if ((info_ instanceof AbstractRefTernaryOperation
        || info_ instanceof AbstractTernaryOperation)
        &&((MethodOperation)info_).getPartOffsetsChildren().isEmpty()) {
            StringList deep_ = info_.getWarns();
            if (!deep_.isEmpty()) {
                int offLoc_ = info_.getOperations().getOperators().firstKey();
                int i_ = offLoc_ + _page.getIndex();
                ((MethodOperation)info_).getPartOffsetsChildren().add(new InfoErrorDto(StringUtil.join(deep_,ExportCst.JOIN_ERR),i_,1,true));
            }
        }
    }

    public static CustList<ReachMethodOperation> getExecutableNodes(CustList<OperationNode> _list) {
        OperationNode root_ = _list.last();
        return getExecutableNodes(root_);
    }

    private static CustList<ReachMethodOperation> getExecutableNodes(OperationNode _root) {
        CustList<ReachMethodOperation> out_ = new CustList<ReachMethodOperation>();
        OperationNode current_ = _root;
        ReachMethodOperation exp_ = ReachOperationNode.creatReachOperationNode(current_);
        while (current_ != null) {
            OperationNode op_ = current_.getFirstChild();
            if (op_ != null) {
                ReachMethodOperation loc_ = ReachOperationNode.creatReachOperationNode(op_);
                exp_.appendChild(loc_);
                exp_ = loc_;
                current_ = op_;
                continue;
            }
            while (true) {
                out_.add(exp_);
                op_ = current_.getNextSibling();
                if (op_ != null) {
                    ReachMethodOperation loc_ = ReachOperationNode.creatReachOperationNode(op_);
                    ReachMethodOperation par_ = exp_.getParent();
                    par_.appendChild(loc_);
                    exp_ = loc_;
                    current_ = op_;
                    break;
                }
                op_ = current_.getParent();
                if (op_ == null) {
                    current_ = null;
                    break;
                }
                ReachMethodOperation par_ = exp_.getParent();
                if (op_ == _root) {
                    out_.add(par_);
                    current_ = null;
                    break;
                }
                current_ = op_;
                exp_ = par_;
            }
        }
        return out_;
    }

    private static void processDot(ReachOperationNode _next, ReachOperationNode _current, ReachMethodOperation _par) {
        if (!(_next instanceof ReachPossibleIntermediateDotted)) {
            return;
        }
        if (_par instanceof ReachDotOperation) {
            ReachPossibleIntermediateDotted possible_ = (ReachPossibleIntermediateDotted) _next;
            _current.setSiblingSet(possible_);
        }
    }

    public static int getNextIndex(ReachOperationNode _operation, Struct _value) {
        int index_ = _operation.getIndexChild();
        ReachMethodOperation par_ = _operation.getParent();
        if (par_ instanceof ReachNullSafeOperation) {
            if (_value != NullStruct.NULL_VALUE) {
                return par_.getOrder();
            }
        }
        if (par_ instanceof ReachAndOperation) {
            if (BooleanStruct.isFalse(_value)) {
                return par_.getOrder();
            }
        }
        if (par_ instanceof ReachOrOperation) {
            if (BooleanStruct.isTrue(_value)) {
                return par_.getOrder();
            }
        }
        if (par_ instanceof ReachTernaryOperation) {
            if (index_ == 1) {
                return par_.getOrder();
            }
            if (index_ == 0) {
                if (BooleanStruct.isFalse(_value)) {
                    return _operation.getNextSibling().getOrder() + 1;
                }
            }
        }
        return _operation.getOrder() + 1;
    }
}
