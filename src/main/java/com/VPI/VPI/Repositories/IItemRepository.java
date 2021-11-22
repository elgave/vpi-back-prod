package com.VPI.VPI.Repositories;

import com.VPI.VPI.Entities.ItemEntity;
import com.VPI.VPI.Entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemRepository extends JpaRepository<ItemEntity, Integer> {

    List<ItemEntity> findByPedido(PedidoEntity pedido);

}


