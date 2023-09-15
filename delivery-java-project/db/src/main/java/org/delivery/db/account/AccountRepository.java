package org.delivery.db.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> { }
