package my.uum;

/**
 * This class is for save  and edit the  user data
 */
public class User_list {

    static String Staff_id= "";

    static String name="";
    static String Mobile_TelNo= "";
    static String email= "";
    static String purpose="";
    static String Booking_Date="";
    static String Booking_Time="";

    static String ICNO= "";


   /* public User_list( String ICNO,String Staff_id,String name,String Mobile_TelNo,String email,String purpose,String Booking_Date, String Booking_Time)
    {
     this.ICNO=ICNO;
     this.Staff_id= Staff_id;
     this.name=name;
        this.Mobile_TelNo=Mobile_TelNo;
        this.email=email;
        this. purpose= purpose;
        this.Booking_Date=Booking_Date;
        this.Booking_Time=Booking_Time;
    }*/

    /**
     * This method is for return value ICNO
     * @return ICNO
     */
    public static String getICNO() {
        return ICNO;
    }
    /**
     * This method is for displaying String value ICNO
     */
    public static void setICNO(String ICNO) {
        User_list.ICNO = ICNO;
    }

    /**
     * This method is for return value staff id
     * @return staff id
     */
    public static String getStaff_id() {
        return Staff_id;
    }

    /**
     * This method is for edit/save String value staffid
     */
    public static void setStaff_id(String staff_id) {
        Staff_id = staff_id;
    }
    /**
     * This method is for return value name
     * @return name
     */

    public static String getName() {
        return name;
    }

    /**
     * This method is for edit/save String value name
     */
    public static void setName(String name) {
        User_list.name = name;
    }

    /**
     * This method is for return value handphone number
     * @return handphone number
     */
    public static String getMobile_TelNo() {
        return Mobile_TelNo;
    }
    /**
     * This method is for edit/save  String value handphone number
     */
    public static void setMobile_TelNo(String mobile_TelNo) {
        Mobile_TelNo = mobile_TelNo;
    }

    /**
     * This method is for return value email
     * @return email
     */
    public static String getEmail() {
        return email;
    }
    /**
     * This method is for edit/save  String value email
     */
    public static void setEmail(String email) {
        User_list.email = email;
    }

    /**
     * This method is for return value purpose
     * @return purpose
     */
    public static String getPurpose() {
        return purpose;
    }

    /**
     * This method is for edit/save  String value purpose of meeting
     */
    public static void setPurpose(String purpose) {
        User_list.purpose = purpose;
    }

    /**
     * This method is for return value Booking Date
     * @return BookingDate
     */
    public static String getBooking_Date() {
        return Booking_Date;
    }


    /**
     * This method is for edit/save  String value booking date
     */
    public static void setBooking_Date(String booking_Date) {
        Booking_Date = booking_Date;
    }

    /**
     * This method is for return value Booking Time
     * @return Booking Time
     */
    public static String getBooking_Time() {
        return Booking_Time;
    }
    /**
     * This method is for edit/save String value booking time
     */
    public static void setBooking_Time(String booking_Time) {
        Booking_Time = booking_Time;
    }


}
