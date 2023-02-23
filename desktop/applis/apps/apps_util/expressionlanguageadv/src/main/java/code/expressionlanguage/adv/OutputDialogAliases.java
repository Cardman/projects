package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.guicompos.GuiAliasGroups;
import code.expressionlanguage.guicompos.GuiAliases;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.CustAliases;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class OutputDialogAliases {
    private static final String NB_DIG0 = "NbDig0";
    private static final String NB_DIG1 = "NbDig1";
    private static final String NB_DIG2 = "NbDig2";
    private static final String NB_DIG3 = "NbDig3";
    private static final String NB_DIG4 = "NbDig4";
    private static final String NB_DIG5 = "NbDig5";
    private final OutputDialogMapMessagesEdit keyWords;
    private final OutputDialogMapMessagesEdit aliases;
    private final AbsPlainButton check;
    private final AbsPlainButton val;
    private final AbsPlainButton cancel;
    private final AbsTextArea errors;
    private final GuiAliases guiAliases;
    private final CustAliases custAliases;
    private final LgNamesContent lgNamesContent;

    public OutputDialogAliases(WindowCdmEditor _w) {
        keyWords = new OutputDialogMapMessagesEdit(_w,_w.getLgKeyWords(), keyWords());
        guiAliases = new GuiAliases();
        custAliases = new CustAliases();
        lgNamesContent = new LgNamesContent();
        aliases = new OutputDialogMapMessagesEdit(_w,_w.getLgAliases(), aliases(guiAliases, custAliases, lgNamesContent));
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        AbsTabbedPane tab_ = factories_.getCompoFactory().newAbsTabbedPane();
        tab_.add("key words",keyWords.getScroll());
        tab_.add("aliases",aliases.getScroll());
        all_.add(tab_);
        errors = factories_.getCompoFactory().newTextArea(32, 32);
        AbsScrollPane scErr_ = factories_.getCompoFactory().newAbsScrollPane(errors);
        scErr_.setPreferredSize(new MetaDimension(384,128));
        all_.add(scErr_);
        AbsPanel buttons_ = factories_.getCompoFactory().newLineBox();
        check = factories_.getCompoFactory().newPlainButton("X");
        check.addActionListener(new CheckAliases(this, _w,errors));
        buttons_.add(check);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateAliases(aliases.getMessagesRows(), keyWords.getMessagesRows(), _w));
        buttons_.add(val);
        cancel = factories_.getCompoFactory().newPlainButton("KO");
        cancel.addActionListener(new CancelBasic(_w.getDialogAliases()));
        buttons_.add(cancel);
        all_.add(buttons_);
        _w.getDialogAliases().setContentPane(all_);
        _w.getDialogAliases().pack();
        _w.getDialogAliases().setVisible(true);
    }
    static StringList keyWords() {
        KeyWords g_ = new KeyWords();
        StringList keys_ = new StringList();
        keys_.add(NB_DIG0);
        keys_.add(NB_DIG1);
        keys_.add(NB_DIG2);
        keys_.add(NB_DIG3);
        keys_.add(NB_DIG4);
        keys_.add(NB_DIG5);
        keys_.addAllElts(g_.allKeyWords().getKeys());
        keys_.addAllElts(g_.allEscapings().getKeys());
        StringMap<String> nbBasic_ = g_.allNbWordsBasic();
        keys_.addAllElts(g_.allNbWords(nbBasic_).getKeys());
        keys_.removeDuplicates();
        return keys_;
    }
    static StringList aliases() {
        return aliases(new GuiAliases(), new CustAliases(), new LgNamesContent());
    }
    static StringList aliases(GuiAliases _g, CustAliases _c, LgNamesContent _l) {
        GuiAliasGroups g_ = new GuiAliasGroups(_g, _c, _l);
        StringList keys_ = new StringList();
        keys_.addAllElts(g_.allPrimitives().getKeys());
        keys_.addAllElts(g_.allRefTypes().getKeys());
        feed(keys_, g_.allTableTypeMethodNames());
        feed(keys_, g_.allTableTypeFieldNames());
        feed(keys_, g_.allTableTypeVarTypes());
        feed(keys_, g_.allMergeTableTypeMethodNames());
        feed(keys_, g_.allTableTypeMethodParamNames());
        keys_.removeDuplicates();
        return keys_;
    }

    private static void feed(StringList _keys, CustList<CustList<KeyValueMemberName>> _list) {
        for (CustList<KeyValueMemberName> e: _list) {
            for (KeyValueMemberName f: e) {
                _keys.add(f.getKey());
            }
        }
    }

    private static void feed(StringList _keys, StringMap<CustList<KeyValueMemberName>> _map) {
        for (EntryCust<String,CustList<KeyValueMemberName>> e: _map.entryList()) {
            for (KeyValueMemberName f: e.getValue()) {
                _keys.add(f.getKey());
            }
        }
    }

    public CustAliases getCustAliases() {
        return custAliases;
    }

    public AbsTextArea getErrors() {
        return errors;
    }

    public AbsPlainButton getCheck() {
        return check;
    }

    public GuiAliases getGuiAliases() {
        return guiAliases;
    }

    public LgNamesContent getLgNamesContent() {
        return lgNamesContent;
    }

    public AbsPlainButton getCancel() {
        return cancel;
    }

    public OutputDialogMapMessagesEdit getAliases() {
        return aliases;
    }

    public OutputDialogMapMessagesEdit getKeyWords() {
        return keyWords;
    }


    public AbsPlainButton getVal() {
        return val;
    }

}
