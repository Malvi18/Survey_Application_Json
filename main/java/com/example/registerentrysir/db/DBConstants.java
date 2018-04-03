package com.example.registerentrysir.db;

/**
 * Created by Dell on 05-01-2018.
 */

public interface DBConstants {
    String DBNAME="register";
    String TABLE_NAME="tablename";
    int VERSION=1;

    String COL_ID="ID";
    String COL_NAME="USERNAME";
    String COL_PASS="PASSWORD";


    String CREATE="create table "+TABLE_NAME
            +" ("+COL_ID+" integer primary key autoincrement, "
            +COL_NAME+" text, "
            +COL_PASS+" text)";

    //String DROP1="drop table if exists"


}
