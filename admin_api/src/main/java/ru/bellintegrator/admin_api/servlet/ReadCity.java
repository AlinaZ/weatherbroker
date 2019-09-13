package ru.bellintegrator.admin_api.servlet;

import ru.bellintegrator.admin_api.jms.JMSSender;
//import org.ivd.weather.error.exception.WeatherException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.admin_api.jms.JMSSender;

//import javax.inject.Inject;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Получение названия города из запроса
 */
@WebServlet(urlPatterns = "/admin")
public class ReadCity extends HttpServlet {
    private final Logger LOG = LoggerFactory.getLogger(ReadCity.class);

    @Inject
    private JMSSender jmssender;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        /*RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, res);*/
        PrintWriter writer = res.getWriter();

        String city = req.getParameter("city");
        /*TODO
        сделать проверку названия города на правильность, передать в if
        * */
        if (city.trim().isEmpty()){
            writer.println("City cannot be empty! Return and enter city name");
        }
        else {
            writer.println("Weather will be downloaded for "+city);
        }
        LOG.info("ReadCity servlet executed, city name for weather downloading = {}", city);

        if (!city.isEmpty()) {
            try {
                jmssender.send(city);
            } catch (Exception e) {
                throw new ServletException(e.getMessage());
            }
            LOG.info("RequestCity sent city name to JMS service: {}", city);
        }

    }
}