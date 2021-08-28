package cards.gui.dialogs;
import java.awt.Dimension;

import javax.swing.WindowConstants;

import cards.gui.WindowCards;
import cards.gui.dialogs.events.ListenerClickTree;
import cards.gui.dialogs.help.ComparatorListSizeElement;
import cards.gui.dialogs.help.ElementHelp;
import cards.gui.dialogs.help.HelpIndexes;
import cards.gui.dialogs.help.NodeHelp;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.events.ClosingChildFrameEvent;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class FrameGeneralHelp extends ChildFrame {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.framegeneralhelp";

//    private static final String TRUMP_SUIT = "trumpSuit";
//
//    private static final String NORMAL_SUIT = "normalSuit";
//
//    private static final String TRUMPS = "trumps";
//
//    private static final String ORDER_TRUMPS = "orderTrumps";
//
//    private static final String ORDER_NO_TRUMPS = "orderNoTrumps";

    private static final String SEARCH_LABEL = "searchLabel";

//    private static final String TYPE = "type";

    //    private static final String TOUTE_CARTE_BELOTE = "touteCarteBelote";
//    private static final String ORDRE_CARTES_BELOTE = "ordreCartesBelote";
//    private static final String TOUTE_CARTE_TAROT = "touteCarteTarot";
//    private static final String ORDRE_CARTES_TAROT = "ordreCartesTarot";
//    private static final String EMPTY_STRING = "";
    private StringMap<String> messages;

    private ObjectMap<HelpIndexes,ElementHelp> elementsBis = new ObjectMap<HelpIndexes,ElementHelp>();

    private NodeHelp racineBis;

    //private MainWindow window;

    private RenderedPage editor;

    private AbsTextField field;

    private AbsSplitPane separateur;
    private AbsPlainButton search;

    public FrameGeneralHelp(String _titre, WindowCards _fenetre) {
        super(_fenetre.getLanguageKey(),_fenetre);
        setAccessFile(DIALOG_ACCESS);
        setDialogIcon(_fenetre);
        setTitle(_titre);
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new ClosingChildFrameEvent(this));
        //window = _fenetre;
    }

    @Override
    public void closeWindow() {
        setVisible(false);
//        editor.clearSession();
    }

//    @Override
//    public void dispose() {
//        super.dispose();
//        window.clearHelpDialogs();
//        window = null;
//    }

    public void initialize(WindowCards _w) {
        messages = WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _w.getLanguageKey(), getAccessFile());
        String lg_ = _w.getLanguageKey();
        elementsBis = _w.getHelpInitializerTask().getTrees().getVal(lg_);
        setFocusable(true);
        setFocusableWindowState(true);
        CustList<HelpIndexes> cles_ = new CustList<HelpIndexes>(
                elementsBis.getKeys());
        // Non null pour les valeurs
        cles_.sortElts(new ComparatorListSizeElement());
        racineBis = new NodeHelp(elementsBis.getVal(cles_.first()));
        AbstractMutableTreeNode root_ = _w.getCompoFactory().newMutableTreeNode(
                racineBis.nom());
        boolean wasNull_ = editor == null;
        AbsPanel container_;
        if (wasNull_) {
            container_ = _w.getCompoFactory().newPageBox();
        } else {
            container_ = getPane();
            container_.removeAll();
        }
        for (HelpIndexes chemin_ : cles_) {
            CustList<Integer> cheminSansNoeud_ = chemin_.left(
                    chemin_.getLastIndex());
            NodeHelp noeudLoc_;
            AbstractMutableTreeNode noeudLocGraphique_;
            noeudLoc_ = racineBis.element(cheminSansNoeud_);
            if (cheminSansNoeud_.isEmpty()) {
                noeudLocGraphique_ = root_;
            } else {
                noeudLocGraphique_ = root_
                        .getChildAt(cheminSansNoeud_.first());
                int lengthPath_ = cheminSansNoeud_.size();
                for (int indice_ = IndexConstants.SECOND_INDEX; indice_ < lengthPath_; indice_++) {
                    noeudLocGraphique_ = noeudLocGraphique_
                            .getChildAt(cheminSansNoeud_.get(indice_));
                }
            }
            ElementHelp elementLoc_ = elementsBis.getVal(chemin_);
            NodeHelp nouveauNoeud_ = new NodeHelp(elementLoc_);
            noeudLoc_.ajouterInfo(nouveauNoeud_);
            noeudLocGraphique_.add(
                    elementLoc_.nom());
        }
        if (wasNull_) {
            editor = new RenderedPage(_w.getCompoFactory().newAbsScrollPane(), _w.getFrames());
        }
        AbsTreeGui arbre_ = _w.getCompoFactory().newTreeGui(root_);
        arbre_.setRootVisible(false);
        arbre_.addTreeSelectionListener(new ListenerClickTree(racineBis, editor, arbre_));
        editor.initialize(racineBis.getElementLocal().getNavigation(),racineBis.getElementLocal().getMetaDocument());
        if (field == null) {
            field = _w.getCompoFactory().newTextField(20);
            search = _w.getCompoFactory().newPlainButton(messages.getVal(SEARCH_LABEL));
            editor.setSearchText(search);
            editor.setField(field);
            editor.addFinder();
        }
//        search.setTextAndSize(messages.getVal(SEARCH_LABEL));
        if (wasNull_) {
            separateur = _w.getCompoFactory().newAbsSplitPane(GuiConstants.HORIZONTAL_SPLIT,
                    _w.getCompoFactory().newAbsScrollPane(arbre_.getTree()), editor.getScroll());
            separateur.setPreferredSize(new Dimension(600, 550));
            separateur.setDividerLocation(150);
            container_.add(separateur);
            container_.add(field);
            container_.add(search);
            setContentPane(container_);
        } else {
            separateur.setLeftComponent(_w.getCompoFactory().newAbsScrollPane(arbre_.getTree()));
            container_.add(separateur);
            container_.add(field);
            container_.add(search);
        }
        separateur.revalidate();
        pack();
        setVisible(true);
    }
}
