package grades.util;

public class Date {

    private int day;
    private int month;
    private int year;

    public Date(){
        this(0,0,0);
    }

    public Date(int day, int month, int year) {

        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void set(int day, int month, int year) {

        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDate(String date) {
        String str[] = date.split("/");
        day = Integer.parseInt(str[0]);
        month = Integer.parseInt(str[1]);
        year = Integer.parseInt(str[2]);
    }

    @Override
    public String toString() {
        return ((day < 10) ? "0" : "") + day + "/" + ((month < 10) ? "0" : "") + month + "/" + year;
    }
}
