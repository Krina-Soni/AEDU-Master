package AEDU.Utilities;

import AEDU.Testcases.Basecase;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import java.sql.*;
import java.util.ArrayList;


public class DatabaseFunctions extends Basecase {

    Connection conn = null;
    //Docker
//    String url = "jdbc:mysql://localhost:6603/";
    //localDB
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "aedu_school1";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "lamp";
    String password = "lamp";
    Statement statement;
    ResultSet queryRs;
    ResultSet Qcount;

    public static ExtentTest test;

    public DatabaseFunctions(ExtentTest test)
    {
       this.test=test;
    }

    public Connection connect() {
        try {
            //Class.forName(driver).newInstance();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url + dbName, userName, password);
            test.log(Status.INFO,"Sucessfully connected to Databse"+dbName);
            System.out.println("connected to database successfully");

        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL,"Unable to Provide Connection With Database" +dbName);
        }
        return conn;
    }

    public void printUserNamePassword() throws SQLException {
        conn = connect();
        statement = conn.createStatement();
        String query = "SELECT * from admin";
        queryRs = statement.executeQuery(query);
        while (queryRs.next()) {
            System.out.println("Title is : " + queryRs.getString("id"));
            System.out.println("Title is : " + queryRs.getString("username"));
            System.out.println("Title is : " + queryRs.getString("role"));
            System.out.println("Title is : " + queryRs.getString("email"));
            System.out.println("Title is : " + queryRs.getString("password"));
        }
    }
    //Print all students in console which are active and in Current session: 2020-21
    public Object[] printAllStudents() throws SQLException {
        conn = connect();
        statement= conn.createStatement();
        String students = "SELECT student_session.id, student_session.session_id, students.firstname, students.lastname, students.is_active, students.is_inactive, classes.class, students.admission_no FROM `student_session` INNER JOIN students ON student_session.student_id = students.id INNER JOIN classes ON student_session.class_id=classes.id WHERE student_session.session_id='15' AND students.is_inactive='no' ORDER BY `students`.`admission_no` ASC";
        queryRs= statement.executeQuery(students);
        ArrayList listNames = new ArrayList();

        while (queryRs.next()) {
            String s = null;
            listNames.add(queryRs.getString("students.admission_no"));

        }
        return listNames.toArray();
    }
}


