package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.syntax.*;
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
        updateCallersView(window, result);
    }

    static void updateCallersView(WindowWithTreeImpl _window, ResultRowSrcLocationList _result) {
        AbsTreeGui tree_ = tree(_window, _result);
        _window.getPanelSymbolsDetailScroll().setViewportView(tree_);
        GuiBaseUtil.recalculate(_window.getPanelSymbolsDetailScroll());
    }

    static AbstractMutableTreeNode node(WindowWithTreeImpl _window, ResultRowSrcLocationList _result, MetaCaller _r) {
        CustList<CalleeToCaller> current_ = new CustList<CalleeToCaller>();
        CustList<MetaCaller> roots_ = new CustList<MetaCaller>();
        CalleeToCaller parent_ = new CalleeToCaller(_r);
        for (SrcFileLocation s: _result.getUsages()) {
            CustList<FileBlockIndex> ave_ = new CustList<FileBlockIndex>();
            ave_.add(new FileBlockIndex(s.getFile(),s.getIndex(),null,s));
            MetaCaller m_ = new MetaCaller(null,s, ave_);
            current_.add(new CalleeToCaller(m_,parent_));
            roots_.add(m_);
            MutableTreeNodeCoreUtil.add(_r,m_);
        }
        while (true) {
            CustList<CalleeToCaller> next_ = children(current_, _result);
            if (next_.isEmpty()) {
                break;
            }
            current_ = next_;
        }
        AbsCompoFactory compo_ = _window.getCommonFrame().getFrames().getCompoFactory();
        AbstractMutableTreeNode n_ = compo_.newMutableTreeNode("");
        for (MetaCaller r: roots_) {
            buildRoot(compo_, n_, r, _result);
        }
        return n_;
    }

    private static AbsTreeGui tree(WindowWithTreeImpl _window, ResultRowSrcLocationList _result) {
        AbsCompoFactory compo_ = _window.getCommonFrame().getFrames().getCompoFactory();
        AbsTreeGui tree_ = compo_.newTreeGui(_result.getNode());
        tree_.addTreeSelectionListener(new LocationsTreeEvent(_result.getPage(), _window, _result.getRoot()));
        return tree_;
    }

    private static void buildRoot(AbsCompoFactory _compo, AbstractMutableTreeNode _n, MetaCaller _r, ResultRowSrcLocationList _result) {
        MetaCaller curr_ = _r;
        AbstractMutableTreeNode rootLoc_ = _n;
        while (curr_ != null) {
            AbstractMutableTreeNodeCore child_ = curr_.getFirstChild();
            rootLoc_ = complete(_compo,rootLoc_,curr_, _result);
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
                if (curr_ == _r || rootLoc_ == null) {
                    curr_ = null;
                } else {
                    rootLoc_ = (AbstractMutableTreeNode) rootLoc_.getParent();
                    curr_ = (MetaCaller) curr_.getParent();
                }
            }
        }
    }

    private static AbstractMutableTreeNode complete(AbsCompoFactory _compo, AbstractMutableTreeNode _blockToWrite, MetaCaller _read, ResultRowSrcLocationList _result) {
        CallerKind b_ = _read.getKind();
        String r_ ="";
        if (_read.isRecursive()) {
            r_+="<->";
        }
        String s_ ="";
        if (b_ != null) {
            s_+=b_;
        }
        String d_ = _read.getCall().build(_result.getPage().getDisplayedStrings()).getDisplay();
        AbstractMutableTreeNode gr_ = _compo.newMutableTreeNode(_read.getNumber().size()+"::"+r_+s_ +";"+ d_);
        _blockToWrite.add(gr_);
        if (_read.getFirstChild() != null) {
            return gr_;
        }
        return _blockToWrite;
    }
    private static CustList<CalleeToCaller> children(CustList<CalleeToCaller> _current, ResultRowSrcLocationList _result) {
        CustList<CalleeToCaller> next_ = new CustList<CalleeToCaller>();
        for (CalleeToCaller c: _current) {
            CustList<SrcFileLocation> ls_ = new CustList<SrcFileLocation>();
            ls_.add(c.getCall().getCall());
            for (EntryCust<CallerKind, IdMap<SrcFileLocation, CustList<FileBlockIndex>>> e: CallersRef.loop(_result.getPage(), ls_).entryList()) {
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

    private static CalleeToCaller foundCaller(CalleeToCaller _edge, SrcFileLocation _caller) {
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
