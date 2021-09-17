package rs.raf.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name = "jelovnikServlet", value = "/jelovnik")
public class JelovnikServlet  extends HttpServlet {

    private String message;
    private String id;

    private boolean visited = false;

    private boolean svaPolja = true;

    public JelovnikServlet() {
        System.out.println("Constructor");
    }

    public void init() {
        System.out.println("init method");

        try {
            FileUtil.ucitajFajlove();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, ArrayList<String>> baza = Baza.getInstance().getMenu();

        ArrayList<String> ponedeljak = baza.get("ponedeljak");
        ArrayList<String> utorak = baza.get("utorak");
        ArrayList<String> sreda = baza.get("sreda");
        ArrayList<String> cetvrtak = baza.get("cetvrtak");
        ArrayList<String> petak = baza.get("petak");



        message = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Jelovnik</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form method=\"POST\" action=\"/jelovnik\">\n" +
                "    <label for=\"ponedeljak\">Ponedeljak:</label>\n" +
                "    <br>\n" +
                "    <input list=\"ponedeljak\" name=\"ponedeljak\">\n" +
                "    <datalist id=\"ponedeljak\">\n" +
                "        <option value=\" "+ ponedeljak.get(0) + "\">\n" +
                "        <option value=\" "+ ponedeljak.get(1) + "\">\n" +
                "        <option value=\""+ ponedeljak.get(2) + "\">\n" +
                "    </datalist>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <label for=\"utorak\">Utorak:</label>\n" +
                "    <br>\n" +
                "    <input list=\"utorak\" name=\"utorak\">\n" +
                "    <datalist id=\"utorak\">\n" +
                "        <option value=\" "+ utorak.get(0) + "\">\n" +
                "        <option value=\" "+ utorak.get(1) + "\">\n" +
                "        <option value=\""+ utorak.get(2) + "\">\n" +

                "    </datalist>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <label for=\"sreda\">Sreda:</label>\n" +
                "    <br>\n" +
                "    <input list=\"sreda\" name=\"sreda\">\n" +
                "    <datalist id=\"sreda\">\n" +
                "        <option value=\" "+ sreda.get(0) + "\">\n" +
                "        <option value=\" "+ sreda.get(1) + "\">\n" +
                "        <option value=\""+ sreda.get(2) + "\">\n" +
                "    </datalist>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <label for=\"cetvrtak\">Cetvrtak:</label>\n" +
                "    <br>\n" +
                "    <input list=\"cetvrtak\" name=\"cetvrtak\">\n" +
                "    <datalist id=\"cetvrtak\">\n" +
                "        <option value=\" "+ cetvrtak.get(0) + "\">\n" +
                "        <option value=\" "+ cetvrtak.get(1) + "\">\n" +
                "        <option value=\""+ cetvrtak.get(2) + "\">\n" +
                "    </datalist>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <label for=\"petak\">Petak:</label>\n" +
                "    <br>\n" +
                "    <input list=\"petak\" name=\"petak\">\n" +
                "    <datalist id=\"petak\">\n" +
                "        <option value=\" "+ petak.get(0) + "\">\n" +
                "        <option value=\" "+ petak.get(1) + "\">\n" +
                "        <option value=\""+ petak.get(2) + "\">\n" +
                "    </datalist>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "\n" +
                "\n" +
                "    <input type=\"submit\" name=\"submit\" value=\"Potvrda\"/>\n" +
                "    <br>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n";
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service method");
        super.service(req, resp);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("doGet method");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        id = request.getSession().getId();

        if(Baza.getInstance().getIds().contains(id)){
            ArrayList<String> izabrano = Baza.getInstance().getChosenForPerson().get(id);
            String res = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<body>\n" +
                    "\n" +
                    "<p>Ponedeljak: " + izabrano.get(0) + "</p>\n" +
                    "<p>Utorak: " + izabrano.get(1) + "</p>\n" +
                    "<p>Sreda: " + izabrano.get(2) + "</p>\n" +
                    "<p>Cetvrtak: " + izabrano.get(3) + "</p>\n" +
                    "<p>Petak: " + izabrano.get(4) + "</p>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>";
            out.println(res);
        }else {
            out.println(message);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(visited){
            svaPolja = true;
            if (request.getParameter("ponedeljak") == null || request.getParameter("ponedeljak").equals("")) {
                svaPolja = false;
            }else{
                request.getSession().setAttribute("ponedeljak", request.getParameter("ponedeljak"));
            }

            if (request.getParameter("utorak") == null || request.getParameter("utorak").equals("")) {
                svaPolja = false;
            }else{
                request.getSession().setAttribute("utorak", request.getParameter("utorak"));
            }

            if (request.getParameter("sreda") == null || request.getParameter("sreda").equals("")) {
                svaPolja = false;
            }else{
                request.getSession().setAttribute("sreda", request.getParameter("sreda"));
            }

            if (request.getParameter("cetvrtak") == null || request.getParameter("cetvrtak").equals("")) {
                svaPolja = false;
            }else{
                request.getSession().setAttribute("cetvrtak", request.getParameter("cetvrtak"));
            }

            if (request.getParameter("petak") == null || request.getParameter("petak").equals("")) {
                svaPolja = false;
            }else{
                request.getSession().setAttribute("petak", request.getParameter("petak"));
            }

            if(!svaPolja){
                response.getOutputStream().println("Sva polja su obavezna!");
                response.setStatus(403);
                return;
            }else{
                ArrayList<String> jela = new ArrayList<>();
                jela.add((String) request.getSession().getAttribute("ponedeljak"));
                jela.add((String) request.getSession().getAttribute("utorak"));
                jela.add((String) request.getSession().getAttribute("sreda"));
                jela.add((String) request.getSession().getAttribute("cetvrtak"));
                jela.add((String) request.getSession().getAttribute("petak"));

                Baza.getInstance().getIds().add(request.getSession().getId());
                Baza.getInstance().getChosenForPerson().put(request.getSession().getId(), jela);
            }

        }
        visited = true;
        response.sendRedirect("/jelovnik");
    }

    public void destroy() {
        System.out.println("destroy method");
    }
}
