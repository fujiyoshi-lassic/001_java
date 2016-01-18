/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsc.server;

import java.io.IOException;
import java.util.*;
import java.net.*;

import lsc.GenericResource;
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
     * Tokenサーバアカウント
     */
    public String const_token_sv_url;
    
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
    }

}
