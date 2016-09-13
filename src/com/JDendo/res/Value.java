/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JDendo.res;

import java.util.LinkedList;

/**
 *
 * @author home
 */
public class Value {
    String name;
    LinkedList value;
    public String getName() {
        return this.name;
    }
    public LinkedList getValue() {
        return this.value;
    }
    public Value(String n, LinkedList au) {
        this.name = n;
        this.value =au;
    }
   
    public void add(double x) {
        this.value.add(x);

    }
}
