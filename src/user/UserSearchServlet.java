package user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UserSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String userName = request.getParameter("userName");

//        response.getWriter().write(getJSON(userName));
//
        response.getWriter().write(getJSON(userName));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String userName = request.getParameter("userName");

        response.getWriter().write(getJSON(userName));


    }

    public String getJSON(String userName){
        if(userName == null){
            userName = "";
        }
        StringBuffer result = new StringBuffer("");
        result.append("[");
        UserDAO DAO = new UserDAO();
        ArrayList<UserDTO> userList = DAO.search(userName);
        String requestName;
        int requestAge;
        String requestGender;
        String requestEmail;
        for(int i = 0;i<userList.size();i++){
            requestName = userList.get(i).getUserName();
            requestAge = userList.get(i).getUserAge();
            requestGender = userList.get(i).getUserGender();
            requestEmail = userList.get(i).getUserEmail();
//            result.append("[{\"value\": \"" + userList.get(i).getUserName() + "\"},");
//            result.append("{\"value\": \"" + userList.get(i).getUserAge() + "\"},");
//            result.append("{\"value\": \"" + userList.get(i).getUserGender() + "\"},");
//            result.append("{\"value\": \"" + userList.get(i).getUserEmail() + "\"}],");


            if(i == userList.size() - 1){
                result.append("{\n" +
                        "  \"userName\": \"" + requestName  +  "\",\n" +
                        "  \"userAge\": \"" + requestAge + "\",\n" +
                        "  \"userGender\": \"" + requestGender + "\",\n" +
                        "  \"userEmail\": \"" + requestEmail + "\"\n" +
                        "}");
            }
            else{
                result.append("{\n" +
                        "  \"userName\": \"" + requestName  +  "\",\n" +
                        "  \"userAge\": \"" + requestAge + "\",\n" +
                        "  \"userGender\": \"" + requestGender + "\",\n" +
                        "  \"userEmail\": \"" + requestEmail + "\"\n" +
                        "},");
            }
        }
        result.append("]");
        return result.toString();

    }
}
