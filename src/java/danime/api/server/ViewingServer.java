/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danime.api.server;

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
     *
     *
     * @see
     * "http://itdoc.hitachi.co.jp/manuals/link/cosmi_v0870/APWK/EU310159.HTM"
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

}
