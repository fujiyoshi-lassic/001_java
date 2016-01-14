/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsc.server;

/**
 *
 * @author fujiyohi
 */
public class viewingClass {
    private String customerId;
    private String authPass;
    private String userId;
    private String keyId;

    public viewingClass(){
        
    }



    /*
    * 
    * 自動生成(getter,setter)
    *
    */
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setAuthPass(String authPass) {
        this.authPass = authPass;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAuthPass() {
        return authPass;
    }

    public String getUserId() {
        return userId;
    }

    public String getKeyId() {
        return keyId;
    }
    
}
