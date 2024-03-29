package code.scripts.messages.aiki;
import code.util.*;
public final class MessPkGr{
private static final String SEP="=";
private static final String M_ISSUE_AFTER_FIGHT="issue_after_fight";
private static final String M_ISSUE_RANDOM="issue_random";
private static final String M_ISSUE_RULES_LEARN="issue_rules_learn";
private static final String M_ISSUE_RULES_MOVES="issue_rules_moves";
private static final String M_ISSUE_RULES_SWITCH="issue_rules_switch";
private static final String M_ISSUE_SENDING="issue_sending";
private static final String M_ISSUE_TOO_HARD="issue_too_hard";
private static final String M_ISSUE_USING="issue_using";
private static final String C_63="Issue of moves choice\n";
private static final String C_64="Issue of learning moves choice or evolving\n";
private static final String C_65="Issue of switching\n";
private static final String C_66="Issue of random editing\n";
private static final String C_67="Issue of sending\n";
private static final String C_68="Issue of using a move for a pokemon player\n";
private static final String C_69="Issue of hard simulation\n";
private static final String C_70="Bad choice of evolutions after the fight\n";
private static final String C_159="Probl&egrave;me de choix d''attaques\n";
private static final String C_160="Probl&egrave;me de choix d''apprentissage d''attaques ou d''&eacute;volutions\n";
private static final String C_161="Probl&egrave;me de remplacement\n";
private static final String C_162="Probl&egrave;me de tirage al&eacute;atoire\n";
private static final String C_163="Probl&egrave;me d''envoi\n";
private static final String C_164="Probl&egrave;me d''utilisation d''une attaque pour un pokemon de joueur\n";
private static final String C_165="Probl&egrave;me de simulation compliqu&eacute;e\n";
private static final String C_166="Mauvais choix d''&eacute;volutions apr&egrave;s le combat\n";
private MessPkGr(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_pk/gui/messages/en/aiki/game/fight/enums/actiontype.properties",enAikiGameFightEnumsActiontype());
m.addEntry("resources_pk/gui/messages/fr/aiki/game/fight/enums/actiontype.properties",frAikiGameFightEnumsActiontype());
m.addEntry("resources_pk/gui/messages/en/aiki/game/fight/fight.properties",enAikiGameFightFight());
m.addEntry("resources_pk/gui/messages/fr/aiki/game/fight/fight.properties",frAikiGameFightFight());
m.addEntry("resources_pk/gui/messages/en/aiki/game/fight/fighter.properties",enAikiGameFightFighter());
m.addEntry("resources_pk/gui/messages/fr/aiki/game/fight/fighter.properties",frAikiGameFightFighter());
m.addEntry("resources_pk/gui/messages/en/aiki/game/fight/team.properties",enAikiGameFightTeam());
m.addEntry("resources_pk/gui/messages/fr/aiki/game/fight/team.properties",frAikiGameFightTeam());
m.addEntry("resources_pk/gui/messages/en/aiki/game/game.properties",enAikiGameGame());
m.addEntry("resources_pk/gui/messages/fr/aiki/game/game.properties",frAikiGameGame());
m.addEntry("resources_pk/gui/messages/en/aiki/game/player/player.properties",enAikiGamePlayerPlayer());
m.addEntry("resources_pk/gui/messages/fr/aiki/game/player/player.properties",frAikiGamePlayerPlayer());
m.addEntry("resources_pk/gui/messages/en/aiki/map/pokemon/pokemonplayer.properties",enAikiMapPokemonPokemonplayer());
m.addEntry("resources_pk/gui/messages/fr/aiki/map/pokemon/pokemonplayer.properties",frAikiMapPokemonPokemonplayer());
return m;
}
static String enAikiGameFightEnumsActiontype(){
String f="NOTHING=Nothing\n";
f+="MOVE=Use a move\n";
f+="HEALING=Heal\n";
f+="SWITCH=Substitute\n";
return f;
}
static String frAikiGameFightEnumsActiontype(){
String f="NOTHING=Rien\n";
f+="MOVE=Attaquer\n";
f+="HEALING=Soigner\n";
f+="SWITCH=Remplacer\n";
return f;
}
static String enAikiGameFightFight(){
String f="errSubstitute={0} cannot be substitute twice.\n";
f+="errBackMove={0} cannot use a move because {0} is a back fighter.\n";
f+="errNoChosenTarget=No target was chosen for the move {0} used by {1}.\n";
f+="errTooFarTarget=The chosen target for the move {0} used by {1} is too far.\n";
f+="errBadChoice=The target was badly chosen for the move {0} used by {1}.\n";
f+="errUnusableMove={0} cannot use the move {1}.\n";
f+="errKoSubstitute={0} is ko, so {0} cannot be sent.\n";
f+="errBackSwitch={0} cannot be substituted because {0} is already at back of fight.\n";
f+="errSwitch={0} must have a single substitute.\n";
f+="errFrontSwitch={0} cannot be substitute because {0} is already at front of fight.\n";
f+="errBelongSwitch={0} cannot be substitute because {0} does not belong to you.\n";
f+="errNoItem=For healing {0}, an healing item must be used. \n";
f+="errTooManyItems=The item {0} is used more time than the number of {0} in the inventory.\n";
f+="errNoEffect=The item {0} has no effect on {1}.\n";
f+="errNoHealedMove=For using the item {0} on {1}, one single move must be selected.\n";
f+="errTooFewActions=All your not ko pokemon must act.\n";
f+="errTooManyActions=Only {0} actions must be chosen, {1} actions are currently chosen.\n";
f+="errSubstituteKoEndRound={0} is ko, so {0} cannot stay at front of fight.\n";
f+="errSubstituteBelong=The place does not belong to you.\n";
f+="errSubstituteNoSwitchPlace=You cannot switch a pokemon at front with an other pokemon at front.\n";
f+="errSubstituteUsedPlace=Each place belonging to you must be used once only.\n";
f+="errSubstitutePlace=The place {0} is used at least twice.\n";
f+=M_ISSUE_RULES_MOVES+SEP+C_63;
f+=M_ISSUE_RULES_LEARN+SEP+C_64;
f+=M_ISSUE_RULES_SWITCH+SEP+C_65;
f+=M_ISSUE_RANDOM+SEP+C_66;
f+=M_ISSUE_SENDING+SEP+C_67;
f+=M_ISSUE_USING+SEP+C_68;
f+=M_ISSUE_TOO_HARD+SEP+C_69;
f+=M_ISSUE_AFTER_FIGHT+SEP+C_70;
f+="sendSubstituteFoe=The foe trainer is going to send {0}.\n";
f+="sendSubstitute=Your ally is going to send {0}.\n";
f+="errEvolving={0} must have between {1} and {2} moves, but {0} has {3} moves.\n";
f+="errEvolvingAb=The evolution of {0} must have an ability.\n";
f+="fighterFoe=foe {0}\n";
f+="fighterAlly=ally {0}\n";
f+="winHp={0} has {1} health points restored.\n";
f+="looseHp={0} lost {1} health points.\n";
f+="noVarHp={0} does not suffered by variation of health points.\n";
f+="koFighter={0} falls ko.\n";
f+="varStatistic=The statistic {0} of {1} changed by {2} levels.\n";
f+="affStatus={0} is affected by the status {1}.\n";
f+="affStatusRel={0} is affected by the status {1} with {2}.\n";
f+="disabledStatus=The status {0} of {1} is disabled.\n";
f+="disabledStatusRel=The status {0} of {1} with {2} is disabled.\n";
f+="disabledStatusRelOther=The status {0} of {1} with any fighter is disabled.\n";
f+="incrStatus=The number of rounds of the status {0} of {1} est increased by 1.\n";
f+="incrStatusRel=The number of rounds of the status {0} of {1} with {2} est increased by 1.\n";
f+="cannotUseMoveStatus={0} cannot use any move, because of the status {1}.\n";
f+="cannotUseMoveStatusRel={0} cannot use any move, because of the status {1} by {2}.\n";
f+="cloneDamage=The clone of {0} lost {1} health points.\n";
f+="cloneZero=The clone of {0} is destroyed.\n";
f+="changedAbility=The ability of {0} became {1}.\n";
f+="changedAbilityDisabled=The ability of {0} became disabled.\n";
f+="enabledAbility=The ability of {0} is enabled by changement.\n";
f+="switchItems={0} hold the item {2} instead of the item {1}.\n";
f+="looseItem={0} lost the item {1}.\n";
f+="winItem={0} take the item {1}.\n";
f+="disableItem={0} cannot use anymore an item.\n";
f+="reEnableItem={0} can an item again.\n";
f+="changedTypes=The types of {0} became {1}.\n";
f+="moveTypes=The types of the move {2}, used by {0}, are {1}.\n";
f+="privateMoves={0} cannot use anymore the move {1}.\n";
f+="noPrivateMoves={0} can use the move {1} again.\n";
f+="disabledMove={0} is no longer under the effect of the move {1}.\n";
f+="enabledMove={0} is under the effect of the move {1}.\n";
f+="disabledMoveRel={0} is no longer under the effect of the move {1} with {2}.\n";
f+="enabledMoveRel={0} is under the effect of the move {1} with {2}.\n";
f+="commonStatistic=The common value of the base of statistic {0} of {1} and {2} is now {3}.\n";
f+="learnMoveRound={0} learnt the move {1}.\n";
f+="learnMoveRoundDef={0} definitively learnt the move {1}.\n";
f+="copyFighter={0} became {1}.\n";
f+="switchPlaces={0} and {1} switch their places.\n";
f+="enabledTeamMove=Your team is under effect of the move {0}.\n";
f+="disabledTeamMove=Your team is no longer under effect of the move {0}.\n";
f+="enabledFoeTeamMove=The foe team is under effect of the move {0}.\n";
f+="disabledFoeTeamMove=The foe team is no longer under effect of the move {0}.\n";
f+="incrTeamUsesMove=The number of uses of the move {0} by your team is increased by one.\n";
f+="disabledTeamUsesMove=The number of uses of the move {0} by your team falls to zero.\n";
f+="incrFoeTeamUsesMove=The number of uses of the move {0} by the foe team is increased by one.\n";
f+="disabledFoeTeamUsesMove=The number of uses of the move {0} by the foe team falls to zero.\n";
f+="batonPass={0} forwards its strenght and weakness to {1}.\n";
f+="varPpEffect=The power points the move {0} of {1} vary by {2}.\n";
f+="enabledWeather=The weather {0} is enabled.\n";
f+="disabledWeather=The weather {0} is disabled.\n";
f+="weatherIncr=The number of rounds of the weather {0} is increased by one.\n";
f+="globalMoveEndRound=The weather {0} is acting.\n";
f+="moveEndRound=The move {0} is acting on {1}.\n";
f+="moveEndRoundRel=The move {0} is acting on {1} by {2}.\n";
f+="comboMoveEndRound=The moves {0} used by your team are acting.\n";
f+="comboMoveEndRoundFoe=The moves {0} used by the foe team are acting.\n";
f+="abilityEndRound=The ability {0} is acting on {1}.\n";
f+="itemEndRound=The item {0} is acting on {1}.\n";
f+="statusEndRound=The status {0} is acting on {1}.\n";
f+="statusRelEndRound=The status {0} is acting on {1} by {2}.\n";
f+="firstMove={0} use the move {1}.\n";
f+="invokeMove={0} invoke the move {1}.\n";
f+="invokeMoveFail=The invokation of the move {0} failed.\n";
f+="withdraw={0} is withdrawen.\n";
f+="send={0} is sent.\n";
f+="prepaRound={0} is preparing the move {1}.\n";
f+="rechargeRound={0} is recharge while this round, so {0} cannot use a move while this round.\n";
f+="disappeared={0} is disappeared.\n";
f+="criticalHit=Critical hit!\n";
f+="nbHits={0} hits are inflicted against {1}.\n";
f+="failMove=The move {0} failed against {1}.\n";
f+="noAchieveTarget=The move {0} used by {1} do not achieve any target.\n";
f+="successfulMoveButNoDamage=The move {0} is successful against {1} but does not inflict damage against {1}.\n";
f+="successfulMove=The move {0} is successful against {1}.\n";
f+="learnMoveEvolution={0} learnt the move {1}.\n";
f+="forgetMoveEvolution={0} forgot the move {1}.\n";
f+="keepMoveEvolution={0} kept the move {1}.\n";
f+="fightEvolution={0} evolved into {1}.\n";
f+="statusBeginRound={0} cannot use a move because {0} is under the effect of the status {1}.\n";
f+="statusBeginRoundRel={0} cannot use a move against {2} because {0} is under the effect of the status {1} by {2}.\n";
f+="changingViewPointUser={0} became the user.\n";
f+="changingViewPointTarget={0} became the target.\n";
f+="protectTypeByGlobalMove={0} is protected against the type {1} by the global move {2}.\n";
f+="protectTypeByIndividualMove={0} is protected against the type {1} by some moves.\n";
f+="protectTypeByAbilityWeather={0} is protected against the type {1} by the ability {2} enabled by the global move {3}.\n";
f+="protectTypeByAbility={0} is protected against the type {1} by the ability {2}.\n";
f+="protectTypeByItem={0} is protected against the type {1} by the item {2}.\n";
f+="protectByAllyAbility={0} is protected against the move {1} by the ability {2} of an ally of {0}.\n";
f+="protectByAbilityDamageAlly=The ally {0} is protected against the damaging move {1} by the ability {2}.\n";
f+="protectByAbility={0} is protected against the move {1} by the ability {2}.\n";
f+="protectByAbilityDamage={0} is protected against the damaging move {1} by the ability {2}.\n";
f+="protectByItem={0} is protected against the move {1} by the item {2}.\n";
f+="protectedAgainstSecEff={0} is protected against the second effects of the move {1} by the ability {2}.\n";
f+="protectedByIndividualMove={0} is protected against the move {1} by the individual move {2}.\n";
f+="protectedByTeamMove={0} is protected against the move {1} by the team move {2}.\n";
f+="protectedByDisappearing={0} is untouchable.\n";
f+="immuLowStatAbilityAlly={0} is protected against lowering the statistic {1} by the ability {2} of an ally.\n";
f+="immuLowStatTeam={0} is protected against lowering the statistic {1} by the team move {2}.\n";
f+="immuLowStatAbility={0} is protected against lowering the statistic {1} by the ability {2}.\n";
f+="immuLowStatStAbility={0} is protected against lowering the statistic {1} by the ability {2} with the status {3}.\n";
f+="immuLowStatItem={0} is protected against lowering the statistic {1} by the item {2}.\n";
f+="immuChgtStatMin={0} achieved the minimum of the statistic {1}.\n";
f+="immuChgtStatMax={0} achieved the maximum of the statistic {1}.\n";
f+="immuStatGlobalMove={0} is protected against the status {1} by a global move.\n";
f+="immuStatAbilityAlly={0} is protected against the status {1} by the ability {2} of an ally.\n";
f+="immuStatTeam={0} is protected against the status {1} by the team move {2}.\n";
f+="immuStatGlobalMoveAbility={0} is protected against the status {1} by the ability {2} enabled by the global move {3}.\n";
f+="immuStatAbility={0} is protected against the status {1} by the ability {2}.\n";
f+="immuStatItem={0} is protected against the status {1} by the item {2}.\n";
f+="createClone={0} creates itself a clone with {1} health points.\n";
f+="helpAlly={0} helps {1}.\n";
return f;
}
static String frAikiGameFightFight(){
String f="errSubstitute={0} ne peut pas &ecirc;tre rempla&ccedil;ant deux fois.\n";
f+="errBackMove={0} ne peut pas lancer une attaque car {0} est un combattant &agrave; l''arri&egrave;.\n";
f+="errNoChosenTarget=Aucune cible n''est choisie pour l''attaque {0} lanc&eacute;e par {1}.\n";
f+="errTooFarTarget=La cible choisie pour l''attaque {0} lanc&eacute;e par {1} est trop loin.\n";
f+="errBadChoice=La cible a mal &eacute;t&eacute; choisie pour l''attaque {0} lanc&eacute;e par {1}.\n";
f+="errUnusableMove={0} ne peut pas lancer l''attaque {1}.\n";
f+="errKoSubstitute={0} est ko, donc {0} ne peut pas &ecirc;tre envoy&eacute;.\n";
f+="errBackSwitch={0} ne peut pas &ecirc;tre remplac&eacute; car {0} est d&eacute;j&agrave; &agrave; l''arri&egrave;.\n";
f+="errSwitch={0} doit avoir un seul rempla&ccedil;ant.\n";
f+="errFrontSwitch={0} ne peut pas &ecirc;tre rempla&ccedil;ant car {0} est d&eacute;j&agrave; au front.\n";
f+="errBelongSwitch={0} ne peut pas &ecirc;tre rempla&ccedil;ant car {0} ne vous appartient pas.\n";
f+="errNoItem=Pour soigner {0}, il faut utiliser un objet de soin. \n";
f+="errTooManyItems=L''objet {0} est plus de fois utilis&eacute; que la quantit&eacute; de {0} dans l''inventaire.\n";
f+="errNoEffect=L''objet {0} est sans effet sur {1}.\n";
f+="errNoHealedMove=Pour utiliser l''objet {0} sur {1}, il faut qu''une seule attaque soit s&eacute;lectionn&eacute;e.\n";
f+="errTooFewActions=Tous vos pokemon non ko doivent agir.\n";
f+="errTooManyActions=Seules {0} actions doivent &ecirc;tre choisies, {1} actions sont actuellement choisies.\n";
f+="errSubstituteKoEndRound={0} est ko, donc {0} ne peut pas rester au front.\n";
f+="errSubstituteBelong=La place ne vous appartient pas.\n";
f+="errSubstituteNoSwitchPlace=Vous ne pouvez pas &eaute;changer un pokemon de front avec un autre pokemon de front.\n";
f+="errSubstituteUsedPlace=Chaque place vous appartenant doit &ecirc;tre utilis&eacute;e une fois seulement.\n";
f+="errSubstitutePlace=La place num&eacute;ro {0} est utilis&eacute;e au moins deux fois.\n";
f+=M_ISSUE_RULES_MOVES+SEP+C_159;
f+=M_ISSUE_RULES_LEARN+SEP+C_160;
f+=M_ISSUE_RULES_SWITCH+SEP+C_161;
f+=M_ISSUE_RANDOM+SEP+C_162;
f+=M_ISSUE_SENDING+SEP+C_163;
f+=M_ISSUE_USING+SEP+C_164;
f+=M_ISSUE_TOO_HARD+SEP+C_165;
f+=M_ISSUE_AFTER_FIGHT+SEP+C_166;
f+="sendSubstituteFoe=Le dresseur ennemi va envoyer {0}.\n";
f+="sendSubstitute=Votre alli&eacute; va envoyer {0}.\n";
f+="errEvolving={0} doit avoir entre {1} et {2} attaques, mais {0} a {3} attaques.\n";
f+="errEvolvingAb=L''&eacute;volution de {0} doit avoir une capacit&eacute;.\n";
f+="fighterFoe={0} ennemi\n";
f+="fighterAlly={0} alli&eacute;\n";
f+="winHp={0} a {1} points de vie restor&eacute;s.\n";
f+="looseHp={0} perd {1} points de vie.\n";
f+="noVarHp={0} ne subit pas de variation de points de vie.\n";
f+="koFighter={0} tombe ko.\n";
f+="varStatistic=La statistique {0} de {1} varie de {2} crans.\n";
f+="affStatus={0} prend le statut {1}.\n";
f+="affStatusRel={0} prend le statut {1} avec {2}.\n";
f+="disabledStatus=Le statut {0} de {1} est d&eacute;sactiv&eacute;.\n";
f+="disabledStatusRel=Le statut {0} de {1} avec {2} est d&eacute;sactiv&eacute;.\n";
f+="disabledStatusRelOther=Le statut {0} de {1} avec n''importe quel combattant est d&eacute;sactiv&eacute;.\n";
f+="incrStatus=Le nombre de tours du statut {0} de {1} est incr&eacute;ment&eacute; de 1.\n";
f+="incrStatusRel=Le nombre de tours du statut {0} de {1} avec {2} est incr&eacute;ment&eacute; de 1.\n";
f+="cannotUseMoveStatus={0} ne peut pas attaquer, &agrave; cause du statut {1}.\n";
f+="cannotUseMoveStatusRel={0} ne peut pas attaquer, &agrave; cause du statut {1} avec {2}.\n";
f+="cloneDamage=Le clone de {0} perd {1} points de vie.\n";
f+="cloneZero=Le clone de {0} est d&eacute;truit.\n";
f+="changedAbility=La capacit&eacute; de {0} devient {1}.\n";
f+="changedAbilityDisabled=La capacit&eacute; de {0} devient inactive.\n";
f+="enabledAbility=La capacit&eacute; de {0} est activ&eacute; par changement.\n";
f+="switchItems={0} tient &agrave; la place de l''objet {1} l''objet {2}.\n";
f+="looseItem={0} perd l''objet {1}.\n";
f+="winItem={0} prend l''objet {1}.\n";
f+="disableItem={0} ne peut pas utiliser un objet.\n";
f+="reEnableItem={0} peut &agrave; nouveau utiliser un objet.\n";
f+="changedTypes=Les types de {0} deviennent {1}.\n";
f+="moveTypes=Les types de l''attaque {2}, utilis&eacute;e par {0}, sont {1}.\n";
f+="privateMoves={0} ne peut plus utiliser l''attaque {1}.\n";
f+="noPrivateMoves={0} peut &agrave; nouveau utiliser l''attaque {1}.\n";
f+="disabledMove={0} n''est plus sous l''effet de l''attaque {1}.\n";
f+="enabledMove={0} est sous l''effet de l''attaque {1}.\n";
f+="disabledMoveRel={0} n''est plus sous l''effet de l''attaque {1} avec {2}.\n";
f+="enabledMoveRel={0} est sous l''effet de l''attaque {1} avec {2}.\n";
f+="commonStatistic=La valeur commune de la base de statistique {0} de {1} et {2} est maintenant de {3}.\n";
f+="learnMoveRound={0} apprend l''attaque {1}.\n";
f+="learnMoveRoundDef={0} a d&eacute;finitivement appris l''attaque {1}.\n";
f+="copyFighter={0} devient {1}.\n";
f+="switchPlaces={0} et {1} &eacute;changent leurs places.\n";
f+="enabledTeamMove=Votre &eacute;quipe est sous effet de l''attaque {0}.\n";
f+="disabledTeamMove=Votre &eacute;quipe n''est plus sous effet de l''attaque {0}.\n";
f+="enabledFoeTeamMove=L''&eacute;quipe ennemie est sous effet de l''attaque {0}.\n";
f+="disabledFoeTeamMove=L''&eacute;quipe ennemie n''est plus sous effet de l''attaque {0}.\n";
f+="incrTeamUsesMove=Le numbre d''utilisations de l''attaque {0} par votre &eacute;quipe est augment&eacute; de un.\n";
f+="disabledTeamUsesMove=Le numbre d''utilisations de l''attaque {0} par votre &eacute;quipe tombe &agrave; zero.\n";
f+="incrFoeTeamUsesMove=Le numbre d''utilisations de l''attaque {0} par l''&eacute;quipe ennemie est augment&eacute; de un.\n";
f+="disabledFoeTeamUsesMove=Le numbre d''utilisations de l''attaque {0} par l''&eacute;quipe ennemie tombe &agrave; zero.\n";
f+="batonPass={0} transf&egrave;re sa force et sa faiblesse &agrave; {1}.\n";
f+="varPpEffect=Les points de pouvoir de l''attaque {0} de {1} varient de {2}.\n";
f+="enabledWeather=Le climat {0} est activ&eacute;.\n";
f+="disabledWeather=Le climat {0} est d&eacute;sactiv&eacute;.\n";
f+="weatherIncr=Le nombre de tours du climat {0} est augment&eacute; de un.\n";
f+="globalMoveEndRound=Le climat {0} agit.\n";
f+="moveEndRound=L''attaque {0} agit sur {1}.\n";
f+="moveEndRoundRel=L''attaque {0} agit sur {1} par {2}.\n";
f+="comboMoveEndRound=Les attaques {0} utilis&eacute;es par votre &eacute;quipe agissent.\n";
f+="comboMoveEndRoundFoe=Les attaques {0} utilis&eacute;es par l''&eacute;quipe ennemie agissent.\n";
f+="abilityEndRound=La capacit&eacute; {0} agit sur {1}.\n";
f+="itemEndRound=L''objet {0} agit sur {1}.\n";
f+="statusEndRound=Le statut {0} agit sur {1}.\n";
f+="statusRelEndRound=Le statut {0} agit sur {1} par {2}.\n";
f+="firstMove={0} lance l''attaque {1}.\n";
f+="invokeMove={0} invoque l''attaque {1}.\n";
f+="invokeMoveFail=L''invoquation de l''attaque {0} a &eacute;chou&eacute;.\n";
f+="withdraw={0} est retir&eacute;.\n";
f+="send={0} est envoy&eacute;.\n";
f+="prepaRound={0} pr&eacute;pare l''attaque {1}.\n";
f+="rechargeRound={0} recharge pendant ce tour, donc {0} ne peut pas utiliser une attaque pendant ce tour.\n";
f+="disappeared={0} a disparu.\n";
f+="criticalHit=Coup critique!\n";
f+="nbHits={0} coups sont inflig&eacute;s contre {1}.\n";
f+="failMove=L''attaque {0} a &eacute;chou&eacute; contre {1}.\n";
f+="noAchieveTarget=L''attaque {0} lanc&eacute;e par {1} n''atteint aucune cible.\n";
f+="successfulMoveButNoDamage=L''attaque {0} a r&eacute;ussi contre {1} mais n''inflige aucun d&eacute;g&acirc;t contre {1}.\n";
f+="successfulMove=L''attaque {0} a r&eacute;ussi contre {1}.\n";
f+="learnMoveEvolution={0} apprend l''attaque {1}.\n";
f+="forgetMoveEvolution={0} oublie l''attaque {1}.\n";
f+="keepMoveEvolution={0} garde l''attaque {1}.\n";
f+="fightEvolution={0} a &eacute;volu&eacute; en {1}.\n";
f+="statusBeginRound={0} ne peut pas utiliser une attaque car {0} est sous l''effet du statut {1}.\n";
f+="statusBeginRoundRel={0} ne peut pas utiliser une attaque contre {2} car {0} est sous l''effet du statut {1} par {2}.\n";
f+="changingViewPointUser={0} devient le lanceur.\n";
f+="changingViewPointTarget={0} devient la cible.\n";
f+="protectTypeByGlobalMove={0} est prot&eacute;g&eacute; contre le type {1} par l''attaque globale {2}.\n";
f+="protectTypeByIndividualMove={0} est prot&eacute;g&eacute; contre le type {1} par certaines attaques.\n";
f+="protectTypeByAbilityWeather={0} est prot&eacute;g&eacute; contre le type {1} par la capacit&eacute; {2} activ&eacute; par l''attaque globale {3}.\n";
f+="protectTypeByAbility={0} est prot&eacute;g&eacute; contre le type {1} par la capacit&eacute; {2}.\n";
f+="protectTypeByItem={0} est prot&eacute;g&eacute; contre le type {1} par l''objet{2}.\n";
f+="protectByAllyAbility={0} est prot&eacute;g&eacute; contre l''attaque {1} par la capacit&eacute; {2} d''un alli&eacute; de {0}.\n";
f+="protectByAbilityDamageAlly=L''alli&eacute; {0} est prot&eacute;g&eacute; contre l''attaque offensive {1} par la capacit&eacute; {2}.\n";
f+="protectByAbility={0} est prot&eacute;g&eacute; contre l''attaque {1} par la capacit&eacute; {2}.\n";
f+="protectByAbilityDamage={0} est prot&eacute;g&eacute; contre l''attaque offensive {1} par la capacit&eacute; {2}.\n";
f+="protectByItem={0} est prot&eacute;g&eacute; contre l''attaque {1} par l''objet {2}.\n";
f+="protectedAgainstSecEff={0} est prot&eacute;g&eacute; contre les effets secondaires de l''attaque {1} par la capacit&eacute; {2}.\n";
f+="protectedByIndividualMove={0} est prot&eacute;g&eacute; contre l''attaque {1} par l''attaque individuelle {2}.\n";
f+="protectedByTeamMove={0} est prot&eacute;g&eacute; contre l''attaque {1} par l''attaque d''&eacute;quipe {2}.\n";
f+="protectedByDisappearing={0} est intouchable.\n";
f+="immuLowStatAbilityAlly={0} est prot&eacute;g&eacute; contre la baisse de la statistique {1} par la capacit&eacute; {2} d''un alli&eacute;.\n";
f+="immuLowStatTeam={0} est prot&eacute;g&eacute; contre la baisse de la statistique {1} par l''attaque d''&eacute;quipe {2}.\n";
f+="immuLowStatAbility={0} est prot&eacute;g&eacute; contre la baisse de la statistique {1} par la capacit&eacute; {2}.\n";
f+="immuLowStatStAbility={0} est prot&eacute;g&eacute; contre la baisse de la statistique {1} par la capacit&eacute; {2} avec le statut {3}.\n";
f+="immuLowStatItem={0} est prot&eacute;g&eacute; contre la baisse de la statistique {1} par l''objet {2}.\n";
f+="immuChgtStatMin={0} a atteint le minimum de la statistique {1}.\n";
f+="immuChgtStatMax={0} a atteint le maximum de la statistique {1}.\n";
f+="immuStatGlobalMove={0} est prot&eacute;g&eacute; contre le statut {1} par une attaque globale.\n";
f+="immuStatAbilityAlly={0} est prot&eacute;g&eacute; contre le statut {1} par la capacit&eacute; {2} d''un alli&eacute;.\n";
f+="immuStatTeam={0} est prot&eacute;g&eacute; contre le statut {1} par l''attaque d''&eacute;quipe {2}.\n";
f+="immuStatGlobalMoveAbility={0} est prot&eacute;g&eacute; contre le statut {1} par la capacit&eacute; {2} activ&eacute; par l''attaque globale {3}.\n";
f+="immuStatAbility={0} est prot&eacute;g&eacute; contre le statut {1} par la capacit&eacute; {2}.\n";
f+="immuStatItem={0} est prot&eacute;g&eacute; contre le statut {1} par l''objet {2}.\n";
f+="createClone={0} se cr&eacute;e un clone avec {1} points de vie.\n";
f+="helpAlly={0} aide {1}.\n";
return f;
}
static String enAikiGameFightFighter(){
String f="wonEv={0} won {1} points of ev for the statistic {2}.\n";
f+="wonEvMax={0} has the maximum points of ev for the statistic {1}.\n";
f+="wonExp={0} won {1} points of experience.\n";
f+="growLevel={0} grew to level {1}.\n";
f+="learnMove={0} learnt the move {1}.\n";
f+="wonHappines={0} won {1} happiness points.\n";
f+="maxHappines={0} achieved the maximum of happiness points.\n";
f+="fullHeal={0} is full healed.\n";
return f;
}
static String frAikiGameFightFighter(){
String f="wonEv={0} a gagn&eacute; {1} points d''ev pour la statistique {2}.\n";
f+="wonEvMax={0} a le maximum de points d''ev pour la statistique {1}.\n";
f+="wonExp={0} a gagn&eacute; {1} points d''exp&eacute;rience.\n";
f+="growLevel={0} monte au niveau {1}.\n";
f+="learnMove={0} apprend l''attaque {1}.\n";
f+="wonHappines={0} a gagn&eacute; {1} points de bonheur.\n";
f+="maxHappines={0} a le maximum de points de bonheur.\n";
f+="fullHeal={0} est totalement soign&eacute;.\n";
return f;
}
static String enAikiGameFightTeam(){
String f="cancelUseItem=With the help of an ally, {0} can use its item again.\n";
f+="useItem={0} lost its item, so {0} cannot use anymore it.\n";
return f;
}
static String frAikiGameFightTeam(){
String f="cancelUseItem=Avec l''aide d''un alli&eacute;, {0} peut utiliser de nouveau son objet.\n";
f+="useItem={0} a perdu son objet, donc {0} ne peut plus l''utiliser.\n";
return f;
}
static String enAikiGameGame(){
String f="noBeatenTrainer={0} from {1} was not beaten yet.\n";
f+="beatenTrainerFull=The unlocked places are: {0}.\n";
f+="beatenTrainer=The partially unlocked places are: {0}.\n";
f+="missingPk=It is missing {0} pokemon for hosting.\n";
f+="sameGender=The pokemon have same gender.\n";
f+="samePk=The same pokemon is selected twice.\n";
f+="noCommonEgg=The pokemon cannot be together.\n";
f+="receivedEgg=You got an egg containing {0}.\n";
f+="notEnoughPlace=You have not room enough to get the egg to your team.\n";
f+="receivedEggParents=You got an egg containing {0} and the two pokemon having produced it.\n";
f+="receivedEggWithoutParent=You got an egg containing {0} without the two pokemon having produced it.\n";
f+="receivedParents=You got the two hosted pokemon.\n";
f+="notEnoughPlaceParents=You have not room enough to get the two hosted pokemon.\n";
f+="caughtPk=The wild pokemon is caught.\n";
f+="notCaughtPk=The wild pokemon is not caught.\n";
f+="endGame=The game is successful, you are master over pokemon.\n";
f+="possibleBeatLeader=You can beat {0}.\n";
f+="remainingTrainersGym=You must beat all other trainers in this gym.\n";
f+="wonMoney=You won {0} euros.\n";
f+="lostMoney=You lost {0} euros.\n";
return f;
}
static String frAikiGameGame(){
String f="noBeatenTrainer={0} de {1} n''a pas encore &eacute;t&eacute; battu.\n";
f+="beatenTrainerFull=Les lieux d&eacute;bloqu&eacute;s sont: {0}.\n";
f+="beatenTrainer=Les lieux partiellement d&eacute;bloqu&eacute;s sont: {0}.\n";
f+="missingPk=Il manque {0} pokemon pour la pension.\n";
f+="sameGender=Les pokemon ont le m&ecirc;me genre.\n";
f+="samePk=Le m&ecirc;me pokemon est s&eacute;lectionn&eacute; deux fois.\n";
f+="noCommonEgg=Les pokemon ne sont pas compatibles.\n";
f+="receivedEgg=Vous avez r&eacute;cup&eacute;r&eacute; un oeuf contenant {0}.\n";
f+="notEnoughPlace=Vous n'avez pas assez de place dans l'&eacute;quipe pour r&eacute;cup&eacute;rer l'oeuf\n";
f+="receivedEggParents=Vous avez r&eacute;cup&eacute;r&eacute; un oeuf contenant {0} et les deux pokemons l''ayant produit.\n";
f+="receivedEggWithoutParent=Vous avez r&eacute;cup&eacute;r&eacute; un oeuf contenant {0} sans les deux pokemons l''ayant produit.\n";
f+="receivedParents=Vous avez r&eacute;cup&eacute;r&eacute; les pokemon de la pension.\n";
f+="notEnoughPlaceParents=Vous n'avez pas assez de place dans l'&eacute;quipe pour r&eacute;cup&eacute;rer les pokemon de la pension.\n";
f+="caughtPk=Le pokemon sauvage est captur&eacute;.\n";
f+="notCaughtPk=Le pokemon sauvage n''est pas captur&eacute;.\n";
f+="endGame=Le jeu est fini, vous &ecirc;tes ma&icirc;tre des pokemon.\n";
f+="possibleBeatLeader=Vous pouvez combattre {0}.\n";
f+="remainingTrainersGym=Vous devez battre tous les autres dresseurs dans cette ar&ecirc;ne.\n";
f+="wonMoney=Vous avez gagn&eacute; {0} euros.\n";
f+="lostMoney=Vous avez perdu {0} euros.\n";
return f;
}
static String enAikiGamePlayerPlayer(){
String f="cannotEvolve={0} cannot evolve\n";
f+="evolveInto={0} evolve into {1}.\n";
f+="badNumberMoves={0} cannot know {1} moves.\n";
f+="betweenNumberMoves={0} must know between {1} and {2} moves.\n";
f+="missMoves={0} must know all moves.\n";
f+="hatch={0} hatched from its egg.\n";
f+="repelOff=The wild pokemon are no longer repelled\n";
f+="newPk={0} is added to the data.\n";
f+="newPkAddedBox=The new pokemon is added to the box.\n";
f+="learnMove={0} learnt {1}.\n";
f+="learnMoveForget={0} learnt {1} by forgetting {2}.\n";
f+="restoredHp=The restored health points of {0} are {1}.\n";
f+="restoredMove=The move {0} of the pokemon {1} is recovered by {2} power points.\n";
f+="boostedMove=The maximum of power points of the move {0} of the pokemon {1} are increased by {2}.\n";
f+="boostedStatistic=The statistic {0} of the pokemon {1} is increased by {2}.\n";
f+="healStatus=The status {0} of the pokemon {1} is deleted.\n";
f+="enableRepel=The item {0} is enabled for {1} steps.\n";
f+="learnMoves={0} learnt the move {1}.\n";
f+="forgetMoves={0} forgot the move {1}.\n";
f+="keepMoves={0} kept the move {1}.\n";
f+="takenItem=The item {0} is moved to the inventory.\n";
return f;
}
static String frAikiGamePlayerPlayer(){
String f="cannotEvolve={0} ne peut pas &eacute;voluer.\n";
f+="evolveInto={0} &eacute;volue en {1}.\n";
f+="badNumberMoves={0} ne peut pas conna&icirc;tre {1} attaques.\n";
f+="betweenNumberMoves={0} doit conna&icirc;tre entre {1} et {2} attaques.\n";
f+="missMoves={0} doit conna&icirc;tre toutes les attaques.\n";
f+="hatch={0} est sorti de son oeuf.\n";
f+="repelOff=Les pokemon sauvages ne sont plus repouss&eacute;s\n";
f+="newPk={0} est ajout&eacute; aux donn&eacute;es.\n";
f+="newPkAddedBox=Le nouveau pokemon est ajout&eacute; &agrave; la bo&icirc;te.\n";
f+="learnMove={0} apprend {1}.\n";
f+="learnMoveForget={0} apprend {1} en oubliant {2}.\n";
f+="restoredHp=Le nombre de points de vie restaur&eacute;s de {0} vaut {1}.\n";
f+="restoredMove=L''attaque {0} du pokemon {1} est restaur&eacute;e de {2} points de pouvoir.\n";
f+="boostedMove=Le maximum des points de pouvoir de l''attaque {0} du pokemon {1} sont augment&eacute;s de {2}.\n";
f+="boostedStatistic=La statistique {0} du pokemon {1} est augment&eacute;e de {2}.\n";
f+="healStatus=Le statut {0} du pokemon {1} est retir&eacute;.\n";
f+="enableRepel=L''objet {0} est activ&eacute; pendant {1} pas.\n";
f+="learnMoves={0} apprend l''attaque {1}.\n";
f+="forgetMoves={0} oublie l''attaque {1}.\n";
f+="keepMoves={0} garde l''attaque {1}.\n";
f+="takenItem=L''objet {0} est rang&eacute; dans l''inventaire.\n";
return f;
}
static String enAikiMapPokemonPokemonplayer(){
String f="happiness={0} won {1} points of happiness.\n";
f+="decreasingHp=The health points of {0} are set from {1} to {2}.\n";
return f;
}
static String frAikiMapPokemonPokemonplayer(){
String f="happiness={0} gagne {1} points de bonheur.\n";
f+="decreasingHp=Les points de vie de {0} passent de {1} &agrave; {2}.\n";
return f;
}
}
