package ru.bellintegrator.db_service.service.wind;

import ru.bellintegrator.db_service.model.CurrentObservationEntity;
import ru.bellintegrator.db_service.model.WindEntity;
import ru.bellintegrator.weatherparser.Wind;

public interface WindService {
    void saveWind(Wind windJson, CurrentObservationEntity currentObservationEntity);

    Wind mapEntityToView(WindEntity windEntity);
}
