package ru.bellintegrator.db_service.service.condition;

import ru.bellintegrator.db_service.model.ConditionEntity;
import ru.bellintegrator.db_service.model.CurrentObservationEntity;
import ru.bellintegrator.db_service.service.WeatherElementService;
import ru.bellintegrator.weatherparser.Condition;

public interface ConditionService extends WeatherElementService<Condition, ConditionEntity, CurrentObservationEntity> {

}
