package main.java;

public class Date implements Comparable<Date> {
    private int year;
    private int month;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
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

        return (this.day + "/" + this.month + "/" + this.year);
    }

    @Override
    public int compareTo(Date o) {
        return 0;
    }
}
