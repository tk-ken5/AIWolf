package jp.ne.sakura.vopaldragon.aiwolf.framework;

/**
 * 本フレームワーク内で発行されるイベントの種類。
 */
public enum EventType {
    /**
     * 昼間の会話、会話1巡でまとめて1イベント
     */
    TALK,
    /**
     * 人狼同士の会話、会話1巡でまとめて1イベント
     */
    WHISPER,
    /**
     * 襲撃対象を決める投票の結果（再投票も含む）
     */
    ATTACK_VOTE,
    /**
     * 投票の結果、全員の投票でまとめて1イベント（再投票も含む）
     */
    VOTE,
    /**
     * 最終的に追放の対象になった人。占い師や狼の場合、夜に判る。それ以外の人は朝判る。
     */
    EXECUTE,
    /**
     * 夜に襲撃された人（ボディガードが守った場合にはイベントが無い）
     */
    ATTACK,
    /**
     * ボディガードが守った場合でもイベントがある。狼の襲撃相手の決定（人狼のみに発生するイベント；意見が割れたときの結果が判る）
     */
    VICTIM_DECIDED,
    /**
     * 占いの結果
     */
    DIVINE,
    /**
     * 霊能力者の結果
     */
    MEDIUM,
    /**
     * ガードに成功した（＝ATTACKイベントは無い）
     */
    GUARD,
    /**
     * 新しい日が始まる
     */
    DAYSTART

}
