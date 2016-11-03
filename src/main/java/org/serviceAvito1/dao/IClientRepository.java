package org.serviceAvito1.dao;

import org.serviceAvito1.entities.Client;

import org.springframework.data.jpa.repository.JpaRepository;
 

public interface IClientRepository extends JpaRepository<Client, Long>{
	Client findByTel(String tel);
}
