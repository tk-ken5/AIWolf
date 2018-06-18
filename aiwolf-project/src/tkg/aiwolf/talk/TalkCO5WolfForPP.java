package tkg.aiwolf.talk;

import org.aiwolf.client.lib.ComingoutContentBuilder;
import org.aiwolf.client.lib.ContentBuilder;
import org.aiwolf.client.lib.Topic;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.data.Species;

import jp.ne.sakura.vopaldragon.aiwolf.framework.Game;
import tkg.aiwolf.model.TFAFGameModel;

public class TalkCO5WolfForPP extends TFAFTalkTactic {

    @Override
    public ContentBuilder talkImpl(int turn, int skip, int utter, TFAFGameModel model, Game game) {
        if (!game.getSelf().hasCO()) {
            if (game.getAgentStream().anyMatch(ag -> ag.coRole == Role.POSSESSED && ag.isAlive)//狂人告白者が生存しているか
                || game.getAllTalks().anyMatch(t //狂人占いをした発言者が生きている場合
                    -> (t.getTopic() == Topic.DIVINED
                && t.getTalker().isAlive
                && ((t.getTarget().isSelf && t.getResult() == Species.HUMAN)
                || (!t.getTarget().isSelf && t.getResult() == Species.WEREWOLF)))
                ))
                return new ComingoutContentBuilder(game.getSelf().agent, Role.POSSESSED);
        }
        return null;
    }

}
