package aiki.beans.game;

import aiki.beans.PkProg;
import aiki.beans.ProgGameInit;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.Game;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import code.bean.nat.NatNavigation;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.scripts.pages.aiki.CssInit;
import code.scripts.pages.aiki.MessagesInit;
import code.scripts.pages.aiki.PagesInit;
import code.sml.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import org.junit.Test;

public final class GameProgressionBeanTest extends InitDbGameProgressionBean {
    static final String GIRL = "G";
    static final String BOY = "B";
    static final String MONEY_REM = "20";

    @Test
    public void nickname1() {
        assertEq(GIRL, callGameProgressionBeanNicknameGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void nickname2() {
        assertEq(BOY, callGameProgressionBeanNicknameGet(displaying(beanProg(EN, fac(progress(),BOY, Sex.BOY)))));
    }

    @Test
    public void heroImage1() {
        assertEq(one(IMG_H_1), callGameProgressionBeanHeroImageGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void heroImage2() {
        assertEq(one(IMG_H_2), callGameProgressionBeanHeroImageGet(displaying(beanProg(EN, fac(progress(),BOY, Sex.BOY)))));
    }

    @Test
    public void heroImageOppositeSex1() {
        assertEq(one(IMG_H_2), callGameProgressionBeanHeroImageOppositeSexGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void heroImageOppositeSex2() {
        assertEq(one(IMG_H_1), callGameProgressionBeanHeroImageOppositeSexGet(displaying(beanProg(EN, fac(progress(),BOY, Sex.BOY)))));
    }

    @Test
    public void finish1() {
        assertFalse(callGameProgressionBeanFinishedGameGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void finish2() {
        assertFalse(callGameProgressionBeanFinishedGameGet(displaying(beanProg(EN, fac(progress(),BOY, Sex.BOY)))));
    }

    @Test
    public void finish3() {
        assertTrue(callGameProgressionBeanFinishedGameGet(displaying(beanProg(EN, finish(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void finish4() {
        assertTrue(callGameProgressionBeanFinishedGameGet(displaying(beanProg(EN, finish(progress(),BOY, Sex.BOY)))));
    }

    @Test
    public void endGameImage1() {
        assertEq(one(IMG_H_5), callGameProgressionBeanEndGameImageGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void endGameImage2() {
        assertEq(one(IMG_H_5), callGameProgressionBeanEndGameImageGet(displaying(beanProg(EN, fac(progress(),BOY, Sex.BOY)))));
    }

    @Test
    public void repel1() {
        assertEq(15, callGameProgressionBeanRemainStepsRepelGet(displaying(beanProg(EN, incomplete(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void repel2() {
        assertEq(15, callGameProgressionBeanRemainStepsRepelGet(displaying(beanProg(EN, incomplete(progress(),BOY, Sex.BOY)))));
    }

    @Test
    public void money1() {
        assertEq(LgInt.newLgInt(MONEY_REM), callGameProgressionBeanMoneyGet(displaying(beanProg(EN, incomplete(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void money2() {
        assertEq(LgInt.newLgInt(MONEY_REM), callGameProgressionBeanMoneyGet(displaying(beanProg(EN, incomplete(progress(),BOY, Sex.BOY)))));
    }

    @Test
    public void eggs1() {
        assertEq(1, callGameProgressionBeanNbRemainingEggsGet(displaying(beanProg(EN, incomplete(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void eggs2() {
        assertEq(1, callGameProgressionBeanNbRemainingEggsGet(displaying(beanProg(EN, incomplete(progress(),BOY, Sex.BOY)))));
    }

    @Test
    public void nbRemainingNotMaxLevel1() {
        assertEq(1, callGameProgressionBeanNbRemainingNotMaxLevelGet(displaying(beanProg(EN, incomplete(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void nbRemainingNotMaxLevel2() {
        assertEq(1, callGameProgressionBeanNbRemainingNotMaxLevelGet(displaying(beanProg(EN, incomplete(progress(),BOY, Sex.BOY)))));
    }

    @Test
    public void nbRemainingNotMaxHappiness1() {
        assertEq(1, callGameProgressionBeanNbRemainingNotMaxHappinessGet(displaying(beanProg(EN, incomplete(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void nbRemainingNotMaxHappiness2() {
        assertEq(1, callGameProgressionBeanNbRemainingNotMaxHappinessGet(displaying(beanProg(EN, incomplete(progress(),BOY, Sex.BOY)))));
    }

    @Test
    public void unvisitedPlaces1() {
        assertSizeEq(1,callGameProgressionBeanUnVisitedPlacesGet(displaying(beanProg(EN, visit(progressPlaces(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void unvisitedPlaces2() {
        assertSizeEq(1,callGameProgressionBeanUnVisitedPlacesGet(displaying(beanProg(EN, visit(progressPlaces(),BOY,Sex.BOY)))));
    }

    @Test
    public void unvisitedPlaces3() {
        assertEq(CI_2,elt(callGameProgressionBeanUnVisitedPlacesGet(displaying(beanProg(EN, visit(progressPlaces(),GIRL,Sex.GIRL)))),0));
    }

    @Test
    public void unvisitedPlaces4() {
        assertEq(CI_2,elt(callGameProgressionBeanUnVisitedPlacesGet(displaying(beanProg(EN, visit(progressPlaces(),BOY,Sex.BOY)))),0));
    }

    @Test
    public void visitedPlaces1() {
        assertSizeEq(1,callGameProgressionBeanVisitedPlacesGet(displaying(beanProg(EN, visit(progressPlaces(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void visitedPlaces2() {
        assertSizeEq(1,callGameProgressionBeanVisitedPlacesGet(displaying(beanProg(EN, visit(progressPlaces(),BOY,Sex.BOY)))));
    }

    @Test
    public void visitedPlaces3() {
        assertEq(CI_1,elt(callGameProgressionBeanVisitedPlacesGet(displaying(beanProg(EN, visit(progressPlaces(),GIRL,Sex.GIRL)))),0));
    }

    @Test
    public void visitedPlaces4() {
        assertEq(CI_1,elt(callGameProgressionBeanVisitedPlacesGet(displaying(beanProg(EN, visit(progressPlaces(),BOY,Sex.BOY)))),0));
    }

    @Test
    public void partial1() {
        assertSizeEq(1,callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void partial2() {
        assertSizeEq(1,callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY)))));
    }

    @Test
    public void partial3() {
        assertEq(PROG_PK_TR1,first(elt(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))),0)));
    }

    @Test
    public void partial4() {
        assertEq(PROG_PK_TR1,first(elt(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY)))),0)));
    }

    @Test
    public void partial5() {
        assertSizeEq(2,second(elt(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))),0)));
    }

    @Test
    public void partial6() {
        assertSizeEq(2,second(elt(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY)))),0)));
    }

    @Test
    public void partial7() {
        assertSizeEq(0,elt(second(elt(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))),0)),0));
    }

    @Test
    public void partial8() {
        assertSizeEq(0,elt(second(elt(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY)))),0)),0));
    }

    @Test
    public void partial9() {
        assertSizeEq(1,elt(second(elt(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))),0)),1));
    }

    @Test
    public void partial10() {
        assertSizeEq(1,elt(second(elt(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY)))),0)),1));
    }

    @Test
    public void partial11() {
        assertEq(PROG_PK2,elt(elt(second(elt(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))),0)),1),0));
    }

    @Test
    public void partial12() {
        assertEq(PROG_PK2,elt(elt(second(elt(callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY)))),0)),1),0));
    }

    @Test
    public void not1() {
        assertSizeEq(2,callGameProgressionBeanNotAtAllFamiliesBaseGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void not2() {
        assertSizeEq(2,callGameProgressionBeanNotAtAllFamiliesBaseGet(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY)))));
    }

    @Test
    public void not3() {
        assertEq(PROG_PK_TR3,first(elt(callGameProgressionBeanNotAtAllFamiliesBaseGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))),0)));
    }

    @Test
    public void not4() {
        assertEq(PROG_PK_TR3,first(elt(callGameProgressionBeanNotAtAllFamiliesBaseGet(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY)))),0)));
    }

    @Test
    public void not5() {
        assertSizeEq(2,second(elt(callGameProgressionBeanNotAtAllFamiliesBaseGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))),0)));
    }

    @Test
    public void not6() {
        assertSizeEq(2,second(elt(callGameProgressionBeanNotAtAllFamiliesBaseGet(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY)))),0)));
    }

    @Test
    public void not7() {
        assertSizeEq(1,elt(second(elt(callGameProgressionBeanNotAtAllFamiliesBaseGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))),0)),0));
    }

    @Test
    public void not8() {
        assertSizeEq(1,elt(second(elt(callGameProgressionBeanNotAtAllFamiliesBaseGet(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY)))),0)),0));
    }

    @Test
    public void not9() {
        assertEq(PROG_PK3,elt(elt(second(elt(callGameProgressionBeanNotAtAllFamiliesBaseGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))),0)),0),0));
    }

    @Test
    public void not10() {
        assertEq(PROG_PK3,elt(elt(second(elt(callGameProgressionBeanNotAtAllFamiliesBaseGet(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY)))),0)),0),0));
    }
    @Test
    public void not11() {
        assertSizeEq(1,elt(second(elt(callGameProgressionBeanNotAtAllFamiliesBaseGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))),0)),1));
    }

    @Test
    public void not12() {
        assertSizeEq(1,elt(second(elt(callGameProgressionBeanNotAtAllFamiliesBaseGet(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY)))),0)),1));
    }

    @Test
    public void not13() {
        assertEq(PROG_PK4,elt(elt(second(elt(callGameProgressionBeanNotAtAllFamiliesBaseGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))),0)),1),0));
    }

    @Test
    public void not14() {
        assertEq(PROG_PK4,elt(elt(second(elt(callGameProgressionBeanNotAtAllFamiliesBaseGet(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY)))),0)),1),0));
    }

    @Test
    public void all1() {
        assertSizeEq(3,callGameProgressionBeanFullFamiliesBaseGet(displaying(beanProg(EN, finish(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void all2() {
        assertSizeEq(3,callGameProgressionBeanFullFamiliesBaseGet(displaying(beanProg(EN, finish(progress(),BOY,Sex.BOY)))));
    }

    @Test
    public void all3() {
        assertEq(PROG_PK_TR1,first(elt(callGameProgressionBeanFullFamiliesBaseGet(displaying(beanProg(EN, finish(progress(),GIRL,Sex.GIRL)))),0)));
    }

    @Test
    public void all4() {
        assertEq(PROG_PK_TR1,first(elt(callGameProgressionBeanFullFamiliesBaseGet(displaying(beanProg(EN, finish(progress(),BOY,Sex.BOY)))),0)));
    }

    @Test
    public void all5() {
        assertSizeEq(2,second(elt(callGameProgressionBeanFullFamiliesBaseGet(displaying(beanProg(EN, finish(progress(),GIRL,Sex.GIRL)))),0)));
    }

    @Test
    public void all6() {
        assertSizeEq(2,second(elt(callGameProgressionBeanFullFamiliesBaseGet(displaying(beanProg(EN, finish(progress(),BOY,Sex.BOY)))),0)));
    }

    @Test
    public void all7() {
        assertSizeEq(1,elt(second(elt(callGameProgressionBeanFullFamiliesBaseGet(displaying(beanProg(EN, finish(progress(),GIRL,Sex.GIRL)))),0)),0));
    }

    @Test
    public void all8() {
        assertSizeEq(1,elt(second(elt(callGameProgressionBeanFullFamiliesBaseGet(displaying(beanProg(EN, finish(progress(),BOY,Sex.BOY)))),0)),0));
    }

    @Test
    public void all9() {
        assertEq(PROG_PK1,elt(elt(second(elt(callGameProgressionBeanFullFamiliesBaseGet(displaying(beanProg(EN, finish(progress(),GIRL,Sex.GIRL)))),0)),0),0));
    }

    @Test
    public void all10() {
        assertEq(PROG_PK1,elt(elt(second(elt(callGameProgressionBeanFullFamiliesBaseGet(displaying(beanProg(EN, finish(progress(),BOY,Sex.BOY)))),0)),0),0));
    }
    @Test
    public void all11() {
        assertSizeEq(1,elt(second(elt(callGameProgressionBeanFullFamiliesBaseGet(displaying(beanProg(EN, finish(progress(),GIRL,Sex.GIRL)))),0)),1));
    }

    @Test
    public void all12() {
        assertSizeEq(1,elt(second(elt(callGameProgressionBeanFullFamiliesBaseGet(displaying(beanProg(EN, finish(progress(),BOY,Sex.BOY)))),0)),1));
    }

    @Test
    public void all13() {
        assertEq(PROG_PK2,elt(elt(second(elt(callGameProgressionBeanFullFamiliesBaseGet(displaying(beanProg(EN, finish(progress(),GIRL,Sex.GIRL)))),0)),1),0));
    }

    @Test
    public void all14() {
        assertEq(PROG_PK2,elt(elt(second(elt(callGameProgressionBeanFullFamiliesBaseGet(displaying(beanProg(EN, finish(progress(),BOY,Sex.BOY)))),0)),1),0));
    }

    @Test
    public void unbeat1() {
        assertSizeEq(1,callGameProgressionBeanUnBeatenImportantTrainersGet(displaying(beanProg(EN, trainer(progressTrainers(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void unbeat2() {
        assertSizeEq(1,callGameProgressionBeanUnBeatenImportantTrainersGet(displaying(beanProg(EN, trainer(progressTrainers(),BOY,Sex.BOY)))));
    }

    @Test
    public void unbeat3() {
        assertEq(SECOND_TRAINER,callTrainerPlaceNamesGetTrainer(elt(callGameProgressionBeanUnBeatenImportantTrainersGet(displaying(beanProg(EN, trainer(progressTrainers(),GIRL,Sex.GIRL)))),0)));
    }

    @Test
    public void unbeat4() {
        assertEq(SECOND_TRAINER,callTrainerPlaceNamesGetTrainer(elt(callGameProgressionBeanUnBeatenImportantTrainersGet(displaying(beanProg(EN, trainer(progressTrainers(),BOY,Sex.BOY)))),0)));
    }

    @Test
    public void unbeat5() {
        assertEq(CI_2,callTrainerPlaceNamesGetPlace(elt(callGameProgressionBeanUnBeatenImportantTrainersGet(displaying(beanProg(EN, trainer(progressTrainers(),GIRL,Sex.GIRL)))),0)));
    }

    @Test
    public void unbeat6() {
        assertEq(CI_2,callTrainerPlaceNamesGetPlace(elt(callGameProgressionBeanUnBeatenImportantTrainersGet(displaying(beanProg(EN, trainer(progressTrainers(),BOY,Sex.BOY)))),0)));
    }

    @Test
    public void beat1() {
        assertSizeEq(1,callGameProgressionBeanBeatenImportantTrainersGet(displaying(beanProg(EN, trainer(progressTrainers(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void beat2() {
        assertSizeEq(1,callGameProgressionBeanBeatenImportantTrainersGet(displaying(beanProg(EN, trainer(progressTrainers(),BOY,Sex.BOY)))));
    }

    @Test
    public void beat3() {
        assertEq(FIRST_TRAINER,callTrainerPlaceNamesGetTrainer(elt(callGameProgressionBeanBeatenImportantTrainersGet(displaying(beanProg(EN, trainer(progressTrainers(),GIRL,Sex.GIRL)))),0)));
    }

    @Test
    public void beat4() {
        assertEq(FIRST_TRAINER,callTrainerPlaceNamesGetTrainer(elt(callGameProgressionBeanBeatenImportantTrainersGet(displaying(beanProg(EN, trainer(progressTrainers(),BOY,Sex.BOY)))),0)));
    }

    @Test
    public void beat5() {
        assertEq(CI_1,callTrainerPlaceNamesGetPlace(elt(callGameProgressionBeanBeatenImportantTrainersGet(displaying(beanProg(EN, trainer(progressTrainers(),GIRL,Sex.GIRL)))),0)));
    }

    @Test
    public void beat6() {
        assertEq(CI_1,callTrainerPlaceNamesGetPlace(elt(callGameProgressionBeanBeatenImportantTrainersGet(displaying(beanProg(EN, trainer(progressTrainers(),BOY,Sex.BOY)))),0)));
    }

    @Test
    public void ord1() {
        assertSizeEq(1,callGameProgressionBeanRemainingOtherTrainerPlacesGet(displaying(beanProg(EN, visit(progressOrdTrainers(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void ord2() {
        assertSizeEq(1,callGameProgressionBeanRemainingOtherTrainerPlacesGet(displaying(beanProg(EN, visit(progressOrdTrainers(),BOY,Sex.BOY)))));
    }

    @Test
    public void ord3() {
        assertEq(2,first(elt(callGameProgressionBeanRemainingOtherTrainerPlacesGet(displaying(beanProg(EN, visit(progressOrdTrainers(),GIRL,Sex.GIRL)))),0)));
    }

    @Test
    public void ord4() {
        assertEq(2,first(elt(callGameProgressionBeanRemainingOtherTrainerPlacesGet(displaying(beanProg(EN, visit(progressOrdTrainers(),BOY,Sex.BOY)))),0)));
    }

    @Test
    public void ord5() {
        assertEq(1,second(elt(callGameProgressionBeanRemainingOtherTrainerPlacesGet(displaying(beanProg(EN, visit(progressOrdTrainers(),GIRL,Sex.GIRL)))),0)));
    }

    @Test
    public void ord6() {
        assertEq(1,second(elt(callGameProgressionBeanRemainingOtherTrainerPlacesGet(displaying(beanProg(EN, visit(progressOrdTrainers(),BOY,Sex.BOY)))),0)));
    }

    @Test
    public void pl1() {
        assertEq(RO_1,callGameProgressionBeanGetRemainingOtherTrainersPlaceName(displaying(beanProg(EN, visit(progressOrdTrainers(),GIRL,Sex.GIRL))),0));
    }

    @Test
    public void pl2() {
        assertEq(RO_1,callGameProgressionBeanGetRemainingOtherTrainersPlaceName(displaying(beanProg(EN, visit(progressOrdTrainers(),BOY,Sex.BOY))),0));
    }

    @Test
    public void keyPk1() {
        assertSizeEq(1,callGameProgressionBeanGetKeyPokemon(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL))),0,0));
    }

    @Test
    public void keyPk2() {
        assertSizeEq(1,callGameProgressionBeanGetKeyPokemon(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY))),0,0));
    }

    @Test
    public void keyPk3() {
        assertEq(PROG_PK1,elt(callGameProgressionBeanGetKeyPokemon(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL))),0,0),0));
    }

    @Test
    public void keyPk4() {
        assertEq(PROG_PK1,elt(callGameProgressionBeanGetKeyPokemon(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY))),0,0),0));
    }

    @Test
    public void trPart1() {
        assertEq(PROG_PK_TR1,callGameProgressionBeanGetTrPokemonPartial(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL))),0,0,0));
    }

    @Test
    public void trPart2() {
        assertEq(PROG_PK_TR1,callGameProgressionBeanGetTrPokemonPartial(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY))),0,0,0));
    }

    @Test
    public void imgPart1() {
        assertEq(one(IMG_PR_1),callGameProgressionBeanGetImagePokemonPartial(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL))),0,0,0));
    }

    @Test
    public void imgPart2() {
        assertEq(one(IMG_PR_1),callGameProgressionBeanGetImagePokemonPartial(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY))),0,0,0));
    }

    @Test
    public void trPartNot1() {
        assertEq(PROG_PK_TR2,callGameProgressionBeanGetTrPokemonPartialNot(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL))),0,1,0));
    }

    @Test
    public void trPartNot2() {
        assertEq(PROG_PK_TR2,callGameProgressionBeanGetTrPokemonPartialNot(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY))),0,1,0));
    }

    @Test
    public void imgPartNot1() {
        assertEq(one(IMG_PR_2),callGameProgressionBeanGetImagePokemonPartialNot(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL))),0,1,0));
    }

    @Test
    public void imgPartNot2() {
        assertEq(one(IMG_PR_2),callGameProgressionBeanGetImagePokemonPartialNot(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY))),0,1,0));
    }

    @Test
    public void trNotAll1() {
        assertEq(PROG_PK_TR4,callGameProgressionBeanGetTrPokemonNotAll(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL))),0,1,0));
    }

    @Test
    public void trNotAll2() {
        assertEq(PROG_PK_TR4,callGameProgressionBeanGetTrPokemonNotAll(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY))),0,1,0));
    }

    @Test
    public void imgNotAll1() {
        assertEq(one(IMG_PR_4),callGameProgressionBeanGetImagePokemonNotAll(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL))),0,1,0));
    }

    @Test
    public void imgNotAll2() {
        assertEq(one(IMG_PR_4),callGameProgressionBeanGetImagePokemonNotAll(displaying(beanProg(EN, fac(progress(),BOY,Sex.BOY))),0,1,0));
    }

    @Test
    public void trAll1() {
        assertEq(PROG_PK_TR1,callGameProgressionBeanGetTrPokemonFull(displaying(beanProg(EN, finish(progress(),GIRL,Sex.GIRL))),0,0,0));
    }

    @Test
    public void trAll2() {
        assertEq(PROG_PK_TR1,callGameProgressionBeanGetTrPokemonFull(displaying(beanProg(EN, finish(progress(),BOY,Sex.BOY))),0,0,0));
    }

    @Test
    public void imgAll1() {
        assertEq(one(IMG_PR_1),callGameProgressionBeanGetImagePokemonFull(displaying(beanProg(EN, finish(progress(),GIRL,Sex.GIRL))),0,0,0));
    }

    @Test
    public void imgAll2() {
        assertEq(one(IMG_PR_1),callGameProgressionBeanGetImagePokemonFull(displaying(beanProg(EN, finish(progress(),BOY,Sex.BOY))),0,0,0));
    }

    @Test
    public void nav1() {
        StringMap<TranslationsAppli> builtMessages_ = new StringMap<TranslationsAppli>();
        builtMessages_.addEntry(EN,MessagesInit.en());
        builtMessages_.addEntry(FR,MessagesInit.fr());
        StringMap<String> builtOther_ = CssInit.ms();
        PkProg pk_ = new PkProg();
        NatNavigation nav_ = pk_.nav(new StringList(EN,FR), new ProgGameInit(),PagesInit.buildProg(),builtOther_,builtMessages_);
        nav_.setLanguage(EN);
        pk_.setDataBase(fac(progress(),GIRL,Sex.GIRL));
//        pk_.setBaseEncode(BASE);
        pk_.initializeRendSessionDoc(nav_);
        String firstPage_ = "<html xmlns:c=\"javahtml\"><head><title>Game progression</title><link href=\""+PkScriptPages.REN_ADD_WEB_PROG_CSS_DIFFICULTY_CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>p{\n" +
                "\ttext-indent:25px;\n" +
                "}\n" +
                "body{\n" +
                "\ttext-align:justify;\n" +
                "}\n" +
                "td{\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "th{\n" +
                "\tbackground: yellow;\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "table{\n" +
                "\tborder-spacing:0;\n" +
                "}\n" +
                "h1{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "h2{\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "span.errormessage{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "</style></head><body>nickname: G<img src=\"AAABAADO\"/><br/><a c:command=\"progressing.n\" href=\"\" n-a=\"0\">Pokemon families without caught pokemon</a><br/><a c:command=\"progressing.p\" href=\"\" n-a=\"1\">Pokemon families with caught pokemon and uncaught pokemon</a><br/><a c:command=\"progressing.a\" href=\"\" n-a=\"2\">Pokemon families without uncaught pokemon</a><br/>Not beaten important trainers<ul/><br/>Beaten important trainers<ul/><br/><table><caption>Remaining trainers to be beaten</caption><thead><tr><th>Place where to find</th><th>Number of remaining trainers at this place</th></tr></thead><tbody/></table><br/>Not visited places<ul/><br/>Visited places<ul/><br/>number remaining pokemon to set to the maximum level: 0<br/>number remaining pokemon to set to the maximum happiness: 0<br/>number remaining eggs: 0<br/>remaining steps repel: 0<br/>money: 0<br/></body></html>";
        assertEq(firstPage_,nav_.getHtmlText());

        goToPage(pk_, nav_, 0);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Pokemon families without caught pokemon</title><link href=\""+PkScriptPages.REN_ADD_WEB_PROG_CSS_DIFFICULTY_CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>p{\n" +
                "\ttext-indent:25px;\n" +
                "}\n" +
                "body{\n" +
                "\ttext-align:justify;\n" +
                "}\n" +
                "td{\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "th{\n" +
                "\tbackground: yellow;\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "table{\n" +
                "\tborder-spacing:0;\n" +
                "}\n" +
                "h1{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "h2{\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "span.errormessage{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "</style></head><body><a c:command=\"progressing.g\" href=\"\" n-a=\"0\">Return to the summary</a><br/>P_3<br/><table><tbody><tr><td>remaining pokemon to be caught among the current family</td><td><img src=\"AAABAAD3\"/>P_3<br/></td><td><img src=\"AAABAAD4\"/>P_4<br/></td></tr></tbody></table><br/>P_5<br/><table><tbody><tr><td>remaining pokemon to be caught among the current family</td><td><img src=\"AAABAAD5\"/>P_5<br/></td><td><img src=\"AAABAAD6\"/>P_6<br/></td></tr></tbody></table><br/></body></html>",nav_.getHtmlText());
        goToPage(pk_, nav_, 0);
        goToPage(pk_, nav_, 1);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Pokemon families with caught pokemon and uncaught pokemon</title><link href=\""+PkScriptPages.REN_ADD_WEB_PROG_CSS_DIFFICULTY_CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>p{\n" +
                "\ttext-indent:25px;\n" +
                "}\n" +
                "body{\n" +
                "\ttext-align:justify;\n" +
                "}\n" +
                "td{\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "th{\n" +
                "\tbackground: yellow;\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "table{\n" +
                "\tborder-spacing:0;\n" +
                "}\n" +
                "h1{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "h2{\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "span.errormessage{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "</style></head><body><a c:command=\"progressing.g\" href=\"\" n-a=\"0\">Return to the summary</a><br/>P_1<br/><table><tbody><tr><td>remaining pokemon to be caught among the current family</td><td/><td><img src=\"AAABAAD2\"/>P_2<br/></td></tr><tr><td>caught pokemon among the current family</td><td><img src=\"AAABAAD1\"/>P_1<br/></td><td/></tr></tbody></table><br/></body></html>",nav_.getHtmlText());
        goToPage(pk_, nav_, 0);
        goToPage(pk_, nav_, 2);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Pokemon families without uncaught pokemon</title><link href=\""+PkScriptPages.REN_ADD_WEB_PROG_CSS_DIFFICULTY_CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>p{\n" +
                "\ttext-indent:25px;\n" +
                "}\n" +
                "body{\n" +
                "\ttext-align:justify;\n" +
                "}\n" +
                "td{\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "th{\n" +
                "\tbackground: yellow;\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "table{\n" +
                "\tborder-spacing:0;\n" +
                "}\n" +
                "h1{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "h2{\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "span.errormessage{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "</style></head><body><a c:command=\"progressing.g\" href=\"\" n-a=\"0\">Return to the summary</a><br/></body></html>",nav_.getHtmlText());
        goToPage(pk_, nav_, 0);
        assertEq(firstPage_,nav_.getHtmlText());
    }

    @Test
    public void nav2() {
        StringMap<TranslationsAppli> builtMessages_ = new StringMap<TranslationsAppli>();
        builtMessages_.addEntry(EN,MessagesInit.en());
        builtMessages_.addEntry(FR,MessagesInit.fr());
        StringMap<String> builtOther_ = CssInit.ms();
        PkProg pk_ = new PkProg();
        NatNavigation nav_ = pk_.nav(new StringList(EN,FR), new ProgGameInit(),PagesInit.buildProg(),builtOther_,builtMessages_);
        nav_.setLanguage(EN);
        pk_.setDataBase(fac(progress(),BOY,Sex.BOY));
//        pk_.setBaseEncode(BASE);
        pk_.initializeRendSessionDoc(nav_);
        String firstPage_ = "<html xmlns:c=\"javahtml\"><head><title>Game progression</title><link href=\""+PkScriptPages.REN_ADD_WEB_PROG_CSS_DIFFICULTY_CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>p{\n" +
                "\ttext-indent:25px;\n" +
                "}\n" +
                "body{\n" +
                "\ttext-align:justify;\n" +
                "}\n" +
                "td{\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "th{\n" +
                "\tbackground: yellow;\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "table{\n" +
                "\tborder-spacing:0;\n" +
                "}\n" +
                "h1{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "h2{\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "span.errormessage{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "</style></head><body>nickname: B<img src=\"AAABAAEO\"/><br/><a c:command=\"progressing.n\" href=\"\" n-a=\"0\">Pokemon families without caught pokemon</a><br/><a c:command=\"progressing.p\" href=\"\" n-a=\"1\">Pokemon families with caught pokemon and uncaught pokemon</a><br/><a c:command=\"progressing.a\" href=\"\" n-a=\"2\">Pokemon families without uncaught pokemon</a><br/>Not beaten important trainers<ul/><br/>Beaten important trainers<ul/><br/><table><caption>Remaining trainers to be beaten</caption><thead><tr><th>Place where to find</th><th>Number of remaining trainers at this place</th></tr></thead><tbody/></table><br/>Not visited places<ul/><br/>Visited places<ul/><br/>number remaining pokemon to set to the maximum level: 0<br/>number remaining pokemon to set to the maximum happiness: 0<br/>number remaining eggs: 0<br/>remaining steps repel: 0<br/>money: 0<br/></body></html>";
        assertEq(firstPage_,nav_.getHtmlText());

        goToPage(pk_, nav_, 0);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Pokemon families without caught pokemon</title><link href=\""+PkScriptPages.REN_ADD_WEB_PROG_CSS_DIFFICULTY_CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>p{\n" +
                "\ttext-indent:25px;\n" +
                "}\n" +
                "body{\n" +
                "\ttext-align:justify;\n" +
                "}\n" +
                "td{\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "th{\n" +
                "\tbackground: yellow;\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "table{\n" +
                "\tborder-spacing:0;\n" +
                "}\n" +
                "h1{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "h2{\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "span.errormessage{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "</style></head><body><a c:command=\"progressing.g\" href=\"\" n-a=\"0\">Return to the summary</a><br/>P_3<br/><table><tbody><tr><td>remaining pokemon to be caught among the current family</td><td><img src=\"AAABAAD3\"/>P_3<br/></td><td><img src=\"AAABAAD4\"/>P_4<br/></td></tr></tbody></table><br/>P_5<br/><table><tbody><tr><td>remaining pokemon to be caught among the current family</td><td><img src=\"AAABAAD5\"/>P_5<br/></td><td><img src=\"AAABAAD6\"/>P_6<br/></td></tr></tbody></table><br/></body></html>",nav_.getHtmlText());
        goToPage(pk_, nav_, 0);
        goToPage(pk_, nav_, 1);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Pokemon families with caught pokemon and uncaught pokemon</title><link href=\""+PkScriptPages.REN_ADD_WEB_PROG_CSS_DIFFICULTY_CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>p{\n" +
                "\ttext-indent:25px;\n" +
                "}\n" +
                "body{\n" +
                "\ttext-align:justify;\n" +
                "}\n" +
                "td{\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "th{\n" +
                "\tbackground: yellow;\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "table{\n" +
                "\tborder-spacing:0;\n" +
                "}\n" +
                "h1{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "h2{\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "span.errormessage{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "</style></head><body><a c:command=\"progressing.g\" href=\"\" n-a=\"0\">Return to the summary</a><br/>P_1<br/><table><tbody><tr><td>remaining pokemon to be caught among the current family</td><td/><td><img src=\"AAABAAD2\"/>P_2<br/></td></tr><tr><td>caught pokemon among the current family</td><td><img src=\"AAABAAD1\"/>P_1<br/></td><td/></tr></tbody></table><br/></body></html>",nav_.getHtmlText());
        goToPage(pk_, nav_, 0);
        goToPage(pk_, nav_, 2);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Pokemon families without uncaught pokemon</title><link href=\""+PkScriptPages.REN_ADD_WEB_PROG_CSS_DIFFICULTY_CSS+"\" rel=\"stylesheet\" type=\"text/css\"/><style>p{\n" +
                "\ttext-indent:25px;\n" +
                "}\n" +
                "body{\n" +
                "\ttext-align:justify;\n" +
                "}\n" +
                "td{\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "th{\n" +
                "\tbackground: yellow;\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "table{\n" +
                "\tborder-spacing:0;\n" +
                "}\n" +
                "h1{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "h2{\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "span.errormessage{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "</style></head><body><a c:command=\"progressing.g\" href=\"\" n-a=\"0\">Return to the summary</a><br/></body></html>",nav_.getHtmlText());
        goToPage(pk_, nav_, 0);
        assertEq(firstPage_,nav_.getHtmlText());
    }

    private void goToPage(PkProg _pk, NatNavigation _nav, int _nb) {
        _pk.getNatPage().setUrl(_nb);
        _pk.execute(false, _nav);
    }


    private FacadeGame trainer(DataBase _init, String _nickname,Sex _s) {
        FacadeGame facadeGame_ = visit(_init, _nickname, _s);
        facadeGame_.getGame().beatGymLeader(newCoords(0,0,0,0,0,0));
        return facadeGame_;
    }

    private FacadeGame visit(DataBase _init, String _nickname,Sex _s) {
        FacadeGame facadeGame_ = common(_init);
        Game game_ = new Game(_init);
        game_.initUserInteract(_nickname, _s, game_.getDifficulty(), _init);
        game_.setPlayerOrientation(Direction.UP);
        facadeGame_.setGame(game_);
        return facadeGame_;
    }
    private FacadeGame incomplete(DataBase _init, String _nickname,Sex _s) {
        _init.addConstNumTest(DataBase.NIVEAU_PK_MAX, Rate.newRate("10"));
        _init.addConstNumTest(DataBase.MAX_BONHEUR, Rate.newRate("128"));
        FacadeGame fac_ = fac(_init, _nickname, _s);
        fac_.getGame().getPlayer().setRemainingRepelSteps(15);
        fac_.getGame().getPlayer().setMoney(LgInt.newLgInt(MONEY_REM));
        fac_.getGame().getPlayer().recupererOeufPensions(Egg.newEgg(PROG_PK1));
        return fac_;
    }
    private FacadeGame finish(DataBase _init, String _nickname,Sex _s) {
        FacadeGame fac_ = fac(_init, _nickname, _s);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK1, BoolVal.TRUE);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK2, BoolVal.TRUE);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK3, BoolVal.TRUE);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK4, BoolVal.TRUE);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK5, BoolVal.TRUE);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK6, BoolVal.TRUE);
        PokemonPlayer pk_ = fac_.getGame().getPlayer().getPokemonPlayerList().getValue(0);
        pk_.setLevel( fac_.getData().getMaxLevel());
        pk_.setHappiness( fac_.getData().getHappinessMax());
        return fac_;
    }
    private FacadeGame fac(DataBase _init, String _nickname,Sex _s) {
        FacadeGame facadeGame_ = common(_init);
        Game game_ = new Game(_init);
        game_.initUtilisateur(_nickname, _s, game_.getDifficulty(), _init);
        game_.setPlayerOrientation(Direction.UP);
        facadeGame_.setGame(game_);
        return facadeGame_;
    }

    private FacadeGame common(DataBase _init) {
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(_init);
        StringMap<String> displayLanguages_ = new StringMap<String>();
        displayLanguages_.put(EN,EN);
        facadeGame_.setLanguages(indexes());
        facadeGame_.setDisplayLanguages(displayLanguages_);
        facadeGame_.setLanguage(EN);
        facadeGame_.setZipName("zip");
        facadeGame_.setLanguages(indexes());
        _init.setMessages(facadeGame_.getData());
        facadeGame_.setLoadedData(true);
        return facadeGame_;
    }
}
