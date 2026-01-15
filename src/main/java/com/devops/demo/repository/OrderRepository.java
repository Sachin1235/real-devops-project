package com.devops.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devops.demo.model.Order;
public interface OrderRepository extends JpaRepository<Order, Long>{}
