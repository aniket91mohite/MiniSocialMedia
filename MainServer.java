package com.company;

import java.sql.*;
import java.net.*;
import java.io.*;

public class Main
{
    public static void main(String a[]) throws Exception
    {
        System.out.println("Server Running");

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "DarkKnight532";
        String pass = "DarkKnight";

        boolean permission = false;

        ServerSocket SS = new ServerSocket(1080);
        Socket S = SS.accept();
        DataInputStream Din = new DataInputStream(S.getInputStream());
        DataOutputStream Dout = new DataOutputStream(S.getOutputStream());

            if (Din.readBoolean()) {

                String uName = Din.readUTF();
                String pWord = Din.readUTF();
                System.out.println(uName + " " + pWord);
                try {
                    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                    Connection con = DriverManager.getConnection(url, user, pass);
                    Statement st = con.createStatement();

                    String sql = "SELECT username, password FROM USERINFO" +
                            " where username = '" + uName + "' and password = '" + pWord + "'";
                    ResultSet rs = st.executeQuery(sql);

                    if (rs.next())
                        permission = true;

                    Dout.writeBoolean(permission);

                    con.close();
                    rs.close();
                } catch (Exception ex){
                    System.err.println(ex);
                }

            } else {

                String first_name = Din.readUTF();
                String last_name = Din.readUTF();
                String email_id = Din.readUTF();
                String contact_no = Din.readUTF();
                String username = Din.readUTF();
                String password = Din.readUTF();
                String cpassword = Din.readUTF();
                String birthdate = Din.readUTF();
                String gender = Din.readUTF();
                String about = Din.readUTF();
                String dateOfSignUp = Din.readUTF();
                int userid = Din.readInt();
                System.out.println(userid);


                try {
                    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                    Connection con = DriverManager.getConnection(url, user, pass);
                    Statement st = con.createStatement();

                    String sql = "INSERT INTO USERINFO VALUES('" + first_name + "', '" + last_name + "', '" + email_id + "', '" + contact_no + "', " +
                            "'" + username + "', " + userid + ", '" + password + "', '" + birthdate + "', '" + about + "', '" + gender + "'," +
                            " '" + dateOfSignUp + "')";

                    int p = st.executeUpdate(sql);

                    if (p == 1)
                        permission = true;

                    Dout.writeBoolean(permission);

                    con.close();
                } catch (Exception ex) {
                    Dout.writeBoolean(permission);
                    System.err.println(ex);
            }
        }
    }
}