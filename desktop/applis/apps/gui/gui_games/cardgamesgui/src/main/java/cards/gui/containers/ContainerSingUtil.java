package cards.gui.containers;

import cards.gui.WindowCardsInt;
import cards.gui.animations.AddTextEvents;
import cards.gui.animations.SimulationDiscard;
import cards.gui.animations.SimulationRefreshHand;
import cards.gui.animations.WithdrawCards;
import cards.gui.labels.AbsMetaLabelCard;
import cards.gui.labels.GraphicCard;
import cards.gui.labels.IntCardConverter;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.images.AbstractImageFactory;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.threads.ThreadUtil;
import code.util.CustList;
import code.util.IdList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ContainerSingUtil<T> {
    private final IntCardConverter<T> converter;

    public ContainerSingUtil(IntCardConverter<T> _conv) {
        converter = _conv;
    }
    public void seeHand(ContainerSingleImpl _container,IdList<T> _calledCards, AbsPanel _center, byte _player, byte _user) {
        if (_player != _user) {
            return;
        }
        _container.getOwner().getFrames().getCompoFactory().invokeNow(new SimulationRefreshHand<T>(_container, converter, _calledCards,_center));
    }
    public void seeDog(ContainerSingleImpl _container, IdList<T> _calledCards, AbsPanel _center) {
        String event_ = StringUtil.concat(_container.fileSimu().getVal(MessagesGuiCards.SIMU_SHOWN_DOG),ContainerGame.RETURN_LINE);
        _container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(_container, event_));
        ThreadUtil.sleep(_container.getOwner().getThreadFactory(),1000);
//        container.setCanDiscard(false);
        _container.getOwner().getFrames().getCompoFactory().invokeNow(new SimulationRefreshHand<T>(_container,converter, _calledCards, _center));
        event_ = StringUtil.concat(_container.fileSimu().getVal(MessagesGuiCards.SIMU_PLAYERS_SHOW_DOG),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        _container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(_container, event_));
        _container.revalidate();
        event_ = StringUtil.concat(_container.fileSimu().getVal(MessagesGuiCards.SIMU_TAKE_DOG),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        _container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(_container, event_));
        _container.getOwner().getFrames().getCompoFactory().invokeNow(new WithdrawCards(_center));
    }
    public void seeHandDog(ContainerSingleImpl _container, IdList<T> _takerHand, AbsPanel _panelHand, byte _player, byte _user) {
        seeHand(_container,_takerHand,_panelHand,_player, _user);
    }
    public void mergeDog(ContainerSingleImpl _container, AbsPanel _center, int _total) {
        String mess_ = _container.fileSimu().getVal(MessagesGuiCards.SIMU_DISCARD_CARDS);
        String event_ = StringUtil.concat(StringUtil.simpleNumberFormat(mess_, _total),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        _container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(_container, event_));
        _container.getOwner().getFrames().getCompoFactory().invokeNow(new SimulationDiscard<T>(_container, _center, converter, _total));
    }

    public CustList<GraphicCard<T>> getGraphicCardsGene(WindowCardsInt _fact, TranslationsLg _lg, CustList<T> _hand) {
        CustList<GraphicCard<T>> list_;
        list_ = new CustList<GraphicCard<T>>();
        boolean entered_ = false;
        for(T c: _hand) {
            GraphicCard<T> carte_ = prepare(_fact, _lg, c, !entered_);
//            int w_ = carte_.getWidth();
//            int h_ = carte_.getHeight();
//            AbstractImage img_ = imageFactory_.newImageArgb(w_, h_);
//            img_.setFont(carte_.getPaintableLabel());
//            carte_.paintComponent(img_);
//            carte_.setIcon(imageFactory_,img_);
            list_.add(carte_);
            entered_ = true;
        }
        return list_;
    }
    public void setTalon(TranslationsLg _lg, WindowCardsInt _compoFactory, int _total, AbsPanel _center) {
        _center.removeAll();
        _center.setBackground(GuiConstants.newColor(0, 125, 0));

        CustList<GraphicCard<T>> ls_ = getGraphicCardsGene(_compoFactory, _lg, _total);
        int s_ = ls_.size();
        for (byte b = IndexConstants.FIRST_INDEX; b < s_; b++) {
            _center.add(ls_.get(b).getPaintableLabel());
        }
        _center.setSize(_center.getPreferredSizeValue());
    }

    public CustList<GraphicCard<T>> getGraphicCardsGene(WindowCardsInt _fact, TranslationsLg _lg, int _hand) {
        CustList<GraphicCard<T>> list_;
        list_ = new CustList<GraphicCard<T>>();
        boolean entered_ = false;
        for (int i = 0; i < _hand; i++) {
            GraphicCard<T> carte_ = prepare(_fact, _lg, !entered_);
            list_.add(carte_);
            entered_ = true;
        }
//        for(T c: _hand) {
//            GraphicCard<T> carte_ = prepare(_fact, _lg, c, !entered_);
////            int w_ = carte_.getWidth();
////            int h_ = carte_.getHeight();
////            AbstractImage img_ = imageFactory_.newImageArgb(w_, h_);
////            img_.setFont(carte_.getPaintableLabel());
////            carte_.paintComponent(img_);
////            carte_.setIcon(imageFactory_,img_);
//            list_.add(carte_);
//            entered_ = true;
//        }
        return list_;
    }

    public GraphicCard<T> prepare(WindowCardsInt _fact, TranslationsLg _lg, T _c, boolean _full) {
        AbstractImageFactory imageFactory_ = _fact.getImageFactory();
        GraphicCard<T> carte_=new GraphicCard<T>(converter, _c, _full, _fact.getFrames(), _lg);
        carte_.setPreferredSize(!_full);
        AbsMetaLabelCard.paintCard(imageFactory_, carte_);
        return carte_;
    }

    public GraphicCard<T> prepare(WindowCardsInt _fact, TranslationsLg _lg, boolean _full) {
        AbstractImageFactory imageFactory_ = _fact.getImageFactory();
        GraphicCard<T> carte_=new GraphicCard<T>(converter, _full, _fact.getFrames().getCompoFactory(), _lg);
        carte_.setPreferredSize(!_full);
        AbsMetaLabelCard.paintCard(imageFactory_, carte_);
        return carte_;
    }
}
