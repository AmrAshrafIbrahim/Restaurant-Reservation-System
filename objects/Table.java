/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author kamal alexander
 */
public class Table {
    int number;
    int seats;
    boolean smoke;
    
    
    
    


    public Table(int number, int seats, boolean smoke) {
        this.number = number;
        this.seats = seats;
        this.smoke = smoke;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isSmoke() {
        return smoke;
    }

    public void setSmoke(boolean smoke) {
        this.smoke = smoke;
    }
    
    //
//    public Table(int number, int seats, boolean smoke) {
//        this.number =Integer.toString(number);
//        this.seats = Integer.toString(seats);
//        this.smoke = smoke;
//    }
//
//    public String getNumber() {
//        return number;
//    }
//
//    public void setNumber(String number) {
//        this.number = number;
//    }
//
//    public String getSeats() {
//        return seats;
//    }
//
//    public void setSeats(String seats) {
//        this.seats = seats;
//    }
//
//    public boolean isSmoke() {
//        return smoke;
//    }
//
//    public void setSmoke(boolean smoke) {
//        this.smoke = smoke;
//    }
}
