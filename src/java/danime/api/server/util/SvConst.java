/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danime.api.server.util;

import java.io.IOException;
import java.util.*;
import java.net.*;

import java.io.File;
import java.nio.file.Paths;

/**
 *
 * @author fujiyohi
 */
public class SvConst {
    /**
     * 視聴サーバURL
     */
    public String const_viewing_sv_url;
    /**
     * コンテンツプロバイダID
     */
    public String const_contents_provider_id;
    /**
     * コンテンツプロバイダパスワード
     */
    public String const_contents_provider_pw;
    /**
     * TokenサーバURL
     */
    public String const_token_sv_url;
    /**
     * トークンサーバのデバッグモード
     */
    public Boolean const_token_debug_flag;
    /**
     * デバッグレベル(0：本番モード、1：検証モード、9：開発モード、それ以外：検証モード(1と同じ))
     */
    public int const_api_debug_mode;
    
    /**
     * プロパティファイルの場所指定
     * どこでも設定可能
     */
    public static final String propertiesPath = "D:\\dev\\ApiServer\\resources";
    /**
     * プロパティファイルの名前
     */
    public static final String propertiesFile = "setting";
    
    public static final int SERVER_API = 1;
    public static final int SERVER_VIEW = 2;
    public static final int SERVER_TOKEN = 3;
    
    public static final int DEBUG_LEVEL_OPERATION = 0;
    public static final int DEBUG_LEVEL_VERIFICATION = 1;
    public static final int DEBUG_LEVEL_DEVELOPMENT = 9;
    /**
     * コンストラクタ
     * Const.xmlから定数を取得して保持する
     * @throws MalformedURLException プロパティファイルが存在しない場合
     */
    public SvConst() throws MalformedURLException{
        File dicDir = Paths.get(propertiesPath).toFile();
        URLClassLoader urlLoader = new URLClassLoader(new URL[]{dicDir.toURI().toURL()});
        ResourceBundle myResource  = ResourceBundle.getBundle(propertiesFile, Locale.getDefault(), urlLoader);
        
        this.const_viewing_sv_url       = (String)myResource.getString("CNT_VIEWING_SV_URL");
        this.const_contents_provider_id = (String)myResource.getString("CNT_CONTENTS_PROVIDER_ID");
        this.const_contents_provider_pw = (String)myResource.getString("CNT_CONTENTS_PROVIDER_PW");
        this.const_token_sv_url         = (String)myResource.getString("CNT_TOKEN_SV_URL");
        
        // TODO: トークンサーバのtestFlagはbooleanで保存してみる。
        String testFlag = (String)myResource.getObject("CNT_TOKEN_DEBUG_FLAG");
        // TODO: 醜いソース。。。。。if文が冗長だってNBが怒る
        if(testFlag.equals("true")){
            this.const_token_debug_flag = true;
        }else{
            this.const_token_debug_flag = false;
        }
        
        // TODO: デバッグフラグはintで保存してみる
        String debugMode = (String)myResource.getString("CNT_API_DEBUG_MODE");
        if(debugMode.equals("0")){
            this.const_api_debug_mode = 0;
        }else if(debugMode.equals("2")){
            this.const_api_debug_mode = 9;
        }else{
            this.const_api_debug_mode = 1;
        }
        
    }

}
