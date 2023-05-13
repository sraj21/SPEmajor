package com.Debtly10.Repository;

import com.Debtly10.models.Mortgage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MortgageRepository extends JpaRepository<Mortgage,Long> {
}
