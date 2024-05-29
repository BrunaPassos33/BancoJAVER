package com.ibm.www.bancoJaver.Cadastro.repository;


import com.ibm.www.bancoJaver.Cadastro.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
