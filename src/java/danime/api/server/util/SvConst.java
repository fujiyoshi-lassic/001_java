/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danime.api.server.util;

import java.io.IOException;
import java.util.*;
import java.net.*;

import danimeApi.GenericResource;
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
    
    
    public String CNT_TOKEN_DEBUG_FLAG;
    
    public static final String propertiesPath = "D:\\dev\\ApiServer\\resources";
    public static final String propertiesFile = "setting";
    
    /**
     * コンストラクタ
     * Const.xmlから定数を取得して保持する
     * @throws IOException 
     */
    public SvConst() throws MalformedURLException{
        File dicDir = Paths.get(propertiesPath).toFile();
        URLClassLoader urlLoader = new URLClassLoader(new URL[]{dicDir.toURI().toURL()});
        ResourceBundle myResource  = ResourceBundle.getBundle(propertiesFile, Locale.getDefault(), urlLoader);
        
        this.const_viewing_sv_url       = (String)myResource.getString("CNT_VIEWING_SV_URL");
        this.const_contents_provider_id = (String)myResource.getString("CNT_CONTENTS_PROVIDER_ID");
        this.const_contents_provider_pw = (String)myResource.getString("CNT_CONTENTS_PROVIDER_PW");
        this.const_token_sv_url         = (String)myResource.getString("CNT_TOKEN_SV_URL");
        
        // トークンサーバのtestFlagはbooleanで保存してみる。
        String testFlag = (String)myResource.getObject("CNT_TOKEN_DEBUG_FLAG");
        // TODO: 醜いソース。。。。。
        if(testFlag.equals("true")){
            this.const_token_debug_flag = true;
        }else{
            this.const_token_debug_flag = false;
        }
        
    }

}
