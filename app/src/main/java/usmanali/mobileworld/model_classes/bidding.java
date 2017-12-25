package usmanali.mobileworld.model_classes;

/**
 * Created by shaban on 8/10/2017.
 */

public class bidding
 {
     String name;
     int biddingprice;
     String Date;
     String mobile_name;
     int mobile_id;

     public String getMobile_name() {
         return mobile_name;
     }

     public void setMobile_name(String mobile_name) {
         this.mobile_name = mobile_name;
     }

     public int getMobile_id() {
         return mobile_id;
     }

     public void setMobile_id(int mobile_id) {
         this.mobile_id = mobile_id;
     }
     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public int getBiddingprice() {
         return biddingprice;
     }

     public void setBiddingprice(int biddingprice) {
         this.biddingprice = biddingprice;
     }

     public String getDate() {
         return Date;
     }

     public void setDate(String date) {
         Date = date;
     }
 }
