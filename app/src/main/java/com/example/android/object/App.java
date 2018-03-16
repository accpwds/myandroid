package com.example.android.object;

/**
 * Created by Administrator on 2018/3/16.
 */

public class App {

    private String resultcode;

    private String reason;

    public class Result {

        private String province;

        private String city;

        private String areacode;

        private String zip;

        private String company;

        private String card;

        public String getProvince(){

            return province;
        }

        public void setProvince(String province){

            this.province = province;
        }

        public String getCity(){

            return city;
        }

        public void setCity(String city){

            this.city = city;
        }

        public String getAreacode(){

            return areacode;
        }

        public void setAreacode(String areacode){

            this.areacode = areacode;
        }

        public String getZip(){

            return zip;
        }

        public void setZip(String zip){

            this.zip = zip;
        }

        public String getCompany(){

            return company;
        }

        public void setCompany(String company){

            this.company = company;
        }

        public String getCard(){

            return card;
        }

        public void setCard(String card){

            this.card = card;
        }
    }

    public com.example.android.object.Result getResult(){

        return new com.example.android.object.Result();
    }



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
}
