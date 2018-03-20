package com.example.android.object;

/**
 * Created by Administrator on 2018/3/16.
 */

public class App {

    private String resultcode;

    private String reason;

    private Result result;

    private String error_code;

    public void setResultcode(String resultcode){

        this.resultcode = resultcode;
    }

    public String getResultcode(){

        return resultcode;
    }

    public void setReason(String reason){

        this.reason = reason;
    }

    public String getReason(){

        return reason;
    }

    public void setError_code(String error_code){

        this.error_code = error_code;
    }

    public String getError_code(){

        return error_code;
    }

    public Result getResult(){

        return result;
    }

    public void setResult(Result result){

        this.result = result;
    }
}
