package com.ifat.bdd.final_exam.repo;

import com.ifat.bdd.final_exam.model.loading.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface UserRepository extends JpaRepository<User, Long> {
}


