package danime.api.client;

import danime.api.JSon.JSon4Player;

/**
 * クライアントサイドのプレイヤーの動きを定義
 * @author fujiyohi
 */
public class Player {

    /**
     * クライアントに返すJSONオブジェクト
     */
    private JSon4Player _json;
    /**
     * クライアントに返すJSON文字列(シリアライズ化済)
     */
    private String _jsonpString;
    
    /**
     * コンストラクタ
     * 初期化処理
     */
    public Player() {
        _json = new JSon4Player();
        _jsonpString = "";
    }
    /**
     * 内部保持しているはずのJSONオブジェクトからシリアライズ化されたjsonp文字列を返す
     * @param callBackStr コールバックパラメータ
     * @return シリアライズ化されたjsonp文字列
     */
    public String json2jsonp(String callBackStr){
        return _jsonpString;
    }
    
}
