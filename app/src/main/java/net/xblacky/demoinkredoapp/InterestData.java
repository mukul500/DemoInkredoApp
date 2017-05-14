package net.xblacky.demoinkredoapp;

/**
 * Created by xBlacky on 5/14/2017.
 */

public class InterestData {

    private int month;
    private float emi,interest;

    public InterestData(){

    }

    public void setMonth(int month)
    {
        this.month=month;
    }
    public void setEmi(float emi){
        this.emi=emi;
    }
    public void setTotalPayment(float interest){
        this.interest=interest;
    }

    public int getMonth(){
        return month;
    }
    public float getEmi(){
        return emi;
    }
    public float getTottalPayment(){
        return interest;
    }
}
