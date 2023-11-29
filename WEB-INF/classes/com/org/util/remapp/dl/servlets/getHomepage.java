package com.org.util.remapp.dl.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.time.*;
public class getHomepage extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
String username="";
String password="";
PrintWriter printwriter=null;
try
{
response.setContentType("text/html");
printwriter=response.getWriter();
username=request.getParameter("username");
password=request.getParameter("password");
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection=null;
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/hrdb","hr","hr");
PreparedStatement p;
p=connection.prepareStatement("select * from user where username=?;");
p.setString(1,username);
ResultSet result=p.executeQuery();
if(!result.next())
{ 
result.close();
p.close();
connection.close();
//unable to login form invalid username and password
return;
}
int uid=result.getInt("userid");
String uname=result.getString("username");
String upass=result.getString("password");
if(uname.equals(username)&&upass.equals(password))
{
printwriter.println("<!DOCTYPE HTML>");
printwriter.println("<HTML>");
printwriter.println("<head>");
printwriter.println("<meta charset='utf-8'>");
printwriter.println("<title>Reminder Application</title>");
printwriter.println("<script>");
printwriter.println("function cancelLogin()");
printwriter.println("{");
printwriter.println("var cc=document.getElementById('cancelForm');");
printwriter.println("cc.submit();");
printwriter.println("return true;");
printwriter.println("}");
printwriter.println("function setReminder()");
printwriter.println("{");
printwriter.println("var cc=document.getElementById('setReminderForm');");
printwriter.println("cc.submit();");
printwriter.println("return true;");
printwriter.println("}");
printwriter.println("</script>");
printwriter.println("</head>");
printwriter.println("<body>");
printwriter.println("<h2>Welcome to the Remainder Application,"+username+"</h2>");
printwriter.println("<h2>Today is Tuesday , 14th feb</h2>");
printwriter.println("<input type='button' value='Set Reminder' onclick='setReminder()'><br>");
printwriter.println("<input type='button' value='Modify Reminder'><br>");
printwriter.println("<input type='button' value='Disable Reminder'><br>");
printwriter.println("<input type='button' value='Delete Reminder'><br>");
printwriter.println("<input type='button' value='Enable Reminder'><br>");
printwriter.println("<input type='button' value='View Your Reminders'><br><br><br>");
printwriter.println("<input type='button' style='background-color:#6CB4EE;color:white;font-size:20px;border:none;padding:8px;border-radius:2px' value='Cancel' onclick='goBack()'>");
printwriter.println("<form action='/remapp/index.html' id='cancelForm'>");
printwriter.println("</form>");
printwriter.println("<form action='/remapp/setReminder.html' id='setReminderForm'>");
printwriter.println("</form>");
printwriter.println("</body>");
printwriter.println("</HTML>");
}
else
{
printwriter.println("<!DOCTYPE HTML>");
printwriter.println("<html>");
printwriter.println("<head>");
printwriter.println("<meta charset='utf-8'>");
printwriter.println("<title>CancelLogin</title>");
printwriter.println("</head>");
printwriter.println("<body>");
printwriter.println("<h1>Sorry,invalid username and password</h1>");
printwriter.println("</body>");
printwriter.println("</html>");
}
result.close();
p.close();
connection.close();
}catch(Exception exception)
{
printwriter.println("<!DOCTYPE HTML>");
printwriter.println("<html>");
printwriter.println("<head>");
printwriter.println("<meta charset='utf-8'>");
printwriter.println("<title>CancelLogin</title>");
printwriter.println("</head>");
printwriter.println("<body>");
printwriter.println("<h1>Some internal error occured</h1>");
printwriter.println("</body>");
printwriter.println("</html>");
}
}
}