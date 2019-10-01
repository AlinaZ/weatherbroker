package ru.bellintegrator.db_service.service;


import ru.bellintegrator.exception.CustomException;
import ru.bellintegrator.weatherparser.Result;

import javax.transaction.Transactional;


public interface DBService {

    /**
     * Получить данные о погоде для города из БД
     *
     * @param city название города
     * @return данные о погоде
     */
    Result getResult(String city, String region);

    /**
     * Сохранить данные о погоде в БД
     *
     * @param result данные о погоде для города, сохраненные админом
     */
    void saveResult(Result result) throws CustomException, CustomException;

    void saveCurObserv(Result resultJson);

    @Transactional
    void saveCurObservDetails(Result resultJson);
}
