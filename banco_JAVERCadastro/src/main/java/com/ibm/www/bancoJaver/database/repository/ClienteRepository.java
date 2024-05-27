package com.ibm.www.bancoJaver.database.repository;


import com.ibm.www.bancoJaver.database.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
