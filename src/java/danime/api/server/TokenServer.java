package danime.api.server;

import danime.api.JSon.JSon4TokenServer;
import java.net.*;
import java.io.*;
import net.arnx.jsonic.JSON;
import javax.net.ssl.*;
// 証明書無視ロジック用
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import danime.api.server.base.ServerObject;


/**
 *
 * @author fujiyohi
 */
public class TokenServer extends danime.api.server.base.ServerObject{

    //テストのメイン... (※例外処理は簡略)
    final int CONNECT_TIMEOUT = 10 * 1000;  //接続タイムアウト[ms]
    final int READ_TIMEOUT = 5 * 1000;  //読み取りタイムアウト[ms]
    final String ENCORDING = "UTF-8";  //エンコードは固定

    // TODO: For SSL
    //String phpUrl = "https://localhost/WidevineLicenseTokenServer/TokenServer/token.php?ll";  //JSON 受信用 PHPのURL
    String phpUrl = "http://localhost/WidevineLicenseTokenServer/TokenServer/token.php?ll";  //JSON 受信用 PHPのURL
    
    public void send(){
        try {
            // 証明書無視ロジック
            TrustManager[] tm = {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] xc,String type) {
                    }

                    public void checkServerTrusted(X509Certificate[] xc,String type) {
                    }
                }
            };

            SSLContext ctx = SSLContext.getInstance("SSL");
            ctx.init(null, tm, new SecureRandom());
        
        
            URL url = new URL(phpUrl);
            // TODO: For SSL
            //HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            // TODO: For SSL
            //con.setDoOutput(true);
            // TODO: For SSL
            //con.setSSLSocketFactory(ctx.getSocketFactory());
            
            con.setConnectTimeout(CONNECT_TIMEOUT);  //接続タイムアウト
            con.setReadTimeout(READ_TIMEOUT);  //読み取りタイムアウト
            con.setRequestMethod("POST");  //"GET"でも可
            con.setDoOutput(true);  //出力用接続フラグON
            con.connect();

            //データ用クラス から、JSON 形式に変換
            String text4 = JSON.encode(json);

            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), ENCORDING)));
            pw.write(text4);  //出力
            pw.close();  //フラッシュして閉じる

            //PHP からの応答(確認用)
            // {"error_code":11,"error_message":"json parse fail"}
            String res = readLinesText(con.getInputStream());
            if(isJSon(res)){
                // エラーなのでオブジェクトに格納する。
                TokenServerErrorObject tokenErrObj = JSON.decode(res, TokenServerErrorObject.class);
                this.returnCd = tokenErrObj.error_code;
                this.errorCode = tokenErrObj.error_code;
                this.errorMessage = tokenErrObj.error_message;
                this.tokenInfo = "";
            }else{
                // 成功なのでトークンを格納する
                this.returnCd = 0;
                this.errorCode = 0;
                this.tokenInfo = res;
                this.errorMessage = "";
            }
            
            

            con.disconnect();

        } catch (Exception e) {
            // TODO: 自動生成された catch ブロック
            e.printStackTrace();
        }
    }
    
//<editor-fold defaultstate="collapsed" desc="トークンサーバに投げるJSONクラス">
    private JSon4TokenServer json;
    /**
     * トークンサーバに投げるJSONクラスを受け取る
     * @param svrObj サーバオブジェクト
     */
    public void setSendObj(ServerObject svrObj){
        // JSONデータを保持
        JSon4TokenServer json = new JSon4TokenServer();
        json.usage_type = svrObj.usageType;
        json.product_type = svrObj.productType;
        json.product_id = svrObj.keyId;
        json.purchase_id = svrObj.purchaseId;
        json.user_id = svrObj.userId;
        json.valid_from = svrObj.beginDate;
        json.valid_until = svrObj.expirationDate;
        json.test_flag = svrObj.testFlag;
        json.drm_key_id = svrObj.drmKeyId;
        json.device_type = svrObj.deviceType;
        json.output_quality = svrObj.outputQuality;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="サーバからの返却データの読み込み">
    public static final String readLinesText(InputStream is) throws IOException {
        final StringBuilder str = new StringBuilder();
        final String sep = System.getProperty("line.separator");  //改行コード
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            while (br.ready()) {
                str.append(br.readLine());  //終端文字は含まない
                str.append(sep);  //改行コード追加
            }
            return str.toString();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                //IOException
            }
            str.setLength(0);
        }
    }
//</editor-fold>
    
    private Boolean isJSon(String str){
        Boolean ret = false;
        //JSon形式に変換できるか評価する
        try{
            TokenServerErrorObject tokenErrObj = JSON.decode(str, TokenServerErrorObject.class);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    
}
