/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author Amr Ashraf
 */
public class TableWriting {
    int number;
    String name;
    String order;
    double paid;

    public TableWriting(int number, String name, String order, double paid) {
        this.number = number;
        this.name = name;
        this.order = order;
        this.paid = paid;
    }
    

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }
    
    
    
}
