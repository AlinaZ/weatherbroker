package ru.bellintegrator.db_service.service.condition;

import ru.bellintegrator.db_service.dao.astronomy.ResultDao;
import ru.bellintegrator.db_service.model.ConditionEntity;
import ru.bellintegrator.db_service.model.CurrentObservationEntity;
import ru.bellintegrator.weatherparser.Condition;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ConditionServiceImpl implements ConditionService {

    @Inject
    private ResultDao dao;

    @Override
    public void saveCondition(Condition conditionJson, CurrentObservationEntity currentObservationEntity) {
        ConditionEntity conditionEntity = new ConditionEntity();
        conditionEntity.setText(conditionJson.getText());
        conditionEntity.setCode(conditionJson.getCode());
        conditionEntity.setTemperature(conditionJson.getTemperature());


        conditionEntity.setCurrentObservation(currentObservationEntity);
        dao.saveCondition(conditionEntity);

    }
}
