package ru.bellintegrator.weather_service.controller;

//import com.caucho.hessian.server.HessianServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    Result getWeather(@RequestParam(value="city",defaultValue = "ufa") String city) throws Exception {
        Result oweather = dbService.getResult(city);
        if (oweather==null){
            throw new Exception("Weather not found");
        }
        return oweather;
    }
}
