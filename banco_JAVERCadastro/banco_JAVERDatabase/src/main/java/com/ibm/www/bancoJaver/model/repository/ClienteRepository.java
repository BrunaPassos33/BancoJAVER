package com.ibm.www.bancoJaver.model.repository;


import com.ibm.www.bancoJaver.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
