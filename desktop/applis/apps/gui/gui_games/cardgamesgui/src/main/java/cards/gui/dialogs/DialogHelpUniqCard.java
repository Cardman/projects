package cards.gui.dialogs;

import cards.consts.*;
import cards.facade.Games;
import cards.gui.containers.*;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.*;
import code.scripts.messages.cards.*;
import code.sml.util.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;

public abstract class DialogHelpUniqCard<T> extends DialogHelpCards {
    private static final String EMPTY="";
    private static final String RETURN_LINE="\n";
    private static final String SPACE=" ";
    private static final String TAB="\t";
    protected DialogHelpUniqCard(AbstractProgramInfos _fact, AbstractAtomicBoolean _modal) {
        super(_fact, _modal);
    }

    protected abstract IdList<Suit> suits();
    protected abstract IdMap<Suit, IdList<T>> couleurs();
    protected abstract int id(T _carte);
    public void setDialogue(IdMap<Suit, CustList<IdList<T>>> _cartesPossibles,
                                        IdMap<Suit, CustList<IdList<T>>> _cartesCertaines, IdMap<Suit, IdList<T>> _repartitionJouees,
                                        StringList _pseudos, TranslationsLg _lg) {
        AbsPanel container_= getCompo().newLineBox();
        AbsPanel panneau2_= getCompo().newBorder();
        IdMap<Suit, IdList<T>> couleurs_ = couleurs();
        IdList<Suit> suits_ = suits();
        AbsPanel panneau3_ = getCompo().newLineBox();
        int nbPlayers_ = _pseudos.size();
        nbPlayers_++;
        //Dog hand
        for(int indicePseudo_ = IndexConstants.SECOND_INDEX; indicePseudo_<nbPlayers_; indicePseudo_++) {
            AbsTextArea zone_ = getCompo().newTextArea(EMPTY);
            zone_.setEditable(false);
            pseudo(_pseudos, indicePseudo_, zone_);
            StringBuilder strBuild_ = player(_cartesPossibles, _cartesCertaines, _repartitionJouees, _lg, couleurs_, suits_, indicePseudo_);
            zone_.append(strBuild_.toString());
            panneau3_.add(zone_);
        }
        panneau2_.add(panneau3_,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsScrollPane ascenseur_= getCompo().newAbsScrollPane(panneau2_);
        ascenseur_.setPreferredSize(new MetaDimension(600,600));
        container_.add(ascenseur_);
        getAbsDialog().setContentPane(container_);
        getAbsDialog().pack();
        getAbsDialog().setVisible(true);
    }

    private StringBuilder player(IdMap<Suit, CustList<IdList<T>>> _cartesPossibles, IdMap<Suit, CustList<IdList<T>>> _cartesCertaines, IdMap<Suit, IdList<T>> _repartitionJouees, TranslationsLg _lg, IdMap<Suit, IdList<T>> _couleurs, IdList<Suit> _suits, int _indicePseudo) {
        StringMap<String> messages_ = ContainerSingleImpl.file(_lg);
        StringBuilder strBuild_ = new StringBuilder();
        for (Suit s: _suits) {
            IdList<T> h_ = _couleurs.getVal(s);
            if(s != Suit.UNDEFINED) {
                strBuild_.append(StringUtil.concat(Games.toString(s, _lg),RETURN_LINE));
            }
            for(T carte_:h_) {
                strBuild_.append(TAB);
                strBuild_.append(StringUtil.concat(Games.toCardString(id(carte_), _lg),SPACE));
                CustList<IdList<T>> valPoss_ = _cartesPossibles.getVal(s);
                if(containsCard(valPoss_, _indicePseudo, carte_)) {
                    strBuild_.append(messages_.getVal(MessagesGuiCards.MAIN_POSSIBLE));
                }
                CustList<IdList<T>> valCert_ = _cartesCertaines.getVal(s);
                if(containsCard(valCert_, _indicePseudo, carte_)) {
                    strBuild_.append(messages_.getVal(MessagesGuiCards.MAIN_OWNED));
                }
                if(_repartitionJouees.getVal(s).containsObj(carte_)) {
                    strBuild_.append(messages_.getVal(MessagesGuiCards.MAIN_PLAYED));
                }
                strBuild_.append(RETURN_LINE);
            }
        }
        return strBuild_;
    }

    private boolean containsCard(CustList<IdList<T>> _hypo, int _indicePseudo, T _carte) {
        return _hypo != null && _hypo.isValidIndex(_indicePseudo) && _hypo.get(_indicePseudo).containsObj(_carte);
    }

    private void pseudo(StringList _pseudos, int _i, AbsTextArea _zone) {
        if(_i < _pseudos.size()) {
            _zone.append(StringUtil.concat(_pseudos.get(_i),RETURN_LINE));
        } else {
            _zone.append(RETURN_LINE);
        }
    }
}
