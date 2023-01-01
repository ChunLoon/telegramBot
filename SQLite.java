package my.uum;

import java.io.File;
import java.sql.*;

/**
 * This class is for sqlite database
 */
public class SQLite {
    private static Connection connection;

    private static Statement statement;

    static String getroomId ="";
    /**
     * This method is for insert data into database
     * @param ICNO   String
     * @param Staff_id String
     * @param name String
     * @param Mobile_TelNo String
     * @param email String
     * @param purpose String
     * @param booking_Date String
     * @param booking_Time String
     */
    public static void insertSS(String ICNO,String Staff_id,String name,String Mobile_TelNo,String email,String purpose,String booking_Date,String booking_Time) {

        String sql = "INSERT INTO Booking(ICNO,staff_id,name,Mobile_TelNo, email, Purpose, Booking_Date ,Booking_Time) VALUES(?,?,?,?,?,?,?,?)";



        try{


            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, (ICNO));
            pstmt.setString(2, (Staff_id));
            pstmt.setString(3,name);
            pstmt.setString(4, (Mobile_TelNo));
            pstmt.setString(5,email);
            pstmt.setString(6,purpose);
            pstmt.setString(7,booking_Date);
            pstmt.setString(8,booking_Time);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

public static void roomIc(){
    //String sql="UPDATE Room_List SET ICNO = "+ User_list.getICNO() + " WHERE Room_id= ?";
    String sql="UPDATE Room_List SET ICNO = "+"'+" +User_list.getICNO()+"'"  + " WHERE Room_id= ?";

    try{
        PreparedStatement pstmt1 = connection.prepareStatement(sql);

        pstmt1.setString(1,getroomId);
        pstmt1.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

    /**
     * This method is for delete the data in database when user need to cancel booking
     * @param msg ICNO
     * @return cancel successfully or not
     */

public static String cancel(String msg) {

    String responseCancel = "";
    String sql = "DELETE FROM Booking WHERE ICNO= "+ "'"+msg+"'" ; //+ msg
    String sql2= "SELECT*FROM Booking WHERE ICNO ='"+msg+"'" ;


    try {


        Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(sql2);
         String Icno =rs.getString("ICNO");

         int cancel = stmt.executeUpdate(sql);




   if (cancel==1) {



       String sql1 = "UPDATE Room_List SET ICNO = "+ '?' +"WHERE ICNO = "+ "'"+Icno +"'";
       PreparedStatement pstmt1 = connection.prepareStatement(sql1);
       pstmt1.setString(1,null);
       pstmt1.executeUpdate();

        responseCancel = "Cancel Successfully";


    } else {

        responseCancel = "Not Available";


    }


    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return  responseCancel;
}


    /**
     *Check available room
     * @return
     */

  public static String displayAvailbleRoom() {

        //SELECT *FROM Room_List WHERE Booking_Date LIKE '%13/12/2022%'
      //INSERT INTO Room_List( Room_Id, Room_Description, Maximum_Capacity , Booking_Date , Booking_Time) VALUES(20,'errr',20,'13/12/2022','10')
    //1  String sql = "SELECT *FROM Room_List WHERE Booking_Date LIKE '%" + booking_date + "%'";

      String sql = "SELECT *FROM Room_List WHERE ICNO IS NULL ";
       StringBuilder stringBuilder=new StringBuilder();
      String responseFinal=null;
     String response = null;

      try {

          Statement stmt = connection.createStatement();
          ResultSet rs = stmt.executeQuery(sql);



          /*if (rs.getString("Room_id")==null){

              responseFinal = "\nRoom Not Available";
          } else  {*/

          while (rs.next()) {


              response = ( "\n"+
                   "RoomID:  "+   rs.getString("Room_id") + "\n" +
                      "Room_Description:  "+  rs.getString("Room_Description") + "\n" +
                      "Maximum_capacity:  "+   rs.getInt("Maximum_capacity") + "\n"
                      //+"Booking_Date:  "+ rs.getString("Booking_Date") + "\n" +
                      //"Booking_Time:  "+ rs.getString("Booking_Time") + "\n "
                      );

              if(response==null)
                  response = "\nRoom Not Available";

              stringBuilder.append(response);


          }
              responseFinal=stringBuilder.toString();



      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }

      return responseFinal;
  }








    public static String displayListAll() {

        String sql = "SELECT *FROM Booking";

        StringBuilder stringBuilder=new StringBuilder();
        String responseFinal=null;
        String response = null;
        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


            if (rs.getString("ICNO")==null){
                responseFinal="\n Not available";
            }
            else {



                while (rs.next()) {

                    String send =rs.getString("ICNO");
                    String output =displayRoom(send);

                    response = ("\n" +
                            "Staff ID:  " + rs.getString("staff_id") + "\n" +
                            "Name:  " + rs.getString("name") + "\n" +
                            "Booking Date:  " + rs.getString("Booking_Date") + "\n" +
                            "Booking Time:  " + rs.getString("Booking_Time") + "\n"+
                            output+"\n");



                    stringBuilder.append(response);


                }
                responseFinal = stringBuilder.toString();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
return responseFinal;
    }

    public static String displayRoom(String ICNO){


        String sql = "SELECT *FROM Room_List WHERE ICNO ='" + ICNO + "'";
        StringBuilder stringBuilder=new StringBuilder();
        String responseFinal=null;
        String response = null;
        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);



                while (rs.next()) {



                    response = (
                            "RoomID:  "+   rs.getString("Room_id") + "\n" +
                            "Room_Description:  "+  rs.getString("Room_Description") + "\n" +
                            "Maximum_capacity:  "+   rs.getInt("Maximum_capacity") + "\n" );
                         //  + "Booking_Time:  "+ rs.getString("Booking_Time") + "\n ");

                    stringBuilder.append(response);

                }
                responseFinal=stringBuilder.toString();



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return responseFinal;
    }


    /**
     *
     * @param msg
     * @return
     */



public static String bookRoom(String msg){

    String sql = "SELECT *FROM Room_List WHERE Room_id = " + msg + " and ICNO IS NULL";

    String response=null;


    try {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);


        if (rs.getString("Room_id") == null) {

            response = "Room id not available.Please insert again.";
        }

        else  {


            response ="You are  make a booking with\n\n"+
                    "RoomID:  "+   rs.getString("Room_id") + "\n" +
                    "Room_Description:  "+  rs.getString("Room_Description") + "\n" +
                    "Maximum_capacity:  "+   rs.getInt("Maximum_capacity") + "\n" ;

            getroomId= rs.getString("Room_id");

        }

    }catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return response;
}









    /**
     * This method is for connect bot with sqlite
     */
    public static void setConnection() {

        connection = null;

        try {

            File file = new File("database.db");

            if (!file.exists()) {

                file.createNewFile();

            }

            String url = "jdbc:sqlite:" + file.getPath();

            connection = DriverManager.getConnection(url);

            statement = connection.createStatement();

            System.out.println("[SYSTEM] Connected to database");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }






    /* public static void disconnect() {
        if (connection != null) {

            try {

                connection.close();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void onUpdate(String sql) {
        if (statement != null) {

            try {

                statement.execute(sql);

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static ResultSet onQuery(String sql) {

        if (statement != null) {

            try {

                return statement.executeQuery(sql);

            } catch (SQLException exception) {
                exception.printStackTrace();
            }

        }
        return null;

    }*/



}

