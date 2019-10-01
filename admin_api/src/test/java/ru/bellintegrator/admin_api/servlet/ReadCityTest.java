package ru.bellintegrator.admin_api.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.bellintegrator.admin_api.jms.JMSSender;
import ru.bellintegrator.weatherparser.CityAndRegion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ReadCityTest {
    private String attribute = "errorMessage";
    private String forwardUrl = "/error.jsp";

    @Mock
    private JMSSender sender;

    @InjectMocks
    private ReadCity servlet;

    /**
     * Проверка при пустых значениях
     *
     * @throws ServletException исключение метода doGet
     * @throws IOException исключение метода doGet
     */
    @Test
    public void cityAndRegionNullTest() throws ServletException, IOException {

        CityAndRegion city = new CityAndRegion(null, null);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("city")).thenReturn(city.getCity());
        when(request.getParameter("region")).thenReturn(city.getRegion());
        when(request.getRequestDispatcher(forwardUrl)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verifyZeroInteractions(sender);
        verify(request, atLeast(1)).setAttribute(attribute, "City or region cannot be empty! Return and enter full location info");
        verify(request, atLeast(1)).getRequestDispatcher(forwardUrl);
        verify(dispatcher, atLeast(1)).forward(request, response);
    }

    /**
     * Проверка при введённых пробелах
     *
     * @throws ServletException иссключение метода doGet
     * @throws IOException иссключение метода doGet
     */
    @Test
    public void cityAndRegionProbelTest() throws ServletException, IOException {

        CityAndRegion city = new CityAndRegion("  ", " ");

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("city")).thenReturn(city.getCity());
        when(request.getParameter("region")).thenReturn(city.getRegion());
        when(request.getRequestDispatcher(forwardUrl)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verifyZeroInteractions(sender);
        verify(request, atLeast(1)).setAttribute(attribute, "City or region cannot be empty! Return and enter full location info");
        verify(request, atLeast(1)).getRequestDispatcher(forwardUrl);
        verify(dispatcher, atLeast(1)).forward(request, response);
    }
}
