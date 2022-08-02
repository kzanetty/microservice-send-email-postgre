package com.henriquezanetti.mssendmail.repository;

import com.henriquezanetti.mssendmail.model.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, Long> {
}
