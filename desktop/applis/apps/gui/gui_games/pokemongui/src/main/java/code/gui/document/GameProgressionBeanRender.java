package code.gui.document;

import aiki.beans.*;
import aiki.beans.game.*;
import aiki.facade.*;
import code.gui.*;
import code.util.*;

public final class GameProgressionBeanRender extends AbsBeanRender {
    private final GameProgressionBean bean = new GameProgressionBean();
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(bean,_facade, _form);
        initPage();
        setBackground(GuiConstants.WHITE);
        setTitledBorder(file().getVal(MessagesProgGameprog.M_P_95_TITLE));
        initPage();
        displayBool(bean.getFinishedGame(),CommonBean.TRUE_VALUE,bean.getHeroImage());
        displayBool(bean.getFinishedGame(),CommonBean.TRUE_VALUE,bean.getHeroImageOppositeSex());
        displayBool(bean.getFinishedGame(),CommonBean.TRUE_VALUE,bean.getEndGameImage());
        displayBoolFalse(MessagesPkBean.GAMEPROG,bean.getFinishedGame(),MessagesProgGameprog.M_P_95_NICKNAME,bean.getNickname());
        displayBool(bean.getFinishedGame(),CommonBean.FALSE_VALUE,bean.getHeroImage());
        feedParents();
        nextPart();
        build(bean.getNotAtAllFamiliesBase(),file().getVal(MessagesProgGameprog.M_P_95_TITLENOTATALL));
        feedParents();
        nextPart();
        buildPart(file().getVal(MessagesProgGameprog.M_P_95_TITLEPART));
        feedParents();
        nextPart();
        build(bean.getFullFamiliesBase(),file().getVal(MessagesProgGameprog.M_P_95_TITLEFULL));
        feedParents();
        nextPart();
        displayTrainerPlaceNamesList(MessagesPkBean.GAMEPROG,bean.getUnBeatenImportantTrainers(),MessagesProgGameprog.M_P_95_UNBEATTRAINER);
        nextPart();
        displayTrainerPlaceNamesList(MessagesPkBean.GAMEPROG,bean.getBeatenImportantTrainers(),MessagesProgGameprog.M_P_95_BEATTRAINER);
        nextPart();
        display(MessagesPkBean.GAMEPROG,bean.getRemainingOtherTrainerPlaces(),MessagesProgGameprog.M_P_95_OTHERTRAINERS);
        initGrid();
        headerCols(MessagesPkBean.GAMEPROG,bean.getRemainingOtherTrainerPlaces(),MessagesProgGameprog.M_P_95_OTHERTRAINERSPLACE,MessagesProgGameprog.M_P_95_OTHERTRAINERSNUMBER);
        for (EntryCust<Integer,PlaceNamePk> e: bean.getRemainingOtherTrainerPlaces().entryList()) {
            formatMessageDirCts(e.getValue().getName());
            formatMessageDirCts(Long.toString(e.getValue().getIndex()));
        }
        feedParents();
        nextPart();
        displayStringList(MessagesPkBean.GAMEPROG,bean.getUnVisitedPlaces(),MessagesProgGameprog.M_P_95_UNVISITPLACE);
        nextPart();
        displayStringList(MessagesPkBean.GAMEPROG,bean.getVisitedPlaces(),MessagesProgGameprog.M_P_95_VISITPLACE);
        nextPart();
        initPage();
        formatMessage(MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_NBREMPKLEVEL,Long.toString(bean.getNbRemainingNotMaxLevel()));
        formatMessage(MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_NBREMPKHAPPINESS,Long.toString(bean.getNbRemainingNotMaxHappiness()));
        formatMessage(MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_NBREMEGG,Long.toString(bean.getNbRemainingEggs()));
        formatMessage(MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_REPEL,Long.toString(bean.getRemainStepsRepel()));
        formatMessage(MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_MONEY,bean.getMoney().toNumberString());
        feedParents();
    }
    private void build(NatStringTreeMap<CustList<CustList<ImgPkPlayer>>> _groups, String _title) {
        initPage();
        setTitledBorder(_title);
        for (EntryCust<String,CustList<CustList<ImgPkPlayer>>> e: _groups.entryList()) {
            initLine();
            formatMessageDir(e.getKey());
            nextPart();
            for (CustList<ImgPkPlayer> s:e.getValue()) {
                initPage();
                buildPkList(s);
                feedParents();
            }
            feedParents();
        }
    }

    private void buildPart(String _title) {
        initPage();
        setTitledBorder(_title);
        int len_ = bean.getPartialFamiliesBaseNotCaught().size();
        for (int i = 0; i < len_; i++) {
            EntryCust<String, CustList<CustList<ImgPkPlayer>>> e_ = bean.getPartialFamiliesBaseNotCaught().getEntry(i);
            initLine();
            formatMessageDir(e_.getKey());
            initGrid();
            int s_ = e_.getValue().size();
            getBuilder().setColCount(s_+1);
            formatMessageCts(MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_NOTCAUGHTPKCAUGHTNOTPART);
            nextPart();
            for (int j = 0; j < s_; j++) {
                initPage();
                buildPkList(e_.getValue().get(j));
                feedParentsCts();
            }
            formatMessageCts(MessagesPkBean.GAMEPROG,MessagesProgGameprog.M_P_95_NOTCAUGHTPKCAUGHTPART);
            nextPart();
            for (int j = 0; j < s_; j++) {
                initPage();
                buildPkList(bean.getPartialFamiliesBaseCaught().getValue(i).get(j));
                feedParentsCts();
            }
            feedParents();
            feedParents();
        }
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.GAMEPROG).getMapping();
    }
    @Override
    public String appName() {
        return MessagesPkBean.APP_BEAN;
    }
}
