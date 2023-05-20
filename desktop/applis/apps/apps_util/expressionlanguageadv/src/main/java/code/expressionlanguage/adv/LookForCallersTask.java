package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.syntax.CallerKind;
import code.expressionlanguage.analyze.syntax.CallersRef;
import code.expressionlanguage.analyze.syntax.FileBlockIndex;
import code.expressionlanguage.analyze.syntax.SrcFileLocation;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;

public final class LookForCallersTask implements Runnable {
    private final WindowWithTreeImpl window;
    private final ResultRowSrcLocationList result;

    public LookForCallersTask(WindowWithTreeImpl _w,ResultRowSrcLocationList _r) {
        this.window = _w;
        this.result = _r;
    }

    @Override
    public void run() {
        CustList<CalleeToCaller> current_ = new CustList<CalleeToCaller>();
        CustList<MetaCaller> roots_ = new CustList<MetaCaller>();
        MetaCaller r_ = new MetaCaller(null,null,new CustList<FileBlockIndex>());
        CalleeToCaller parent_ = new CalleeToCaller(r_);
        for (SrcFileLocation s: result.getUsages()) {
            MetaCaller m_ = new MetaCaller(null,s,new CustList<FileBlockIndex>());
            current_.add(new CalleeToCaller(m_,parent_));
            roots_.add(m_);
            MutableTreeNodeCoreUtil.add(r_,m_);
        }
        while (true) {
            CustList<CalleeToCaller> next_ = children(current_);
            if (next_.isEmpty()) {
                break;
            }
            current_ = next_;
        }
        AbsCompoFactory compo_ = window.getCommonFrame().getFrames().getCompoFactory();
        AbstractMutableTreeNode n_ = compo_.newMutableTreeNode("");
        for (MetaCaller r: roots_) {
            buildRoot(compo_, n_, r);
        }
        AbsTreeGui tree_ = compo_.newTreeGui(n_);
        tree_.addTreeSelectionListener(new LocationsTreeEvent(window,r_));
        window.getPanelSymbolsDetailScroll().setViewportView(tree_);
        GuiBaseUtil.recalculate(window.getDetail());
    }

    private void buildRoot(AbsCompoFactory _compo, AbstractMutableTreeNode _n, MetaCaller _r) {
        MetaCaller curr_ = _r;
        AbstractMutableTreeNode rootLoc_ = _n;
        while (curr_ != null) {
            AbstractMutableTreeNodeCore child_ = curr_.getFirstChild();
            rootLoc_ = complete(_compo,rootLoc_,curr_);
            if (child_ instanceof MetaCaller) {
                curr_ = (MetaCaller) child_;
                continue;
            }
            while (curr_ != null) {
                AbstractMutableTreeNodeCore next_ = curr_.getNextSibling();
                if (next_ instanceof MetaCaller) {
                    curr_ = (MetaCaller) next_;
                    break;
                }
                if (curr_ == _r) {
                    curr_ = null;
                } else {
                    rootLoc_ = (AbstractMutableTreeNode) rootLoc_.getParent();
                    curr_ = (MetaCaller) curr_.getParent();
                }
            }
        }
    }

    private AbstractMutableTreeNode complete(AbsCompoFactory _compo,AbstractMutableTreeNode _blockToWrite, MetaCaller _read) {
        AbstractMutableTreeNode next_;
        CallerKind b_ = _read.getKind();
        String r_ ="";
        if (_read.isRecursive()) {
            r_+="<->";
        }
        String s_ ="";
        if (b_ != null) {
            s_+=b_;
        }
        String d_ = _read.getCall().build(result.getPage().getDisplayedStrings()).getDisplay();
        AbstractMutableTreeNode gr_ = _compo.newMutableTreeNode(r_+s_ +";"+ d_ +"::"+ _read.getNumber().size());
        _blockToWrite.add(gr_);
        next_ = gr_;
        if (_read.getFirstChild() != null) {
            return next_;
        }
        return _blockToWrite;
    }
    private CustList<CalleeToCaller> children(CustList<CalleeToCaller> _current) {
        CustList<CalleeToCaller> next_ = new CustList<CalleeToCaller>();
        for (CalleeToCaller c: _current) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            ls_.add(c.getCall().getCall());
            for (EntryCust<CallerKind, IdMap<SrcFileLocation, CustList<FileBlockIndex>>> e: CallersRef.loop(result.getPage(), ls_).entryList()) {
                for (EntryCust<SrcFileLocation, CustList<FileBlockIndex>> f: e.getValue().entryList()) {
                    SrcFileLocation caller_ = f.getKey();
                    CalleeToCaller found_ = foundCaller(c, caller_);
                    if (found_ == null) {
                        MetaCaller ch_ = new MetaCaller(e.getKey(),caller_,f.getValue());
                        MutableTreeNodeCoreUtil.add(c.getCall(),ch_);
                        next_.add(new CalleeToCaller(ch_,c));
                    } else {
                        found_.getCall().recurse();
                    }
                }
            }
        }
        return next_;
    }

    private CalleeToCaller foundCaller(CalleeToCaller _edge, SrcFileLocation _caller) {
        CalleeToCaller parent_ = _edge;
        while (parent_ != null) {
            SrcFileLocation elt_ = parent_.getCall().getCall();
            if (elt_ != null && _caller.match(elt_)) {
                return parent_;
            }
            parent_ = parent_.getParent();
        }
        return null;
    }
}
