/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsc.server;

import java.io.IOException;
import java.util.Properties;
import lsc.GenericResource;
import java.io.File;

/**
 *
 * @author fujiyohi
 */
public class svConst {
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
    /**
     * コンストラクタ
     * Const.xmlから定数を取得して保持する
     * @throws IOException 
     */
    public svConst() throws IOException{
        try{
            String path = new File(".").getAbsoluteFile().getParent();
            System.out.println(path);
            path = System.getProperty("user.dir");
            System.out.println(path);
        
            
            Properties props = new Properties();
            props.loadFromXML(GenericResource.class.getResourceAsStream("./Const.xml"));
            this.const_viewing_sv_url       = (String)props.get("CNT_VIEWING_SV_URL");
            this.const_contents_provider_id = (String)props.get("CNT_CONTENTS_PROVIDER_ID");
            this.const_contents_provider_pw = (String)props.get("CNT_CONTENTS_PROVIDER_PW");
            this.const_token_sv_url         = (String)props.get("CNT_TOKEN_SV_URL");
        }catch(IOException ex){
            System.out.println(ex.toString());
        }
        
    }

}
