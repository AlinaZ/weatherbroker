package ru.bellintegrator.db_service.service.condition;

import ru.bellintegrator.db_service.model.CurrentObservationEntity;
import ru.bellintegrator.weatherparser.Condition;

public interface ConditionService {
    void saveCondition(Condition conditionJson, CurrentObservationEntity currentObservationEntity);
}
