
package jp.dmktsp.anime.back.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PurchaseInformation", targetNamespace = "http://ws.back.anime.dmktsp.jp/", wsdlLocation = "https://dev.anime.dmkt-sp.jp/animestore/services/PurchaseInformation?wsdl")
public class PurchaseInformation
    extends Service
{

    private final static URL PURCHASEINFORMATION_WSDL_LOCATION;
    private final static WebServiceException PURCHASEINFORMATION_EXCEPTION;
    private final static QName PURCHASEINFORMATION_QNAME = new QName("http://ws.back.anime.dmktsp.jp/", "PurchaseInformation");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://dev.anime.dmkt-sp.jp/animestore/services/PurchaseInformation?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PURCHASEINFORMATION_WSDL_LOCATION = url;
        PURCHASEINFORMATION_EXCEPTION = e;
    }

    public PurchaseInformation() {
        super(__getWsdlLocation(), PURCHASEINFORMATION_QNAME);
    }

    public PurchaseInformation(WebServiceFeature... features) {
        super(__getWsdlLocation(), PURCHASEINFORMATION_QNAME, features);
    }

    public PurchaseInformation(URL wsdlLocation) {
        super(wsdlLocation, PURCHASEINFORMATION_QNAME);
    }

    public PurchaseInformation(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PURCHASEINFORMATION_QNAME, features);
    }

    public PurchaseInformation(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PurchaseInformation(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PurchaseInformationSoap
     */
    @WebEndpoint(name = "PurchaseInformationSoap12")
    public PurchaseInformationSoap getPurchaseInformationSoap12() {
        return super.getPort(new QName("http://ws.back.anime.dmktsp.jp/", "PurchaseInformationSoap12"), PurchaseInformationSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PurchaseInformationSoap
     */
    @WebEndpoint(name = "PurchaseInformationSoap12")
    public PurchaseInformationSoap getPurchaseInformationSoap12(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.back.anime.dmktsp.jp/", "PurchaseInformationSoap12"), PurchaseInformationSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns PurchaseInformationSoap
     */
    @WebEndpoint(name = "PurchaseInformationSoap")
    public PurchaseInformationSoap getPurchaseInformationSoap() {
        return super.getPort(new QName("http://ws.back.anime.dmktsp.jp/", "PurchaseInformationSoap"), PurchaseInformationSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PurchaseInformationSoap
     */
    @WebEndpoint(name = "PurchaseInformationSoap")
    public PurchaseInformationSoap getPurchaseInformationSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.back.anime.dmktsp.jp/", "PurchaseInformationSoap"), PurchaseInformationSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PURCHASEINFORMATION_EXCEPTION!= null) {
            throw PURCHASEINFORMATION_EXCEPTION;
        }
        return PURCHASEINFORMATION_WSDL_LOCATION;
    }

}
