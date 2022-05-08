package com.tenutz.cracknotifier.domain.crack;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CrackRepository extends JpaRepository<Crack, String>, CrackQueryRepository {

}
