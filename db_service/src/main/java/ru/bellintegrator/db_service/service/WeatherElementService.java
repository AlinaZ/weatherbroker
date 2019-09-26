package ru.bellintegrator.db_service.service;

import ru.bellintegrator.db_service.model.BaseEntity;
import ru.bellintegrator.weatherparser.WeatherElement;



public interface WeatherElementService <W extends WeatherElement,B extends BaseEntity,B1 extends BaseEntity> {

    void saveElement(W element,B1 entity);

    W mapEntityToView  (B entity);
}
