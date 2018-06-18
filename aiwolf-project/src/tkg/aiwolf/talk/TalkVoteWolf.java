package tkg.aiwolf.talk;

import java.util.List;

import jp.ne.sakura.vopaldragon.aiwolf.framework.Game;
import jp.ne.sakura.vopaldragon.aiwolf.framework.GameAgent;
import jp.ne.sakura.vopaldragon.aiwolf.util.Utils;
import tkg.aiwolf.model.TFAFGameModel;

import org.aiwolf.client.lib.ContentBuilder;
import org.aiwolf.client.lib.VoteContentBuilder;

/**
 * 最も狼らしいAgentに投票する
 *
 */
public class TalkVoteWolf extends TFAFTalkTactic {

    @Override
    public ContentBuilder talkImpl(int turn, int skip, int utter, TFAFGameModel model, Game game) {
        if (model.currentVoteTarget == null) {
            //最も狼らしいAgentに投票宣言
            List<GameAgent> list = game.getAliveOthers();
            Utils.sortByScore(list, model.getEvilScore(), false);
            GameAgent wolfCand = list.get(0);
            model.currentVoteTarget = wolfCand;
            return new VoteContentBuilder(wolfCand.agent);
        }
        return null;
    }
}
