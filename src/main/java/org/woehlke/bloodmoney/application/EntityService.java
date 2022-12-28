package org.woehlke.bloodmoney.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.woehlke.bloodmoney.domain.measurements.MeasurementEntity;

import java.util.List;
import java.util.Optional;

public interface EntityService<E extends MeasurementEntity, ID extends Long> {

    List<E> getAll();
    Page<E> getAll(Pageable pageable);
    long count();

    E getOne(ID id);
    Optional<E> findById(ID id);

    E add(E one);
    E update(E one, ID id);
    void delete(E one);
    void deleteAll();
}
