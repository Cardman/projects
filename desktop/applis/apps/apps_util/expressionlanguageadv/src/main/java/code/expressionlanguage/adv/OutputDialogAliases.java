package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.guicompos.GuiAliasGroups;
import code.expressionlanguage.guicompos.GuiAliases;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.CustAliases;
import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.AbsTabbedPane;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class OutputDialogAliases {
    private final OutputDialogMapMessagesEdit keyWords;
    private final OutputDialogMapMessagesEdit aliases;
    private final AbsPlainButton val;
    private final AbsPlainButton cancel;

    public OutputDialogAliases(WindowCdmEditor _w) {
        keyWords = new OutputDialogMapMessagesEdit(_w,_w.getLgKeyWords(), keyWords());
        aliases = new OutputDialogMapMessagesEdit(_w,_w.getLgAliases(), aliases());
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        AbsTabbedPane tab_ = factories_.getCompoFactory().newAbsTabbedPane();
        tab_.add("key words",keyWords.getScroll());
        tab_.add("aliases",aliases.getScroll());
        all_.add(tab_);
        AbsPanel buttons_ = factories_.getCompoFactory().newLineBox();
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
        keys_.addAllElts(g_.allKeyWords().getKeys());
        keys_.addAllElts(g_.allEscapings().getKeys());
        StringMap<String> nbBasic_ = g_.allNbWordsBasic();
        keys_.addAllElts(g_.allNbWords(nbBasic_).getKeys());
        keys_.removeDuplicates();
        return keys_;
    }
    static StringList aliases() {
        GuiAliasGroups g_ = new GuiAliasGroups(new GuiAliases(), new CustAliases(), new LgNamesContent());
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
