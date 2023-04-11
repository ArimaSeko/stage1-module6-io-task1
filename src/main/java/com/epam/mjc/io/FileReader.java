package com.epam.mjc.io;

import java.io.*;


public class FileReader {

    public Profile getDataFromFile(File file) {
        int Age=0;
        long Phone=0;
        String Name;
        String Email;
        char[] charArray = null;
        try (FileInputStream fis = new FileInputStream(file)) {
            charArray = new char[(int) file.length()];
            int ch = fis.read();
            int i = 0;
            while (ch != -1) {
                charArray[i] = (char) ch;
                ch = fis.read();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data ="";
        if(charArray!=null)data = String.valueOf(charArray);
        String strAge = cut(data, "Age: ");
        Email = cut(data, "Email: ");
        String strPhone = cut(data, "Phone: ");
        Name = cut(data, "Name: ");
        try{
        Age = Integer.parseInt(strAge.trim());
        Phone = Long.parseLong(strPhone.trim());}
        catch (NumberFormatException e){e.printStackTrace();}
        return new Profile(Name,Age,Email,Phone);
    }

    public static String cut(String text, String find) {
        String answer = "";
        int start = text.indexOf(find);
        text = text.substring(start);
        start = text.indexOf(find);
        int end = text.indexOf("\n");
        if (start > -1)answer = text.substring(start + find.length(), end);
        return answer;
    }
}
