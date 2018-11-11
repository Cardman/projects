package aiki.gui.dialogs;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.WindowConstants;

import aiki.Resources;
import aiki.comparators.TrMovesComparator;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.components.walk.HealedMoveEvent;
import code.gui.Dialog;
import code.gui.LabelButton;
import code.gui.Panel;
import code.gui.events.ClosingDialogEvent;
import code.util.StringList;
import code.util.StringMap;

public final class SelectHealedMove extends Dialog {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.SelectHealedMove";

    private static final SelectHealedMove DIALOG = new SelectHealedMove();

    private static final String TITLE = "title";

    private static final String CANCEL = "cancel";

    private static final String SPACE = " ";

    private FacadeGame facade;

    private Panel movesLearnt = new Panel();

    private StringMap<String> messages;

    private SelectHealedMove() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setSelectHealedMove(MainWindow _parent, FacadeGame _facade) {
        DIALOG.init(_parent, _facade);
    }

    private void init(MainWindow _parent, FacadeGame _facade) {
        setDialogIcon(_parent);
        messages = getMessages(_parent,Resources.MESSAGES_FOLDER);
        setTitle(messages.getVal(TITLE));
        facade = _facade;
        Panel contentPane_ = new Panel();
        contentPane_.setLayout(new BorderLayout());
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
        movesLearnt.setLayout(new BoxLayout(movesLearnt.getComponent(), BoxLayout.PAGE_AXIS));
        movesLearnt.removeAll();
        for (String m: keys_) {
            String tr_ = facade.translateMove(m);
            LabelButton check_ = new LabelButton(StringList.concat(tr_,SPACE,Long.toString(moves_.getVal(m))));
            check_.addMouseListener(new HealedMoveEvent(this,facade, m));
            movesLearnt.add(check_);
        }
        contentPane_.add(movesLearnt, BorderLayout.CENTER);
        //window.healMove(move);
        //contentPane_.add(new JScrollPane(new PaginatorHealingItem(this, _facade)), BorderLayout.CENTER);
        Panel buttons_ = new Panel();
        LabelButton cancel_ = new LabelButton(messages.getVal(CANCEL));
        cancel_.addMouseListener(new ClosingDialogEvent(this));
        buttons_.add(cancel_);
        contentPane_.add(buttons_, BorderLayout.SOUTH);
        setContentPane(contentPane_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
