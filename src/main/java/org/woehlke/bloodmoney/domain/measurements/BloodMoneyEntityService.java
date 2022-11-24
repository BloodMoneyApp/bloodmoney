package org.woehlke.bloodmoney.domain.measurements;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BloodMoneyEntityService<E extends BloodMoneyEntity, ID extends Long> {

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
