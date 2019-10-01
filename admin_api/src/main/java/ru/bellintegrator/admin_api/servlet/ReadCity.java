package ru.bellintegrator.admin_api.servlet;

import ru.bellintegrator.admin_api.jms.JMSSender;
//import org.ivd.weather.error.exception.WeatherException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.weatherparser.CityAndRegion;

//import javax.inject.Inject;
import javax.inject.Inject;
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
        System.out.println(req.hashCode());
        CityAndRegion location;
        //PrintWriter writer = res.getWriter();

        String city = req.getParameter("city");
        String region = req.getParameter("region");
        /*TODO
        сделать проверку названия города/region на правильность, передать в if
        * */
        location = new CityAndRegion(city, region);
        if (location.getCity() == null || location.getRegion() == null) {
            req.setAttribute("errorMessage", "City or region cannot be empty! Return and enter full location info");
            req.getRequestDispatcher("/error.jsp").forward(req, res);
            LOG.info("ReadCity servlet has not read location info");
        } else {
            if (location.getCity().trim().isEmpty() || location.getRegion().trim().isEmpty()) {
                req.setAttribute("errorMessage", "City or region cannot be empty! Return and enter full location info");
                req.getRequestDispatcher("/error.jsp").forward(req, res);
                LOG.info("ReadCity servlet has not received location info");
            } else {
                //writer.println("Weather will be downloaded for " + location.toString());
                LOG.info("ReadCity servlet executed, location info for weather downloading = {}", location.toString());
                try {
                    jmssender.send(location);
                    req.getRequestDispatcher("/index.jsp").forward(req, res);
                } catch (Exception e) {
                    throw new ServletException(e.getMessage());
                }
                LOG.info("RequestCity sent location info to JMS service: {}", location.toString());
            }
        }
    }
}




















































