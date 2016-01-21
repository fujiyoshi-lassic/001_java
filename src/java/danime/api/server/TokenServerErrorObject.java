/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danime.api.server;

/**
 * トークンサーバから受け取るエラー時のJSONデータ格納POJO
 * @author fujiyohi
 */
public class TokenServerErrorObject {
    public int error_code;
    public String error_message;

    public TokenServerErrorObject() {
    }
    
}
