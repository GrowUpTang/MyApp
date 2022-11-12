package com.xaaccp.myapp;

import java.util.List;

public class Demo {

    /**
     * code : 1
     * msg : ok
     * time : 1620783810
     * data : {"info":{"integral_worth":"5.00","integral":"50000"},"list":[{"id":2,"date":"2021-05-11","type":"线下充值","affect_integral":"50000.00"}]}
     */

    private int code;
    private String msg;
    private String time;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * info : {"integral_worth":"5.00","integral":"50000"}
         * list : [{"id":2,"date":"2021-05-11","type":"线下充值","affect_integral":"50000.00"}]
         */

        private InfoBean info;
        private List<ListBean> list;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class InfoBean {
            /**
             * integral_worth : 5.00
             * integral : 50000
             */

            private String integral_worth;
            private String integral;

            public String getIntegral_worth() {
                return integral_worth;
            }

            public void setIntegral_worth(String integral_worth) {
                this.integral_worth = integral_worth;
            }

            public String getIntegral() {
                return integral;
            }

            public void setIntegral(String integral) {
                this.integral = integral;
            }
        }

        public static class ListBean {
            /**
             * id : 2
             * date : 2021-05-11
             * type : 线下充值
             * affect_integral : 50000.00
             */

            private int id;
            private String date;
            private String type;
            private String affect_integral;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAffect_integral() {
                return affect_integral;
            }

            public void setAffect_integral(String affect_integral) {
                this.affect_integral = affect_integral;
            }
        }
    }
}
