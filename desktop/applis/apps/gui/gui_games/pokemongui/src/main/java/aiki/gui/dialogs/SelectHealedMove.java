package aiki.gui.dialogs;




import aiki.comparators.TrMovesComparator;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.walk.HealedMoveEvent;
import code.gui.AbsDialog;
import code.gui.AbsPanel;
import code.gui.AbsButton;
import code.gui.GuiConstants;
import code.gui.events.ClosingDialogEvent;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SelectHealedMove {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.selecthealedmove";

    private static final String TITLE = "title";

    private static final String CANCEL = "cancel";

    private static final String SPACE = " ";
    private final AbsDialog absDialog;

    private FacadeGame facade;

    private final AbsPanel movesLearnt;
    private final AbsCompoFactory compo;
    private StringMap<String> messages;

    public SelectHealedMove(AbstractProgramInfos _infos) {
        absDialog = _infos.getFrameFactory().newDialog();
        absDialog.setAccessFile(DIALOG_ACCESS);
        compo = _infos.getCompoFactory();
        movesLearnt = compo.newPageBox();
    }

    public static void setSelectHealedMove(WindowAiki _parent, FacadeGame _facade) {
        _parent.getSelectHealedMove().init(_parent, _facade);
    }

    private void init(WindowAiki _parent, FacadeGame _facade) {
        absDialog.setDialogIcon(_parent.getImageFactory(),_parent.getCommonFrame());
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), absDialog.getAccessFile());
        absDialog.setTitle(messages.getVal(TITLE));
        facade = _facade;
        AbsPanel contentPane_ = compo.newBorder();
        StringMap<Short> moves_ = facade.getPlayer().getChosenMoves();
        StringList keys_ = new StringList(moves_.getKeys());
//        keys_.sort(new Comparator<String>() {
//            @Override
//            public int compare(String _o1, String _o2) {
//                String trOne_ = facade.translateMove(_o1);
//                String trTwo_ = facade.translateMove(_o2);
//                return trOne_.compareTo(trTwo_);
//            }
//        });
        keys_.sortElts(new TrMovesComparator(facade.getData()));
        movesLearnt.removeAll();
        for (String m: keys_) {
            String tr_ = facade.translateMove(m);
            AbsButton check_ = _parent.getCompoFactory().newPlainButton(StringUtil.concat(tr_,SPACE,Long.toString(moves_.getVal(m))));
            check_.addActionListener(new HealedMoveEvent(this,facade, m));
            movesLearnt.add(check_);
        }
        contentPane_.add(movesLearnt, GuiConstants.BORDER_LAYOUT_CENTER);
        //window.healMove(move);
        //contentPane_.add(new JScrollPane(new PaginatorHealingItem(this, _facade)), BorderLayout.CENTER);
        AbsPanel buttons_ = compo.newLineBox();
        AbsButton cancel_ = _parent.getCompoFactory().newPlainButton(messages.getVal(CANCEL));
        cancel_.addActionListener(new ClosingDialogEvent(absDialog));
        buttons_.add(cancel_);
        contentPane_.add(buttons_, GuiConstants.BORDER_LAYOUT_SOUTH);
        absDialog.setContentPane(contentPane_);
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
        absDialog.setVisible(true);
    }

    public AbsDialog getAbsDialog() {
        return absDialog;
    }
}
