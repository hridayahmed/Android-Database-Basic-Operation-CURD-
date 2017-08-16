package com.hridha.Database_Basic_Operation;

/**
 * Created by hrida on 8/1/2017.
 */

public class Config {
    //Address of our scripts of the CRUD
    public static final String URL_ADD="http://websitebangladesh.com/new/addEmp.php";
    public static final String URL_GET_ALL = "http://websitebangladesh.com/new/getAllEmp.php";
    public static final String URL_GET_EMP = "http://websitebangladesh.com/new/getEmp.php?username=";
    public static final String URL_UPDATE_EMP = "http://websitebangladesh.com/new/updateEmp.php";
    public static final String URL_DELETE_EMP = "http://websitebangladesh.com/new/deleteEmp.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAME = "name";
    public static final String KEY_EMP_USERNAME = "username";
    public static final String KEY_EMP_PHONE = "phone";
    public static final String KEY_EMP_PASSWORD = "password";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_PHONE = "phone";
    public static final String TAG_PASSWORD = "password";

    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";
}
