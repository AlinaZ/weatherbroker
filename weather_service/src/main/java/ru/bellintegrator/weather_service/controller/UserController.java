package ru.bellintegrator.weather_service.controller;

//import com.caucho.hessian.server.HessianServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.weather_service.exception.ErrorResponse;
import ru.bellintegrator.weather_service.exception.ExceptionsHandler;
import ru.bellintegrator.weather_service.exception.WeatherNotFoundException;
import ru.bellintegrator.weather_service.service_interfaces.DBService;
import ru.bellintegrator.weatherparser.Result;


@RestController
public class UserController {

    private final DBService dbService;

    @Autowired
    public UserController(DBService dbService){
        this.dbService=dbService;
    }


    /**
     * Возвращает погоду (наиболее актуальное наблюдение и прогноз на 10 дней от него из базы для этого города)
     * по параметрам city
     *
     * @return
     */

    @GetMapping(value = "/user", produces = "application/json")
    public @ResponseBody
    Result getWeather(@RequestParam(value="city",defaultValue = "ufa") String city) throws WeatherNotFoundException {
        Result oweather = dbService.getResult(city);
        if (oweather==null){
            throw new WeatherNotFoundException("Weather not found in DATABASE!");
        }
        return oweather;
    }

    /**
     * Обработчик ошибок о том, что актуальные данные о погоде не найдены в БД
     *
     * @param ex ошибка класса NotFoundException
     * @return строку с сообщением об ошибке
     */

    private final Logger log = LoggerFactory.getLogger(ExceptionsHandler.class);

    @ExceptionHandler({WeatherNotFoundException.class})
    protected @ResponseBody
    ResponseEntity<?> handleCustomExceptions(RuntimeException e) {
        log.error(e.getMessage(), e.getCause());
        return new ResponseEntity<>(new ErrorResponse("Weather for this location not found in the database"+e.getMessage()), HttpStatus.NOT_FOUND);
    }


}
