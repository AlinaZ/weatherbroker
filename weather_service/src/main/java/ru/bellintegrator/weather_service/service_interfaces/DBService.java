package ru.bellintegrator.weather_service.service_interfaces;


import ru.bellintegrator.exception.CustomException;
import ru.bellintegrator.weatherparser.Result;

//import javax.transaction.Transactional;


public interface DBService {

    /**
     * Получить данные о погоде для города из БД
     *
     * @param city название города
     * @return данные о погоде
     */
    Result getResult(String city);

    /**
     * Сохранить данные о погоде в БД
     *
     * @param result данные о погоде для города, сохраненные админом
     */
    void saveResult(Result result) throws CustomException, CustomException;

    void saveCurObserv(Result resultJson);

    void saveCurObservDetails(Result resultJson);
}
