package main.java;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public Date(){

    }
    public Date(int d, int month, int y, int h, int m){
        this.year = y;
        this.month = month;
        this.day = d;
        this.hour = h;
        this.minute = m;
    }

    public static Date getMin(){
        return new Date(1, 1, 1, 0, 0);
    }
    public static Date getMax(){
        return new Date(31, 12, 9999, 11, 59);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getMonth() {
        return month;
    }

    public void getMinute(int minute) {
        this.minute = minute;
    }

    public void getHour(int hour) {
        this.hour = hour;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }


    public String toString(){

        return (String.format("%02d", this.day) + "/" + String.format("%02d", this.month) + "/" + this.year + " " + String.format("%02d", this.hour) + ":" + String.format("%02d", this.minute));
    }

    public static Date parseDate(String a){
        if(a.length() <= 7){
            a = 0 + a;
        }
        return new Date(Integer.parseInt(a.substring(0,2)), Integer.parseInt(a.substring(2,4)), Integer.parseInt(a.substring(4)), 0, 0);
    }
    public static Date parseDate(int a){
        return parseDate(String.valueOf(a));
    }

    @Override
    public int compareTo(Date o) {
        int bandera = 0;
        if(year > o.year){
            bandera = 1;
        }else if (year < o.year){
            bandera = -1;
        }else if(month > o.month){
            bandera = 1;
        }else if(month < o.month){
            bandera = -1;
        }else if(day > o.day){
            bandera = 1;
        } else if(day < o.day){
            bandera = -1;
        } else if(hour > o.hour){
            bandera = 1;
        } else if(hour < o.hour){
            bandera = -1;
        } else if(minute < o.minute){
            bandera = -1;
        } else if(minute > o.minute){
            bandera = 1;
        }
        return bandera;
    }
}
