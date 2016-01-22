package danime.api.server;

import com.cdyne.ws.DocumentSummary;
import danime.api.server.base.ServerObject;
import java.util.GregorianCalendar;
import jp.dmktsp.anime.back.ws.PurchaseQueryInfo;
import jp.dmktsp.anime.back.ws.PurchaseQueryResult;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeFactory;

/**
 * 送信受信クラスを内包しつつ、SOAP処理を実装
 * @author fujiyohi
 */
public class ViewingServer extends danime.api.server.base.ServerObject {

    /**
     * 視聴WEBへのIN、OUTのパラメータを保持する
     */
    private ServerObject svrObj;

    /**
     * コンストラクタ
     */
    public ViewingServer() {
        // 初期化必要なし
    }

    /**
     * 視聴WEBのSOAPサービスに接続して処理を得る
     * @return 成否
     * @see "https://netbeans.org/kb/docs/websvc/client_ja.html#developingtheclient"
     */
    public boolean connectSoap(){
        try{
            PurchaseQueryInfo info = new PurchaseQueryInfo();
//            info.setCustomerId("wdv_dmkt");
//            info.setAuthPass("L8slviaqAkyf");
//            info.setUserId("999999999999999999999999999999");
//            info.setKeyId("2343112313");
            // パラメータをセット
            info.setCustomerId(svrObj.customerId);
            info.setAuthPass(svrObj.authPass);
            info.setUserId(svrObj.userId);
            info.setKeyId(svrObj.keyId);
            // 処理
            //PurchaseQueryResult result = getPurchaseInfo(info);
            // TODO: *************************ダミー処理 Start
            PurchaseQueryResult result = new PurchaseQueryResult();
            result.setQueryResult(0);
            result.setBusinessModel("RENTAL");
            result.setBeginDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
            result.setExpirationDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
            // TODO: *************************ダミー処理 End
            // 返値を取得
            svrObj.queryResult = result.getQueryResult();
            svrObj.usageType = result.getBusinessModel();
            svrObj.beginDate = XMLGregorianCalendar2String(result.getBeginDate());
            svrObj.expirationDate = XMLGregorianCalendar2String(result.getExpirationDate());
            svrObj.gracePeriod = XMLGregorianCalendar2String(result.getExpirationDate());
            // 下位のオブジェクトに内包
            svrObj.createViewServer();
        }catch(Exception ex){
            // 呼び元に返してエラーを実装させる
            throw new RuntimeException(ex.toString());
        }
        return true;
    }

    /**
     * 視聴WEBのSOAPサービスへのメソッド呼び出し
     * @param info パラメータクラス
     * @return リターンクラス
     */
    private static PurchaseQueryResult getPurchaseInfo(jp.dmktsp.anime.back.ws.PurchaseQueryInfo info) {
        jp.dmktsp.anime.back.ws.PurchaseInformation service = new jp.dmktsp.anime.back.ws.PurchaseInformation();
        jp.dmktsp.anime.back.ws.PurchaseInformationSoap port = service.getPurchaseInformationSoap();
        return port.getPurchaseInfo(info);
    }

    /**
     * SOAPへのメソッド呼び出しダミー(スペルチェック処理)
     * @param bodyText
     * @return 
     */
    private static DocumentSummary checkTextBodyV2(java.lang.String bodyText) {
        com.cdyne.ws.Check service = new com.cdyne.ws.Check();
        com.cdyne.ws.CheckSoap port = service.getCheckSoap();
        return port.checkTextBodyV2(bodyText);
    }

    /**
     * SOAP接続のダミー(スペルチェック処理)
     * @return 
     */
    public boolean connectSoapV2(){
        try{
            String targetStr = "I am a Japanees.";
            DocumentSummary result = checkTextBodyV2(targetStr);
            String body = result.getBody();
            String version = result.getVer();
        }catch(Exception ex){
            ex.toString();
        }
        return true;
    }
    /**
     * パラメータ群の受け取り、内部に格納
     * @param sObj ServerObject(APIで操作する全パラメータが格納されたクラス)
     */
    public void setParam(ServerObject sObj){
        this.svrObj = sObj;
    }
    /**
     * 処理結果を内包した親オブジェクトを返す
     * @return 
     */
    public ServerObject getParam(){
        return this.svrObj;
    }
    /**
     * XMLGregorianCalendarクラスからStringクラスにキャスト
     * @param cal XMLGregorianCalendarクラス
     * @return 文字列化された日時情報
     */
    private String XMLGregorianCalendar2String(XMLGregorianCalendar cal){
        return cal.toString();
    }
}
