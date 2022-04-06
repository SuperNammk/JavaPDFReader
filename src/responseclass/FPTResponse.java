/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responseclass;

/**
 *
 * @author hnam1
 */
public class FPTResponse {

    private String async;
    private Integer error;
    private String message;
    private String request_id;

    public String getAsync() {
        return async;
    }

    public void setAsync(String async) {
        this.async = async;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return request_id;
    }

    public void setRequestId(String requestId) {
        this.request_id = requestId;
    }

}
