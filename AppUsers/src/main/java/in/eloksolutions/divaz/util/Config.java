package in.eloksolutions.divaz.util;

/**
 * Created by welcome on 4/13/2018.
 */

public class Config {
    public  static String SERVER_URL =  "http://52.91.45.77/";
    public  static String APP_PREFERENCES="divaz";
    public static  String AWS_URl="https://s3.console.aws.amazon.com/s3/buckets/divaz/";
    public static  String IMG_AWS="https://s3.amazonaws.com/divaz/";
    public  static String userId;
    public  static String firstName;
    public  static String lastName;
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        userId = userId;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        lastName = lastName;
    }
}
