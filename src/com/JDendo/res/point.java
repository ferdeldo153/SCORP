package com.JDendo.res;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author home
 */
public class point {
    int x, y;
    double value;
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public double getValue() {
        return this.value;
    }
    public point(int p1, int p2, double val) {
        this.x = p1;
        this.y = p2;
        this.value = val;

    }
}
