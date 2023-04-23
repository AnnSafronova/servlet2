import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import logic.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@WebServlet(name = "/calc")
public class Calc extends HttpServlet {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();




    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        }catch (Exception e){
            System.out.println("Error");
        }


        PrintWriter out = response.getWriter();
        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);

        request.setCharacterEncoding("UTF-8");


        double NumA = jobj.get("a").getAsDouble();
        double NumB = jobj.get("b").getAsDouble();
        String math = jobj.get("math").getAsString();


        double result = 0;

        if("+".equals(math)){
            result = NumA + NumB;
        }else if("-".equals(math)){
            result = NumA - NumB;
        }else if("*".equals(math)){
            result = NumA * NumB;
        }else if("/".equals(math)){
            result = NumA / NumB;
        }

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        out.print("result:" + result);

    }

}