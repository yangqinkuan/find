/**
 * Copyright (C), 2018-2018,
 * FileName: ValidCode
 * Author:   Administrator
 * Date:     2018/12/5 23:20
 * Description: get valid code
 */
package com.ice.find.util.codegenerate;

import java.util.Random;

public class ValidCode {

    public static String get6Code(){
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<6;i++){
            sb.append(rand.nextInt(10));
        }

        return sb.toString();
    }

    public static void main(String[] args) {

    }
}