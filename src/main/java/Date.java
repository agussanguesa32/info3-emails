package main.java;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int hour;
    private int minute;

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

    private int day;


    public String toString(){

        return (String.format("%02d", this.day) + "/" + String.format("%02d", this.month) + "/" + this.year + " " + String.format("%02d", this.hour) + ":" + String.format("%02d", this.minute));
    }

    @Override
    public int compareTo(Date o) {
        int bandera = 0;
        if(year > o.year){
            bandera = 1;
        }else if (month > o.month){
            bandera = 1;
        }else if(day > o.day){
            bandera = 1;
        }else if(hour > o.hour){
            bandera = 1;
        }else if(minute > o.minute){
            bandera = 1;
        }
        return bandera;
    }
}
