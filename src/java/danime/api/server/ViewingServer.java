/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danime.api.server;

import com.cdyne.ws.DocumentSummary;
import java.io.File;
import java.util.Iterator;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import jp.dmktsp.anime.back.ws.PurchaseQueryResult;

import jp.dmktsp.anime.back.ws.PurchaseQueryInfo;
import jp.dmktsp.anime.back.ws.PurchaseQueryResult;
/**
 *
 * @author fujiyohi
 */
public class ViewingServer extends danime.api.server.base.ServerObject {

    // 視聴サーバ独自のパラメーター
    private String customerId;
    private String authPass;

    public ViewingServer() {

    }

    /**
     * 視聴WEBのSOAPサービスに接続して処理を得る
     * @return 成否
     * @see "https://netbeans.org/kb/docs/websvc/client_ja.html#developingtheclient"
     */
    public boolean connectSoap(){
        try{
            PurchaseQueryInfo info = new PurchaseQueryInfo();
            info.setCustomerId("000000");
            info.setAuthPass("pass");
            info.setUserId("012345");
            info.setKeyId("2343112313");
            PurchaseQueryResult result = getPurchaseInfo(info);
        }catch(Exception ex){
            ex.toString();
        }
        return true;
    }
    
    /**
     *
     *
     * @see "http://itdoc.hitachi.co.jp/manuals/link/cosmi_v0870/APWK/EU310159.HTM"
     * @return SOAPサーバからのデータ取得成否
     */
    public boolean getSoap() {
        QName port = new QName("http://view-sv/", "UserInfoPort");
        Service service = Service.create(
                new QName("http://view-sv/", "UserInfoService"));
        String serviceURL = "http://webhost:8085/dispatch_provider/UserInfoService";

        // サービスにポートを追加
        service.addPort(port, SOAPBinding.SOAP11HTTP_BINDING, serviceURL);

        // Dispatchオブジェクト生成
        Dispatch<SOAPMessage> dispatch = service.createDispatch(
                port, SOAPMessage.class, Service.Mode.MESSAGE);

        // 要求メッセージ
        SOAPMessage request = null;

        try {
            // 要求メッセージの生成
            request = MessageFactory.newInstance().createMessage();
            SOAPBody reqSoapBody = request.getSOAPBody();

            // 社員番号を設定
            SOAPBodyElement requestRoot = reqSoapBody.addBodyElement(
                    new QName("http://sample.com", "number"));
            SOAPElement soapElement = requestRoot.addChildElement(
                    new QName("http://sample.com", "value"));
            soapElement.addTextNode("1234");

            // 添付ファイル(顔写真)を設定
            String filePath = "C:\\attachment.jpg";
            FileDataSource fds = new FileDataSource(filePath);
            AttachmentPart apPart
                    = request.createAttachmentPart(new DataHandler(fds));
            request.addAttachmentPart(apPart);

            // SOAPメッセージの送受信
            SOAPMessage response = dispatch.invoke(request);

            // 応答メッセージからデータを取得
            SOAPBody resSoapBody = response.getSOAPBody();
            SOAPBodyElement resRoot
                    = (SOAPBodyElement) resSoapBody.getChildElements().next();
            Iterator iterator = resRoot.getChildElements();
            String result
                    = ((SOAPElement) iterator.next()).getFirstChild().getNodeValue();

            // 登録確認メッセージの表示
            System.out.println("[RESULT] " + result);
        } catch (SOAPException e) {
            return false;
        }
        return true;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAuthPass() {
        return authPass;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setAuthPass(String authPass) {
        this.authPass = authPass;
    }

    private static PurchaseQueryResult getPurchaseInfo(jp.dmktsp.anime.back.ws.PurchaseQueryInfo info) {
        jp.dmktsp.anime.back.ws.PurchaseInformation service = new jp.dmktsp.anime.back.ws.PurchaseInformation();
        jp.dmktsp.anime.back.ws.PurchaseInformationSoap port = service.getPurchaseInformationSoap();
        return port.getPurchaseInfo(info);
    }

    private static DocumentSummary checkTextBodyV2(java.lang.String bodyText) {
        com.cdyne.ws.Check service = new com.cdyne.ws.Check();
        com.cdyne.ws.CheckSoap port = service.getCheckSoap();
        return port.checkTextBodyV2(bodyText);
    }

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

    private static PurchaseQueryResult getPurchaseInfo_1(jp.dmktsp.anime.back.ws.PurchaseQueryInfo info) {
        jp.dmktsp.anime.back.ws.PurchaseInformation service = new jp.dmktsp.anime.back.ws.PurchaseInformation();
        jp.dmktsp.anime.back.ws.PurchaseInformationSoap port = service.getPurchaseInformationSoap12();
        return port.getPurchaseInfo(info);
    }
    public boolean connectSoapV3(){
        try{
            PurchaseQueryInfo info = new PurchaseQueryInfo();
            info.setCustomerId("000000");
            info.setAuthPass("pass");
            info.setUserId("012345");
            info.setKeyId("2343112313");
            PurchaseQueryResult result = getPurchaseInfo_1(info);
            int i = result.getQueryResult();
        }catch(Exception ex){
            ex.toString();
        }
        return true;
    }
    
}
