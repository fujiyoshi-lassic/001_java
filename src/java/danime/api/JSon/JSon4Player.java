/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danime.api.JSon;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

/**
 *
 * @author fujiyohi
 */
public class JSon4Player {
    public int returnCd;
    public String tokenInfo;
    public String productType;
    public String beginDate;
    public String expirationDate;
    public String messageText;
    
    /**
     * 視聴WEBとの通信でのエラーメッセージ
     */
    private static final Map VIEW_SERVER_ERROR;
	static {
		HashMap<Integer ,String> map = new HashMap<Integer ,String>();
		map.put(1 ,"未購入");
		map.put(2 ,"通信エラー");
		map.put(3 ,"認証エラー");
		VIEW_SERVER_ERROR = Collections.unmodifiableMap(map);
	}
    /**
     * トークンサーバとの通信でのエラーメッセージ
     */
    private static final Map TOKEN_SERVER_ERROR;
        static {
                HashMap<Integer ,String> map = new HashMap<Integer ,String>();
                map.put(1 ,"jsonデータが存在しない場合。");
                map.put(2 ,"drm_key_idが存在しない場合。");
                map.put(3 ,"usage_type値が指定された値以外のデータである場合");
                map.put(4 ,"product_type値が指定された値以外のデータである場合");
                map.put(6 ,"usage_type値が存在しない場合。");
                map.put(7 ,"product_type値が存在しない場合。");
                map.put(9 ,"その他の入力値にエラーがある場合。");
                map.put(11 ,"jsonデータ解析失敗");
                map.put(12 ,"日付データの型が間違っている場合。");
                map.put(13 ,"基準データの形式を超えた場合。");
                TOKEN_SERVER_ERROR = Collections.unmodifiableMap(map);
        }
    /**
     * APIサーバ内部でのエラーメッセージ
     */
    private static final Map API_SERVER_ERROR;
	static {
		HashMap<Integer ,String> map = new HashMap<Integer ,String>();
		map.put(1001 ,"パラメーターエラー");
		map.put(1002 ,"タイムアウト");
		map.put(1003 ,"予備");
		map.put(1004 ,"予備");
		map.put(1005 ,"予備");
		map.put(1010 ,"APIサーバ内部エラー");
		API_SERVER_ERROR = Collections.unmodifiableMap(map);
	}

    public JSon4Player() {
        // 不慮の事故に備えて初期値を入れておく
        this.returnCd = 1010;
        this.messageText = (String)JSon4Player.API_SERVER_ERROR.get(this.returnCd);
    }
    
    
}
